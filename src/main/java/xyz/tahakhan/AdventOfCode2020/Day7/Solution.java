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

    private final String SPECIAL_BAG = "shiny gold";

    @Override
    public void process() throws Exception {
        val input = readFile();

        val bags = input
                .stream()
                .map(BagParser::parse)
                .collect(Collectors.toList());

        System.out.println("Day 7 part 1 answer: " + partOne(bags));

        System.out.println("Day 7 part 2 answer: " + partTwo(bags));
    }

    //region Part One

    private int partOne(List<Bag> bags) {
        val map = createOrGetMap(bags);

        var totalBagsWithShinyGold = new HashSet<String>();
        for (val bag : bags)
            if (containsBag(bag, SPECIAL_BAG, map))
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

    //endregion

    //region Part Two

    private int partTwo(List<Bag> bags) {
        val map = createOrGetMap(bags);
        // numbers of bags minus the shiny gold bag
        return numberOfBags(map.get(SPECIAL_BAG), map) - 1;
    }

    private int numberOfBags(Bag bag, HashMap<String, Bag> map) {
        var numberOfInnerBags = 0;
        for (val innerBag : bag.getInnerBags()) {
            val actualBag = map.get(innerBag.getColor());
            numberOfInnerBags += innerBag.getNumber() * numberOfBags(actualBag, map);
        }
        // itself + other bags
        return 1 + numberOfInnerBags;
    }

    //endregion

    //region Helpers

    private HashMap<String, Bag> createOrGetMap(List<Bag> bags) {
        if (map != null)
            return map;
        val dictionary = new HashMap<String, Bag>();
        for (val bag : bags)
            dictionary.put(bag.getColor(), bag);

        map = dictionary;
        return dictionary;
    }

    private HashMap<String, Bag> map;

    //endregion
}
