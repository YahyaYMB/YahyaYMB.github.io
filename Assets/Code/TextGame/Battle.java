/*
Name: Yahya Bilal
Date: June 8-9, 2024
Class Name: Battle
Purpose: Class responsible for complete battles and identifiying the winner/loser
*/

import java.io.*; //Importing java.io library
import java.util.*; //Importing java.util library

public class Battle{
    //Initializing required variables and objects
    private HashMap<String, Integer> moveList = new HashMap<String, Integer>(); //List of all the moves in the game
    private String[] playerMoveList; //List of moves the player can use
    public String[] botMoveList; //List of moves that the bot can use
    private int playerDamageLoss; //How much damage the player lost
    
    //GameSprite objects
    private GameSprite player; //Player character
    private GameSprite bot; //Bot character

    //Constructor that will set the two oppponents
    //p - player character
    //b - bot character
    //moves - a hashmap storing all the moves in the game and the damage they do
    //playerMoves - list of moves player currently has equiped
    //playerCurrentHealth - How much health the player currently has
    Battle(GameSprite p, GameSprite b, HashMap<String, Integer> moves, String[] playerMoves, String[] botMoves){
        player = p; //Assigning the proper GameSprite object to player
        bot = b; //Assigning the proper GameSprite object to the bot
        moveList = moves; //Assigning the hashmap of moves to moveList
        playerMoveList = playerMoves; //Assigning the list of player moves to playerMoveList
        botMoveList = botMoves; //Assigning the list of player moves to botMoveList
    }//BattleTurn Constructor

    //Starts the battle and returns true or false depending on if the player wins or loses
    public Boolean Start(int playerHealth) throws IOException{
        //Creating required objects
        BotOpponent enemy = new BotOpponent(playerMoveList, botMoveList, moveList, player, bot);  //Creating bot oponent object
        BattleTurn battle = new BattleTurn(player, bot, moveList, playerMoveList, botMoveList, enemy); //Creating BattleTurn object

        enemy.TrainBot(); //Training bot
        bot.HealthChange(-999); //Resetting bot health

        //loop runs until battle.CheckComplete() returns true
        while (!battle.CheckComplete()){
            battle.turn();
            //If bot is faster
            if (bot.GetSpeed() > player.GetSpeed()){
                player.HealthChange(battle.GetPlayerLoss()); //Changing player health accordingly
                playerDamageLoss += battle.GetPlayerLoss(); //Changeing the values of playerDamageLoss accordingly
                if (player.GetCurrentHealth() > 0){
                 }//If
            }//if
            //Else if player is faster
            else if (player.GetSpeed() > bot.GetSpeed()){
                bot.HealthChange(battle.GetBotLoss()); //Changing player health accordingly
                //If bot is still laive after player move
                if (bot.GetCurrentHealth() > 0){
                    player.HealthChange(battle.GetPlayerLoss()); //Changing bot health accordingly
                }//If
            }//else if
            //Else both are the same speed
            else{
                player.HealthChange(battle.GetPlayerLoss());
                bot.HealthChange(battle.GetBotLoss());
            }//Else
        }//While loop

        //Checking who won based on whether the player has 0 health or the bot opponent
        if (player.GetCurrentHealth() > 0){
            return true;
        }//If
        else{
            return false;
        }//Else
    }//Start method

    //Returns how much damage the player lost during this battle
    public int PlayerHealthLoss(){
        return playerDamageLoss;
    }//PlayerHealthLoss method
}//Battle class