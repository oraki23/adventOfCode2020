package com.advco;

import sun.nio.cs.ext.GB18030;

import java.math.BigInteger;
import java.util.*;

public class Day20 {
    public static void main(String[] args) {
        partTwo();
    }

    public static void partOne(){
        String[] tilesString = PUZZLE_TEST.split("\n\n");

        TreeMap<Integer, char[][]> tiles = new TreeMap<>();
        for(int i = 0; i < tilesString.length; i++){
            int index = Integer.parseInt(tilesString[i].substring(5, tilesString[i].indexOf(":")));

            String[] lines = tilesString[i].split("\n");

            char[][] matrix = new char[lines.length-1][lines.length-1];

            for(int j = 0; j < lines.length-1; j++){
                for(int k = 0; k < lines[j].length(); k++){
                    matrix[j][k] = lines[j+1].charAt(k);
                }
            }
            tiles.put(index, matrix);
        }

        /*Find and store all adjacent*/
        int[][] adjacentTable = new int[tiles.size()][5];

        Set<Map.Entry<Integer, char[][]>> entryMap = tiles.entrySet();

        int i = 0;
        for(Map.Entry<Integer, char[][]> entry: entryMap){
            int index = entry.getKey();
            char[][] matrix = entry.getValue();

            int topIdAdj = -1;
            int bottomIdAdj = -1;
            int leftIdAdj = -1;
            int rightIdAdj = -1;

            /* Invert or rotate until you find the good entry that will match... */
            int testIndex = 0;
            while((topIdAdj == -1 || bottomIdAdj == -1 || leftIdAdj == -1 || rightIdAdj == -1) && testIndex <= 12){
                if(testIndex == 1){
                    matrix = rotate90Matrix(matrix);
                } else if (testIndex == 2){
                    matrix = rotate90Matrix(flipMatrix(matrix,1));
                } else if (testIndex == 3){
                    matrix = rotate90Matrix(flipMatrix(matrix,2));
                } else if (testIndex == 4){
                    matrix = rotate90Matrix(flipMatrix(flipMatrix(matrix,2),1));
                }else if (testIndex == 5){
                    matrix = rotate90Matrix(rotate90Matrix(matrix));
                }else if (testIndex == 6){
                    matrix = rotate90Matrix(rotate90Matrix(flipMatrix(matrix,1)));
                }else if (testIndex == 7){
                    matrix = rotate90Matrix(rotate90Matrix(flipMatrix(matrix,2)));
                } else if (testIndex == 8){
                    matrix = rotate90Matrix(rotate90Matrix(flipMatrix(flipMatrix(matrix,2),1)));
                 }else if (testIndex == 9){
                    matrix = rotate90Matrix(rotate90Matrix(rotate90Matrix(matrix)));
                }else if (testIndex == 10){
                    matrix = rotate90Matrix(rotate90Matrix(rotate90Matrix(flipMatrix(matrix,1))));
                }else if (testIndex == 11){
                    matrix = rotate90Matrix(rotate90Matrix(rotate90Matrix(flipMatrix(matrix,2))));
                }else if (testIndex == 12){
                    matrix = rotate90Matrix(rotate90Matrix(rotate90Matrix(flipMatrix(flipMatrix(matrix,2),1))));
                }

                for(Map.Entry<Integer, char[][]> entry2: entryMap){
                    int index2 = entry2.getKey();
                    char[][] matrix2 = entry2.getValue();

                    if(index2 != index){
                        int ifAdj = -1;

                        int testIndex2 = 0;
                        while(ifAdj == -1 && testIndex2 <= 12){
                            if(testIndex2 == 1){
                                matrix2 = rotate90Matrix(matrix2);
                            } else if (testIndex2 == 2){
                                matrix2 = rotate90Matrix(flipMatrix(matrix2,1));
                            } else if (testIndex2 == 3){
                                matrix2 = rotate90Matrix(flipMatrix(matrix2,2));
                            } else if (testIndex2 == 4){
                                matrix2 = rotate90Matrix(flipMatrix(flipMatrix(matrix2,2),1));
                            } else if (testIndex2 == 5){
                                matrix2 = rotate90Matrix(rotate90Matrix(matrix2));
                            } else if (testIndex2 == 6){
                                matrix2 = rotate90Matrix(rotate90Matrix(flipMatrix(matrix2,1)));
                            } else if (testIndex2 == 7){
                                matrix2 = rotate90Matrix(rotate90Matrix(flipMatrix(matrix2,2)));
                            } else if (testIndex2 == 8){
                                matrix2 = rotate90Matrix(rotate90Matrix(flipMatrix(flipMatrix(matrix2,2),1)));
                            } else if (testIndex2 == 9){
                                matrix2 = rotate90Matrix(rotate90Matrix(rotate90Matrix(matrix2)));
                            }else if (testIndex2 == 10){
                                matrix2 = rotate90Matrix(rotate90Matrix(rotate90Matrix(flipMatrix(matrix2,1))));
                            }else if (testIndex2 == 11){
                                matrix2 = rotate90Matrix(rotate90Matrix(rotate90Matrix(flipMatrix(matrix2,2))));
                            }else if (testIndex2 == 12){
                                matrix2 = rotate90Matrix(rotate90Matrix(rotate90Matrix(flipMatrix(flipMatrix(matrix2,2),1))));
                            }
                            testIndex2++;

                            ifAdj = findIfAdjacent(matrix, matrix2);
                        }

                        if(ifAdj == 0){
                            if(leftIdAdj != index2 && bottomIdAdj != index2 && rightIdAdj != index2){
                                topIdAdj = index2;
                            }
                        } else if(ifAdj == 1){
                            if(topIdAdj != index2 && bottomIdAdj != index2 && rightIdAdj != index2){
                                leftIdAdj = index2;
                            }
                        } else if(ifAdj == 2){
                            if(topIdAdj != index2 && leftIdAdj != index2 && rightIdAdj != index2){
                                bottomIdAdj = index2;
                            }
                        } else if(ifAdj == 3){
                            if(topIdAdj != index2 && leftIdAdj != index2 && bottomIdAdj != index2){
                                rightIdAdj = index2;
                            }
                        }
                    }
                }

                testIndex++;
            }

            tiles.put(index, matrix);
            entryMap = tiles.entrySet();

            adjacentTable[i][0] = index;


            int count = 0;
            for(int y = 1; y < 5; y++){
                if(adjacentTable[i][y] == -1){
                    count++;
                }
            }

            if(count <= 2){
                adjacentTable[i][1] = topIdAdj;
                adjacentTable[i][2] = leftIdAdj;
                adjacentTable[i][3] = bottomIdAdj;
                adjacentTable[i][4] = rightIdAdj;
                i++;
            }
        }

        List<Integer> cornerId = new ArrayList<>();
        for(int x = 0; x < adjacentTable.length; x++){
            int count = 0;

            for(int y = 1; y < 5; y++){
                if(adjacentTable[x][y] == -1){
                    count++;
                }
            }

            if(count == 2){
                cornerId.add(adjacentTable[x][0]);
            }
        }

        printMatrixes(tiles);

        BigInteger response = BigInteger.valueOf(cornerId.get(0)).multiply(BigInteger.valueOf(cornerId.get(1)).multiply(BigInteger.valueOf(cornerId.get(2))).multiply(BigInteger.valueOf(cornerId.get(3))));
        System.out.println("response: " + response);
    }

    public static int findIfAdjacent(char[][] matrix1, char[][] matrix2){
        /*
        * 0 = top
        * 1 = left
        * 2 = bottom
        * 3 = right
        * */

        // BOTTOM matrix 1 TOP Matrix2
        boolean works = true;
        for(int i = 0; i < matrix1[0].length; i++){
            if(matrix1[0][i] != matrix2[matrix2.length-1][i]){
                works = false;
                break;
            }
        }
        if(works == true){
            return 0;
        }

        // TOP matrix 1 BOTTOM Matrix2
        works = true;
        for(int i = 0; i < matrix1[matrix1.length-1].length; i++){
            if(matrix1[matrix1.length-1][i] != matrix2[0][i]){
                works = false;
                break;
            }
        }
        if(works == true){
            return 2;
        }

        //LEFT matrix 1 RIGHT 2
        works = true;
        for(int i = 0; i < matrix1.length; i++) {
            if (matrix1[i][matrix1.length-1] != matrix2[i][0]) {
                works = false;
                break;
            }
        }
        if(works == true){
            return 3;
        }


        //RIGHT matrix 1 LEFT matrix 2
        works = true;
        for(int i = 0; i < matrix1.length; i++){
            if(matrix1[i][0] != matrix2[i][matrix2.length-1]){
                works = false;
                break;
            }
        }
        if(works == true){
            return 1;
        }

        return -1;
    }

