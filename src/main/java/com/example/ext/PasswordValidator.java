package com.example.ext;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by home on 29.01.16.
 */
@Component
public class PasswordValidator {

    Pattern pattern;
    Matcher matcher;
    private StringBuilder patternBuilder;

    public StringBuilder getPatternBuilder() {
        return patternBuilder;
    }

    public PasswordValidator() {
        buildValidator(false, true, true, 6, 30);
    }

    public void buildValidator(boolean forceSpecialChar,
                               boolean forceCapitalLetter,
                               boolean forceNumber,
                               int minLength,
                               int maxLength) {
        patternBuilder = new StringBuilder("((?=.*[a-z])");

        if (forceSpecialChar) {
            patternBuilder.append("(?=.*[@#$%])");
        }

        if (forceCapitalLetter) {
            patternBuilder.append("(?=.*[A-Z])");
        }

        if (forceNumber) {
            patternBuilder.append("(?=.*[0-9])");
        }

        patternBuilder.append(".{").append(minLength).append(",").append(maxLength).append("})");
        String PASSWORD_PATTERN = patternBuilder.toString();
        pattern = Pattern.compile(PASSWORD_PATTERN);
    }


    /**
     * Validate password with regular expression
     *
     * @param password password for validation
     * @return true valid password, false invalid password
     */
    public boolean validate(final String password) {
        matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
