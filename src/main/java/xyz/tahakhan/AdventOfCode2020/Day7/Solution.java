package xyz.tahakhan.AdventOfCode2020.Day7;

import lombok.*;
import xyz.tahakhan.AdventOfCode2020.Day7.Logic.Bag;
import xyz.tahakhan.AdventOfCode2020.Day7.Logic.BagParser;
import xyz.tahakhan.AdventOfCode2020.Helpers.BaseSolution;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Solution implements BaseSolution {
    @Override
    public void process() throws Exception {
        val input = readFile();

        val bags = input
                .stream()
                .map(BagParser::parse)
                .collect(Collectors.toList());

        System.out.println("Day 7 part 1 answer: " + partOne(bags));
    }

    private int partOne(List<Bag> bags) {
        val bagToFind = "shiny gold";
        val map = createMap(bags);

        var totalBagsWithShinyGold = new HashSet<String>();
        for (val bag : bags)
            if (containsBag(bag, bagToFind, map))
                totalBagsWithShinyGold.add(bag.getColor());

        return totalBagsWithShinyGold.size();
    }

    private boolean containsBag(Bag currentBag, String bagColorToFind, HashMap<String, Bag> bags) {
        for(val bag : currentBag.getInnerBags()) {
            val bagColor = bag.getColor();
            if (bagColor.equals(bagColorToFind))
                return true;
            if (containsBag(bags.get(bagColor), bagColorToFind, bags))
                return true;
        }
        return false;
    }

    private HashMap<String, Bag> createMap(List<Bag> bags) {
        val dictionary = new HashMap<String, Bag>();
        for (val bag : bags)
            dictionary.put(bag.getColor(), bag);

        return dictionary;
    }
}
