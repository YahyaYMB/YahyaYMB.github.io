/*
Name: Yahya Bilal
Date: June 6, 2024
Class Name: Quick
Purpose: Class responsible for training BotOpponent depending on character moveset and stats
*/
import java.util.Random;

public class Quick{
    //Method to delay program by certain number of milliseconds
    //ms - amount of milliseconds to pause the program
    public static void Wait(int ms){
        try{
            Thread.sleep(ms); //Using threading to make it run with other parts of the program
        }//Try
        catch(InterruptedException ex){ //Error handling to prevent common hiccups
            Thread.currentThread().interrupt(); //Interrupting the process if something goes wrong
        }//Catch
    }//Wat method

    //Method to clear screen when needed
    public static void ClearScreen(){  
        System.out.print("\033[H\033[2J"); //Code to clearscreen
        System.out.flush(); 
    }//ClearScreen method

    //Makes user press enter before moving on with the program
    public static void PressEnter(){
        System.out.println("PRESS ENTER TO CONTINUE"); //Message
        Prompt.GetString();
    }//PressEnter Method

    //Checks if the user would like to exit the game
    public static Boolean AskExit(){
        Random num = new Random(); //Creating a random object called 'num'
        int code = num.nextInt(9999 - 1000) + 1000; //Generating a random 4 digit number to act as an exit code
        System.out.println("If you would like to exit the game please enter this code: " + code); //Asking user to enter exit code if they choose
        System.out.println("Otherwise enter any other integer");
        int input = Prompt.GetInt(); //Allowing user to enter what they would like to do
        //If the user provided input is the same as the generated code
        if (code == input){
            //Confirming user choice
            input = 0;
            System.out.println("Are you sure you would like to exit? (1 = No, 2 = Yes)"); 
            while (input == 0){
                input = Prompt.GetInt(1, 2);
            }//While loop
            if (input == 2){
                return true; //Returning true if the user would like to exit
            }//If
        }//If
        return false; //Retruning false if the user would like to continue
    }//AskExit method

    //Checks if the user would like to exit the game
    public static Boolean AskRestart(){
        Random num = new Random(); //Creating a random object called 'num'
        int code = num.nextInt(9999 - 1000) + 1000; //Generating a random 4 digit number to act as an exit code
        System.out.println("If you would like to restart the game please enter this code: " + code); //Asking user to enter exit code if they choose
        System.out.println("Otherwise enter any other integer");
        int input = Prompt.GetInt(); //Allowing user to enter what they would like to do
        //If the user provided input is the same as the generated code
        if (code == input){
            //Confirming user choice
            input = 0;
            System.out.println("Are you sure you would like to restart? (1 = No, 2 = Yes)"); 
            while (input == 0){
                input = Prompt.GetInt(1, 2);
            }//While loop
            if (input == 2){
                return true; //Returning true if the user would like to exit
            }//If
        }//If
        return false; //Retruning false if the user would like to continue
    }//AskExit method

    //Checks if an array holds a certain integer
    public static boolean contains(final int[] array, final int key) {
        for (final int i : array) {
            if (i == key) {
                return true;
            }//if
        }//For
        return false;
    }//Contains method
}//Quick Class