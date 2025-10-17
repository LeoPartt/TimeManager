package eu.epitech.t_dev_700.controllers;

import eu.epitech.t_dev_700.services.TeamService;
import eu.epitech.t_dev_700.services.exceptions.ResourceNotFound;
import eu.epitech.t_dev_700.models.UserModels;
import eu.epitech.t_dev_700.services.ClockService;
import eu.epitech.t_dev_700.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
@ActiveProfiles("test")
@AutoConfigureMockMvc(addFilters = false)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;

    @MockitoBean
    private TeamService teamService;

    @MockitoBean
    private ClockService clockService;

    private UserModels.UserModel userModel;
    private UserModels.UserModel[] userModels;

    @BeforeEach
    void setUp() {
        userModel = new UserModels.UserModel(
                1L,
                "johndoe",
                "John",
                "Doe",
                "john.doe@example.com",
                "+1234567890"
        );

        userModels = new UserModels.UserModel[]{userModel};
    }

    @Test
    void testGet_whenUserExists_shouldReturnUser() throws Exception {
        when(userService.get(1L)).thenReturn(userModel);

        mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.username").value("johndoe"))
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Doe"))
                .andExpect(jsonPath("$.email").value("john.doe@example.com"))
                .andExpect(jsonPath("$.phoneNumber").value("+1234567890"));
    }

    @Test
    void testGet_whenUserNotExists_shouldReturn404() throws Exception {
        when(userService.get(999L)).thenThrow(new ResourceNotFound("User", 999L));

        mockMvc.perform(get("/users/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetAll_shouldReturnAllUsers() throws Exception {
        when(userService.list()).thenReturn(userModels);

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].firstName").value("John"));
    }

    @Test
    void testPost_shouldCreateUser() throws Exception {
        String requestBody = """
                {
                    "username": "johndoe",
                    "password": "password123",
                    "firstName": "John",
                    "lastName": "Doe",
                    "email": "john.doe@example.com",
                    "phoneNumber": "+1234567890"
                }
                """;

        when(userService.create(any())).thenReturn(userModel);

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.firstName").value("John"));
    }

    @Test
    void testPost_withInvalidData_shouldReturn422() throws Exception {
        String requestBody = """
                {
                    "username": "",
                    "password": "password123",
                    "firstName": "",
                    "lastName": "Doe",
                    "email": "invalid-email",
                    "phoneNumber": "+1234567890"
                }
                """;

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void testPost_withMissingRequiredFields_shouldReturn422() throws Exception {
        String requestBody = """
                {
                    "username": "johndoe"
                }
                """;

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void testPut_shouldReplaceUser() throws Exception {
        String requestBody = """
                {
                    "username": "johndoe",
                    "firstName": "UpdatedJohn",
                    "lastName": "UpdatedDoe",
                    "email": "updated@example.com",
                    "phoneNumber": "+9999999999"
                }
                """;

        UserModels.UserModel updatedModel = new UserModels.UserModel(
                1L,
                "johndoe",
                "UpdatedJohn",
                "UpdatedDoe",
                "updated@example.com",
                "+9999999999"
        );

        when(userService.replace(eq(1L), any())).thenReturn(updatedModel);

        mockMvc.perform(put("/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("UpdatedJohn"))
                .andExpect(jsonPath("$.email").value("updated@example.com"));
    }

    @Test
    void testPut_whenUserNotExists_shouldReturn404() throws Exception {
        String requestBody = """
                {
                    "username": "johndoe",
                    "firstName": "John",
                    "lastName": "Doe",
                    "email": "john@example.com",
                    "phoneNumber": "+123456"
                }
                """;

        when(userService.replace(eq(999L), any()))
                .thenThrow(new ResourceNotFound("User", 999L));

        mockMvc.perform(put("/users/999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isNotFound());
    }

    @Test
    void testPatch_shouldUpdateUser() throws Exception {
        String requestBody = """
                {
                    "firstName": "Jane"
                }
                """;

        UserModels.UserModel patchedModel = new UserModels.UserModel(
                1L,
                "johndoe",
                "Jane",
                "Doe",
                "john.doe@example.com",
                "+1234567890"
        );

        when(userService.update(eq(1L), any())).thenReturn(patchedModel);

        mockMvc.perform(patch("/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Jane"))
                .andExpect(jsonPath("$.lastName").value("Doe")); // Should remain unchanged
    }

    @Test
    void testPatch_whenUserNotExists_shouldReturn404() throws Exception {
        String requestBody = """
                {
                    "firstName": "Jane"
                }
                """;

        when(userService.update(eq(999L), any()))
                .thenThrow(new ResourceNotFound("User", 999L));

        mockMvc.perform(patch("/users/999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDelete_shouldDeleteUser() throws Exception {
        mockMvc.perform(delete("/users/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDelete_whenUserNotExists_shouldReturn404() throws Exception {
        doThrow(new ResourceNotFound("User", 999L))
                .when(userService).delete(999L);

        mockMvc.perform(delete("/users/999"))
                .andExpect(status().isNotFound());
    }
}
