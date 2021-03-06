package com.advco;

import java.util.*;

public class Day5 {
    public static void main(String[] args) {
        partTwo();
    }

    public static void partOne(){
        int highestSeatId = -1;
        List<String> lines = Arrays.asList(PUZZLE_INPUT.split("\n"));
        System.out.println("Number of lines: " + lines.size());

        for(String line : lines){
            int rowIdMin = 0;
            int rowIdMax = 127;
            int columnIdMin = 0;
            int columnIdMax = 7;

            for(int i = 0; i < line.length(); i++){
                if(line.charAt(i) == 'F'){
                    rowIdMax = rowIdMax - ((rowIdMax - rowIdMin) / 2) - 1;
                } else if(line.charAt(i) == 'B'){
                    rowIdMin = rowIdMin + ((rowIdMax - rowIdMin) / 2) + 1;
                } else if(line.charAt(i) == 'L'){
                    columnIdMax = columnIdMax - ((columnIdMax - columnIdMin) / 2) - 1;
                } else if(line.charAt(i) == 'R'){
                    columnIdMin = columnIdMin + ((columnIdMax - columnIdMin) / 2) + 1;
                }

                System.out.println("Row Id Min: " + rowIdMin);
                System.out.println("Row Id Max: " + rowIdMax);
                System.out.println("Column Id Min: " + columnIdMin);
                System.out.println("Column Id Max: " + columnIdMax);
                System.out.println("---");

                int seatId = rowIdMin * 8 + columnIdMin;

                if(seatId > highestSeatId){
                    highestSeatId = seatId;
                }
            }
        }
        System.out.println("highestSeatId: " + highestSeatId);
    }

    public static void partTwo(){
        List<String> lines = Arrays.asList(PUZZLE_INPUT.split("\n"));
        System.out.println("Number of lines: " + lines.size());

        TreeSet<Integer> treeSet = new TreeSet<>();

        for(String line : lines){
            int rowIdMin = 0;
            int rowIdMax = 127;
            int columnIdMin = 0;
            int columnIdMax = 7;

            for(int i = 0; i < line.length(); i++){
                if(line.charAt(i) == 'F'){
                    rowIdMax = rowIdMax - ((rowIdMax - rowIdMin) / 2) - 1;
                } else if(line.charAt(i) == 'B'){
                    rowIdMin = rowIdMin + ((rowIdMax - rowIdMin) / 2) + 1;
                } else if(line.charAt(i) == 'L'){
                    columnIdMax = columnIdMax - ((columnIdMax - columnIdMin) / 2) - 1;
                } else if(line.charAt(i) == 'R'){
                    columnIdMin = columnIdMin + ((columnIdMax - columnIdMin) / 2) + 1;
                }

                System.out.println("Row Id Min: " + rowIdMin);
                System.out.println("Row Id Max: " + rowIdMax);
                System.out.println("Column Id Min: " + columnIdMin);
                System.out.println("Column Id Max: " + columnIdMax);
                System.out.println("---");
            }
            int seatId = rowIdMin * 8 + columnIdMin;
            treeSet.add(seatId);

        }

        int missingNumber = -1;
        Iterator<Integer> x = treeSet.iterator();
        int previous = x.next();
        int current = x.next();
        while(x.hasNext()){
            int future = x.next();
            if(previous+1 != current || future-1 != current){
                if(previous+2 == current){
                    //Previous+1 is missing
                    missingNumber = previous+1;
                    break;
                } else if(future-2 == current){
                    missingNumber = future-1;
                    break;
                }
            }

            previous = current;
            current = future;
        }
        System.out.println("Treeset: " + treeSet.toString());
        System.out.println("missingNumber: " + missingNumber);


    }

