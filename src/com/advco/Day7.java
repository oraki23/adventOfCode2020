package com.advco;

import java.util.Arrays;
import java.util.List;

public class Day7 {
    public static void main(String[] args) {
        partTwo();
    }

    public static void partOne(){
        List<String> bags = Arrays.asList(PUZZLE_INPUT.split(".\n"));
        String myBag = "shiny gold";

        int nbOfBagColor = 0;

        for(String bag : bags){
            String bagName = bag.substring(0,bag.indexOf("contain"));
            List<String> containedBags = Arrays.asList(bag.substring(bag.indexOf("contain")+8).split(", "));

            System.out.println(bagName);
            System.out.println(containedBags);
            if(canContainBag(bags, containedBags, myBag) == true){
                nbOfBagColor++;
            }

        }
        System.out.println("nb of bag color: " + nbOfBagColor);
    }

    public static boolean canContainBag(List<String> listOfBags, List<String> containedBags, String bagName){
        System.out.println("Start of canContainBag: " + containedBags);

        if(containedBags.contains("no other bags")){
            System.out.println("return false");
            return false;
        }
        for(String containedBagName : containedBags){
            if(containedBagName.contains(bagName)){
                System.out.println("return true");
                return true;
            }
        }


        boolean canContainBag = false;

        for(String containedBagName : containedBags){
            containedBagName = containedBagName.substring(2);
            for(String bag : listOfBags){
                String bagNameList = bag.substring(0,bag.indexOf("contain"));
                if(bagNameList.contains(containedBagName)){
                    containedBagName = bag;
                    List<String> containedBagsList = Arrays.asList(containedBagName.substring(bag.indexOf("contain")+8).split(", "));
                    canContainBag = canContainBag(listOfBags, containedBagsList, bagName);
                    if(canContainBag == true){
                        break;
                    }
                }
            }
            if (canContainBag) {
                break;
            }
        }

        return canContainBag;
    }

    public static int nbOfIndividualBag = 0;
    public static void partTwoFake(){
        List<String> bags = Arrays.asList(PUZZLE_INPUT.split(".\n"));
        String myBag = "1 shiny gold bag";

       /* nbOfIndividualBag = nbOfContainedBags(bags, Arrays.asList(myBag))-1;*/
        String stuff = nbOfContainedBagsFake(bags, Arrays.asList(myBag));
        System.out.println(stuff);
        System.out.println(stuff.split("\n").length-1);
        for(String bag : bags){
            if(bag.contains(myBag)){
                /*List<String> containedBags = Arrays.asList(bag.substring(bag.indexOf("contain")+8).split(", "));
                System.out.println(containedBags);

                nbOfIndividualBag = nbOfContainedBags(bags, containedBags);
                for(String containedBagName : containedBags){
                    int nbOfContainedBagName = Integer.parseInt(containedBagName.substring(0,1));
                    nbOfIndividualBag += nbOfContainedBagName;
                }*/
            }
        }
        System.out.println("nb of indiv bag: " + nbOfIndividualBag);
    }

    public static String nbOfContainedBagsFake(List<String> listOfBags, List<String> containedBags){

        if(containedBags.contains("no other bags") || containedBags.contains("no other bags.")){
            return "no other bags";
        }

        int nbOfContainedBag = 0;
        String output = "";
        for(String containedBagName : containedBags){
            int nbOfContainedBagName = Integer.parseInt(containedBagName.substring(0,1));
            int nbOfContainedBagInBag = 0;
            String value = String.copyValueOf(containedBagName.toCharArray());
            containedBagName = containedBagName.substring(2);

            for(String bag : listOfBags){
                String bagNameList = bag.substring(0,bag.indexOf("contain"));
                if(bagNameList.contains(containedBagName)){
                    containedBagName = bag;

                    List<String> containedBagsList = Arrays.asList(containedBagName.substring(bag.indexOf("contain")+8).split(", "));
                    String nb = nbOfContainedBagsFake(listOfBags, containedBagsList);
                   /* int nb = nbOfContainedBags(listOfBags, containedBagsList);*/
                    int nbToPrint = Integer.parseInt(value.substring(0,1));
                    int val = 0;
                    String concatBagName = value.substring(2);
                    for(int i = 0; i < nbToPrint; i++){
                        output = output + concatBagName + '\n';
                        String[] test = nb.split("\n");
                        for(int j = 0; j < test.length; j++){
                            if(!test[j].equals("no other bags")){
                                output = output + '\t' + test[j] + '\n';
                            }
                        }
                    }
                }
            }
        }

        return output;
    }

    public static void partTwo(){
        List<String> bags = Arrays.asList(PUZZLE_INPUT.split(".\n"));
        String myBag = "1 shiny gold bag";

        nbOfIndividualBag = nbOfContainedBags(bags, Arrays.asList(myBag))-1;

        System.out.println("nb of indiv bag: " + nbOfIndividualBag);
    }

