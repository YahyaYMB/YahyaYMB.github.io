/*
Name: Yahya Bilal
Date: June 8-9, 2024
Class Name: MapExplore
Purpose: Class responsible map exploration 
*/

import java.util.*; //import java.util library
import java.io.*; //import java.io library

public class MapExplore{
    //Initializing required variables and objects
    int mapNum; //Which map/stage the player is currently on
    Scanner input = new Scanner (System.in);

    //Stringbuilder and map to hold 
    StringBuilder map = new StringBuilder();
    String[] maps = {"", "", "", "", "", "", "", "", "", ""};

    //Constructor which chooses the required number of maps from a pool of maps
    //stages - required number of maps
    MapExplore(int stages) throws IOException{
        for (int i = 1; i <= stages; i++){
            //Creating required objects and variables
            FileReader fileReader = new FileReader("./map" + i + ".txt"); //Filreader object
            BufferedReader read = new BufferedReader(fileReader); //Buffered reader object
            String line = ""; //String to hold individual lines before being added to a stringbuilder

            //Assigning all the maps to their respective String
            while (line != null){
                line = read.readLine(); //Assigning a single line from a file to 'line'
                //If the line actually has a text
                if (line != null){
                    map.append(line + "\n"); //Appending a line from the file to a stringbuilder
                }//if
            }//While loop
            maps[i- 1] = map.toString(); //Assigning the map stored in the stringbuilder to proper sport in the 'maps' array
            map.setLength(0); //Resetting the stringbuilder for rest of the maps
            //Closing required objects
            fileReader.close();
            read.close();
        }//For loop
    }//MapExplore Constructor

    //Responsible for allowing user to explore all the maps and displays map on screen
    //stageNum - What number stage it currently is
    //explore - list of areas on the stage already explored
    public int Explore(int stageNum, int[] explored){
        System.out.println("Here is a map for stage " + (stageNum + 1)); //Indicating the stage number
        System.out.println(maps[stageNum]); //Print out the map of the stage
        System.out.println("Please enter the area you would like to explore, remeber you cannot visit the same area twice: ");
        //Loops until a valid value for are is provided by the user
        while (true){
            int area = Prompt.GetInt(1, 4); //Allowing the user to enter the area they want to go to
            //If the area entered has not been explored so far
            if (!(Quick.contains(explored, area))){
                return area;
            }//if
            //Else the are entered has been explored
            else{
                Quick.ClearScreen();
                System.out.println("It seems that you have already visited this area, please enter another area: ");
                return 0;
            }//Else
        }//While loop
    }//Explore Method
}//MapExplore Class