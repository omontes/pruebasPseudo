/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poker;

/**
 *
 * @author Se baso el codigo con https://github.com/mashlol/Poker-Hand-Evaluator
 */
public class Card {
    private int rank;
    

    
    public Card(int i) {
        this.rank = i;
    }
    
    public int getRank() {
    	return rank;
    }
    
    
    
    public String valueToString () {
		String value = "Error";
		
		int temp = this.rank;
		
		if (temp == 0) {
			value = "Ace";
		} else if (temp == 1) {
			value = "Two";
		} else if (temp == 2) {
			value = "Three";
		} else if (temp == 3) {
			value = "Four";
		} else if (temp == 4) {
			value = "Five";
		} else if (temp == 5) {
			value = "Six";
		} else if (temp == 6) {
			value = "Seven";
		} else if (temp == 7) {
			value = "Eight";
		} else if (temp == 8) {
			value = "Nine";
		} else if (temp == 9) {
			value = "Ten";
		} else if (temp == 10) {
			value = "Jack";
		} else if (temp == 11) {
			value = "Queen";
		} else if (temp == 12) {
			value = "King";
		}
		
		return value;
	}
	
		
        public String toWords () {
		String words = "";
		
		words = valueToString();
		
		return words;
	}
	
	
	
}
