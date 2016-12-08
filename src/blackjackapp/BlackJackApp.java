/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjackapp;

import blackjackapp.Entities.Game;

/**
 *
 * @author Mihnea
 */
public class BlackJackApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Game newGame = new Game();
        
        newGame.runGame();
    }
    
}
