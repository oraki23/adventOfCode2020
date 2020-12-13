package com.advco;

public class Day12 {
    public static void main(String[] args) {
        partTwo();
    }

    public static void partOne(){
        String[] instructions = PUZZLE_INPUT.split("\n");

        int northValue = 0;
        int eastValue = 0;

        char direction = 'E';

        for(int i = 0; i < instructions.length; i++){
            String instruction = instructions[i];
            char operation = instruction.charAt(0);
            int value = Integer.parseInt(instruction.substring(1));

            int deg = 0;
            switch(operation){
                case 'N':
                    northValue += value;
                    break;
                case 'S':
                    northValue -= value;
                    break;
                case 'E':
                    eastValue += value;
                    break;
                case 'W':
                    eastValue -= value;
                    break;
                case 'L':
                    deg = value;
                    while(deg != 0){
                        if(direction == 'N'){
                            direction = 'W';
                        } else if(direction == 'S'){
                            direction = 'E';
                        } else if(direction == 'E'){
                            direction = 'N';
                        } else if(direction == 'W'){
                            direction = 'S';
                        }
                        deg -= 90;
                    }
                    break;
                case 'R':
                    deg = value;
                    while(deg != 0){
                        if(direction == 'N'){
                            direction = 'E';
                        } else if(direction == 'S'){
                            direction = 'W';
                        } else if(direction == 'E'){
                            direction = 'S';
                        } else if(direction == 'W'){
                            direction = 'N';
                        }
                        deg -= 90;
                    }
                    break;
                case 'F':
                    if(direction == 'N'){
                        northValue += value;
                    } else if(direction == 'S'){
                        northValue -= value;
                    } else if(direction == 'E'){
                        eastValue += value;
                    } else if(direction == 'W'){
                        eastValue -= value;
                    }
                    break;
            }
        }
        System.out.println("North Value: " + northValue);
        System.out.println("East Value: " + eastValue);

        int manhattan = Math.abs(northValue) + Math.abs(eastValue);
        System.out.println("manhattan: " + manhattan);
    }

    public static void partTwo(){
        String[] instructions = PUZZLE_INPUT.split("\n");

        int northValueShip = 0;
        int eastValueShip = 0;

        int northValueWaypoint = 1;
        int eastValueWaypoint = 10;


        char wayPointDirection = 'E';

        for(int i = 0; i < instructions.length; i++){
            String instruction = instructions[i];
            char operation = instruction.charAt(0);
            int value = Integer.parseInt(instruction.substring(1));

            int deg = 0;
            switch(operation){
                case 'N':
                    northValueWaypoint += value;
                    break;
                case 'S':
                    northValueWaypoint -= value;
                    break;
                case 'E':
                    eastValueWaypoint += value;
                    break;
                case 'W':
                    eastValueWaypoint -= value;
                    break;
                case 'L':
                    deg = value;
                    while(deg != 0){

                        int temp = eastValueWaypoint;

                        eastValueWaypoint = northValueWaypoint * -1;
                        northValueWaypoint = temp;

                        deg -= 90;
                    }
                    break;
                case 'R':
                    deg = value;
                    while(deg != 0){
                        int temp = eastValueWaypoint;

                        eastValueWaypoint = northValueWaypoint; //Validated
                        northValueWaypoint = temp * -1; //validated

                        deg -= 90;
                    }
                    break;
                case 'F':
                    northValueShip += (value * northValueWaypoint);
                    eastValueShip += (value * eastValueWaypoint);

                    /*if(wayPointDirection == 'N'){
                    } else if(wayPointDirection == 'S'){
                        northValueShip -= (value * northValueWaypoint);
                    } else if(wayPointDirection == 'E'){
                    } else if(wayPointDirection == 'W'){
                        eastValueShip -= (value * eastValueWaypoint);
                    }*/
                    break;
            }
            System.out.println("North Ship Value: " + northValueShip);
            System.out.println("East Ship Value: " + eastValueShip);
            System.out.println("North WayPoint Value: " + northValueWaypoint);
            System.out.println("East WayPoint Value: " + eastValueWaypoint);
            System.out.println();

        }
        System.out.println("North Value: " + northValueShip);
        System.out.println("East Value: " + eastValueShip);

        int manhattan = Math.abs(northValueShip) + Math.abs(eastValueShip);
        System.out.println("manhattan: " + manhattan);
    }

