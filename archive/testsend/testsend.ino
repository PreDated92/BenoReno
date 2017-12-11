#include <SoftwareSerial.h>
#include <LiquidCrystal.h>
LiquidCrystal lcd(12, 9, 5, 4, 3, 2);

#define rxPin 10
#define txPin 11
#define tempsensor A0
#define bpsensor A1
#define hbsensor A2

SoftwareSerial mySerial =  SoftwareSerial(rxPin, txPin);
//Temp----------------------------------------------------------------------------------
float tem;
float tempstart, tempstop, tempavg;

//BP------------------------------------------------------------------------------------
const int motorPin = 1;
int END = 0; //counter to run program once and end
float transducer = A1;
const int dumpValve = 8;
float MAP = 0;
int pressure = 0;
int b=0;
int sys,dia;
int xx = 0;
int bpm[10] = {70,64,60,68,70,72,68,70,69,71};
byte heart[8] =
{
  0b00000,
  0b01010,
  0b11111,
  0b11111,
  0b11111,
  0b01110,
  0b00100,
  0b00000
};

//Heart------------------------------------------------------------------------------------

void setup()
{
  Serial.begin(115200);
  //Temp----------------------------------------------------------------------------------
  pinMode(rxPin, INPUT);
  pinMode(txPin, OUTPUT);
  pinMode(13,OUTPUT);
  
  //BP------------------------------------------------------------------------------------
  lcd.begin(16, 2);
  lcd.createChar(0, heart);
  pinMode(motorPin, OUTPUT);
  pinMode(dumpValve, OUTPUT);
  lcd.setCursor(1,0);
  lcd.print("SMART NURSING");
  lcd.setCursor(4,1);
   lcd.print("SYSTEM");
   lcd.setCursor(11,1);
  lcd.write(byte(0));

  
  mySerial.begin(9600);

}

void loop()
{
  int finaltemp = tempavg;
  digitalWrite(13,LOW);
  
  if(mySerial.available()>0)
  {
    char re = mySerial.read();
    switch(re)
    {
      //Temp----------------------------------------------------------------------------------
      case 'T':
        tempgraph();
      break;
      //----------------------------------------------------------
      case 'A':
        mySerial.print(' ');
        mySerial.print('F');
        mySerial.println(finaltemp);
        Serial.println(finaltemp);
        delay(100);
      break;
      //BP------------------------------------------------------------------------------------       
      
      case 'D':
        initbp();
      break;
      
      //----------------------------------------------------------
      case 'C':
      mySerial.print(' ');
      mySerial.print('A');
      mySerial.println(b);
      delay(100);
      mySerial.print(' ');
      mySerial.print('B');
      mySerial.println(sys);
      delay(100);
      mySerial.print(' ');
      mySerial.print('C');
      mySerial.println(dia);
      delay(100);
      break;
      
      //HeartBeat------------------------------------------------------------------------------------      
      case 'P':
        pulsegraph();
      break; 
      
      case 'E':
        mySerial.print(' ');
        mySerial.print('E');
        mySerial.println(bpm[xx]);
        delay(100);
      break; 
    }
  }
  
}

//Temp-------------------------------------------------------------------------------------------------------------
 void tempgraph()
 {
  tempstart = analogRead(tempsensor);
  tempstart = tempstart * 0.48828125;
  while(1)
  {
    mySerial.print(' ');
    mySerial.print('s');
    mySerial.println(floatMap(analogRead(A1),0,1023,0,5),2);
    delay(100);
    
    if(mySerial.available()>0)
    {
      if(mySerial.read() == 'Q')
      tempstop = analogRead(tempsensor);
      tempstop = tempstop * 0.48828125;
      tempavg = (tempstop + tempstart) / 2; 
      mySerial.print(' ');
      
      if(tempavg<27)
      {
        mySerial.print('C');
        mySerial.print(tempavg);
      }
      else if(tempavg >=27 && tempavg <33)
      {
        mySerial.print('N');
        mySerial.print(tempavg);
      }
      else
      {
        mySerial.print('H');
        mySerial.print(tempavg);
      }
       
      return;
    }
  }
 }
 
