package com.advco;

import java.util.*;

public class Day17 {
    public static void main(String[] args) {
        partTwo();
    }

    public static void partOne(){
        String[] arrayInput = PUZZLE_INPUT.split("\n");
        HashMap<String, Boolean> coordinates = new HashMap<>();

        int xSize = arrayInput.length;
        int ySize = arrayInput.length;
        int zSize = 1;

        int lowestX = 0;
        int lowestY = 0;
        int lowestZ = 0;

        int numberOfCycle = 6;

        for(int i = 0; i < arrayInput.length; i++){
            for(int j = 0; j < arrayInput.length; j++){
                boolean state = arrayInput[i].charAt(j) == '#' ? true : false;
                coordinates.put("x" + i + "y" + j +"z0", state);
            }
        }

        for(int k = 0; k < zSize; k++){
            System.out.println("z = " + k);
            for(int i = 0; i < xSize; i++){
                for(int j = 0; j < ySize; j++){
                    String toPrint = coordinates.get("x"+ i + "y" + j + "z" + k) == true ? "#" : ".";
                    System.out.print(toPrint);
                }
                System.out.println();
            }

            System.out.println();
            System.out.println();
        }



        for(int u = 0; u < numberOfCycle; u++){
            System.out.println("After " + (u+1) + " cycle:");

            /*---- Add a layer before continuing ---- */
            lowestX--;
            lowestY--;
            lowestZ--;
            xSize++;
            ySize++;
            zSize++;

            for(int y = lowestY; y < ySize; y++){
                for(int z = lowestZ; z < zSize; z++){
                    coordinates.put("x" + (lowestX) + "y" + y + "z" + z, false);
                    coordinates.put("x" + (xSize-1) + "y" + y + "z" + z, false);
                }
            }
            for(int x = lowestX; x < xSize; x++){
                for(int z = lowestZ; z < zSize; z++){
                    coordinates.put("x" + x + "y" + (lowestY) + "z" + z, false);
                    coordinates.put("x" + x + "y" + (ySize-1) + "z" + z, false);
                }
            }
            for(int x = lowestX; x < xSize; x++){
                for(int y = lowestY; y < ySize; y++){
                    coordinates.put("x" + x + "y" + y + "z" + (lowestZ), false);
                    coordinates.put("x" + x + "y" + y + "z" + (zSize-1), false);
                }
            }

            /*-------- */

            HashMap<String, Boolean> tempMap = (HashMap<String, Boolean>)coordinates.clone();

            Set<Map.Entry<String, Boolean>> entrySet = coordinates.entrySet();
            Iterator it = entrySet.iterator();
            while(it.hasNext()){
                Map.Entry<String, Boolean> entry = (Map.Entry)it.next();

                int xValue = Integer.parseInt(entry.getKey().substring(entry.getKey().indexOf("x")+1, entry.getKey().indexOf("y")));
                int yValue = Integer.parseInt(entry.getKey().substring(entry.getKey().indexOf("y")+1, entry.getKey().indexOf("z")));
                int zValue = Integer.parseInt(entry.getKey().substring(entry.getKey().indexOf("z")+1));

                int nbOfActiveNeighbors = 0;
                for(int x = xValue-1; x < xValue+2; x++){
                    for(int y = yValue-1; y < yValue+2; y++){
                        for(int z = zValue-1; z < zValue+2; z++){
                            if(coordinates.get("x" + x + "y" + y + "z" + z) != null){
                                if((x != xValue || y != yValue || z != zValue) && coordinates.get("x" + x + "y" + y + "z" + z) == true){
                                    nbOfActiveNeighbors++;
                                }
                            }
                        }
                    }
                }

                if(entry.getValue() == true){
                    if(nbOfActiveNeighbors == 3 || nbOfActiveNeighbors == 2){
                        tempMap.put("x" + xValue + "y" + yValue + "z" + zValue, true);
                    } else{
                        tempMap.put("x" + xValue + "y" + yValue + "z" + zValue, false);
                    }
                } else{
                    if(nbOfActiveNeighbors == 3){
                        tempMap.put("x" + xValue + "y" + yValue + "z" + zValue, true);
                    } else{
                        tempMap.put("x" + xValue + "y" + yValue + "z" + zValue, false);
                    }
                }

            }

            coordinates = (HashMap<String, Boolean>) tempMap.clone();
            /*---- New values print ----*/
            for(int k = lowestZ; k < zSize; k++){
                System.out.println("z = " + k);
                for(int i = lowestX; i < xSize; i++){
                    for(int j = lowestY; j < ySize; j++){
                        if(coordinates.get("x" + i + "y" + j + "z" + k) != null) {
                            String toPrint = coordinates.get("x" + i + "y" + j + "z" + k) == true ? "#" : ".";
                            System.out.print(toPrint);
                        }
                    }
                    System.out.println();
                }

                System.out.println();
                System.out.println();
            }
            /*---- ----*/

        }

        int nbOfCubesInActiveState = 0;

        for(int k = lowestZ; k < zSize; k++){
            for(int i = lowestX; i < xSize; i++){
                for(int j = lowestY; j < ySize; j++){
                    if(coordinates.get("x" + i + "y" + j + "z" + k) != null) {
                        if(coordinates.get("x" + i + "y" + j + "z" + k) == true){
                            nbOfCubesInActiveState++;
                        }
                    }
                }
            }
        }

        System.out.println("nbOfCubesInActiveState: " + nbOfCubesInActiveState);


    }

