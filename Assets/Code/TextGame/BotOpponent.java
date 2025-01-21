/*
Name: Yahya Bilal
Date: June 6-11, 2024
Class Name: BotOpponent
Purpose: Class responsible for training BotOpponent depending on character moveset and stats
*/

import java.util.*; //Importing java.util library
import java.io.*; //Importing java.io library

public class BotOpponent{
    //Initializing variables and objects
    //Related to moves
    private String[] playerMoves; //Array of player's equiped moves
    private String[] botMoves; //Array of Bot's equiped moves
    private  HashMap<String, Integer> moveList = new HashMap<String, Integer>(); //HashMap of all the moves and their damage

    //Training battles
    private GameSprite testPlayer;
    private GameSprite testBot;
    private BattleTurn train;

    private File save = new File("./training.txt"); //Creating file to store all tested battle possibilites

    //BotOpponent constructor values to all the required variables
    //pMoves - Array of player moves
    //bMoves - Array of bot moves
    //moves - HashMap of all the moves in the game and their damage
    //p - player GameSprite
    //b - bot Gamesprite
    BotOpponent(String[] pMoves, String[] bMoves, HashMap<String, Integer> moves, GameSprite p, GameSprite b){
        playerMoves = pMoves;
        botMoves = bMoves;
        moveList = moves;
        testPlayer = p;
        testBot = b;
        train = new BattleTurn(testPlayer, testBot, moveList, playerMoves, botMoves, null);
    }//BotOpponent Constructor

    //Responsible for training the bot based on moveset of player and bot and will save winning scenarios
    public void TrainBot() throws FileNotFoundException{
        //Creating file objects
        PrintWriter write = new PrintWriter(save); //Creating PrintWriter to write all tested possibilities

        //Actual bot training
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                int gain = train.Training(i, j);
                write.println(i + "," + j + "," + gain);
            }//inner For loop
        }//outer For loop
        
        write.close(); //Closing printwriter object
    }//TrainBot Method

    //Choose best move possible based on the bot's training
    public int ChooseMove(int playerMove) throws IOException{
        //Initializing required variables
        BufferedReader read = new BufferedReader(new FileReader(save)); //Buffered reader object to read training file
        save.createNewFile();
        int choice = 0;
        int[] choices = {5, 5, 5, 5};
        String line = ""; //String to hold individual lines before being added to a stringbuilder
        int index = 0;
        int damage = -999;

        //Assigning all the maps to their respective String
        while (line != null){
            line = read.readLine(); //Assigning a single line from a file to 'line'
            if (line != null){
                if (line.substring(0, 1).equals(String.valueOf(playerMove))){
                    String botChoice[] = line.split(",");
                    choices[index] = Integer.parseInt(botChoice[2]);
                    index++;
                }//if
            }//If
        }//While loop

        //Checks which bot moves yeilds the most benefit (highest net gain)
        for (int i = 0; i < choices.length; i++){
            if (choices[i] > damage){
                damage = choices[i];
                choice = i;
            }//If
        }//For loop

        read.close(); //Closing BufferedReader object
        return choice; //Returning bot choice of move
    }//ChooseMove Method
}//BotOpponent Class