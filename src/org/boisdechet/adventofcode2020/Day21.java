package org.boisdechet.adventofcode2020;

import org.boisdechet.adventofcode2020.utils.InputUtil;
import org.boisdechet.adventofcode2020.utils.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day21 {

    private static class Result {
        long ingredientOKCount = 0;
        String dangerousList = "";
    }

    public static Result solve(String sample) throws IOException {

        Map<String, Set<String>> allergens = new HashMap<>(); // [allergen, list of potential matching ingredient]
        Map<String, Integer> ingredients = new HashMap<>();   // [ingredient, total occ. of that ingredient in food

        // first loop => retrieve all ingredients & allergens, and count occurrences
        BufferedReader input = InputUtil.readInput(21, sample);
        String food = "";
        Pattern p = Pattern.compile("(.+) \\(contains (.+)\\)");
        while ((food = input.readLine()) != null) {
            Matcher m = p.matcher(food);
            if (!m.matches()) throw new IllegalStateException("Invalid input : " + food);
            Set<String> allerg =  new HashSet<>(Arrays.asList(m.group(2).split(", ")));
            Set<String> ingred = new HashSet<>(Arrays.asList(m.group(1).split(" ")));
            for(String a : allerg) {
                allergens.put(a, new HashSet<>()); // initialize with empty potential list because not all ingredient known yet
            }
            // increase total number of ingredients
            for (String i : ingred) {
                if (!ingredients.containsKey(i)) ingredients.put(i, 1);
                else ingredients.put(i, ingredients.get(i) + 1);
            }
        }

        // fill allergens with all ingredients as potential
        for(Set<String> potList : allergens.values()) {
            potList.addAll(ingredients.keySet());
        }

        // second loop => remove from potential list if ingredient is not present in food when allergen is listed
        input = InputUtil.readInput(21, sample);
        while ((food = input.readLine()) != null) {
            Matcher m = p.matcher(food);
            if (!m.matches()) throw new IllegalStateException("Invalid input : " + food);
            Set<String> allerg =  new HashSet<>(Arrays.asList(m.group(2).split(", ")));
            Set<String> ingred = new HashSet<>(Arrays.asList(m.group(1).split(" ")));
            for(String a : allerg) {
                Set<String> potList = allergens.get(a);
                for(String pot : new HashSet<>(potList)) {
                    if(!ingred.contains(pot)) {
                        potList.remove(pot);
                    }
                }
            }
        }

        // match allergens => 1 ingredient
        boolean unsolved = true;
        while(unsolved) {
            unsolved = false;
            for (String a : allergens.keySet()) {
                //System.out.println(String.format("%s has %d potential", a, allergens.get(a).size()));
                if (allergens.get(a).size() == 1) {
                    // remove ingredient from all others allergens
                    String ingredient = (String)allergens.get(a).toArray()[0];
                    for (String a2 : allergens.keySet()) {
                        if(!a2.equals(a)) {
                            allergens.get(a2).remove(ingredient);
                        }
                    }
                } else {
                    unsolved = true;
                }
            }
        }

        // build list of ingredients with allergens
        Set<String> ingrWithAllergens = new HashSet<>();
        for(Set<String> list : allergens.values()) {
            ingrWithAllergens.add((String)list.toArray()[0]);
        }

        // count all the other ingredients
        long count = 0L;
        for(String i : ingredients.keySet()) {
            if(!ingrWithAllergens.contains(i)) {
                count += ingredients.get(i);
            }
        }

        // create result
        Result r = new Result();
        r.ingredientOKCount = count;

        List<String> allergensSorted = new ArrayList<>(allergens.keySet());
        Collections.sort(allergensSorted);
        StringBuilder builder = new StringBuilder();
        for(String a : allergensSorted) {
            builder.append(allergens.get(a).toArray()[0]).append(',');
        }
        builder.deleteCharAt(builder.length()-1);
        r.dangerousList = builder.toString();

        return r;
    }

    /**
     * Part 1
     */
    public static long part1(String sample) throws IOException {
        return solve(sample).ingredientOKCount;
    }

    /**
     * Part 2
     */
    public static String part2(String sample) throws IOException {
        return solve(sample).dangerousList;
    }

    public static void main(String[] args) {
        Log.welcome();
        try {
            Log.i(String.format("Times any of non-allergic ingredients appear : %d", part1(null)));
            Log.i(String.format("Canonical dangerous ingredient list : %s", part2(null)));
        } catch(Exception exc) {
            Log.w(String.format("Error during execution: %s", exc.getMessage()));
        }
        Log.bye();
    }


}
