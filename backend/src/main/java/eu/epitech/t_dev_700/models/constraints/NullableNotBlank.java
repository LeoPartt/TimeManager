package eu.epitech.t_dev_700.models.constraints;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NullableNotBlank.Validator.class)
public @interface NullableNotBlank {

    String message() default "must not be empty if provided";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    class Validator implements ConstraintValidator<NullableNotBlank, CharSequence> {
        @Override
        public boolean isValid(CharSequence value, ConstraintValidatorContext ctx) {
            return (value == null || !value.toString().trim().isEmpty());
        }
    }
}
