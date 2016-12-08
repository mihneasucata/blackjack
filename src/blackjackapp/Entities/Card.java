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
 * This class is a wrapper for a single card
 */
public class Card {
    
    public enum CardSuits {
        HEART("Heart"), SPADE("Spade"), CLUB("Club"), DIAMOND("Diamond");
        
        private final String friendlyName;
        
        private CardSuits(String name) {
            this.friendlyName = name;
        }
        
        public String getFriendlyName() {
            return this.friendlyName;
        }
    }
    
    private String name;
    private int value;
    private int alternativeValue;
    private CardSuits suit;
    
    public Card(String name, int value, CardSuits suit) {
        this(name, value, 0, suit);
    }
    
    public Card(String name, int value, int alternativeValue, CardSuits suit) {
        this.name = name;
        this.value = value;
        this.alternativeValue = alternativeValue;
        this.suit = suit;
    }
    
    public String getName() {
        return this.name;
    }
    
    public int getValue() {
        return this.value;
    }
    
    public int getAlternativeValue() {
        return this.alternativeValue;
    }
    
    public CardSuits getSuit() {
        return this.suit;
    }
    
    public String getDisplayString() {
        return String.format("%s %s", getName(), getSuit().getFriendlyName());
    }
    
    public static List<Card> createAllSuits(String name, int value) {
        return createAllSuits(name, value, 0);
    }
    
    public static List<Card> createAllSuits(String name, int value, int alternativeValue) {
        List<Card> result = new ArrayList<>();
        
        for (CardSuits suitValue : CardSuits.values()) {
            result.add(new Card(name, value, alternativeValue, suitValue));
        }
        
        return result;
    }
}
