/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poker;

/**
 *
 * @author Oscar Montes
 */
public class Main {

    public static void main(String[] args) {
        Card player1[] = new Card[5];

        // MAKE PLAYER 1's HAND
        player1[0] = new Card(0);
        player1[1] = new Card(9);
        player1[2] = new Card(0);
        player1[3] = new Card(0);
        player1[4] = new Card(0);
        
        

        // PRINT P1's HAND
        System.out.println("Player 1's hand:");
        for (int i = 0; i < 5; i++) {
            System.out.println(player1[i].toWords());
        }

        System.out.println();

        HandEvaluator he = new HandEvaluator(player1);
        System.out.println("Tiene: " +
                he.getPokerHandAsString());
        

    }

}
