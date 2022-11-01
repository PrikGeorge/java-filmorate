package ru.yandex.practicum.filmorate.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ReleaseDateValidationImpl.class)
public @interface ReleaseDateValidation {

    String dateStart();

    String message() default "{ru.yandex.practicum.filmorate.annotation.ReleaseDateValidation.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
