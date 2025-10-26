import processing.core.*;

public class CountDown extends PApplet {

    private int xPos, yPos;

    private int startTime;
    private int timer = startTime;
    private boolean started;
    private float programStart;

    //GETTERS AND SETTERS
    public int getxPos(){return xPos;}
    public void setxPos(int xPos){this.xPos = xPos;}
    public int getyPos(){return yPos;}
    public void setyPos(int yPos){this.yPos = yPos;}
    public int getStartTime(){return startTime;}
    public void setStartTime(int startTime){this.startTime = startTime;}
    public double getProgramStart(){return programStart;}
    public void setProgramStart(float time){this.programStart = time;}
    public int getTimer(){return timer;}
    public void setTimer(int timer){this.timer = timer;}
    public boolean isStarted(){return started;}
    public void setStarted(boolean timerStarted){this.started = timerStarted;}

    //Constructor
    public CountDown(int x, int y, int t) {
        xPos = x;
        yPos = y;
        startTime = t;
        started = false;
    }//end of constructor

    public void draw(PApplet p) {
        p.fill(60, 70, 100);
        p.stroke(40,50,80);
        p.strokeWeight(5);
        p.rectMode(PApplet.CENTER);
        p.rect(xPos, yPos-8, 50,36 );
        if (started) {
            int currTime = (int) (millis() - programStart) / 1000;
            timer = startTime - currTime;

            p.fill(255,0,0);
            p.textSize(20);
            p.textAlign(PApplet.CENTER );
            p.text(timer, xPos, yPos);
            p.textAlign(PApplet.CORNER);
            if (timer <= 0) {
                started = false;
            }
        }
    }

    public void reset() {
        setTimer(startTime);
        programStart = millis();
        setStarted(true);
    }
}
