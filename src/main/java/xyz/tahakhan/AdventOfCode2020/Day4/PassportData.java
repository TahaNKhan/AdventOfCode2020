package xyz.tahakhan.AdventOfCode2020.Day4;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PassportData {
    private String birthYear;
    private String issueYear;
    private String expirationYear;
    private String height;
    private String hairColor;
    private String eyeColor;
    private String passportID;
    private String countryID;

    @Override
    public String toString() {
        return "PassportData{" +
                "birthYear='" + birthYear + '\'' +
                ", issueYear='" + issueYear + '\'' +
                ", expirationYear='" + expirationYear + '\'' +
                ", height='" + height + '\'' +
                ", hairColor='" + hairColor + '\'' +
                ", eyeColor='" + eyeColor + '\'' +
                ", passportID='" + passportID + '\'' +
                ", countryID='" + countryID + '\'' +
                '}';
    }
}
