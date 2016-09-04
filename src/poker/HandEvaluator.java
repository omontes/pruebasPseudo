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


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HandEvaluator {

	private Card cards[];
	private String pattern;
	
	
	public HandEvaluator(Card cards[]) {
		this.cards = cards;
		
		evaluateHand();
	}
	
	public void evaluateHand() {
		byte tallyValues[] = new byte[13];
		
		
		boolean fives = false;
                boolean fours = false;
		boolean threes = false;
		boolean pair1 = false;
		boolean pair2 = false;
		
		for (int i=0; i<5; i++) {
			tallyValues[cards[i].getRank()]++;
			
		}
		
		
		for (byte i=12; i>=0; i--) {
			if (tallyValues[i] == 5) {
				fives = true;
			}
                        else if (tallyValues[i] == 4) {
				fours = true;
			} else if (tallyValues[i] == 3) {
				threes = true;
			} else if (tallyValues[i] == 2) {
				if (pair1 == false)
					pair1 = true;
				else
					pair2 = true;
			}
		}
		
		List<Integer> sortedCardValues = new ArrayList<Integer>();
		
		for (int i=0; i<5; i++) {
			sortedCardValues.add(cards[i].getRank());
		}
		Collections.sort(sortedCardValues);
		
		
		
		if (fives) {
			
			pattern = "Quintilla";
		}	
                else if (fours) {
			
			pattern = "Poker";
		} else if (threes) {
			if (pair1) {
				
				pattern = "Full";
			} else {
				
				pattern = "Tercia";
			}
		} else if (pair1) {
			if (pair2) {
				
				pattern = "Dos pares";
			} else {
				
				pattern = "Un Par";
			}
		}
		
		
		else {
			
			pattern = "todos diferentes";
		}
		
		
	}
	
	public String getPokerHandAsString() {
		return pattern;
	}
	
	
	
	
}
