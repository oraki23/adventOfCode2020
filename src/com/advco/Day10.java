package com.advco;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day10 {
    public static void main(String[] args) {
        partTwo();
    }

    public static void partOne(){
        int nbOf1JoltDiff = 0;
        int nbOf3JoltDiff = 0;

        String[] input = PUZZLE_INPUT.split("\n");
        int[] array = new int[input.length];
        for(int i = 0; i < array.length; i++){
            array[i] = Integer.parseInt(input[i]);
        }

        Arrays.sort(array);

        int currentHighestRatedAdapter = 0;

        for(int i = 0; i < array.length; i++){
            int diff = array[i] - currentHighestRatedAdapter;
            if(diff == 1){
                nbOf1JoltDiff++;
            } else if(diff == 3){
                nbOf3JoltDiff++;
            }

            currentHighestRatedAdapter = array[i];
        }
        nbOf3JoltDiff++;

        System.out.println("nbOf1JoltDiff = " + (nbOf1JoltDiff));
        System.out.println("nbOf3JoltDiff = " + (nbOf3JoltDiff));
        System.out.println("nbOf1JoltDiff * nbOf3JoltDiff = " + (nbOf1JoltDiff * nbOf3JoltDiff));
    }

    public static void partTwo(){
        String[] input = PUZZLE_INPUT.split("\n");
        int[] array = new int[input.length];
        for(int i = 0; i < array.length; i++){
            array[i] = Integer.parseInt(input[i]);
        }

        Arrays.sort(array);

        BigInteger nbOfArrangements = validateCombination(array, 0/*, "(0), "*/);

        System.out.println("nbOfArrangements = " + nbOfArrangements);
    }

    public static Map<int[], BigInteger> test = new HashMap<>();
    public static BigInteger valuemax = BigInteger.valueOf(Integer.MIN_VALUE);

    public static BigInteger validateCombination(int[] array, int currentHighestRatedAdapter/*, String previous*/){
        if(array.length == 1){
            //System.out.println(previous + array[0] + ", (" + (array[0]+3) + ")");
            return BigInteger.ONE;
        }

        BigInteger nbOfArrangement = BigInteger.ZERO;
        for(int i = 0; i < array.length; i++){
            int diff = array[i] - currentHighestRatedAdapter;

            if(diff <= 3 && diff != 0){
                int[] subArray = Arrays.copyOfRange(array, i, array.length);

                BigInteger newGetValue = newGet(subArray);
                if(newGetValue.compareTo(BigInteger.valueOf(-1)) != 0){
                    nbOfArrangement = nbOfArrangement.add(newGetValue);
                } else {
                    BigInteger nbOfSubArrangements = validateCombination(subArray, array[i]/*, previous + array[i] + ", "*/);
                    test.put(subArray, nbOfSubArrangements);
                    nbOfArrangement = nbOfArrangement.add(nbOfSubArrangements);
                }

            } else if (diff > 3) {
                break;
            }
        }

        if(nbOfArrangement.compareTo(valuemax) == 1){
            valuemax = nbOfArrangement;
            System.out.println(valuemax);
        }
        return nbOfArrangement;
    }

    public static BigInteger newGet(int[] array){
        for(int i = 0; i < test.size(); i++){
            int[] arrayInside = (int[])test.keySet().toArray()[i];
            if(Arrays.equals(array, arrayInside)){
                return (BigInteger)(test.values().toArray()[i]);
            }
        }
        return BigInteger.valueOf(-1);
    }

    public static final String PUZZLE_TEST_2 = "16\n" +
            "10\n" +
            "15\n" +
            "5\n" +
            "1\n" +
            "11\n" +
            "7\n" +
            "19\n" +
            "6\n" +
            "12\n" +
            "4";
    public static final String PUZZLE_TEST = "28\n" +
            "33\n" +
            "18\n" +
            "42\n" +
            "31\n" +
            "14\n" +
            "46\n" +
            "20\n" +
            "48\n" +
            "47\n" +
            "24\n" +
            "23\n" +
            "49\n" +
            "45\n" +
            "19\n" +
            "38\n" +
            "39\n" +
            "11\n" +
            "1\n" +
            "32\n" +
            "25\n" +
            "35\n" +
            "8\n" +
            "17\n" +
            "7\n" +
            "9\n" +
            "4\n" +
            "2\n" +
            "34\n" +
            "10\n" +
            "3";
    public static final String PUZZLE_INPUT = "178\n" +
            "135\n" +
            "78\n" +
            "181\n" +
            "137\n" +
            "16\n" +
            "74\n" +
            "11\n" +
            "142\n" +
            "109\n" +
            "148\n" +
            "108\n" +
            "151\n" +
            "184\n" +
            "121\n" +
            "58\n" +
            "110\n" +
            "52\n" +
            "169\n" +
            "128\n" +
            "2\n" +
            "119\n" +
            "38\n" +
            "136\n" +
            "25\n" +
            "26\n" +
            "73\n" +
            "157\n" +
            "153\n" +
            "7\n" +
            "19\n" +
            "160\n" +
            "4\n" +
            "80\n" +
            "10\n" +
            "51\n" +
            "1\n" +
            "131\n" +
            "55\n" +
            "86\n" +
            "87\n" +
            "21\n" +
            "46\n" +
            "88\n" +
            "173\n" +
            "71\n" +
            "64\n" +
            "114\n" +
            "120\n" +
            "167\n" +
            "172\n" +
            "145\n" +
            "130\n" +
            "33\n" +
            "20\n" +
            "190\n" +
            "35\n" +
            "79\n" +
            "162\n" +
            "122\n" +
            "98\n" +
            "177\n" +
            "179\n" +
            "68\n" +
            "48\n" +
            "118\n" +
            "125\n" +
            "192\n" +
            "174\n" +
            "99\n" +
            "152\n" +
            "3\n" +
            "89\n" +
            "105\n" +
            "180\n" +
            "191\n" +
            "61\n" +
            "13\n" +
            "90\n" +
            "129\n" +
            "47\n" +
            "138\n" +
            "67\n" +
            "115\n" +
            "44\n" +
            "59\n" +
            "60\n" +
            "95\n" +
            "93\n" +
            "166\n" +
            "154\n" +
            "101\n" +
            "34\n" +
            "113\n" +
            "139\n" +
            "77\n" +
            "94\n" +
            "161\n" +
            "187\n" +
            "45\n" +
            "22\n" +
            "12\n" +
            "163\n" +
            "41\n" +
            "27\n" +
            "132\n" +
            "30\n" +
            "143\n" +
            "168\n" +
            "144\n" +
            "83\n" +
            "100\n" +
            "102\n" +
            "72\n";
}
