#include <Gamepad.h>

int leftXcenter = 500;
int leftYcenter = 500;
double multiplierLX = 0.254;
double multiplierLY = 0.254;

Gamepad gp;

void setup() {

  pinMode(A2, INPUT);
  pinMode(A3, INPUT);
  pinMode(7,  INPUT_PULLUP); //UP
  pinMode(6,  INPUT_PULLUP); //DOWN
  pinMode(4,  INPUT_PULLUP); //LEFT
  pinMode(5,  INPUT_PULLUP); //RIGHT
  pinMode(9,  INPUT_PULLUP); //Y
  pinMode(10,  INPUT_PULLUP); //X
  pinMode(14,  INPUT_PULLUP); //A
  pinMode(8,  INPUT_PULLUP); //B  
  pinMode(2,  INPUT_PULLUP); //Start
  pinMode(3,  INPUT_PULLUP); //Select
  //pinMode(14, INPUT_PULLUP); //L3
  
  calibrate();
}

void loop() {
  int lx, ly, rx, ry;
  lx = analogRead(A3);
  ly = analogRead(A2);
  //we need to convert a 0-1000 to -127 - 127
  lx = floor((lx - leftXcenter) * multiplierLX);
  ly = floor((ly - leftYcenter) * multiplierLY);
  if(lx > 127) lx = 127;
  if(ly > 127) ly = 127;
  gp.setLeftXaxis(lx);
  gp.setLeftYaxis(ly);
  
  int UP, DOWN, LEFT, RIGHT, X, Y, A, B, Start, Select, Stick;
  UP = digitalRead(7);
  DOWN = digitalRead(6);
  LEFT = digitalRead(4);
  RIGHT = digitalRead(5);
  X = digitalRead(10);
  Y = digitalRead(9);
  A = digitalRead(14);
  B = digitalRead(8);
  Start = digitalRead(2);
  Select = digitalRead(3);
  //Stick = digitalRead(14);     
  
  if(UP == LOW)
    gp.setButtonState(7, true);
  else
    gp.setButtonState(7, false);

  if(DOWN == LOW)
    gp.setButtonState(6, true);
  else
    gp.setButtonState(6, false);    

  if(LEFT == LOW)
    gp.setButtonState(4, true);
  else
    gp.setButtonState(4, false); 

  if(RIGHT == LOW)
    gp.setButtonState(5, true);
  else
    gp.setButtonState(5, false);     

  if(X == LOW)
    gp.setButtonState(10, true);
  else
    gp.setButtonState(10, false);

  if(Y == LOW)
    gp.setButtonState(9, true);
  else
    gp.setButtonState(9, false);

  if(A == LOW)
    gp.setButtonState(14, true);
  else
    gp.setButtonState(14, false);  

  if(B == LOW)
    gp.setButtonState(8, true);
  else
    gp.setButtonState(8, false);
 
  if(Start == LOW)
    gp.setButtonState(2, true);
  else
    gp.setButtonState(2, false);

  if(Select == LOW)
    gp.setButtonState(3, true);
  else
    gp.setButtonState(3, false);

  //if(Stick == LOW)
    //gp.setButtonState(14, true);
  //else
    //gp.setButtonState(14, false);            

  delay(20);
}

void calibrate()
{
  int lx, ly;
  int i = 0;
  while(i < 13)
  {
    lx = analogRead(A2);
    ly = analogRead(A3);
    bool validLX = lx > (leftXcenter - 100) && lx < (leftXcenter + 100);
    bool validLY = ly > (leftYcenter - 100) && ly < (leftYcenter + 100);
    if(validLX && validLY)
    {
      i++;
      //nothing to do here!
    }
    else i = 0;
    delay(20);
  }
  leftXcenter = lx;
  leftYcenter = ly;
  multiplierLX = (double)127 / (double)lx;
  multiplierLY = (double)127 / (double)ly;
}