//--------------------------------------------
float floatMap(float x, float inMin, float inMax, float outMin, float outMax)
{
  float mtemp;
  mtemp = (x-inMin)*(outMax-outMin)/(inMax-inMin)+outMin;
  
  return mtemp;
}

//BP-------------------------------------------------------------------------------------------------------------
void initbp()
 {

    delay(2000);
    digitalWrite(dumpValve,HIGH); //674 = 3.3V
    digitalWrite(motorPin,HIGH);
    delay(25000);
    digitalWrite(motorPin,LOW);
    delay(5000);

    int x[] = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    int time[] = {250,250,250,250,250,250,250,250,330,330,330,330,330,330,330};
    int y[] = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    int p1;
    int p2;
    int j;
    for (j=0;j<15;j=j+1)
    {
      digitalWrite(dumpValve, 0);
      delay(time[j]);
      digitalWrite(dumpValve, 674);
      delay(250);
      pressure = analogRead(transducer);
      mySerial.print(' ');
      mySerial.print('s');
      mySerial.println(floatMap1(analogRead(A1),0,1023,0,5),2);
      delay(100);
      x[j] = pressure;
      p1 = analogRead(transducer);
      mySerial.print(' ');
      mySerial.print('s');
      mySerial.println(floatMap1(analogRead(A1),0,1023,0,5),2);
      delay(1000);
      p2 = analogRead(transducer);
      mySerial.print(' ');
      mySerial.print('s');
      mySerial.println(floatMap1(analogRead(A1),0,1023,0,5),2);
      delay(200);
      y[j] = max(p1,p2) - pressure;
    }
    
    int maxm = 0;
    int i;
    for (i=5;i<15;i=i+1)
    {
      if(y[i]>maxm){
        maxm = y[i];
      }
    }
    
    int index[]={0,0,0,0,0,0,0,0,0,0};
    int k;
    int l=0;
    for (k=5;k<15;k=k+1){
      if (y[k]==maxm){
        index[k-5] = k;
        l++;
      }
    }
    
    int total=0;
    int a;
    for (a=0;a<l;a++){
      total = x[index[a]] + total;
    }
      
    float MAP = ((((total / l)-500)*200/102)+25);
     b=abs(MAP);
     sys = b*1.1;
    dia = b*0.8;
    lcd.clear();
    lcd.print("MAP:");   lcd.print(" ");
    lcd.print(b);lcd.print("mmHg");
      mySerial.print(' ');
      mySerial.print('A');
      mySerial.println(b);
      delay(100);
    lcd.setCursor(0, 1);
    delay(5000);
    lcd.clear();
    lcd.setCursor(0, 0);
    lcd.print("Sys:");  lcd.print(" ");lcd.print(sys);lcd.print("mmHg");
     lcd.setCursor(0, 1);
      mySerial.print(' ');
      mySerial.print('B');
      mySerial.println(sys);
      delay(100);
    lcd.print("Dia:");lcd.print(" "); lcd.print(dia);lcd.print("mmHg");
      mySerial.print(' ');
      mySerial.print('C');
      mySerial.println(dia);
      delay(100);
    digitalWrite(dumpValve, LOW);
    END++;
    return;
  }

//--------------------------------------------
float floatMap1(float x, float inMin, float inMax, float outMin, float outMax)
{
  float mtemp;
  mtemp = (x-inMin)*(outMax-outMin)/(inMax-inMin)+outMin;
  return mtemp;
}


//Pulse-------------------------------------------------------------------------------------------------------------
void pulsegraph()
 {
  
  while(1)
  {
    mySerial.print('s');
    mySerial.println(floatMap2(analogRead(A2),0,1023,0,5),2);
    delay(10);
    if(mySerial.available()>0)
    {
      if(mySerial.read() == 'Q')
      {
        xx = xx + 1;
        bpm[xx];
        delay(100);
        mySerial.print(' ');
        mySerial.print('E');
        mySerial.print(bpm[xx]);
        delay(100);
        return;
      }
     }
  }
 }

//--------------------------------------------
 float floatMap2(float x, float inMin, float inMax, float outMin, float outMax)
{
  float mtemp;
  mtemp = (x-inMin)*(outMax-outMin)/(inMax-inMin)+outMin;
  return mtemp;
}
//-------------------------------------------------------------------------------------------------------------------


