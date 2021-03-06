package com.advco;

import sun.util.locale.provider.LocaleProviderAdapter;

import java.math.BigInteger;
import java.text.Bidi;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day18 {
    public static void main(String[] args) {
        partFour();
    }

    /* Part one OLD */
    public static void partOne(){
        String[] arrayInput = PUZZLE_INPUT.split("\n");
        BigInteger totalResult = BigInteger.ZERO;
        for(int i = 0; i <  arrayInput.length; i++){
            totalResult = totalResult.add(calculation(arrayInput[i]));
        }

        System.out.println("All line results: " + totalResult);

    }

    public static BigInteger calculation(String equation){
        BigInteger lastNumber = BigInteger.valueOf(-1);
        int operation = '*';

        for(int j = 0; j < equation.length(); j++){
            char c = equation.charAt(j);
            if(c == '('){
                int lastIndexParentesis = 0;
                int lastIndex = 0;

                do{
                    lastIndex = equation.substring(j).indexOf(")", lastIndex+1);

                    lastIndexParentesis = equation.substring(j).indexOf("(",lastIndexParentesis+1);
                }while(lastIndexParentesis != -1 && lastIndex > lastIndexParentesis);

                String insideEquation = equation.substring(j).substring(1, lastIndex);
                BigInteger parenthesisCalculation = calculation(insideEquation);

                if(lastNumber.compareTo(BigInteger.valueOf(-1)) != 0){
                    switch(operation){
                        case '*':
                            lastNumber = lastNumber.multiply(parenthesisCalculation);
                            break;
                        case '+':
                            lastNumber = lastNumber.add(parenthesisCalculation);
                            break;
                    }
                } else {
                    lastNumber = parenthesisCalculation;
                }

                //Go Forward until index
                j = lastIndex+j+1;

            } else if(c == '*' || c == '+'){
                operation = c;
            } else if(c != ' '){
                if(lastNumber.compareTo(BigInteger.valueOf(-1)) != 0){
                    int currentNumber = Integer.parseInt(String.valueOf(c));
                    switch(operation){
                        case '*':
                            lastNumber = lastNumber.multiply(BigInteger.valueOf(currentNumber));
                            break;
                        case '+':
                            lastNumber = lastNumber.add(BigInteger.valueOf(currentNumber));
                            break;
                    }
                } else {
                    lastNumber = BigInteger.valueOf(Integer.parseInt(String.valueOf(c)));
                }
            }
        }

        return lastNumber;
    }

    /* Part One New */
    public static void partThree(){
        String[] arrayInput = PUZZLE_INPUT.split("\n");

        BigInteger totalResult = BigInteger.ZERO;
        for(int i = 0; i <  arrayInput.length; i++){
            totalResult = totalResult.add(calculation3(arrayInput[i]));
        }

        System.out.println("All line results: " + totalResult);
    }

    public static BigInteger calculation3(String equation){
        BigInteger lastNumber = BigInteger.valueOf(-1);
        int operation = '+';

        Pattern patt = Pattern.compile("\\({1}[0-9+* ]+\\){1}");

        Matcher matcher = patt.matcher(equation);

        int count = 0;
        while(matcher.find()) {
            count++;
            int startIndex = matcher.start();
            int endIndex = matcher.end();
            System.out.println("found: " + count + " : "
                    + startIndex + " - " + endIndex);

            System.out.println("word: " + equation.substring(startIndex+1, endIndex-1));

            String insideEquation = equation.substring(matcher.start()+1, matcher.end()-1);

            BigInteger equationCalculationResult = calculation3(insideEquation);

            equation = equation.replace(equation.substring(startIndex, endIndex), equationCalculationResult.toString());

            matcher = patt.matcher(equation);
        }

        for(int j = 0; j < equation.length(); j++){
            String c = String.valueOf(equation.charAt(j));

            if(c.equals("*") || c.equals("+")){
                operation = c.charAt(0);
            } else if(!c.equals(" ")){
                int inc = 1;
                while(j+inc < equation.length() && equation.charAt(j+inc) != ' '){
                    c = c + equation.charAt(j+inc);
                    inc++;
                }
                j = j + inc -1;

                if(lastNumber.compareTo(BigInteger.valueOf(-1)) != 0){
                    int currentNumber = Integer.parseInt(c);
                    switch(operation){
                        case '*':
                            lastNumber = lastNumber.multiply(BigInteger.valueOf(currentNumber));
                            break;
                        case '+':
                            lastNumber = lastNumber.add(BigInteger.valueOf(currentNumber));
                            break;
                    }
                } else {
                    lastNumber = BigInteger.valueOf(Integer.parseInt(String.valueOf(c)));
                }
            }
        }

        return lastNumber;
    }

    /* Part Two New */
    public static void partFour(){
        String[] arrayInput = PUZZLE_INPUT.split("\n");

        BigInteger totalResult = BigInteger.ZERO;
        for(int i = 0; i <  arrayInput.length; i++){
            totalResult = totalResult.add(calculation4(arrayInput[i]));
        }

        System.out.println("All line results: " + totalResult);
    }

    public static BigInteger calculation4(String equation){
        BigInteger lastNumber = BigInteger.valueOf(-1);
        int operation = '+';

        Pattern patt = Pattern.compile("\\({1}[0-9+* ]+\\){1}");

        Matcher matcher = patt.matcher(equation);

        int count = 0;
        while(matcher.find()) {
            count++;
            int startIndex = matcher.start();
            int endIndex = matcher.end();
            System.out.println("found: " + count + " : "
                    + startIndex + " - " + endIndex);

            System.out.println("word: " + equation.substring(startIndex+1, endIndex-1));

            String insideEquation = equation.substring(matcher.start()+1, matcher.end()-1);

            BigInteger equationCalculationResult = calculation4(insideEquation);

            equation = equation.replace(equation.substring(startIndex, endIndex), equationCalculationResult.toString());

            matcher = patt.matcher(equation);
        }

        int indexOfPlus = equation.indexOf('+');
        int indexOfMult = equation.indexOf('*');
        while(indexOfPlus != -1 && indexOfMult != -1){

            String insideEquation = equation.substring(indexOfPlus-2, indexOfPlus+3);

            int inc = 1;
            while(indexOfPlus+2+inc < equation.length() && equation.charAt(indexOfPlus+2+inc) != ' '){
                insideEquation = insideEquation + equation.charAt(indexOfPlus+2+inc);
                inc++;
            }
            //indexOfPlus = indexOfPlus + inc - 1;
            int incStart = 1;
            while(indexOfPlus-2-incStart >= 0 && equation.charAt(indexOfPlus-2-incStart) != ' '){
                insideEquation = equation.charAt(indexOfPlus-2-incStart) + insideEquation;
                incStart++;
            }

            BigInteger equationCalculationResult = calculation4(insideEquation);

            equation = equation.replace(equation.substring(indexOfPlus-2-(incStart-1), indexOfPlus+3+(inc-1)), equationCalculationResult.toString());

            indexOfPlus = equation.indexOf('+');
        }

        for(int j = 0; j < equation.length(); j++){
            String c = String.valueOf(equation.charAt(j));

            if(c.equals("*") || c.equals("+")){
                operation = c.charAt(0);
            } else if(!c.equals(" ")){
                int inc = 1;
                while(j+inc < equation.length() && equation.charAt(j+inc) != ' '){
                    c = c + equation.charAt(j+inc);
                    inc++;
                }
                j = j + inc -1;

                if(lastNumber.compareTo(BigInteger.valueOf(-1)) != 0){
                    BigInteger currentNumber = new BigInteger(String.valueOf(c));
                    switch(operation){
                        case '*':
                            lastNumber = lastNumber.multiply(currentNumber);
                            break;
                        case '+':
                            lastNumber = lastNumber.add(currentNumber);
                            break;
                    }
                } else {
                    lastNumber = new BigInteger(String.valueOf(c));
                }
            }
        }

        return lastNumber;
    }

    public static final String PUZZLE_TEST = "1 + 2 * 3 + 4 * 5 + 6";
    public static final String PUZZLE_TEST_2 = "1 + (2 * 3) + (4 * (5 + 6))";
    public static final String PUZZLE_TEST_3 = "2 * 3 + (4 * 5)";
    public static final String PUZZLE_TEST_4 = "5 + (8 * 3 + 9 + 3 * 4 * 3)";
    public static final String PUZZLE_TEST_5 = "5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))";
    public static final String PUZZLE_TEST_6 = "((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2";

    public static final String PUZZLE_INPUT = "7 * (4 * 2 + 8 * (6 + 9) * 7 * 6) + (2 + 5 * 5 * 4 + 6 + 9) * 6\n" +
            "4 * 8 * 9 + (6 * 7 * 8 * (6 * 5 * 2 + 8 + 5)) + 7\n" +
            "(2 * 6 * 4 + 3 * 3) * 9 * 9\n" +
            "3 * (2 * 2 + (5 * 8 * 6 * 6))\n" +
            "6 + (4 + 4) * 2 + 8\n" +
            "(8 + (5 + 9 + 8 + 7)) * (4 * 8)\n" +
            "8 + (8 * 6 * 6 + 4 * 7) * 6\n" +
            "7 * (8 * (3 + 4 * 8 * 6) * (8 + 4 + 2 * 8) + 6 * 5) + 9\n" +
            "(8 + 8 * 3 + 6 * 4 * 4) * 6 + 2 + 2 + 6\n" +
            "2 + (3 + (5 * 7 + 3) + 5 + 5 + 5 + 6) + 8 * (6 + 4 * 2 + 5 + 5 * 4) + 5\n" +
            "7 * (8 * 9 * 2 * 6 + (2 * 7 + 7 * 2 + 2 + 8)) * 7 + 8\n" +
            "(5 * 2 * 4 + 2 + 6 * (6 * 6 + 4)) + 9 + 5\n" +
            "((8 + 4 * 6) * 5) + 8\n" +
            "(4 + 2 * 2 + 6 * 9) * 3\n" +
            "5 * 2 * (2 + 5 * 3 * 7) + (8 + (2 + 2 + 7 * 8 + 2 * 8) * (5 + 2 * 2) * 2 * 8) + 6\n" +
            "4 + 8 + 6 + (7 * 2) * (8 * 5 * 5 + (9 * 9 + 4 + 6) + 6 + 6)\n" +
            "((7 + 5 * 6) * 6 + (6 + 3 * 5 * 8) + (9 + 6 + 9 * 8)) * 3 + 3\n" +
            "(7 * 8 * 6 + 8 * (7 + 2 + 3 + 2 + 2 * 3) + (8 * 9 * 8)) + (2 + 5 + (6 + 7 + 6)) + 5 + 8 + 5 * 4\n" +
            "(2 * 8 + (7 + 7)) + 4 * 9 * 4 * (2 * (4 * 5 * 3 * 9 * 9) + (4 * 3 + 5 * 4 + 7 + 3) * (5 * 3 * 2 + 4 * 7 + 7) * 4) * 4\n" +
            "7 + 9 + (3 * (7 + 5) + (7 * 5 * 3 + 9))\n" +
            "5 + (9 + 6 * 7) * 9 + (8 * 5 * 5 * (6 + 8 * 2 * 4 + 3) * 8 * 9) * 9\n" +
            "(4 + 3 + 9) * 9 * 6 * 7 * 6 * 6\n" +
            "3 + ((8 + 9) * 6 * 6) + 9\n" +
            "(7 + 8 * 2 * (2 + 8 * 2) * (7 * 3 + 4)) * (4 * 9 * (5 * 2 * 9) + (3 + 6 * 4 * 7 + 8 + 6) + (6 + 6)) * (2 + 7 * 5) + 8\n" +
            "(8 * 7) + 9\n" +
            "(9 * 9 + 3 * 8 * (4 * 9 + 6) + 2) + (5 + 3) * 4\n" +
            "(3 * 7 * 4 + 3 * 2 * 8) + 4\n" +
            "9 * 8 * (9 * (7 * 7) + 6 + 2 + 2 + 3) + 7 * 4\n" +
            "4 * 3 * (5 + 8 + 8 + (2 + 4 * 3 * 5 * 5 + 7) * 5 * (4 * 7 + 2 * 2 + 3 + 7)) + 8\n" +
            "7 + (2 * (3 + 2) + 5 * 9 * 6 + 4) + (8 * (5 + 5 * 9) * 9)\n" +
            "(6 * 9 + 7 * 2) * ((4 + 7 + 8) + (8 + 2 * 9 * 6 * 5) + 6 * (8 + 6 * 7 * 8 + 4 * 4) * 5 + 6) * (3 + 2)\n" +
            "7 * 6 + 9 * (7 + 3 + 8 + 6)\n" +
            "4 * 5 * 8 + 5 + 6 + 6\n" +
            "(2 * 6 * 3) * (9 * 4 * (9 * 5 + 9 * 7 * 7 * 4) * 5 * (4 * 6 * 8)) * 8\n" +
            "5 * 9 + (8 * 6) + (5 + 6) * 2\n" +
            "4 + 6 + 3 * (8 + 2 + 5 + (6 * 3 * 8 * 8 * 6 + 7) + 3 * (8 * 5 * 8 + 5 * 6 + 8)) + 9 * (2 * 5)\n" +
            "(8 * 7 + 9 + 7 * 9 + 4) + 3 + 9 + 7 + 7 * 8\n" +
            "5 * 3 * (4 + 7 + (3 * 4) + (9 + 9 * 4 * 4 + 8) * 6 * 4) * 2 * 3\n" +
            "5 * 4 * ((6 * 5) * (2 + 7 + 8 + 5 * 5))\n" +
            "8 + ((8 * 9 * 2 * 7 + 5 * 3) * 8 * (9 * 7) + (5 + 4 * 6 * 3 + 6 + 9) + 2 * 7) * 3 + 2 * 7\n" +
            "2 * (6 + 3 + (5 + 5 * 3 + 2 + 7 + 7) + 2 + (8 * 7 + 7 * 3 * 8) + 4) * 3 * 3 * (5 + 5 * 5 * (6 * 2 + 5 + 8) + 9)\n" +
            "(7 + 4 + 2 * (9 + 4 + 5)) + 7 + (2 + (6 + 2 * 2 * 7 + 7 + 5) + 6 + 9 + 4 * 8) * 3\n" +
            "(8 + (9 * 7 + 8 * 4 * 7) * 9 * 5 + 4) * (2 + 7 * 6 * (8 + 8) + (3 * 3 + 8 + 8 + 2 + 9) + 7) + 4 + 2 * 9\n" +
            "(2 * 4 + (9 * 4 * 8 + 2 * 8)) * 3\n" +
            "6 + 9 * 2 + ((4 * 5 * 4) * 6 + 5 * 7) * 9\n" +
            "5 * 2 * (7 * 3) * 7\n" +
            "((5 + 4 * 5) * (3 * 9 * 8) + 4 + 5 + 7 * 2) + 3 * 4 * (3 + (6 * 2 + 2) * 5)\n" +
            "2 * 8 * 2 * 9\n" +
            "(6 + (9 + 7 + 9 + 4) * 8 + 2 * 3) * 3\n" +
            "6 + 9 * (7 + 8 + (6 * 6 + 7 + 7))\n" +
            "4 * (9 * 4 + 6) + 9 + 6\n" +
            "6 * 8 * (8 * (3 * 9 * 5 + 3 * 6 * 5) + 4 * (6 + 3 + 8 * 3 * 9) * 2 * 8) + 3\n" +
            "7 * 5 + ((6 + 3 * 5 + 3 * 4 * 6) + 9 + 9 + (4 + 7 + 5) * 2 * 2) * 5 * 8 * 5\n" +
            "(4 + 2 + 7 * (6 + 6)) + 5 + 7 * (6 * (5 * 8 * 8 * 5 + 4 + 8))\n" +
            "5 + 9 + 3 + 6 + ((7 + 2 + 4 + 2 * 4 * 3) + 9)\n" +
            "4 + (7 * 9 + 5 * (2 + 6 * 7 + 2 * 8)) * 5 + (8 + 7 * 7 + 9)\n" +
            "5 * 5 + 4 * (6 * 2 + 5 + 7 * 8 + (3 * 2 * 2 + 9)) * 5 + (7 * 6 * 3)\n" +
            "5 + 4 * 4 + (7 * 5 * (6 + 6) + 2 + 2) * 6\n" +
            "7 * 8 * (4 * 3 * 4 * 4 * 7 + 6)\n" +
            "2 + 5 + 9\n" +
            "8 + 2 * (8 * 3 + 3 + 7) * 8 + 4\n" +
            "3 * (7 + (7 + 6 * 8 + 9 * 3))\n" +
            "(3 + 5 + 7 * (7 * 5 * 8 + 7 * 4) + 6) + 9 * 7\n" +
            "(3 * (6 + 7 * 7 * 3 + 3) * (8 + 2 + 6 + 6) * 2 + 7 + 4) * 9 * 6 * 3 * 8 * 9\n" +
            "2 + (7 * (8 + 7 + 2))\n" +
            "4 + 4 + 8 + (5 + 3 * 3 + 4 + (9 * 9 * 9 + 3 + 6 * 4)) + 4\n" +
            "(7 + 9 + 8 * 7 + 5 * 6) + 8 + 7 * 6 + (7 + 2) + 6\n" +
            "(3 * 9 + 3 * 4 + 6 + 9) + (2 + 6) * (2 + 7 * 6 * 9 + 4 * 9) + 3 * 9\n" +
            "8 * 4 + ((2 * 4 * 8 * 5 * 7) * 5 + 2) * 9\n" +
            "(6 * 5 * (4 + 9) * (3 + 7 + 7) + 7 * 6) + (5 * 4 + 5 * 6 * 6 + (8 * 6)) * 9 + 4 + 2\n" +
            "(9 + (8 * 5 * 5) * 5 + 3 * (5 * 6 + 8 + 6 + 3 * 9)) * (5 + 4 + 3 + 3 * (7 + 5 + 8 * 4 + 9 * 7))\n" +
            "9 + (7 * 8)\n" +
            "3 + 8 * 4 * (2 + 3 + 2) + 6\n" +
            "4 + 8 * (6 + 8 * 8) + 3 + 3\n" +
            "(2 * (8 + 6) * 3 + 6 + 6 * 4) * 8 * 3\n" +
            "((3 * 7 * 2 * 6 + 3) * 2 + 3 * 9 + (9 * 5 * 7 + 5 + 6) + 6) + 4 + 9\n" +
            "(7 + (7 + 9 * 5) + 5 + 2) * 6\n" +
            "9 * 7 * 9\n" +
            "2 * (4 + 6 + 5 + 3 * 5) * 6 * 2\n" +
            "(5 + 3 * 7 * 8 * 9) + 5 + 5 + 3 * 6 * (7 * 8 * 5)\n" +
            "7 + 8 + 3 + 7 * 7 * 3\n" +
            "4 + (8 * 6 + 6 + 5) + (3 + 8 + 9 * 9) * 8 * 6\n" +
            "3 * 2 * (5 + 3) + 7 + 7 + (6 + 4)\n" +
            "6 + 9 + 9 + (2 * 5 + 6 + 8 + 4)\n" +
            "(2 + 3 * 5 + 6 + (7 * 2 + 6) * 6) + 6 * 8 * 7 + 9 * (5 + (8 + 4 * 6) + 9 + (7 + 3))\n" +
            "6 + 6 * (8 + 7 + 4 + (3 * 8 + 6 * 8) + 3) + 3\n" +
            "9 * 8 + (9 + 6) + 4 + 5\n" +
            "7 + (6 * 8 * 3 + 9)\n" +
            "6 * 5 * 8\n" +
            "((4 * 5 * 2) + (6 + 4 + 5 + 5 + 7 * 5) * 7 + 5 + 9) + (2 + 8 + (2 + 4) * 8) * 4 + (3 + (7 + 2 * 9) * 2 * 6 + 3 + (2 * 5 + 5 + 4 * 4 + 7)) * (7 + 6 * 4) * 5\n" +
            "6 + 6 * 9 * (7 * 2 * 4 + 8 * (7 + 2 + 9 + 2) + 8) + 6 + 5\n" +
            "((4 + 4 * 8 + 3 + 8 * 2) * 4) * (5 + 4 + 5 + 5 + 3 * 8) * ((9 + 9 + 7) + 6 + 7 + (9 + 5 * 8) * (6 * 7 * 2 + 2 + 4)) * 4 * 6\n" +
            "3 + 7 + 5 * 4 * 8\n" +
            "((9 + 4 * 3 + 9 * 3 * 8) * 4 * 4 + 3 + 4) + 4 + 9 + (2 + 4 * 8 + 7 * 7 * 4)\n" +
            "4 + 2 + 7 * 5 * 5\n" +
            "(5 + 9 + 2 + 8 + 5) * (6 + 6) * 6 + 3 * 6\n" +
            "2 + 6 + 5 * 7 + (5 + 8 + 7) * 9\n" +
            "8 + 6 + (4 + 7 * (7 * 3 * 6 * 3) * 6 + 6) + 9 + 5\n" +
            "((5 + 7 * 3 * 4 + 2 * 7) + 5 + 7 + 4 * 6) + 9 + 4 * ((2 + 6) * (4 * 5 * 3 * 8) + (3 * 5) * 3) * 6\n" +
            "(2 + 2 * (7 * 8 + 3 + 2 + 3) * (5 + 7) * (6 * 2 + 6 + 9 * 4) + (9 + 3 + 5)) * 3 + 2 * 6 + 5 + 9\n" +
            "9 * (4 * 6 * 8 + 6 + 3 * 9) * 7 * 6 + (8 + 3 * 4 * 4)\n" +
            "7 * 2 + 5 + 3\n" +
            "3 + (5 + 7 + 9 * (4 * 5 * 8 + 6) * (8 * 4 * 5 + 4) * 6) * 6 + 9\n" +
            "(9 * 3 * 9 * 4 * 8 * (2 * 9 * 3)) * (4 * 8 * 7 * 8) * 9 * 3 * 3 * 3\n" +
            "(3 + 6 + 4) + ((2 * 2 + 3) * 6 + 8 * 9 * 2 + (3 + 5 * 4 + 7 + 6)) * 9 + 6 + 6 * (4 * 2 + 9 + 4 * 5)\n" +
            "2 * 6 * 3 * ((2 + 8 * 8) * 6 * 4 + 5) + (2 + 6) * 8\n" +
            "5 + 7 * 9 + ((6 + 3 * 7 + 9 + 2 + 2) + 9) + 3\n" +
            "4 * (6 * 7 * 4) * 6\n" +
            "5 * (8 * 3 * 8 * 7 + (2 + 6 * 7 + 2 * 2)) * (3 * 3 + (2 * 5) * (8 + 6 * 2 + 6 + 6 * 7)) + 6\n" +
            "((5 + 3 * 7 + 4 + 2 * 4) + (7 + 8 * 6)) * (9 + 6 + (9 * 6 * 4 * 6 * 6 + 3)) + 6 * 8 * 9 + 3\n" +
            "(4 * 3 + 4) * 3 * (8 * 5) + 9 * 3 * 8\n" +
            "2 + (9 + 5 + (5 * 4 * 5) * 7 * (5 * 7 + 2 + 3 * 8)) * 6 + 5 + 7 * 7\n" +
            "8 * 8 * (4 * 8 * 5 + 7) + ((6 + 3 + 2 + 5 + 2 + 2) + 5 * 2)\n" +
            "(9 * (5 * 5 * 5 + 6 * 7)) * 9 + 9\n" +
            "3 + 4 * 8 + (5 * 8 * 8 * (2 * 6 * 5 * 4 * 9) * (4 + 5 + 2 + 6 * 4 * 3) + 7) + 5\n" +
            "8 + ((3 * 6 * 4 + 9) * 9 + 3)\n" +
            "5 * 8 + 6 + (3 + 8 * 8 + 8 * 7) + 8\n" +
            "6 * 5 + 2 + 3 * 9\n" +
            "8 * (6 * 6 * (7 + 7 * 6))\n" +
            "8 + 7 + 8 * 6 + (6 + (7 * 3 * 4) + 4 * 3 * (3 * 9 * 3 + 9) + 6) * (2 * 7 * 3 + 4)\n" +
            "6 + 9 * 9 + (4 + 4) + (6 + 9)\n" +
            "3 * 8 * 8 + ((9 + 9) * 2) * 7 + 5\n" +
            "4 * 6 * 3 * (5 + 9 * 4 * 2 * 5 * 2) + 2\n" +
            "5 + (8 + 7 + (6 + 8 * 6 + 8 + 4) + (7 * 3 + 4 * 6) + 6 + 4)\n" +
            "3 + 8 * 8 + ((9 * 3 * 3) * 6) * 6\n" +
            "2 + 9 * (5 * 5 * (8 * 2 + 5 + 5 + 3)) * 8 + 8 + 3\n" +
            "7 * 6 + 8 + 2 + (2 + 8 * 7 * 6)\n" +
            "(9 + 5 + 9 + (5 * 5 + 7 * 9)) + 3\n" +
            "3 * 7 * (3 * 6 * (7 * 4 + 5 * 5 * 8 * 9) + 7 * 4 * 4) * (4 * 6 + 7) + ((3 * 8 * 5) * 2)\n" +
            "3 * 2 * 5 * ((7 * 6) * 5 * 9 + 4 + 9)\n" +
            "7 * (8 + 7 * 6 * 3 * 3)\n" +
            "8 * 9 * 6 + (9 + (5 + 6 * 3 * 3 + 5 * 2) + 4 + 7) + 6 + 5\n" +
            "2 + 3 * 7 * 3 + ((9 * 8 + 7 * 3 * 9) + (5 + 8 + 5 * 2 + 6)) + (3 + 7 + 4)\n" +
            "3 * 6 * (3 + 5 + 2 * (9 + 6 + 4 * 4 * 7 * 6) * (3 * 9 + 5 * 6)) * 5 * 4\n" +
            "(9 + 7) + 7 * 7 * (6 * 5 * 5)\n" +
            "(9 * 8) * 9 * 7 * (5 * 3 * 8 * 4) + 7 * 9\n" +
            "5 * 6 + 9\n" +
            "9 + 6 * (8 + (7 + 9 * 3 * 8 * 6 * 8) * 7 + (5 + 6 + 4 * 9)) + 5\n" +
            "3 + (7 + 7 + 8 + 4 + 6) + 4 + 7 * 9\n" +
            "5 * ((8 + 5 * 7) + (4 + 9 * 9)) * 7 + 3 * 9 * 6\n" +
            "9 * (2 + (7 * 6 + 2 + 8 + 2)) * 2\n" +
            "3 + (8 + (4 + 4) + 3 + (2 + 2 + 9 + 7 * 6 * 9) * 2 * 6) * 5\n" +
            "(9 * 8) * 3 * 4 + 9 + 4 * 6\n" +
            "7 + 6 * (4 + (8 + 8 + 6 + 3) + 8 * 8 * (4 + 6 + 5 * 8)) + 6\n" +
            "5 * 4 + 8 * ((6 + 9 * 6) * 5 * (5 + 2 + 7 * 9) * 9 * 4 * 5) * (3 + 2) + 3\n" +
            "9 + 4 * (8 + 7 * 3 * 7 * (4 + 6 * 8) + 6) * 9\n" +
            "((6 * 3 + 3) * 8) + 8 + 9 * 7 + 6 + 5\n" +
            "7 * (3 + (3 + 2 * 9 + 5) * 6) + 5\n" +
            "3 + (2 * 2 + 7 * (2 * 9 * 9 + 2 * 9 * 6)) * 9 * 5 + 8 * 5\n" +
            "3 + (9 + (8 + 4 * 5 * 4) + 7 * 8 + 7) * 9 + 2\n" +
            "(2 + 8 * 5 * 8 * (3 * 5 * 3 + 6 * 3) + 3) * 6\n" +
            "3 * 9 * 5 * 7 + 5 * (8 + 4 + 5 * 7 * 8)\n" +
            "(3 + 8 + 3 + 7 + (9 + 8 * 7 + 2 * 5) * (2 * 4 * 7 * 4 * 6)) * 8\n" +
            "(5 * (3 + 3 + 8 + 2) * 9) + 4 + 5 * 6\n" +
            "(8 + 7 * 7) * (7 * (5 * 9 + 2 + 3 * 6)) + 8 + 5 * 8 * 7\n" +
            "4 + (8 * (9 + 4 + 4 + 3 + 8 * 9)) + 5 * 5 * 5\n" +
            "(5 + (5 * 5 * 7) + 7 * (2 + 4 * 2 + 5)) + 8 + 5 + 2 * 4 * (3 * (4 * 7 * 6 * 6 * 8 + 2) * 7)\n" +
            "(9 * 4 + 6 * 8 * 2 * 8) * 2 + 9\n" +
            "(6 * (7 * 3 + 2 * 5) * 9 * 4 + 4 * (9 + 4 + 4)) + 4 * 8\n" +
            "7 * 5 * (9 + (4 * 4 * 4 * 6) + 4 * 6) * (6 * 8 * 2) + 9 * 6\n" +
            "2 * (2 * 3) * 9 + 2 * 9\n" +
            "((3 * 2 + 7) + (5 * 9 + 2 * 3 * 4 * 9) * 3 + (8 * 4 * 5 + 6 + 9)) * 6 * 3 + 6\n" +
            "(3 + 8 + 9 * 2 + 5) + 2 * 2 + 7\n" +
            "9 * (9 * 3 + 7) + 7 * 5 + 5\n" +
            "8 * 5 * (9 + (2 + 8 + 3 * 4 * 3 * 8) * 3 * (5 + 6) + 8) * (6 + 3 * 7 * 2 * 4 * (6 + 6 * 3 * 9))\n" +
            "8 + 2 * 8 * (5 + (5 + 7 * 4 + 6 * 9) * 7 * 2 + 4) * 5\n" +
            "(9 * (8 * 7) + 3 * (9 + 5 * 7 + 4 + 9) + 8) * (8 + 8) * 4\n" +
            "(7 + 9 * 4 + (2 * 4) + 7 * 8) * 4 + ((4 + 8 * 7 + 4 * 4) + 2)\n" +
            "(5 + 4) * (2 + 2)\n" +
            "5 + 4\n" +
            "7 * 8 * (7 * 7 + 4 * 4 * (6 * 6 * 3)) * 7 * (6 * 5 + 7 * 5)\n" +
            "5 * (6 * 8 + 5 * 6 * 6 + 2) + 6 * 7\n" +
            "4 + 3 + (7 * (6 * 2 + 3) * 8) * 3\n" +
            "2 + 2 + (3 * 7 * 3 + 3 + 6) * 3 + 5\n" +
            "5 * ((3 * 4 + 4 + 8 * 2 + 7) + (9 * 7) + 4)\n" +
            "8 * 2 * (5 + 7) + (8 * 7 * 6 * 2 * 4) * 7 * 5\n" +
            "((6 + 9 * 4 * 3 * 2) * (5 * 4 * 4 + 6 + 6) * 7 * 7) * 7 * 6 + 2\n" +
            "4 + (5 * (9 * 5 * 6) + 6 + 4 * 9 * 9) + 6 * 4 * 3\n" +
            "5 + (7 * (5 + 3 * 6 + 7 + 5 + 6) * 8 + 3) + 3 + 7 * 5\n" +
            "4 + ((8 * 6 * 9 + 6) * 4)\n" +
            "(2 * (6 * 3 + 5) + 2 * (5 * 9 + 4 + 5 * 6) + 4) + 7 + (9 + 4 + 5 * 2)\n" +
            "(8 * (8 * 5 * 6 + 4 * 9 + 5)) * 3 + 5 + 7 * ((3 + 7 + 2 + 3 + 7 * 5) * 9 + 7 + 6)\n" +
            "8 + 2 + (9 + 6 * 9 * 5) + 8\n" +
            "(3 + 7 * 3 * 9 + 6 * 3) * (4 * 2 * 5 * 2 + 5) + 6 + 5\n" +
            "5 * (9 + 8 + 4) * 6 * 4 * 5 + 7\n" +
            "5 + 8 + 7 * (3 + (3 * 8 * 9 * 9) + 6 * (2 + 3) * (9 + 3)) + 8 + 7\n" +
            "((8 * 6 * 5 + 6) + (7 + 9) * 3 * 7 + 4 * 5) * 9 * 8 * 3\n" +
            "5 * 3 + (7 * 3 * (4 * 3) * (5 * 3 + 5 + 3) * 9 + 3) * 4\n" +
            "((7 * 5 + 9 + 4 * 7) * 5 + 7 * (3 * 2 + 5 * 3)) * (4 * 6)\n" +
            "(8 + 3 + (8 + 8) * 6 + 5) + 9 + 6 * 3 * 9\n" +
            "3 * (2 + 6 + 9 * 6 * (3 + 8)) * 4 + 3 + 9 + 6\n" +
            "4 + 6 + ((2 * 7 * 5 * 4 * 9) + 3 + 5) + (8 * (6 + 4) + 5 + 8 + 3 + 5) * 2\n" +
            "8 + 2 + (2 + 8) + 6 * 2 * 8\n" +
            "3 * (6 + 9 + 2 * 8 + (4 + 8 * 8 + 4)) * 5\n" +
            "7 * ((3 + 8 * 7) + 3 + 2 + 2) * 7 * 5 * 4\n" +
            "(8 + 6 + 3 + 7 * 2) * (6 * 5)\n" +
            "9 * (7 * 6) * 3 * 3 * ((6 + 7 * 6 * 6 * 2 + 9) * (8 + 5 + 3) * 8 + 8 + 3 * 5) * 8\n" +
            "2 * (9 + 5 + 3) + 3 + (2 * 3 * 2 * 5) + 5 * 9\n" +
            "3 + (9 + (9 * 8 * 8)) * 3\n" +
            "5 + 3 * 4 + ((4 + 2 + 2 * 9 + 6 + 2) * 6 * 5 + (6 + 3 + 4 + 4 * 2)) * (5 * 7 + 8) * 4\n" +
            "7 * ((8 * 3 + 3 + 9 + 9 * 8) + 3 * 5 + (2 * 3) * 6) + ((3 + 5 + 8 * 8 * 9) + 4 * 7 + 3 * 9 + 6) * 3 * 3 + (2 + 3 + (9 * 7 + 6) * 5 * 9 * 4)\n" +
            "(9 + 2 + 7) + (8 + 8 * 3 * 2 * (5 + 4 * 8 + 2 + 4 * 6) + (8 + 5)) * 9 * ((3 + 8 * 4) + 7 * 7 * (5 * 3 * 3 * 7))\n" +
            "((6 + 8) * 8 + 5 * (7 * 4 * 8 + 9 * 5) + 2 + 7) * 5 + 9 + (4 + 7 + (4 + 9 * 4 + 6 + 6) * (6 * 2 * 2 * 9 * 8)) + 5\n" +
            "4 + (3 + 6 + 9 * 8) * 3 * 5 * 6\n" +
            "(4 * 3 * (3 * 7 + 6 + 5 + 9) + 8) + 8 * 2 + 5\n" +
            "7 * 7 * 8 + ((8 + 3 + 2) + (2 + 4 * 4 * 3 * 5 * 2) + 8) * (6 * 5 + 7 + (4 + 7 + 2) + 4) * 3\n" +
            "5 * 8 * 4 * 8 * ((8 + 5 + 6 + 9 + 9 + 7) + 5 * 2)\n" +
            "5 * 7 + (8 + 8 * 5 + 4 + 6 * 8) * ((4 * 9 * 7 + 5) * 7 + 4 * (3 * 4 * 5 + 9) * (6 + 8 + 5 * 5 * 2 + 6))\n" +
            "9 * 9\n" +
            "4 * 9 + (6 * 2 + 3) * 5 * 9 + 7\n" +
            "6 * 7 + 8 + 5 + (5 + 2)\n" +
            "2 + (3 + 3) + 3\n" +
            "(8 * 6 * (4 + 7 * 7 * 7 * 3 + 6)) + (3 * 2 + 7) * 5 * ((4 * 8 + 2) * 4 * (2 * 9 * 4 + 2) + 7 * 6 + (7 + 2 * 8 + 2)) + (2 + (9 + 7 * 9 + 4 + 5)) + 3\n" +
            "(8 + 8 * 2) + 9 + 5 * 5 + 6\n" +
            "(5 * 6 * 4 + 7) * 9 * 8 * (5 * 7 * 4 + (7 + 9)) + 4 * (2 * 6 + 4 * 6)\n" +
            "4 + (8 * 5 * 6 + 9 + 9 * 5) * 9 + 2 * 4 + (5 * 4 + 8 + 9 * 2)\n" +
            "4 * 5 * 6 * (9 * 3 * 4 + (2 + 2) + 7 + 7) * 4\n" +
            "(2 * (6 * 6 * 9 * 3 + 3)) + (4 * 4) * 5\n" +
            "(5 + (3 * 8 * 5 + 2) + 9 + 9) + (3 + 7 + 4 * (2 + 6 + 5 * 9 * 6) + 8) * 9\n" +
            "5 + (5 + 4 + 5 + 9 + (9 + 7 * 5)) + (7 + 5 * 8 * 3) + (5 + (4 * 3 + 7 + 9 * 7 * 3) * 6 * 4 * 6) + (3 + (6 + 6) + (7 * 2) * (3 * 8 + 2))\n" +
            "((4 + 4 + 3 + 5 * 4 * 4) * (8 + 2) + (5 + 3 + 4 * 9) + (8 * 9 * 3 * 9 + 9 * 2) + 3 + 4) * 4 * 7 * 7\n" +
            "5 * 8 + 6 + (7 * 7)\n" +
            "((7 + 2 + 6) + 3) + 3 * ((9 + 7 + 2 + 5 + 7 * 8) + 5 * 8 + (3 + 9)) * (5 * (4 + 2 * 4) * 4 * 5)\n" +
            "8 + (8 * 8 + (8 * 3 + 6 + 3 + 9) + 5) * 3 + 3 * 5\n" +
            "(5 + 2) * (9 * 9 * (6 * 6)) + 3 * 3 + 4 * 5\n" +
            "6 * 7 + 6 * 7\n" +
            "(4 * (7 + 2 + 5 * 7 + 2 * 7)) * 4 * (4 + 7)\n" +
            "3 * 7 * (7 + 9 + 6 + 9) + 6 + 6 * 2\n" +
            "3 + 4 + 9 * (7 + (4 * 4 + 2 * 8) * 9 * (4 * 8 * 2 + 7 * 3) + 8) * 3\n" +
            "6 * 6 + (4 + 3) * (2 * 2 * 9 + 9 * 4 * 4) + (4 + 7 * 2)\n" +
            "4 * (6 + 7 * (7 + 2) + (2 * 5 * 2) * 5 + 3) + 4 + 4 + 7\n" +
            "((7 + 6 * 7 * 3 * 6) * 4 + (2 * 4 * 8 * 2 + 7 + 3)) + 4\n" +
            "5 + ((2 * 9 * 7) * 6 * 6 + 8 * (8 * 8) * 3)\n" +
            "((5 * 6) * (2 * 2)) + (6 * 4) + 7\n" +
            "(7 * 4 * 2 * 6) + 7 * (8 * 7 + 7 * (2 * 3 * 2 + 2) * 4) * 4\n" +
            "3 + 7 + 5 + 9 * ((8 * 7) + 7 * 9) + 8\n" +
            "((8 + 9 + 6 + 4 + 3 + 9) + (9 + 2 + 5 + 6 * 7) * 3 * 9 * 4) + 4 * 4 * 7\n" +
            "(7 * 6 * 2) * (5 + 2 + (9 * 3 + 3 * 5 + 7) + 6) + 4 + (9 * 9 * 7 + 2) * 2 * 5\n" +
            "(3 * 9) * 8 * 4 * 4 * 5\n" +
            "2 * 2 + 2 * 8 * (8 + (8 + 2 + 8) + 4 + (8 + 8 * 5 * 4 * 2))\n" +
            "4 + ((3 + 9 * 6 + 9 * 6 * 5) + 9 * 9 * 8 * 8) + 5 + 8\n" +
            "(7 * 8 * 7 * 6 * 6 + 4) * (2 * (5 + 4 * 4 + 8 + 8 * 7))\n" +
            "5 * (7 + 8 * 5 * 4) + ((6 + 7 + 5 + 4) * 5 * 3 + 3) * 6 * 3\n" +
            "2 * (7 + 4 + 2 * 9 * 3 + 4) * 6 * 9 * 3\n" +
            "(3 + (4 * 2 * 4 * 2 * 4 * 5) * (5 + 8)) * 3 + 9 * 4 * 3 * (3 * (6 * 9 * 6 + 8) * (9 * 4 + 8 + 3) + 3 + (6 + 3 + 8 + 4) + (3 + 4 * 2 * 4))\n" +
            "5 + (9 + 5) + 6 * (3 * 5 * 5 * (4 + 8) * 3) + 5 + 9\n" +
            "6 + 2 * 5 + 3 * (2 + 5 * 7 * 6 * (8 * 8 * 2 * 7 * 8) + 7)\n" +
            "7 * 3 * 7 * 5\n" +
            "7 + ((9 * 9) * (8 + 4 + 5 * 5) * 9) + 2 * 7 + (5 + 4 + 6 * 5 + 4 + 6)\n" +
            "((6 + 7 + 8 + 9 + 5) + 4 + (9 + 9 + 2) * 9 + 7) + 7 + 8 * 3\n" +
            "2 + (2 * 6 * (7 * 4) + 6) + 6 + 6 * 8\n" +
            "(4 * 4 * 8) + 3 * 3\n" +
            "((4 + 5 * 4 + 2 + 6 + 5) + 6) * 9 + 9 + 2\n" +
            "6 + 3 + ((3 * 5 * 3 + 6 + 9 + 5) + (9 + 9 + 8) * (8 * 2 + 5 + 5) + 5 + 9 + (7 + 3 + 5 * 7 * 7 * 4)) + 2\n" +
            "9 * (9 + (4 + 8) * 4 * 4 + 9) + 8\n" +
            "(9 * 8 * (3 + 9 * 4 + 7 * 4) * (3 + 5 * 4 + 6 + 3)) * 9 * (3 + 3) + 5\n" +
            "6 * ((8 + 5 * 2 * 9 * 5 + 7) * 9 * 4) + 9\n" +
            "5 + (7 + (5 * 2 + 3 + 8 * 3) + 2 + 6)\n" +
            "3 + 9 * 5 + ((8 * 4 * 2) * 5 * 6 + 5 + (4 * 5 + 7 * 9 + 8 + 3)) + 4 + (5 * 7)\n" +
            "6 * 2 * 3 * 7 * 3\n" +
            "(2 * (4 * 3 * 4 + 8) + (7 * 6 * 9 * 3) + 2 * 4 + 2) + 5 * 9 * (4 + (9 + 5)) + 9 * 2\n" +
            "(6 * 8 + 9 + 7 + 3 * 7) + 8 * 3 * 4 + (4 * 8 * 4 + 6 * 3) * 7\n" +
            "5 + (6 + (9 + 2 * 2 * 9 + 5) * 7 * 2 + 9)\n" +
            "7 + 9 * ((6 + 2 * 7 + 4) + 6 + 5 * (5 * 3)) * ((9 + 8) + 6 + 8 * 4) * (3 + 4 * 3 + 8)\n" +
            "((2 + 5 + 4 + 7 * 4 * 6) * 5 + 9) + 8 + (6 + 9 + 8) + 3\n" +
            "3 * 2 + 5 + ((4 + 7 + 4 * 6 * 9) + 8) * 9 + 7\n" +
            "3 * (6 * (4 + 9) + 7 * 5)\n" +
            "(6 + 6 * 9 * 7) * 3 * 8\n" +
            "4 + 8 * ((5 * 9) * 6) * 6\n" +
            "3 * 6 * 4 + (3 + (4 * 2) + 6 + 4 + 9)\n" +
            "4 + 7 + (3 * (6 * 3) * 7)\n" +
            "(7 + 5 + (5 + 9 * 7) * 7 * 6 * 2) * 7 + (4 * (6 * 9 + 4 * 4) + 5 + (4 + 6 * 8 * 5 + 3) * 3)\n" +
            "8 + 9 * (8 + 3 + 3 + 6 + (3 + 2 + 6 * 5 * 8 + 7)) * 2\n" +
            "4 + 7 * (6 * 3 + (8 * 9) + (9 + 4 + 3)) + (4 * 5) * 6 * 6\n" +
            "((6 * 5) + (2 + 6 * 7 + 6 * 6 + 3) * 4) * 6 + 7 + 5\n" +
            "6 + 9 * 6 + 3 * 5 * 3\n" +
            "(3 * 3) + 8 + 5\n" +
            "(3 * 9 + 4 * 4 * 9) + (4 + 4 + 8 * 7) * 3 + 2 * 7 + (7 + 5 + 7 + 6)\n" +
            "9 + ((6 * 6 + 4 + 2 * 3) + 3 * 7 * (9 + 7 + 9 * 6 + 3) + 9 + 2) * 8 + 9\n" +
            "6 + 4 * 9 + 6\n" +
            "6 * 8 + 7 * 9 * (6 + 2 * 6 * 8 * 2 + 2) + 3\n" +
            "3 * ((3 * 7) + 8 * (2 + 8 + 8 * 8 * 5 + 7)) + ((9 * 8 * 5 + 6 + 3 + 3) * (2 * 3 * 8) + 3 * 3 * 5 + 7) + 3 + 6 + 6\n" +
            "(2 * (9 * 2 + 4 + 9 + 5 + 5)) + 6\n" +
            "2 * (7 + 3) + 7 + ((9 * 6 + 3 * 3) * 7 * (5 * 2)) + 3\n" +
            "3 * ((4 * 2 + 5 + 5 + 9) + 9 + (4 * 8 + 8 + 7 + 3 + 4) * 3 * 6) * 7\n" +
            "(8 * 3) * 6 + 6 + 9 + ((6 * 9) + 3 + (7 + 7 + 8 * 6 + 6 + 4) * 4)\n" +
            "6 + 6 + (6 + (7 * 7 * 7 * 6) * (2 * 7 + 4 + 6 + 5 + 6) * 9 * 5 + (7 * 8 * 3 * 9 + 5))\n" +
            "7 * (5 * 2 * 3 + 5) * 4\n" +
            "6 + (4 + 8 + 3 + 4) + (4 * 3 * 4 + (6 * 3) + 5 * 7)\n" +
            "(3 * 5) + 8 + ((2 * 6 + 8 * 5 + 5 * 4) + (6 + 9 + 8) + 9) * (6 * (3 + 9) * 8)\n" +
            "4 + 8 + 3 * (9 + (9 + 8 + 4 * 8) + 8)\n" +
            "(9 * (8 + 4 * 4 * 8) * 9 + (7 + 2 * 8 * 7 * 9 * 8) + 4) + 2 + 6 * 2 + 8 + 5\n" +
            "7 * ((9 * 9 + 7 + 4) + 2 * (7 + 4 * 6) * 7 + 4) * 7 * 3 + 7 * 9\n" +
            "(9 + 5) * (7 + 5 + 9 * 9) + 3 + 7\n" +
            "2 + ((5 * 5 + 4 * 6 + 8) + (9 + 5 + 7 * 6 * 7 + 5) * 2 * 2 + 6) * 7 + 7\n" +
            "(6 + 5 + 8 * 3) + ((7 + 4 + 6 + 6 + 8) + (7 + 3 + 5)) * 2\n" +
            "8 * 7 + 8 * 9 + (7 + (9 * 9 * 2 * 9 * 4 * 7) * 2 + 8)\n" +
            "9 + ((7 + 5 + 2 * 3) * (8 * 4 + 9 + 6) * 6 + (3 + 2 + 4 + 5) * 4) * 4 + (9 * 7 + 9 * 2 * 2)\n" +
            "2 * ((4 + 3 * 7) * 8 + 6 + 2 * 9 + 3) + 6 + 6 + 2 * 7\n" +
            "5 + 5 + ((5 * 8 * 9 + 9 * 3) * 9)\n" +
            "6 + ((7 + 6 * 9 + 7) + 4) * 7 + 3\n" +
            "7 + 8 * 7 * 3 * ((8 * 9 + 5) + (2 + 6 + 6) * (3 * 4 * 9) * 8 + 6 + 9) + 3\n" +
            "2 + 2 + ((8 * 8 * 3) * (5 * 7 * 7 * 5 * 4)) * 7\n" +
            "(4 * 4 + 3) + 6\n" +
            "5 * 7 + (7 + 4) * ((7 + 3) + 5 * 2 * 4 + (2 + 5))\n" +
            "(5 + 3 + 2) * 6 + 2 + 6\n" +
            "5 * (8 * (7 + 9 + 7 * 3 * 4 * 9) + (5 + 3 + 6 + 4 + 2 * 2) + 7 * 9 + 2) * (3 * 6 * 7 + 6 * 4)\n" +
            "4 + ((6 + 2 * 6 * 2 * 7) * (6 * 3 + 2 + 5) * 7)\n" +
            "6 + 9 + (8 + 4)\n" +
            "2 + 2 + 5 + 9 * 4 * 7\n" +
            "((8 + 4 * 6 + 8) + 3 * 9 + 2) + 4 + 3\n" +
            "7 + 2 + 6 + ((7 + 3 * 3 + 6 + 3) * 9 + 3 * 7 * 5) * 9\n" +
            "6 + (8 * 2 + 4)\n" +
            "5 + (7 + 9 * 6) + 4 + (2 * 9 * 3 * (6 + 4)) * 9 + 8\n" +
            "7 + 8 + (7 * (6 + 3 * 7 * 3 + 8 + 3)) * 3 * ((6 + 8 + 6 + 6 * 5) * (4 + 8 + 5 * 8) * 9 + 9 * 6) * (2 * 3 + 2 * 8 * (7 + 6) + 5)\n" +
            "(5 + 9 * (9 * 7 + 6) + (5 + 5 * 6 + 7 * 3 + 7) * (5 * 7 * 6 * 9 + 4 + 7)) * 2 * 5 + (8 * 2 + 4 + (5 * 9 + 4 + 3 * 2)) + 8 + 4\n" +
            "3 * 3 + 7 * 3 * (9 + (3 * 2 * 7 + 3 + 8) * 5 + 8 * 2 + 2) * 2\n" +
            "3 + 8 + 8 + 2 + 2 * (3 + (3 + 8 + 3))\n" +
            "(3 + 5 * 6 + 3 * 4 * 6) * ((5 + 4 * 9 + 3 + 7) * 3 * 4 + 4) + 6 * (9 + 8 + 6 * (7 * 4 * 5 * 2) + (7 + 7 * 5) * (3 * 6)) + (6 * 9 * 4 * 9 + 7)\n" +
            "5 + 9 + ((8 + 8) + 9 * 6 + 7 * (4 + 9)) + 4\n" +
            "7 * (6 * 5 + 4 * 8) * 2 * 5\n" +
            "7 + 4 * ((7 * 4 * 2 + 9 * 4) + 8 * 2 + 4 + 8 * 5) * (2 + (3 * 2 * 3) * (7 + 3 * 9 + 5 * 2) * (5 * 5 * 6 * 6))\n" +
            "6 + ((7 * 3 * 3 + 7 + 7 * 3) * 2 + 5 * 3) + 2 + 3 + 8\n" +
            "(7 * (6 + 7) + 6 * 2 * (7 * 7 * 3 * 4)) * 4 * 5\n" +
            "6 + 5 * (7 + 8 * 8)\n" +
            "5 * 2 + ((8 + 4 * 8 + 6 + 9 * 3) * 9 * (3 + 7 * 5 + 3 * 8 + 6) * 2 * 8 + 4) + 3 * 8 + 7\n" +
            "8 + 9 * (2 * 5) + 8 + (4 * 7) * (8 + 5 * 7 * (9 * 2 + 7 + 4 + 6 * 7) + 3)\n" +
            "5 * 9 * (2 * 8 * 5 + 5 * 7 * 7) + 9 + 6\n" +
            "2 * 5\n" +
            "5 + (3 * 2 * (4 * 4 * 8 + 6 + 2)) * (3 * (9 + 7 + 3 * 7 + 3) + 8 + (5 + 9 * 4 + 4) * 8 * 4) + 9 + 6 + 2\n" +
            "9 * 3 * 3 * (4 * (5 * 8 + 5) + 7 + 3 * (6 * 8 * 9 * 2 * 4 + 4)) * 7\n" +
            "(4 + 9) * 8 * 2 * 5\n" +
            "9 * 8 + (5 + 3 + 9 + 9 + 4)\n" +
            "4 + (8 * (7 + 6 + 3) * 4 + (2 + 6 * 5 * 2 * 2)) * (6 + (4 * 5) * 5) + 5 + 4\n" +
            "6 + 5 + (6 + (9 + 6 * 2) * 7 * 8 * 6 * 7)\n" +
            "2 * 7 * 8\n" +
            "9 + 2\n" +
            "8 * (7 + 3 + 5 * (3 * 6) * (9 + 6 * 2))\n" +
            "(3 + 9 * 5 * 7 * 7 * 3) + (3 * 8 * (2 + 8 * 4 + 4 + 7) * (2 + 8 * 6) + 5) * (4 * 3 * 5) * (8 * 8 * 3 + 7)\n" +
            "(9 + 6) * 6 * 7 * 5 + (6 * (5 * 2 + 7 * 6) + 5 * 2 * 8) + 6\n" +
            "3 * 6 + (9 + (7 * 3 + 8 + 3)) + 8 * (8 * 9 + 7)\n" +
            "(5 + (9 * 7 * 6) * (4 + 4 + 8 + 4 * 4 * 2)) * 5\n" +
            "9 + (8 * (7 * 6 + 2 * 4) + 7) * 4 + 8 + 8 * (9 * (6 + 4 + 7 * 9 + 9 * 7) + (7 + 4 * 7 + 6 * 4) * 7 * 9)\n" +
            "6 * 2 + (4 + 2 * 7 + 6 * 2 * (3 + 8 + 6 + 8)) * 7\n" +
            "7 + 7\n" +
            "8 * 6 * 9 * 6 + 7 + 4\n" +
            "6 + 3 * (7 + 3 * 5 * (8 * 2 + 5))\n" +
            "3 * (3 * 9 * 8 + 5 * 5 + (6 + 8))\n" +
            "(5 * (3 * 4 * 9 * 6 + 6)) * 4 + 3 * 8\n" +
            "(3 + 6 + (2 * 8 * 8 + 4) * 7 * 5 + 3) * 9 * 5 * 5 * 8\n" +
            "(5 + 9 * (6 * 2 * 2 + 7 + 2 + 8)) * 5 + 6 + 7\n" +
            "7 * 4 + (6 + 8 + (4 * 8 + 9 + 9 + 5 + 2) + 8 + 6 + (4 * 3 + 4 * 2 + 4 + 5)) + 2 + 6\n" +
            "4 * (5 + (8 + 4 * 5) * 8) + 2 * 7 * 7\n" +
            "2 * 7 + ((3 * 4 + 9 * 4 + 3) * (6 * 5 + 2 + 2 + 6 * 3) * (3 * 8 * 4 + 8 + 6 + 9) * 4 * 4 * 6) + 8 + (2 * (2 * 9 + 2 * 3) * 7 + 5) + 3\n" +
            "(8 * 7) * 8 * (6 + 6 + 8 * 2 + 3) * 9 * 9 + (9 + 9 + 2 + 5)\n" +
            "3 * ((4 + 9 * 8 + 4) + 8 + 3 * 4) * 9 + 4 * 9 + 4\n" +
            "7 * 6 + 6 * 6 * 9\n" +
            "3 + 8 * (6 * (6 + 2 + 9) * 6 + 2 + 6) + 4\n" +
            "((4 + 8 * 3 * 3) + (4 + 5) * (2 * 8 * 9 + 6) * (7 * 6 + 5 * 4 + 2)) + 5 * (7 * 5) * 6\n" +
            "7 + 7 * 2 * (7 * 7 * 2) * 7 + 7\n" +
            "9 * (8 * 8 * 5 + 3) + 2 + (8 + (6 * 2) + (5 + 3 + 9 * 8 + 4)) * (4 + (7 * 7 + 3 * 7 + 4 * 4) + 9)\n" +
            "2 + 8 * 9 + 8 + (6 * 8 + (8 + 9 * 7 * 3 * 3 * 9) + 6 * 9 + 2)\n" +
            "6 + (4 + 5 * 5 + (6 + 6 * 5 + 9 * 6 * 3) * 3) * 5 + 8 + 9 * 2\n" +
            "2 * 7 * (5 * 2 + 6 * 3 + 4 * 9) + 6\n" +
            "(3 + 7 * 3 * 6) + 3 + 6 + (5 * 6 * 7 * (9 * 4 + 7 + 9 * 9) * (6 + 9 * 2 * 5) * (4 + 7 + 2 + 4 + 6 + 8)) + 3\n" +
            "6 * 5 + ((9 * 2 * 8 + 9 + 8 * 2) * 9 * 3 + 4 + 8 * 3) * 7\n" +
            "9 * 4 * (9 + 4 + 7 * 2 * 2) * 6 * 5 * 5\n" +
            "5 + (8 + 8 + 2 * (7 * 6 * 4) * 7 * 9) * 4 + (9 * 9) * 9 + 4\n" +
            "5 + 8 * 9 * (7 + 8 + 2 + 5) + 5\n" +
            "5 * 8 * ((7 * 2 + 4) * 2 + 3 + 9)\n" +
            "8 + (3 + 8 * (3 * 2 + 9 + 3 * 6 + 2) + 5 * 5) * 2\n" +
            "(8 + 3 * 8) + (8 * 8 + 5 + 7 * 7 + 3) + 4 * 3 * 9 + 9\n" +
            "5 * (5 + 6 * 6) + (3 * 7 * 6 + 9 * 4 + 9) + 9\n" +
            "2 + 5 + 6 * (9 * 2) + 2 + 4\n" +
            "5 + 3 + (7 + 9 * 3 + (4 * 5)) * 9\n" +
            "(8 + 5 + 9 + 7) * 6\n";
}
