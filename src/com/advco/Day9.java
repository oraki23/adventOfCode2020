package com.advco;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Day9 {
    public static void main(String[] args) {
        partTwo();
    }

    public static void partOne(){
        int preambleSize = 25;

        String[] numbersString = PUZZLE_INPUT.split("\n");
        List<BigInteger> numbers = new ArrayList<>();
        for(int i = 0; i < numbersString.length; i++){
            numbers.add(new BigInteger(numbersString[i]));
        }

        BigInteger firstNbWithoutProprety = BigInteger.valueOf(-1);
        for(int i = preambleSize; i < numbers.size(); i++){
            BigInteger nbToCompare = numbers.get(i);
            boolean working = false;

            for(int j = i-1; j >= i-preambleSize; j--){
                BigInteger nbOne = numbers.get(j);

                for(int k = i-1; k >= i-preambleSize; k--){
                    BigInteger nbTwo = numbers.get(k);
                    if(nbOne != nbTwo){
                        if(nbToCompare.compareTo(nbOne.add(nbTwo)) == 0){
                            working = true;
                        }
                    }
                }
            }
            if(!working && firstNbWithoutProprety.compareTo(BigInteger.valueOf(-1)) == 0){
                firstNbWithoutProprety = nbToCompare;
            }
        }

        System.out.println("firstNbWithoutProprety: " + firstNbWithoutProprety);

    }

    public static void partTwo(){
        int preambleSize = 25;

        String[] numbersString = PUZZLE_INPUT.split("\n");
        List<BigInteger> numbers = new ArrayList<>();
        for(int i = 0; i < numbersString.length; i++){
            numbers.add(new BigInteger(numbersString[i]));
        }

        BigInteger firstNbWithoutProprety = BigInteger.valueOf(-1);
        for(int i = preambleSize; i < numbers.size(); i++){
            BigInteger nbToCompare = numbers.get(i);

            boolean working = false;

            for(int j = i-1; j >= i-preambleSize; j--){
                BigInteger nbOne = numbers.get(j);

                for(int k = i-1; k >= i-preambleSize; k--){
                    BigInteger nbTwo = numbers.get(k);
                    if(nbOne != nbTwo){
                        if(nbToCompare.compareTo(nbOne.add(nbTwo)) == 0){
                            working = true;
                        }
                    }
                }
            }
            if(!working && firstNbWithoutProprety.compareTo(BigInteger.valueOf(-1)) == 0){
                firstNbWithoutProprety = nbToCompare;
            }
        }

        BigInteger total = BigInteger.valueOf(-1);
        for(int i = 0; i < numbers.size(); i++){
            BigInteger beginning = numbers.get(i);

            BigInteger smallest = new BigInteger(beginning.toString());
            BigInteger highest = new BigInteger(beginning.toString());

            for(int j = i+1; j < numbers.size(); j++){
                if(i != j){
                    if(numbers.get(j).compareTo(smallest) == -1){
                        smallest = numbers.get(j);
                    } else if(numbers.get(j).compareTo(highest) == 1){
                        highest = numbers.get(j);
                    }

                    beginning = beginning.add(numbers.get(j));
                    //Equals
                    if(beginning.compareTo(firstNbWithoutProprety) == 0){
                        if(total.compareTo(BigInteger.valueOf(-1)) == 0){
                            total = smallest.add(highest);
                        }
                    } else if(beginning.compareTo(firstNbWithoutProprety) == 1){
                        break;
                    }
                }
            }
        }
        System.out.println("total: " + total);
    }

    public static final String PUZZLE_TEST = "35\n" +
            "20\n" +
            "15\n" +
            "25\n" +
            "47\n" +
            "40\n" +
            "62\n" +
            "55\n" +
            "65\n" +
            "95\n" +
            "102\n" +
            "117\n" +
            "150\n" +
            "182\n" +
            "127\n" +
            "219\n" +
            "299\n" +
            "277\n" +
            "309\n" +
            "576";
    public static final String PUZZLE_INPUT = "48\n" +
            "34\n" +
            "42\n" +
            "10\n" +
            "36\n" +
            "30\n" +
            "12\n" +
            "31\n" +
            "38\n" +
            "1\n" +
            "37\n" +
            "9\n" +
            "16\n" +
            "26\n" +
            "20\n" +
            "23\n" +
            "13\n" +
            "5\n" +
            "39\n" +
            "14\n" +
            "4\n" +
            "32\n" +
            "21\n" +
            "17\n" +
            "22\n" +
            "6\n" +
            "7\n" +
            "19\n" +
            "8\n" +
            "62\n" +
            "69\n" +
            "41\n" +
            "10\n" +
            "18\n" +
            "15\n" +
            "11\n" +
            "27\n" +
            "53\n" +
            "25\n" +
            "9\n" +
            "29\n" +
            "83\n" +
            "12\n" +
            "13\n" +
            "28\n" +
            "14\n" +
            "33\n" +
            "16\n" +
            "46\n" +
            "30\n" +
            "24\n" +
            "38\n" +
            "17\n" +
            "39\n" +
            "93\n" +
            "19\n" +
            "20\n" +
            "21\n" +
            "22\n" +
            "26\n" +
            "23\n" +
            "35\n" +
            "31\n" +
            "36\n" +
            "34\n" +
            "25\n" +
            "27\n" +
            "29\n" +
            "47\n" +
            "37\n" +
            "40\n" +
            "41\n" +
            "43\n" +
            "42\n" +
            "44\n" +
            "46\n" +
            "67\n" +
            "38\n" +
            "45\n" +
            "39\n" +
            "48\n" +
            "71\n" +
            "60\n" +
            "49\n" +
            "50\n" +
            "52\n" +
            "56\n" +
            "54\n" +
            "59\n" +
            "62\n" +
            "95\n" +
            "64\n" +
            "66\n" +
            "93\n" +
            "80\n" +
            "79\n" +
            "81\n" +
            "88\n" +
            "91\n" +
            "83\n" +
            "100\n" +
            "77\n" +
            "84\n" +
            "87\n" +
            "126\n" +
            "114\n" +
            "120\n" +
            "99\n" +
            "101\n" +
            "102\n" +
            "106\n" +
            "136\n" +
            "141\n" +
            "121\n" +
            "143\n" +
            "236\n" +
            "145\n" +
            "379\n" +
            "187\n" +
            "157\n" +
            "156\n" +
            "199\n" +
            "243\n" +
            "160\n" +
            "219\n" +
            "161\n" +
            "164\n" +
            "232\n" +
            "188\n" +
            "205\n" +
            "331\n" +
            "282\n" +
            "320\n" +
            "203\n" +
            "245\n" +
            "227\n" +
            "257\n" +
            "407\n" +
            "346\n" +
            "664\n" +
            "301\n" +
            "302\n" +
            "451\n" +
            "313\n" +
            "317\n" +
            "435\n" +
            "393\n" +
            "348\n" +
            "321\n" +
            "325\n" +
            "349\n" +
            "352\n" +
            "574\n" +
            "391\n" +
            "634\n" +
            "797\n" +
            "430\n" +
            "700\n" +
            "502\n" +
            "575\n" +
            "648\n" +
            "582\n" +
            "1130\n" +
            "923\n" +
            "603\n" +
            "1316\n" +
            "615\n" +
            "630\n" +
            "1623\n" +
            "638\n" +
            "743\n" +
            "646\n" +
            "755\n" +
            "670\n" +
            "967\n" +
            "779\n" +
            "1188\n" +
            "821\n" +
            "893\n" +
            "932\n" +
            "1564\n" +
            "1060\n" +
            "1666\n" +
            "1213\n" +
            "1157\n" +
            "1185\n" +
            "1931\n" +
            "1261\n" +
            "1370\n" +
            "1401\n" +
            "1245\n" +
            "1253\n" +
            "1276\n" +
            "1284\n" +
            "1308\n" +
            "1687\n" +
            "1316\n" +
            "1425\n" +
            "1449\n" +
            "1600\n" +
            "1672\n" +
            "1714\n" +
            "2583\n" +
            "2138\n" +
            "2193\n" +
            "2872\n" +
            "2217\n" +
            "2342\n" +
            "2438\n" +
            "2402\n" +
            "2430\n" +
            "2498\n" +
            "2959\n" +
            "2521\n" +
            "2529\n" +
            "2537\n" +
            "2569\n" +
            "3810\n" +
            "2592\n" +
            "3272\n" +
            "2988\n" +
            "2741\n" +
            "4121\n" +
            "5120\n" +
            "4038\n" +
            "3889\n" +
            "7558\n" +
            "4331\n" +
            "7462\n" +
            "4832\n" +
            "4559\n" +
            "6152\n" +
            "4744\n" +
            "4840\n" +
            "10127\n" +
            "4928\n" +
            "5019\n" +
            "5050\n" +
            "5058\n" +
            "5278\n" +
            "5129\n" +
            "5161\n" +
            "6798\n" +
            "5333\n" +
            "12582\n" +
            "9303\n" +
            "15020\n" +
            "8452\n" +
            "7927\n" +
            "8220\n" +
            "11210\n" +
            "9163\n" +
            "9350\n" +
            "9391\n" +
            "9688\n" +
            "13292\n" +
            "9584\n" +
            "9672\n" +
            "9890\n" +
            "9947\n" +
            "9978\n" +
            "10069\n" +
            "14862\n" +
            "16379\n" +
            "15108\n" +
            "21188\n" +
            "10494\n" +
            "14636\n" +
            "19653\n" +
            "17090\n" +
            "16147\n" +
            "16672\n" +
            "20078\n" +
            "17277\n" +
            "17383\n" +
            "20898\n" +
            "18934\n" +
            "18741\n" +
            "19868\n" +
            "19272\n" +
            "20441\n" +
            "19256\n" +
            "28824\n" +
            "19837\n" +
            "20016\n" +
            "28810\n" +
            "20563\n" +
            "30147\n" +
            "26873\n" +
            "26641\n" +
            "25130\n" +
            "39884\n" +
            "37653\n" +
            "32819\n" +
            "35944\n" +
            "35403\n" +
            "33949\n" +
            "34660\n" +
            "36018\n" +
            "36124\n" +
            "37675\n" +
            "75328\n" +
            "52075\n" +
            "60447\n" +
            "45897\n" +
            "64842\n" +
            "39272\n" +
            "62765\n" +
            "39853\n" +
            "40579\n" +
            "52003\n" +
            "56788\n" +
            "62585\n" +
            "51771\n" +
            "57949\n" +
            "98100\n" +
            "73221\n" +
            "66768\n" +
            "67479\n" +
            "119373\n" +
            "68609\n" +
            "102685\n" +
            "91448\n" +
            "122791\n" +
            "106621\n" +
            "192594\n" +
            "79125\n" +
            "79851\n" +
            "91275\n" +
            "80432\n" +
            "91043\n" +
            "149224\n" +
            "91624\n" +
            "91856\n" +
            "143451\n" +
            "200239\n" +
            "114356\n" +
            "109720\n" +
            "118539\n" +
            "124717\n" +
            "135377\n" +
            "146604\n" +
            "134247\n" +
            "309959\n" +
            "147734\n" +
            "170749\n" +
            "238062\n" +
            "273941\n" +
            "237879\n" +
            "158976\n" +
            "159557\n" +
            "170894\n" +
            "160283\n" +
            "171475\n" +
            "182667\n" +
            "274291\n" +
            "183480\n" +
            "201344\n" +
            "201576\n" +
            "224076\n" +
            "232895\n" +
            "228259\n" +
            "373256\n" +
            "243256\n" +
            "258964\n" +
            "294338\n" +
            "280851\n" +
            "434239\n" +
            "306710\n" +
            "318483\n" +
            "402813\n" +
            "434574\n" +
            "456958\n" +
            "318533\n" +
            "721296\n" +
            "478040\n" +
            "519877\n" +
            "331758\n" +
            "354142\n" +
            "384824\n" +
            "385056\n" +
            "407556\n" +
            "539605\n" +
            "425652\n" +
            "597398\n" +
            "565674\n" +
            "549966\n" +
            "637016\n" +
            "502220\n" +
            "703307\n" +
            "575189\n" +
            "587561\n" +
            "625193\n" +
            "734571\n" +
            "819630\n" +
            "1050845\n" +
            "951540\n" +
            "650291\n" +
            "1156893\n" +
            "685900\n" +
            "716582\n" +
            "779794\n" +
            "935022\n" +
            "960013\n" +
            "950730\n" +
            "1402482\n" +
            "1474627\n" +
            "1023050\n" +
            "927872\n" +
            "1052186\n" +
            "1067894\n" +
            "1077409\n" +
            "1420471\n" +
            "1089781\n" +
            "1726357\n" +
            "1162750\n" +
            "1212754\n" +
            "1384862\n" +
            "1430085\n" +
            "2070762\n" +
            "1465694\n" +
            "1336191\n" +
            "2027907\n" +
            "2474643\n" +
            "1620922\n" +
            "2146667\n" +
            "2488744\n" +
            "2380484\n" +
            "1878602\n" +
            "1950922\n" +
            "2129595\n" +
            "1980058\n" +
            "1995766\n" +
            "2264940\n" +
            "2482271\n" +
            "2497880\n" +
            "2167190\n" +
            "3867133\n" +
            "2302535\n" +
            "2628444\n" +
            "2498941\n" +
            "3874368\n" +
            "3883803\n" +
            "3858660\n" +
            "2801885\n" +
            "4181137\n" +
            "2957113\n" +
            "3788112\n" +
            "4376482\n" +
            "3600980\n" +
            "4432130\n" +
            "3829524\n" +
            "3930980\n" +
            "4125361\n" +
            "3946688\n" +
            "5758998\n" +
            "5259648\n" +
            "5104420\n" +
            "4567475\n" +
            "4469725\n" +
            "4666131\n" +
            "4795634\n" +
            "7369360\n" +
            "4801476\n" +
            "7977462\n" +
            "7906305\n" +
            "6402865\n" +
            "13736460\n" +
            "6558093\n" +
            "6589997\n" +
            "8127825\n" +
            "6786637\n" +
            "21642765\n" +
            "8070705\n" +
            "7430504\n" +
            "7760504\n" +
            "14717822\n" +
            "7877668\n" +
            "9900054\n" +
            "8416413\n" +
            "9037200\n" +
            "15638172\n" +
            "9135856\n" +
            "9233606\n" +
            "12376030\n" +
            "9461765\n" +
            "9597110\n" +
            "11359569\n" +
            "11204341\n" +
            "14819278\n" +
            "12960958\n" +
            "19120073\n" +
            "21088783\n" +
            "13148090\n" +
            "13376634\n" +
            "14547141\n" +
            "14857342\n" +
            "15191008\n" +
            "15948373\n" +
            "15846917\n" +
            "16176917\n" +
            "30704259\n" +
            "17474778\n" +
            "24757396\n" +
            "17453613\n" +
            "18173056\n" +
            "18369462\n" +
            "18597621\n" +
            "31330420\n" +
            "19058875\n" +
            "20666106\n" +
            "31558579\n" +
            "22563910\n" +
            "24352431\n" +
            "26337592\n" +
            "26109048\n" +
            "30048350\n" +
            "42285965\n" +
            "26524724\n" +
            "35523448\n" +
            "53696504\n" +
            "34121429\n" +
            "33630530\n" +
            "31795290\n" +
            "36513023\n" +
            "33651695\n" +
            "41622785\n" +
            "34928391\n" +
            "44697780\n" +
            "35626669\n" +
            "36542518\n" +
            "36967083\n" +
            "37656496\n" +
            "72490531\n" +
            "39724981\n" +
            "43230016\n" +
            "56685339\n" +
            "77809413\n" +
            "72593752\n" +
            "52446640\n" +
            "86733689\n" +
            "56573074\n" +
            "68762373\n" +
            "63491807\n" +
            "65425820\n" +
            "70618778\n" +
            "104740207\n" +
            "65446985\n" +
            "66723681\n" +
            "72139692\n" +
            "88989158\n" +
            "101148303\n" +
            "170187192\n" +
            "94229570\n" +
            "72169187\n" +
            "198969777\n" +
            "95676656\n" +
            "123065418\n" +
            "105171966\n" +
            "82954997\n" +
            "128854526\n" +
            "109019714\n" +
            "115938447\n" +
            "137616172\n" +
            "128917627\n" +
            "128712766\n" +
            "120064881\n" +
            "137565512\n" +
            "161158345\n" +
            "130872805\n" +
            "132170666\n" +
            "138892868\n" +
            "195234605\n" +
            "138863373\n" +
            "144308879\n" +
            "155124184\n" +
            "166398757\n" +
            "221110413\n" +
            "300051213\n" +
            "177341153\n" +
            "188126963\n" +
            "251983045\n" +
            "191974711\n" +
            "203019878\n" +
            "341883251\n" +
            "167829540\n" +
            "224958161\n" +
            "267810495\n" +
            "419812585\n" +
            "248777647\n" +
            "250937686\n" +
            "252235547\n" +
            "268438317\n" +
            "263043471\n" +
            "361633362\n" +
            "271034039\n" +
            "277756241\n" +
            "508282008\n" +
            "387509170\n" +
            "299433063\n" +
            "321522941\n" +
            "334228297\n" +
            "435640035\n" +
            "345170693\n" +
            "355956503\n" +
            "359804251\n" +
            "586211342\n" +
            "370849418\n" +
            "687053944\n" +
            "644770746\n" +
            "596108379\n" +
            "602038792\n" +
            "619627065\n" +
            "499715333\n" +
            "686942233\n" +
            "528693927\n" +
            "679398990\n" +
            "534077510\n" +
            "771325479\n" +
            "548790280\n" +
            "1085926675\n" +
            "677479444\n" +
            "620956004\n" +
            "701127196\n" +
            "1221131454\n" +
            "655751238\n" +
            "1046858195\n" +
            "1702609433\n" +
            "704974944\n" +
            "888498178\n" +
            "972888210\n" +
            "1082867790\n" +
            "1015620164\n" +
            "1028409260\n" +
            "1120671337\n" +
            "1616945300\n" +
            "1208092917\n" +
            "1320754261\n" +
            "1169746284\n" +
            "1575440411\n" +
            "1742170427\n" +
            "1226269724\n" +
            "1155033514\n" +
            "1427076717\n" +
            "1204541518\n" +
            "1775989518\n" +
            "1649365264\n" +
            "1593473122\n" +
            "2447401178\n" +
            "1360726182\n" +
            "1904118342\n" +
            "1677863154\n" +
            "2566361332\n" +
            "1720595108\n" +
            "2977671482\n" +
            "1988508374\n" +
            "2044029424\n" +
            "2389135442\n" +
            "2325212855\n" +
            "2275704851\n" +
            "2324779798\n" +
            "2359575032\n" +
            "2374287802\n" +
            "2582110231\n" +
            "2381303238\n" +
            "2430811242\n" +
            "2954199304\n" +
            "3020549839\n" +
            "3948147766\n" +
            "3010091446\n" +
            "4259973385\n" +
            "4706516093\n" +
            "3038589336\n" +
            "3742029420\n" +
            "3720301214\n" +
            "3398458262\n" +
            "3709103482\n" +
            "3764624532\n" +
            "4080170140\n" +
            "6346734763\n" +
            "4032537798\n" +
            "4403604456\n" +
            "4600484649\n" +
            "4600917706\n" +
            "4635279883\n" +
            "6729653321\n" +
            "4733862834\n" +
            "4812114480\n" +
            "4963413469\n" +
            "7016583121\n" +
            "6151112456\n" +
            "6437047598\n" +
            "7658431647\n" +
            "6408549708\n" +
            "6747692818\n" +
            "6758890550\n" +
            "8448545513\n" +
            "7118759476\n" +
            "7107561744\n" +
            "7163082794\n" +
            "9367017925\n" +
            "7473728014\n" +
            "10554716912\n" +
            "11566687250\n" +
            "8436142254\n" +
            "9413032186\n" +
            "9004089105\n" +
            "11400461067\n" +
            "9236197589\n" +
            "9447394363\n" +
            "9545977314\n" +
            "11722304019\n" +
            "15954527022\n" +
            "11114525925\n" +
            "13195938148\n" +
            "16106977160\n" +
            "13882277722\n" +
            "13167440258\n" +
            "13910775612\n" +
            "13866452294\n" +
            "13877650026\n" +
            "19558806017\n" +
            "14226321220\n" +
            "14270644538\n" +
            "14636810808\n" +
            "18588253939\n" +
            "15909870268\n" +
            "18417121291\n" +
            "17440231359\n" +
            "18240286694\n" +
            "28795003606\n" +
            "22432135737\n" +
            "25455847582\n" +
            "18683591952\n" +
            "18993371677\n" +
            "22836829944\n" +
            "24310464073\n" +
            "24281966183\n" +
            "24996803647\n" +
            "30180514806\n" +
            "27033892552\n" +
            "28863132028\n" +
            "34195616825\n" +
            "27777227906\n" +
            "27744102320\n" +
            "28103971246\n" +
            "40849257028\n" +
            "31666552579\n" +
            "33053932099\n" +
            "30546681076\n" +
            "39872367096\n" +
            "33350101627\n" +
            "35680518053\n" +
            "36123823311\n" +
            "45268965681\n" +
            "46770599583\n" +
            "59443780485\n" +
            "64182831169\n" +
            "60087824651\n" +
            "53100774893\n" +
            "47118796127\n" +
            "92286802415\n" +
            "49278769830\n" +
            "52030696199\n" +
            "54777994872\n" +
            "79774798519\n" +
            "55521330226\n" +
            "55848073566\n" +
            "95720440662\n" +
            "58290783396\n" +
            "87547751731\n" +
            "62213233655\n" +
            "63896782703\n" +
            "143957629688\n" +
            "95563335282\n" +
            "69030619680\n" +
            "95768342704\n" +
            "107569553226\n" +
            "81392788992\n" +
            "147064797287\n" +
            "93889395710\n" +
            "96397565957\n" +
            "146990170603\n" +
            "101309466029\n" +
            "99149492326\n" +
            "101896790999\n" +
            "104056764702\n" +
            "107878769765\n" +
            "106808691071\n" +
            "117734563881\n" +
            "111369403792\n" +
            "119418112929\n" +
            "114138856962\n" +
            "156102629365\n" +
            "120504017051\n" +
            "163046275029\n" +
            "131243853335\n" +
            "132927402383\n" +
            "150423408672\n" +
            "162920015390\n" +
            "164798962384\n" +
            "212636642327\n" +
            "269854966100\n" +
            "276168366176\n" +
            "197707031986\n" +
            "193038888036\n" +
            "195547058283\n" +
            "200458958355\n" +
            "201046283325\n" +
            "213266194791\n" +
            "297095652738\n" +
            "210865455773\n" +
            "214687460836\n" +
            "218178094863\n" +
            "225508260754\n" +
            "250661966264\n" +
            "233556969891\n" +
            "270927425723\n" +
            "316051075334\n" +
            "391431442774\n" +
            "264171255718\n" +
            "283350811055\n" +
            "366484372274\n" +
            "421055319037\n" +
            "327718977774\n" +
            "357837850420\n" +
            "688527095512\n" +
            "388585946319\n" +
            "471386384078\n" +
            "390745920022\n" +
            "393497846391\n" +
            "396006016638\n" +
            "401505241680\n" +
            "411911739098\n" +
            "424131650564\n" +
            "451735064754\n" +
            "425552916609\n" +
            "432865555699\n" +
            "489105520586\n" +
            "735085875809\n" +
            "561275947665\n" +
            "504484395614\n" +
            "535098681441\n" +
            "547522066773\n" +
            "780017389093\n" +
            "665676497398\n" +
            "611069788829\n" +
            "685556828194\n" +
            "723724994412\n" +
            "807917755736\n" +
            "746423796739\n" +
            "779331866341\n" +
            "895230315636\n" +
            "784243766413\n" +
            "940840585340\n" +
            "789503863029\n" +
            "827058158289\n" +
            "825636892244\n" +
            "836043389662\n" +
            "849684567173\n" +
            "858418472308\n" +
            "914658437195\n" +
            "1082620748214\n" +
            "1720172451681\n" +
            "1722576192931\n" +
            "1039583077055\n" +
            "1052006462387\n" +
            "1685476630597\n" +
            "1515361064571\n" +
            "1276746286227\n" +
            "1334794783241\n" +
            "1296626617023\n" +
            "1582143466720\n" +
            "1786006873794\n" +
            "2390061222456\n" +
            "1525755663080\n" +
            "1563575632754\n" +
            "1573747629442\n" +
            "1609880658657\n" +
            "1615140755273\n" +
            "1676742725462\n" +
            "1954241514250\n" +
            "1750701826857\n" +
            "1685727956835\n" +
            "1708103039481\n" +
            "2386801245628\n" +
            "2860550446321\n" +
            "3408052823528\n" +
            "5362294337778\n" +
            "2091589539442\n" +
            "2316329363282\n" +
            "3089108694013\n" +
            "2573372903250\n" +
            "2611541069468\n" +
            "2886626944884\n" +
            "2631421400264\n" +
            "2822382280103\n" +
            "4386306109401\n" +
            "3089331295834\n" +
            "5981425726778\n" +
            "4594729984365\n" +
            "3137323262196\n" +
            "3183628288099\n" +
            "3225021413930\n" +
            "5682932726424\n" +
            "3842291366299\n" +
            "3393830996316\n" +
            "3436429783692\n" +
            "3777317496277\n" +
            "3799692578923\n" +
            "4952139985763\n" +
            "4407918902724\n" +
            "4664962442692\n" +
            "6067851183956\n" +
            "5459999848134\n" +
            "4889702266532\n" +
            "5184913972718\n" +
            "5453803680367\n" +
            "5959705542299\n" +
            "6937015841119\n" +
            "5720752696098\n" +
            "12918441567897\n" +
            "6226654558030\n" +
            "6272959583933\n" +
            "6362344676126\n" +
            "6320951550295\n" +
            "8177161399693\n" +
            "10634573460754\n" +
            "9504280967648\n" +
            "6830260780008\n" +
            "9861722583091\n" +
            "9617102428455\n" +
            "22535543996352\n" +
            "7577010075200\n" +
            "8207611481647\n" +
            "9072881345416\n" +
            "15890062012388\n" +
            "13928364177745\n" +
            "10074616239250\n" +
            "12390819521486\n" +
            "11144619515017\n" +
            "11457873556651\n" +
            "11816148356493\n" +
            "22211013562683\n" +
            "19649116873843\n" +
            "13103220363941\n" +
            "12499614141963\n" +
            "14450120983626\n" +
            "12593911134228\n" +
            "13192605456134\n" +
            "13151212330303\n" +
            "24067223412081\n" +
            "14407270855208\n" +
            "15903142125424\n" +
            "29510839456934\n" +
            "15784621556847\n" +
            "16649891420616\n" +
            "17280492827063\n" +
            "17651626314450\n" +
            "23274021913144\n" +
            "19147497584666\n" +
            "21219235754267\n" +
            "21532489795901\n" +
            "37259828868215\n" +
            "22602493071668\n" +
            "22960767871510\n" +
            "23957487698614\n" +
            "27044032117854\n" +
            "42703444913068\n" +
            "25093525276191\n" +
            "25602834505904\n" +
            "25650826472266\n" +
            "31687763682271\n" +
            "41451302973062\n" +
            "40878146833038\n" +
            "27558483185511\n" +
            "42751725550168\n" +
            "36427990411729\n" +
            "32434512977463\n" +
            "34301517735066\n" +
            "33065114383910\n" +
            "33930384247679\n" +
            "34932119141513\n" +
            "53077881832345\n" +
            "40679987380567\n" +
            "68231901982745\n" +
            "46822070260171\n" +
            "44134982867569\n" +
            "102533419717811\n" +
            "48563602377414\n" +
            "53209309657777\n" +
            "59023909523870\n" +
            "60025644417704\n" +
            "88273373233233\n" +
            "50696359782095\n" +
            "51253660978170\n" +
            "71693466053080\n" +
            "59246246867782\n" +
            "100903791250742\n" +
            "59992996162974\n";
}
