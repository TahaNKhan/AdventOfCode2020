package xyz.tahakhan.AdventOfCode2020.Day6.Logic;

import lombok.val;
import lombok.var;

import java.util.*;

public class CustomsAnswersParser {

    public static List<List<String>> parseGroups(List<String> input)
    {
        val retVal = new ArrayList<List<String>>();

        var groupBuffer = new ArrayList<String>();
        for(val line : input) {
            if (line.isEmpty()) {
                retVal.add(groupBuffer);
                groupBuffer = new ArrayList<>();
                continue;
            }
            groupBuffer.add(line);
        }

        // add the last one if there is one
        if (groupBuffer.size() > 0)
            retVal.add(groupBuffer);

        return retVal;
    }

    public static int numberOfPopularAnswers(List<String> groupAnswers) {
        val answersDictionary = new HashMap<Character, Integer>();
        for (val personAnswer : groupAnswers) {
            for (val answer : personAnswer.toCharArray()) {
                if (answersDictionary.containsKey(answer)) {
                    val newVal = answersDictionary.get(answer) + 1;
                    answersDictionary.put(answer, newVal);
                } else {
                    answersDictionary.put(answer, 1);
                }
            }
        }
        var retVal = 0;
        for(var key : answersDictionary.keySet()) {
            if (answersDictionary.get(key) == groupAnswers.size())
                retVal++;
        }
        return retVal;
    }

    public static int numberOfUniqueAnswers(List<String> groupAnswers) {
        val answers = new HashSet<Character>();
        for (val personAnswer : groupAnswers) {
            for (val answer : personAnswer.toCharArray()) {
                answers.add(answer);
            }
        }
        return answers.size();
    }
}
