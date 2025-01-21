/*
Name: Yahya (provided by Mr.Couch)
Date: June 9, 2024
Project Name: Prompt
Purpose: Ensure that certain values entered meet specific criteria to prevent my code from crashing
*/

import java.util.Scanner; //Importing Scanner class

public class Prompt{
     private static final Scanner in = new Scanner(System.in);

     // Reads console input and checks if it is the correct type (int)
     public static int GetInt(){
          // Loop until a valid entry is received
          while(true){
               // Check if it is an integer
               if(Prompt.in.hasNextInt()){
                    // Integer inputted, return result
                    int answer = Prompt.in.nextInt();
                    Prompt.in.nextLine();
                    return answer;
               }//if
               else{
                    // Invalid data type entered
                    String input = Prompt.in.nextLine();
                    System.out.println("Error: " + input + " is not an integer, please provide an integer");
               }//Else
          }//While loop
     }//GetInt method
     
     // Reads console input and checks if it is the correct type (int)
     // and validates the number is within the requested range - lowValue to highValue
     public static int GetInt(int lowValue, int highValue){
          // Loop until a valid entry is received
          while(true){             
               // Check if it is an integer
               if(Prompt.in.hasNextInt()){
                    // Integer inputted, return result
                    int answer = Prompt.in.nextInt();
                    Prompt.in.nextLine();
                    
                    // Check if the number is in required range
                    if (answer < lowValue || answer > highValue) {
                         //Out of Range Error
                         System.out.println("The number entered is out of range ensure that the input fall within " + lowValue + " and " + highValue );
                    }//If
                    else {
                         //answer is OK
                         return answer;
                    }//Else
               }//If
               else{
                    // Invalid data type entered
                    String input = Prompt.in.nextLine();
                    System.out.println("Error: " + input + " is not an integer, please provide a integer");
               }//Else
          }//While loop
     }//GetInt method

     //Reads console input and returns it
     public static String GetString(){
               String answer = Prompt.in.nextLine();
               return answer;
     }//GetString method
}//Prompt class