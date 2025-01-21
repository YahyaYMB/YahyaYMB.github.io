/*
Name: Yahya Bilal
Date: June 6, 2024
Class Name: Scoreboard
Purpose: Responsible for storing all scores and creating scoreboard
*/

import java.io.*;

public class Save{
    //File objects
    private static File output = new File("./Output.txt");

    //Variables required to save final score and some stats
    private static int score;
    private static int[] playerStats = {0,0,0};

    //Outputs to terminal and writes player stats from this game to Output.txt file
    //wins - number of wins
    //playerHealth - player health
    //stageNum - current stage
    public static void OutputResults(int wins, int playerHealth, int stageNum, GameSprite p) throws IOException{
        output.createNewFile(); //Creatng an actual file
        PrintWriter writeOut = new PrintWriter(output); //PrintWrite to write on output file
        playerStats[0] = wins;
        playerStats[1] = playerHealth;
        playerStats[2] = stageNum;
        playerStats[3] = (wins * 100) + (playerHealth * 10) + (stageNum * 1000) - (p.GetSpeed() + p.GetAttack() + p.GetDefense()) * 10;


        //Writing to terminal and text file
        System.out.println("You have " + wins + " battle wins, " + playerHealth + "hp remaining and are on stage " + stageNum);
        System.out.println("Your score is " + score);
        writeOut.println("WINS:" + wins + "\nREMAINING HP: " + playerHealth + "\nSTAGE: " + stageNum + "\n SCORE: " + score);

        //Verification before moving on
        Quick.PressEnter();
        Quick.ClearScreen();

        writeOut.close(); //Closing printwriter objects 
    }//SaveStats method
}//Scoreboard class