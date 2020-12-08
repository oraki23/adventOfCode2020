package com.advco;

import java.util.TreeSet;

public class Day8 {
    public static void main(String[] args) {
        partTwo();
    }

    public static void partOne(){
        String[] instructions = PUZZLE_INPUT.split("\n");
        TreeSet ranInstructions = new TreeSet<>();

        int acc = 0;
        for(int i = 0; i < instructions.length; i++){
            if(ranInstructions.contains(i)){
                System.out.println("Infinite loop. Acc value: " + acc);
                break;
            }
            ranInstructions.add(i);

            String instruction = instructions[i].substring(0,3);
            String action = instructions[i].substring(4,5);
            int value = Integer.parseInt(instructions[i].substring(5));
            System.out.println(instruction + ' ' + action + value);

            if(action.equals("-")) {
                value = -1 * value;
            }

            if(instruction.equals("acc")){
                acc += value;
            } else if(instruction.equals("jmp")){
                i += value-1;
            }
        }
    }

    public static void partTwo(){
        String[] instructions = PUZZLE_INPUT.split("\n");

        int acc = 0;
        for(int i = 0; i < instructions.length; i++){
            boolean infiniteLoop = false;
            TreeSet ranInstructions = new TreeSet<>();
            acc = 0;

            for(int j = 0; j < instructions.length; j++){
                if(ranInstructions.contains(j)){
                    System.out.println("Infinite loop. breaking.");
                    infiniteLoop = true;
                    break;
                }
                ranInstructions.add(j);

                /*Change value depending on j*/
                String instruction = instructions[j].substring(0,3);
                if(i == j){
                    if(instruction.equals("jmp")){
                        instruction = "nop";
                    } else if(instruction.equals("nop")){
                        instruction = "jmp";
                    }
                }

                String action = instructions[j].substring(4,5);
                int value = Integer.parseInt(instructions[j].substring(5));
                System.out.println(instruction + ' ' + action + value);

                if(action.equals("-")) {
                    value = -1 * value;
                }

                if(instruction.equals("acc")){
                    acc += value;
                } else if(instruction.equals("jmp")){
                    j += value-1;
                }
            }

            if(infiniteLoop == false){
                break;
            }
        }

        System.out.println("Program ended. Acc value: " + acc);
    }

