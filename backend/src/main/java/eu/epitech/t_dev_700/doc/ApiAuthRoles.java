package eu.epitech.t_dev_700.doc;

import eu.epitech.t_dev_700.utils.AuthRole;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiAuthRoles {
    AuthRole[] value();
}
