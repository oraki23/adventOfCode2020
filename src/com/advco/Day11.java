package com.advco;

import java.util.Arrays;

public class Day11 {
    public static void main(String[] args) {
        partTwo();
    }

    public static void partOne(){
        String[] rows = PUZZLE_INPUT.split("\n");
        char[][] initialLayout = new char[rows.length][rows[0].length()];

        for(int i = 0; i < rows.length; i++){
            for(int j = 0; j < rows[i].length(); j++){
                initialLayout[i][j] = rows[i].charAt(j);
            }
        }

        boolean consistentState = false;
        char[][] startLayout = Arrays.stream(initialLayout).map(char[]::clone).toArray(char[][]::new);
        char[][] endLayout = Arrays.stream(initialLayout).map(char[]::clone).toArray(char[][]::new);
        while(!consistentState){
            for(int i = 0; i < rows.length; i++){
                for(int j = 0; j < rows[i].length(); j++){
                    char seat = startLayout[i][j];

                    int nbOfOccupiedSeatAdjacent = 0;

                    char top = 'L';
                    char bottom = 'L';
                    char left = 'L';
                    char right = 'L';
                    char topLeft = 'L';
                    char topRight = 'L';
                    char bottomLeft = 'L';
                    char bottomRight = 'L';
                    if(i-1 >= 0){
                        top = startLayout[i-1][j];
                        if(top == '#'){
                            nbOfOccupiedSeatAdjacent++;
                        }
                    }
                    if(i+1 < rows.length){
                        bottom = startLayout[i+1][j];
                        if(bottom == '#'){
                            nbOfOccupiedSeatAdjacent++;
                        }
                    }
                    if(j-1 >= 0){
                        left = startLayout[i][j-1];
                        if(left == '#'){
                            nbOfOccupiedSeatAdjacent++;
                        }
                    }
                    if(j+1 < rows[i].length()){
                        right = startLayout[i][j+1];
                        if(right == '#'){
                            nbOfOccupiedSeatAdjacent++;
                        }
                    }
                    if(i-1 >= 0 && j-1 >= 0){
                        topLeft = startLayout[i-1][j-1];
                        if(topLeft == '#'){
                            nbOfOccupiedSeatAdjacent++;
                        }
                    }
                    if(i-1 >= 0 && j+1 < rows[i].length()){
                        topRight = startLayout[i-1][j+1];
                        if(topRight == '#'){
                            nbOfOccupiedSeatAdjacent++;
                        }
                    }
                    if(i+1 < rows.length && j-1 >= 0){
                        bottomLeft = startLayout[i+1][j-1];
                        if(bottomLeft == '#'){
                            nbOfOccupiedSeatAdjacent++;
                        }
                    }
                    if(i+1 < rows.length && j+1 < rows[i].length()){
                        bottomRight = startLayout[i+1][j+1];
                        if(bottomRight == '#'){
                            nbOfOccupiedSeatAdjacent++;
                        }
                    }

                    if(seat == 'L' &&
                            top != '#' &&
                            bottom != '#' &&
                            left != '#' &&
                            right != '#' &&
                            topLeft != '#' &&
                            topRight != '#' &&
                            bottomLeft != '#' &&
                            bottomRight != '#'
                    ){
                        endLayout[i][j] = '#';
                    } else if(seat == '#' && nbOfOccupiedSeatAdjacent >= 4){
                        endLayout[i][j] = 'L';
                    }
                }
            }

            for(int i = 0; i < rows.length; i++){
                for(int j = 0; j < rows[i].length(); j++){
                    System.out.print(endLayout[i][j]);
                }
                System.out.println();
            }
            System.out.println();
            System.out.println();

            consistentState = true;
            for(int i = 0; i < rows.length; i++){
                for(int j = 0; j < rows[i].length(); j++){
                    if(startLayout[i][j] != endLayout[i][j]){
                        consistentState = false;
                    }
                }
            }

            startLayout = endLayout;
            endLayout = Arrays.stream(startLayout).map(char[]::clone).toArray(char[][]::new);
        }

        int nbOfOccupiedSeats = 0;
        for(int i = 0; i < rows.length; i++){
            for(int j = 0; j < rows[i].length(); j++){
                if(endLayout[i][j] == '#'){
                    nbOfOccupiedSeats++;
                }
            }
        }
        System.out.println("nbOfOccupiedSeats = " + nbOfOccupiedSeats);
    }

