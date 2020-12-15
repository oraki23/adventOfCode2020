package com.advco;

import java.util.*;

public class Day15 {
    public static void main(String[] args) {
        partTwo();
    }

    public static void partOne(){
        String[] arrayInput = PUZZLE_INPUT.split(",");
        List<Integer> turns = new ArrayList<>();

        int lastSpokenNumber = -1;

        //Creating the turns array
        for(int i = 0; i < arrayInput.length; i++){
            int number = Integer.parseInt(arrayInput[i]);
            turns.add(number);
            lastSpokenNumber = number;
        }

        while(turns.size() < 2020){
            int otherRoundLastSpokenNumberMem = lastSpokenNumber;
            if(!turns.subList(0,turns.size()-1).contains(lastSpokenNumber)){
                turns.add(0);
                lastSpokenNumber = 0;
            } else{
                int otherLastId = -1;
                List<Integer> subList = turns.subList(0,turns.size()-1);
                for(int i = subList.size()-1; i >= 0; i--){
                    if(subList.get(i) == lastSpokenNumber){
                        otherLastId = i;
                        break;
                    }
                }
                int numberToAdd = (turns.size()-1) - otherLastId;

                turns.add(numberToAdd);
                lastSpokenNumber = numberToAdd;
            }
            System.out.println("Turn Number: " + (turns.size()) + " - For number: " + otherRoundLastSpokenNumberMem + " added number: " + lastSpokenNumber);
        }

        System.out.println("Last spoken Number: " + lastSpokenNumber);
    }

    public static void partTwo(){
        String[] arrayInput = PUZZLE_INPUT.split(",");

        HashMap<Integer, Integer> hmap = new HashMap<>();

        int lastSpokenNumber = -1;

        int arraySize = 0;

        //Creating the turns array
        for(int i = 0; i < arrayInput.length; i++){
            int number = Integer.parseInt(arrayInput[i]);
            arraySize++;

            if(i != arrayInput.length-1){
                hmap.put(number, i);
            }

            lastSpokenNumber = number;
        }

        while(arraySize < 30000000){
            if(!hmap.containsKey(lastSpokenNumber)){
                arraySize++;
                hmap.put(lastSpokenNumber, arraySize-2);
                lastSpokenNumber = 0;
            } else{
                int otherLastId = hmap.get(lastSpokenNumber);

                int numberToAdd = (arraySize-1) - otherLastId;

                arraySize++;
                hmap.put(lastSpokenNumber, arraySize-2);

                lastSpokenNumber = numberToAdd;
            }
        }

        System.out.println("Last spoken Number: " + lastSpokenNumber);
    }

    public static final String PUZZLE_TEST = "0,3,6";
    public static final String PUZZLE_TEST_1 = "1,3,2";
    public static final String PUZZLE_TEST_2 = "2,1,3";
    public static final String PUZZLE_TEST_3 = "1,2,3";
    public static final String PUZZLE_TEST_4 = "2,3,1";
    public static final String PUZZLE_TEST_5 = "3,2,1";
    public static final String PUZZLE_TEST_6 = "3,1,2";

    public static final String PUZZLE_INPUT = "15,12,0,14,3,1";
}
