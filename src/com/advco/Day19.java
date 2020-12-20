package com.advco;

import sun.reflect.generics.tree.Tree;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

public class Day19 {
    public static void main(String[] args) {
        partTwo();
    }

    public static void partOne(){
        String[] content = PUZZLE_INPUT.split("\n\n");

        String[] rulesString = content[0].split("\n");
        String[] receivedMessages = content[1].split("\n");

        TreeMap<Integer, List<List<String>>> rules = new TreeMap<>();

        int ruleToApply = 0;

        for(int i = 0; i < rulesString.length; i++){
            int index = Integer.parseInt(rulesString[i].substring(0, rulesString[i].indexOf(':')));
            String rule = rulesString[i].substring(rulesString[i].indexOf(':')+2);

            List<List<String>> rulesForThisIndex = new ArrayList<>();

            String[] rulesPiped = rule.split("\\|");
            for(int j = 0; j < rulesPiped.length; j++){
                List<String> rulesForThisSideOfThePipe = new ArrayList<>();
                String[] rulesSplitted = rulesPiped[j].trim().split(" ");
                for(int x = 0; x < rulesSplitted.length; x++){
                    rulesForThisSideOfThePipe.add(rulesSplitted[x]);
                }
                rulesForThisIndex.add(rulesForThisSideOfThePipe);
            }
            rules.put(index, rulesForThisIndex);
        }

        int nbOfValidMessages = 0;
        List<String> matchingPatterns = decryptRule(rules, rules.get(ruleToApply));
        for(int i = 0; i < receivedMessages.length; i++){
            String receivedMessage = receivedMessages[i];
            for(String pattern : matchingPatterns){
                if(receivedMessage.equals(pattern)){
                    nbOfValidMessages++;
                    break;
                }
            }
        }


        /*Print validation*/
        for(int i = 0; i < rules.size(); i++){
            System.out.print(i +  ":" + "");
            List<List<String>> pipedRules = rules.get(i);

            int n = 0;
            for(List<String> rulesInPipe : pipedRules){
                for(String individualRule : rulesInPipe){
                    System.out.print(" " + individualRule);
                }
                n++;
                if(n < pipedRules.size()){
                    System.out.print(" |");
                }
            }

            System.out.println();

        }

        System.out.println("nbOfValidMessages: " + nbOfValidMessages);

    }

    public static List<String> decryptRule(TreeMap<Integer, List<List<String>>> rules, List<List<String>> rulesToApply){

        List<String> matchingPatterns = new ArrayList<>();

        for(int i = 0; i < rulesToApply.size(); i++){
            List<String> pipedRules = rulesToApply.get(i);

            List<String> previousPatternOfRules = new ArrayList<>();
            for(int j = 0; j < pipedRules.size(); j++) {
                List<String> patternOfRule = new ArrayList<>();

                String rule = pipedRules.get(j);

                if(rule.contains("\"")){
                    rule = rule.replace("\"", "");
                    char character = rule.charAt(0);
                    patternOfRule.add(character + "");
                } else{
                    List<String> list2 = decryptRule(rules, rules.get(Integer.parseInt(rule)));
                    patternOfRule.addAll(list2);
                }

                if(previousPatternOfRules.size() == 0){
                    previousPatternOfRules = patternOfRule;
                } else{
                    List<String> newPatternsOfRules = new ArrayList<>();
                    for(int x = 0; x < patternOfRule.size(); x++){
                        for(int y = 0; y < previousPatternOfRules.size(); y++){
                            newPatternsOfRules.add(previousPatternOfRules.get(y) + patternOfRule.get(x));
                        }
                    }
                    previousPatternOfRules = newPatternsOfRules;
                }
            }

            matchingPatterns.addAll(previousPatternOfRules);

            //Need pattern of the first rule * patterns of second * pattern of third

        }

        return matchingPatterns;
    }

    public static void partTwo(){
        String[] content = PUZZLE_TEST_4.split("\n\n");

        String[] rulesString = content[0].split("\n");
        String[] receivedMessages = content[1].split("\n");

        TreeMap<Integer, List<List<String>>> rules = new TreeMap<>();

        int ruleToApply = 0;

        for(int i = 0; i < rulesString.length; i++){
            int index = Integer.parseInt(rulesString[i].substring(0, rulesString[i].indexOf(':')));
            String rule = rulesString[i].substring(rulesString[i].indexOf(':')+2);

            List<List<String>> rulesForThisIndex = new ArrayList<>();

            String[] rulesPiped = rule.split("\\|");
            for(int j = 0; j < rulesPiped.length; j++){
                List<String> rulesForThisSideOfThePipe = new ArrayList<>();
                String[] rulesSplitted = rulesPiped[j].trim().split(" ");
                for(int x = 0; x < rulesSplitted.length; x++){
                    rulesForThisSideOfThePipe.add(rulesSplitted[x]);
                }
                rulesForThisIndex.add(rulesForThisSideOfThePipe);
            }
            rules.put(index, rulesForThisIndex);
        }

        int nbOfValidMessages = 0;
        List<String> matchingPatterns = decryptRule2(new TreeMap<>(), rules, rules.get(ruleToApply));
/*
        List<String> matchingPatterns8 = decryptRule2(rules, rules.get(8));
        List<String> matchingPatterns11 = decryptRule2(rules, rules.get(11));
*/

        for(int i = 0; i < receivedMessages.length; i++){
            String receivedMessage = receivedMessages[i];
            for(String pattern : matchingPatterns){
                if(receivedMessage.matches(pattern)){
                    nbOfValidMessages++;
                    break;
                }
            }
        }


        /*Print validation*/
        /*for(int i = 0; i < rules.size(); i++){
            System.out.print(i +  ":" + "");
            List<List<String>> pipedRules = rules.get(i);

            int n = 0;
            for(List<String> rulesInPipe : pipedRules){
                for(String individualRule : rulesInPipe){
                    System.out.print(" " + individualRule);
                }
                n++;
                if(n < pipedRules.size()){
                    System.out.print(" |");
                }
            }

            System.out.println();

        }*/

        System.out.println("nbOfValidMessages: " + nbOfValidMessages);
    }

