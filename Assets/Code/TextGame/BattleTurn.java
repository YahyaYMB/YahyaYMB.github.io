/*
Name: Yahya Bilal
Date: June 5-8, 2024
Class Name: BattleTurn
Purpose: Class responsible for individual turns in battles
*/

import java.io.*;
import java.util.*;

public class BattleTurn{
    //Initializing required variables and objects
    static Scanner input; //Scanner object to get use inputs

    //Related to choice of move
    private HashMap<String, Integer> moveList = new HashMap<String, Integer>();
    private String playerMove; //What move/attack the player has chosen
    private String botMove; //What move/attack the bot has chosen
    private String[] playerMoveList; //List of moves the player can use
    private String[] botMoveList; //List of moves the bot can use
    private int moveChoice = 0;

    //Damage lost
    private int playerDamageLoss; //How much damage the player lost
    private int botDamageLoss; //How much damage the bot lost

    //GameSprite and BotOpponent objects
    private GameSprite player; //Player character
    private GameSprite bot; //Bot character
    private BotOpponent enemy; //enemy AI

    //Constructor that will set the two oppponents
    //p - player character
    //b - bot character
    //moves - a hashmap storing all the moves in the game and the damage they do
    //playerMoves - list of moves player currently has equiped
    //e - BotOpponent object
    BattleTurn(GameSprite p, GameSprite b, HashMap<String, Integer> moves, String[] playerMoves, String[] botMoves, BotOpponent e){
        player = p; //Assigning the proper GameSprite object to player
        bot = b; //Assigning the proper GameSprite object to the bot
        moveList = moves; //Assigning the hashmap of moves to moveList
        playerMoveList = playerMoves; //Assigning the list of player moves to playerMoveList
        botMoveList = botMoves; //Assigning the list of bot moves to botMoveList
        enemy = e; //Assigning the BotOpponent object to enemy
    }//BattleTurn Constructor

    //Method responsible for retieving and verifying the players move
    public void PlayerMove(){
        System.out.print("Enter your move(");
        //Prints out all the current player moves
        for (int i  = 0; i < playerMoveList.length; i++){
            System.out.print((i+1) + "-" + playerMoveList[i]);
            if (i <= 2){
                System.out.print(", ");
            }//If
        }//For Loop
        System.out.print("): ");

        //Until user provides a valid response
        while (moveChoice == 0){
            moveChoice = Prompt.GetInt(1, 4);
        }//While loop

        playerMove = playerMoveList[moveChoice - 1]; //Returning user choice of move
    }//PlayerMove Method 

    //Method responsible for retrieving the bot's choice of move
    public void BotMove() throws IOException{
        botMove = botMoveList[enemy.ChooseMove(moveChoice - 1)];
    }//BotMove Method

    //Method responsible for calculating damage done
    //playerAttack - if the player is attack or not, in which case the bot is attacking
    public void DamageLoss(Boolean playerAttack){
        //If player is attacking
        if (playerAttack){
            //If the player uses a move which is supposed to heal them
            if (playerMove.contains("heal")){
                playerDamageLoss += moveList.get(playerMove); //Adding a negative value to playerDamageLoss to repersent healing
            }//If
            //Else if the player uses an attack and not a block
            else if (!playerMove.contains("block")){
                int bonus = player.GetAttack() - bot.GetDefense(); //Calculating bonus damage based on attack and defense stats
                int damage = moveList.get(playerMove) + bonus; //Calculating total damage
                botDamageLoss += damage; //Setting the amount of health lost
            }//else if
        }//If
        //Else bot is attacking
        else{
            //If the bot uses a move which is supposed to heal them
            if (botMove.contains("heal")){
                botDamageLoss += moveList.get(botMove); //Adding a negative value to botDamageLoss to repersent healing
            }//If
            //Else if the bot uses an attack and not a block
            else if (!botMove.contains("block")){
                int bonus = bot.GetAttack() - player.GetDefense(); //Calculating bonus damage based on attack and defense stats
                int damage = moveList.get(botMove) + bonus; //Calculating total damage
                playerDamageLoss += damage; //Setting the amount of health lost
            }//else if
        }//else
    }//DamageLoss Method

    //Method responsible for executing a single turn of the battle
    public void turn() throws IOException{
        playerDamageLoss = 0; //Resetting playerDamageLoss
        botDamageLoss = 0; //Resetting botDamageLoss
        moveChoice = 0; //Resetting value of moveChoice
        
        System.out.println("You have " + player.GetCurrentHealth() + "hp and your opponent has " + bot.GetCurrentHealth() + "hp");

        //Player and bot choosing move 
        PlayerMove(); //Player choosing move
        BotMove(); //Bot choosing move 

        //Damage calculation ussing Damageloss method
        DamageLoss(true); //Calculating damage on bot
        DamageLoss(false); //Calculating damage on player

        //Checking if the player or bot used a block move
        if (!CheckComplete() && playerMove.contains("block") && botMove.contains("ash")){
            playerDamageLoss += moveList.get(playerMove);
        }//If
        if (!CheckComplete() && botMove.contains("block") && playerMove.contains("ash")){
            botDamageLoss += moveList.get(botMove);
        }//If
    }//Turns Method

    //Method responsible for bot training
    //write - PrintWriter object to write all the possibilities per turn in battle and there net gain
    //pMove - Choice of player move as an integer
    //bMove - Choice of bot move as an integer
    public int Training(int pMove, int bMove){
        //Calculating damage loss
        playerMove = playerMoveList[pMove]; //Assigning player move for damage calculation
        botMove = botMoveList[bMove]; //Assigning bot move for damage calculation
        DamageLoss(false); //Calculating damage dealt on player
        DamageLoss(true); //Calculating damage dealt on bot
        //Checking if the player or bot used a defencive move
        if (!CheckComplete() && playerMove.contains("block") && botMove.contains("ash")){
            playerDamageLoss += moveList.get(playerMove); //Reducing damage dealt if there is a block
        }//If
        if (!CheckComplete() && botMove.contains("block") && playerMove.contains("ash")){
            botDamageLoss += moveList.get(botMove); //Reducing damage dealt if there is a block
        }//If
        int netGain = playerDamageLoss - botDamageLoss; //Calculating net gain

        //Ressetting damage loss values
        playerDamageLoss = 0;
        botDamageLoss = 0;

        return netGain;//Returning net gain (higher net gain == better)
    }//Training method

    //Returns how much damage the player has lost in this turn
    public int GetPlayerLoss(){
        return playerDamageLoss;
    }//GetPlayerLoss Method

    //Returns how much damage the bot has lost in this turn
    public int GetBotLoss(){
        return botDamageLoss;
    }//GetBossLoss Method

    public Boolean CheckComplete(){
        //If either the player or bot is dead
        if (bot.GetCurrentHealth() <= 0 || player.GetCurrentHealth() <= 0){
            return true;
        }//If
        //Else both are still alive
        else{
            return false;
        }//Else
    }//CheckComplete Method
}//BattleTurn Class