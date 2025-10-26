import processing.core.*;

public class Monster extends PApplet {

    private int monsterID;
    private Tile atTile;
    private Boolean isDead;
    private int xPos, yPos;

    private int frameNumberI = 0;
    private int frameNumberD = 0;
    private int idleTotal = 4;
    private int deathTotal = 3;
    private int frameWidthI;
    private int frameWidthD;
    private int frameHeight;
    private int delayCounter = 0;

    private PImage idleSheet;
    private PImage deathSheet;

    //getters and setters
    public int getTileX(){return atTile.getxPos();}
    public void setTileX(int xPos) {this.xPos = xPos;}
    public int getTileY(){return atTile.getyPos();}
    public void setTileY(int yPos) {this.yPos = yPos;}
    public int getMonsterID() {
        return monsterID;
    }
    public void setMonsterID(int monsterID) {
        this.monsterID = monsterID;
    }
    public Boolean getIsDead() {
        return isDead;
    }
    public void setIsDead(Boolean isDead) {
        this.isDead = isDead;
    }
    public Tile getAtTile(){
        return atTile;
    }
    public void setAtTile(Tile tile){this.atTile=tile;}
    public void setxPos(int x) {xPos = x;}
    public int getxPos() {return xPos;}
    public void setyPos(int y) {yPos = y;}
    public int getyPos() {return yPos;}

    //constructor
    public Monster(int monsterID) {
        this.monsterID = monsterID;
        atTile = null;
        isDead = false;

    }//end Constructor

    // PApplet Functions
    public void draw(PApplet p) {

        int yOffset = 0;

        int frameDelay = 10;
        if (!isDead) {
            int xOffset = (frameNumberI) * frameWidthI;
            p.image(idleSheet.get(xOffset, yOffset, frameWidthI, frameHeight), xPos+25, yPos-25);
            delayCounter++;
            if (delayCounter >= frameDelay) {
                frameNumberI = (frameNumberI + 1) % idleTotal;
                delayCounter = 0;
            }
        }
        else{

            int xOffset = (frameNumberD) * frameWidthD;
            p.image(deathSheet.get(xOffset, yOffset, frameWidthD, frameHeight), xPos+20, yPos-25);
            delayCounter++;
            if (frameNumberD < deathTotal) {
                delayCounter++;
                if (delayCounter >= frameDelay) {
                    frameNumberD++;
                    delayCounter = 0;
                }
            }
        }
    }

    public void loadMedia(PApplet p){
        idleSheet = p.loadImage("Media"+ DungeonTilesUI.fileSeparator + "slimeIdle.png");
        idleSheet.resize(idleSheet.width+20,idleSheet.height+20 );

        deathSheet = p.loadImage("Media"+ DungeonTilesUI.fileSeparator + "slimeDeath.png");
        deathSheet.resize(deathSheet.width+20,deathSheet.height+20 );

        frameWidthI = idleSheet.width /(idleTotal);
        frameWidthD = deathSheet.width /(deathTotal);
        frameHeight = idleSheet.height;
    }

    //Game functions
    public String toString() {
        return "Monster ID: " + monsterID + " is at: " + xPos + " " + yPos;
    }

    public void runTests(){
        assert monsterID >=0;
        assert atTile != null;
        assert !isDead;
    }
}