    public static List<String> decryptRule2(TreeMap<Integer, List<String>> decryptedRules, TreeMap<Integer, List<List<String>>> rules, List<List<String>> rulesToApply){

        List<String> matchingPatterns = new ArrayList<>();

        for(int i = 0; i < rulesToApply.size(); i++){
            List<String> pipedRules = rulesToApply.get(i);

            List<String> previousPatternOfRules = new ArrayList<>();
            for(int j = 0; j < pipedRules.size(); j++) {
                List<String> patternOfRule = new ArrayList<>();

                String rule = pipedRules.get(j);

                if(rule.contains("\"")){
                    rule = rule.replace("\"", "");
                    char character = rule.charAt(0);
                    patternOfRule.add(character + "");
                } else{
                    List<String> list2 = null;
                    if(decryptedRules.get(Integer.parseInt(rule)) != null){
                        list2 = decryptedRules.get(Integer.parseInt(rule));
                    } else{
                        list2 = decryptRule2(decryptedRules, rules, rules.get(Integer.parseInt(rule)));
                        decryptedRules.put(Integer.parseInt(rule), list2);
                    }

                    patternOfRule.addAll(list2);
                }

                /*if(rule.equals("8")){
                    List<String> temp = new ArrayList<>(patternOfRule);
                    for(String pattern8 : temp){
                        patternOfRule.add("(" + pattern8 + ")+");

                    }
                }
                if(rule.equals("11")){
                    List<String> patterns42 = decryptRule2(rules, rules.get(Integer.parseInt("11")));
                    List<String> patterns31 = decryptRule2(rules, rules.get(Integer.parseInt("11")));

                    List<String> temp = new ArrayList<>(patternOfRule);
                    for(String pattern11 : temp){
                        for(String patterns422 : patterns42){
                            for(String patterns312 : patterns31){
                                patternOfRule.add(patterns422 + pattern11 + patterns312);
                            }
                        }
                    }
                }*/
                /*if(rule.equals("8")){
                    List<String> temp = new ArrayList<>(patternOfRule);
                    for(String pattern8 : temp){
                        for(String pattern82 : temp){
                            patternOfRule.add(pattern8 + pattern82);
                        }
                    }

                    temp = new ArrayList<>(patternOfRule);
                    for(String pattern8 : temp){
                        for(String pattern82 : temp){
                            patternOfRule.add(pattern8 + pattern82);
                        }
                    }
                }
                if(rule.equals("11")){
                    List<String> patterns42 = decryptRule2(rules, rules.get(Integer.parseInt("11")));
                    List<String> patterns31 = decryptRule2(rules, rules.get(Integer.parseInt("11")));

                    List<String> temp = new ArrayList<>(patternOfRule);
                    for(String pattern11 : temp){
                        for(String patterns422 : patterns42){
                            for(String patterns312 : patterns31){
                                patternOfRule.add(patterns422 + pattern11 + patterns312);
                            }
                        }
                    }
                }*/

                if(previousPatternOfRules.size() == 0){
                    previousPatternOfRules = patternOfRule;
                } else{
                    String[] newPatternsOfRules = new String[previousPatternOfRules.size() * patternOfRule.size()];
                    int inc = 0;
                    for(int x = 0; x < patternOfRule.size(); x++){
                        String xValue = patternOfRule.get(x);
                        for(int y = 0; y < previousPatternOfRules.size(); y++){
                            newPatternsOfRules[inc] = (new StringBuilder().append(previousPatternOfRules.get(y)).append(xValue).toString());
                            inc++;
                        }
                    }
                    previousPatternOfRules = Arrays.asList(newPatternsOfRules);
                }
            }

            matchingPatterns.addAll(previousPatternOfRules);

            //Need pattern of the first rule * patterns of second * pattern of third

        }

        return matchingPatterns;
    }
    public static final String PUZZLE_TEST_4 = "42: 9 14 | 10 1\n" +
            "9: 14 27 | 1 26\n" +
            "10: 23 14 | 28 1\n" +
            "1: \"a\"\n" +
            "11: 42 31 | 42 42 31 31 | 42 42 42 31 31 31 | 42 42 42 42 31 31 31 31 | 42 42 42 42 42 31 31 31 31 31\n" +
            "5: 1 14 | 15 1\n" +
            "19: 14 1 | 14 14\n" +
            "12: 24 14 | 19 1\n" +
            "16: 15 1 | 14 14\n" +
            "31: 14 17 | 1 13\n" +
            "6: 14 14 | 1 14\n" +
            "2: 1 24 | 14 4\n" +
            "0: 8 11\n" +
            "13: 14 3 | 1 12\n" +
            "15: 1 | 14\n" +
            "17: 14 2 | 1 7\n" +
            "23: 25 1 | 22 14\n" +
            "28: 16 1\n" +
            "4: 1 1\n" +
            "20: 14 14 | 1 15\n" +
            "3: 5 14 | 16 1\n" +
            "27: 1 6 | 14 18\n" +
            "14: \"b\"\n" +
            "21: 14 1 | 1 14\n" +
            "25: 1 1 | 1 14\n" +
            "22: 14 14\n" +
            "8: 42 | 42 42 | 42 42 42 | 42 42 42 42 | 42 42 42 42 42\n" +
            "26: 14 22 | 1 20\n" +
            "18: 15 15\n" +
            "7: 14 5 | 1 21\n" +
            "24: 14 1\n" +
            "\n" +
            "abbbbbabbbaaaababbaabbbbabababbbabbbbbbabaaaa\n" +
            "bbabbbbaabaabba\n" +
            "babbbbaabbbbbabbbbbbaabaaabaaa\n" +
            "aaabbbbbbaaaabaababaabababbabaaabbababababaaa\n" +
            "bbbbbbbaaaabbbbaaabbabaaa\n" +
            "bbbababbbbaaaaaaaabbababaaababaabab\n" +
            "ababaaaaaabaaab\n" +
            "ababaaaaabbbaba\n" +
            "baabbaaaabbaaaababbaababb\n" +
            "abbbbabbbbaaaababbbbbbaaaababb\n" +
            "aaaaabbaabaaaaababaa\n" +
            "aaaabbaaaabbaaa\n" +
            "aaaabbaabbaaaaaaabbbabbbaaabbaabaaa\n" +
            "babaaabbbaaabaababbaabababaaab\n" +
            "aabbbbbaabbbaaaaaabbbbbababaaaaabbaaabba";
    public static final String PUZZLE_TEST_3 = "42: 9 14 | 10 1\n" +
            "9: 14 27 | 1 26\n" +
            "10: 23 14 | 28 1\n" +
            "1: \"a\"\n" +
            "11: 42 31\n" +
            "5: 1 14 | 15 1\n" +
            "19: 14 1 | 14 14\n" +
            "12: 24 14 | 19 1\n" +
            "16: 15 1 | 14 14\n" +
            "31: 14 17 | 1 13\n" +
            "6: 14 14 | 1 14\n" +
            "2: 1 24 | 14 4\n" +
            "0: 8 11\n" +
            "13: 14 3 | 1 12\n" +
            "15: 1 | 14\n" +
            "17: 14 2 | 1 7\n" +
            "23: 25 1 | 22 14\n" +
            "28: 16 1\n" +
            "4: 1 1\n" +
            "20: 14 14 | 1 15\n" +
            "3: 5 14 | 16 1\n" +
            "27: 1 6 | 14 18\n" +
            "14: \"b\"\n" +
            "21: 14 1 | 1 14\n" +
            "25: 1 1 | 1 14\n" +
            "22: 14 14\n" +
            "8: 42\n" +
            "26: 14 22 | 1 20\n" +
            "18: 15 15\n" +
            "7: 14 5 | 1 21\n" +
            "24: 14 1\n" +
            "\n" +
            "abbbbbabbbaaaababbaabbbbabababbbabbbbbbabaaaa\n" +
            "bbabbbbaabaabba\n" +
            "babbbbaabbbbbabbbbbbaabaaabaaa\n" +
            "aaabbbbbbaaaabaababaabababbabaaabbababababaaa\n" +
            "bbbbbbbaaaabbbbaaabbabaaa\n" +
            "bbbababbbbaaaaaaaabbababaaababaabab\n" +
            "ababaaaaaabaaab\n" +
            "ababaaaaabbbaba\n" +
            "baabbaaaabbaaaababbaababb\n" +
            "abbbbabbbbaaaababbbbbbaaaababb\n" +
            "aaaaabbaabaaaaababaa\n" +
            "aaaabbaaaabbaaa\n" +
            "aaaabbaabbaaaaaaabbbabbbaaabbaabaaa\n" +
            "babaaabbbaaabaababbaabababaaab\n" +
            "aabbbbbaabbbaaaaaabbbbbababaaaaabbaaabba";
    public static final String PUZZLE_TEST_2 = "42: 9 14 | 10 1\n" +
            "9: 14 27 | 1 26\n" +
            "10: 23 14 | 28 1\n" +
            "1: \"a\"\n" +
            "11: 42 31\n" +
            "5: 1 14 | 15 1\n" +
            "19: 14 1 | 14 14\n" +
            "12: 24 14 | 19 1\n" +
            "16: 15 1 | 14 14\n" +
            "31: 14 17 | 1 13\n" +
            "6: 14 14 | 1 14\n" +
            "2: 1 24 | 14 4\n" +
            "0: 8 11\n" +
            "13: 14 3 | 1 12\n" +
            "15: 1 | 14\n" +
            "17: 14 2 | 1 7\n" +
            "23: 25 1 | 22 14\n" +
            "28: 16 1\n" +
            "4: 1 1\n" +
            "20: 14 14 | 1 15\n" +
            "3: 5 14 | 16 1\n" +
            "27: 1 6 | 14 18\n" +
            "14: \"b\"\n" +
            "21: 14 1 | 1 14\n" +
            "25: 1 1 | 1 14\n" +
            "22: 14 14\n" +
            "8: 42\n" +
            "26: 14 22 | 1 20\n" +
            "18: 15 15\n" +
            "7: 14 5 | 1 21\n" +
            "24: 14 1\n" +
            "\n" +
            "abbbbbabbbaaaababbaabbbbabababbbabbbbbbabaaaa\n" +
            "bbabbbbaabaabba\n" +
            "babbbbaabbbbbabbbbbbaabaaabaaa\n" +
            "aaabbbbbbaaaabaababaabababbabaaabbababababaaa\n" +
            "bbbbbbbaaaabbbbaaabbabaaa\n" +
            "bbbababbbbaaaaaaaabbababaaababaabab\n" +
            "ababaaaaaabaaab\n" +
            "ababaaaaabbbaba\n" +
            "baabbaaaabbaaaababbaababb\n" +
            "abbbbabbbbaaaababbbbbbaaaababb\n" +
            "aaaaabbaabaaaaababaa\n" +
            "aaaabbaaaabbaaa\n" +
            "aaaabbaabbaaaaaaabbbabbbaaabbaabaaa\n" +
            "babaaabbbaaabaababbaabababaaab\n" +
            "aabbbbbaabbbaaaaaabbbbbababaaaaabbaaabba";

