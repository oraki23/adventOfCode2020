package com.advco;

import java.math.BigInteger;
import java.text.Bidi;
import java.util.ArrayList;
import java.util.List;

public class Day13 {
    public static void main(String[] args) {
        partTwo();
    }

    public static void partOne(){
        String[] arrayInput = PUZZLE_INPUT.split("\n");

        int earliestTimestamp = Integer.parseInt(arrayInput[0]);

        String[] busIds = arrayInput[1].split(",");
        List<Integer> busIdsInt = new ArrayList<>();
        for(int i = 0; i < busIds.length; i++){
            if(!busIds[i].equals("x")){
                busIdsInt.add(Integer.parseInt(busIds[i]));
            }
        }

        int answerBusId = -1;
        boolean continu = true;

        int inc = 0;
        while(continu){
            for(int i = 0; i < busIdsInt.size(); i++){
                if((earliestTimestamp + inc) % busIdsInt.get(i) == 0){
                    answerBusId = busIdsInt.get(i);
                    continu = false;
                    break;
                }
            }
            inc++;
        }


        System.out.println("Answer Bus id: " + answerBusId);
        System.out.println("time waited: " + (inc-1));

        System.out.println("Answer: " + (answerBusId * (inc-1)));

    }

    public static void partTwo(){
        String[] arrayInput = PUZZLE_INPUT.split("\n");

        String[] busIds = arrayInput[1].split(",");
        List<Integer> busIdsInt = new ArrayList<>();
        for(int i = 0; i < busIds.length; i++){
            if(!busIds[i].equals("x")){
                busIdsInt.add(Integer.parseInt(busIds[i]));
            } else {
                busIdsInt.add(-1);
            }
        }

        BigInteger earliestTimestamp = new BigInteger("0");
        BigInteger possibleCandidateTimestamp = new BigInteger("0");
        BigInteger answerBus = BigInteger.valueOf(-1);

        BigInteger earliestTimestampInc = BigInteger.valueOf(busIdsInt.get(0));
        boolean continu = true;

        int maxI = 0;
        while(continu){
            int i;
            for(i = 0; i < busIdsInt.size(); i++){
                if(busIdsInt.get(i) != -1 && possibleCandidateTimestamp.mod(BigInteger.valueOf(busIdsInt.get(i))).compareTo(BigInteger.ZERO) != 0){
                    break;
                }

                possibleCandidateTimestamp = possibleCandidateTimestamp.add(BigInteger.ONE);

                if(busIdsInt.get(i) != -1 && i > maxI){
                    earliestTimestampInc = earliestTimestampInc.multiply(BigInteger.valueOf(busIdsInt.get(i)));
                    maxI = i;
                }
            }
            if(i == busIdsInt.size()){
                answerBus = earliestTimestamp;
                continu = false;
            }
            earliestTimestamp = earliestTimestamp.add(earliestTimestampInc);
            possibleCandidateTimestamp = earliestTimestamp;
        }

        System.out.println("answerBus: " + (answerBus));
    }

    public static final String PUZZLE_TEST = "939\n" +
            "7,13,x,x,59,x,31,19";
    public static final String PUZZLE_TEST_2 = "939\n" +
            "17,x,13,19";
    public static final String PUZZLE_TEST_3 = "939\n" +
            "67,7,59,61";
    public static final String PUZZLE_TEST_4 = "939\n" +
            "67,x,7,59,61";
    public static final String PUZZLE_TEST_5 = "939\n" +
            "67,7,x,59,61";
    public static final String PUZZLE_TEST_6 = "939\n" +
            "1789,37,47,1889";
    public static final String PUZZLE_INPUT = "1001938\n" +
            "41,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,37,x,x,x,x,x,431,x,x,x,x,x,x,x,23,x,x,x,x,13,x,x,x,17,x,19,x,x,x,x,x,x,x,x,x,x,x,863,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,29";
}
