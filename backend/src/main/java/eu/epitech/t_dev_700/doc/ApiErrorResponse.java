package eu.epitech.t_dev_700.doc;


import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiErrorResponse {
    Class<? extends Throwable>[] value();
}

