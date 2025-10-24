package eu.epitech.t_dev_700.config.customizers;

import eu.epitech.t_dev_700.doc.ApiErrorResponse;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConflictDetailExtractor {

    @Bean
    public OperationCustomizer extractConflictDetail() {
        return (operation, handlerMethod) -> {
            ApiErrorResponse apiErrorResponse = handlerMethod.getMethodAnnotation(ApiErrorResponse.class);
            if (apiErrorResponse != null) operation.addExtension("x-errors", apiErrorResponse.value());
            return operation;
        };
    }
}