    public static final String PUZZLE_TEST = "FBFBBFFRLR";
    public static final String PUZZLE_INPUT = "FBBBBBBLRR\n" +
            "FFFBFFFLLR\n" +
            "FFBBBBBRRL\n" +
            "BFFBFFBRLL\n" +
            "BFBFBBFLLR\n" +
            "FBFBBFBLLL\n" +
            "FBFBBBFRRR\n" +
            "FBFFBBBLRR\n" +
            "FBFBBBBRLL\n" +
            "FFFBBFBLLR\n" +
            "FBFBFFFRRL\n" +
            "FFBBBFFLLR\n" +
            "BFFFFFBRRR\n" +
            "FBFFFFFLLR\n" +
            "FFFBBFFRRL\n" +
            "FBBBBBBRRR\n" +
            "FBBFFBBRLR\n" +
            "FBFBFBBRLL\n" +
            "BFFBBFBLLR\n" +
            "FBFFFFBLLR\n" +
            "FFFBFFBRLL\n" +
            "BFBBBBBLRL\n" +
            "BFFBFBFLRR\n" +
            "FFBBFFBRLR\n" +
            "BFFFFBFLRR\n" +
            "BFFFFBBLRR\n" +
            "FBBFFFBLLL\n" +
            "FFBFBFBLRR\n" +
            "FBFBFBBRRL\n" +
            "FFBFBBBLRR\n" +
            "BFFBFFFRRL\n" +
            "BFBFBFBRRL\n" +
            "BFFBBBFRLR\n" +
            "BFBBFBFLLR\n" +
            "FBFBBBFLRL\n" +
            "FBFFBBBRLL\n" +
            "BFFFFBBLRL\n" +
            "BFBBBBBRRR\n" +
            "BFFBFFFLLL\n" +
            "BBFFFBFRRL\n" +
            "BFFBFBBRLL\n" +
            "FFBBBBFLRL\n" +
            "BFFFFFFRLL\n" +
            "BFFFBFBLRL\n" +
            "FFBBFBBLRL\n" +
            "BFBBBBBRLL\n" +
            "FBFFBBBLLL\n" +
            "BFBBFBFLLL\n" +
            "BFFBBBBRRL\n" +
            "FFFBFFBRLR\n" +
            "BFFBFFFRRR\n" +
            "FFBBBBFLLR\n" +
            "FBFBBBFRRL\n" +
            "BFBBFBBRLR\n" +
            "FBFBFFFRRR\n" +
            "BBFFFFBRLL\n" +
            "FBFFBFFRLL\n" +
            "BFBFBBBRRL\n" +
            "FFFBFBBLLL\n" +
            "FBBFBBFLLL\n" +
            "FFBBBBFRRR\n" +
            "FFFFBBBRRR\n" +
            "BFBFFBBRRL\n" +
            "FBBFFFFLRR\n" +
            "BFFBBFBRLL\n" +
            "FBFBBBBRLR\n" +
            "FFBBFBBRRR\n" +
            "FBBBBFFLRL\n" +
            "FFBFBFFRLL\n" +
            "BFFBBFFRRR\n" +
            "FBFBBBFLLR\n" +
            "BBFFBFFLRR\n" +
            "FBBFBFBLRR\n" +
            "FFFFBBBLRR\n" +
            "FBBFFBBLLR\n" +
            "FBBBFBFLRR\n" +
            "BFBBFFBLRL\n" +
            "FFBBFFFRLR\n" +
            "FBFFBFBRLR\n" +
            "FBFFFFFRLL\n" +
            "BFFFBFFRRL\n" +
            "BBFFFBBRRL\n" +
            "FFFBFFBLLL\n" +
            "FFBBFBBRLL\n" +
            "FBBFBBFRRR\n" +
            "FFFBFFFRLL\n" +
            "FFBFFFBRRL\n" +
            "BBFFFBFLRL\n" +
            "BFBFBBFLRL\n" +
            "FFFBFBFLLR\n" +
            "FFBBFFFRRR\n" +
            "BFFBBFBLRR\n" +
            "BFFBFFBLRL\n" +
            "FBBBFFBRRL\n" +
            "BFBBBFBRLR\n" +
            "BFFFBBBRRR\n" +
            "BBFFFBFRLR\n" +
            "FBFBFFBLLR\n" +
            "BFFFBFBLLL\n" +
            "FFBFFFBLRR\n" +
            "FFBBFFFRLL\n" +
            "BFFFBFFRRR\n" +
            "FBBFBFBRRR\n" +
            "BFBBBBFLLR\n" +
            "FBBFFBBRRL\n" +
            "BFFBFBBRRL\n" +
            "BFFBFBBLRL\n" +
            "BFFFBFBRRL\n" +
            "FFFBFBFRRL\n" +
            "FBFFFFBLRR\n" +
            "BFBBBFFRLL\n" +
            "FBBBFFFRLL\n" +
            "FBFFFFFLLL\n" +
            "BFBFFBBRRR\n" +
            "FBFBFFFLRL\n" +
            "FFFFBBFRLL\n" +
            "FBFFFBBLRR\n" +
            "BBFFFBFLLR\n" +
            "FBBBFFFLRL\n" +
            "FFBBFBFLRL\n" +
            "BFFFBBBLRR\n" +
            "BFFBBBFLRR\n" +
            "BBFFFFBRLR\n" +
            "FFBFBBFLRR\n" +
            "FFFBBBBRRL\n" +
            "BFBFFFFLLL\n" +
            "FBFBBFBLLR\n" +
            "FBBBBFFRRL\n" +
            "FFBFBBFRRL\n" +
            "FFFFBBFRRR\n" +
            "BFFFBBBRRL\n" +
            "BFBBFBFRRR\n" +
            "BBFFFBBLRR\n" +
            "BFBBBFBLRR\n" +
            "BFFBBBBLLL\n" +
            "BFBFFFBRLL\n" +
            "FBBBFBBRRL\n" +
            "FFBBBFBLLL\n" +
            "BFFBFBFLLL\n" +
            "BFBBFFBRRR\n" +
            "BBFFBFFLLR\n" +
            "BFBBBBFRRR\n" +
            "BFFFBBFLRR\n" +
            "BFFFFBBLLR\n" +
            "FFFBFFBLRL\n" +
            "BFBBFBFRRL\n" +
            "FFBBFBFLLL\n" +
            "BFFFFBFLLL\n" +
            "BFBFBBFRRL\n" +
            "BFFBFFFRLR\n" +
            "FBBBFFBRLL\n" +
            "BFBBBBFLLL\n" +
            "FBFFBFFRRR\n" +
            "BFFBFFBRRR\n" +
            "FBBBBBFRLL\n" +
            "FBBFBBBRRL\n" +
            "FBFBFFFRLR\n" +
            "FFFBFBBRLL\n" +
            "FFFBBFFRLR\n" +
            "BFBBFBBRRL\n" +
            "FFBBBBFLLL\n" +
            "BFFBFFBLLL\n" +
            "FBFBBFFRLL\n" +
            "FBBBFBFLLR\n" +
            "BBFFFFFRLL\n" +
            "FFBFBBBRRL\n" +
            "BFBFFFBRRR\n" +
            "FBBFFFFRLL\n" +
            "FBFBFBBLRL\n" +
            "FFBBFFFRRL\n" +
            "FBBBFFBLLL\n" +
            "FBBFFBBLRR\n" +
            "BFBFBBBRRR\n" +
            "BFBBBBBLRR\n" +
            "FBBFBFBRLR\n" +
            "FBBBBFBRRL\n" +
            "BFBFFBFRLL\n" +
            "FFBFBBBRLL\n" +
            "FFBFFFFRLL\n" +
            "FBFFBFFLLR\n" +
            "BFBBBBBLLR\n" +
            "FFBFFBFRLL\n" +
            "FBFFFBFLRL\n" +
            "BFFFFBFRRL\n" +
            "FFBBFBBLRR\n" +
            "BFBFFBFLRR\n" +
            "FBBBBFBLLL\n" +
            "BBFFFFFRRL\n" +
            "BFFBFFBRLR\n" +
            "BFFBBBBRRR\n" +
            "FFFBFFBLLR\n" +
            "BFFBFBBRRR\n" +
            "FFFBFFBLRR\n" +
            "FFBBBFBRLL\n" +
            "FFBFFFBRRR\n" +
            "FBFBBFBLRR\n" +
            "FBFFFFBLLL\n" +
            "FBFBFBBRRR\n" +
            "BFFBFFFLRL\n" +
            "BFBBBFFRLR\n" +
            "FBFBFBBLLR\n" +
            "FFFBBFBLRR\n" +
            "BFFBFFFLLR\n" +
            "BBFFFBBLLR\n" +
            "BFFFBBBLLR\n" +
            "BFBBBBBRLR\n" +
            "FBFFFBFRRR\n" +
            "BFBFBFFLRR\n" +
            "FBBBBBBRLL\n" +
            "FFFBFFFLRL\n" +
            "BBFFFBFRLL\n" +
            "BBFFBFFLRL\n" +
            "FBFFFBBLLL\n" +
            "BFFBBBFLLL\n" +
            "BFBBFBFRLR\n" +
            "BFBFBFBLRR\n" +
            "FBBFFBFRRR\n" +
            "FFFBFFBRRR\n" +
            "BFBBBBFLRL\n" +
            "FBBBFBBLLR\n" +
            "FFBBBBBRLR\n" +
            "FFFBBBFRLR\n" +
            "FBBFFBFLLL\n" +
            "FFFBFBBLRR\n" +
            "BFBFBBFLRR\n" +
            "BBFFFBBLLL\n" +
            "FBBBBBFLRR\n" +
            "BBFFFBFLLL\n" +
            "FBFBBFBRRR\n" +
            "FBFFBBFLLR\n" +
            "FFFBBFBRRR\n" +
            "BFBFBBFRLR\n" +
            "FBFBBFFLRL\n" +
            "FFBBBFBRRL\n" +
            "FFBFFBFRRR\n" +
            "BBFFFFFLLL\n" +
            "BFBBFFFRLR\n" +
            "FBFFFFBRRR\n" +
            "FBBFBFFRRL\n" +
            "FBBFBBFRLR\n" +
            "FFFBFFFLRR\n" +
            "FBBBFFFRRL\n" +
            "FFBBBBBLLR\n" +
            "BFBBBFBLLL\n" +
            "FBBBBBFLLL\n" +
            "FFBBFBFLRR\n" +
            "BBFFFFBRRL\n" +
            "FFBBBFFRRR\n" +
            "FBFBBFBRLL\n" +
            "FBBBBBBLRL\n" +
            "BFBFFFFRRL\n" +
            "FBBFBBBRLR\n" +
            "FBBFBBBLRR\n" +
            "FBFBFBFRRL\n" +
            "FBBFBFFLLL\n" +
            "FFFBFBFLRR\n" +
            "FBFBBFFRLR\n" +
            "BFFFBFFLLR\n" +
            "BFFBFBBRLR\n" +
            "FFBBBBBLRR\n" +
            "BFFFFFBLLR\n" +
            "BFFBBBBLLR\n" +
            "FBFBBFBLRL\n" +
            "BFFBFFFLRR\n" +
            "FFFBFBFRLR\n" +
            "BFFBBBBLRR\n" +
            "FFFBBFFLLR\n" +
            "FBFFFBBRRR\n" +
            "BFBBBFFRRL\n" +
            "FFBBBFBRRR\n" +
            "FBBBFBBLRL\n" +
            "BFBBFFFLLR\n" +
            "BFFFFFBRRL\n" +
            "BFFBBBBLRL\n" +
            "FBBBBBFLLR\n" +
            "FFFFBBFLRR\n" +
            "BFBBFBBLLL\n" +
            "FFFBBBFRLL\n" +
            "BFBFFFFRRR\n" +
            "BFFBBFBRRL\n" +
            "FBFFFBBRLL\n" +
            "BFFFFBBRRL\n" +
            "BFFBBFFLLL\n" +
            "FFFBFBFRRR\n" +
            "FBBBBBFLRL\n" +
            "FFBFFBBLRR\n" +
            "FFBBBFBLRR\n" +
            "FFBFFFBLLR\n" +
            "FBBFFBFRRL\n" +
            "BFFFFBBLLL\n" +
            "BFFFBFFRLR\n" +
            "FBBBFFFRRR\n" +
            "BFBFFBFRLR\n" +
            "FBFBFFBLLL\n" +
            "FBFBBBFRLL\n" +
            "FBBFBFBRRL\n" +
            "FFFBBFFRRR\n" +
            "FFFBFBFLRL\n" +
            "FBFBFFFRLL\n" +
            "BFFBBBFRRL\n" +
            "FBFFBFBLLR\n" +
            "BFFFFFFRRR\n" +
            "BFFBFBFRLR\n" +
            "FFBBFFBLRR\n" +
            "FBFFBFFLRR\n" +
            "FBBBFFBRRR\n" +
            "FFFBBBBLLL\n" +
            "FBBFBBBLLR\n" +
            "BFFFFBFLLR\n" +
            "BFFBBBBRLR\n" +
            "BFFBBBFRRR\n" +
            "FBBBFFFLLL\n" +
            "BFBFFFBRRL\n" +
            "FBFFBFBLRL\n" +
            "BFBBBFFLLL\n" +
            "FBBBFFBLRR\n" +
            "FBBFBFFRLL\n" +
            "BFBFFBBRLL\n" +
            "BFBFBFFLLR\n" +
            "FBFFFFFRLR\n" +
            "FFFBBBBRLR\n" +
            "FFFFBBBRLR\n" +
            "BFFFBFFLRL\n" +
            "FBBFFFBLLR\n" +
            "FFBFBBFLLR\n" +
            "FFBBFBBLLR\n" +
            "FBBBFBFLLL\n" +
            "FBFFBFBRLL\n" +
            "FBBFBFBLLR\n" +
            "FFBFBFFRLR\n" +
            "FFFFBBBRRL\n" +
            "BFBFFBFRRL\n" +
            "FBBBBFFRLL\n" +
            "BFFBFBFRRL\n" +
            "BFBBFFBRRL\n" +
            "BFFFBFBRLR\n" +
            "FFFBFFBRRL\n" +
            "FFBFFBFRLR\n" +
            "BFFFBBBRLL\n" +
            "BFBBBFBRLL\n" +
            "FBBFBFFLRR\n" +
            "FFBBFBFRLR\n" +
            "BFBFBFBRLR\n" +
            "FBFFFBBRLR\n" +
            "BFFFFFBLRL\n" +
            "FBBFFFFLLR\n" +
            "BFFBFFFRLL\n" +
            "FBFBBBBLRL\n" +
            "FBFBFBBLLL\n" +
            "FBBBBFBLRR\n" +
            "FBBFBFBLRL\n" +
            "BFFFFBFLRL\n" +
            "FBFFBFBLRR\n" +
            "FBFFBBFLLL\n" +
            "BBFFFFFLRL\n" +
            "BFBFFFFLRR\n" +
            "BFBFBFFRRR\n" +
            "BFBBFBBLRR\n" +
            "FFFBBBFLLR\n" +
            "BFFBBFFLLR\n" +
            "FBBFFFFRRL\n" +
            "FBFFBBBRLR\n" +
            "FBBFFBBLLL\n" +
            "FFFFBBBLLR\n" +
            "BFBFBFFLRL\n" +
            "FFBFFFFRRL\n" +
            "BFBFFFBRLR\n" +
            "BFBFFFBLRR\n" +
            "FBFFBBFRLR\n" +
            "FBBBBFFLRR\n" +
            "FBFBFBFLRR\n" +
            "FFBBBFFLRR\n" +
            "FFBFFFFRRR\n" +
            "BFFFBFFLLL\n" +
            "FBBBBBBLLR\n" +
            "BFBFFFBLLL\n" +
            "FBFBFBBLRR\n" +
            "BBFFBFFRLR\n" +
            "FBFFBFFRLR\n" +
            "FFBBFFBLLR\n" +
            "FBBBBFBRRR\n" +
            "FFFBBBFLLL\n" +
            "FFBFBBFRLR\n" +
            "FBBBFBFRRR\n" +
            "FBFBBFBRLR\n" +
            "FBFFFBFRRL\n" +
            "FFFBFBFRLL\n" +
            "FBBFFFBRLL\n" +
            "FBBBFFFLRR\n" +
            "FFBBFBFRRR\n" +
            "BFBBFBFLRR\n" +
            "BFBFFBBLRL\n" +
            "FFFFBBFLLR\n" +
            "BFFBBFFRLL\n" +
            "FFFBBBFRRL\n" +
            "BFBBBFBRRR\n" +
            "FFBFBBBLRL\n" +
            "BBFFFFBLRL\n" +
            "BFFFFFFLRR\n" +
            "FFBFFBBRLL\n" +
            "FBBBFBBRLR\n" +
            "BBFFFFFLLR\n" +
            "FBFBFBFRRR\n" +
            "FBBFFBBRRR\n" +
            "FBBFFFBRRL\n" +
            "BFFBFBBLLL\n" +
            "FFBBBBFRLL\n" +
            "FFBBFFBLRL\n" +
            "FFFBBFFLLL\n" +
            "FFFBBBBLRR\n" +
            "FFBFBBBRRR\n" +
            "FBBBBFBRLL\n" +
            "FBFFFFFLRR\n" +
            "BBFFFFBLLL\n" +
            "FBFBBFFLLR\n" +
            "BFFFBFBLRR\n" +
            "BBFFFFBRRR\n" +
            "FBBFBFFRLR\n" +
            "BFBFBFBRLL\n" +
            "FFBBBFFRLL\n" +
            "FBFFFBBLRL\n" +
            "FFFBBFFLRR\n" +
            "BFBFFBBLLL\n" +
            "BFBFBBBLLR\n" +
            "FBBBBBFRRL\n" +
            "FFFFBBFRLR\n" +
            "FBBFBFBRLL\n" +
            "FFBFFFFRLR\n" +
            "BFBBFFFRRR\n" +
            "FFBFBBFLRL\n" +
            "BFBFFFFLLR\n" +
            "BFFFBBFRRL\n" +
            "FBFFBFFLLL\n" +
            "FBBBFBFRRL\n" +
            "FBFBFBFLLR\n" +
            "FBBFBBBRLL\n" +
            "BFFFFFBRLL\n" +
            "FBFFBBFRLL\n" +
            "BFBBFFBRLL\n" +
            "BFFBFBBLLR\n" +
            "BFFFFFFLLR\n" +
            "FBBBBFBLRL\n" +
            "FBFBBBBLRR\n" +
            "FFFFBBBLLL\n" +
            "FBBBFFFRLR\n" +
            "FBBBFBFLRL\n" +
            "FFFBFFFRLR\n" +
            "BBFFFBBRLR\n" +
            "BFBFBBBLRR\n" +
            "FFBFFBBRRR\n" +
            "FFFBFBBRRR\n" +
            "FBBBBFFLLL\n" +
            "BFBBBBBLLL\n" +
            "FFBFFFFLRL\n" +
            "BFBFFBBLRR\n" +
            "FFBFBFBRLL\n" +
            "FBBFBBBLLL\n" +
            "FBBFBBFRRL\n" +
            "BBFFFBFLRR\n" +
            "FBFFFFBRLR\n" +
            "FBBBBBFRRR\n" +
            "FFFBBFFRLL\n" +
            "BFBBFBBLRL\n" +
            "FFBFFFBRLR\n" +
            "FBBFFFBRRR\n" +
            "FFFBBFBRRL\n" +
            "BFBBBFFRRR\n" +
            "FBFBFFBLRL\n" +
            "FFBFFBBRRL\n" +
            "BFBFFFFRLL\n" +
            "FBBBFBFRLL\n" +
            "FBFBFFBLRR\n" +
            "FFFBBBFRRR\n" +
            "FBFFBBBLRL\n" +
            "BFFFBBBLRL\n" +
            "BFBBFFFLRR\n" +
            "BBFFFBBLRL\n" +
            "FBFFBBFRRL\n" +
            "BFBBBFBLLR\n" +
            "BFFBFFBRRL\n" +
            "FFBBBFFLRL\n" +
            "FFBFBFBRLR\n" +
            "BFFBFBFLLR\n" +
            "BFFFFBBRLL\n" +
            "FFFFBBFLRL\n" +
            "BFFFBBBLLL\n" +
            "FBBFFFBLRL\n" +
            "BFFFFBFRLL\n" +
            "BFBBFBBLLR\n" +
            "FBBFBBFLRL\n" +
            "FBBFFBFLRL\n" +
            "FFFFBBFRRL\n" +
            "FBFFBBBLLR\n" +
            "FFBBBFFLLL\n" +
            "FFBFBFFLRR\n" +
            "FBFFBBFLRR\n" +
            "FFBFBBBLLL\n" +
            "BFFBBBFRLL\n" +
            "FBFFFBFLLR\n" +
            "BFBBBBFLRR\n" +
            "FBFBFFFLLL\n" +
            "FFFBFBBLLR\n" +
            "FFFBBFFLRL\n" +
            "FBBBFBBLRR\n" +
            "FBBBFBBRLL\n" +
            "FFBBBFFRLR\n" +
            "BFBFFFFRLR\n" +
            "FBBFFBBLRL\n" +
            "FBFBFFBRRL\n" +
            "BFBBBFFLRL\n" +
            "FFBBBBBLRL\n" +
            "FBBBBFFRLR\n" +
            "FFBBFFFLLL\n" +
            "FBFFFFFLRL\n" +
            "FFFBFFFRRL\n" +
            "BFFFBBFRLR\n" +
            "FFFBBFBLRL\n" +
            "FBBFBBBRRR\n" +
            "FFFBBFBLLL\n" +
            "BFFFFFFLLL\n" +
            "BFBFBBFRLL\n" +
            "BFFFBFFRLL\n" +
            "FFBBBBFLRR\n" +
            "FFFBBBBRLL\n" +
            "FFBFBBFLLL\n" +
            "BFBBFFBLRR\n" +
            "FBFFFBFLRR\n" +
            "BFBBFFFLRL\n" +
            "FFBBFBBRLR\n" +
            "BFBFFBFRRR\n" +
            "FBBFFFBLRR\n" +
            "BFFFFBFRLR\n" +
            "FBFFFBBLLR\n" +
            "FBFBFFFLLR\n" +
            "FBBBBFFRRR\n" +
            "FBBFFFFLLL\n" +
            "FFBFFBBLRL\n" +
            "FFBBBBBRLL\n" +
            "FFBFBBBLLR\n" +
            "FBFFBFFRRL\n" +
            "FFBFFBBLLR\n" +
            "BFFFBBFLLL\n" +
            "FFBBFFBRLL\n" +
            "FFBFFBFLRR\n" +
            "FFBFFBBLLL\n" +
            "BFBBBFFLLR\n" +
            "FBFBFBFLRL\n" +
            "FFBBBBFRLR\n" +
            "BFBBFFBLLR\n" +
            "BFFFFFBRLR\n" +
            "FBFFFFBRLL\n" +
            "FBFBFFBRLR\n" +
            "BFFBBFBLLL\n" +
            "BBFFBFFRLL\n" +
            "BBFFFFFLRR\n" +
            "BFFFFBFRRR\n" +
            "BFBFBFBLLR\n" +
            "BFFFFFFLRL\n" +
            "FFBBFBBLLL\n" +
            "FFFBBBFLRR\n" +
            "FBBFFBFRLR\n" +
            "BBFFFBBRLL\n" +
            "FBBFBFFLLR\n" +
            "FBBFFBFLRR\n" +
            "BFFFBFBRLL\n" +
            "BFBFBFFLLL\n" +
            "FBFFBBBRRL\n" +
            "FBBBFBFRLR\n" +
            "FFBFFBFRRL\n" +
            "BBFFBFFRRL\n" +
            "FBBFFFBRLR\n" +
            "FFFFBBBRLL\n" +
            "BFBBFFFRRL\n" +
            "BFBBBBBRRL\n" +
            "BFBBFFFLLL\n" +
            "BFFBBBFLLR\n" +
            "FBFFFFFRRR\n" +
            "FBFBBFFRRL\n" +
            "FBBBFBBRRR\n" +
            "FBFBBBBLLL\n" +
            "BFFFFBBRLR\n" +
            "FFFBFBBRLR\n" +
            "BFFBFFBLRR\n" +
            "FBBFFFFRLR\n" +
            "FBFFBBFRRR\n" +
            "FFFBFBFLLL\n" +
            "BBFFFFBLRR\n" +
            "FBFBBBFLRR\n" +
            "BFBBBBFRRL\n" +
            "FFBBFFBRRL\n" +
            "BFFBBFBRRR\n" +
            "FFBFBBFRRR\n" +
            "FBFFFBBRRL\n" +
            "FBFBBFFLLL\n" +
            "BFFBFFBLLR\n" +
            "FBBFBFFRRR\n" +
            "FFFBBBFLRL\n" +
            "BFFBBBFLRL\n" +
            "FBFFFBFRLR\n" +
            "BFFFFFFRRL\n" +
            "FBBBFFBLLR\n" +
            "FBBBBBFRLR\n" +
            "BFBFFFBLLR\n" +
            "FFBBFBBRRL\n" +
            "BFBBFBFLRL\n" +
            "FBBBBBBRLR\n" +
            "BFFBFBBLRR\n" +
            "FBFFBBFLRL\n" +
            "FBBBFFBLRL\n" +
            "FFBBFBFRLL\n" +
            "BFBBBFBRRL\n" +
            "FBFBFFBRRR\n" +
            "FFBFBFFLLR\n" +
            "FFBFFBFLLL\n" +
            "FFFBBBBRRR\n" +
            "FBFFBFBRRL\n" +
            "FFBFFFFLRR\n" +
            "FFFFBBBLRL\n" +
            "FFBBBBBRRR\n" +
            "FFBBFFBRRR\n" +
            "FFBFBFBRRL\n" +
            "FFFBBFBRLR\n" +
            "BFBBFFBRLR\n" +
            "BFBBFBBRLL\n" +
            "FFBFFFBLLL\n" +
            "FFBBFFBLLL\n" +
            "FBBFFFFLRL\n" +
            "FFFBFFFLLL\n" +
            "BFBBFBFRLL\n" +
            "BBFFFBFRRR\n" +
            "FFFBBFBRLL\n" +
            "BFBBFFBLLL\n" +
            "FBFBFBBRLR\n" +
            "BBFFFBBRRR\n" +
            "FFFBBBBLRL\n" +
            "FFBFBFFRRL\n" +
            "FBBBBFBLLR\n" +
            "FBBFBBBLRL\n" +
            "BFBFBBBLLL\n" +
            "FBBFFBFLLR\n" +
            "BFBFBBBLRL\n" +
            "FFFBFFFRRR\n" +
            "FFBFFBFLRL\n" +
            "FBFBFBFRLL\n" +
            "FBFBBBFRLR\n" +
            "FBBBFFFLLR\n" +
            "FFBFBFFLRL\n" +
            "BFFBFBFRRR\n" +
            "FBBFBFBLLL\n" +
            "BFBBBFFLRR\n" +
            "FFFBFBBLRL\n" +
            "FBFBFBFLLL\n" +
            "FFBBBBBLLL\n" +
            "FBBFBFFLRL\n" +
            "FFBBFFFLRL\n" +
            "FBFBBBBRRR\n" +
            "BBFFFFBLLR\n" +
            "FFFBBBBLLR\n" +
            "BFFFBFBRRR\n" +
            "FBFFFFBLRL\n" +
            "FBFFFBFRLL\n" +
            "BBFFFFFRRR\n" +
            "FFBFFFFLLR\n" +
            "BBFFBFFLLL\n" +
            "FBFFBBBRRR\n" +
            "BFBBBBFRLR\n" +
            "BBFFFFFRLR\n" +
            "FBFBBFBRRL\n" +
            "FBFFFBFLLL\n" +
            "FBFBFFFLRR\n" +
            "FFBFFFBLRL\n" +
            "BFFFBBFLLR\n" +
            "FBFBFFBRLL\n" +
            "FFBBBFFRRL\n" +
            "FFBBBFBLRL\n" +
            "BFBFBBFLLL\n" +
            "BFBFBFBLRL\n" +
            "FBFBBBBLLR\n" +
            "FBBBBFFLLR\n" +
            "FFBBFBFLLR\n" +
            "BFBFBFFRLL\n" +
            "FFBBFBFRRL\n" +
            "FBBBFBBLLL\n" +
            "FFBBFFFLRR\n" +
            "FBFFBFBRRR\n" +
            "BFFFFFFRLR\n" +
            "BFBFBBFRRR\n" +
            "FBBBBBBRRL\n" +
            "FFBFFBFLLR\n" +
            "FFBFBBFRLL\n" +
            "FFBFBFFLLL\n" +
            "BFFFFFBLLL\n" +
            "BFFFBFBLLR\n" +
            "FFBBBFBLLR\n" +
            "FBBFFBFRLL\n" +
            "FBFBBBFLLL\n" +
            "FBFFFFFRRL\n" +
            "BFFBBFFRRL\n" +
            "BFBFFBFLLR\n" +
            "BFBBFBBRRR\n" +
            "BFFBBFFLRR\n" +
            "FBBFBBFRLL\n" +
            "FBBBBFBRLR\n" +
            "FFBFBBBRLR\n" +
            "FFFBFBBRRL\n" +
            "BFBBBBFRLL\n" +
            "FBFBBFFRRR\n" +
            "BFFFBBFRLL\n" +
            "FBFFBFBLLL\n" +
            "FBBFBBFLRR\n" +
            "BFBFFBFLRL\n" +
            "BFBFBFFRLR\n" +
            "BFBBBFBLRL\n" +
            "FBFFFFBRRL\n" +
            "FBBFFFFRRR\n" +
            "BFBFBFBLLL\n" +
            "BFBFBBBRLR\n" +
            "FBBFFBBRLL\n" +
            "BFBFFFFLRL\n" +
            "FFBFFFFLLL\n" +
            "FBFBFBFRLR\n" +
            "BFFBFBFLRL\n" +
            "FBFFBFFLRL\n" +
            "BFFFFFBLRR\n" +
            "FFBBFFFLLR\n" +
            "FFBFBFBLRL\n" +
            "BFBFFBFLLL\n" +
            "BFFBBFBRLR\n" +
            "FFBFFFBRLL\n" +
            "BFBBFFFRLL\n" +
            "BFFFFBBRRR\n" +
            "BFFBBFFLRL\n" +
            "FBFBBBBRRL\n" +
            "BFBFFBBLLR\n" +
            "FFBFBFBLLR\n" +
            "BFBFFBBRLR\n" +
            "BFBFBFFRRL\n" +
            "FBBFBBFLLR\n" +
            "BFBFBBBRLL\n" +
            "FBBBBBBLLL\n" +
            "BFFBBFFRLR\n" +
            "BFFFBBBRLR\n" +
            "FBFBBFFLRR\n" +
            "FBBBFFBRLR\n" +
            "BFBFFFBLRL\n" +
            "BFFFBFFLRR\n" +
            "FFBFFBBRLR\n" +
            "FFBFBFBLLL\n" +
            "BFFBBFBLRL\n" +
            "BFBFBFBRRR\n" +
            "FFBFBFBRRR\n" +
            "FFBBBBFRRL\n" +
            "FFBBBFBRLR\n" +
            "BFFBFBFRLL\n" +
            "BFFFBBFRRR\n" +
            "FFBFBFFRRR\n" +
            "BFFBBBBRLL\n";
}