    public static void partTwo(){
        String[] arrayInput = PUZZLE_INPUT.split("\n");
        HashMap<String, Boolean> coordinates = new HashMap<>();

        int xSize = arrayInput.length;
        int ySize = arrayInput.length;
        int zSize = 1;
        int wSize = 1;

        int lowestX = 0;
        int lowestY = 0;
        int lowestZ = 0;
        int lowestW = 0;

        int numberOfCycle = 6;

        for(int i = 0; i < arrayInput.length; i++){
            for(int j = 0; j < arrayInput.length; j++){
                boolean state = arrayInput[i].charAt(j) == '#' ? true : false;
                coordinates.put("x" + i + "y" + j +"z0" + "w0", state);
            }
        }

        /*for(int k = 0; k < zSize; k++){
            System.out.println("z = " + k);
            for(int i = 0; i < xSize; i++){
                for(int j = 0; j < ySize; j++){
                    String toPrint = coordinates.get("x"+ i + "y" + j + "z" + k) == true ? "#" : ".";
                    System.out.print(toPrint);
                }
                System.out.println();
            }

            System.out.println();
            System.out.println();
        }*/



        for(int u = 0; u < numberOfCycle; u++){
            System.out.println("After " + (u+1) + " cycle:");

            /*---- Add a layer before continuing ---- */
            lowestX--;
            lowestY--;
            lowestZ--;
            lowestW--;
            xSize++;
            ySize++;
            zSize++;
            wSize++;

            for(int y = lowestY; y < ySize; y++){
                for(int z = lowestZ; z < zSize; z++){
                    for(int w = lowestW; w < wSize; w++){
                        coordinates.put("x" + (lowestX) + "y" + y + "z" + z + "w" + w, false);
                        coordinates.put("x" + (xSize-1) + "y" + y + "z" + z + "w" + w, false);
                    }
                }
            }
            for(int x = lowestX; x < xSize; x++){
                for(int z = lowestZ; z < zSize; z++){
                    for(int w = lowestW; w < wSize; w++){
                        coordinates.put("x" + x + "y" + (lowestY) + "z" + z + "w" + w, false);
                        coordinates.put("x" + x + "y" + (ySize-1) + "z" + z + "w" + w, false);
                    }
                }
            }
            for(int x = lowestX; x < xSize; x++){
                for(int y = lowestY; y < ySize; y++){
                    for(int w = lowestW; w < wSize; w++){
                        coordinates.put("x" + x + "y" + y + "z" + (lowestZ) + "w" + w, false);
                        coordinates.put("x" + x + "y" + y + "z" + (zSize-1) + "w" + w, false);
                    }
                }
            }
            for(int x = lowestX; x < xSize; x++){
                for(int y = lowestY; y < ySize; y++){
                    for(int z = lowestZ; z < zSize; z++){
                        coordinates.put("x" + x + "y" + y + "z" + z + "w" + (lowestW), false);
                        coordinates.put("x" + x + "y" + y + "z" + z + "w" + (wSize-1), false);
                    }
                }
            }

            /*-------- */

            HashMap<String, Boolean> tempMap = (HashMap<String, Boolean>)coordinates.clone();

            Set<Map.Entry<String, Boolean>> entrySet = coordinates.entrySet();
            Iterator it = entrySet.iterator();
            while(it.hasNext()){
                Map.Entry<String, Boolean> entry = (Map.Entry)it.next();

                int xValue = Integer.parseInt(entry.getKey().substring(entry.getKey().indexOf("x")+1, entry.getKey().indexOf("y")));
                int yValue = Integer.parseInt(entry.getKey().substring(entry.getKey().indexOf("y")+1, entry.getKey().indexOf("z")));
                int zValue = Integer.parseInt(entry.getKey().substring(entry.getKey().indexOf("z")+1, entry.getKey().indexOf("w")));
                int wValue = Integer.parseInt(entry.getKey().substring(entry.getKey().indexOf("w")+1));

                int nbOfActiveNeighbors = 0;
                for(int x = xValue-1; x < xValue+2; x++){
                    for(int y = yValue-1; y < yValue+2; y++){
                        for(int z = zValue-1; z < zValue+2; z++){
                            for(int w = wValue-1; w < wValue+2; w++){
                                if(coordinates.get("x" + x + "y" + y + "z" + z + "w" + w) != null){
                                    if((x != xValue || y != yValue || z != zValue || w != wValue) && coordinates.get("x" + x + "y" + y + "z" + z + "w" + w) == true){
                                        nbOfActiveNeighbors++;
                                    }
                                }
                            }
                        }
                    }
                }

                if(entry.getValue() == true){
                    if(nbOfActiveNeighbors == 3 || nbOfActiveNeighbors == 2){
                        tempMap.put("x" + xValue + "y" + yValue + "z" + zValue + "w" + wValue, true);
                    } else{
                        tempMap.put("x" + xValue + "y" + yValue + "z" + zValue + "w" + wValue, false);
                    }
                } else{
                    if(nbOfActiveNeighbors == 3){
                        tempMap.put("x" + xValue + "y" + yValue + "z" + zValue + "w" + wValue, true);
                    } else{
                        tempMap.put("x" + xValue + "y" + yValue + "z" + zValue + "w" + wValue, false);
                    }
                }

            }

            coordinates = (HashMap<String, Boolean>) tempMap.clone();
            /*---- New values print ----*/
            for(int k = lowestZ; k < zSize; k++){
                System.out.println("z = " + k);
                for(int i = lowestX; i < xSize; i++){
                    for(int j = lowestY; j < ySize; j++){
                        if(coordinates.get("x" + i + "y" + j + "z" + k) != null) {
                            String toPrint = coordinates.get("x" + i + "y" + j + "z" + k) == true ? "#" : ".";
                            System.out.print(toPrint);
                        }
                    }
                    System.out.println();
                }

                System.out.println();
                System.out.println();
            }
            /*---- ----*/

        }

        int nbOfCubesInActiveState = 0;

        for(int k = lowestZ; k < zSize; k++){
            for(int i = lowestX; i < xSize; i++){
                for(int j = lowestY; j < ySize; j++){
                    for(int w = lowestW; w < wSize; w++){
                        if(coordinates.get("x" + i + "y" + j + "z" + k + "w" + w) != null) {
                            if(coordinates.get("x" + i + "y" + j + "z" + k + "w" + w) == true){
                                nbOfCubesInActiveState++;
                            }
                        }
                    }
                }
            }
        }

        System.out.println("nbOfCubesInActiveState: " + nbOfCubesInActiveState);
    }

    public static final String PUZZLE_TEST = ".#.\n" +
            "..#\n" +
            "###";
    public static final String PUZZLE_INPUT = ".#######\n" +
            "#######.\n" +
            "###.###.\n" +
            "#....###\n" +
            ".#..##..\n" +
            "#.#.###.\n" +
            "###..###\n" +
            ".#.#.##.";
}
