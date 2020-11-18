package spm.project.restaurantrecommendation.constraint;

import org.passay.PasswordData;
import org.passay.PasswordValidator;
import org.passay.RuleResult;
import org.passay.WhitespaceRule;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {
    @Override
    public void initialize(ValidPassword validPassword) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        PasswordValidator validator = new PasswordValidator(Arrays.asList(
                //new LengthRule(8, 70),
                //new UppercaseCharacterRule(1),
                //new DigitCharacterRule(1),
                //new SpecialCharacterRule(1),
                //new NumericalSequenceRule(3, false),
                //new AlphabeticalSequenceRule(3, false),
                //new QwertySequenceRule(3, false),
                new WhitespaceRule()));

        RuleResult result = validator.validate(new PasswordData(s));
        if (result.isValid()) {
            return true;
        }
        List<String> messages = validator.getMessages(result);
        String messageTemplate = messages.stream().collect(Collectors.joining(","));
        constraintValidatorContext.buildConstraintViolationWithTemplate(messageTemplate)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();
        return false;
    }
}

