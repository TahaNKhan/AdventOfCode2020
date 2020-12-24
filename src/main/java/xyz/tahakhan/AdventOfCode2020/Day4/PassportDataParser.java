package xyz.tahakhan.AdventOfCode2020.Day4;

import java.util.List;

public class PassportDataParser {
    public static PassportData parse(List<String> input) {
        PassportData retVal = new PassportData();
        for(String line : input) {
            String[] splitLine = line.split(" ");
            for(int j = 0; j < splitLine.length; j++) {
                String[] splitKV = splitLine[j].split(":");
                String key = splitKV[0];
                String value = splitKV[1];
                switch (key)
                {
                    case "byr":
                        retVal.setBirthYear(value);
                        break;
                    case "iyr":
                        retVal.setIssueYear(value);
                        break;
                    case "eyr":
                        retVal.setExpirationYear(value);
                        break;
                    case "hgt":
                        retVal.setHeight(value);
                        break;
                    case "hcl":
                        retVal.setHairColor(value);
                        break;
                    case "ecl":
                        retVal.setEyeColor(value);
                        break;
                    case "pid":
                        retVal.setPassportID(value);
                        break;
                    case "cid":
                        retVal.setCountryID(value);
                        break;
                }
            }
        }
        return retVal;
    }
}