    public static final String PUZZLE_TEST = "0: 4 1 5\n" +
            "1: 2 3 | 3 2\n" +
            "2: 4 4 | 5 5\n" +
            "3: 4 5 | 5 4\n" +
            "4: \"a\"\n" +
            "5: \"b\"\n" +
            "\n" +
            "ababbb\n" +
            "bababa\n" +
            "abbbab\n" +
            "aaabbb\n" +
            "aaaabbb";
    public static final String PUZZLE_INPUT = "107: 18 | 47\n" +
            "116: 1 18 | 111 47\n" +
            "21: 45 47 | 110 18\n" +
            "20: 2 47 | 76 18\n" +
            "44: 47 47 | 18 107\n" +
            "2: 47 18 | 107 47\n" +
            "113: 51 47 | 35 18\n" +
            "80: 18 22 | 47 44\n" +
            "110: 18 18 | 47 107\n" +
            "1: 22 18 | 112 47\n" +
            "77: 47 7 | 18 113\n" +
            "16: 121 47 | 2 18\n" +
            "66: 91 18 | 58 47\n" +
            "86: 12 18 | 78 47\n" +
            "72: 18 76 | 47 121\n" +
            "46: 47 68 | 18 82\n" +
            "131: 66 18 | 75 47\n" +
            "108: 84 18 | 24 47\n" +
            "27: 22 47 | 110 18\n" +
            "53: 108 18 | 122 47\n" +
            "90: 77 47 | 37 18\n" +
            "6: 18 29 | 47 52\n" +
            "10: 76 18 | 44 47\n" +
            "43: 47 39 | 18 130\n" +
            "106: 18 112 | 47 110\n" +
            "89: 97 47 | 51 18\n" +
            "84: 112 18\n" +
            "91: 74 47 | 110 18\n" +
            "114: 18 47 | 107 18\n" +
            "105: 26 18 | 118 47\n" +
            "18: \"b\"\n" +
            "19: 2 47 | 22 18\n" +
            "15: 34 47 | 2 18\n" +
            "111: 110 47 | 22 18\n" +
            "94: 47 95 | 18 57\n" +
            "34: 107 107\n" +
            "60: 47 114 | 18 121\n" +
            "62: 44 47 | 45 18\n" +
            "50: 38 47 | 103 18\n" +
            "61: 18 44 | 47 110\n" +
            "87: 112 107\n" +
            "8: 42\n" +
            "31: 18 49 | 47 25\n" +
            "57: 18 20 | 47 60\n" +
            "103: 18 67 | 47 40\n" +
            "39: 18 45 | 47 22\n" +
            "128: 112 18 | 13 47\n" +
            "5: 45 47 | 44 18\n" +
            "13: 18 18\n" +
            "3: 47 10 | 18 80\n" +
            "11: 42 31\n" +
            "124: 22 18\n" +
            "88: 18 18 | 47 18\n" +
            "54: 32 18 | 129 47\n" +
            "63: 81 18 | 86 47\n" +
            "49: 18 132 | 47 105\n" +
            "102: 10 18 | 120 47\n" +
            "93: 18 44 | 47 34\n" +
            "65: 107 2\n" +
            "132: 83 47 | 14 18\n" +
            "125: 18 43 | 47 71\n" +
            "109: 47 22 | 18 76\n" +
            "117: 18 112 | 47 22\n" +
            "59: 47 22 | 18 13\n" +
            "75: 18 109 | 47 27\n" +
            "4: 47 90 | 18 69\n" +
            "48: 18 65 | 47 115\n" +
            "24: 18 2 | 47 44\n" +
            "42: 47 50 | 18 4\n" +
            "96: 106 47 | 16 18\n" +
            "101: 111 47 | 91 18\n" +
            "58: 47 76 | 18 73\n" +
            "79: 47 73 | 18 22\n" +
            "12: 47 34 | 18 88\n" +
            "25: 33 47 | 55 18\n" +
            "130: 18 114 | 47 44\n" +
            "83: 126 18 | 102 47\n" +
            "45: 47 18\n" +
            "9: 41 18 | 62 47\n" +
            "78: 112 18 | 45 47\n" +
            "14: 3 47 | 85 18\n" +
            "97: 47 45 | 18 22\n" +
            "22: 18 47\n" +
            "41: 18 22 | 47 112\n" +
            "112: 18 47 | 18 18\n" +
            "68: 21 18 | 19 47\n" +
            "121: 47 47 | 18 18\n" +
            "119: 22 47\n" +
            "129: 47 5 | 18 39\n" +
            "30: 73 18 | 2 47\n" +
            "98: 34 18 | 45 47\n" +
            "40: 18 70 | 47 9\n" +
            "7: 64 47 | 79 18\n" +
            "115: 121 47 | 44 18\n" +
            "32: 19 47 | 30 18\n" +
            "73: 18 47 | 47 18\n" +
            "35: 18 76 | 47 45\n" +
            "95: 93 18 | 72 47\n" +
            "52: 28 47 | 124 18\n" +
            "126: 59 18 | 92 47\n" +
            "26: 104 18 | 89 47\n" +
            "81: 47 98 | 18 127\n" +
            "0: 8 11\n" +
            "64: 112 47\n" +
            "71: 47 119 | 18 117\n" +
            "74: 47 47\n" +
            "82: 47 120 | 18 17\n" +
            "56: 74 47 | 88 18\n" +
            "123: 88 47 | 76 18\n" +
            "33: 6 18 | 63 47\n" +
            "104: 80 18 | 128 47\n" +
            "99: 98 18 | 23 47\n" +
            "38: 94 18 | 53 47\n" +
            "36: 47 110 | 18 74\n" +
            "37: 47 48 | 18 101\n" +
            "70: 61 47 | 87 18\n" +
            "55: 47 54 | 18 131\n" +
            "23: 2 47 | 112 18\n" +
            "122: 10 47 | 64 18\n" +
            "120: 88 18 | 112 47\n" +
            "28: 18 112 | 47 73\n" +
            "51: 47 74 | 18 110\n" +
            "17: 47 88 | 18 22\n" +
            "127: 18 73 | 47 45\n" +
            "69: 125 18 | 46 47\n" +
            "92: 2 47 | 114 18\n" +
            "29: 117 18 | 56 47\n" +
            "67: 18 96 | 47 116\n" +
            "85: 18 36 | 47 15\n" +
            "100: 18 123 | 47 12\n" +
            "47: \"a\"\n" +
            "118: 100 47 | 99 18\n" +
            "76: 47 47 | 47 18\n" +
            "\n" +
            "bbbbbbbbbaaaabbaababbabbaaabbbabbbbaaabb\n" +
            "babaabbaabbbbaababbaabbabaababba\n" +
            "baabbaaabbabbbbaaabababb\n" +
            "bbbbababbaabbbabbabaaaaaaabbbbbbabbabbbaabbbbbbbbbaaaaab\n" +
            "bbbbbabbbbbbaaabbaaaaaba\n" +
            "baabbabbbaabbbabbaabbabbababaaab\n" +
            "bbabbbbaabbabaaababbbbbbbbbabaab\n" +
            "aaababbaabbaaabbbbbbbabbbaaaabbaabbabbabaababbaa\n" +
            "aabbbabbaaaaaaabbbabbaba\n" +
            "baabaaaabbbababbbbaabbbb\n" +
            "bbbbabababbbbabbbbbbbbba\n" +
            "babbaababbbbababaabaababbbabbbbbaabaaabb\n" +
            "baabbbabaabbabbbaaaaabba\n" +
            "baabbabbbbaabbbabbbaaabb\n" +
            "aaaaabbaababaabaabbabbbbbabbabaabbaaabbb\n" +
            "babbaabaaabbbbbbbbbbbabbaabbaaabbaaaaabbabbaabaabaaabbbaaabaaabb\n" +
            "aaabaaabbaaaaabbabaaaaababbabaaababbaabaabbaaaabaaaabaaaaaaabbaaababbbbb\n" +
            "baaababbbaabaaaaaaaaaaaabbababaababaaaaabaaabaaa\n" +
            "abaaaaabbbaababababaaaba\n" +
            "abbaabbabaaabbabaaaaaabaababbabbbbbbabbaabaabbbb\n" +
            "baabbabaaaaabbabbaaaaaabaababaab\n" +
            "abbaabbabbaabaaaabbaaabbabbaaababaaaaaaa\n" +
            "abaaaaabbbbaabbabbbbbbaaaaaaababbbbbbaaa\n" +
            "aabbaaabaaaaaabababaabab\n" +
            "bbbabbaaaaabaaaaaabbbbaa\n" +
            "abbaaababbaaaabbaabaaabbbaaaabbbbbbbaaaaabbbbabaabaabbab\n" +
            "aaaaaaaaaababbabbbabbaba\n" +
            "abbaaaaabbbabbbaabbaaabbabbbbaabbabbabbbabaababbbbabaaba\n" +
            "babaaaaabaabbaaabbaabaaababababa\n" +
            "bababaabbaaaabbabbbabbbabaabbaaaabbaaababbbbbbaaaaababaa\n" +
            "aabaabbababbbabaaaaaaaabababaaaababbabaabbaaaaabbbbbbaab\n" +
            "baaabbbbaaabbaabaaaaabaaababbaabaaababbabaababbb\n" +
            "abaabbbaaabbabbabaaababbbbabbaaaabaaaabb\n" +
            "abbaababbaaaaabbbbaaabbb\n" +
            "aabbabbabbaaaaaabbbaaabb\n" +
            "baabbbabbabbaababbabbbbabaabbbabbbbabbbaabbabbaababbbbbaabbababbaababaab\n" +
            "abbaabaabaaabbbbbaaabbabababbabbbaabbabbaaabbaaababbbababbbbbaaa\n" +
            "aabbbaabbbbababbbabaabaa\n" +
            "baabbaaaabbbaababaabbabbabbbbbaabababaaabaabbbbaaababbbb\n" +
            "abbabababbbbbabbababbbaaaaaaabbbaabababb\n" +
            "baaabbabbbbaababbbaaaabb\n" +
            "aabaaaaaaaaaaabbbabbbabbaaabbbab\n" +
            "abbaabbababbbbaabaaaabbaababbabbaaabbbbabbbaaabbbbaaaabb\n" +
            "baaabbabbbaabaabbbabbaba\n" +
            "abbbbbaaabababbbbbbabbbb\n" +
            "aaabbabbbbaabbbabbaaabbb\n" +
            "babbbbaaabaaabbaabaaaaabbabbaaab\n" +
            "aaabbabaabbaababaabbbbbbbabaaaba\n" +
            "baaaaabbababbbbabbbbabbbababaaaa\n" +
            "abbaaaabaabbbaabbabbbbab\n" +
            "bbbaabbaabaaaaaaababbbbaabbaaaaabbaaabbabbabbbaa\n" +
            "baabbbabaabaabbaababbabbbbaaaaaaaabbbaabbbabbbaa\n" +
            "babaabbabbbbaabaabababbbaabaababbbbaabbb\n" +
            "aabbbabbbaaaaaababbaaaaaaabbaaabbabbabbaaabbaaba\n" +
            "aaabababababbbabbbaabaabaaababbaabbbaabbbbaaaaba\n" +
            "aaabaaababbaababbbbabbab\n" +
            "baaabbabbaabaaabaaaababbabbbabba\n" +
            "abbbabaaaabbbaaaabaabbabbbaabbbbaaabaabb\n" +
            "abbbbabbabbaababbbbbbbaabaaabbbbabaabababbbaaaabaabbaaba\n" +
            "baabbababbbabaaaaabbbaab\n" +
            "aaaabbaabbaabbbbaabbaaaabbabbabaabaaababbaabaabababbaabbbabbbaabaabbbabbbabaaabb\n" +
            "abaaaaabbabbaaaabbabbbaa\n" +
            "baaabbbbababbbaaaababbabbabbaababbabbbabbbbbaaaa\n" +
            "aaaababbabaaaaabbbaabaabbababbbbaabbaaabbbbaaabbbabbbaaaababbaba\n" +
            "abbaaabbbbbbaaabaaaaaababbbbbaaa\n" +
            "aaaaabbababbaabaabaaaaaababbbaabaaaabbaa\n" +
            "bababaabbbbbaaababbaaaaaaabbbbabaaaaabbbaabbbaaa\n" +
            "bbaabbaaaabaaabbaabbaaaabbabaaaabaabbbab\n" +
            "aabbaaabbaabaaaaaaaababbbabaabbaaaababbb\n" +
            "abbbaabaabbaabbbaaaababa\n" +
            "abbaaabbaabaaaabaababbbb\n" +
            "baabababbbbaaabaaabbabababbbabba\n" +
            "abaaabbaaaabbabaabbaaabbaaaaaaaaabbbaababaababbaaabaaaaaabbbabaa\n" +
            "abbbbbabaabbaaabaaaaaabb\n" +
            "abbaabbaabbbbaabbbaabaababbbbbaaababbbbb\n" +
            "aaabbababaaabbabbbaaabbb\n" +
            "baaababaababaabbbbbaabaa\n" +
            "bbbaababbbaaabbaaaaabbabbabbbbab\n" +
            "bbaabaaaaaababbabbaaaaab\n" +
            "aaabaaabbabbabbabbaaaaba\n" +
            "babbabababbbbabbaabbbaba\n" +
            "ababaabbbaabbaababbaabababbbabbbabbbbaabbababaaababbbabbbbbabbbb\n" +
            "abbbbabbaababbababbbbbaaabababbbaabaaababbbaaabb\n" +
            "babaabbbababaabaabaaabbbbaabbbaa\n" +
            "bbbbabbbbbababaabbbbaaabbaabababbbbbabaabaaaaaaa\n" +
            "aabbabaaaaabaaabaaaaabab\n" +
            "abababaaabbabaaabbaaabbababbbbaaaaaababbbabbbaaa\n" +
            "abbaaaaabbabbbbaaabaabbabbaabaababbaaabaaababaaa\n" +
            "aabbbabbababaabbababaabbaaabbbab\n" +
            "baaaaabbbbbbbbaaabbbbbabaabaababaaaabaab\n" +
            "abaaaaaaaaabbababbabababaabbbabbbbaabbababbbaaab\n" +
            "baabaaaaabbaaaabbbbabaaabbababababbbbbba\n" +
            "baabaaababababaaaabbabaaaabaabababaaaabb\n" +
            "bbaaabbaaaabaaababbbbabbaababaaa\n" +
            "babaabbaabbbbaabbbaaabbabbaabbbbbabbbaaa\n" +
            "bbaaaaaaabbaaaaaababbaaaababbbabbbbaaaabbaabababababbabbbbbbaabbaaababbbbbbbbbab\n" +
            "aabaababbabbaaaaabaaaaabbababbaa\n" +
            "aaabbaabaabaabaabbbaababaabaabaabbaabaabbaaabbaa\n" +
            "abaaabbabbbabbaabbbbbabbabaababbababbaaa\n" +
            "bbabaaaaaababababbababba\n" +
            "aaabbabbbabbbbbbabbaabbbbbbaababbaabaaabababbbbbabbabbaa\n" +
            "aabbbbabbabbabaabababbaa\n" +
            "aabaaabaaaabaaaabbabbbbaaaabbbbb\n" +
            "aaaabbabbbabbbbbbaaababaaaaabbabaaaaabab\n" +
            "baabbbabaaabbabbbbababba\n" +
            "bbbabbbaabbaabaabbabababbbbababbabaaabbababbabbbabbbaaaabbaaababbaababaa\n" +
            "abbaabbbbaaabbabbaaaaaaa\n" +
            "aabaaabaaaaaaaaaaaaaaabb\n" +
            "babbaaaabbabaaaaaaaaabbaabbbbaabbbabbbbbbbbaaaaa\n" +
            "aabbaabbbaaaaabbabbbbbba\n" +
            "baaaaabbaaabaaaaabaabbaa\n" +
            "bbaabaabbaabbbabbabbbaabbbabbbaababaabab\n" +
            "aaabbabababaabbabaaababaaaabbabbbbabbaabbabbbbba\n" +
            "bbaaabbabaaababbabaaaaaabaabbbaa\n" +
            "bbbbbabbabbbbbabbbbababa\n" +
            "abaabbbabaabbabababaabaa\n" +
            "babbabaaaaaababbbaaaaabbababbabbbbbbbabbbbbbabaa\n" +
            "aaaababbabbbbbaabbbbaaabbaaabbbbaabbbbbababbaabababbbaaa\n" +
            "babaaaaaabbbabbbaabaabbabaaabbbbbabababbbaabbbaabbaabbaa\n" +
            "ababbaabbbbbbabbbabaaabbabaabaabbababbbbabbbbaab\n" +
            "abaaaaaaaaaaaabaabbbbaabbababbbbbaaabbaa\n" +
            "bbbaabbabbbabbaaaaabbabbbaaaabbaaabaabbb\n" +
            "aaaaaaabaaabaaabaabbaaabbbabbbabaaaabaaababbbaaa\n" +
            "aabbbbababbaaababababaabaababbba\n" +
            "bbabbbbbbabaabbbbabbababaaaaabaabbaaabaabbbabbabababbaaa\n" +
            "babaabbaabbabbabbabbabaaabbabaab\n" +
            "bbbabbbaabbaaabbaabaaabaaababbaa\n" +
            "abbbabbbababababbaaabbab\n" +
            "abbaaaaababbababaaaaaabaaabaaabb\n" +
            "aaaaaabaabbaaabaaabaabbabbabbaab\n" +
            "aabababaaabaaababaabaabbaababaabbaabaaba\n" +
            "bbababaabaabababaabaaaabbaaabaab\n" +
            "aabbbbabbaaabababbabaabb\n" +
            "bbbbbbaaaabbbbabbabaaaababbbbbba\n" +
            "ababbbbabaabaaaaababbbbaabbaaaabbbabbaaa\n" +
            "abbaaabbaabbaabbaaaababbbaabaabbaabbbbbabababaaabbaaababbbabaaab\n" +
            "aaaaaaabbaaaaabbabaabaabbbbbbbabbbbbaaaabbbabbbb\n" +
            "babbabbaaaaaaaaabbbaaabb\n" +
            "aaabaaaaaaaaaabaabbabaaabaaababbbbbabaaaabbbaaba\n" +
            "aabbaaabbaabbabbbbabbaaa\n" +
            "aabbbaabaabbbbbaabbaaababbaabbbabbbabbbb\n" +
            "bbbabbbabbababababababbbabbababb\n" +
            "aaaaaaabaabbabaaababbbbb\n" +
            "bbabababbbbabbaabaaabbababbaaaaabbaaabaa\n" +
            "bbabbbbaaabbaabbabaabbaa\n" +
            "aaabbaabaabaabbaabbaababaabbbaabaabaabbababbabababaaabbbbbabaababaaaababbbbbbbbabababbba\n" +
            "abababaaabbaabbabbbbaaaa\n" +
            "abaabbbaaabbbabaabababbbabaaaaabbaaaaabaaabbabbbbaaaaaabbabbabab\n" +
            "baabababaabbaabbbbababba\n" +
            "abbaaabbabbaabaabbabbaab\n" +
            "baabbaababaabaababaaabab\n" +
            "abaabaaaaaababbabbabbaba\n" +
            "baaabbbbbbbbbbababaabbab\n" +
            "babaaabbaabaabbaabbabababababbba\n" +
            "aaababbaaabaabaabbbabbbababbbaabaabbbaaa\n" +
            "aabbaaaaababbbbaababbaaa\n" +
            "babaaaabbbabbbbababbbabb\n" +
            "bbbaabbabbbaabbabbabbbaa\n" +
            "babaaaaaabbaabbabbaabaabbbbbbabbbbaaabbabababaaaaaabbbbb\n" +
            "babbaaaabbbbababbaabbbabbaaabaabbaabbbba\n" +
            "bbbbbbaabaaababaababbaabababbbab\n" +
            "abbabbbbaabbbbaaabbbaabbbbabbabb\n" +
            "bbbabaaaaabbaaabbabababb\n" +
            "abbaabababbbbaababbabbabbabbaaaaaaaaabaaaaaabaab\n" +
            "aabbabaabbababaaaaabbababbaabaaabaaaaaaa\n" +
            "bbbbaabbbbbababaabbbabaaaabababb\n" +
            "baabbabbaabaaabaaabaaabababaabab\n" +
            "baabaaaaaabbaaabbbabababbabbbbbbbbbbaaaa\n" +
            "aaaaaaababaabbbabbaabbbaaaaabaababbbaaababbbaabaabbbbbbbababbababaabbaab\n" +
            "abbabbbbbbbbbabbababaaaa\n" +
            "babbbaabaabbbbabbaabbaabbbbabbaabbabbaab\n" +
            "ababaabbbbaabaabaaabbabbbaaaaaaa\n" +
            "baaaaaabaabbaaababbbbabbabaaababbbaaaaba\n" +
            "bbbbabbaaabbbaabbaaaabab\n" +
            "ababbbaaabbabbabbbbabbab\n" +
            "abababaaaaabbabbbbabaaba\n" +
            "aaaaabbabbabbbababbbabba\n" +
            "aabbbbbabbabababaabbaaabaaaaabbbbbbaaaab\n" +
            "bbbabaaabbbabbbaaaabbababbbbbaba\n" +
            "aaabaaababbbaababaabbbaa\n" +
            "aaaababbbbbbbbabaabbaabbabbbabbbbababaaabbbaabbbbabbbbab\n" +
            "aabaabbabaabababbabaabbaaaaaabab\n" +
            "abbaabababaabaaababbbbaaaabbaaba\n" +
            "bbabababbaabaabbababbaaa\n" +
            "babaaaabbaabababbbbaabbaaaabaaabbababbaaaaaabbaabbbaaaaa\n" +
            "bbbabbbababbbbbbbaaaabaa\n" +
            "bbbbabbaababbbaabbabbbbabbabaaaaaabaababababaababbaababbbbbbaaaa\n" +
            "baabbbbaaaabbbabababbaba\n" +
            "aabbbbabbbbbababbbababbbababbaaaabbbbaaa\n" +
            "aabbaaabbbabababbbbabbbabaabbaaababbaaababababba\n" +
            "baabaabbbaabaaababbbbaabbaaaaababababbbabaaabbbbbaababababaaaaaa\n" +
            "bababbbbaabaaaababbaaabbbabaabab\n" +
            "babaabbbaaaabbbaaaaabbbbabbbaaaabbabbaba\n" +
            "abaaaaabbbbabbaabbbbbbabaaabaabb\n" +
            "ababaababbaabaabababbaba\n" +
            "baabaaaabaabbabbbbabaaaababbaaab\n" +
            "aaaabbbabbbbbbabbbabbabb\n" +
            "bbbbbbabbabbaaaaabaaabbabaaababbaabbbaaa\n" +
            "bbbbbabbbbbaababbabababb\n" +
            "aaabbaababababaababaaabbabaaabbb\n" +
            "baabaabbaaaaabbabaabaaaaabaaabab\n" +
            "bbbbbbababbbbaababbabbabbbbaabaa\n" +
            "abaabbabbabaababbbbbbbbabbbbbbaaabbbaabaabbbbbbbbbbbaaab\n" +
            "abaabababaabbabbbbbbabbabaaabaab\n" +
            "bbabbbababbaabbaababbbbaabbaabbbbbaabaabbabaababbbababbbbaababaa\n" +
            "abbaaaaaaaaabbbaabbabaaaabbaaaaaababbabaaaaabaabaabababb\n" +
            "aaaaabbaaaabbbbaaaabbbbaaaababbababbbaabbaababbababaaabaaabaaabbbbaaaaab\n" +
            "aabaabbaaabaaabaaabababb\n" +
            "aaabbabbabaaaaabbbbbababaaaabaaa\n" +
            "baababababbbbabbabbabaaaaabaaabbaaababbb\n" +
            "ababbbbaabbaabbaabbbbaaa\n" +
            "aaaabbabbbabaaaaaabaabbb\n" +
            "bbbbabbaaaaaabbabbbababa\n" +
            "aaaaaabbbabaaababaaabbaaabaabbbabaaababbbbabaabaaababaabaaaaaaaaabbbaaaababaabaa\n" +
            "baabbbababbbbbaabaabaaaaaaabaabb\n" +
            "abbabbabbbbbabbbaaaaabbabaaabbaa\n" +
            "baabaaababaabaabbaabbabbaaaaaaababbabaaaaababbba\n" +
            "baaabbabaaaaabaaabbabbbaaababbba\n" +
            "aabbaaababaabaaababbaabaaababbabbabbbbabbbaaaabb\n" +
            "baabbaaabaabbaababbbbaba\n" +
            "aaaaabaabbaaabbaaabaaaabbaabbaaaaabaaabb\n" +
            "bbaababaabaaabbababababb\n" +
            "bbabbabbbbaaaaaaaaaabaabbbaaabaabbabbabbbbbbabbbabbbaaaabaababbbbabbbbabababbaab\n" +
            "aaabbbbabaabaaaaaaabbbab\n" +
            "baabbbabbbbabbaaabaaabab\n" +
            "bbaaabbabaabababaabbabbabbaaabaa\n" +
            "ababbbaaababaabbbabaaaabbababbbbbaaaaaba\n" +
            "baabbaaaabaababaaabababb\n" +
            "aababbabbbababababbbbbaabaabbaaaaaaabaaabbaaaaba\n" +
            "aaaaaaabbaabaaaaababababbabbaaabaaabaaba\n" +
            "abbaabbbaabbbabbaaabbaaa\n" +
            "baabbaabbbbababbbaaaaabbbbabbabb\n" +
            "aaaaabbababbaaaaabaaabbaabaabbbabaababaa\n" +
            "aabbabbbbbbbabbbbbbbbbba\n" +
            "abababbbaaaaaaabaabbabbaabbbbaaa\n" +
            "abababbbaabaaababbaababaabbabbaa\n" +
            "baabbaabaaaaaaaaaaabaaba\n" +
            "ababbbaabbbbababbbaaaaab\n" +
            "baaabbabaaabaaabaaaabbbbbaaaaaba\n" +
            "bbaaabbabbabaaaabbaaaaaaababaabbbabaaaba\n" +
            "abaabbbaabaaabaaaabbbbbbbaaabaabaababbba\n" +
            "bbbbabaabbbbbaababaaaaabaaabbabbbabababbbaabbaaa\n" +
            "abaabaabaaaaabaababaabbbbbaabaaaaaababbb\n" +
            "aabbbbbaaabbbbabaabaaababbbbbbabbbbaabbbbaabbbaa\n" +
            "abaabbbaabaaaaaaaaaabaaa\n" +
            "baabbbababbbaababbabaaba\n" +
            "babbbaaaaabbbababbababbabbababab\n" +
            "baaabbababbbabbbabbbbbba\n" +
            "abbaaaaaabaabaabaaabbbbaabbababbababbaaa\n" +
            "abbaaaabbaaabbababbaababaaaababbbabbaabb\n" +
            "baabaaabaaabaaabbaabababbaabaaba\n" +
            "ababbbbaabbaaabbbbaababaaaaabbbbabbababb\n" +
            "baabbabbbbabbbbabaabbababbbbababbaaaaaababababba\n" +
            "abaabaaaabbaaaababbaaaabababbbaaababbabaabaabababbbbbaab\n" +
            "abbbbbabbaaaaaabbabaaaaabaaabbbbbaabababaaababaaabbbabab\n" +
            "baabaaabbabbabababbaaabbbabbabbabbbbbabbbabbbaaabaaaabab\n" +
            "babbabaaaaaabbbbabaaaaabaabbbabbabbaaaaaaababbba\n" +
            "babbbabaabbbaaaababaabaa\n" +
            "aaabbabbbabbabaaabbbbabbabbababbabbbabba\n" +
            "abbaaaabaaabaaabbaabbabaaaababbabaaabbbbbababbaaabbabaab\n" +
            "aabbabaabaaaaabbaabbbaabbabaabab\n" +
            "baaaaabbbbaaaaaaabbbbbabbbbbbbbbaababbbbbbbaabaa\n" +
            "bbababaaabbaabaabbabababbabaabbbabbaaaaaabbbabbb\n" +
            "aaaabbabbbaabaabaaaaaaaaabbbbbaaaaabbbababbbaaaa\n" +
            "abbabbbbbbbbabababaabbab\n" +
            "babaabbabbbbabbabaaabbbbbbababbaaaabbbabbabaaabaabbabaab\n" +
            "bbababaaaabbbabbbbababbb\n" +
            "baabbbababbaabaabbbbababbababbbbabbbabab\n" +
            "baabaaaabbbbbbababbabbaa\n" +
            "babaaaaaaabbbaabbbbbbbaabaabaaabaaaaabbbabaaabbb\n" +
            "aaababbaaaaabbbbbbabbbba\n" +
            "aabbbaabbbabaaaaaabababaabbbabbbbbaaabaabbbaaaba\n" +
            "abbbabbbbabaaabbbbbbababababbbbb\n" +
            "abbbbaabaaaabbbaaaabbbbaaaaaabab\n" +
            "ababbbbaaabaaabaababaaab\n" +
            "aaaaaaabbbbaababbaabababaabababaabbbbabb\n" +
            "aabaaaabbaabaaabbaaaaaabbababbbaababaaaa\n" +
            "aabbabbabaaaaabbaaabbbab\n" +
            "bbbbbabbbbbbbbabbbabbaba\n" +
            "aabaababaaaabbabbbbaabbaabaaabaabbabababbbaabbbbbaaaabaa\n" +
            "babbabbabbbaababaabbbbbbabbaaabaabbabbbaabbbbbbb\n" +
            "aabbaaabaababbababaaabab\n" +
            "abaaabaaaababbabbbbbbbabbbaaaaaaabbabbaaaaabbbbb\n" +
            "babbbbbbbbaabaaabbbbbaba\n" +
            "aababbaaaaabababbababbbaabbbabaa\n" +
            "abbbbbaaabbbbaabaaaabbbbababbbabbbaabbaa\n" +
            "aabbabbaaabaaabaaaaabbabaabbaabbabbbbbabbaabbbba\n" +
            "baabbbabbabbaababbbbabbb\n" +
            "aaaaaaabaabaaaaabbbbbbbbaaabbabbbabaabababababbb\n" +
            "bbabbbabbabaaaabbaabbbabaaaabbbabaabbabbbbabbabbabbbaabbbaabaabaaababaaa\n" +
            "bbaabaabaabbaaaaabaabaaaaabaabbbbababbaa\n" +
            "bbbababbabaaaaabbbbabbab\n" +
            "abbbabbbaabbbaabbbbbabbababaaabbabbbbbaabbaabbbbabbababbabbbbbbb\n" +
            "babbabbaaababbabbbbabbbaaaabaaababbaabaaabababab\n" +
            "bbabaaaabbbabbbaababbabbaababbaa\n" +
            "abbbbabbabbaaaaaaaababaa\n" +
            "bbbababbabbaaabbaabbabbbbbbabbbbaaaabbaa\n" +
            "abbabbbbbbbaababbaabaabbbbabababbaaaaaaabbbbbababbabaabb\n" +
            "aaaaaababaaabbaaabaaaabb\n" +
            "babbababbabbbaaababbbabbbbbabaab\n" +
            "aaabaaabbabaabbbbababaabaabbbbbaaabababb\n" +
            "aabbbbbaabbbbabbbbbbababaaaaabbaabbabbabbaaabbbbbbabbabb\n" +
            "abbabaaababaabbaaabbaaabbaaaababbbbbbbba\n" +
            "bbbbbbabababababbbabbbabbaaaaabbabaaaabaabaaaababababbaa\n" +
            "abbaabaaaabbabbbabaababaababaabbbbbbbaba\n" +
            "abbabbbbbabbaababbbaabaa\n" +
            "abbaababbababaabbbaaabbaababbbaaabaaabaabbaaabbb\n" +
            "bababaabaaaababbbbaaaaaaaaaaabab\n" +
            "bbbbabbbbaabbabababbabbb\n" +
            "abbaaaabaaaaaabaabaaabaaabbbbaaabbabaaba\n" +
            "bababbbbaaaaaababaabbabbbbaabbab\n" +
            "abbaaabbababaabaabaabbab\n" +
            "aabaaaabaabbabbabbbababa\n" +
            "aaaaabbabbbbbbaaaaabbabaaaaaaaaaabbbbabbaabbabbabbabbbaaaaabaabb\n" +
            "bbbbbaaaabababbababbbaabbabbbbaaaabaabaabaabaaababbaabababbabbbb\n" +
            "abaabaabaabbbbbbaaaaabab\n" +
            "aabbabaaababababbbbaabbaabbbbbaabbabbaabaabaaabbbaabbbaa\n" +
            "abaaaaaaabbaababbbbbbabbbbaabbab\n" +
            "babbabaabaaabbbbabbabbaa\n" +
            "aabaaaabababaabaaabbaabbabbbabababbbbbbb\n" +
            "aabbaaaaaaabaaaaabbbbabbbaabbbababbaaababbaaababbbaabbbb\n" +
            "aaabbbbabbbbbbbbbbaaabab\n" +
            "aabbbabbbaabaabbaaaaaababbabbbbbbbbaabbb\n" +
            "bbbababbbababaabaabababb\n" +
            "aabaabaaaaaabbabbaabbaabaaababab\n" +
            "babbbaababaaabbaabbaaabbbbbababa\n" +
            "baabbaabbbaaaaaaaaaabbabbabbbaabbabaaabbbbaabbbbbbbaabaaaabaaabbabbabbaa\n" +
            "abbaababbabaaaaababbbaba\n" +
            "bbabababaaaaabaababaaaba\n" +
            "aaaaaababaaaaabbaabbaaabaaaaababababbaba\n" +
            "babbbbbbaabbaaaabbbbaabb\n" +
            "ababaabbbbaabbbaababbbbb\n" +
            "bbbabbbaaaabbaabbabbbbaa\n" +
            "aabababaabbaaabbabaaaaba\n" +
            "baabaaabaabbabbabbbbbaaa\n" +
            "bbbbbbbbbbbabbaabbabbbbabaabbaab\n" +
            "babbaabaaabaabaaabaabbbabbabbbaa\n" +
            "ababbbbaaaaaabbaabbaaaabbbaababaabbabbabbbbabbabaaaaababbaaabaab\n" +
            "aaaabbabbaaabbabababababbaababaa\n" +
            "aaabaaabbabbaaaaaaabaaaabbbbbbbaaaabaabababbbabbabaaaababbbbabaa\n" +
            "babaaaaaabbaabbbbbababaababababb\n" +
            "bbabbbabbbaabaaaabaaabbabbbbbbba\n" +
            "abaabaaaaabaabaaababaabaababbabbbbababbb\n" +
            "bbaabababbbaababaaabbbbaabaaaaaaabaaabbb\n" +
            "aabaabbaabbbbbaabaaaaaabbaabbababbaaabbaaabbabab\n" +
            "baabaabbaaaaaaabbaaaabab\n" +
            "baaabbababbabbbbaabbabab\n" +
            "aabbbbbbbaabbababaabababbabababbababbaaa\n" +
            "bbabbbbbbaabbabbabaaaaaabbbbaabb\n" +
            "aabbaaaaaabbaaaaaababbbb\n" +
            "bbbbabbababbabaaababbbbaabaaaaba\n" +
            "bbbbbbaaababaabaabbbbaababbbbbabbababbaa\n" +
            "aaaabbbaaababababbbaaabaabaaabbbbabaaaabaabbbaaa\n" +
            "bbabbbabbbbbbbbbababaabaabbbabaabbaababb\n" +
            "aababababbabaaaaababaaaa\n" +
            "babbaabbbaaaababbbaabaaababbabaaaabaabaaabbaabbabbabbbbbabbbbbab\n" +
            "aaabbabbaaaaabbaaabbbaabaaabaabb\n" +
            "abbbaababbbababbaabbbbbbababbbab\n" +
            "bbbabaabbaaaaaabbbbbbaababbaaabaabbabbbbaaaabbaa\n" +
            "aaabaaabababbaabababaaab\n" +
            "bbbbaabaaabbbaabaababbabaabaaaabaaaabbaaaabaaabb\n" +
            "aaaaaaaababaaaaababbbaabaaaaabbababbbbba\n" +
            "abbababbbbbaaaababbaaababaaabaababbabaaabbbbbabaababbabbaaabbabaabaabaab\n" +
            "aaabaaaabbabaaaaababaabaabbaaaaaaababbabbababaaa\n" +
            "baabbabbabababababbabbbbbabbabbb\n" +
            "abaaabbaabbbaababbbbaaabbbbbbabbaabaababbbbaaaba\n" +
            "ababbaabaabbabbababaaaabbaaaabbabbaabaababbbaabbbabbbbabbbbbaabb\n" +
            "baabbbabaabaabbaabbbbaaa\n" +
            "abababaaaaabaaaabbbbbabbbbaababb\n" +
            "ababababbaaabbabababaabbabbaabbbbaaabbababbbbbbb\n" +
            "abaaabbabbaabbbabbaabababbaaabbb\n" +
            "babbabaabaaababababbbbaabaaaaaabababbbaabaabaaba\n" +
            "babaaaaaababaabbabaabaabababaabbbbbbabbbaabbaababaababbaaababbbbbbbababa\n" +
            "baabaabbaabaaabaaabbaabbabbabbabbbaaaaaaabbbabab\n" +
            "bbbaabbababbaaaabbaaabbb\n" +
            "aabaabaabbbbaaababbabaaaababbabbbbbabbbaabaaabbbbbaaabbbaababaab\n" +
            "aaababbbbaababbbbabbaabbabbabaababaababb\n" +
            "bbababaabbbbabbaaabbabbbababababaabbaaabababaaaa\n" +
            "bababaababbaaabbaaaabbaa\n" +
            "abbbaabaaabaabbbaabbbababbbbabbaabaabaaaaabbbabaaaaaabbb\n" +
            "aabaabbabbabbbbbbbaaaaab\n" +
            "bbbabbabbbbaabbbabbbabab\n" +
            "bbaaabbaabaaaaaabbaaabbb\n" +
            "aaaaabaabbabaaaababbbabb\n" +
            "aaaaaaaaabbabbbbbbabbbbbaababbaa\n" +
            "bbbbbbabbbbbabbbabaaabab\n" +
            "abaabbbaabaababaaaaaaaababaaaababbbbaaaa\n" +
            "babbbaabbabaaabbbaabbaabbbbabaaaaaabaaaabbbbababbbaabbbbbbbbbaab\n" +
            "aabbbaabaaaabbbbaaababbabababaabbbbbbbbbbbbababbbbbbaaaaabaaabbbaababaaabbaaaaabbbbaabaa\n" +
            "aaaababbaabbabaaabbabbbaaabbaabbbbabbbbaabbabbaa\n" +
            "bbaabbaabbbaaaaaaabaaabb\n" +
            "babaabbbababaabaabaaaaaaababbabbababaabbabbbbaba\n" +
            "aaabbabababaaaaabbabaaaaaaaaaaabbbbbaabaaaabbbabbbbbbaab\n" +
            "aaaaaaaaababbaabaababbaa\n" +
            "bbabaaaabbaaaaaaaabbbbbbaabaaaaa\n" +
            "abbabaaaabababaabaabababbaaabaab\n" +
            "abababbbaababbabbbbbbbbbabbbbabbaababbabbbaabbabbbabbbaabababbab\n" +
            "babbbbaaabaabbbaaaabbaaa\n" +
            "bbbbbbaabbaabaaaabbaaabbbbbabbbbbbbabaab\n" +
            "babbaabaabbabbabbaababaa\n" +
            "abbaabbabaabbaaaabaaaabb\n" +
            "babaaaabbabbbbbbaaaabbaabbbabbababbbbaba\n";
}
