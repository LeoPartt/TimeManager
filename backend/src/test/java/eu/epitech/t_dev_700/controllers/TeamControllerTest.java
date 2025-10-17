package eu.epitech.t_dev_700.controllers;

import eu.epitech.t_dev_700.models.TeamModels;
import eu.epitech.t_dev_700.services.TeamService;
import eu.epitech.t_dev_700.services.exceptions.ResourceNotFound;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TeamController.class)
@ActiveProfiles("test")
@AutoConfigureMockMvc(addFilters = false)
class TeamControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TeamService teamService;

    private TeamModels.TeamModel teamModel;
    private TeamModels.TeamModel[] teamModels;

    @BeforeEach
    void setUp() {
        teamModel = new TeamModels.TeamModel(
                1L,
                "Development Team",
                "A team of developers"
        );

        teamModels = new TeamModels.TeamModel[]{teamModel};
    }

    @Test
    void testGet_whenTeamExists_shouldReturnTeam() throws Exception {
        when(teamService.get(1L)).thenReturn(teamModel);

        mockMvc.perform(get("/teams/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Development Team"))
                .andExpect(jsonPath("$.description").value("A team of developers"));
    }

    @Test
    void testGet_whenTeamNotExists_shouldReturn404() throws Exception {
        when(teamService.get(999L)).thenThrow(new ResourceNotFound("Team", 999L));

        mockMvc.perform(get("/teams/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetAll_shouldReturnAllTeams() throws Exception {
        when(teamService.list()).thenReturn(teamModels);

        mockMvc.perform(get("/teams"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Development Team"));
    }

    @Test
    void testPost_shouldCreateTeam() throws Exception {
        String requestBody = """
                {
                    "name": "Development Team",
                    "description": "A team of developers"
                }
                """;

        when(teamService.create(any())).thenReturn(teamModel);

        mockMvc.perform(post("/teams")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Development Team"));
    }

    @Test
    void testPost_withInvalidData_shouldReturn422() throws Exception {
        String requestBody = """
                {
                    "name": "",
                    "description": "Description"
                }
                """;

        mockMvc.perform(post("/teams")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void testPost_withMissingRequiredFields_shouldReturn422() throws Exception {
        String requestBody = """
                {
                    "description": "Only description"
                }
                """;

        mockMvc.perform(post("/teams")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void testPut_shouldReplaceTeam() throws Exception {
        String requestBody = """
                {
                    "name": "Updated Team",
                    "description": "Updated description"
                }
                """;

        TeamModels.TeamModel updatedModel = new TeamModels.TeamModel(
                1L,
                "Updated Team",
                "Updated description"
        );

        when(teamService.replace(eq(1L), any())).thenReturn(updatedModel);

        mockMvc.perform(put("/teams/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Team"))
                .andExpect(jsonPath("$.description").value("Updated description"));
    }

    @Test
    void testPut_whenTeamNotExists_shouldReturn404() throws Exception {
        String requestBody = """
                {
                    "name": "Team",
                    "description": "Description"
                }
                """;

        when(teamService.replace(eq(999L), any()))
                .thenThrow(new ResourceNotFound("Team", 999L));

        mockMvc.perform(put("/teams/999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isNotFound());
    }

    @Test
    void testPatch_shouldUpdateTeam() throws Exception {
        String requestBody = """
                {
                    "description": "New description only"
                }
                """;

        TeamModels.TeamModel patchedModel = new TeamModels.TeamModel(
                1L,
                "Development Team",
                "New description only"
        );

        when(teamService.update(eq(1L), any())).thenReturn(patchedModel);

        mockMvc.perform(patch("/teams/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Development Team")) // Should remain unchanged
                .andExpect(jsonPath("$.description").value("New description only"));
    }

    @Test
    void testPatch_withNameOnly_shouldUpdateName() throws Exception {
        String requestBody = """
                {
                    "name": "New Team Name"
                }
                """;

        TeamModels.TeamModel patchedModel = new TeamModels.TeamModel(
                1L,
                "New Team Name",
                "A team of developers"
        );

        when(teamService.update(eq(1L), any())).thenReturn(patchedModel);

        mockMvc.perform(patch("/teams/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("New Team Name"))
                .andExpect(jsonPath("$.description").value("A team of developers"));
    }

    @Test
    void testPatch_whenTeamNotExists_shouldReturn404() throws Exception {
        String requestBody = """
                {
                    "name": "New Name"
                }
                """;

        when(teamService.update(eq(999L), any()))
                .thenThrow(new ResourceNotFound("Team", 999L));

        mockMvc.perform(patch("/teams/999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDelete_shouldDeleteTeam() throws Exception {
        mockMvc.perform(delete("/teams/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDelete_whenTeamNotExists_shouldReturn404() throws Exception {
        doThrow(new ResourceNotFound("Team", 999L))
                .when(teamService).delete(999L);

        mockMvc.perform(delete("/teams/999"))
                .andExpect(status().isNotFound());
    }
}
