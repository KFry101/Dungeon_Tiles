import processing.core.*;
import processing.sound.*;

import java.util.ArrayList;

public class Player extends PApplet {

    private Tile atTile;
    private int xPos;
    private int yPos;
    private Boolean dead, attacking;

    private int frameNumber = 0;
    private int idleTotal = 5;
    private int attackTotal = 4;
    private int frameWidth;
    private int frameHeight;
    private int delayCounter = 0;

    private PImage idleSheet;
    private PImage attackSheet;
    private SoundFile attackSound;
    private SoundFile deathSound;
    private SoundFile winSound;

    //Getters and Setters///////////////////////////////////
    public Tile getAtTile(){
        return atTile;
    }
    public void setAtTile(Tile tile){
        this.atTile=tile;
    }
    public int getxPos() {return xPos;}
    public void setxPos(int xPos) {this.xPos = xPos;}
    public int getyPos() {return yPos;}
    public void setyPos(int yPos) {this.yPos = yPos;}
    public Boolean isDead() {return dead;}
    public void setDead(Boolean dead) {this.dead = dead;}
    public Boolean isAttacking() {return attacking;}
    public void setAttacking(Boolean attacking) {this.attacking = attacking;}

    public Player(Tile t, int x, int y) {
        dead = false;
        attacking = false;
        atTile = t;
        xPos = x;
        yPos = y;
    }//end of constructor

    // PApplet Functions ////////////////////////////////
    public void loadMedia(PApplet p) {
        idleSheet = p.loadImage("Media"+ DungeonTilesUI.fileSeparator + "playerIdle.png");
        idleSheet.resize(idleSheet.width+20, idleSheet.height+20);
        frameWidth = idleSheet.width /(idleTotal);
        frameHeight = idleSheet.height;

        attackSheet = p.loadImage("Media"+ DungeonTilesUI.fileSeparator+ "playerAttack.png");
        attackSheet.resize(attackSheet.width+20, attackSheet.height+20);

        attackSound = new SoundFile(p, "Media"+ DungeonTilesUI.fileSeparator +"soundAttack.mp3");
        deathSound = new SoundFile(p, "Media"+ DungeonTilesUI.fileSeparator +"soundLose.mp3");
        winSound = new SoundFile(p, "Media"+ DungeonTilesUI.fileSeparator +"soundWin.mp3");

    }

    public void draw(PApplet p) {
        int xOffset = (frameNumber) * frameWidth;
        int yOffset = 0;

        delayCounter++;
        int frameDelay = 7;
        if (delayCounter >= frameDelay) {
            frameNumber = (frameNumber + 1) % idleTotal;
            delayCounter = 0;
        }

        if (attacking) {
            if (frameNumber < attackTotal) {
                p.image(attackSheet.get(xOffset, yOffset, frameWidth, frameHeight), xPos, yPos);
            } else {
                // Reset to idle state
                attacking = false;
                frameNumber = 0;
            }
        } else {
            if (frameNumber >= idleTotal) {
                frameNumber = 0;
            }
            p.image(idleSheet.get(xOffset, yOffset, frameWidth, frameHeight), xPos, yPos);
        }
    }

    public void keyPressed(char key, PlayingField pf, ArrayList<Monster> mons){
        if(key=='F'||key=='f'){
            atTile= atTile.getNextTile();
            setxPos(atTile.getxPos()+25);
            setyPos(atTile.getyPos()-25);
            if(atTile.isMonsterHere()){
                dead();
            }
            if(atTile == pf.getEndTile()){
                win();
            }
        }
        else if(key=='B'||key=='b'){
            atTile= atTile.getPreviousTile();
            setxPos(atTile.getxPos()+25);
            setyPos(atTile.getyPos()-25);
        }
        else if (key == 'A' || key == 'a') {
            attack(mons);
            if (!attacking) {
                attacking = true;
                frameNumber = 0;  // start attack animation from beginning
            }
        }

    }

    //Game functions////////////////////////////////////
    public int getTileX(){
        return atTile.getxPos();
    }
    public int getTileY(){
        return atTile.getyPos();
    }

    public boolean isMonsterNear() {
        return atTile.isMonsterNear();
    }

    public void attack(ArrayList<Monster> monsters) {
        attackSound.play();
        if(isMonsterNear()){
            Tile checkTile = atTile.getNextTile();
            for(Monster m : monsters){
                if(m.getAtTile()==checkTile){
                    m.setIsDead(true);

                    m.getAtTile().setMonsterHere(false);
                }
            }
        }
    }
    public void dead(){
        deathSound.play();
        dead = true;
    }
    public void win(){
        winSound.play();
    }

    //Development methods////////////////////////////////
    public String toString() {
        return "Player\n";
    }

    public void runTests() {
        assert atTile != null;
    }
}
