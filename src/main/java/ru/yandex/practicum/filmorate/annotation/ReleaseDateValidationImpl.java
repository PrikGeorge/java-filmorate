package ru.yandex.practicum.filmorate.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @project java-filmorate
 * @auther George Prikashchenkov on 31.10.2022
 */
public class ReleaseDateValidationImpl implements ConstraintValidator<ReleaseDateValidation, LocalDate> {

    private String dateStart;

    @Override
    public void initialize(ReleaseDateValidation context) {
        dateStart = context.dateStart();
    }

    @Override
    public boolean isValid(LocalDate localDate, ConstraintValidatorContext constraintValidatorContext) {
        LocalDate currentDate = LocalDate.parse(dateStart, DateTimeFormatter.ofPattern("yyyy.MM.dd"));
        return currentDate.isBefore(localDate);
    }

}
