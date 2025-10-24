package eu.epitech.t_dev_700.controllers;

import eu.epitech.t_dev_700.services.MembershipService;
import eu.epitech.t_dev_700.services.PlanningService;
import eu.epitech.t_dev_700.services.components.UserAuthorization;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PlanningController.class)
public class PlanningAuthTest extends AbstractAuthTest {

    static String REQUEST_BODY = """
            {
                    "userId": 1,
                    "weekDay": 1,
                    "startTime": "08:00",
                    "endTime": "12:00"
            }
            """;

    @TestConfiguration
    @EnableMethodSecurity(proxyTargetClass = true)
    public static class MethodSecurityTestConfig {
        @Bean
        UserAuthorization userAuth(MembershipService membershipService, PlanningService planningService) {
            return new UserAuthorization(membershipService, planningService);
        }

        @Bean
        SecurityFilterChain testSecurity(HttpSecurity http) throws Exception {
            http
                    .csrf(AbstractHttpConfigurer::disable)
                    .authorizeHttpRequests(auth -> auth.anyRequest().permitAll());
            return http.build();
        }
    }

    @Test
    void testAuth_getPlannings_admin() throws Exception {
        doTestRequestForAuthExpectCode(
                authForAdmin(),
                get("/plannings"),
                status().isOk());
    }

    @Test
    void testAuth_getPlannings_user() throws Exception {
        doTestRequestForAuthExpectCode(
                authForUser(),
                get("/plannings"),
                status().isForbidden());
    }

    @Test
    void testAuth_getPlannings_manager() throws Exception {
        doTestRequestForAuthExpectCode(
                authForManager(),
                get("/plannings"),
                status().isForbidden());
    }

    @Test
    void testAuth_getPlanning_admin() throws Exception {
        doTestRequestForAuthExpectCode(
                authForAdmin(),
                get("/plannings/1"),
                status().isOk());
    }

    @Test
    void testAuth_getPlanning_own() throws Exception {
        doTestRequestForAuthExpectCode(
                authForUser(),
                get("/plannings/1"),
                status().isOk());
    }

    @Test
    void testAuth_getPlanning_other() throws Exception {
        doTestRequestForAuthExpectCode(
                authForManager(),
                get("/plannings/2"),
                status().isForbidden());
    }

    @Test
    void testAuth_getPlanning_manager() throws Exception {
        doTestRequestForAuthExpectCode(
                authForManager(),
                get("/plannings/1"),
                status().isOk());
    }

    @Test
    void testAuth_getPlanning_nonManager() throws Exception {
        doTestRequestForAuthExpectCode(
                authForManager(),
                get("/plannings/2"),
                status().isForbidden());
    }

    @Test
    void testAuth_postPlanning_admin() throws Exception {
        doTestRequestForAuthExpectCode(
                authForAdmin(),
                post("/plannings").contentType(MediaType.APPLICATION_JSON).content(REQUEST_BODY),
                status().isCreated());
    }

    @Test
    void testAuth_postPlanning_owner() throws Exception {
        doTestRequestForAuthExpectCode(
                authForUser(),
                post("/plannings").contentType(MediaType.APPLICATION_JSON).content(REQUEST_BODY),
                status().isForbidden());
    }

    @Test
    void testAuth_postPlanning_manager() throws Exception {
        doTestRequestForAuthExpectCode(
                authForManager(),
                post("/plannings").contentType(MediaType.APPLICATION_JSON).content(REQUEST_BODY),
                status().isForbidden());
    }

    @Test
    void testAuth_putPlanning_admin() throws Exception {
        doTestRequestForAuthExpectCode(
                authForAdmin(),
                put("/plannings/1").contentType(MediaType.APPLICATION_JSON).content(REQUEST_BODY),
                status().isOk());
    }

    @Test
    void testAuth_putPlanning_own() throws Exception {
        doTestRequestForAuthExpectCode(
                authForUser(),
                put("/plannings/1").contentType(MediaType.APPLICATION_JSON).content(REQUEST_BODY),
                status().isForbidden());
    }

    @Test
    void testAuth_putPlanning_other() throws Exception {
        doTestRequestForAuthExpectCode(
                authForManager(),
                put("/plannings/2").contentType(MediaType.APPLICATION_JSON).content(REQUEST_BODY),
                status().isForbidden());
    }

    @Test
    void testAuth_putPlanning_manager() throws Exception {
        doTestRequestForAuthExpectCode(
                authForManager(),
                put("/plannings/1").contentType(MediaType.APPLICATION_JSON).content(REQUEST_BODY),
                status().isOk());
    }

    @Test
    void testAuth_putPlanning_nonManager() throws Exception {
        doTestRequestForAuthExpectCode(
                authForManager(),
                put("/plannings/2").contentType(MediaType.APPLICATION_JSON).content(REQUEST_BODY),
                status().isForbidden());
    }

    @Test
    void testAuth_patchPlanning_admin() throws Exception {
        doTestRequestForAuthExpectCode(
                authForAdmin(),
                patch("/plannings/1").contentType(MediaType.APPLICATION_JSON).content("{\"weekDay\": 1}"),
                status().isOk());
    }

    @Test
    void testAuth_patchPlanning_own() throws Exception {
        doTestRequestForAuthExpectCode(
                authForUser(),
                patch("/plannings/1").contentType(MediaType.APPLICATION_JSON).content("{\"weekDay\": 1}"),
                status().isForbidden());
    }

    @Test
    void testAuth_patchPlanning_other() throws Exception {
        doTestRequestForAuthExpectCode(
                authForManager(),
                patch("/plannings/2").contentType(MediaType.APPLICATION_JSON).content("{\"weekDay\": 1}"),
                status().isForbidden());
    }

    @Test
    void testAuth_patchPlanning_manager() throws Exception {
        doTestRequestForAuthExpectCode(
                authForManager(),
                patch("/plannings/1").contentType(MediaType.APPLICATION_JSON).content("{\"weekDay\": 1}"),
                status().isOk());
    }

    @Test
    void testAuth_patchPlanning_nonManager() throws Exception {
        doTestRequestForAuthExpectCode(
                authForManager(),
                patch("/plannings/2").contentType(MediaType.APPLICATION_JSON).content("{\"weekDay\": 1}"),
                status().isForbidden());
    }

    @Test
    void testAuth_deletePlanning_admin() throws Exception {
        doTestRequestForAuthExpectCode(
                authForAdmin(),
                delete("/plannings/1"),
                status().isNoContent());
    }

    @Test
    void testAuth_deletePlanning_own() throws Exception {
        doTestRequestForAuthExpectCode(
                authForUser(),
                delete("/plannings/1"),
                status().isForbidden());
    }

    @Test
    void testAuth_deletePlanning_other() throws Exception {
        doTestRequestForAuthExpectCode(
                authForManager(),
                delete("/plannings/2"),
                status().isForbidden());
    }

    @Test
    void testAuth_deletePlanning_manager() throws Exception {
        doTestRequestForAuthExpectCode(
                authForManager(),
                delete("/plannings/1"),
                status().isNoContent());
    }

    @Test
    void testAuth_deletePlanning_nonManager() throws Exception {
        doTestRequestForAuthExpectCode(
                authForManager(),
                delete("/plannings/2"),
                status().isForbidden());
    }

}
