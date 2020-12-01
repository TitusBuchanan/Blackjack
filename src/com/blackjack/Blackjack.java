package com.blackjack;

import java.util.Scanner;

public class Blackjack {
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		//Welcome
		System.out.println("Welcome to blackjack");
		
		//Playing Deck Creation
		Deck playingDeck = new Deck();
		playingDeck.createFullDeck();
		playingDeck.shuffle();
		
		
		//Deck for player
		Deck playerDeck = new Deck();
		
		//Dealer Deck
		Deck dealerDeck = new Deck();
		
		double playerMoney = 200.00;
		
		Scanner userInput = new Scanner(System.in);
		
		//BlackJack Loop
		while(playerMoney > 0) {
			//Continue Play
			System.out.println("You have $" + playerMoney + ", how much would you like to wager?" );
			double playerBet = userInput.nextDouble();
			if(playerBet > playerMoney) {
				System.out.println("You cannot bet more than you have.");
				break;
			}
			
			boolean endRound = false;
			
			//Player getting two Cards
			playerDeck.draw(playingDeck);
			playerDeck.draw(playingDeck);
			
			//Dealer Getting Two Cards
			dealerDeck.draw(playingDeck);
			dealerDeck.draw(playingDeck);
			
			while(true) {
				System.out.println("Your Hand: ");
				System.out.print(playerDeck.toString());
				System.out.println(" Your Deck is valued at : " + playerDeck.cardsValue());
				
				
				//Dealer Hand
				System.out.println("Dealer Hand: " + dealerDeck.getCard(0).toString() + "and [Hidden]");
				
				//Player Choice
				System.out.println("Would you like to (1)Hit or (2) Stand?");
				int response = userInput.nextInt();
				
				//Player Hit
				if(response == 1) {
					playerDeck.draw(playingDeck);
					System.out.println("You draw a" + playerDeck.getCard(playerDeck.deckSize() - 1).toString());
					
					
				//Player Over 21
				if(playerDeck.cardsValue() > 21) {
					System.out.println("Bust. Currently Valued at: " + playerDeck.cardsValue());
					playerMoney -= playerBet;
					endRound = true;
					break;
					
					}
				}
				if(response == 2) {
					break;
				}
				
			}
			
			//Dealer Cards
			System.out.println("Dealer Cards" + dealerDeck.toString());
			
			//See if dealer has more points than player
			if((dealerDeck.cardsValue() > playerDeck.cardsValue()) && endRound == false) {
				System.out.println("Dealer beats you");
				playerMoney -= playerBet;
				endRound = true;
			}
			
			
			while((dealerDeck.cardsValue() < 17) && endRound == false) {
			dealerDeck.draw(playingDeck);
			System.out.println("Dealer Draws " + dealerDeck.getCard(dealerDeck.deckSize() -1).toString());
			}
			
			//Display Total Value
			System.out.println("Dealer's Hand is valued at:" + dealerDeck.cardsValue());
			
			//Determine if Dealer busted
			if((dealerDeck.cardsValue() > 21) && endRound == false) {
				System.out.println("Dealer Busts! You win. ");
				playerMoney += playerBet;
				endRound = true;
			}
			
			//Determine if push(Tie)
			if((playerDeck.cardsValue() == dealerDeck.cardsValue()) && endRound == false){
				System.out.println("Push");
				endRound = true;
			}
			
			if((playerDeck.cardsValue() > dealerDeck.cardsValue()) && endRound == false){
				System.out.println("You win the Hand!");
				playerMoney += playerBet;
				endRound = true;
			}
			else if(endRound == false ) {
				System.out.println("You Lose the hand!");
				playerMoney -= playerBet;
				endRound = true;
			}
			
			
			playerDeck.moveAllToDeck(playingDeck);
			dealerDeck.moveAllToDeck(playingDeck);
			System.out.println("End Of Hand");
		
			

		}
		System.out.println("Game Over! You're out of money!!!!");

	
	}

}