    public static int nbOfContainedBags(List<String> listOfBags, List<String> containedBags){

        if(containedBags.contains("no other bags") || containedBags.contains("no other bags.")){
            return 0;
        }

        int nbOfContainedBag = 0;
        for(String containedBagName : containedBags){
            int nbOfContainedBagName = Integer.parseInt(containedBagName.substring(0,1));
            int nbOfContainedBagInBag = 0;
            containedBagName = containedBagName.substring(2);

            for(String bag : listOfBags){
                String bagNameList = bag.substring(0,bag.indexOf("contain"));
                if(bagNameList.contains(containedBagName)){
                    containedBagName = bag;

                    List<String> containedBagsList = Arrays.asList(containedBagName.substring(bag.indexOf("contain")+8).split(", "));
                    int nb = nbOfContainedBags(listOfBags, containedBagsList);

                    nbOfContainedBagInBag += nbOfContainedBagName * nb;
                }
            }
            nbOfContainedBag += nbOfContainedBagName + nbOfContainedBagInBag;
        }
        return nbOfContainedBag;
    }
    public static final String PUZZLE_TEST = "light red bags contain 1 bright white bag, 2 muted yellow bags.\n" +
            "dark orange bags contain 3 bright white bags, 4 muted yellow bags.\n" +
            "bright white bags contain 1 shiny gold bag.\n" +
            "muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.\n" +
            "shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.\n" +
            "dark olive bags contain 3 faded blue bags, 4 dotted black bags.\n" +
            "vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.\n" +
            "faded blue bags contain no other bags.\n" +
            "dotted black bags contain no other bags.";
    public static final String PUZZLE_TEST_2 = "shiny gold bags contain 2 dark red bags.\n" +
            "dark red bags contain 2 dark orange bags.\n" +
            "dark orange bags contain 2 dark yellow bags.\n" +
            "dark yellow bags contain 2 dark green bags.\n" +
            "dark green bags contain 2 dark blue bags.\n" +
            "dark blue bags contain 2 dark violet bags.\n" +
            "dark violet bags contain no other bags.";
    public static final String PUZZLE_INPUT = "dark olive bags contain 2 muted brown bags, 1 mirrored tomato bag, 4 bright black bags.\n" +
            "faded coral bags contain 3 drab cyan bags, 1 light aqua bag.\n" +
            "plaid plum bags contain 2 mirrored cyan bags.\n" +
            "clear maroon bags contain 1 dotted turquoise bag, 3 dim lavender bags.\n" +
            "plaid coral bags contain 3 posh fuchsia bags, 3 dotted beige bags, 2 wavy turquoise bags.\n" +
            "mirrored indigo bags contain 5 pale orange bags, 5 striped aqua bags, 1 dotted cyan bag, 4 muted coral bags.\n" +
            "striped brown bags contain 4 faded green bags, 5 vibrant red bags, 3 drab black bags, 3 dark orange bags.\n" +
            "drab fuchsia bags contain 5 shiny chartreuse bags, 1 dark orange bag, 4 drab blue bags, 1 bright chartreuse bag.\n" +
            "light fuchsia bags contain 3 bright gold bags, 5 clear indigo bags.\n" +
            "plaid olive bags contain 4 striped indigo bags, 4 muted olive bags, 2 light gray bags, 2 wavy purple bags.\n" +
            "dotted green bags contain 2 faded yellow bags.\n" +
            "dotted cyan bags contain 1 light red bag, 5 dark cyan bags.\n" +
            "muted magenta bags contain 2 clear plum bags.\n" +
            "mirrored cyan bags contain 4 bright lavender bags, 3 mirrored gold bags, 2 plaid silver bags, 1 posh chartreuse bag.\n" +
            "muted red bags contain 2 wavy olive bags, 4 shiny cyan bags.\n" +
            "clear indigo bags contain 2 dotted magenta bags, 4 bright silver bags, 4 muted aqua bags.\n" +
            "plaid chartreuse bags contain 3 dark chartreuse bags, 1 faded lavender bag.\n" +
            "wavy tan bags contain 3 light red bags.\n" +
            "bright crimson bags contain 4 pale magenta bags.\n" +
            "pale yellow bags contain 3 clear plum bags, 2 vibrant cyan bags, 2 muted white bags.\n" +
            "pale tomato bags contain 2 muted red bags.\n" +
            "dull salmon bags contain 3 dark gold bags.\n" +
            "light white bags contain 2 drab chartreuse bags.\n" +
            "drab plum bags contain 1 dark brown bag.\n" +
            "shiny purple bags contain 3 pale brown bags, 1 bright crimson bag, 5 muted teal bags.\n" +
            "pale red bags contain 5 posh cyan bags, 2 drab cyan bags.\n" +
            "dull bronze bags contain 2 striped indigo bags, 4 plaid black bags, 3 clear violet bags, 1 dull chartreuse bag.\n" +
            "wavy indigo bags contain 2 dull coral bags, 1 dark tan bag.\n" +
            "vibrant silver bags contain 5 muted aqua bags.\n" +
            "shiny tan bags contain 4 pale beige bags, 4 bright gold bags, 5 muted coral bags, 3 shiny red bags.\n" +
            "dim yellow bags contain 1 dotted chartreuse bag.\n" +
            "faded white bags contain 5 posh lavender bags, 3 bright silver bags, 2 dark green bags, 5 muted lavender bags.\n" +
            "shiny tomato bags contain 4 mirrored chartreuse bags, 2 bright yellow bags, 4 vibrant plum bags.\n" +
            "dark yellow bags contain 3 plaid lime bags, 4 wavy lavender bags.\n" +
            "dim magenta bags contain 3 wavy violet bags.\n" +
            "striped white bags contain 5 striped chartreuse bags.\n" +
            "muted gray bags contain 5 muted brown bags, 5 light lavender bags, 2 clear gold bags, 1 shiny green bag.\n" +
            "drab white bags contain 4 muted yellow bags, 3 posh lavender bags, 3 faded fuchsia bags.\n" +
            "dark lime bags contain 1 bright crimson bag.\n" +
            "muted salmon bags contain 4 dull red bags, 1 dull violet bag.\n" +
            "muted black bags contain 2 vibrant black bags, 5 pale tomato bags.\n" +
            "plaid white bags contain 4 drab black bags, 4 drab cyan bags, 1 dim olive bag.\n" +
            "clear green bags contain 3 wavy purple bags, 1 pale gold bag.\n" +
            "drab orange bags contain 4 clear maroon bags, 3 vibrant gold bags.\n" +
            "light salmon bags contain 1 pale black bag, 2 posh fuchsia bags.\n" +
            "faded tomato bags contain 5 faded black bags, 3 vibrant coral bags, 3 bright brown bags.\n" +
            "dull green bags contain 5 clear lime bags.\n" +
            "dim orange bags contain 5 posh red bags, 2 dim gray bags, 2 muted gold bags.\n" +
            "dotted orange bags contain 1 faded tan bag, 2 drab tomato bags.\n" +
            "dull gold bags contain 5 muted gold bags.\n" +
            "clear beige bags contain 5 plaid tan bags, 4 dim maroon bags.\n" +
            "mirrored bronze bags contain 1 clear blue bag, 2 clear tan bags, 3 clear orange bags.\n" +
            "shiny brown bags contain 5 light tomato bags.\n" +
            "pale cyan bags contain 3 dull silver bags.\n" +
            "muted purple bags contain 4 posh brown bags.\n" +
            "pale maroon bags contain 1 clear maroon bag, 5 vibrant yellow bags, 1 shiny violet bag.\n" +
            "muted green bags contain 5 plaid plum bags.\n" +
            "light coral bags contain 5 bright lime bags, 1 dotted indigo bag, 5 shiny bronze bags.\n" +
            "wavy beige bags contain 5 light magenta bags, 3 pale lime bags, 1 faded yellow bag.\n" +
            "wavy turquoise bags contain 5 shiny red bags, 3 mirrored yellow bags, 2 posh bronze bags.\n" +
            "pale brown bags contain 2 striped salmon bags, 4 vibrant plum bags, 2 dull silver bags.\n" +
            "striped bronze bags contain 3 posh black bags, 2 bright yellow bags, 3 dotted silver bags, 1 mirrored violet bag.\n" +
            "pale tan bags contain 4 shiny lavender bags, 3 dotted turquoise bags, 3 pale turquoise bags, 5 plaid white bags.\n" +
            "muted crimson bags contain 4 dull lime bags, 4 pale magenta bags, 2 light cyan bags.\n" +
            "dotted purple bags contain 2 wavy magenta bags.\n" +
            "dull black bags contain 3 light crimson bags, 3 mirrored fuchsia bags, 3 posh lime bags.\n" +
            "mirrored aqua bags contain 1 dark brown bag, 5 pale gold bags, 2 pale purple bags, 2 vibrant crimson bags.\n" +
            "shiny beige bags contain 2 muted indigo bags, 2 dark tan bags.\n" +
            "shiny aqua bags contain 5 vibrant tan bags, 2 pale tomato bags, 2 faded blue bags, 4 pale magenta bags.\n" +
            "mirrored lavender bags contain 4 faded yellow bags, 3 light chartreuse bags, 2 dull crimson bags, 1 pale aqua bag.\n" +
            "faded gray bags contain 5 bright yellow bags, 4 light silver bags.\n" +
            "light tan bags contain 3 bright fuchsia bags, 3 light crimson bags, 3 clear olive bags, 1 clear silver bag.\n" +
            "dull crimson bags contain 3 wavy olive bags, 1 light maroon bag.\n" +
            "pale olive bags contain 4 vibrant purple bags, 3 clear fuchsia bags, 2 pale tan bags.\n" +
            "dim beige bags contain 3 dull silver bags.\n" +
            "dark bronze bags contain 1 mirrored orange bag, 2 wavy crimson bags, 1 dark maroon bag.\n" +
            "mirrored fuchsia bags contain 1 dark chartreuse bag, 1 dotted bronze bag, 5 shiny silver bags.\n" +
            "striped tomato bags contain 1 dark fuchsia bag, 5 vibrant maroon bags, 4 drab crimson bags.\n" +
            "muted chartreuse bags contain 1 light tomato bag, 3 light magenta bags, 3 dull beige bags.\n" +
            "muted violet bags contain 5 light tomato bags.\n" +
            "dim violet bags contain 2 mirrored purple bags, 3 bright black bags, 5 vibrant plum bags.\n" +
            "muted beige bags contain 4 muted plum bags, 4 mirrored gold bags, 5 bright yellow bags.\n" +
            "shiny coral bags contain 5 vibrant gray bags, 1 light bronze bag, 5 faded chartreuse bags, 2 plaid violet bags.\n" +
            "dotted yellow bags contain 5 dotted turquoise bags.\n" +
            "faded bronze bags contain 4 dull purple bags, 2 pale fuchsia bags.\n" +
            "clear fuchsia bags contain 5 bright yellow bags.\n" +
            "dotted beige bags contain 1 pale turquoise bag.\n" +
            "bright aqua bags contain 2 faded silver bags, 5 dim fuchsia bags.\n" +
            "dim silver bags contain 5 wavy brown bags, 1 light maroon bag, 2 wavy black bags.\n" +
            "plaid tan bags contain 3 dotted green bags.\n" +
            "drab teal bags contain 4 mirrored violet bags, 5 dotted bronze bags, 1 pale cyan bag.\n" +
            "plaid yellow bags contain 5 shiny magenta bags, 1 dark silver bag, 5 shiny indigo bags, 3 faded gray bags.\n" +
            "dull cyan bags contain 1 posh plum bag, 5 dim coral bags.\n" +
            "pale beige bags contain 3 dark cyan bags, 4 faded tan bags, 2 plaid silver bags.\n" +
            "faded salmon bags contain 2 light olive bags, 2 dark tan bags.\n" +
            "bright silver bags contain 1 clear fuchsia bag, 2 clear lime bags.\n" +
            "dark lavender bags contain 1 pale cyan bag, 5 dotted salmon bags, 1 striped turquoise bag, 2 dim crimson bags.\n" +
            "light gray bags contain 3 dotted chartreuse bags, 3 dull red bags, 4 bright gold bags, 2 light lime bags.\n" +
            "dark indigo bags contain 5 shiny coral bags, 1 muted teal bag, 2 plaid purple bags, 4 faded yellow bags.\n" +
            "light maroon bags contain 1 wavy brown bag.\n" +
            "dotted brown bags contain 1 clear violet bag.\n" +
            "pale indigo bags contain 1 wavy brown bag, 2 dark olive bags, 4 pale black bags, 5 striped lavender bags.\n" +
            "pale purple bags contain 5 bright chartreuse bags, 1 vibrant magenta bag, 4 clear tomato bags, 4 light cyan bags.\n" +
            "dim teal bags contain 2 dark salmon bags, 5 mirrored gold bags, 3 bright gold bags.\n" +
            "wavy olive bags contain 1 vibrant purple bag, 3 dull silver bags.\n" +
            "pale aqua bags contain 4 dark violet bags, 2 dark plum bags, 3 vibrant brown bags.\n" +
            "muted cyan bags contain 5 vibrant crimson bags, 3 pale magenta bags.\n" +
            "vibrant fuchsia bags contain 2 light beige bags, 5 bright purple bags, 3 light maroon bags, 4 dull beige bags.\n" +
            "bright blue bags contain 4 vibrant plum bags.\n" +
            "vibrant green bags contain 1 dark salmon bag.\n" +
            "dull red bags contain 2 dotted maroon bags, 1 posh salmon bag, 3 dotted chartreuse bags, 2 dim yellow bags.\n" +
            "dotted magenta bags contain 3 plaid violet bags, 3 light gray bags.\n" +
            "wavy bronze bags contain 1 posh plum bag, 2 bright lavender bags.\n" +
            "shiny orange bags contain 3 muted plum bags.\n" +
            "plaid bronze bags contain 5 plaid chartreuse bags, 3 mirrored turquoise bags, 3 light salmon bags.\n" +
            "shiny yellow bags contain 4 striped teal bags, 1 vibrant maroon bag, 1 drab indigo bag, 4 muted beige bags.\n" +
            "faded indigo bags contain 2 posh lime bags, 1 vibrant teal bag.\n" +
            "posh tomato bags contain 4 striped salmon bags, 3 drab red bags.\n" +
            "light plum bags contain 3 bright blue bags, 5 dotted black bags, 4 shiny brown bags, 2 clear tan bags.\n" +
            "pale green bags contain 4 clear maroon bags, 4 dull green bags, 5 clear aqua bags, 3 drab beige bags.\n" +
            "drab brown bags contain 1 bright plum bag, 5 striped indigo bags, 1 vibrant lime bag.\n" +
            "muted lime bags contain 3 faded tan bags, 5 vibrant magenta bags, 5 posh coral bags.\n" +
            "faded violet bags contain 1 posh purple bag, 4 muted crimson bags, 5 striped red bags.\n" +
            "striped green bags contain 4 clear chartreuse bags, 4 dim maroon bags, 1 faded cyan bag, 5 faded silver bags.\n" +
            "dim plum bags contain 1 faded tan bag, 5 vibrant crimson bags, 3 light gray bags, 2 wavy teal bags.\n" +
            "drab cyan bags contain 4 muted plum bags, 5 mirrored black bags, 4 clear fuchsia bags.\n" +
            "vibrant lime bags contain 2 pale brown bags, 5 plaid violet bags.\n" +
            "drab purple bags contain 1 posh turquoise bag, 4 clear violet bags, 5 faded coral bags, 1 striped gold bag.\n" +
            "mirrored gray bags contain 3 dotted orange bags, 4 mirrored tomato bags, 4 plaid lime bags.\n" +
            "plaid teal bags contain 2 dim coral bags, 5 shiny magenta bags, 5 striped beige bags.\n" +
            "vibrant white bags contain 2 shiny red bags, 1 plaid black bag.\n" +
            "faded crimson bags contain 2 faded yellow bags.\n" +
            "dim chartreuse bags contain 4 mirrored teal bags, 5 light lavender bags.\n" +
            "wavy magenta bags contain 3 dark cyan bags.\n" +
            "mirrored magenta bags contain 4 muted salmon bags, 4 pale maroon bags.\n" +
            "plaid silver bags contain 2 pale turquoise bags, 3 posh salmon bags.\n" +
            "bright cyan bags contain 3 dark orange bags, 1 mirrored gold bag, 1 light violet bag, 5 faded silver bags.\n" +
            "faded orange bags contain 3 drab red bags, 5 dark tan bags, 2 vibrant magenta bags.\n" +
            "wavy gold bags contain 4 drab tomato bags, 2 muted yellow bags.\n" +
            "wavy salmon bags contain 2 dotted bronze bags, 3 light white bags, 2 dotted brown bags.\n" +
            "posh plum bags contain 4 drab black bags, 1 light silver bag.\n" +
            "dim aqua bags contain 5 striped black bags, 1 bright yellow bag, 4 posh salmon bags, 3 striped salmon bags.\n" +
            "shiny fuchsia bags contain 2 dim cyan bags.\n" +
            "dark tan bags contain 3 dull plum bags.\n" +
            "mirrored red bags contain 3 pale lime bags, 3 dim maroon bags.\n" +
            "bright purple bags contain 4 posh magenta bags, 4 wavy magenta bags, 3 posh plum bags, 3 dull lime bags.\n" +
            "striped olive bags contain 3 bright lavender bags, 3 mirrored black bags, 3 bright gold bags, 2 pale plum bags.\n" +
            "dotted bronze bags contain 3 dull coral bags, 1 faded black bag.\n" +
            "shiny green bags contain 1 shiny indigo bag.\n" +
            "mirrored chartreuse bags contain no other bags.\n" +
            "pale crimson bags contain 2 light orange bags, 3 light lime bags, 4 dotted cyan bags.\n" +
            "shiny lavender bags contain 5 shiny crimson bags, 5 striped tan bags.\n" +
            "wavy blue bags contain 1 pale brown bag, 2 light cyan bags, 1 light magenta bag, 4 pale tan bags.\n" +
            "muted plum bags contain 4 dull red bags, 1 dotted maroon bag, 1 vibrant red bag, 4 bright chartreuse bags.\n" +
            "vibrant violet bags contain 4 dull brown bags.\n" +
            "faded chartreuse bags contain 3 dotted yellow bags.\n" +
            "drab chartreuse bags contain 4 shiny gold bags.\n" +
            "posh crimson bags contain 3 clear cyan bags, 2 light crimson bags, 1 drab crimson bag, 5 clear blue bags.\n" +
            "drab tomato bags contain 4 striped salmon bags.\n" +
            "clear red bags contain 1 pale blue bag, 1 posh orange bag, 2 dark aqua bags, 5 posh red bags.\n" +
            "drab lavender bags contain 2 muted gray bags.\n" +
            "pale blue bags contain 5 pale beige bags, 3 muted green bags, 3 shiny white bags.\n" +
            "posh cyan bags contain 2 clear aqua bags, 2 drab chartreuse bags, 2 dark purple bags, 2 posh salmon bags.\n" +
            "faded silver bags contain 5 dim salmon bags, 2 striped bronze bags.\n" +
            "dim green bags contain 1 dark fuchsia bag.\n" +
            "plaid aqua bags contain 3 dim red bags, 3 drab turquoise bags, 3 dim aqua bags, 5 dull salmon bags.\n" +
            "posh fuchsia bags contain 2 dotted fuchsia bags.\n" +
            "striped lime bags contain 1 dull silver bag, 5 posh black bags, 1 dark fuchsia bag, 3 dull lime bags.\n" +
            "clear olive bags contain 1 pale crimson bag, 2 shiny orange bags, 2 posh magenta bags, 1 dark fuchsia bag.\n" +
            "bright lime bags contain 2 striped tan bags, 5 dull plum bags, 4 bright yellow bags.\n" +
            "faded green bags contain 2 pale turquoise bags, 5 light lime bags.\n" +
            "plaid indigo bags contain 1 drab crimson bag.\n" +
            "shiny turquoise bags contain 2 faded crimson bags.\n" +
            "drab aqua bags contain 4 mirrored red bags, 1 drab gold bag, 5 wavy orange bags.\n" +
            "dim gray bags contain 1 shiny lime bag, 5 dotted fuchsia bags.\n" +
            "light teal bags contain 5 pale beige bags, 4 shiny cyan bags, 2 striped red bags, 1 light orange bag.\n" +
            "wavy red bags contain 3 dotted tan bags, 3 pale aqua bags.\n" +
            "vibrant red bags contain no other bags.\n" +
            "striped salmon bags contain no other bags.\n" +
            "clear silver bags contain 1 shiny orange bag, 1 pale aqua bag, 1 faded purple bag, 2 dim coral bags.\n" +
            "muted tan bags contain 3 pale maroon bags, 4 dotted maroon bags, 2 bright plum bags, 4 pale teal bags.\n" +
            "faded yellow bags contain 2 striped black bags, 2 dotted chartreuse bags, 5 drab chartreuse bags, 5 shiny tomato bags.\n" +
            "dark green bags contain 1 light lime bag.\n" +
            "striped cyan bags contain 2 posh magenta bags, 2 dim cyan bags.\n" +
            "vibrant teal bags contain 2 dim magenta bags, 1 bright chartreuse bag, 5 bright tan bags, 1 bright yellow bag.\n" +
            "bright red bags contain 1 posh white bag.\n" +
            "bright magenta bags contain 3 posh salmon bags, 2 dull fuchsia bags, 1 posh lavender bag.\n" +
            "bright white bags contain 1 bright chartreuse bag, 1 mirrored yellow bag.\n" +
            "dotted teal bags contain 3 bright gray bags, 3 vibrant fuchsia bags.\n" +
            "clear blue bags contain 4 pale brown bags, 2 drab indigo bags, 2 pale salmon bags, 3 muted olive bags.\n" +
            "dull brown bags contain 1 dim cyan bag, 2 vibrant plum bags, 3 posh chartreuse bags.\n" +
            "vibrant tan bags contain 1 dark orange bag.\n" +
            "shiny white bags contain 3 mirrored violet bags, 1 drab indigo bag.\n" +
            "clear violet bags contain 2 vibrant crimson bags, 1 light gold bag, 2 striped silver bags.\n" +
            "bright coral bags contain 3 drab blue bags, 4 muted olive bags, 3 faded purple bags, 1 vibrant maroon bag.\n" +
            "dotted aqua bags contain 5 wavy black bags, 4 mirrored gold bags, 1 posh red bag, 2 plaid silver bags.\n" +
            "clear crimson bags contain 1 plaid gold bag.\n" +
            "faded fuchsia bags contain 5 shiny coral bags, 2 pale beige bags.\n" +
            "wavy crimson bags contain 4 dotted turquoise bags, 1 light gray bag, 5 wavy purple bags, 1 faded gray bag.\n" +
            "dark fuchsia bags contain 2 wavy brown bags, 3 vibrant orange bags.\n" +
            "striped plum bags contain 1 vibrant cyan bag, 3 posh lime bags.\n" +
            "shiny salmon bags contain 5 drab magenta bags, 4 mirrored turquoise bags.\n" +
            "dim brown bags contain 2 shiny gold bags, 5 dotted cyan bags, 3 plaid cyan bags.\n" +
            "posh violet bags contain 3 dim red bags, 2 posh white bags, 3 faded aqua bags, 4 shiny gold bags.\n" +
            "pale black bags contain 3 pale plum bags.\n" +
            "shiny red bags contain 4 posh salmon bags, 1 dotted chartreuse bag.\n" +
            "dull olive bags contain 2 light blue bags.\n" +
            "bright olive bags contain 2 striped olive bags, 3 muted plum bags, 2 striped magenta bags.\n" +
            "posh brown bags contain 1 vibrant gold bag, 2 wavy silver bags, 4 dotted salmon bags, 4 drab orange bags.\n" +
            "bright tan bags contain 3 light chartreuse bags.\n" +
            "dull beige bags contain 2 pale violet bags.\n" +
            "light yellow bags contain 2 clear indigo bags, 2 wavy brown bags, 2 bright black bags, 4 shiny lime bags.\n" +
            "dull aqua bags contain 2 dull black bags, 5 posh black bags, 2 wavy fuchsia bags.\n" +
            "dark gold bags contain 3 clear fuchsia bags, 1 dark cyan bag, 4 dark orange bags.\n" +
            "wavy white bags contain 2 dark brown bags, 5 drab yellow bags, 5 dotted turquoise bags, 4 muted tomato bags.\n" +
            "posh silver bags contain 2 dim chartreuse bags, 5 light fuchsia bags, 4 faded purple bags, 5 drab cyan bags.\n" +
            "dotted coral bags contain 5 plaid silver bags, 2 posh beige bags, 5 dim beige bags, 2 vibrant crimson bags.\n" +
            "drab silver bags contain 1 posh chartreuse bag.\n" +
            "dull teal bags contain 1 dim indigo bag.\n" +
            "wavy brown bags contain no other bags.\n" +
            "posh purple bags contain 1 wavy purple bag, 5 muted gold bags.\n" +
            "faded magenta bags contain 2 wavy aqua bags, 4 vibrant beige bags.\n" +
            "vibrant magenta bags contain 5 wavy black bags, 4 shiny maroon bags, 3 dim tomato bags, 5 vibrant gray bags.\n" +
            "mirrored brown bags contain 2 light aqua bags, 1 clear orange bag, 2 bright lavender bags.\n" +
            "light crimson bags contain 5 posh chartreuse bags.\n" +
            "dotted olive bags contain 5 dim tomato bags, 3 dim plum bags.\n" +
            "light indigo bags contain 1 bright gold bag, 4 drab chartreuse bags, 4 dim fuchsia bags, 3 vibrant black bags.\n" +
            "vibrant chartreuse bags contain 5 dim crimson bags, 1 clear plum bag, 4 striped black bags.\n" +
            "pale violet bags contain 3 dark salmon bags.\n" +
            "bright violet bags contain 3 striped aqua bags.\n" +
            "dull maroon bags contain 1 drab aqua bag, 3 mirrored lavender bags, 5 dotted brown bags.\n" +
            "dim lime bags contain 3 wavy salmon bags, 3 striped tan bags.\n" +
            "dotted tan bags contain 3 dull yellow bags, 3 shiny violet bags.\n" +
            "posh yellow bags contain 3 dull crimson bags, 4 dim olive bags, 4 dark black bags.\n" +
            "dull violet bags contain 4 bright yellow bags, 2 posh magenta bags, 5 shiny red bags.\n" +
            "dotted silver bags contain 2 wavy turquoise bags, 3 dull tomato bags, 3 bright tan bags, 1 dotted salmon bag.\n" +
            "pale gray bags contain 1 plaid turquoise bag, 4 bright salmon bags.\n" +
            "clear purple bags contain 5 shiny gold bags, 5 faded teal bags.\n" +
            "striped tan bags contain 1 mirrored cyan bag, 2 pale beige bags.\n" +
            "dotted indigo bags contain 2 pale purple bags, 2 pale black bags, 2 dark salmon bags.\n" +
            "clear turquoise bags contain 2 light lavender bags.\n" +
            "wavy plum bags contain 5 shiny aqua bags.\n" +
            "muted indigo bags contain 5 drab teal bags.\n" +
            "muted yellow bags contain 2 vibrant cyan bags, 1 dim white bag.\n" +
            "dark white bags contain 2 drab crimson bags, 5 dull gray bags.\n" +
            "faded blue bags contain 1 faded olive bag, 2 shiny magenta bags, 5 dark plum bags.\n" +
            "posh tan bags contain 2 posh plum bags, 5 muted black bags, 3 clear indigo bags, 4 striped gold bags.\n" +
            "striped silver bags contain 4 drab tomato bags.\n" +
            "muted lavender bags contain 3 dim silver bags, 2 wavy lavender bags, 1 faded tan bag.\n" +
            "wavy chartreuse bags contain 5 dull bronze bags.\n" +
            "dark blue bags contain 1 dotted silver bag, 3 light yellow bags, 3 mirrored bronze bags, 4 shiny lavender bags.\n" +
            "dim turquoise bags contain 3 pale green bags, 4 mirrored gray bags.\n" +
            "muted blue bags contain 1 striped lavender bag, 3 dark fuchsia bags.\n" +
            "faded brown bags contain 2 dotted violet bags, 5 faded indigo bags, 5 drab indigo bags.\n" +
            "shiny magenta bags contain 5 dotted turquoise bags, 3 plaid violet bags, 2 dim cyan bags.\n" +
            "clear chartreuse bags contain 3 pale maroon bags, 5 plaid blue bags, 1 clear tan bag.\n" +
            "faded turquoise bags contain 4 mirrored magenta bags.\n" +
            "posh turquoise bags contain 3 dotted yellow bags, 5 striped purple bags, 3 pale cyan bags.\n" +
            "vibrant tomato bags contain 4 dark cyan bags.\n" +
            "mirrored white bags contain 2 mirrored indigo bags, 3 dim cyan bags, 3 bright lavender bags.\n" +
            "dull purple bags contain 2 striped yellow bags, 3 dull plum bags, 3 shiny gold bags, 3 pale gold bags.\n" +
            "mirrored yellow bags contain 4 shiny crimson bags.\n" +
            "posh white bags contain 3 mirrored orange bags.\n" +
            "wavy fuchsia bags contain 3 dotted tan bags, 5 shiny coral bags.\n" +
            "mirrored green bags contain 1 dull white bag, 5 shiny crimson bags.\n" +
            "plaid turquoise bags contain 2 pale salmon bags, 4 dull beige bags.\n" +
            "clear cyan bags contain 1 dim silver bag, 4 drab chartreuse bags.\n" +
            "mirrored purple bags contain 1 dull coral bag, 3 vibrant plum bags.\n" +
            "dim lavender bags contain 1 striped black bag, 4 shiny red bags, 4 posh chartreuse bags, 2 dim teal bags.\n" +
            "plaid cyan bags contain 1 dim orange bag.\n" +
            "plaid magenta bags contain 5 striped red bags, 4 striped salmon bags.\n" +
            "shiny blue bags contain 2 shiny turquoise bags, 1 posh orange bag.\n" +
            "faded aqua bags contain 3 light yellow bags, 3 wavy purple bags, 5 dull lime bags, 5 dotted black bags.\n" +
            "mirrored blue bags contain 5 wavy silver bags.\n" +
            "posh indigo bags contain 5 dark tan bags, 2 vibrant blue bags, 5 dark bronze bags, 2 vibrant crimson bags.\n" +
            "clear plum bags contain 1 wavy teal bag, 1 faded yellow bag.\n" +
            "dark gray bags contain 4 bright lavender bags.\n" +
            "muted coral bags contain 4 shiny red bags, 2 pale teal bags, 4 dim olive bags, 3 muted silver bags.\n" +
            "dim gold bags contain 2 pale gold bags, 3 vibrant red bags.\n" +
            "plaid violet bags contain 3 faded green bags, 4 mirrored teal bags, 1 wavy purple bag, 1 faded yellow bag.\n" +
            "clear tomato bags contain 2 drab beige bags, 3 dim cyan bags.\n" +
            "bright beige bags contain 5 muted brown bags, 4 wavy red bags, 4 clear maroon bags.\n" +
            "wavy teal bags contain 4 muted beige bags, 1 posh salmon bag, 2 posh black bags, 2 dotted maroon bags.\n" +
            "vibrant salmon bags contain 4 vibrant purple bags, 4 dull chartreuse bags.\n" +
            "dull coral bags contain 2 light red bags, 3 dark plum bags, 3 bright gray bags, 1 dotted cyan bag.\n" +
            "bright turquoise bags contain 4 plaid teal bags, 3 muted orange bags.\n" +
            "wavy maroon bags contain 5 pale fuchsia bags, 5 dotted magenta bags.\n" +
            "plaid gold bags contain 2 shiny lavender bags, 1 vibrant gray bag, 5 mirrored teal bags.\n" +
            "wavy black bags contain 1 wavy magenta bag.\n" +
            "drab beige bags contain 1 mirrored chartreuse bag, 3 wavy brown bags, 4 pale beige bags, 2 pale turquoise bags.\n" +
            "posh blue bags contain 4 clear silver bags.\n" +
            "vibrant turquoise bags contain 1 posh white bag, 2 bright gold bags.\n" +
            "faded black bags contain 5 wavy black bags, 1 shiny crimson bag.\n" +
            "pale salmon bags contain 1 vibrant brown bag.\n" +
            "striped gray bags contain 1 mirrored violet bag, 4 dull violet bags, 4 muted turquoise bags.\n" +
            "light brown bags contain 5 muted crimson bags, 1 drab crimson bag, 4 muted aqua bags, 5 dotted blue bags.\n" +
            "striped turquoise bags contain 5 muted blue bags, 4 mirrored salmon bags.\n" +
            "shiny lime bags contain 1 vibrant purple bag, 4 pale turquoise bags, 4 drab tomato bags, 4 dotted maroon bags.\n" +
            "dotted white bags contain 5 pale violet bags, 1 posh plum bag, 1 shiny chartreuse bag, 2 faded bronze bags.\n" +
            "dotted crimson bags contain 2 shiny olive bags, 2 mirrored yellow bags.\n" +
            "mirrored teal bags contain 1 dull violet bag, 4 shiny red bags, 3 drab black bags, 1 posh magenta bag.\n" +
            "striped lavender bags contain 4 light crimson bags.\n" +
            "pale chartreuse bags contain 1 wavy red bag.\n" +
            "posh gray bags contain 3 posh black bags.\n" +
            "clear teal bags contain 4 dull violet bags, 4 dotted chartreuse bags, 3 drab tan bags.\n" +
            "light olive bags contain 3 muted olive bags, 4 plaid turquoise bags.\n" +
            "light tomato bags contain 5 dull chartreuse bags, 3 muted beige bags.\n" +
            "light orange bags contain 2 wavy lavender bags.\n" +
            "wavy coral bags contain 5 drab beige bags, 4 plaid white bags.\n" +
            "dotted violet bags contain 5 muted salmon bags, 5 shiny red bags, 3 shiny crimson bags.\n" +
            "muted fuchsia bags contain 5 dull coral bags.\n" +
            "pale turquoise bags contain 3 vibrant red bags.\n" +
            "faded beige bags contain 2 striped magenta bags.\n" +
            "dull tan bags contain 3 drab maroon bags, 3 muted beige bags.\n" +
            "posh lime bags contain 4 wavy turquoise bags, 2 light blue bags, 1 posh salmon bag.\n" +
            "drab magenta bags contain 5 dim magenta bags.\n" +
            "striped crimson bags contain 1 dull white bag, 4 muted plum bags, 1 posh chartreuse bag.\n" +
            "vibrant maroon bags contain 4 pale aqua bags.\n" +
            "dotted black bags contain 5 shiny bronze bags, 1 shiny lime bag, 5 dotted yellow bags, 1 wavy turquoise bag.\n" +
            "wavy aqua bags contain 3 dull tomato bags, 1 shiny olive bag, 4 vibrant brown bags, 2 dim chartreuse bags.\n" +
            "dim tan bags contain 2 bright plum bags, 4 striped gold bags, 4 dull coral bags.\n" +
            "dull fuchsia bags contain 5 wavy maroon bags.\n" +
            "striped red bags contain 5 shiny red bags, 4 clear gold bags, 4 posh magenta bags, 2 bright yellow bags.\n" +
            "dotted lavender bags contain 3 faded lavender bags.\n" +
            "muted turquoise bags contain 4 faded fuchsia bags.\n" +
            "bright gray bags contain 2 light silver bags, 3 dull violet bags.\n" +
            "dull indigo bags contain 2 dim violet bags, 3 plaid salmon bags, 5 plaid beige bags.\n" +
            "wavy purple bags contain 3 dim yellow bags, 1 posh salmon bag.\n" +
            "wavy lime bags contain 2 dark fuchsia bags, 3 dark salmon bags, 5 bright silver bags, 5 pale aqua bags.\n" +
            "light cyan bags contain 2 clear blue bags.\n" +
            "light beige bags contain 5 dull red bags, 3 dark violet bags, 5 shiny lime bags.\n" +
            "mirrored silver bags contain 1 plaid beige bag, 5 dull coral bags.\n" +
            "dark beige bags contain 1 clear tan bag, 4 wavy orange bags, 3 shiny cyan bags, 5 shiny magenta bags.\n" +
            "vibrant crimson bags contain 2 mirrored teal bags, 3 pale turquoise bags, 5 posh magenta bags.\n" +
            "faded red bags contain 3 plaid lime bags, 2 clear brown bags.\n" +
            "dim bronze bags contain 2 dark beige bags, 1 plaid cyan bag, 3 clear purple bags.\n" +
            "bright maroon bags contain 3 light gray bags, 2 faded turquoise bags, 1 posh cyan bag.\n" +
            "clear gray bags contain 4 faded green bags.\n" +
            "pale silver bags contain 3 dull magenta bags.\n" +
            "dotted red bags contain 2 clear purple bags, 1 faded crimson bag, 5 dull bronze bags, 5 muted plum bags.\n" +
            "striped maroon bags contain 5 mirrored cyan bags, 3 dark green bags, 4 clear indigo bags, 2 muted beige bags.\n" +
            "dark cyan bags contain 3 dotted maroon bags, 2 vibrant red bags.\n" +
            "clear bronze bags contain 1 drab lime bag, 5 light gold bags.\n" +
            "dull yellow bags contain 1 dull chartreuse bag, 1 muted olive bag.\n" +
            "plaid lavender bags contain 1 vibrant silver bag, 5 clear fuchsia bags.\n" +
            "posh black bags contain 5 dotted salmon bags, 4 drab tomato bags, 5 drab black bags.\n" +
            "faded lime bags contain 1 dark silver bag.\n" +
            "clear lavender bags contain 5 light violet bags.\n" +
            "dotted gray bags contain 4 clear lavender bags, 3 shiny magenta bags.\n" +
            "shiny gray bags contain 5 dull brown bags.\n" +
            "drab coral bags contain 2 faded beige bags.\n" +
            "dark teal bags contain 3 drab indigo bags, 2 light aqua bags, 1 faded tan bag, 2 wavy brown bags.\n" +
            "shiny violet bags contain 1 posh black bag, 5 dim beige bags.\n" +
            "clear salmon bags contain 5 clear orange bags.\n" +
            "shiny olive bags contain 2 shiny yellow bags, 1 shiny indigo bag, 4 bright yellow bags.\n" +
            "plaid salmon bags contain 4 striped yellow bags, 4 mirrored chartreuse bags, 3 light magenta bags, 2 mirrored magenta bags.\n" +
            "wavy orange bags contain 5 vibrant purple bags.\n" +
            "posh magenta bags contain 3 mirrored chartreuse bags, 4 vibrant red bags.\n" +
            "pale fuchsia bags contain 5 muted silver bags.\n" +
            "light black bags contain 5 wavy orange bags, 5 faded tomato bags.\n" +
            "light gold bags contain 1 dull red bag.\n" +
            "bright brown bags contain 5 mirrored beige bags, 5 faded indigo bags.\n" +
            "muted teal bags contain 5 posh magenta bags, 1 dim gray bag, 3 pale plum bags.\n" +
            "pale plum bags contain 2 muted beige bags, 2 posh magenta bags, 2 shiny gold bags.\n" +
            "drab gold bags contain 1 striped beige bag.\n" +
            "posh gold bags contain 3 shiny silver bags.\n" +
            "dull turquoise bags contain 4 vibrant crimson bags, 3 faded gray bags, 5 muted purple bags.\n" +
            "light chartreuse bags contain 2 bright lavender bags, 4 bright silver bags.\n" +
            "striped chartreuse bags contain 1 shiny coral bag, 2 clear blue bags, 2 dotted turquoise bags.\n" +
            "dark aqua bags contain 3 wavy bronze bags, 4 shiny brown bags, 2 bright violet bags, 1 dotted indigo bag.\n" +
            "muted bronze bags contain 3 pale salmon bags, 1 striped teal bag.\n" +
            "dark violet bags contain 2 wavy magenta bags.\n" +
            "dark maroon bags contain 2 muted red bags.\n" +
            "shiny silver bags contain 2 light gray bags, 1 pale aqua bag, 5 dim tomato bags.\n" +
            "bright lavender bags contain 5 striped red bags, 3 faded tan bags.\n" +
            "dim olive bags contain 3 posh cyan bags, 4 light aqua bags, 1 wavy tan bag, 3 dull silver bags.\n" +
            "posh beige bags contain 5 faded tan bags, 1 shiny lime bag, 4 wavy lavender bags.\n" +
            "muted maroon bags contain 3 mirrored purple bags, 2 mirrored gold bags, 1 clear tan bag.\n" +
            "vibrant yellow bags contain 4 light lavender bags.\n" +
            "light purple bags contain 1 faded olive bag, 1 wavy red bag.\n" +
            "bright yellow bags contain 3 dotted salmon bags, 4 posh magenta bags, 4 striped salmon bags.\n" +
            "dull gray bags contain 5 posh magenta bags, 1 shiny white bag, 2 dim bronze bags, 2 dim lavender bags.\n" +
            "vibrant plum bags contain 1 faded tan bag, 2 pale turquoise bags, 4 bright chartreuse bags, 4 dull violet bags.\n" +
            "posh chartreuse bags contain 1 bright chartreuse bag, 4 drab black bags, 2 posh magenta bags, 4 dark orange bags.\n" +
            "drab gray bags contain 4 plaid turquoise bags.\n" +
            "dark brown bags contain 4 dotted green bags, 3 dim lavender bags.\n" +
            "mirrored crimson bags contain 2 light teal bags, 2 plaid blue bags, 5 wavy red bags, 2 bright tomato bags.\n" +
            "dotted gold bags contain 4 bright olive bags.\n" +
            "dark salmon bags contain 3 pale turquoise bags, 5 faded tan bags, 1 mirrored chartreuse bag.\n" +
            "wavy gray bags contain 4 shiny green bags, 5 shiny red bags.\n" +
            "dotted blue bags contain 2 wavy yellow bags, 1 dull beige bag.\n" +
            "dim maroon bags contain 2 wavy maroon bags, 5 dim violet bags, 4 dark tan bags.\n" +
            "plaid purple bags contain 2 dark teal bags, 2 mirrored black bags, 1 wavy lavender bag.\n" +
            "dull blue bags contain 1 bright gold bag, 2 dim olive bags, 4 muted chartreuse bags, 2 striped salmon bags.\n" +
            "muted white bags contain 4 light bronze bags, 3 wavy beige bags.\n" +
            "muted gold bags contain no other bags.\n" +
            "drab green bags contain 2 plaid chartreuse bags.\n" +
            "clear coral bags contain 5 mirrored cyan bags.\n" +
            "drab turquoise bags contain 3 posh black bags.\n" +
            "dotted fuchsia bags contain 2 light red bags, 4 clear aqua bags, 1 posh magenta bag, 4 vibrant cyan bags.\n" +
            "clear lime bags contain 3 dark chartreuse bags.\n" +
            "mirrored plum bags contain 1 wavy tan bag, 2 bright olive bags.\n" +
            "vibrant coral bags contain 3 light gray bags, 5 light white bags.\n" +
            "pale bronze bags contain 2 bright silver bags, 5 vibrant tan bags, 4 posh lime bags, 3 wavy silver bags.\n" +
            "plaid blue bags contain 4 dotted beige bags, 2 wavy olive bags, 2 striped tomato bags.\n" +
            "shiny teal bags contain 2 shiny violet bags, 1 faded blue bag, 5 shiny white bags, 4 dim fuchsia bags.\n" +
            "clear black bags contain 4 faded purple bags, 1 dim tomato bag, 5 striped brown bags, 2 faded indigo bags.\n" +
            "clear orange bags contain 2 bright gold bags, 5 light crimson bags, 2 faded yellow bags.\n" +
            "drab maroon bags contain 1 vibrant red bag, 2 dotted bronze bags.\n" +
            "drab blue bags contain 1 mirrored aqua bag, 1 dark gold bag, 3 shiny crimson bags.\n" +
            "striped black bags contain 2 drab black bags, 5 dark orange bags.\n" +
            "light lavender bags contain 1 clear blue bag, 1 striped red bag.\n" +
            "posh bronze bags contain 5 light red bags, 4 dull plum bags, 1 dim coral bag, 3 clear blue bags.\n" +
            "plaid beige bags contain 5 dotted turquoise bags, 2 light cyan bags.\n" +
            "plaid fuchsia bags contain 3 wavy fuchsia bags.\n" +
            "shiny bronze bags contain 5 vibrant silver bags, 3 striped silver bags, 3 pale turquoise bags, 2 faded olive bags.\n" +
            "vibrant purple bags contain 3 wavy magenta bags.\n" +
            "pale magenta bags contain 4 dim yellow bags, 1 dim aqua bag.\n" +
            "faded purple bags contain 5 mirrored black bags, 1 muted beige bag, 1 muted aqua bag, 1 light maroon bag.\n" +
            "light turquoise bags contain 5 muted brown bags, 1 posh beige bag, 2 vibrant fuchsia bags, 3 faded teal bags.\n" +
            "vibrant lavender bags contain 1 light chartreuse bag, 3 mirrored teal bags, 5 drab lavender bags, 4 shiny brown bags.\n" +
            "striped yellow bags contain 1 dotted cyan bag, 3 mirrored turquoise bags.\n" +
            "drab bronze bags contain 3 shiny lavender bags, 5 muted yellow bags, 3 plaid teal bags, 2 plaid gold bags.\n" +
            "posh maroon bags contain 5 bright fuchsia bags, 3 dotted indigo bags, 5 dark teal bags, 1 faded violet bag.\n" +
            "vibrant cyan bags contain 2 dark orange bags.\n" +
            "striped aqua bags contain 2 light bronze bags, 1 dull beige bag, 4 pale violet bags, 5 pale beige bags.\n" +
            "faded gold bags contain 4 pale crimson bags.\n" +
            "muted olive bags contain 2 mirrored teal bags, 1 dim aqua bag, 3 posh chartreuse bags, 3 dull brown bags.\n" +
            "dim coral bags contain 1 mirrored gold bag.\n" +
            "dull silver bags contain 5 faded gray bags, 5 light red bags, 3 light crimson bags, 4 bright chartreuse bags.\n" +
            "vibrant gray bags contain 2 plaid silver bags, 5 plaid violet bags, 1 dim silver bag, 4 mirrored chartreuse bags.\n" +
            "plaid crimson bags contain 2 vibrant bronze bags, 1 drab olive bag, 1 pale purple bag.\n" +
            "dotted maroon bags contain no other bags.\n" +
            "vibrant brown bags contain 2 dull violet bags, 4 muted beige bags, 4 wavy teal bags.\n" +
            "bright tomato bags contain 3 muted beige bags.\n" +
            "shiny cyan bags contain 5 clear gold bags, 2 shiny lime bags, 4 wavy magenta bags, 2 wavy lavender bags.\n" +
            "drab tan bags contain 1 plaid red bag.\n" +
            "dull plum bags contain 4 dotted magenta bags, 1 plaid silver bag, 1 pale teal bag, 1 dim yellow bag.\n" +
            "clear gold bags contain 1 bright gold bag, 4 dark orange bags, 4 posh magenta bags.\n" +
            "shiny gold bags contain 5 dark salmon bags, 2 wavy purple bags, 5 dark cyan bags, 5 dull chartreuse bags.\n" +
            "mirrored tomato bags contain 3 light aqua bags, 4 pale plum bags, 1 pale beige bag.\n" +
            "dim tomato bags contain 5 light crimson bags, 2 bright lavender bags.\n" +
            "mirrored tan bags contain 1 vibrant plum bag, 2 faded violet bags, 5 striped lime bags, 3 muted white bags.\n" +
            "faded teal bags contain 1 drab silver bag.\n" +
            "dim red bags contain 2 clear turquoise bags, 3 drab maroon bags.\n" +
            "dark magenta bags contain 2 clear cyan bags.\n" +
            "vibrant gold bags contain 4 dotted salmon bags, 3 bright chartreuse bags, 3 striped purple bags.\n" +
            "muted brown bags contain 4 posh beige bags, 3 muted aqua bags, 5 dim beige bags, 4 dim magenta bags.\n" +
            "plaid orange bags contain 2 dark purple bags, 4 mirrored tomato bags, 1 vibrant orange bag.\n" +
            "pale gold bags contain 1 vibrant plum bag, 4 dotted violet bags.\n" +
            "plaid gray bags contain 3 bright silver bags, 5 drab fuchsia bags.\n" +
            "muted orange bags contain 5 dull magenta bags, 5 clear lavender bags, 4 mirrored chartreuse bags, 4 vibrant silver bags.\n" +
            "muted aqua bags contain 2 dim silver bags, 2 wavy black bags.\n" +
            "dim crimson bags contain 2 mirrored bronze bags, 4 muted cyan bags.\n" +
            "dark tomato bags contain 5 pale blue bags.\n" +
            "bright salmon bags contain 1 vibrant tan bag, 4 bright white bags, 1 dim gray bag.\n" +
            "mirrored orange bags contain 1 dark salmon bag, 5 drab beige bags, 5 dull chartreuse bags, 1 shiny cyan bag.\n" +
            "clear aqua bags contain 2 dim teal bags, 4 wavy olive bags.\n" +
            "bright bronze bags contain 3 shiny maroon bags.\n" +
            "vibrant black bags contain 3 muted gold bags, 2 striped red bags, 2 pale magenta bags, 4 clear violet bags.\n" +
            "clear yellow bags contain 2 plaid maroon bags.\n" +
            "pale lavender bags contain 1 shiny maroon bag, 5 bright cyan bags, 5 shiny orange bags, 4 striped tomato bags.\n" +
            "dark chartreuse bags contain 2 wavy brown bags.\n" +
            "mirrored violet bags contain 2 light lavender bags, 4 dull coral bags, 2 light red bags.\n" +
            "vibrant beige bags contain 2 shiny red bags, 2 clear tan bags.\n" +
            "mirrored coral bags contain 4 faded coral bags, 2 dull bronze bags.\n" +
            "posh red bags contain 4 wavy brown bags, 1 dark salmon bag, 5 dull chartreuse bags, 5 vibrant crimson bags.\n" +
            "light green bags contain 4 posh fuchsia bags.\n" +
            "wavy green bags contain 3 dark green bags, 4 posh chartreuse bags, 1 mirrored black bag.\n" +
            "drab violet bags contain 5 wavy yellow bags.\n" +
            "dark crimson bags contain 1 bright gray bag, 2 shiny violet bags, 3 pale chartreuse bags.\n" +
            "posh green bags contain 2 bright yellow bags, 2 faded yellow bags.\n" +
            "shiny plum bags contain 3 shiny indigo bags, 1 dark gold bag, 2 dim coral bags.\n" +
            "striped beige bags contain 2 muted beige bags.\n" +
            "dark orange bags contain 5 bright gold bags, 2 dotted maroon bags, 1 striped salmon bag, 3 vibrant red bags.\n" +
            "bright orange bags contain 4 muted blue bags, 5 striped blue bags, 4 dark cyan bags.\n" +
            "dark black bags contain 1 shiny lime bag, 1 clear olive bag, 4 dull bronze bags, 3 muted lavender bags.\n" +
            "pale orange bags contain 3 plaid violet bags, 1 shiny red bag, 1 dull coral bag, 1 shiny bronze bag.\n" +
            "mirrored salmon bags contain 4 vibrant red bags, 3 muted beige bags, 2 dark orange bags.\n" +
            "drab yellow bags contain 1 bright white bag.\n" +
            "dull tomato bags contain 4 muted beige bags, 3 posh salmon bags, 2 shiny maroon bags.\n" +
            "striped fuchsia bags contain 5 pale beige bags, 3 mirrored gray bags.\n" +
            "mirrored beige bags contain 5 mirrored black bags, 2 dim lavender bags, 1 clear lime bag.\n" +
            "wavy tomato bags contain 4 wavy fuchsia bags.\n" +
            "bright indigo bags contain 2 dull violet bags, 5 dull green bags, 3 dotted cyan bags, 2 dotted tan bags.\n" +
            "striped blue bags contain 1 posh red bag, 4 bright fuchsia bags, 4 dark aqua bags.\n" +
            "bright green bags contain 3 dim violet bags, 2 shiny coral bags, 1 dim white bag, 4 dim yellow bags.\n" +
            "dotted salmon bags contain 5 dull red bags, 2 striped salmon bags, 5 dotted maroon bags, 3 shiny red bags.\n" +
            "light magenta bags contain 1 dark fuchsia bag, 1 pale turquoise bag, 5 pale plum bags, 3 vibrant tan bags.\n" +
            "mirrored olive bags contain 1 shiny lavender bag.\n" +
            "dark coral bags contain 3 wavy teal bags, 4 muted plum bags.\n" +
            "posh teal bags contain 1 pale violet bag, 5 muted tan bags, 1 drab gray bag, 2 drab beige bags.\n" +
            "bright teal bags contain 5 mirrored coral bags.\n" +
            "striped orange bags contain 3 clear olive bags.\n" +
            "faded plum bags contain 5 striped black bags, 1 striped gold bag.\n" +
            "plaid tomato bags contain 1 muted salmon bag, 2 pale violet bags, 1 light violet bag, 3 dim tomato bags.\n" +
            "dim blue bags contain 4 drab salmon bags, 4 faded plum bags, 1 light salmon bag, 3 dark turquoise bags.\n" +
            "striped coral bags contain 3 wavy fuchsia bags.\n" +
            "light violet bags contain 4 dim beige bags, 4 striped lavender bags, 1 dotted orange bag, 1 wavy purple bag.\n" +
            "dull white bags contain 2 faded purple bags, 1 bright silver bag, 4 pale bronze bags, 3 clear fuchsia bags.\n" +
            "dim white bags contain 2 light yellow bags, 5 posh gold bags, 1 bright blue bag, 1 striped chartreuse bag.\n" +
            "bright chartreuse bags contain 1 muted gold bag, 1 dotted maroon bag.\n" +
            "striped purple bags contain 2 light orange bags, 1 dotted maroon bag, 3 dim magenta bags, 4 plaid violet bags.\n" +
            "dotted tomato bags contain 5 posh chartreuse bags.\n" +
            "wavy lavender bags contain 4 dim yellow bags, 4 pale turquoise bags, 4 vibrant red bags, 3 bright yellow bags.\n" +
            "striped teal bags contain 2 bright yellow bags.\n" +
            "drab crimson bags contain 1 wavy tan bag, 1 dull green bag, 3 muted blue bags.\n" +
            "dull lime bags contain 3 shiny tomato bags.\n" +
            "posh salmon bags contain no other bags.\n" +
            "vibrant aqua bags contain 2 shiny indigo bags, 3 shiny crimson bags, 4 shiny cyan bags.\n" +
            "drab black bags contain 3 dull violet bags, 4 dotted maroon bags, 3 vibrant purple bags, 3 muted plum bags.\n" +
            "drab olive bags contain 5 drab orange bags, 4 dotted green bags, 5 drab chartreuse bags.\n" +
            "dark plum bags contain 2 dotted salmon bags, 5 wavy magenta bags, 1 bright chartreuse bag.\n" +
            "plaid brown bags contain 2 light lavender bags.\n" +
            "striped magenta bags contain 5 pale lime bags, 4 posh salmon bags, 5 clear tomato bags, 5 dull plum bags.\n" +
            "dull orange bags contain 4 light maroon bags.\n" +
            "vibrant orange bags contain 5 dull red bags, 1 posh chartreuse bag.\n" +
            "mirrored turquoise bags contain 4 drab cyan bags, 1 posh cyan bag, 4 clear gold bags, 5 plaid magenta bags.\n" +
            "mirrored black bags contain no other bags.\n" +
            "shiny black bags contain 5 dotted green bags, 1 dim violet bag, 1 clear cyan bag, 2 muted fuchsia bags.\n" +
            "vibrant indigo bags contain 1 light maroon bag.\n" +
            "bright fuchsia bags contain 5 faded olive bags.\n" +
            "dotted plum bags contain 5 shiny violet bags.\n" +
            "plaid lime bags contain 2 bright gray bags, 4 pale beige bags, 4 light silver bags.\n" +
            "plaid red bags contain 1 shiny maroon bag, 4 striped lavender bags, 5 mirrored purple bags.\n" +
            "mirrored lime bags contain 5 faded coral bags, 3 dark gold bags, 5 mirrored indigo bags, 5 dull coral bags.\n" +
            "drab lime bags contain 2 dotted aqua bags, 5 shiny chartreuse bags.\n" +
            "faded tan bags contain 1 muted plum bag, 1 posh salmon bag.\n" +
            "dark silver bags contain 4 dotted maroon bags, 4 shiny white bags.\n" +
            "striped indigo bags contain 5 dull plum bags, 2 light lavender bags, 3 light red bags, 1 muted olive bag.\n" +
            "bright gold bags contain no other bags.\n" +
            "plaid black bags contain 2 dull violet bags, 5 muted olive bags, 1 muted teal bag.\n" +
            "dotted chartreuse bags contain no other bags.\n" +
            "vibrant blue bags contain 1 shiny cyan bag.\n" +
            "dim indigo bags contain 1 bright purple bag, 1 plaid magenta bag, 5 posh fuchsia bags, 5 plaid red bags.\n" +
            "light red bags contain 5 bright gold bags, 4 plaid silver bags, 1 dull violet bag, 4 pale turquoise bags.\n" +
            "dim black bags contain 3 dim orange bags, 5 dull lime bags, 2 faded indigo bags, 3 dotted olive bags.\n" +
            "mirrored gold bags contain 2 muted gold bags, 5 dark cyan bags.\n" +
            "posh aqua bags contain 1 clear violet bag.\n" +
            "light blue bags contain 3 drab chartreuse bags, 2 dull chartreuse bags.\n" +
            "shiny maroon bags contain 1 wavy violet bag.\n" +
            "faded lavender bags contain 1 dark salmon bag, 5 vibrant orange bags, 3 vibrant purple bags, 5 posh black bags.\n" +
            "faded olive bags contain 2 mirrored teal bags, 5 pale plum bags, 4 pale brown bags.\n" +
            "drab indigo bags contain 1 dim cyan bag, 1 plaid silver bag.\n" +
            "clear white bags contain 4 posh beige bags.\n" +
            "wavy violet bags contain 5 dotted fuchsia bags.\n" +
            "dark turquoise bags contain 5 vibrant turquoise bags, 3 vibrant lavender bags.\n" +
            "striped violet bags contain 5 mirrored turquoise bags, 1 muted orange bag, 3 dim teal bags, 4 posh green bags.\n" +
            "wavy yellow bags contain 2 dim yellow bags, 5 dim lavender bags.\n" +
            "dark red bags contain 4 striped gold bags, 3 vibrant tomato bags.\n" +
            "plaid maroon bags contain 5 light white bags, 5 plaid black bags.\n" +
            "bright black bags contain 3 light maroon bags, 5 shiny lime bags, 2 vibrant maroon bags, 3 clear violet bags.\n" +
            "drab salmon bags contain 2 clear coral bags, 4 dark indigo bags.\n" +
            "plaid green bags contain 2 pale indigo bags.\n" +
            "pale teal bags contain 4 drab black bags, 3 faded gray bags, 5 posh beige bags, 4 faded yellow bags.\n" +
            "light silver bags contain 5 bright yellow bags, 1 dim silver bag, 5 posh chartreuse bags.\n" +
            "dull magenta bags contain 3 posh turquoise bags, 3 light magenta bags, 4 shiny orange bags, 4 clear turquoise bags.\n" +
            "pale white bags contain 5 dark cyan bags.\n" +
            "dull chartreuse bags contain 3 mirrored black bags, 3 dotted salmon bags, 5 pale turquoise bags.\n" +
            "dim salmon bags contain 2 mirrored yellow bags, 5 striped salmon bags, 1 drab tomato bag, 5 dull yellow bags.\n" +
            "shiny crimson bags contain 5 faded tan bags, 2 muted salmon bags, 1 wavy teal bag, 3 vibrant orange bags.\n" +
            "dark purple bags contain 2 muted plum bags, 5 bright lavender bags, 1 dark cyan bag, 4 clear orange bags.\n" +
            "clear brown bags contain 2 dim orange bags, 1 vibrant maroon bag, 2 striped lime bags.\n" +
            "muted silver bags contain 5 vibrant gray bags.\n" +
            "vibrant bronze bags contain 3 posh red bags.\n" +
            "mirrored maroon bags contain 3 clear magenta bags, 2 posh brown bags, 5 wavy teal bags.\n" +
            "muted tomato bags contain 5 faded aqua bags, 4 wavy lavender bags, 1 mirrored silver bag.\n" +
            "posh olive bags contain 4 faded salmon bags, 1 muted green bag, 5 light tomato bags, 3 dark gold bags.\n" +
            "dotted turquoise bags contain 5 light orange bags, 1 dull brown bag.\n" +
            "light bronze bags contain 2 pale cyan bags, 3 shiny lime bags, 2 faded olive bags.\n" +
            "striped gold bags contain 4 muted salmon bags, 1 bright yellow bag, 1 dark plum bag, 4 light maroon bags.\n" +
            "faded maroon bags contain 5 wavy silver bags, 3 plaid magenta bags.\n" +
            "clear magenta bags contain 2 wavy turquoise bags.\n" +
            "vibrant olive bags contain 1 muted silver bag.\n" +
            "clear tan bags contain 4 mirrored gold bags.\n" +
            "posh orange bags contain 3 plaid blue bags, 4 dotted aqua bags.\n" +
            "wavy cyan bags contain 3 posh lavender bags.\n" +
            "shiny chartreuse bags contain 5 mirrored cyan bags, 3 posh chartreuse bags, 4 dotted aqua bags.\n" +
            "light lime bags contain 5 muted plum bags, 2 wavy purple bags.\n" +
            "wavy silver bags contain 4 faded lavender bags.\n" +
            "drab red bags contain 4 clear tomato bags, 4 pale turquoise bags, 2 dull yellow bags.\n" +
            "light aqua bags contain 3 dotted aqua bags.\n" +
            "dim purple bags contain 2 striped gold bags, 2 drab tan bags, 2 mirrored tan bags.\n" +
            "shiny indigo bags contain 1 pale magenta bag, 5 dim plum bags, 3 muted blue bags, 4 dim coral bags.\n" +
            "pale lime bags contain 2 mirrored cyan bags, 4 dull violet bags, 1 striped lime bag.\n" +
            "posh lavender bags contain 5 clear purple bags, 2 muted gold bags, 5 dull brown bags, 4 muted lime bags.\n" +
            "dull lavender bags contain 1 striped violet bag, 3 muted bronze bags.\n" +
            "dotted lime bags contain 3 plaid magenta bags, 5 plaid violet bags, 1 shiny lime bag, 3 plaid purple bags.\n" +
            "posh coral bags contain 5 mirrored violet bags, 1 clear violet bag, 1 dark fuchsia bag, 3 dotted silver bags.\n" +
            "bright plum bags contain 5 wavy brown bags.\n" +
            "pale coral bags contain 3 bright olive bags.\n" +
            "dim fuchsia bags contain 2 bright chartreuse bags, 4 mirrored teal bags, 4 posh salmon bags, 3 light chartreuse bags.\n" +
            "dim cyan bags contain 2 faded tan bags, 4 mirrored gold bags.\n" +
            "faded cyan bags contain 1 dotted black bag, 1 shiny maroon bag, 2 muted chartreuse bags.";
}
