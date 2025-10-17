package eu.epitech.t_dev_700.doc;

import io.swagger.v3.oas.annotations.Operation;
import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@ApiForbiddenResponse
public @interface ApiRoleProtected {

    /** Roles required for this operation */
    String[] roles() default {};

}