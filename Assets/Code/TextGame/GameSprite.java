/*
Name: Yahya Bilal
Date: June 3, 2024
Class Name: GameSprite
Purpose: Responsible for creating character objects and managing character stats
*/

public class GameSprite{
    //Initilaizing Required Variables for Character Stats
    private int health;
    private int currentHealth;
    private int speed;
    private int attack;
    private int defense;

    //Constructor that will set character attributes
    //h - health value (also current amount of health)
    //s - speed value
    //a - attack value
    //d - defense value
    GameSprite(int h, int s, int a, int d){
        health = h; //Setting health
        currentHealth = h; //Setting currentHealth
        speed = s; //Setting speed
        attack = a; //Setting attack
        defense = d; //Setting defense
    }//GameSprite Constructor

    //Method that will take care of any health changes for the character
    //damage - amount of health be added or deducted to the current health stat
    public void HealthChange(int damage){
        currentHealth -= damage; //deducting/adding health depending on if the value is positive or negative
        //If the character heals more than their maximum health
        if (currentHealth > health){ 
            currentHealth = health; //Setting currentHealth to health which is the maximum health of the character
        }//IF
        //Else if the characters health is below 0
        else if(currentHealth < 0){
            currentHealth = 0; //Setting character health to 0
        }//Else if
    }//HealthChange  method

    //Method that will upgrade character statistice when needed
    //h - amount of upgrade to defense health stat
    //s - amount of upgrade to defense speed stat
    //a - amount of upgrade to defense attack stat
    //d - amount of upgrade to defense stat
    //heal - if the user has their health recovered
    public void Upgrade(int h, int s, int a, int d, Boolean heal){
        health += h; //Adding h to health variable to increase max health
        //If heal is equal to true
        if (heal){
            currentHealth = health; //Setting curent health to healh (healing character)
        }//If
        speed += s; //Adding s to speed variable to increase speed stat
        attack += a; //Adding a to attack variable to increase attack stat
        defense += d; //Adding d to defense variable to increase defense stat
    }//Upgrade Method

    /*Retriving all attributes of the character object*/
    //Returns health stat
    public int GetHealth(){
        return health;
    }//GetHealth method

    //Returns value of currentHealth
    public int GetCurrentHealth(){
        return currentHealth;
    }//GetCurrentHealth method

    //Returns speed stat
    public int GetSpeed(){
        return speed;
    }//GetSpeed method

    //Returns attack stat
    public int GetAttack(){
        return attack;
    }//GetAttack method

    //Returns defense stat
    public int GetDefense(){
        return defense;
    }//GetDefense method
}//GameSprite Class
