package xyz.tahakhan.AdventOfCode2020.Day4;

import lombok.val;
import lombok.var;

import java.util.Arrays;
import java.util.HashSet;
import java.util.regex.*;

public class PassportDataValidator {
    private static final HashSet<String> validEyeColors = new HashSet<>(Arrays.asList("amb", "blu", "brn", "gry", "grn", "hzl", "oth"));

    public boolean validatePartOne(PassportData data) {
        return data.getBirthYear() != null
                && data.getIssueYear() != null
                && data.getExpirationYear() != null
                && data.getHeight() != null
                && data.getHairColor() != null
                && data.getEyeColor() != null
                && data.getPassportID() != null;
    }

    public boolean validatePartTwo(PassportData data) {
        return isBirthYearValid(data)
                && isExpirationYearValid(data)
                && isIssueYearValid(data)
                && isHeightValid(data)
                && isHairColorValid(data)
                && isEyeColorValid(data)
                && isPassportIDValid(data);
    }

    private boolean isPassportIDValid(PassportData data) {
        var passportID = data.getPassportID();
        if (passportID == null || passportID.isEmpty())
            return false;

        passportID = passportID.trim();
        var passportIDRegex = "[0-9]{9}";
        var pattern = Pattern.compile(passportIDRegex);
        var matcher = pattern.matcher(passportID);

        return matcher.find();
    }

    private boolean isEyeColorValid(PassportData data) {
        val eyeColor = data.getEyeColor();
        if (eyeColor == null || eyeColor.isEmpty())
            return false;
        return validEyeColors.contains(eyeColor);
    }

    private boolean isHairColorValid(PassportData data) {
        var hairColor = data.getHairColor();
        if (hairColor == null || hairColor.isEmpty())
            return false;

        String regexPattern = "#+[0-9|a-f]{6}";
        Pattern hairColorPattern = Pattern.compile(regexPattern, Pattern.CASE_INSENSITIVE);
        Matcher matcher = hairColorPattern.matcher(hairColor);
        return matcher.find();
    }

    private boolean isBirthYearValid(PassportData data) {
        return validateYear(data.getBirthYear(), 1920, 2002);
    }

    private boolean isIssueYearValid(PassportData data) {
        return validateYear(data.getIssueYear(), 2010, 2020);
    }

    private boolean isExpirationYearValid(PassportData data) {
        return validateYear(data.getExpirationYear(), 2020, 2030);
    }

    private boolean isHeightValid(PassportData data) {
        String height = data.getHeight();
        if (height == null || height.isEmpty())
            return false;

        try {
            if (height.contains("cm")) {
                String value = height.trim().split("cm")[0];
                int parsed = Integer.parseInt(value);
                return parsed >= 150 && parsed <= 193;
            } else if (height.contains("in")) {
                String value = height.trim().split("in")[0];
                int parsed = Integer.parseInt(value);
                return parsed >= 59 && parsed <= 76;
            } else
                return false;
        } catch (NumberFormatException ex) {
            logBadLine(data, "height");
            return false;
        }
    }

    private boolean validateYear(String value, int min, int max) {
        if (value == null || value.isEmpty())
            return false;
        if (value.length() > 4)
            return false;
        try {
            int parsedYear = Integer.parseInt(value);
            return parsedYear >= min && parsedYear <= max;
        } catch (NumberFormatException ex) {
            logBadLine(value, "year");
            return false;
        }
    }

    private void logBadLine(Object data, String field) {
        System.out.println("bad passport: " + data);
        System.out.println("bad field: " + field);
    }
}
