package xyz.tahakhan.AdventOfCode2020.Helpers;

import com.google.gson.Gson;

public class Utilities {
    private static Gson gson = new Gson();
    public static Object DeepClone(Object obj) {
        return gson.fromJson(gson.toJson(obj), obj.getClass());
    }
}