    public static final String PUZZLE_TEST = "F10\n" +
            "N3\n" +
            "F7\n" +
            "R90\n" +
            "F11";
    public static final String PUZZLE_INPUT = "W1\n" +
            "L90\n" +
            "F26\n" +
            "S3\n" +
            "W2\n" +
            "N5\n" +
            "L180\n" +
            "S4\n" +
            "F41\n" +
            "W1\n" +
            "F48\n" +
            "W3\n" +
            "F44\n" +
            "F63\n" +
            "W5\n" +
            "N3\n" +
            "E5\n" +
            "F7\n" +
            "R180\n" +
            "W1\n" +
            "N3\n" +
            "W3\n" +
            "R180\n" +
            "F38\n" +
            "N1\n" +
            "E3\n" +
            "L90\n" +
            "F49\n" +
            "S5\n" +
            "F11\n" +
            "E1\n" +
            "R90\n" +
            "W3\n" +
            "N4\n" +
            "E3\n" +
            "R180\n" +
            "W5\n" +
            "F93\n" +
            "S1\n" +
            "F67\n" +
            "L180\n" +
            "W3\n" +
            "S5\n" +
            "N3\n" +
            "L180\n" +
            "E1\n" +
            "N2\n" +
            "F90\n" +
            "S3\n" +
            "W3\n" +
            "N3\n" +
            "E1\n" +
            "F77\n" +
            "W3\n" +
            "N3\n" +
            "L180\n" +
            "E2\n" +
            "F25\n" +
            "N1\n" +
            "W1\n" +
            "S1\n" +
            "E5\n" +
            "S4\n" +
            "R180\n" +
            "W1\n" +
            "F13\n" +
            "L90\n" +
            "W4\n" +
            "S5\n" +
            "F13\n" +
            "N2\n" +
            "R90\n" +
            "F89\n" +
            "R90\n" +
            "W3\n" +
            "L90\n" +
            "W5\n" +
            "N5\n" +
            "E4\n" +
            "S4\n" +
            "F26\n" +
            "N1\n" +
            "R270\n" +
            "N5\n" +
            "E5\n" +
            "L180\n" +
            "R180\n" +
            "W4\n" +
            "R90\n" +
            "W5\n" +
            "F49\n" +
            "S2\n" +
            "F53\n" +
            "W5\n" +
            "L180\n" +
            "F54\n" +
            "L90\n" +
            "N3\n" +
            "F3\n" +
            "W5\n" +
            "R180\n" +
            "S4\n" +
            "L90\n" +
            "F49\n" +
            "W4\n" +
            "S5\n" +
            "F73\n" +
            "L270\n" +
            "W1\n" +
            "S4\n" +
            "F46\n" +
            "S4\n" +
            "W2\n" +
            "S5\n" +
            "E4\n" +
            "N4\n" +
            "F6\n" +
            "E4\n" +
            "S4\n" +
            "F38\n" +
            "F4\n" +
            "E4\n" +
            "R90\n" +
            "N1\n" +
            "W2\n" +
            "F20\n" +
            "N3\n" +
            "F64\n" +
            "R90\n" +
            "S3\n" +
            "W5\n" +
            "N1\n" +
            "W4\n" +
            "N3\n" +
            "F17\n" +
            "R90\n" +
            "F62\n" +
            "E4\n" +
            "E2\n" +
            "F47\n" +
            "R90\n" +
            "W1\n" +
            "N3\n" +
            "R90\n" +
            "N2\n" +
            "R90\n" +
            "F57\n" +
            "L270\n" +
            "N2\n" +
            "W3\n" +
            "S5\n" +
            "W4\n" +
            "E2\n" +
            "S5\n" +
            "F93\n" +
            "F57\n" +
            "S1\n" +
            "L90\n" +
            "F50\n" +
            "N2\n" +
            "F4\n" +
            "N1\n" +
            "L90\n" +
            "F34\n" +
            "N2\n" +
            "L90\n" +
            "N4\n" +
            "W3\n" +
            "N5\n" +
            "R180\n" +
            "S3\n" +
            "L90\n" +
            "W3\n" +
            "S3\n" +
            "L90\n" +
            "F70\n" +
            "L90\n" +
            "E1\n" +
            "F92\n" +
            "N1\n" +
            "F96\n" +
            "F85\n" +
            "S5\n" +
            "L90\n" +
            "W1\n" +
            "L90\n" +
            "W1\n" +
            "F23\n" +
            "L90\n" +
            "S1\n" +
            "R90\n" +
            "W5\n" +
            "S5\n" +
            "F66\n" +
            "W3\n" +
            "L180\n" +
            "W2\n" +
            "L90\n" +
            "N2\n" +
            "E3\n" +
            "R270\n" +
            "R270\n" +
            "N3\n" +
            "W5\n" +
            "R90\n" +
            "S3\n" +
            "E1\n" +
            "R90\n" +
            "F78\n" +
            "E1\n" +
            "S1\n" +
            "R90\n" +
            "S3\n" +
            "F52\n" +
            "S4\n" +
            "F9\n" +
            "L90\n" +
            "W1\n" +
            "N2\n" +
            "F8\n" +
            "R90\n" +
            "N1\n" +
            "F63\n" +
            "E5\n" +
            "F18\n" +
            "E3\n" +
            "F43\n" +
            "E2\n" +
            "F10\n" +
            "R90\n" +
            "F96\n" +
            "S5\n" +
            "F22\n" +
            "W2\n" +
            "S5\n" +
            "F39\n" +
            "R90\n" +
            "F38\n" +
            "S5\n" +
            "R90\n" +
            "E3\n" +
            "L90\n" +
            "W3\n" +
            "N2\n" +
            "F14\n" +
            "L270\n" +
            "S4\n" +
            "F78\n" +
            "F85\n" +
            "L90\n" +
            "N3\n" +
            "E3\n" +
            "S3\n" +
            "F98\n" +
            "E2\n" +
            "S2\n" +
            "F100\n" +
            "S3\n" +
            "S3\n" +
            "W5\n" +
            "W3\n" +
            "S5\n" +
            "F67\n" +
            "L180\n" +
            "S2\n" +
            "E5\n" +
            "S1\n" +
            "L90\n" +
            "N5\n" +
            "E2\n" +
            "W2\n" +
            "R90\n" +
            "E1\n" +
            "N2\n" +
            "L90\n" +
            "F77\n" +
            "W1\n" +
            "F84\n" +
            "L90\n" +
            "S2\n" +
            "E4\n" +
            "R90\n" +
            "E1\n" +
            "R90\n" +
            "S3\n" +
            "S4\n" +
            "F89\n" +
            "R90\n" +
            "N1\n" +
            "E4\n" +
            "R90\n" +
            "N1\n" +
            "F97\n" +
            "L90\n" +
            "S1\n" +
            "W3\n" +
            "R180\n" +
            "F70\n" +
            "S5\n" +
            "E1\n" +
            "L180\n" +
            "W5\n" +
            "F86\n" +
            "S3\n" +
            "F20\n" +
            "R90\n" +
            "S1\n" +
            "W4\n" +
            "R90\n" +
            "W1\n" +
            "F3\n" +
            "S3\n" +
            "R90\n" +
            "F43\n" +
            "L180\n" +
            "F81\n" +
            "E2\n" +
            "N3\n" +
            "F16\n" +
            "L90\n" +
            "S2\n" +
            "F17\n" +
            "E3\n" +
            "F1\n" +
            "E4\n" +
            "F17\n" +
            "W3\n" +
            "N3\n" +
            "W5\n" +
            "S3\n" +
            "W4\n" +
            "F60\n" +
            "E3\n" +
            "E1\n" +
            "S5\n" +
            "L90\n" +
            "E2\n" +
            "S5\n" +
            "F19\n" +
            "E2\n" +
            "R90\n" +
            "F20\n" +
            "R180\n" +
            "S4\n" +
            "F9\n" +
            "R90\n" +
            "N5\n" +
            "W5\n" +
            "F56\n" +
            "N2\n" +
            "L180\n" +
            "N1\n" +
            "E5\n" +
            "L90\n" +
            "F15\n" +
            "W4\n" +
            "F26\n" +
            "R90\n" +
            "W2\n" +
            "F19\n" +
            "S3\n" +
            "W1\n" +
            "R90\n" +
            "W5\n" +
            "R180\n" +
            "W4\n" +
            "N2\n" +
            "F86\n" +
            "N5\n" +
            "E3\n" +
            "W3\n" +
            "N3\n" +
            "L270\n" +
            "W3\n" +
            "F42\n" +
            "N5\n" +
            "W2\n" +
            "R180\n" +
            "W2\n" +
            "R180\n" +
            "S4\n" +
            "R90\n" +
            "F55\n" +
            "S3\n" +
            "R90\n" +
            "S3\n" +
            "E3\n" +
            "R90\n" +
            "F11\n" +
            "S4\n" +
            "F38\n" +
            "W1\n" +
            "L90\n" +
            "F8\n" +
            "R90\n" +
            "E5\n" +
            "R90\n" +
            "W1\n" +
            "W5\n" +
            "S2\n" +
            "F2\n" +
            "F92\n" +
            "S3\n" +
            "F77\n" +
            "S5\n" +
            "R90\n" +
            "F24\n" +
            "E3\n" +
            "R90\n" +
            "N3\n" +
            "F16\n" +
            "L270\n" +
            "W3\n" +
            "F83\n" +
            "L270\n" +
            "E2\n" +
            "F98\n" +
            "L180\n" +
            "F89\n" +
            "E5\n" +
            "F98\n" +
            "S4\n" +
            "E2\n" +
            "L90\n" +
            "N4\n" +
            "L180\n" +
            "F57\n" +
            "S5\n" +
            "R90\n" +
            "L90\n" +
            "S4\n" +
            "W4\n" +
            "S5\n" +
            "S4\n" +
            "W4\n" +
            "F43\n" +
            "N2\n" +
            "F29\n" +
            "W3\n" +
            "R90\n" +
            "F41\n" +
            "R90\n" +
            "N2\n" +
            "F78\n" +
            "R90\n" +
            "E5\n" +
            "N1\n" +
            "W2\n" +
            "F6\n" +
            "L270\n" +
            "W5\n" +
            "F91\n" +
            "W5\n" +
            "N1\n" +
            "S4\n" +
            "F41\n" +
            "W4\n" +
            "F74\n" +
            "E1\n" +
            "R90\n" +
            "N4\n" +
            "F76\n" +
            "W4\n" +
            "S2\n" +
            "L180\n" +
            "N2\n" +
            "R180\n" +
            "W4\n" +
            "F79\n" +
            "R270\n" +
            "W1\n" +
            "F92\n" +
            "W1\n" +
            "L90\n" +
            "F71\n" +
            "N4\n" +
            "L180\n" +
            "W4\n" +
            "F16\n" +
            "W5\n" +
            "F84\n" +
            "S5\n" +
            "F35\n" +
            "W4\n" +
            "R90\n" +
            "F25\n" +
            "L180\n" +
            "N1\n" +
            "E3\n" +
            "F15\n" +
            "S4\n" +
            "R180\n" +
            "F46\n" +
            "S1\n" +
            "W1\n" +
            "R180\n" +
            "E4\n" +
            "N5\n" +
            "R90\n" +
            "S1\n" +
            "W3\n" +
            "S3\n" +
            "L270\n" +
            "F94\n" +
            "R180\n" +
            "N1\n" +
            "W4\n" +
            "N5\n" +
            "W2\n" +
            "S2\n" +
            "W3\n" +
            "F53\n" +
            "L180\n" +
            "S3\n" +
            "F19\n" +
            "N3\n" +
            "F54\n" +
            "L180\n" +
            "S5\n" +
            "F8\n" +
            "S1\n" +
            "N5\n" +
            "L90\n" +
            "E4\n" +
            "N3\n" +
            "F28\n" +
            "R180\n" +
            "F23\n" +
            "E1\n" +
            "L90\n" +
            "E3\n" +
            "F6\n" +
            "W4\n" +
            "R90\n" +
            "N1\n" +
            "F89\n" +
            "S1\n" +
            "W2\n" +
            "S5\n" +
            "F8\n" +
            "N3\n" +
            "F23\n" +
            "N4\n" +
            "F5\n" +
            "L90\n" +
            "N3\n" +
            "R90\n" +
            "W4\n" +
            "L180\n" +
            "S3\n" +
            "F7\n" +
            "N2\n" +
            "W3\n" +
            "R180\n" +
            "E1\n" +
            "L180\n" +
            "S4\n" +
            "R90\n" +
            "S1\n" +
            "F99\n" +
            "N3\n" +
            "F96\n" +
            "W3\n" +
            "R90\n" +
            "F73\n" +
            "W5\n" +
            "F71\n" +
            "R180\n" +
            "S2\n" +
            "F84\n" +
            "N4\n" +
            "F4\n" +
            "W4\n" +
            "R90\n" +
            "F34\n" +
            "E2\n" +
            "W2\n" +
            "F53\n" +
            "N4\n" +
            "R90\n" +
            "N5\n" +
            "E5\n" +
            "R90\n" +
            "F60\n" +
            "N4\n" +
            "F28\n" +
            "S2\n" +
            "W1\n" +
            "N4\n" +
            "F54\n" +
            "R270\n" +
            "F45\n" +
            "S5\n" +
            "F93\n" +
            "L90\n" +
            "F66\n" +
            "R180\n" +
            "F92\n" +
            "N4\n" +
            "F97\n" +
            "R90\n" +
            "W5\n" +
            "S1\n" +
            "W5\n" +
            "F68\n" +
            "S3\n" +
            "L90\n" +
            "E3\n" +
            "F94\n" +
            "S4\n" +
            "F64\n" +
            "R180\n" +
            "F18\n" +
            "N1\n" +
            "S4\n" +
            "E5\n" +
            "E2\n" +
            "F81\n" +
            "N1\n" +
            "L90\n" +
            "F3\n" +
            "R90\n" +
            "F81\n" +
            "W4\n" +
            "S4\n" +
            "E5\n" +
            "N5\n" +
            "R270\n" +
            "E3\n" +
            "S2\n" +
            "W1\n" +
            "L180\n" +
            "S1\n" +
            "F84\n" +
            "W2\n" +
            "L270\n" +
            "F6\n" +
            "N1\n" +
            "R180\n" +
            "E5\n" +
            "F7\n" +
            "E2\n" +
            "L180\n" +
            "E2\n" +
            "F80\n" +
            "N1\n" +
            "L90\n" +
            "F88\n" +
            "R90\n" +
            "W5\n" +
            "N1\n" +
            "F71\n" +
            "R180\n" +
            "N2\n" +
            "E2\n" +
            "R90\n" +
            "N1\n" +
            "W1\n" +
            "L90\n" +
            "E4\n" +
            "S4\n" +
            "L180\n" +
            "F27\n" +
            "L90\n" +
            "F57\n" +
            "F38\n" +
            "W5\n" +
            "L180\n" +
            "S5\n" +
            "R90\n" +
            "S4\n" +
            "W1\n" +
            "S3\n" +
            "L90\n" +
            "F36\n" +
            "S1\n" +
            "W5\n" +
            "R90\n" +
            "F65\n" +
            "R90\n" +
            "E1\n" +
            "N4\n" +
            "E1\n" +
            "F14\n" +
            "L90\n" +
            "F44\n" +
            "R90\n" +
            "F34\n" +
            "S2\n" +
            "L90\n" +
            "R180\n" +
            "F87\n" +
            "W3\n" +
            "L90\n" +
            "F9\n" +
            "R90\n" +
            "W3\n" +
            "L90\n" +
            "S5\n" +
            "F69\n" +
            "S3\n" +
            "W4\n" +
            "N4\n" +
            "F30\n" +
            "W5\n" +
            "F15\n" +
            "R90\n" +
            "L180\n" +
            "W4\n" +
            "F5\n" +
            "R180\n" +
            "E1\n" +
            "F6\n" +
            "R180\n" +
            "S1\n" +
            "F20\n" +
            "E1\n" +
            "S2\n" +
            "E5\n" +
            "F13\n" +
            "N5\n" +
            "F83\n" +
            "W2\n" +
            "L270\n" +
            "E2\n" +
            "R90\n" +
            "S5\n" +
            "F62\n" +
            "R270\n" +
            "N4\n" +
            "R90\n" +
            "F20\n" +
            "L90\n" +
            "N2\n" +
            "E3\n" +
            "L90\n" +
            "F37\n" +
            "N2\n" +
            "N2\n" +
            "F82\n" +
            "L90\n" +
            "F23\n" +
            "E3\n" +
            "F63\n" +
            "R180\n" +
            "F1\n" +
            "N2\n" +
            "R90\n" +
            "F68\n" +
            "E5\n" +
            "F75\n" +
            "R90\n" +
            "W3\n" +
            "R180\n" +
            "E4\n" +
            "E1\n" +
            "N3\n" +
            "R90\n" +
            "N3\n" +
            "L180\n" +
            "F92\n" +
            "R90\n" +
            "S4\n" +
            "F27\n" +
            "R180\n" +
            "S4\n" +
            "L180\n" +
            "W5\n" +
            "F70\n" +
            "S5\n" +
            "L180\n" +
            "F89\n" +
            "R90\n" +
            "W2\n" +
            "N3\n" +
            "F64\n" +
            "L90\n" +
            "E1\n" +
            "L90\n" +
            "F77\n" +
            "E4\n" +
            "F55\n" +
            "E2\n" +
            "L90\n" +
            "W2\n" +
            "F46\n" +
            "N2\n" +
            "R90\n" +
            "F94\n" +
            "S5\n" +
            "R180\n" +
            "F9\n" +
            "L180\n" +
            "S4\n" +
            "L90\n" +
            "F25\n";
}
