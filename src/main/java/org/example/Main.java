package org.example;

import java.util.HashMap;
import java.util.Map;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";

    public static void main(String[] args) {

        System.out.println(checkAnagram("hola".toLowerCase(), "al;oh".toLowerCase()));

    }

    private static boolean checkAnagram(String origin, String prospect) {

        Map<Character, Integer> originCountMap = getLetterCount(origin);
        Map<Character, Integer> prospectCountMap = getLetterCount(prospect);

        return originCountMap.size() == prospectCountMap.size() &&
                originCountMap.entrySet().stream()
                        .filter(entry -> !prospectCountMap.get(entry.getKey()).equals(entry.getValue()))
                        .findAny()
                        .isEmpty();

    }

    private static Map<Character, Integer> getLetterCount(String prospect) {
        Map<Character, Integer> countingLettersMap = new HashMap<>();

        for(int i= 0; i < prospect.length(); i++) {

            if(ALPHABET.contains(String.valueOf(prospect.charAt(i)))) {
                if(countingLettersMap.containsKey(prospect.charAt(i))) {
                    countingLettersMap.compute(prospect.charAt(i), (k, value) -> value + 1);
                } else {
                    countingLettersMap.put(prospect.charAt(i), 1);
                }
            }
        }

        return countingLettersMap;

    }

}