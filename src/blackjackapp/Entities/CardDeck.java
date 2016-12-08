/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjackapp.Entities;

import java.util.*;

/**
 *
 * @author Mihnea
 * This class holds all of the cards in a deck, and can be used to obtain a shuffled set of cards
 */
public class CardDeck {
    
    private List<Card> deckCards;
    
    public CardDeck() {
        InitializeCards();
    }

    private void InitializeCards() {
        this.deckCards = new ArrayList<>();
        
        deckCards.addAll(Card.createAllSuits("A", 1, 11));
        
        for (Integer index = 2; index <= 10; index++) {
            deckCards.addAll(Card.createAllSuits(index.toString(), index));
        }
        
        deckCards.addAll(Card.createAllSuits("J", 10));
        deckCards.addAll(Card.createAllSuits("Q", 10));
        deckCards.addAll(Card.createAllSuits("K", 10));
    }
    
    public List<Card> getDeckCards() {
        return this.deckCards;
    }
    
    public List<Card> getNewShuffledDeckCards() {
        List<Card> shuffledCards = new ArrayList<>();
        
        shuffledCards.addAll(this.deckCards);        
        Collections.shuffle(shuffledCards);
        
        return shuffledCards;
    }
}