    public static final String PUZZLE_TEST = "nop +0\n" +
            "acc +1\n" +
            "jmp +4\n" +
            "acc +3\n" +
            "jmp -3\n" +
            "acc -99\n" +
            "acc +1\n" +
            "jmp -4\n" +
            "acc +6";
    public static final String PUZZLE_INPUT = "jmp +254\n" +
            "jmp +1\n" +
            "acc +48\n" +
            "jmp +487\n" +
            "jmp +586\n" +
            "acc -18\n" +
            "jmp +238\n" +
            "acc +37\n" +
            "acc -7\n" +
            "acc +45\n" +
            "jmp +514\n" +
            "jmp +25\n" +
            "acc -2\n" +
            "acc +48\n" +
            "jmp +43\n" +
            "acc +33\n" +
            "acc -1\n" +
            "jmp +98\n" +
            "acc +0\n" +
            "nop +406\n" +
            "acc +32\n" +
            "acc +34\n" +
            "jmp -15\n" +
            "jmp -5\n" +
            "acc +0\n" +
            "nop +60\n" +
            "nop +395\n" +
            "jmp -15\n" +
            "jmp +380\n" +
            "acc -15\n" +
            "jmp +446\n" +
            "acc +38\n" +
            "acc +18\n" +
            "acc -1\n" +
            "acc +23\n" +
            "jmp +386\n" +
            "nop +534\n" +
            "acc +19\n" +
            "acc -6\n" +
            "acc +41\n" +
            "jmp +163\n" +
            "acc +17\n" +
            "jmp +383\n" +
            "acc -13\n" +
            "jmp +346\n" +
            "acc +10\n" +
            "acc -18\n" +
            "nop +448\n" +
            "acc +50\n" +
            "jmp +399\n" +
            "acc +43\n" +
            "acc +36\n" +
            "jmp +24\n" +
            "acc -7\n" +
            "acc +43\n" +
            "nop +60\n" +
            "jmp +80\n" +
            "nop +40\n" +
            "nop +274\n" +
            "acc -16\n" +
            "acc +42\n" +
            "jmp +102\n" +
            "acc +17\n" +
            "jmp +410\n" +
            "acc -8\n" +
            "acc +45\n" +
            "acc +12\n" +
            "acc +50\n" +
            "jmp +486\n" +
            "acc +17\n" +
            "jmp +425\n" +
            "acc +39\n" +
            "jmp +239\n" +
            "acc +7\n" +
            "acc +3\n" +
            "jmp +315\n" +
            "acc +13\n" +
            "jmp +344\n" +
            "jmp +154\n" +
            "acc +20\n" +
            "acc +3\n" +
            "jmp +206\n" +
            "acc -14\n" +
            "acc +33\n" +
            "jmp +79\n" +
            "acc +44\n" +
            "jmp +106\n" +
            "acc +5\n" +
            "jmp +1\n" +
            "acc -19\n" +
            "acc +19\n" +
            "jmp +346\n" +
            "acc +41\n" +
            "acc +42\n" +
            "jmp +481\n" +
            "acc -4\n" +
            "jmp +142\n" +
            "acc +10\n" +
            "acc -5\n" +
            "acc +44\n" +
            "nop +302\n" +
            "jmp +368\n" +
            "nop +36\n" +
            "acc +46\n" +
            "acc +44\n" +
            "nop +171\n" +
            "jmp +256\n" +
            "acc +37\n" +
            "nop -11\n" +
            "acc +7\n" +
            "nop -34\n" +
            "jmp -68\n" +
            "acc +16\n" +
            "acc -4\n" +
            "jmp +355\n" +
            "acc +1\n" +
            "acc +45\n" +
            "acc -19\n" +
            "jmp +464\n" +
            "acc +33\n" +
            "jmp +149\n" +
            "jmp +475\n" +
            "nop -86\n" +
            "acc -7\n" +
            "acc -12\n" +
            "acc +38\n" +
            "jmp +398\n" +
            "acc +8\n" +
            "acc +16\n" +
            "acc +25\n" +
            "jmp +350\n" +
            "acc +18\n" +
            "acc +42\n" +
            "acc +12\n" +
            "acc +43\n" +
            "jmp +302\n" +
            "jmp +331\n" +
            "jmp +14\n" +
            "acc -18\n" +
            "acc +18\n" +
            "acc +7\n" +
            "jmp +140\n" +
            "acc +8\n" +
            "jmp +42\n" +
            "acc +10\n" +
            "acc +4\n" +
            "acc +28\n" +
            "acc +20\n" +
            "jmp +75\n" +
            "acc -8\n" +
            "jmp +30\n" +
            "acc +13\n" +
            "jmp +28\n" +
            "acc -6\n" +
            "jmp -142\n" +
            "acc +17\n" +
            "jmp -41\n" +
            "jmp +361\n" +
            "acc +37\n" +
            "jmp +147\n" +
            "nop +78\n" +
            "jmp +1\n" +
            "jmp -18\n" +
            "acc +1\n" +
            "nop +273\n" +
            "acc +43\n" +
            "acc +30\n" +
            "jmp +126\n" +
            "acc +4\n" +
            "acc +10\n" +
            "acc +9\n" +
            "jmp -128\n" +
            "acc -12\n" +
            "acc -3\n" +
            "jmp +58\n" +
            "acc +17\n" +
            "acc +38\n" +
            "acc +42\n" +
            "acc -10\n" +
            "jmp +218\n" +
            "acc -18\n" +
            "jmp +378\n" +
            "acc -11\n" +
            "acc +6\n" +
            "jmp -33\n" +
            "acc -15\n" +
            "jmp +68\n" +
            "acc -11\n" +
            "nop +312\n" +
            "acc +21\n" +
            "acc +33\n" +
            "jmp -36\n" +
            "jmp +281\n" +
            "acc +34\n" +
            "acc +3\n" +
            "nop -40\n" +
            "acc -5\n" +
            "jmp +141\n" +
            "acc +6\n" +
            "acc -5\n" +
            "jmp +99\n" +
            "acc -9\n" +
            "jmp +360\n" +
            "acc -9\n" +
            "jmp +366\n" +
            "acc -1\n" +
            "nop -188\n" +
            "acc +47\n" +
            "nop -87\n" +
            "jmp +361\n" +
            "jmp -113\n" +
            "acc +43\n" +
            "acc +21\n" +
            "nop +41\n" +
            "acc +1\n" +
            "jmp -23\n" +
            "acc +10\n" +
            "nop -110\n" +
            "acc -16\n" +
            "jmp +136\n" +
            "acc +33\n" +
            "nop +219\n" +
            "jmp -95\n" +
            "jmp +223\n" +
            "acc -6\n" +
            "jmp +354\n" +
            "acc +24\n" +
            "acc +50\n" +
            "acc +10\n" +
            "acc +26\n" +
            "jmp +207\n" +
            "jmp -202\n" +
            "jmp -6\n" +
            "nop +181\n" +
            "acc -9\n" +
            "nop +248\n" +
            "acc +43\n" +
            "jmp +325\n" +
            "acc +2\n" +
            "acc +19\n" +
            "acc +22\n" +
            "jmp +254\n" +
            "acc +31\n" +
            "jmp +233\n" +
            "acc -9\n" +
            "acc +24\n" +
            "acc +49\n" +
            "acc +18\n" +
            "jmp +84\n" +
            "acc -19\n" +
            "acc +16\n" +
            "acc +37\n" +
            "acc +31\n" +
            "jmp +66\n" +
            "acc +6\n" +
            "jmp +1\n" +
            "jmp +206\n" +
            "jmp +126\n" +
            "acc +24\n" +
            "jmp +271\n" +
            "acc +16\n" +
            "jmp +1\n" +
            "acc -11\n" +
            "acc -4\n" +
            "nop +47\n" +
            "jmp +118\n" +
            "nop +136\n" +
            "acc +7\n" +
            "jmp +94\n" +
            "acc +0\n" +
            "acc +0\n" +
            "jmp +239\n" +
            "acc -4\n" +
            "acc +23\n" +
            "acc +16\n" +
            "jmp +270\n" +
            "acc +28\n" +
            "jmp -8\n" +
            "acc +24\n" +
            "acc -13\n" +
            "jmp +117\n" +
            "acc +31\n" +
            "acc -3\n" +
            "acc +21\n" +
            "acc -9\n" +
            "jmp +86\n" +
            "jmp +293\n" +
            "nop -29\n" +
            "acc -11\n" +
            "jmp -162\n" +
            "acc +36\n" +
            "acc -4\n" +
            "jmp +122\n" +
            "acc -13\n" +
            "acc -10\n" +
            "jmp -115\n" +
            "acc +23\n" +
            "acc +7\n" +
            "jmp -126\n" +
            "acc +21\n" +
            "jmp -162\n" +
            "acc +48\n" +
            "acc +43\n" +
            "acc +37\n" +
            "nop -275\n" +
            "jmp -89\n" +
            "nop +248\n" +
            "jmp +107\n" +
            "acc +26\n" +
            "acc -16\n" +
            "jmp +185\n" +
            "acc +40\n" +
            "acc +32\n" +
            "jmp +232\n" +
            "acc +27\n" +
            "jmp +189\n" +
            "nop +259\n" +
            "jmp +131\n" +
            "jmp +261\n" +
            "jmp +230\n" +
            "acc -2\n" +
            "acc +37\n" +
            "jmp +240\n" +
            "nop +1\n" +
            "acc -9\n" +
            "acc +36\n" +
            "jmp -110\n" +
            "acc +5\n" +
            "acc +50\n" +
            "acc +23\n" +
            "acc -19\n" +
            "jmp +142\n" +
            "acc +40\n" +
            "acc -4\n" +
            "acc +3\n" +
            "nop +134\n" +
            "jmp -164\n" +
            "jmp +60\n" +
            "acc +28\n" +
            "acc +28\n" +
            "acc +24\n" +
            "acc -7\n" +
            "jmp +91\n" +
            "acc +1\n" +
            "jmp -268\n" +
            "acc +41\n" +
            "jmp -195\n" +
            "acc -3\n" +
            "jmp +231\n" +
            "acc +48\n" +
            "acc +7\n" +
            "jmp -153\n" +
            "acc +2\n" +
            "acc -11\n" +
            "jmp +60\n" +
            "nop -240\n" +
            "nop -40\n" +
            "jmp -125\n" +
            "acc -7\n" +
            "acc +14\n" +
            "acc +23\n" +
            "jmp -103\n" +
            "jmp +1\n" +
            "acc +27\n" +
            "acc +16\n" +
            "acc -17\n" +
            "jmp -181\n" +
            "acc +38\n" +
            "acc -6\n" +
            "acc +20\n" +
            "jmp -243\n" +
            "acc +13\n" +
            "acc +26\n" +
            "acc +5\n" +
            "acc +38\n" +
            "jmp -268\n" +
            "acc -17\n" +
            "jmp -373\n" +
            "acc -10\n" +
            "acc -10\n" +
            "nop +68\n" +
            "jmp -36\n" +
            "jmp -128\n" +
            "acc -5\n" +
            "acc -9\n" +
            "acc +10\n" +
            "acc +15\n" +
            "jmp +103\n" +
            "acc -6\n" +
            "jmp +64\n" +
            "acc +42\n" +
            "acc +15\n" +
            "acc -12\n" +
            "acc -1\n" +
            "jmp -309\n" +
            "nop +187\n" +
            "jmp -378\n" +
            "jmp -78\n" +
            "jmp +1\n" +
            "acc +7\n" +
            "acc +0\n" +
            "jmp -389\n" +
            "acc -14\n" +
            "jmp -80\n" +
            "acc -13\n" +
            "acc +0\n" +
            "acc +25\n" +
            "jmp -364\n" +
            "acc -9\n" +
            "acc -13\n" +
            "acc +11\n" +
            "jmp +1\n" +
            "jmp -327\n" +
            "jmp +63\n" +
            "acc +0\n" +
            "nop -300\n" +
            "acc +29\n" +
            "jmp -101\n" +
            "nop -238\n" +
            "acc +25\n" +
            "jmp -204\n" +
            "jmp -13\n" +
            "acc +21\n" +
            "acc +43\n" +
            "jmp -137\n" +
            "acc +44\n" +
            "acc +11\n" +
            "acc -18\n" +
            "jmp -307\n" +
            "acc -16\n" +
            "acc +0\n" +
            "acc -7\n" +
            "jmp +138\n" +
            "acc +11\n" +
            "acc +42\n" +
            "jmp -411\n" +
            "jmp -34\n" +
            "acc +9\n" +
            "jmp -89\n" +
            "jmp +115\n" +
            "jmp -62\n" +
            "acc -5\n" +
            "acc +37\n" +
            "acc +14\n" +
            "acc +16\n" +
            "jmp +45\n" +
            "jmp +37\n" +
            "jmp -404\n" +
            "jmp -356\n" +
            "acc -4\n" +
            "acc +18\n" +
            "acc -18\n" +
            "jmp -97\n" +
            "nop +57\n" +
            "acc -5\n" +
            "nop -93\n" +
            "acc +47\n" +
            "jmp -247\n" +
            "acc +4\n" +
            "acc -13\n" +
            "nop -309\n" +
            "jmp -245\n" +
            "acc +9\n" +
            "acc -12\n" +
            "acc +49\n" +
            "jmp -302\n" +
            "jmp +88\n" +
            "jmp -438\n" +
            "jmp -397\n" +
            "acc -9\n" +
            "nop -278\n" +
            "jmp -313\n" +
            "jmp +90\n" +
            "jmp -329\n" +
            "acc +36\n" +
            "jmp -411\n" +
            "acc +3\n" +
            "acc +36\n" +
            "jmp -454\n" +
            "jmp -343\n" +
            "nop -148\n" +
            "jmp -237\n" +
            "jmp -159\n" +
            "acc +6\n" +
            "acc -17\n" +
            "jmp -481\n" +
            "acc +30\n" +
            "acc -12\n" +
            "acc +40\n" +
            "jmp -27\n" +
            "acc -19\n" +
            "acc +30\n" +
            "jmp -39\n" +
            "acc -17\n" +
            "jmp -32\n" +
            "acc +23\n" +
            "jmp -432\n" +
            "acc -18\n" +
            "nop -427\n" +
            "jmp +19\n" +
            "acc -6\n" +
            "nop +81\n" +
            "jmp +16\n" +
            "jmp -23\n" +
            "nop +45\n" +
            "acc +44\n" +
            "nop -33\n" +
            "acc +23\n" +
            "jmp -111\n" +
            "jmp -271\n" +
            "acc +3\n" +
            "acc +0\n" +
            "acc +5\n" +
            "acc -4\n" +
            "jmp +48\n" +
            "acc +0\n" +
            "nop -300\n" +
            "jmp -402\n" +
            "acc +18\n" +
            "acc +3\n" +
            "acc +30\n" +
            "jmp -263\n" +
            "nop -125\n" +
            "jmp +59\n" +
            "jmp -488\n" +
            "nop -518\n" +
            "acc +3\n" +
            "acc -11\n" +
            "acc +3\n" +
            "jmp -522\n" +
            "acc +13\n" +
            "jmp +9\n" +
            "acc +35\n" +
            "acc +22\n" +
            "jmp -276\n" +
            "jmp +1\n" +
            "acc -5\n" +
            "jmp -458\n" +
            "acc -10\n" +
            "jmp -388\n" +
            "nop -100\n" +
            "jmp -6\n" +
            "acc -6\n" +
            "nop -289\n" +
            "jmp -91\n" +
            "acc +31\n" +
            "acc +37\n" +
            "jmp -43\n" +
            "jmp -237\n" +
            "jmp -49\n" +
            "acc +22\n" +
            "acc +13\n" +
            "nop -79\n" +
            "jmp -105\n" +
            "jmp +1\n" +
            "acc -8\n" +
            "jmp -166\n" +
            "nop -27\n" +
            "jmp -23\n" +
            "acc -17\n" +
            "jmp -298\n" +
            "jmp -274\n" +
            "acc +5\n" +
            "acc +11\n" +
            "jmp -372\n" +
            "acc +29\n" +
            "nop -204\n" +
            "acc -14\n" +
            "acc +38\n" +
            "jmp -493\n" +
            "acc +15\n" +
            "jmp -146\n" +
            "acc -6\n" +
            "acc +49\n" +
            "jmp -371\n" +
            "jmp -504\n" +
            "acc +17\n" +
            "nop -367\n" +
            "jmp -279\n" +
            "acc +9\n" +
            "jmp -50\n" +
            "jmp -327\n" +
            "acc +18\n" +
            "acc +39\n" +
            "acc +19\n" +
            "acc +10\n" +
            "jmp -7\n" +
            "nop -87\n" +
            "acc +15\n" +
            "jmp -565\n" +
            "jmp -252\n" +
            "acc -19\n" +
            "acc +17\n" +
            "acc +25\n" +
            "nop -350\n" +
            "jmp -296\n" +
            "acc +39\n" +
            "nop -579\n" +
            "acc +23\n" +
            "acc +43\n" +
            "jmp +1\n";
}