    public static char[][] rotate90Matrix(char[][] matrix){
        char[][] newMatrix = new char[matrix.length][matrix[0].length];

        /* Y axis */
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[i].length; j++) {
                newMatrix[i][j] = matrix[j][matrix.length-1-i];
            }
        }

        return newMatrix;
    }

    public static char[][] flipMatrix(char[][] matrix, int axis){
        char[][] newMatrix = new char[matrix.length][matrix[0].length];

        /* Y axis */
        if(axis == 1){
            for(int i = 0; i < matrix.length; i++) {
                for(int j = 0; j < matrix[i].length; j++) {
                    newMatrix[i][j] = matrix[i][matrix[i].length-1-j];
                }
            }
        } else {
            for(int i = 0; i < matrix.length; i++) {
                for(int j = 0; j < matrix[i].length; j++) {
                    newMatrix[i][j] = matrix[matrix.length-1-i][j];
                }
            }
        }

        return newMatrix;
    }

    public static void printMatrixes(TreeMap<Integer, char[][]> tiles){
        Set<Map.Entry<Integer, char[][]>> entryMap = tiles.entrySet();

        for(Map.Entry<Integer, char[][]> entry: entryMap){
            int index = entry.getKey();
            char[][] matrix = entry.getValue();

            System.out.println("Tile " + index + ":");
            for(int j = 0; j < matrix.length; j++){
                for(int k = 0; k < matrix[j].length; k++){
                    System.out.print(matrix[j][k]);
                }
                System.out.println();
            }
            System.out.println();
        }

    }

    public static void partTwo(){
        String[] tilesString = PUZZLE_TEST.split("\n\n");

        TreeMap<Integer, char[][]> tiles = new TreeMap<>();
        for(int i = 0; i < tilesString.length; i++){
            int index = Integer.parseInt(tilesString[i].substring(5, tilesString[i].indexOf(":")));

            String[] lines = tilesString[i].split("\n");

            char[][] matrix = new char[lines.length-1][lines.length-1];

            for(int j = 0; j < lines.length-1; j++){
                for(int k = 0; k < lines[j].length(); k++){
                    matrix[j][k] = lines[j+1].charAt(k);
                }
            }
            tiles.put(index, matrix);
        }

        /*Find and store all adjacent*/
        int[][] adjacentTable = new int[tiles.size()][5];

        Set<Map.Entry<Integer, char[][]>> entryMap = tiles.entrySet();

        int i = 0;
        for(Map.Entry<Integer, char[][]> entry: entryMap){
            int index = entry.getKey();
            char[][] matrix = entry.getValue();

            int topIdAdj = -1;
            int bottomIdAdj = -1;
            int leftIdAdj = -1;
            int rightIdAdj = -1;

            /* Invert or rotate until you find the good entry that will match... */
            int testIndex = 0;
            while((topIdAdj == -1 || bottomIdAdj == -1 || leftIdAdj == -1 || rightIdAdj == -1) && testIndex <= 12){
                if(testIndex == 1){
                    matrix = rotate90Matrix(matrix);
                } else if (testIndex == 2){
                    matrix = rotate90Matrix(flipMatrix(matrix,1));
                } else if (testIndex == 3){
                    matrix = rotate90Matrix(flipMatrix(matrix,2));
                } else if (testIndex == 4){
                    matrix = rotate90Matrix(flipMatrix(flipMatrix(matrix,2),1));
                }else if (testIndex == 5){
                    matrix = rotate90Matrix(rotate90Matrix(matrix));
                }else if (testIndex == 6){
                    matrix = rotate90Matrix(rotate90Matrix(flipMatrix(matrix,1)));
                }else if (testIndex == 7){
                    matrix = rotate90Matrix(rotate90Matrix(flipMatrix(matrix,2)));
                } else if (testIndex == 8){
                    matrix = rotate90Matrix(rotate90Matrix(flipMatrix(flipMatrix(matrix,2),1)));
                }else if (testIndex == 9){
                    matrix = rotate90Matrix(rotate90Matrix(rotate90Matrix(matrix)));
                }else if (testIndex == 10){
                    matrix = rotate90Matrix(rotate90Matrix(rotate90Matrix(flipMatrix(matrix,1))));
                }else if (testIndex == 11){
                    matrix = rotate90Matrix(rotate90Matrix(rotate90Matrix(flipMatrix(matrix,2))));
                }else if (testIndex == 12){
                    matrix = rotate90Matrix(rotate90Matrix(rotate90Matrix(flipMatrix(flipMatrix(matrix,2),1))));
                }

                for(Map.Entry<Integer, char[][]> entry2: entryMap){
                    int index2 = entry2.getKey();
                    char[][] matrix2 = entry2.getValue();

                    if(index2 != index){
                        int ifAdj = -1;

                        int testIndex2 = 0;
                        while(ifAdj == -1 && testIndex2 <= 12){
                            if(testIndex2 == 1){
                                matrix2 = rotate90Matrix(matrix2);
                            } else if (testIndex2 == 2){
                                matrix2 = rotate90Matrix(flipMatrix(matrix2,1));
                            } else if (testIndex2 == 3){
                                matrix2 = rotate90Matrix(flipMatrix(matrix2,2));
                            } else if (testIndex2 == 4){
                                matrix2 = rotate90Matrix(flipMatrix(flipMatrix(matrix2,2),1));
                            } else if (testIndex2 == 5){
                                matrix2 = rotate90Matrix(rotate90Matrix(matrix2));
                            } else if (testIndex2 == 6){
                                matrix2 = rotate90Matrix(rotate90Matrix(flipMatrix(matrix2,1)));
                            } else if (testIndex2 == 7){
                                matrix2 = rotate90Matrix(rotate90Matrix(flipMatrix(matrix2,2)));
                            } else if (testIndex2 == 8){
                                matrix2 = rotate90Matrix(rotate90Matrix(flipMatrix(flipMatrix(matrix2,2),1)));
                            } else if (testIndex2 == 9){
                                matrix2 = rotate90Matrix(rotate90Matrix(rotate90Matrix(matrix2)));
                            }else if (testIndex2 == 10){
                                matrix2 = rotate90Matrix(rotate90Matrix(rotate90Matrix(flipMatrix(matrix2,1))));
                            }else if (testIndex2 == 11){
                                matrix2 = rotate90Matrix(rotate90Matrix(rotate90Matrix(flipMatrix(matrix2,2))));
                            }else if (testIndex2 == 12){
                                matrix2 = rotate90Matrix(rotate90Matrix(rotate90Matrix(flipMatrix(flipMatrix(matrix2,2),1))));
                            }
                            testIndex2++;

                            ifAdj = findIfAdjacent(matrix, matrix2);
                        }

                        if(ifAdj == 0){
                            if(leftIdAdj != index2 && bottomIdAdj != index2 && rightIdAdj != index2){
                                topIdAdj = index2;
                            }
                        } else if(ifAdj == 1){
                            if(topIdAdj != index2 && bottomIdAdj != index2 && rightIdAdj != index2){
                                leftIdAdj = index2;
                            }
                        } else if(ifAdj == 2){
                            if(topIdAdj != index2 && leftIdAdj != index2 && rightIdAdj != index2){
                                bottomIdAdj = index2;
                            }
                        } else if(ifAdj == 3){
                            if(topIdAdj != index2 && leftIdAdj != index2 && bottomIdAdj != index2){
                                rightIdAdj = index2;
                            }
                        }
                    }
                }

                testIndex++;
            }

            tiles.put(index, matrix);
            entryMap = tiles.entrySet();

            adjacentTable[i][0] = index;


            int count = 0;
            for(int y = 1; y < 5; y++){
                if(adjacentTable[i][y] == -1){
                    count++;
                }
            }

            if(count <= 2){
                adjacentTable[i][1] = topIdAdj;
                adjacentTable[i][2] = leftIdAdj;
                adjacentTable[i][3] = bottomIdAdj;
                adjacentTable[i][4] = rightIdAdj;
                i++;
            }
        }

        List<Integer> cornerId = new ArrayList<>();
        for(int x = 0; x < adjacentTable.length; x++){
            int count = 0;

            for(int y = 1; y < 5; y++){
                if(adjacentTable[x][y] == -1){
                    count++;
                }
            }

            if(count == 2){
                cornerId.add(adjacentTable[x][0]);
            }
        }

        printMatrixes(tiles);

        BigInteger response = BigInteger.valueOf(cornerId.get(0)).multiply(BigInteger.valueOf(cornerId.get(1)).multiply(BigInteger.valueOf(cornerId.get(2))).multiply(BigInteger.valueOf(cornerId.get(3))));
        System.out.println("response: " + response);
    }

    public static final String PUZZLE_TEST = "Tile 2311:\n" +
            "..##.#..#.\n" +
            "##..#.....\n" +
            "#...##..#.\n" +
            "####.#...#\n" +
            "##.##.###.\n" +
            "##...#.###\n" +
            ".#.#.#..##\n" +
            "..#....#..\n" +
            "###...#.#.\n" +
            "..###..###\n" +
            "\n" +
            "Tile 1951:\n" +
            "#.##...##.\n" +
            "#.####...#\n" +
            ".....#..##\n" +
            "#...######\n" +
            ".##.#....#\n" +
            ".###.#####\n" +
            "###.##.##.\n" +
            ".###....#.\n" +
            "..#.#..#.#\n" +
            "#...##.#..\n" +
            "\n" +
            "Tile 1171:\n" +
            "####...##.\n" +
            "#..##.#..#\n" +
            "##.#..#.#.\n" +
            ".###.####.\n" +
            "..###.####\n" +
            ".##....##.\n" +
            ".#...####.\n" +
            "#.##.####.\n" +
            "####..#...\n" +
            ".....##...\n" +
            "\n" +
            "Tile 1427:\n" +
            "###.##.#..\n" +
            ".#..#.##..\n" +
            ".#.##.#..#\n" +
            "#.#.#.##.#\n" +
            "....#...##\n" +
            "...##..##.\n" +
            "...#.#####\n" +
            ".#.####.#.\n" +
            "..#..###.#\n" +
            "..##.#..#.\n" +
            "\n" +
            "Tile 1489:\n" +
            "##.#.#....\n" +
            "..##...#..\n" +
            ".##..##...\n" +
            "..#...#...\n" +
            "#####...#.\n" +
            "#..#.#.#.#\n" +
            "...#.#.#..\n" +
            "##.#...##.\n" +
            "..##.##.##\n" +
            "###.##.#..\n" +
            "\n" +
            "Tile 2473:\n" +
            "#....####.\n" +
            "#..#.##...\n" +
            "#.##..#...\n" +
            "######.#.#\n" +
            ".#...#.#.#\n" +
            ".#########\n" +
            ".###.#..#.\n" +
            "########.#\n" +
            "##...##.#.\n" +
            "..###.#.#.\n" +
            "\n" +
            "Tile 2971:\n" +
            "..#.#....#\n" +
            "#...###...\n" +
            "#.#.###...\n" +
            "##.##..#..\n" +
            ".#####..##\n" +
            ".#..####.#\n" +
            "#..#.#..#.\n" +
            "..####.###\n" +
            "..#.#.###.\n" +
            "...#.#.#.#\n" +
            "\n" +
            "Tile 2729:\n" +
            "...#.#.#.#\n" +
            "####.#....\n" +
            "..#.#.....\n" +
            "....#..#.#\n" +
            ".##..##.#.\n" +
            ".#.####...\n" +
            "####.#.#..\n" +
            "##.####...\n" +
            "##..#.##..\n" +
            "#.##...##.\n" +
            "\n" +
            "Tile 3079:\n" +
            "#.#.#####.\n" +
            ".#..######\n" +
            "..#.......\n" +
            "######....\n" +
            "####.#..#.\n" +
            ".#...#.##.\n" +
            "#.#####.##\n" +
            "..#.###...\n" +
            "..#.......\n" +
            "..#.###...";
    public static final String PUZZLE_INPUT = "Tile 3391:\n" +
            "#.......##\n" +
            "#..#.....#\n" +
            "..#....#.#\n" +
            "##...##.##\n" +
            "#.##.#....\n" +
            ".#.......#\n" +
            ".#.......#\n" +
            "#..#..#..#\n" +
            ".......#.#\n" +
            ".##.#.####\n" +
            "\n" +
            "Tile 2549:\n" +
            ".#...##...\n" +
            "##.#......\n" +
            "....####..\n" +
            "######..##\n" +
            "...##..#.#\n" +
            "......##..\n" +
            ".##..#...#\n" +
            "...#....##\n" +
            "..#....#..\n" +
            ".#.#..##..\n" +
            "\n" +
            "Tile 1109:\n" +
            ".#.###..#.\n" +
            "##..#.#...\n" +
            "#...###..#\n" +
            "#...#..#.#\n" +
            ".......###\n" +
            "#.#....#.#\n" +
            ".#.#.#.###\n" +
            ".#.#.#....\n" +
            ".#....#...\n" +
            "..###.#...\n" +
            "\n" +
            "Tile 3023:\n" +
            "##.#.#.##.\n" +
            "...#..####\n" +
            ".##.#...##\n" +
            "#.##.....#\n" +
            "###.##...#\n" +
            ".......#..\n" +
            "...##..##.\n" +
            "##.....#..\n" +
            "###....#.#\n" +
            ".#...#.##.\n" +
            "\n" +
            "Tile 2833:\n" +
            ".#...#####\n" +
            "....#...##\n" +
            "....##...#\n" +
            "#...###...\n" +
            "#...###...\n" +
            "..#.#..#.#\n" +
            "##.....#.#\n" +
            "..#.......\n" +
            "##..##...#\n" +
            "###....##.\n" +
            "\n" +
            "Tile 2347:\n" +
            "#...###.##\n" +
            "#..##.##.#\n" +
            ".#..#.###.\n" +
            "....##..#.\n" +
            ".......#.#\n" +
            "#........#\n" +
            "##..#...##\n" +
            "..#......#\n" +
            "#...#.....\n" +
            "#....####.\n" +
            "\n" +
            "Tile 2311:\n" +
            "####..##..\n" +
            "#......##.\n" +
            "##..#.....\n" +
            "#..#..#..#\n" +
            "###.#..###\n" +
            ".#...#...#\n" +
            "....#.....\n" +
            "##...##..#\n" +
            "#....##...\n" +
            ".#...####.\n" +
            "\n" +
            "Tile 3137:\n" +
            "...#....#.\n" +
            "#..#.##...\n" +
            "#..#.....#\n" +
            "...#....#.\n" +
            "#..###.##.\n" +
            "..#..#.#.#\n" +
            "#........#\n" +
            "#.........\n" +
            "#.##....#.\n" +
            "#.#.#.#.#.\n" +
            "\n" +
            "Tile 2333:\n" +
            "#.###..#.#\n" +
            ".#....#..#\n" +
            "#....##..#\n" +
            "##....#.##\n" +
            "#..##.##..\n" +
            ".##..#####\n" +
            "#.....#..#\n" +
            "#.......#.\n" +
            ".#..#.###.\n" +
            "...#....##\n" +
            "\n" +
            "Tile 1439:\n" +
            "..###....#\n" +
            "##.#...#..\n" +
            "..##.#...#\n" +
            ".##....#..\n" +
            "#...#.....\n" +
            "#.#.#.....\n" +
            ".#.#....##\n" +
            ".......##.\n" +
            "....#.....\n" +
            "##.##.#..#\n" +
            "\n" +
            "Tile 1999:\n" +
            "....#.#.#.\n" +
            ".....#....\n" +
            "#...###...\n" +
            "...#..#...\n" +
            "....##.#..\n" +
            "#.....#...\n" +
            ".....#....\n" +
            "#..#.##...\n" +
            "##.......#\n" +
            "#.#....###\n" +
            "\n" +
            "Tile 2351:\n" +
            "#..#.###.#\n" +
            "##..##..##\n" +
            "#...#.....\n" +
            ".......###\n" +
            "#....#...#\n" +
            "##....#.#.\n" +
            "#...#.....\n" +
            ".#...#...#\n" +
            "#..#...#.#\n" +
            "##.#..#..#\n" +
            "\n" +
            "Tile 3547:\n" +
            "...#.#...#\n" +
            "###...#..#\n" +
            ".........#\n" +
            "#.#...#...\n" +
            "#.#..##..#\n" +
            ".#.####.#.\n" +
            "#..#.#.#.#\n" +
            ".##......#\n" +
            "#......#..\n" +
            ".###...###\n" +
            "\n" +
            "Tile 3877:\n" +
            "..##...##.\n" +
            "..#.....##\n" +
            ".#..#....#\n" +
            "##...#...#\n" +
            "##.#..#..#\n" +
            ".#..#....#\n" +
            "..##.#.#.#\n" +
            "......#.#.\n" +
            "#.....##..\n" +
            "####..#.##\n" +
            "\n" +
            "Tile 2309:\n" +
            ".##.#..#..\n" +
            "....#..#.#\n" +
            "..#.....##\n" +
            ".........#\n" +
            "#..#......\n" +
            "#........#\n" +
            "..##.#....\n" +
            "......#...\n" +
            "........##\n" +
            "#.##.###..\n" +
            "\n" +
            "Tile 1303:\n" +
            "#.##.####.\n" +
            ".......#..\n" +
            "...####...\n" +
            ".....#....\n" +
            "#......#.#\n" +
            "......#...\n" +
            ".#.#.....#\n" +
            "#........#\n" +
            "#.#..#.#.#\n" +
            "##.##.####\n" +
            "\n" +
            "Tile 3797:\n" +
            ".#.###..#.\n" +
            "#...##....\n" +
            "#.##.##...\n" +
            "#.##.....#\n" +
            "........##\n" +
            "#.#.......\n" +
            "#...##....\n" +
            "..........\n" +
            ".....#..##\n" +
            "#..##.....\n" +
            "\n" +
            "Tile 2161:\n" +
            ".#....##..\n" +
            "...#.....#\n" +
            "#.....#..#\n" +
            "#..#.#.#.#\n" +
            "..........\n" +
            "....#..#.#\n" +
            "##...#...#\n" +
            ".#..#....#\n" +
            "......#...\n" +
            "#.#..#.#..\n" +
            "\n" +
            "Tile 3463:\n" +
            "##.##..###\n" +
            "##.#..##..\n" +
            ".##..#.##.\n" +
            "..........\n" +
            "#.#...#..#\n" +
            "....#...##\n" +
            "#..#.....#\n" +
            "#.........\n" +
            ".......#..\n" +
            "#.#.#.####\n" +
            "\n" +
            "Tile 1657:\n" +
            "#.##.##.##\n" +
            ".....#....\n" +
            ".#.....#.#\n" +
            ".#......#.\n" +
            "#...##...#\n" +
            "##.####...\n" +
            "#.#...#...\n" +
            "#..##.....\n" +
            "#..###....\n" +
            "###.##...#\n" +
            "\n" +
            "Tile 2647:\n" +
            "##.##..##.\n" +
            "#..#......\n" +
            "##....####\n" +
            "#......#..\n" +
            ".#.#...#..\n" +
            ".#..##.#..\n" +
            "##...##...\n" +
            "#.##...#..\n" +
            "..#....#..\n" +
            "###...###.\n" +
            "\n" +
            "Tile 2557:\n" +
            ".###.#.#..\n" +
            "#.#...#.#.\n" +
            "#####....#\n" +
            "#..#.....#\n" +
            "..#.....#.\n" +
            "...#......\n" +
            "#....#....\n" +
            ".#..#....#\n" +
            "........#.\n" +
            ".#.#.#.##.\n" +
            "\n" +
            "Tile 2113:\n" +
            "###.#.....\n" +
            ".####.....\n" +
            ".....#...#\n" +
            ".#......#.\n" +
            "........#.\n" +
            "...#......\n" +
            "......#.#.\n" +
            "..........\n" +
            "..........\n" +
            "..#.#.####\n" +
            "\n" +
            "Tile 2729:\n" +
            "###...##..\n" +
            "...#....#.\n" +
            "#.#.......\n" +
            "....###.##\n" +
            "#..#..##..\n" +
            "..##..####\n" +
            "##....####\n" +
            "#...#.#..#\n" +
            "##...##..#\n" +
            "###.#.#..#\n" +
            "\n" +
            "Tile 2741:\n" +
            ".####..###\n" +
            ".#.#.#.#..\n" +
            ".##..##.##\n" +
            "#.##...#..\n" +
            "#..#.#.#..\n" +
            "#...#....#\n" +
            "#.........\n" +
            "##...#....\n" +
            "..........\n" +
            "#####.#...\n" +
            "\n" +
            "Tile 1901:\n" +
            "#.#..##..#\n" +
            "...#.#....\n" +
            ".##...#...\n" +
            "##........\n" +
            "..#.#..#..\n" +
            "...#.##..#\n" +
            "..#..###..\n" +
            "..##......\n" +
            ".#.#.....#\n" +
            "#.###.#..#\n" +
            "\n" +
            "Tile 2843:\n" +
            "#...##..##\n" +
            ".........#\n" +
            "##.###.#..\n" +
            ".........#\n" +
            "#..###....\n" +
            "..##......\n" +
            "##......##\n" +
            "..#..#####\n" +
            "##....##.#\n" +
            "##.##...##\n" +
            "\n" +
            "Tile 3229:\n" +
            "#...###.#.\n" +
            ".......#..\n" +
            "###...#.#.\n" +
            "#.......##\n" +
            "...#.#...#\n" +
            "#.##...#..\n" +
            "##.###...#\n" +
            "..#.......\n" +
            "....#.....\n" +
            "#....####.\n" +
            "\n" +
            "Tile 1289:\n" +
            "..#.###.##\n" +
            "......#.##\n" +
            "..##...#..\n" +
            "#.........\n" +
            "#..##.....\n" +
            "###....#..\n" +
            ".........#\n" +
            "...##..##.\n" +
            ".#...#....\n" +
            ".#.###...#\n" +
            "\n" +
            "Tile 2953:\n" +
            "..#..##...\n" +
            "...#.#..#.\n" +
            "........#.\n" +
            "#.#......#\n" +
            "###..#.##.\n" +
            "...##.#..#\n" +
            "#...#..##.\n" +
            ".......#.#\n" +
            ".........#\n" +
            "#.#.#.##.#\n" +
            "\n" +
            "Tile 1579:\n" +
            "..##......\n" +
            ".....#....\n" +
            "#..#.##..#\n" +
            ".#........\n" +
            ".....#....\n" +
            "#.......##\n" +
            "#..#......\n" +
            "#.........\n" +
            "...#..#.##\n" +
            "##....##.#\n" +
            "\n" +
            "Tile 3359:\n" +
            ".##..####.\n" +
            ".......#.#\n" +
            "....##...#\n" +
            ".........#\n" +
            "..........\n" +
            "....#....#\n" +
            "....#....#\n" +
            ".#.#.#..#.\n" +
            "...#.#....\n" +
            "#######...\n" +
            "\n" +
            "Tile 1823:\n" +
            "####..##.#\n" +
            ".........#\n" +
            "...##.....\n" +
            "..#.......\n" +
            ".#...##...\n" +
            "#..#......\n" +
            "..........\n" +
            "#....#...#\n" +
            "#...#.#..#\n" +
            "#.#.##....\n" +
            "\n" +
            "Tile 2659:\n" +
            "##.#.###.#\n" +
            "#......##.\n" +
            "..#.#....#\n" +
            "##...##...\n" +
            "#.#..#..##\n" +
            "#...#.....\n" +
            "...#.#....\n" +
            ".......#.#\n" +
            ".#..#..#..\n" +
            "....###..#\n" +
            "\n" +
            "Tile 1097:\n" +
            ".#.#.#....\n" +
            "##..#...##\n" +
            "###.....#.\n" +
            ".#.....###\n" +
            "##...##..#\n" +
            "...#.##...\n" +
            ".....#..##\n" +
            "#..#.#....\n" +
            ".#...#..##\n" +
            ".#.##..##.\n" +
            "\n" +
            "Tile 2341:\n" +
            "####.#...#\n" +
            "....#...##\n" +
            "#.##.....#\n" +
            "..#..###.#\n" +
            "...#.#.###\n" +
            "#...#....#\n" +
            ".##......#\n" +
            "#....#....\n" +
            "##....#.#.\n" +
            "#..#....#.\n" +
            "\n" +
            "Tile 2089:\n" +
            "#.###..##.\n" +
            "#..#####..\n" +
            "....##....\n" +
            "#.....#.##\n" +
            ".....###.#\n" +
            ".#..#.#..#\n" +
            "....#.#.##\n" +
            "#.......#.\n" +
            "###......#\n" +
            "###..####.\n" +
            "\n" +
            "Tile 1583:\n" +
            "#.#...#...\n" +
            "#....#.#.#\n" +
            ".........#\n" +
            "#.....#...\n" +
            "...#......\n" +
            "#........#\n" +
            "#......#..\n" +
            ".......#..\n" +
            ".........#\n" +
            "####...#.#\n" +
            "\n" +
            "Tile 3011:\n" +
            "##.####...\n" +
            ".##......#\n" +
            "...#.....#\n" +
            "#.......##\n" +
            "#..#......\n" +
            "##........\n" +
            "##..#...#.\n" +
            "##..#.....\n" +
            "#...##..#.\n" +
            ".###.#.#.#\n" +
            "\n" +
            "Tile 1481:\n" +
            "###.#.##..\n" +
            "..###...##\n" +
            "....##.#.#\n" +
            "..#......#\n" +
            "..#.#.....\n" +
            "..##......\n" +
            "#........#\n" +
            "......#...\n" +
            "#..#..#..#\n" +
            "#.#.######\n" +
            "\n" +
            "Tile 2927:\n" +
            "##..##.###\n" +
            "#.##.#...#\n" +
            "....#..#.#\n" +
            ".#....#.##\n" +
            "#...#.#.##\n" +
            "#.#.......\n" +
            "#.........\n" +
            "......#..#\n" +
            ".#.#.#...#\n" +
            "###...#.#.\n" +
            "\n" +
            "Tile 2633:\n" +
            ".##..##.#.\n" +
            ".#..#.####\n" +
            "..#..##..#\n" +
            "..#.#....#\n" +
            ".........#\n" +
            "..#.....##\n" +
            "........#.\n" +
            "##....#.##\n" +
            ".......##.\n" +
            "....#####.\n" +
            "\n" +
            "Tile 2371:\n" +
            "#..#.####.\n" +
            "....#.#...\n" +
            "..#...#..#\n" +
            "......#...\n" +
            "..#.##.#..\n" +
            ".#..##.#..\n" +
            "....#....#\n" +
            ".#.......#\n" +
            "#.#.......\n" +
            "#.##.#.#..\n" +
            "\n" +
            "Tile 1733:\n" +
            "#.########\n" +
            ".#.##..#.#\n" +
            "#.#..#....\n" +
            "..###.##..\n" +
            "#..##..##.\n" +
            ".##.#.....\n" +
            "#...#.#...\n" +
            "#..##..###\n" +
            "#........#\n" +
            "..#....#.#\n" +
            "\n" +
            "Tile 3583:\n" +
            "#.#.#.....\n" +
            "....#....#\n" +
            "...#.#....\n" +
            "....#.....\n" +
            "#........#\n" +
            "#.#..##..#\n" +
            ".........#\n" +
            "#....#....\n" +
            "#.....#...\n" +
            "...#..###.\n" +
            "\n" +
            "Tile 1291:\n" +
            ".##.###.##\n" +
            "#...#.#.#.\n" +
            "#.##.#.##.\n" +
            "....#..#.#\n" +
            "#.##.###..\n" +
            "#.##...#.#\n" +
            "....#.#.#.\n" +
            ".###.#...#\n" +
            ".#.#.....#\n" +
            "##..#..###\n" +
            "\n" +
            "Tile 3067:\n" +
            "#.#...##.#\n" +
            "#........#\n" +
            "...##.#.#.\n" +
            "#.##.....#\n" +
            "..#.......\n" +
            "#..#...#.#\n" +
            "#....#....\n" +
            "#.#...#...\n" +
            "..........\n" +
            "########.#\n" +
            "\n" +
            "Tile 1621:\n" +
            ".#..#.#..#\n" +
            "#...#..#..\n" +
            "..#.#.#..#\n" +
            "##..##...#\n" +
            "#.#.......\n" +
            "...#..#..#\n" +
            "##.#.....#\n" +
            "..#.#..#..\n" +
            "...##.##.#\n" +
            "#....#....\n" +
            "\n" +
            "Tile 3719:\n" +
            "#..###....\n" +
            "...##..##.\n" +
            "..###.....\n" +
            ".....#.###\n" +
            "##...#..##\n" +
            "#........#\n" +
            ".....#...#\n" +
            "#...#...#.\n" +
            "#.###.#...\n" +
            "#..#.#..##\n" +
            "\n" +
            "Tile 1217:\n" +
            "#.##..#.##\n" +
            "#.....#...\n" +
            "...#.#....\n" +
            ".#..#.##..\n" +
            "...##.#.##\n" +
            "#.#.#..#..\n" +
            "..........\n" +
            "#.......##\n" +
            ".........#\n" +
            "######..#.\n" +
            "\n" +
            "Tile 2503:\n" +
            "..##.#.#..\n" +
            "#....#....\n" +
            "#.........\n" +
            ".#...#.##.\n" +
            "...#.#....\n" +
            ".........#\n" +
            "..##......\n" +
            ".##.....#.\n" +
            ".#.....#..\n" +
            "###.##..#.\n" +
            "\n" +
            "Tile 2971:\n" +
            "#...###..#\n" +
            "#...#.#.##\n" +
            "#........#\n" +
            "#.#....#..\n" +
            "##........\n" +
            "#.....#..#\n" +
            ".#........\n" +
            ".#........\n" +
            ".#.###..##\n" +
            ".##...#..#\n" +
            "\n" +
            "Tile 2593:\n" +
            "#..#.####.\n" +
            "....#...#.\n" +
            "#.##.#....\n" +
            "......#.#.\n" +
            "........##\n" +
            "#.........\n" +
            "##.#......\n" +
            "#...#.#..#\n" +
            "..##...#..\n" +
            "...####.#.\n" +
            "\n" +
            "Tile 2711:\n" +
            "##..##...#\n" +
            "#....#...#\n" +
            ".........#\n" +
            "##....#.##\n" +
            "##......##\n" +
            "######....\n" +
            "#...#...##\n" +
            ".....#....\n" +
            "....#.#.#.\n" +
            "....##.#.#\n" +
            "\n" +
            "Tile 3929:\n" +
            "#.#.#..###\n" +
            "..#.###.#.\n" +
            ".....#...#\n" +
            ".....##.##\n" +
            "..#.#..##.\n" +
            "#........#\n" +
            "#.###..###\n" +
            "..#..#.#..\n" +
            ".##.....#.\n" +
            "#.#.#.#.##\n" +
            "\n" +
            "Tile 2473:\n" +
            "###....#..\n" +
            "..##...##.\n" +
            ".#...#...#\n" +
            ".#..#...#.\n" +
            "##......##\n" +
            "#.........\n" +
            "#....#..#.\n" +
            "##........\n" +
            "..##......\n" +
            "..#..#..##\n" +
            "\n" +
            "Tile 1103:\n" +
            ".#######..\n" +
            "#....#....\n" +
            ".......#..\n" +
            ".......#..\n" +
            "##.#.....#\n" +
            ".......#.#\n" +
            "#.....#...\n" +
            "#......#.#\n" +
            "#..#..#...\n" +
            "######..#.\n" +
            "\n" +
            "Tile 1907:\n" +
            "#..##.#..#\n" +
            "#..##.#..#\n" +
            ".##.#.#.#.\n" +
            "##########\n" +
            "....####..\n" +
            "##...#....\n" +
            "..##.#....\n" +
            "#...#....#\n" +
            "..#.####..\n" +
            ".#.##..###\n" +
            "\n" +
            "Tile 1553:\n" +
            "###.#...#.\n" +
            "#....#.#.#\n" +
            "..#......#\n" +
            "....##...#\n" +
            "......#..#\n" +
            "###...#.##\n" +
            "##...#....\n" +
            "..........\n" +
            "..#.......\n" +
            "###.#####.\n" +
            "\n" +
            "Tile 1637:\n" +
            "#####.##.#\n" +
            ".......#..\n" +
            "...#.#....\n" +
            "#......##.\n" +
            "#.###...##\n" +
            "..........\n" +
            "..#.###.#.\n" +
            "#....##..#\n" +
            "#..#.....#\n" +
            "#..##.#...\n" +
            "\n" +
            "Tile 2789:\n" +
            "##..####.#\n" +
            "..#.......\n" +
            "#.........\n" +
            ".......#..\n" +
            ".#........\n" +
            "..........\n" +
            "#....##...\n" +
            "####.....#\n" +
            "...##..#.#\n" +
            ".#.###.#..\n" +
            "\n" +
            "Tile 3301:\n" +
            "#.#....#..\n" +
            ".#.....###\n" +
            "##......##\n" +
            ".#.#......\n" +
            "#..#...#..\n" +
            "...#......\n" +
            ".#.....#.#\n" +
            "#........#\n" +
            "#.....#.#.\n" +
            ".###.#....\n" +
            "\n" +
            "Tile 2957:\n" +
            "#.#.##..#.\n" +
            "#..#......\n" +
            ".##...#.#.\n" +
            "..#.......\n" +
            "####......\n" +
            ".....#.#..\n" +
            "#.#......#\n" +
            "#...###...\n" +
            "#..#..##..\n" +
            "###..#.#..\n" +
            "\n" +
            "Tile 2389:\n" +
            "##.#..#...\n" +
            ".#.......#\n" +
            "..#.......\n" +
            "###....#.#\n" +
            ".#....#...\n" +
            "....#.....\n" +
            "....#..#.#\n" +
            "#......#.#\n" +
            "..#.##.##.\n" +
            "...###.##.\n" +
            "\n" +
            "Tile 1087:\n" +
            "##..##.###\n" +
            "#...#....#\n" +
            ".#.##.#.#.\n" +
            ".#.......#\n" +
            "#.#..#...#\n" +
            ".#..##...#\n" +
            "##.#..#.##\n" +
            ".#...#.#..\n" +
            "#........#\n" +
            "#..#...#..\n" +
            "\n" +
            "Tile 2251:\n" +
            "#..####...\n" +
            "#..##..#..\n" +
            "#..#.##...\n" +
            ".#.#.....#\n" +
            ".##.#.#...\n" +
            "#.##.....#\n" +
            "....##....\n" +
            ".........#\n" +
            "...#.....#\n" +
            "##.#.#..#.\n" +
            "\n" +
            "Tile 2969:\n" +
            "####.##..#\n" +
            "##........\n" +
            "...#...#.#\n" +
            ".......#..\n" +
            "###.######\n" +
            "..#..#....\n" +
            "...###...#\n" +
            "#......###\n" +
            ".#........\n" +
            "#.###...##\n" +
            "\n" +
            "Tile 1669:\n" +
            ".#...#.##.\n" +
            "#.##...#.#\n" +
            "#...#.....\n" +
            "#...##.#.#\n" +
            "#.....#...\n" +
            "...#.#.#..\n" +
            "...#.#....\n" +
            "..#.#.#..#\n" +
            "#.#.#.##.#\n" +
            "#..#.##..#\n" +
            "\n" +
            "Tile 2713:\n" +
            "#...#.#.##\n" +
            "...##..#.#\n" +
            ".#..#..###\n" +
            "..#.#.....\n" +
            "..#....#.#\n" +
            "..#...#...\n" +
            "#..#....##\n" +
            "##.....#.#\n" +
            "#....#....\n" +
            ".##.....##\n" +
            "\n" +
            "Tile 3659:\n" +
            "...#....#.\n" +
            "#.........\n" +
            "#..#.#.#.#\n" +
            "#..#..#.##\n" +
            ".#.#.#..##\n" +
            "#....#..##\n" +
            "..#......#\n" +
            "..#.....#.\n" +
            ".##.##.#..\n" +
            "####..#..#\n" +
            "\n" +
            "Tile 1609:\n" +
            "...#......\n" +
            ".......#..\n" +
            "##.....#.#\n" +
            "#...#..#..\n" +
            "....#.....\n" +
            "#.......##\n" +
            "#...##....\n" +
            "#..#..##.#\n" +
            "#####.###.\n" +
            "..##......\n" +
            "\n" +
            "Tile 1979:\n" +
            "#..#.#####\n" +
            ".....#.#..\n" +
            ".#....#.#.\n" +
            "###.###..#\n" +
            "..#....#.#\n" +
            "..#....#..\n" +
            "#....#.##.\n" +
            "....#..#..\n" +
            "....#.....\n" +
            ".#.#..#.##\n" +
            "\n" +
            "Tile 3457:\n" +
            "##.#.#..#.\n" +
            ".#......##\n" +
            "#.##..#..#\n" +
            "#.#..#....\n" +
            "..#..#....\n" +
            "##..#..#..\n" +
            ".........#\n" +
            ".......#.#\n" +
            ".........#\n" +
            "###.##.#..\n" +
            "\n" +
            "Tile 2531:\n" +
            "#..##...##\n" +
            "##....#..#\n" +
            "#.........\n" +
            ".....####.\n" +
            ".#.####..#\n" +
            "##..##.#.#\n" +
            "#.....#..#\n" +
            "#.....#..#\n" +
            ".....#.##.\n" +
            "#..#..##.#\n" +
            "\n" +
            "Tile 2267:\n" +
            "###..##..#\n" +
            "..#......#\n" +
            ".#..#....#\n" +
            "#..#...#..\n" +
            "......#..#\n" +
            "...#......\n" +
            "##.....#.#\n" +
            "##.#....##\n" +
            ".#.....#..\n" +
            "###.##..#.\n" +
            "\n" +
            "Tile 3853:\n" +
            "##.##.##.#\n" +
            ".....#..##\n" +
            "....#..#..\n" +
            ".#...#....\n" +
            ".........#\n" +
            "....#.....\n" +
            "#...#....#\n" +
            "#.........\n" +
            "#........#\n" +
            "#.#..####.\n" +
            "\n" +
            "Tile 3433:\n" +
            "#...#.#...\n" +
            "....#.##..\n" +
            "###..##.##\n" +
            "##.#...#..\n" +
            ".#.#.##.#.\n" +
            "......#..#\n" +
            "...##..##.\n" +
            "##....#..#\n" +
            "..#..##..#\n" +
            "###...####\n" +
            "\n" +
            "Tile 3109:\n" +
            "###.####.#\n" +
            ".#.#....##\n" +
            ".#...#....\n" +
            "..#...#..#\n" +
            ".#.#...#..\n" +
            "#.........\n" +
            "...#.##.#.\n" +
            ".......#..\n" +
            "..#..#....\n" +
            "#.##.#####\n" +
            "\n" +
            "Tile 2399:\n" +
            "####.##.##\n" +
            "...#.....#\n" +
            "..#.#.....\n" +
            ".#.##.....\n" +
            "...#.#...#\n" +
            "..#..#..#.\n" +
            "...#..#..#\n" +
            "#....#..#.\n" +
            "..........\n" +
            "#....#####\n" +
            "\n" +
            "Tile 2141:\n" +
            "#.#...#...\n" +
            "#........#\n" +
            ".#.#.##.#.\n" +
            "..#...##.#\n" +
            "..#......#\n" +
            ".........#\n" +
            "#...#...#.\n" +
            ".#.####..#\n" +
            "..#.....##\n" +
            "###..#.#.#\n" +
            "\n" +
            "Tile 3187:\n" +
            ".##.##.#.#\n" +
            "...#.##.##\n" +
            "##...#...#\n" +
            "......#..#\n" +
            ".##.#...##\n" +
            "..##...#..\n" +
            "#......#.#\n" +
            "...#..###.\n" +
            "#.#....#.#\n" +
            ".#..##.#.#\n" +
            "\n" +
            "Tile 3329:\n" +
            ".#.##..#..\n" +
            ".#........\n" +
            "#..#.....#\n" +
            "#.###..#.#\n" +
            "..#.#.....\n" +
            ".#..###..#\n" +
            "..#.....##\n" +
            "#.#..#.###\n" +
            "##.##..#.#\n" +
            "##..##.##.\n" +
            "\n" +
            "Tile 2693:\n" +
            "#..#####.#\n" +
            ".........#\n" +
            "##.#....##\n" +
            "#..#.....#\n" +
            "####.#....\n" +
            "..####.#.#\n" +
            "....##...#\n" +
            "##.#.....#\n" +
            "#.......#.\n" +
            ".....#####\n" +
            "\n" +
            "Tile 2357:\n" +
            ".#..##...#\n" +
            "......#.##\n" +
            "....#.##..\n" +
            "#.##.....#\n" +
            "..#.#...##\n" +
            "#........#\n" +
            "#..#......\n" +
            "#.........\n" +
            "....#.....\n" +
            "...##...##\n" +
            "\n" +
            "Tile 1381:\n" +
            ".#.....#.#\n" +
            "#.....####\n" +
            "#.........\n" +
            ".#.###.#..\n" +
            "##....#...\n" +
            "..........\n" +
            "#.#.#.#..#\n" +
            ".#####.#.#\n" +
            "#.......##\n" +
            "#.####....\n" +
            "\n" +
            "Tile 3761:\n" +
            "#....##...\n" +
            "#......#..\n" +
            "#...#...##\n" +
            ".........#\n" +
            "..........\n" +
            ".#.##.#.#.\n" +
            "...#....##\n" +
            "#...#...#.\n" +
            "#....#....\n" +
            ".###..####\n" +
            "\n" +
            "Tile 1889:\n" +
            "..#.#....#\n" +
            "..#..#....\n" +
            ".#..#....#\n" +
            "..........\n" +
            "..##..#.#.\n" +
            "..........\n" +
            ".....#.##.\n" +
            "#..#......\n" +
            "#......#..\n" +
            ".#...#..#.\n" +
            "\n" +
            "Tile 3511:\n" +
            "..##.....#\n" +
            ".#....#...\n" +
            "####.....#\n" +
            ".#...#...#\n" +
            "#..##.##..\n" +
            "#.#.....#.\n" +
            ".#.#......\n" +
            "#...#.....\n" +
            "#.#.......\n" +
            "###.###..#\n" +
            "\n" +
            "Tile 1619:\n" +
            "...###.##.\n" +
            "#...#.##.#\n" +
            "###.......\n" +
            "..#..##.##\n" +
            "..#.##....\n" +
            "..###....#\n" +
            "#......###\n" +
            "....###.##\n" +
            "..#....###\n" +
            "..##.##.##\n" +
            "\n" +
            "Tile 1721:\n" +
            "###....#..\n" +
            "......#.##\n" +
            ".#.....#.#\n" +
            "##.##..###\n" +
            ".#....#...\n" +
            "#...#..#.#\n" +
            "#.#.#....#\n" +
            "...#..#...\n" +
            "#.......##\n" +
            ".######.##\n" +
            "\n" +
            "Tile 3539:\n" +
            ".#.....#.#\n" +
            "...##...##\n" +
            "#.#...#...\n" +
            "..#.#....#\n" +
            "#..#....##\n" +
            "#.#..##..#\n" +
            "##..#..#.#\n" +
            ".....#.#.#\n" +
            ".#..#.#.##\n" +
            "..#...###.\n" +
            "\n" +
            "Tile 3847:\n" +
            "##..###..#\n" +
            "#..##..#.#\n" +
            ".......##.\n" +
            ".#.#.....#\n" +
            "###...#...\n" +
            ".##.##...#\n" +
            "....#.#...\n" +
            "..#..##..#\n" +
            "#......#..\n" +
            "####..###.\n" +
            "\n" +
            "Tile 3257:\n" +
            ".##.#.#...\n" +
            "###...#.#.\n" +
            "..........\n" +
            "..........\n" +
            "..#...#..#\n" +
            ".........#\n" +
            "#.....#..#\n" +
            "...#....#.\n" +
            "#..##.#.##\n" +
            "####...###\n" +
            "\n" +
            "Tile 3851:\n" +
            "#.#.####..\n" +
            "#.#....#.#\n" +
            "#.........\n" +
            "####.#...#\n" +
            ".###.....#\n" +
            "##..#..#.#\n" +
            ".#.###....\n" +
            ".........#\n" +
            "####.....#\n" +
            ".#.#..#.##\n" +
            "\n" +
            "Tile 3881:\n" +
            "....#..#.#\n" +
            "#......#..\n" +
            "..........\n" +
            "#....#....\n" +
            "#.#.#.#..#\n" +
            "....#..#..\n" +
            ".....#..##\n" +
            "#.#...#.#.\n" +
            "....#.....\n" +
            "..#.#..###\n" +
            "\n" +
            "Tile 2539:\n" +
            "#.####.#.#\n" +
            "...#.###.#\n" +
            "####...#..\n" +
            "#..#...###\n" +
            "......#.#.\n" +
            "#..#.....#\n" +
            "#....#..##\n" +
            "...#...#..\n" +
            "#.#....#.#\n" +
            ".##.##.#.#\n" +
            "\n" +
            "Tile 1301:\n" +
            "##.##..#.#\n" +
            ".......#..\n" +
            "#..#.#.#.#\n" +
            "#...#.....\n" +
            "#####.#...\n" +
            "...#.#....\n" +
            "..#....#.#\n" +
            "...#..#..#\n" +
            "#...#...##\n" +
            ".###.###..\n" +
            "\n" +
            "Tile 2027:\n" +
            "##..###.#.\n" +
            "##..#.....\n" +
            "#.#..#....\n" +
            ".##...#...\n" +
            "..##.....#\n" +
            "#......#.#\n" +
            "#.#.....#.\n" +
            "##..#....#\n" +
            "..........\n" +
            "#..##.#...\n" +
            "\n" +
            "Tile 3001:\n" +
            ".#...#...#\n" +
            "..#.#...#.\n" +
            "...#.##...\n" +
            "#.........\n" +
            ".#...#....\n" +
            "#.......##\n" +
            "##....##.#\n" +
            "##.#....##\n" +
            "...#.....#\n" +
            "#...######\n" +
            "\n" +
            "Tile 3767:\n" +
            "#..#.#...#\n" +
            "##..#....#\n" +
            "...###...#\n" +
            ".....#.#..\n" +
            "........#.\n" +
            ".#...#....\n" +
            "#..##....#\n" +
            "..........\n" +
            "##..#..#.#\n" +
            ".#......##\n" +
            "\n" +
            "Tile 1433:\n" +
            ".##.#.##.#\n" +
            "#...#.....\n" +
            "......##.#\n" +
            "##.#....#.\n" +
            ".#.##...##\n" +
            "#..#.##..#\n" +
            ".#.##...##\n" +
            "#...##.#.#\n" +
            "...#...#.#\n" +
            "..#.....#.\n" +
            "\n" +
            "Tile 1931:\n" +
            "##.####.#.\n" +
            "#.##..#..#\n" +
            "#....#...#\n" +
            "#....#....\n" +
            "...####...\n" +
            "...#......\n" +
            "#........#\n" +
            "####..#...\n" +
            "...###....\n" +
            ".#..#.#...\n" +
            "\n" +
            "Tile 3491:\n" +
            ".##..#.#.#\n" +
            "##...#...#\n" +
            "#..#......\n" +
            "#.#.#....#\n" +
            "........#.\n" +
            "#.#......#\n" +
            "##........\n" +
            ".#.......#\n" +
            ".##......#\n" +
            "####.....#\n" +
            "\n" +
            "Tile 2683:\n" +
            "...#....##\n" +
            "...#.##.#.\n" +
            "..##.#....\n" +
            "....##...#\n" +
            "..#.##..#.\n" +
            "...##.#.##\n" +
            "#..#.#...#\n" +
            "..#..#....\n" +
            "#...#..#..\n" +
            "#..#####..\n" +
            "\n" +
            "Tile 3323:\n" +
            ".....#.#.#\n" +
            "###.......\n" +
            "....#.#...\n" +
            "..#......#\n" +
            ".##..#....\n" +
            "#.....###.\n" +
            ".#....##..\n" +
            ".....#...#\n" +
            "....#..#.#\n" +
            ".....###..\n" +
            "\n" +
            "Tile 1061:\n" +
            "#....###..\n" +
            "#.#.#.#...\n" +
            "#......#..\n" +
            "#...##...#\n" +
            "#........#\n" +
            ".....#.###\n" +
            "##....#..#\n" +
            "....###..#\n" +
            "###....#..\n" +
            "..##.##...\n" +
            "\n" +
            "Tile 1163:\n" +
            "####.###.#\n" +
            "#..#.....#\n" +
            "#........#\n" +
            "#...##...#\n" +
            ".##..#.#..\n" +
            "..#.#.#...\n" +
            ".....#.#.#\n" +
            "##........\n" +
            ".#....#.#.\n" +
            "###..#####\n" +
            "\n" +
            "Tile 3779:\n" +
            ".#####.#..\n" +
            "####.##.#.\n" +
            "##...##..#\n" +
            "#.###...#.\n" +
            "..#.......\n" +
            "#........#\n" +
            "...#...#.#\n" +
            ".#.#......\n" +
            "#...#.#...\n" +
            ".#.#..##.#\n" +
            "\n" +
            "Tile 1993:\n" +
            "#..##.#.##\n" +
            ".#...#...#\n" +
            "#..###....\n" +
            "#.##.....#\n" +
            "#.....##.#\n" +
            "#...#.#..#\n" +
            "#.#.#.....\n" +
            ".#.#.#...#\n" +
            "....#.#.#.\n" +
            "##......#.\n" +
            "\n" +
            "Tile 1213:\n" +
            "#..##..#..\n" +
            "..##..#..#\n" +
            "#.........\n" +
            ".##.....#.\n" +
            "#.........\n" +
            "........##\n" +
            "........#.\n" +
            "..#.###..#\n" +
            "##.......#\n" +
            "..##...#.#\n" +
            "\n" +
            "Tile 3373:\n" +
            "#.##..#.##\n" +
            "#.........\n" +
            ".....#....\n" +
            "#...#.....\n" +
            "#...##...#\n" +
            "..#..#....\n" +
            "#.........\n" +
            "..........\n" +
            "#...##..#.\n" +
            "..##.###.#\n" +
            "\n" +
            "Tile 2017:\n" +
            "#...#.##.#\n" +
            "#...##....\n" +
            "........##\n" +
            "...#...#.#\n" +
            "..........\n" +
            "##...##..#\n" +
            ".....#...#\n" +
            "#....#.#.#\n" +
            "#........#\n" +
            "#.##...#..\n" +
            "\n" +
            "Tile 1367:\n" +
            "..###.####\n" +
            "#..#.#..#.\n" +
            "#...##..#.\n" +
            ".#.....#.#\n" +
            "##......##\n" +
            ".#.#.....#\n" +
            ".....#....\n" +
            "........##\n" +
            "#...##...#\n" +
            "###.#...##\n" +
            "\n" +
            "Tile 2417:\n" +
            ".#.#.#.#..\n" +
            "#.##..#.##\n" +
            "#.#......#\n" +
            "###.......\n" +
            "#..#.....#\n" +
            "...#.....#\n" +
            "..#.......\n" +
            "#.#.#....#\n" +
            ".#...#....\n" +
            "#..##.....\n" +
            "\n" +
            "Tile 1871:\n" +
            "##..#.##.#\n" +
            ".#.#..####\n" +
            "..#...###.\n" +
            ".##.......\n" +
            "....##..##\n" +
            "..#....#.#\n" +
            "###.......\n" +
            ".#....#.##\n" +
            ".#.#...#.#\n" +
            "#.###..#..\n" +
            "\n" +
            "Tile 1601:\n" +
            "#####.....\n" +
            ".....#....\n" +
            "......#..#\n" +
            ".#..######\n" +
            "#..#.##..#\n" +
            "....#.#...\n" +
            "#.#.......\n" +
            "#.#..##..#\n" +
            "#..##.#.#.\n" +
            "..#..##.##\n" +
            "\n" +
            "Tile 1471:\n" +
            ".#######..\n" +
            "..#..#...#\n" +
            ".##..#...#\n" +
            "##..#.#..#\n" +
            "......#..#\n" +
            ".#...#....\n" +
            "#........#\n" +
            "..#......#\n" +
            ".....##..#\n" +
            "###.#....#\n" +
            "\n" +
            "Tile 2521:\n" +
            "###.....##\n" +
            "###.##.##.\n" +
            "......##.#\n" +
            "#...#.#...\n" +
            "........##\n" +
            "##.####...\n" +
            "...###....\n" +
            "#..#.#...#\n" +
            "#..####...\n" +
            "#.#.#....#\n" +
            "\n" +
            "Tile 1279:\n" +
            "##..#..#.#\n" +
            ".#...#.#.#\n" +
            "#.......#.\n" +
            "#.#.#..#..\n" +
            "#.#......#\n" +
            "#......###\n" +
            "#..#..#..#\n" +
            "#.#.#...#.\n" +
            "#...#....#\n" +
            "..#......#\n" +
            "\n" +
            "Tile 1069:\n" +
            "#.#.#...##\n" +
            "......#.##\n" +
            "#....#.##.\n" +
            "......#..#\n" +
            "##.#.#...#\n" +
            "#.#.#.#.##\n" +
            "#.......#.\n" +
            "##..#....#\n" +
            "......#...\n" +
            ".##..#..##\n" +
            "\n" +
            "Tile 2143:\n" +
            "#.#...#.#.\n" +
            "#.........\n" +
            "..#...#...\n" +
            ".#..##....\n" +
            "#......#.#\n" +
            ".....##..#\n" +
            "#.#.....##\n" +
            "#.........\n" +
            "#....#....\n" +
            "##....#.#.\n" +
            "\n" +
            "Tile 3517:\n" +
            "####.#...#\n" +
            "#.#..#...#\n" +
            "#......#.#\n" +
            "#..#......\n" +
            "##....#..#\n" +
            "#......#..\n" +
            "..#...#...\n" +
            "#.....#...\n" +
            "....#.###.\n" +
            "##...#.#..\n" +
            "\n" +
            "Tile 2543:\n" +
            ".#######.#\n" +
            ".....#....\n" +
            "#.#.#....#\n" +
            "........#.\n" +
            "##...#....\n" +
            "..#.....##\n" +
            "..#.#.##.#\n" +
            "........##\n" +
            "#.......#.\n" +
            "#.####.###\n" +
            "\n" +
            "Tile 1567:\n" +
            "...#.##...\n" +
            "...#...###\n" +
            ".....#....\n" +
            "....#....#\n" +
            "#.....#.#.\n" +
            ".###....#.\n" +
            "#.##..###.\n" +
            "........##\n" +
            "........#.\n" +
            "#####.#.##\n" +
            "\n" +
            "Tile 1459:\n" +
            "..#...###.\n" +
            "##..##...#\n" +
            ".##.......\n" +
            "..###.#...\n" +
            "##.......#\n" +
            "....#..##.\n" +
            "##....#.##\n" +
            "......#...\n" +
            "....#.....\n" +
            ".#.#.#.###\n" +
            "\n" +
            "Tile 1153:\n" +
            ".##...###.\n" +
            "#.#..#.#..\n" +
            "..........\n" +
            "..........\n" +
            "...#.#..#.\n" +
            "..#......#\n" +
            "#.#..##...\n" +
            "..##....#.\n" +
            "#.........\n" +
            "#.#....###\n" +
            "\n" +
            "Tile 1811:\n" +
            "###.#..#.#\n" +
            "#..##....#\n" +
            "#.##..#...\n" +
            "...#...###\n" +
            "......#...\n" +
            "..#....##.\n" +
            "##.......#\n" +
            "..#......#\n" +
            ".....#...#\n" +
            ".....#....\n" +
            "\n" +
            "Tile 1667:\n" +
            ".#.#.##.##\n" +
            ".....#.#.#\n" +
            "....##....\n" +
            "....#....#\n" +
            ".###..#..#\n" +
            ".#.####.##\n" +
            "#..##.....\n" +
            "......##.#\n" +
            "#.#...##..\n" +
            "#####..###\n" +
            "\n" +
            "Tile 2677:\n" +
            ".###...#.#\n" +
            "#..###...#\n" +
            "#....###..\n" +
            "..........\n" +
            "##.......#\n" +
            "##.#....#.\n" +
            "###..##.#.\n" +
            "#.#.......\n" +
            ".#......#.\n" +
            "##.#..#...\n" +
            "\n" +
            "Tile 2707:\n" +
            "#..#.#.##.\n" +
            "#.....####\n" +
            "....#...#.\n" +
            "##..#.....\n" +
            "##........\n" +
            "..#...#...\n" +
            "....#.....\n" +
            "#....#.#.#\n" +
            "..#..#.#..\n" +
            "####...#..\n" +
            "\n" +
            "Tile 1699:\n" +
            ".###....##\n" +
            "..#..#...#\n" +
            ".###......\n" +
            ".....#...#\n" +
            "#.#.....##\n" +
            "#.##..#...\n" +
            ".#.......#\n" +
            "#......#.#\n" +
            "###.....##\n" +
            ".##.......\n" +
            "\n" +
            "Tile 3919:\n" +
            "..#.#..#.#\n" +
            "#.#...#..#\n" +
            "##.#..#.#.\n" +
            ".#..#..#..\n" +
            "#.#.#.#.#.\n" +
            "#...##...#\n" +
            "#...##....\n" +
            "#...#.#...\n" +
            "...#...###\n" +
            "##.#.#..##\n" +
            "\n" +
            "Tile 1019:\n" +
            "#..#..#.##\n" +
            "#....#..##\n" +
            "#......##.\n" +
            "#.#....#..\n" +
            "##....#...\n" +
            "#....##..#\n" +
            "....#.....\n" +
            ".#..#.#...\n" +
            "......##..\n" +
            "...#...#..\n" +
            "\n" +
            "Tile 3319:\n" +
            "..#...#.#.\n" +
            ".##.......\n" +
            "...#.#...#\n" +
            "........#.\n" +
            "#.....##..\n" +
            "...##....#\n" +
            "..##...##.\n" +
            ".....#..##\n" +
            "..###.....\n" +
            "#...####..\n" +
            "\n" +
            "Tile 3041:\n" +
            "##..#####.\n" +
            "......#..#\n" +
            "..#..##..#\n" +
            "..........\n" +
            "#.....#.##\n" +
            "#......#..\n" +
            ".....#..#.\n" +
            "..##.....#\n" +
            ".#.......#\n" +
            "........##\n" +
            "\n" +
            "Tile 3313:\n" +
            ".##.#..###\n" +
            "#.#..###..\n" +
            "#....#.#..\n" +
            "##........\n" +
            "...#.....#\n" +
            "#.........\n" +
            ".#.....#..\n" +
            "#.....#...\n" +
            "#.#.#...#.\n" +
            "#.....##..\n" +
            "\n" +
            "Tile 3541:\n" +
            "#.#.......\n" +
            ".##......#\n" +
            "#.........\n" +
            "##.......#\n" +
            ".#......##\n" +
            "..#..#....\n" +
            "....#.#.##\n" +
            ".#.......#\n" +
            "##.......#\n" +
            "#.#..##..#\n" +
            "\n" +
            "Tile 1319:\n" +
            "#.#...#..#\n" +
            "#......##.\n" +
            "..#.#.####\n" +
            "#.........\n" +
            "#..#.....#\n" +
            "..#...##.#\n" +
            "#...#.##.#\n" +
            "#..#....##\n" +
            "....##....\n" +
            ".#.#.#.###\n" +
            "\n" +
            "Tile 1499:\n" +
            "####...#..\n" +
            "#...#....#\n" +
            ".....#...#\n" +
            ".......#..\n" +
            "#.###....#\n" +
            ".......###\n" +
            ".......##.\n" +
            ".......#.#\n" +
            "..#.#...#.\n" +
            "......#.#.\n" +
            "\n" +
            "Tile 2239:\n" +
            "..#....##.\n" +
            ".####....#\n" +
            "...#.....#\n" +
            "......#...\n" +
            ".....#..#.\n" +
            "#..#.....#\n" +
            "#.#.####..\n" +
            "###.##....\n" +
            ".........#\n" +
            "...#...#..\n" +
            "\n" +
            "Tile 3769:\n" +
            "####.####.\n" +
            "#....#.#.#\n" +
            ".##.......\n" +
            "###.....##\n" +
            "##.#...#.#\n" +
            "....#.#..#\n" +
            "..........\n" +
            "#.#.#.....\n" +
            "##.......#\n" +
            ".#####.###\n" +
            "\n" +
            "Tile 1187:\n" +
            "#..#.#....\n" +
            "#.......#.\n" +
            "...#....##\n" +
            "#..##....#\n" +
            "#.....#.##\n" +
            "...##.####\n" +
            "#....#....\n" +
            ".....#.#..\n" +
            "#.##.#...#\n" +
            "#..#.##.##\n" +
            "\n" +
            "Tile 1913:\n" +
            "#.##.#.##.\n" +
            "##.#...#.#\n" +
            "#.#...#...\n" +
            "##.#.....#\n" +
            "#.##......\n" +
            "#.###....#\n" +
            "..#..#.##.\n" +
            "#.........\n" +
            "#.#......#\n" +
            "...#.###.#\n" +
            "\n" +
            "Tile 1753:\n" +
            "..#..#....\n" +
            ".......#.#\n" +
            "...#.#.#.#\n" +
            "#.#....#..\n" +
            "#......#.#\n" +
            "##...#....\n" +
            "#.#.....##\n" +
            "#..#...##.\n" +
            ".##.#.#..#\n" +
            "##.###.##.\n" +
            "\n";
}