    public static void partTwo(){
        String[] rows = PUZZLE_INPUT.split("\n");
        char[][] initialLayout = new char[rows.length][rows[0].length()];

        for(int i = 0; i < rows.length; i++){
            for(int j = 0; j < rows[i].length(); j++){
                initialLayout[i][j] = rows[i].charAt(j);
            }
        }

        boolean consistentState = false;
        char[][] startLayout = Arrays.stream(initialLayout).map(char[]::clone).toArray(char[][]::new);
        char[][] endLayout = Arrays.stream(initialLayout).map(char[]::clone).toArray(char[][]::new);
        while(!consistentState){
            for(int i = 0; i < rows.length; i++){
                for(int j = 0; j < rows[i].length(); j++){
                    char seat = startLayout[i][j];

                    int nbOfOccupiedSeatAdjacent = 0;

                    char top = 'L';
                    char bottom = 'L';
                    char left = 'L';
                    char right = 'L';
                    char topLeft = 'L';
                    char topRight = 'L';
                    char bottomLeft = 'L';
                    char bottomRight = 'L';

                    /*------ TOP ------*/
                    int incrementI = i-1;
                    int incrementJ = j;
                    do {
                        if(incrementI >= 0){
                            top = startLayout[incrementI][incrementJ];
                            if(top != '.'){
                                break;
                            }
                        }
                        incrementI--;
                    }while(incrementI >= 0);
                    if(top == '#'){
                        nbOfOccupiedSeatAdjacent++;
                    }

                    /*------ BOTTOM ------*/
                    incrementI = i+1;
                    incrementJ = j;
                    do {
                        if(incrementI < rows.length){
                            bottom = startLayout[incrementI][incrementJ];
                            if(bottom != '.'){
                                break;
                            }
                        }
                        incrementI++;
                    }while(incrementI < rows.length);
                    if(bottom == '#'){
                        nbOfOccupiedSeatAdjacent++;
                    }

                    /*------ LEFT ------*/
                    incrementI = i;
                    incrementJ = j-1;
                    do {
                        if(incrementJ >= 0){
                            left = startLayout[incrementI][incrementJ];
                            if(left != '.'){
                                break;
                            }
                        }
                        incrementJ--;
                    }while(incrementJ >= 0);
                    if(left == '#'){
                        nbOfOccupiedSeatAdjacent++;
                    }

                    /*------ right ------*/
                    incrementI = i;
                    incrementJ = j+1;
                    do {
                        if(incrementJ < rows[i].length()){
                            right = startLayout[incrementI][incrementJ];
                            if(right != '.'){
                                break;
                            }
                        }
                        incrementJ++;
                    }while(incrementJ < rows[i].length());
                    if(right == '#'){
                        nbOfOccupiedSeatAdjacent++;
                    }

                    /*------ TOP LEFT ------*/
                    incrementI = i-1;
                    incrementJ = j-1;
                    do {
                        if(incrementI >= 0 && incrementJ >= 0){
                            topLeft = startLayout[incrementI][incrementJ];
                            if(topLeft != '.'){
                                break;
                            }
                        }
                        incrementI--;
                        incrementJ--;
                    }while(incrementI >= 0 && incrementJ >= 0);
                    if(topLeft == '#'){
                        nbOfOccupiedSeatAdjacent++;
                    }

                    /*------ TOP RIGHT ------*/
                    incrementI = i-1;
                    incrementJ = j+1;
                    do {
                        if(incrementI >= 0 && incrementJ < rows[i].length()){
                            topRight = startLayout[incrementI][incrementJ];
                            if(topRight != '.'){
                                break;
                            }
                        }
                        incrementI--;
                        incrementJ++;
                    }while(incrementI >= 0 && incrementJ < rows[i].length());
                    if(topRight == '#'){
                        nbOfOccupiedSeatAdjacent++;
                    }

                    /*------ BOTTOM LEFT ------*/
                    incrementI = i+1;
                    incrementJ = j-1;
                    do {
                        if(incrementI < rows.length && incrementJ >= 0){
                            bottomLeft = startLayout[incrementI][incrementJ];
                            if(bottomLeft != '.'){
                                break;
                            }
                        }
                        incrementI++;
                        incrementJ--;
                    }while(incrementI < rows.length && incrementJ >= 0);
                    if(bottomLeft == '#'){
                        nbOfOccupiedSeatAdjacent++;
                    }

                    /*------ BOTTOM RIGHT ------*/
                    incrementI = i+1;
                    incrementJ = j+1;
                    do {
                        if(incrementI < rows.length && incrementJ < rows[i].length()){
                            bottomRight = startLayout[incrementI][incrementJ];
                            if(bottomRight != '.'){
                                break;
                            }
                        }
                        incrementI++;
                        incrementJ++;
                    }while(incrementI < rows.length && incrementJ < rows[i].length());
                    if(bottomRight == '#'){
                        nbOfOccupiedSeatAdjacent++;
                    }

                    if(seat == 'L' &&
                            top != '#' &&
                            bottom != '#' &&
                            left != '#' &&
                            right != '#' &&
                            topLeft != '#' &&
                            topRight != '#' &&
                            bottomLeft != '#' &&
                            bottomRight != '#'
                    ){
                        endLayout[i][j] = '#';
                    } else if(seat == '#' && nbOfOccupiedSeatAdjacent >= 5){
                        endLayout[i][j] = 'L';
                    }
                }
            }

            for(int i = 0; i < rows.length; i++){
                for(int j = 0; j < rows[i].length(); j++){
                    System.out.print(endLayout[i][j]);
                }
                System.out.println();
            }
            System.out.println();
            System.out.println();

            consistentState = true;
            for(int i = 0; i < rows.length; i++){
                for(int j = 0; j < rows[i].length(); j++){
                    if(startLayout[i][j] != endLayout[i][j]){
                        consistentState = false;
                    }
                }
            }

            startLayout = endLayout;
            endLayout = Arrays.stream(startLayout).map(char[]::clone).toArray(char[][]::new);
        }

        int nbOfOccupiedSeats = 0;
        for(int i = 0; i < rows.length; i++){
            for(int j = 0; j < rows[i].length(); j++){
                if(endLayout[i][j] == '#'){
                    nbOfOccupiedSeats++;
                }
            }
        }
        System.out.println("nbOfOccupiedSeats = " + nbOfOccupiedSeats);
    }

