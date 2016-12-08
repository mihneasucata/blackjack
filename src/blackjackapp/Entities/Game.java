/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjackapp.Entities;

import java.io.IOException;
import java.util.*;

/**
 *
 * @author Mihnea
 */
public class Game {

    public static final int MAX_VALUE = 21;
    public static final int MIN_COMPUTER_VALUE = 17;

    private final PlayerHand humanPlayer = new PlayerHand();
    private final PlayerHand computerPlayer = new PlayerHand();
    private final List<Card> shuffledCards;
    private int cardIndex;

    private int playerDraw;
    private int computerDraw;

    public Game() {
        this.cardIndex = 0;
        this.playerDraw = 0;
        this.computerDraw = 0;
        
        CardDeck deck = new CardDeck();
        shuffledCards = deck.getNewShuffledDeckCards();
        initializePlayerHands();
    }

    private void initializePlayerHands() {
        humanPlayer.getCards().add(shuffledCards.get(cardIndex++));
        computerPlayer.getCards().add(shuffledCards.get(cardIndex++));
        humanPlayer.getCards().add(shuffledCards.get(cardIndex++));
        computerPlayer.getCards().add(shuffledCards.get(cardIndex++));
    }

    /**
     * Main loop of the game
     */
    public void runGame() {
        printFirstRound();

        if (processBlackjack()) {
            return;
        }

        processHumanPlayerPlay();

        System.out.println();
        processComputerPlay();

        processFinalValues();
    }

    private void printFirstRound() {
        if (humanPlayer.getCards().size() < 2 || computerPlayer.getCards().size() < 2) {
            System.out.println("Error, first round not initialized!");
            return;
        }

        System.out.println("Initial Draw");

        System.out.format("Dealer Draw: %s, Hidden", computerPlayer.getCards().get(0).getDisplayString());
        System.out.println();

        System.out.format("Player Draw(%d): %s, %s. ", ++playerDraw,
                humanPlayer.getCards().get(0).getDisplayString(),
                humanPlayer.getCards().get(1).getDisplayString());

        if (!humanPlayer.isBlackjack() && !computerPlayer.isBlackjack()) {
            System.out.print("Do you want to draw another card? (Y/N)");
        }

        System.out.println();
    }

    private boolean processBlackjack() {

        if (!humanPlayer.isBlackjack() && computerPlayer.isBlackjack()) {
            System.out.format("Dealer Hidden Card Was: %s. Dealer has BLACKJACK.\n", computerPlayer.getCards().get(1).getDisplayString());
            System.out.println("Dealer WINS!!!");
            return true;
        }

        if (humanPlayer.isBlackjack() && !computerPlayer.isBlackjack()) {
            System.out.format("Dealer Hidden Card Was: %s.\n", computerPlayer.getCards().get(1).getDisplayString());
            System.out.println("Player has BLACKJACK. Player WINS!!!");
            return true;
        }

        if (humanPlayer.isBlackjack() && computerPlayer.isBlackjack()) {
            System.out.format("Dealer Hidden Card Was: %s.\n", computerPlayer.getCards().get(1).getDisplayString());
            System.out.println("Both player and dealer have BLACKJACK. Game ends in DRAW!");
            return true;
        }

        return false;
    }

    private void processHumanPlayerPlay() {
        while (waitForKeypress()) {
            Card nextCard = shuffledCards.get(cardIndex++);

            humanPlayer.getCards().add(nextCard);
            System.out.format("Player Draw(%d): %s. ", ++playerDraw, nextCard.getDisplayString());

            if (humanPlayer.isBust()) {
                return;
            }
            if (humanPlayer.getBestNonBust() == MAX_VALUE) {
                return;
            }

            System.out.println("Do you want to draw another card? (Y/N)");
        }
    }

    private void processComputerPlay() {
        System.out.format("Dealer Hidden Card Was: %s. Dealer hand is %d, ",
                computerPlayer.getCards().get(1).getDisplayString(), computerPlayer.getBestNonBust());

        if (computerPlayer.getBestNonBust() >= MIN_COMPUTER_VALUE || humanPlayer.isBust()) {
            System.out.println("dealer stops.");
            return;
        }

        System.out.println("dealer draws another hand.");

        while ((computerPlayer.getBestNonBust() < MIN_COMPUTER_VALUE)
                && !computerPlayer.isBust()) {
            Card nextCard = shuffledCards.get(cardIndex++);
            computerPlayer.getCards().add(nextCard);
            computerDraw++;
            System.out.format("Dealer Draw(%d): %s. Dealer hand is %d, ",
                    computerDraw, nextCard.getDisplayString(), computerPlayer.getBestNonBust());
            if (computerPlayer.getBestNonBust() >= MIN_COMPUTER_VALUE) {
                System.out.println("dealer stops.");
            } else {
                System.out.println("dealer draws another hand.");
            }
        }

    }

    private void processFinalValues() {
        System.out.format("Player Hand values %d, Dealer Hand values %d.\n", humanPlayer.getBestNonBust(), computerPlayer.getBestNonBust());

        if (humanPlayer.isBust() && !computerPlayer.isBust()) {
            System.out.println("Player is bust. Dealer WINS!!!");
            return;
        }

        if (!humanPlayer.isBust() && computerPlayer.isBust()) {
            System.out.println("Dealer is bust. Player WINS!!!");
            return;
        }

        if (humanPlayer.getBestNonBust() == computerPlayer.getBestNonBust()) {
            System.out.println("Game ends in DRAW!");
            return;
        }

        if (humanPlayer.getBestNonBust() > computerPlayer.getBestNonBust()) {
            System.out.println("Player WINS!!!");
            return;
        }

        if (humanPlayer.getBestNonBust() < computerPlayer.getBestNonBust()) {
            System.out.println("Dealer WINS!!!");
            return;
        }
    }

    private boolean waitForKeypress() {
        try {
            int charCode = System.in.read();
            while (charCode != (int) 'Y' && charCode != (int) 'y'
                    && charCode != (int) 'N' && charCode != (int) 'n') {
                Thread.sleep(100);
                charCode = System.in.read();
            }

            return (charCode == (int) 'Y' || charCode == (int) 'y');
        } catch (IOException | InterruptedException ex) {
            System.out.println("Error" + ex.toString());
        }

        return false;
    }
}
