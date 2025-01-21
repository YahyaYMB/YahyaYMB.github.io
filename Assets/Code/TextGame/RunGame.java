/*
Name: Yahya Bilal
Date: June 6-11, 2024
Class Name: RunGame
Purpose: Class responsible for running the game and using a collection of the other classes
*/

import java.io.*; //import java.io library
import java.util.*; //import java.util library

public class RunGame{
    //Initializing required variables and objects
    //Basic objects
    static Scanner input = new Scanner (System.in);

    //Arrays and Hashmap for moves
    private static HashMap<String, Integer> moveList = new HashMap<String, Integer>(); //Hashmap for all moves and damage
    static String[] playerMoves = {"slash", "dart", "heal", "block"}; //Array holding all player moves
    static String[] botMoves = {"", "", "", ""}; //Holds bot's moves

    //For Gameplay
    static int stages = 0;
    static int battleNum = 0;
    static Boolean gameOver = false;
    static Boolean win;

    //Method responsible for setting starting player move and bot moves along with overall move list
    static void SetMoveList(int stage, GameSprite player){
        //Close ranged attacks
        moveList.put("slash", 10); 
        moveList.put("smash", 15); 
        moveList.put("dashing smash", 20);

        //Long range attacks (cannot be blocked)
        moveList.put("dart", 5);
        moveList.put("shuriken", 10);
        moveList.put("arrows", 15);

        //Healing
        moveList.put("heal", -5);
        moveList.put("advanced heal", -10);
        moveList.put("ultimate heal", -15);

        //Blocking (against close ranged attacks)
        moveList.put("block", -10);
        moveList.put("advanced block", -15);
        moveList.put("ultimate block", -20);

        //Setting bot moves according to stage number
        if (stage == 1){
            botMoves[0] = "slash";
            botMoves[1] = "dart";
            botMoves[2] = "heal";
            botMoves[3] = "block";
        }//If
        else if (stage == 2){
            botMoves[0] = "smash";
            playerMoves[0] = "smash";
            player.Upgrade(1, 1, 1, 1, true);
        }//else if
        else if (stage == 3){
            botMoves[1] = "shuriken";
            playerMoves[1] = "shuriken";
            player.Upgrade(1, 1, 1, 1, true);
        }//else if
        else if (stage == 4){
            botMoves[2] = "advanced heal";
            playerMoves[2] = "advanced heal";
            player.Upgrade(1, 1, 1, 1, true);
        }//else if
        else if (stage == 5){
            botMoves[3] = "advanced block";
            playerMoves[3] = "advanced block";
            player.Upgrade(1, 1, 1, 1, true);
        }//else if
        else if (stage == 6){
            botMoves[0] = "dashing smash";
            playerMoves[0] = "dashing smash";
            player.Upgrade(1, 1, 1, 1, true);
        }//else if
        else if (stage == 7){
            botMoves[1] = "arrows";
            playerMoves[1] = "arrows";
            player.Upgrade(1, 1, 1, 1, true);
        }//else if
        else if (stage == 8){
            botMoves[2] = "ultimate heal";
            playerMoves[2] = "ultimate heal";
            player.Upgrade(1, 1, 1, 1, true);
        }//else if
        else if (stage == 9){
            botMoves[1] = "ultimate block";
            playerMoves[1] = "ultimate block";
            player.Upgrade(1, 1, 1, 1, true);
        }//else if
    }//SetMoveList Method

    static GameSprite SetPlayerStats(){
        //Variables to hold all the required player stats
        int health = 50;
        int speed = 0;
        int attack = 0;
        int defense = 0;
        
        //Messege to user
        System.out.println("Now is your chance to set your character's stats\n" +
        "You can set your character's speed, attack and defense stats\n" +
        "Your health will be preset to 50, but you will be provided the ability to upgrade it during the game\n"+
        "Remember, each stat can only be set to a max of 5 and a minimum of 1, and will be used to calculate a score bonus at the end\n"
        + "The higher the stat the easier the game will be");

        //Allowing user to set stats and creating GameSprite object
        while (speed == 0){
            System.out.println("Enter Speed Stat(determines who goes first in battle): ");
            speed = Prompt.GetInt(1, 5); //Allowing user to enter speed stat
        }//While loop

        while (attack == 0){
            System.out.println("Enter Attack Stat(will determine damage bonus in battle): ");
            attack = Prompt.GetInt(1, 5); //Allowing user to attack speed stat
        }//While loop

        while (defense == 0){
            System.out.println("Enter Defense Stat(will reduce the amount of damage taken): ");
            defense = Prompt.GetInt(1, 5); //Allowing user to defense speed stat
        }//While loop

        GameSprite player = new GameSprite(health, speed, attack, defense); //Creating GameSprote object based on collected stats

        Quick.PressEnter();
        Quick.ClearScreen();

        return player; //Returning gamesprite object
    }//SetPlayerStats method

