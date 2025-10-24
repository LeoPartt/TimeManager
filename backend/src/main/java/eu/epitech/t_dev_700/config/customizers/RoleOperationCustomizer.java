package eu.epitech.t_dev_700.config.customizers;

import eu.epitech.t_dev_700.doc.ApiAuthRoles;
import eu.epitech.t_dev_700.services.components.UserAuthorization;
import eu.epitech.t_dev_700.utils.AuthRole;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.Arrays;

@Configuration
public class RoleOperationCustomizer {

    @Bean
    public OperationCustomizer customizeRoleOperation() {
        return (operation, handlerMethod) -> {

            PreAuthorize preAuthorize = handlerMethod.getMethodAnnotation(PreAuthorize.class);

            if (preAuthorize != null) {

                String value = preAuthorize.value();

                String methodName = value.substring(value.lastIndexOf('.') + 1, value.indexOf('('));

                Arrays.stream(UserAuthorization.class.getMethods()).filter(m -> m.getName().equals(methodName)).findFirst().ifPresent(method -> {
                    ApiAuthRoles apiAuthRoles = method.getAnnotation(ApiAuthRoles.class);
                    if (apiAuthRoles != null) {
                        AuthRole[] roles = apiAuthRoles.value();

                        String description = (operation.getDescription() == null ? "" : operation.getDescription());
                        String roleList = String.join(", ", Arrays.stream(roles).map(AuthRole::getRole).toList()).trim();

                        operation.setDescription((description + "\n\n") + "**Requires roles:** " + roleList);

                        operation.addSecurityItem(new SecurityRequirement()
                                .addList("bearerAuth"));

                    }
                });
            }
            return operation;
        };
    }
}