    public static final String PUZZLE_TEST = "L.LL.LL.LL\n" +
            "LLLLLLL.LL\n" +
            "L.L.L..L..\n" +
            "LLLL.LL.LL\n" +
            "L.LL.LL.LL\n" +
            "L.LLLLL.LL\n" +
            "..L.L.....\n" +
            "LLLLLLLLLL\n" +
            "L.LLLLLL.L\n" +
            "L.LLLLL.LL";
    public static final String PUZZLE_INPUT =
            "LLLLLLL.LLLLLLLLL.LLLLLL.LLLLLLLLLLLL.LLLLLLLLLLLLLLL.LLLLLLL.LLLL.LLLL.LLLLLLLLLLLLLLLLL.LLLLLLLLL\n" +
            "LLLLLLLLLLLLLLLLL.LLLLLLLLLLLLLL.LLLL.LLLLLLLLL.LLLLL.LLLLLLLLLLLL.LLLL.LLLLLLLLLLLLLLLLL.LLLLLLLLL\n" +
            "LLLLLLL.LLLLLLLLLLLLLLLLLLLLLLLL.LLLL.LLLLLLLLL.LLLLLLLLLLLLL.LLLL.LLLL.LLLLLLLLLLLLLLLLLLLLLLLLLLL\n" +
            "LLLLLLLLLLLLLLLLL.LLLLLL.LLLLLLL.LLLL.LLLLLLLLL.LLLLL.LLLLLLLLLLLL.LLLL.LLLLLLLL.LLLLL.LLLLLLLLLLLL\n" +
            "LLLLLLL.LLLLLLLLLLLLLLLLLLLLLLLL.LLLL.LLLLLLL.LLLLLLL.LLLLLLLLLLLL.LL.LLLLLLLLLL.LLLLLLLLLLLLLLLLLL\n" +
            "LLLLLLL.LLLLLLLLLLLLLLLLLLLLLLLL.LLLL.LLLLLLLLLLLLLLLLLLLLLLL.LLLL.LLLLLLLLLLLLL..LLLLLLL.LLLLLLLLL\n" +
            "LLLLLLL.LLLLLLLLL.LLLLLL.LLLLLLLLLLLL.LLLLLLLLLLLLLLL.LLLLLLL.LLLL.LLLLLLLLLLLLLLLLLLLLLL.LLLLLLLLL\n" +
            "LLLLLLL.LLLLLLLLLLLLLLLL.LLLLLLLLLLLL.LLLLLLLLL.LLLLL.LLLLLLLLLLLLLLLLL.LLLLLLLL.LLLLLLLL.LLLLLLLLL\n" +
            "LLLLL.L.LLLLLLLLL.LLLLLL.LLLLLLLLLLLL.LLLLLLLLLLLLLLL.LLLLLL.LLLLLLLLLL.LLLLLLLL.LLLLLLLLLLLLLLLLLL\n" +
            "..LLL..L.L..LL...LL......L..LL..L..LL........L.....LLLL.L.L.L.........L.L..L.L.....L..L............\n" +
            "LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL.LLLL.LLLLLLLLLLLLLLL.LLLLLLL.LLLLLLLLL.LLLLLLLLLLLLLLLLL.LLLLLLLLL\n" +
            "LLLLLLL.LLLLLLLLLLLL.LLL.LLLLLLLLLLLL.LLLLLLLLL.LLLLL.LLLLLLL.LLLLLLLLL.LLLLLLLLLLLLLLLLL.LLLLLLLLL\n" +
            "LLLLLLLLLLLLLLLLL.LLLLLL.LLLLLLL.LLLL.LLLL.LLLLLLLLLL.LLLLLLL.LLLL.LLLL.LLLLLLLL.LLLLLLLL.LLLLLLLLL\n" +
            "LLLLLLL.LLLLLLLLLLLLLLLLLLLLLLLLLLLLL.LLLLLLLLL.LLLLLLLLLLLLL.LLLL.LLLL.LLLLLLLLLLLLLLLLL.LLLLLLLLL\n" +
            "LLLLLLL.LLLLLLLLL.LLLLLL.LLLLLLLLLLLL.LLLLLLLLLLLLLLL.LLLLLLLLLLLL.LLLL.LLLLLLLLLLLLLLLLLLLLLLLLLLL\n" +
            "LLLLLLL.LLLLL.LLL.LLLLLL.LLLLLLLLLLLL.LLLLLLLLL.LLLLL.LLLLLLL.LLLL.LLLL.LLLLLLLL.LLLLLLLLLLLLLLLLLL\n" +
            "LLLLLLLLLLLLLLLLL.LLLLLL.LLLLLLL.LLLL.LLLLLLLLL.LLLLL.LLLLLLL.LLLL.LLLLLLLLLLLLL.LLLLLLLLLLLLLLLLLL\n" +
            "....L..LL...L.L.L.......LL....L.....L..L...L.L.L....LL.L..L...LL...L....L..L...L.............LL..LL\n" +
            "LLLLLLL.LLLLLLLLLLLLLLLLLLLLLLLL.LLLL.LLLLLLLLL.LLLLL.LLLLLLLLLL.L.LLLL.LLLLLLLL.LLLLLLLL.LLLLLLLLL\n" +
            "LLLLLLL.LLLLLLLLL.LLLLLLLLLLLLLL.LLLL.LLLLLLLLLLLLLLL.LLLLLLL.LLLLLLLLL.LLLLLLLL.LLLLLLLL.LLLLLLLLL\n" +
            "LLLLLLL.LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL.LLLL.LLLLLLLL.LLLLLLLL.LLLLLLLLL\n" +
            "LLLLLLL.LLLLLLLLL.LLLLLL.LLLLLLL.LLLL.LLLLLLLLL.LLLLLLLLLLLLL.LLLLLLLLLLLLLLLLLLLLLLLLLLL.LLLLLLLLL\n" +
            "LLLLL.L.LLLLLLLLLLLLLLLL.LLLLLLL.LLLL.LLLLLLLLL.LLLL..LLLLLLLLLLLL.LLLL.LLLLLLLL.LLLLLLLLLLLLLLLLLL\n" +
            "LLLLLLL.LLLLLLLLL.LLLLLL.LLLLLLLLLLLL.LLLLLLLLLLLLLLL.LLLLLLL.LLLL.LLLL.LLLLLLLL.LLLLLLLL.LLLLLLLLL\n" +
            "LLLLLLL.LLLLLLLLLLLLLLLL.LLLLLLLLLLLL.LLLLLLLLL.LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL.LLLLLLLL.LLLLLLLLL\n" +
            "LLLLLLL.LLLLLLLLL.LLLLLLLLLLLLLL.LLLL.LLLLLLLLL.LLLLLLLLLLLLLLLLLL.LLLL.LLLLLLLL.LLLLLLLL.LLLLLLLLL\n" +
            "LLLLLLL.LLLLLLLLL.LLLLLLL.LLLLLLLLLLL.LLLLLLLLL.LLLLL.LLLLLLLLLLLL.L.LLLL.LLLLLLLLLLLLLLLLLLLLLLLLL\n" +
            "..L...L.L....L........L.......L.L...L..L.LL.........L.......LLL.LL.L...LL....L..L.L.L.LL.....L.....\n" +
            "LLLLLLL.LLLLLLLLL.LLLLLLLLLLLLLLLLLLL.LLLLLLLLL.LLLLL.LLLLLLL.LLLL.LLLL.LLLLLLLL.LLLLLLLL.LLLLLLLLL\n" +
            "LLLLLLL.LLLLLLLLL.LLLLLL.LLLLLLLLLLLL.LLLLLLLLL.LLLLL.LLLLLLLLLLLL.LLLLLLLLLLLLL.LLLLLLLL.LLLLLLLLL\n" +
            "LLLLLLL.LLLLLLLLL.LLLLLLLLLLLLLLLLLLL.LLLLLLLLL.LLLLL.LLLLLLL.LLLL.LLLLLLLLLLLLL.LLLLLLLL.LLLLLLLLL\n" +
            "LLLLLLLLLLLLLLLLLLLLLLLL.LLLLLLLLLLLLLLLLLLLLLL.LLLLLLLLLLLLL.LLLL.LLLL.LLLLLLLLLL.LLLLLLLLLLLLLLLL\n" +
            "LLLLLLL..LLLLLLLL.LLLLLL.LLLLLLL.LLLL.L.LLLLLLL.LLLLL.LLLLLLL.LLLL.LL.L.LLLLLLLLLLLLLLLLL.LLLLLLLLL\n" +
            "LLLLLLL.LLLLLLLLL.LLLLLL.LLLLLLL.LLLL.LLLLLLLLLLLLLLL.LLLLLLL.LLLLLLL.L.L.LLLLL.LLLLLLLLL.LLLLLLLLL\n" +
            "LLLLLLL.LLLLLLLLLLLLLLLL.LLLLLLL.LLLLLLLLLLLLLLLLLLLL.LLLLLLL.LLLL.LLLL.LLLLLLLLLLLLLLLLL.LLLLLLLLL\n" +
            "LLLLLLL.LLLLLLLLL.LLLLLL.LLLLLLL.LLLLLLL.LLLLLL.LLLLL..LLLLLL.LLLL.LLLLLLLLLLLLL.LLLLLLLL.LLLLLLLLL\n" +
            "LLLLLLL.LLLLLLLLL.LLLLLL.LLLLLLLLLLLL.LLLLLLLLL.LLLLLLLLLLLLLLLLLL.LLLL.LLLLLLLL.LLLLLLLL.LLLLLLLLL\n" +
            "L......L..L...L....L...L...L...L...L..LL...L......LL......LL......LLLL..L.....LL...L.LL..L....LL..L\n" +
            "LLLLLLL.LLLLLLLLL.LLLLLL..LLLLLLLLLLL.LLLLLLLLL.LLLLLLLLLLLLL.LLLL.LLLL.LLLLLLLL.LLLLLLLL.LLLLLLLLL\n" +
            "LLLLLLLLLLLLLLLLL.LLLLLLLLLLLLLL.LLLL.LLLLLLLLL.LLLLLLLLLLLLLLLLLL.LLLL.LLLLLLLLLLLLLLLLLLLLLLLLLLL\n" +
            "LLLLLLLLLLLLLLLLL.LLLLLL.LLLLLLL.LLLL.LLLLLLLLL.LLL.LLLLLLLLL.LLLLLLLLL.LLLLLLLL.LLLLLLLL.LLLLLLLLL\n" +
            "LLLLLLL.LLLLLLLLLLLLLLLLLLLLLLLL.LLLL.LLLLLLLLL.LLLLLLLLLLLLLLLLLL.LLLL.LLLLLLLL.LLLLLLLL.LLLLLLLLL\n" +
            "LLLLLL.LLLLLLLLLL.LLLLLL.LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL.LLLL.LLLLLLLLLLLLLLLLL.LLLL.LLLL\n" +
            "LLLLLLLLLLLLLLLLL.LLLLLL.LLLLLLL.LLLL.LLLLL.LLL.LLLLL.LLLLLLL.LL.L.LLLLLLLLLLLLL.LLLLLLLL.LLLLLLLLL\n" +
            "LLLLLLL.LLLLLLLLLLLLLLLLLLLLLLLLLLLLL.LLLLLLLLL.L.LLL.LLLLLLL.LLLL.LLLL..LLLLLLL.LLLLLLLLLLLLLLLLLL\n" +
            "LLLLLLL.LLLLLLLLL.LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL.L.LLLLLLLLLLLLLLLLLLLLLLLL.LLLLLLLL.LLLLLLLLL\n" +
            "L....L...L....L...L.....L....L.LL..L..L.LL.L..L..LL...LL..L......L...L.LL...L.L.....L.L.LLL.LL.L.L.\n" +
            "LLLLLLL.LLLLLLLLL.LLLLLLLLLLLLLL.LLLL.LLLLLLLLL.LLLLL.LLLLLL.LLLLL.LLLL.LLLLLLLL.LLLLLLLL.LLLLLLLLL\n" +
            "LLLLLLL.LLLLLLLLLLLLLLLL.LLLLLLLLLLLLLLLLLLLLLL.LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL.LLLLLLLL.LLLLLLLLL\n" +
            "LLLLLLLLLLLLLLLLL.LLLLL..LLLLLLL.LLLL.LLLLLLLLL.LLLLL.LLLLLLLLLLLL.LLLLLLLLLLLLL.LLLLLLLLLLLLLLLLLL\n" +
            "LLLLLLL.LLLLLLLLL.LLLLL..LLLLLLL.LLLLLLLLLLLLLL.LLLLL.LLLLLL..LLLL.LLLLLLLLLLLLL.LLLLLLLL.LLLLLLLLL\n" +
            "........L........L......L...L...L.L.....L..L..LL.L..L.L.LLL.L..L..L.L.LL.L.LL......L...L..LLL....L.\n" +
            "LLLLLLLLLLLLLLLLL.LLLLLL.LLLLLLL.LLLL.LLLLLLLLLLLLLLL.LLLLLLLLLLLLLLLLL.LLLLLLLL.LLLLLLLL.LLLLLLLLL\n" +
            "LLLLLLLLLLLLLLLLL.LLLLLLLLLLLLLL.LLLL.LLLLLLLLL.LLLLL.LLLLLLL.LLLLLL.LL.LLLLLLLL.LLLLLLLL.LLLLLLLLL\n" +
            "LLLLLLL.LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL.LLLLL.LLLLLLL.LLLL.L.LL.LLLLLLLLLLLLLLLLLLLLLLLLLLL\n" +
            "LLLLLLL.LLLLLLLLL.LLLLLL.LLLLLLL.LLLL.LLLLLLLLL.LLLLL.LLLLLLL.LLLL.LLLL.LLLLLLLL.LLLLLLLLLLLLLLLLLL\n" +
            "LLLLLLL.LLLLLLLLL.LLLLLLLLLLLLLLLLLLLLLLLLLLLL..LLLLL.LLLLLLLLLLLLLLLLL.LLLLLLLL..LLLLLLLLLLLLLLLLL\n" +
            "LLLLLLLLLLLLLLLLL.LLLLLLLLLLLLLL.LLLL.LLLLLLLLL.LLLLL.LLLLLLLLLLLLLLLLL.LLLLLLLL.LLLLLLLL.LLLLLLLLL\n" +
            "LLLLLLLLLLLLLLLLLLLLLLLL.LLLLLLL.LLLL.LLLLLLLLL.L..LLLLLLLLLL.LLLL.LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL\n" +
            "LLLLL.L.LLLL.LLLLLLLLLLLLLLLLLLL.LLLL.LLLLLLLLL.LLLLLLLLLLLLL.LLLLLLLLL.LLLLLLLL.LLLLLLLLLLLLLLLLLL\n" +
            "..LL..L......L.L.L...LL.L.LLL.L.....LLL..LL...L...L.L.LL....L.....L.LL..LLL...LL...L.....LLL.L..LL.\n" +
            "LLLLLLL.LLLLLLL.L.LLLLLL.LLLLLLL..LLL.LLLLLLLLLLLLLLL.LLLLLLLLLLLLLLLLL.LLLLLLLL.LLLLLLLLLLLLLLLLLL\n" +
            "LLLLLLL.LLLLLLLLL.LLLLLL.LLLLLLL.LLLLLLLLLLLLLL.LLLLLLLLLLLLL.LL.L.LLLL.LLLLLLLLLLLLLLLLL.LLLLLLLLL\n" +
            "LLLLLLLLLLLLLLLLL.LLLLLLLLLLLLLLLLLLLLLLLLLLLLL.LLLLL.LLLLLLL.LLLLLLLLL.LLLLLLLLLLLLLLLLL.LLLLLLLLL\n" +
            "LLLLLLL.LLLLLLLLL.LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL.LLLLLLL.LLLL.LLLL..LLLLLLL.LLLLLLLLLLLLLLLLLL\n" +
            "LLLLLLL.LLLLLLLLL.LLLLLL.LLLLLLL.LLLL.LLLLLLLLL.LLLLL.LLLLLLL.LLLL.LLLL.LLLLLLLL.LLLLLLLLLLLLLLLLLL\n" +
            "LLLLLLLLLLLLLLLLL.LLLLLL.LLLLLL..LLLL.LLLLLLLLL.LLLLLLLLLLLLLLLLLL.LLLL.LLLLLLLLLLLLLLLLL.LLLLLLLLL\n" +
            "..L..L.L....L.L..L.L.LL......LLL.L...L..L..L..........LL.......L...LL.L.L.L...L.......L........L...\n" +
            "LLLLLLLLLLLL.LLLL.LLLLLL.LLLLLLLLLLLL.LLLLLLLLLLLLLLL.LLLLLLLLLLLL.LLLL.LLLLLLLL.LLLLLLLL.LLLLLLLLL\n" +
            "LLLLLLL.LLLLLLLLL.LLLLLL.LLLLLLLLLLLLLLLLLLLLLL.LLLLLLLLLLLLL.LLLL.LLLL.LLLLLLLL.LLLLLLLL.LLLLLLLLL\n" +
            "LLLLL.L.LLLLLLLLL.LLLLLL.LLLLLLL.LLLLLLLLLLLLLL.LLLLL.LLL.LLL.LLLLLLLLL.LLLLLLLLLLLLLLLLL.LLLLLLLLL\n" +
            "LLLLLLLLLLLLLLLLL.LLLLLL.LLLLLLL.LLLL.LLLLLLLLL.LLLLL.LLL.LLL.LLLL.LLLL.LLLLLLLL.LLLLLLLL.LLLL.LLL.\n" +
            "LLLLLLL.LLLLLLLLL.LLLLLLLLLLLLLL.LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL.LLLLLLLL.LLLLLLLLL\n" +
            "LLLLLLLLLLLLLLLLL.LLLLLL.LLLLLLLLLLLL.LLLLLLLLL.LLLLL.LLLLLLLLLLLL.LLLL.LLLLLLLL.LLLLLLLL.LLLLLLLLL\n" +
            "LLLLLLLLLLLLLLLLL.LLLLLL.LLLLLLL.LLLLLLLLLLLLLL.LLLLLLLLLLLLL.LLLL.LLLL.LLLLLLLL.LLLLLLLL.LLLLLLLLL\n" +
            "LLLLL.L.LLLLLLLLL.LLLLL..LLLLLLL.LLLL.LLLL.LLLL.LLLLL.LLLLLLL.LLLL.LLLL.LL.LLLLL.LLLLLLLL.LLLLLLLLL\n" +
            "LLLLLLL.LLLLLLLLL.LLLLLLLLLLLLLL.LLLLLLLLLLLLLL.LLLLLLLLLLLLL.LLLL.LLLL.LLLLLLLL.LLLLLLLL.LLLLLLLLL\n" +
            "..L.......L..LL.L..L..LLLLL..L...L.L.L....L...L.LL.L.......L.....LL.........L.L......L.L..L.....L..\n" +
            "LLLLLLL.LLLLLLLLL.LLLLLL.LLLLLLL.LLLL.LLL.LLLLLLLLLLL.LLLLLLL.LLLL.LLLL.LLLLLLLLLLLLLLLLL.LLLLLLLLL\n" +
            "LLLLLLL.LLLLLLLLLLLLLLLL.LLLLLLLLLLLLLLLLLLLLLL.LLLLL.LLLLLLL.LLLL.LLLL.LLLLLLLL.LLLLLLLLLLLLLLLLLL\n" +
            "LLLLLLL.LLLLLLLLL.LLLLLL.LLLLLLL.LLLL.LLLLLLLLL.LLLLLLLLLLLLL.LLLL.LLLL.LLLLLLLL.LLLLLLLL.LLLLLLLLL\n" +
            "LLLLLLL.LLLLLLLLL.LLLLLL.LLLLLLL.LLLLLLLLLLLLLL.LLLLLLLLLLLLL.LLLL.LLLLLLLLLLLLL.LLLLLLLL.LLLLLLLLL\n" +
            "LLLLLLLLLLLLLLLLL.LLLLLL.LLLLLLL.LLLLLLLLLLLLLLLLLLLL.LLLLLLLLLLLLLLLLL.LLLLLLLL.LLLLLLLLLLLLLLLLLL\n" +
            "LLLLLLL.LLLLLLLLLLLLLLLL.LLLLLLL.LLLL.LLLLLLLLL.LLLLL.LLLLLLLLLLLL.LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL\n" +
            "LLLLLLL.LLLLLLLLL.LLLLLLLLLLLLLL.LLLL.LLLLLLLLLLLLLLLLLLLLLLL.LLLL.LLLLLLLLLLLLL.LLLLLLLL.LLLLLLLLL\n" +
            "LLLLLLLLLLLLLLLLL.LLLLLL.LLLLLLL.LLLLLLLLLLLLLL.LLLLL.LLLLLLL.LLLL.LLLLLLLLLLLLL.LLLLLLLL.LLLLLLLLL\n" +
            "L...LL.L..LLLL.LLL.L....L......L.............LL.....L...LL.L...L.L......LL....L......L...LL.L.....L\n" +
            "LLLLLLL.LLLLLLLLL.LLLLLL.L.LLLLL.LLLL.LLLLLLLLLLLLLLL.LLLLLLL.LLLLLLLLL.LLLLLLLL.LLLLLLLLLLLLLLLLLL\n" +
            "LLLLLLL.LLLLLLLLL.LLLLLL.LLLLLLL.LLLLLLLLLLLLLL.LLLLL.LLLLLLL.LLLL.LLLL.LLLLLLLLLLLLLLLLL.LLLLLLLLL\n" +
            "LLLLLLLLLLLLLLLLLLLLLLL.LLLLLLLL.LLLL.LLLLLLLLL.LLLLLLLLLLLLLLLLLL.LLLL.LLLLLLLLLLLLLLLLL.LLLLLLLLL\n" +
            "LLLLLLLLLLLLLLLLL.LLLLLL.LLLLLLLLLLLLLLLLLLLLLLLLLLLL.LLLLLLLLLLLL.LLLLLLLLLLLLL.LLLLLLLL.LLLLLLLLL\n" +
            "LLLLLLLLLLLLLLLLL.LLLLLLLLLLLLLL.LLLLLLLLLLLLLL.LLLLL.LLLLLLL.L.LL.LLLLLLLLLLLLL.LLLLLLLLLLLLLLLLLL\n" +
            "LLLLLLL.LLLLLLLLL.LLLLLL.LLLLLLLLLLLLLLLLLL.LLLLLL.LL.LLLLLLL.LLLL.LLLL.LLLLLLLLLLLLLLLLLLLLLLLLLLL\n" +
            "LLLLLLLLLLLLLLLLLLLLLLLL.LLLLLLL.LLLLLLLLLLLLLL.LLLLL.LLLLLLL.LLLL.LLLL.LLLLLLLL.LLLLLLLL.LLLLLLLLL\n" +
            "LLLLLLL.LLLLLLLLL.LLLLLLLLLLLLLL.LLLL.LLLLLLLLLLLLLLL.LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL.LLLLLLLLL\n" +
            "LLLLLLL.LLLLLLLLL.LLLLLLLLLLLLLL.LLLL.LLLLLLLLL.LLLLL.LLLLLLL.LLLLLLLLL.LLLLLLLL.LLLLLLLL.LLLLLLLLL\n" +
            "LLLLLLL.LLLLLLLLL.LLLLLL.LLLLLLL.LLLL.LLLLLLLLLLLLLLL.LLLLLLL.LLLL.LLLL.LLLLLLLL.LLLLLLLL.LLLLLLLLL\n" +
            "LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL.LLLL.LLLL.LLLL.LLLLL.LLLLLLL.LLLL.L.LLLLLLLLLLL.LLLLLL.L.LLLLLLLLL\n";
}