    public static void main(String[] args) throws IOException{
        //When game starts
        Quick.ClearScreen();
        System.out.println("Welcome to this combat game");
        Quick.PressEnter();
        Quick.ClearScreen();
        System.out.println("In this game all actions will be text based");
        Quick.PressEnter();
        Quick.ClearScreen();

        //While loop that runs until the player chooses not to restart
        while (true){
            //Required to set on start
            GameSprite player = SetPlayerStats(); //Allowing user to set their characters stats
            Boolean restart = false; //Stores whether the user would like to restart

            //Gameplay
            while (stages == 0){
                System.out.println("Please enter the number of levels you would like play(1-10): ");
                stages = Prompt.GetInt(1, 10); //Allowing the user to enter how mnay stages they would like to play
            }//While loop
            MapExplore map = new MapExplore(stages); //Creating a MapExplore object which takes in the number of stages required

            //For loop that allows the user to explore all the stages before conclduing the game, unless they die first
            for (int i = 0; i < stages; i++){
                Quick.ClearScreen(); //Clearing screen
                SetMoveList(i+1, player);
                GameSprite bot = new GameSprite(20 + i*i, 3 + i, 3 + i, 3 + i);
                int[] explored = {0, 0, 0, 0}; //Array to hold the explored areas from this stage

                //Loop runs so long as 'explored' contains 0's
                while (Quick.contains(explored, 0)){
                    int area = map.Explore(i, explored); //Allowing user to choose which area they would like to explore
                    if (area > 0){
                        explored[area - 1] = area; //Adding the explored are to the list of areas explored
                        Quick.ClearScreen(); //Clearing screen
                        Battle fight = new Battle(player, bot, moveList, playerMoves, botMoves); //Battle between player and bot

                        //Message before the start of the first battle
                        if (battleNum == 0){
                            System.out.println("This is an introductory battle (bot will have inflated defense stat)\n" + 
                            "Use this as a chance to experiment with the built in machine learning algorithm");
                            Quick.PressEnter();
                            Quick.ClearScreen();
                        }//If

                        //Sarting battle of entered area
                        win = fight.Start(player.GetCurrentHealth()); //Staring battle
                        Quick.ClearScreen(); //Clearing screen

                        //If the game isn't over, but asking the user if they would 
                        if (win.equals(true) && i <= stages-1){
                            gameOver = Quick.AskExit(); //Asking the user if they would like to exit
                            if (gameOver.equals(true)){
                                stages = 0;
                                break;
                            }//If
                            restart = Quick.AskRestart(); //asking the user if they would like to restart the game
                            if (restart.equals(true)){
                                break;
                            }//If
                            Quick.ClearScreen();
                        }//If
                    }//if
                    //Breaking while loop when player dies
                    if (!win || gameOver.equals(true)){
                        if (!win){
                            System.out.println("You lost the battle");
                            Save.OutputResults(battleNum, player.GetCurrentHealth(), i + 1, player); //Showing player stats from this round
                        }//If
                        break;
                    }//If
                    battleNum++;
                    System.out.println("You won the battle");
                    Save.OutputResults(battleNum, player.GetCurrentHealth(), i + 1, player); //Showing player stats from this round
                    if (restart.equals(true)){
                        System.out.println("Press ENTER to proceed to new game");
                        Prompt.GetString();
                        break;
                    }//If
                }//While loop
                //Breaking for loop when player dies
                if (!win || gameOver.equals(true)){
                    break;
                }//If 
            }//For loop

            //Asking the user if they would like to play again
            if (restart.equals(false) && gameOver.equals(false)){
                System.out.println("Would you like to play again? (1 = No, 2 = Yes)");
                int choice = 0;
                while (choice == 0){
                    choice = Prompt.GetInt(1, 2);
                }//While loop
                if (choice == 1){
                    System.out.println("Press ENTER to proceed to exit screen");
                    Prompt.GetString();
                    Quick.ClearScreen();
                    System.out.println("EXITING GAME");
                    break;
                }//If
            }//If
            if (gameOver.equals(true)){
                System.out.println("Press ENTER to proceed to exit screen");
                Prompt.GetString();
                Quick.ClearScreen();
                System.out.println("EXITING GAME");
                break;
            }//If
        }//While loop
    }//main
}//RunGame Class