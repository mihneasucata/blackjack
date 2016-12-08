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
 * This class holds the cards a player (either human or computer), has been dealt throughout the game
 */
public class PlayerHand {
    
    private final List<Card> cards;
    
    public PlayerHand() {
        this.cards = new ArrayList<>();
    }
    
    public List<Card> getCards() {
        return this.cards;
    }
    
    private int getHandValue(boolean useAlternativeValue) {
        int result = 0;
        for (int index = 0; index < this.cards.size(); index++) {
            Card card = this.cards.get(index);
            result += (useAlternativeValue && card.getAlternativeValue() > 0 ? card.getAlternativeValue() : card.getValue());
        }
        
        return result;
    }
    
    public int getBestNonBust() {
        int result = Math.max(getHandValue(false), getHandValue(true));
        
        return (result > Game.MAX_VALUE ? getHandValue(false) : result);
    }
    
    public boolean isBlackjack() {
        return (cards.size() == 2 && getHandValue(true) == Game.MAX_VALUE);
    }
    
    public boolean isBust() {
        return (getHandValue(false) > Game.MAX_VALUE) && (getHandValue(true) > Game.MAX_VALUE);
    }
}