package com.peelu;

import java.util.ArrayList;
import java.util.List;
/*
Probable XI: Sunil Narine, Chris Lynn, Robin Uthappa, Nitish Rana/Rinku Singh, Dinesh Karthik (C & WK), Andre Russell, Shubman Gill, Shivam Mavi, Piyush Chawla, Mitchell Johnson, Kuldeep Yadav.

Chennai Super Kings: In the last game against DD, CSK made four changes. It was only the third instance of Dhoni making four changes to the playing XI. He has done it in successive matches but it's highly unlikely the visitors will now make any changes.

Probable XI: Shane Watson, Faf du Plessis, Suresh Raina, Ambati Rayudu, MS Dhoni (C & WK), Dwayne Bravo, Ravindra Jadeja, Karn Sharma, Harbhajan Singh, Lungi Ngidi, KM Asif
 */
public class Main2 {
    public static void main(String[] args) {
        List<Player> players = new ArrayList<Player>();

        players.add(new Player("Kartik", 9, 182.5));
        players.add(new Player("Sunil Narine", 10, 197.5));
        players.add(new Player("Dhoni", 9, 229.5));
        int totalCost=20;
        double value = knapSack(totalCost, players, 2);
        System.out.println(value);
    }

    private static double knapSack(double totalCost, List<Player> players, int endIndex) {
        if(endIndex < 0) return 0;
        if(totalCost<0) return 0;
        Player player = players.get(endIndex);
        System.out.println("knapSack(" + totalCost + "," + player.getName());
//        System.out.println(player.getName());
//        players.remove(player);

        double valueWithPlayerSelected = player.getValue()+ knapSack(totalCost - player.getCost(), players, endIndex - 1);
        if(player.getCost() > totalCost) valueWithPlayerSelected = -1;
        System.out.println(player.getName() + " valueWithPlayerSelected : " + valueWithPlayerSelected);
//        players.add(player);
        double valueWithPlayerNotSelected = knapSack(totalCost, players, endIndex -1);
        System.out.println(player.getName() + " valueWithPlayerNotSelected : " + valueWithPlayerNotSelected);
        if(valueWithPlayerNotSelected > valueWithPlayerSelected){
            System.out.println(player.getName() + " Not Selected");
            return valueWithPlayerNotSelected;
        } else {
            System.out.println(player.getName() + " Selected");
            return valueWithPlayerSelected;
        }
    }

    static double max(double a, double b) { return (a > b)? a : b; }

}
