package xyz.tahakhan.AdventOfCode2020.Day7.Logic;

import lombok.*;
import java.util.List;

@Getter
@Setter
public class Bag {
    private String color;
    private List<InnerBag> innerBags;
}

