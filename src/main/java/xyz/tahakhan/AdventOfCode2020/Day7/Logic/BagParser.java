package xyz.tahakhan.AdventOfCode2020.Day7.Logic;

import lombok.*;

import java.util.ArrayList;

public class BagParser {
    public static Bag parse(String input) {
        val retVal = new Bag();
        // ex: "light red bags contain 1 bright white bag, 2 muted yellow bags."
        val split = input.split(" contain ");
        // ex: "light red bags"
        val bagColorName = split[0].split("bags")[0];
        // ex: "light red"
        retVal.setColor(bagColorName.trim());

        // ex: "1 bright white bag, 2 muted yellow bags."
        val innerBags = split[1]
                // ex: "1 bright white , 2 muted yellow"
                .replace("bags", "")
                .replace("bag", "")
                .replace(".", "")
                .trim()
                // ex: ["1 bright white", "2 muted yellow"]
                .split(", ");

        val innerBagsList = new ArrayList<InnerBag>();

        for (val bag : innerBags) {
            val innerBag = new InnerBag();
            // ex: ["1", "bright", "white"]
            val innerBagStringSplit = bag.split(" ");

            if (innerBagStringSplit[0].equals("no"))
                continue;
            // ex: 1
            val numberOfBags = Integer.parseInt(innerBagStringSplit[0]);
            // ex: "bright white"
            val innerBagColor = innerBagStringSplit[1] + " " + innerBagStringSplit[2];

            innerBag.setNumber(numberOfBags);
            innerBag.setColor(innerBagColor);

            innerBagsList.add(innerBag);
        }

        retVal.setInnerBags(innerBagsList);

        return retVal;
    }
}
