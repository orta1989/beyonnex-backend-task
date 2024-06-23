package org.example;

import java.util.*;
import java.util.spi.CalendarNameProvider;
import java.util.stream.Collectors;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";

    private static final List<Set> history = new ArrayList<>();

    public static void main(String[] args) {

        String option;
        Scanner scanner;

        do {
            do {
                System.out.println("Select an option: ");
                System.out.println("1 - Take 2 input strings and check whether they are an anagram of one another");
                System.out.println("2 - Take 1 input strings and returns the list of anagrams previously validated");
                System.out.println("q - Exit");
                System.out.print("Your option: ");
                scanner = new Scanner(System.in);
                option = scanner.nextLine();
            } while (!option.equals("1") && !option.equals("2") && !option.equals("q"));

            if(option.equals("1")) {

                System.out.print("Type in the first string: ");
                scanner = new Scanner(System.in);
                String input1 = scanner.nextLine();

                System.out.print("Type in the second string: ");
                scanner = new Scanner(System.in);
                String input2 = scanner.nextLine();

                if(f1(input1, input2)) {
                    System.out.println("The strings you entered are anagrams of each other");
                    addToHistory(input1, input2);
                } else {
                    System.out.println("The strings you entered are NOT anagrams of each other");
                }

            } else if(option.equals("2")) {

                System.out.print("Type in the string: ");
                scanner = new Scanner(System.in);
                String anagram = scanner.nextLine();

                f2(anagram);

            }
        } while(!"q".equalsIgnoreCase(option));

    }

    private static boolean f1(String origin, String prospect) {

        Map<Character, Integer> originCountMap = getLetterCount(origin.toLowerCase());
        Map<Character, Integer> prospectCountMap = getLetterCount(prospect.toLowerCase());

        return originCountMap.size() == prospectCountMap.size() &&
                originCountMap.entrySet().stream()
                        .filter(entry -> !prospectCountMap.get(entry.getKey()).equals(entry.getValue()))
                        .findAny()
                        .isEmpty();

    }

    private static void f2(String origin) {

        Set<String> anagramSet = history.stream().filter(set -> set.contains(origin)).findAny().orElse(null);

        System.out.println(anagramSet.stream()
                .filter(anagram -> !anagram.equalsIgnoreCase(origin))
                .collect(Collectors.joining(", ")));

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

    private static void addToHistory(String origin, String prospect) {

        Set anagramSet = history.stream().filter(set -> set.contains(origin)).findAny().orElse(null);

        if(anagramSet == null) {
            anagramSet = new HashSet();
            anagramSet.add(origin);
            history.add(anagramSet);
        }
        anagramSet.add(prospect);
    }

}