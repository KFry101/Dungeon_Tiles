import processing.core.*;

public class Tile extends PApplet {

    private int tileID, xPos, yPos;
    private Tile nextTile;
    private Tile previousTile;
    private boolean monsterHere;

    public Tile (int tileID, int x, int y) {
        this.tileID=tileID;
        this.xPos = x;
        this.yPos = y;
        monsterHere = false;
        nextTile = null;
        previousTile = null;
    }

    //GETTERS AND SETTERS
    public int getxPos() {return xPos;}
    public void setxPos(int xPos) {this.xPos = xPos;}

    public int getyPos() {return yPos;}
    public void setyPos(int yPos) {this.yPos = yPos;}

    public Tile getNextTile() {return nextTile;}
    public void setNextTile(Tile nextTile) {this.nextTile = nextTile;}

    public Tile getPreviousTile() {return previousTile;}
    public void setPreviousTile(Tile previousTile) {this.previousTile = previousTile;}

    public boolean isMonsterHere() {return monsterHere;}
    public void setMonsterHere(boolean monsterHere) {this.monsterHere = monsterHere;}

    //Game Functions
    public boolean isMonsterNear(){
        return nextTile.isMonsterHere();
    }


    //Developmental Methods
    public String toString() {
        String s = "";
        s = String.format("Tile" + tileID+ " at  " + xPos + ", "+ yPos + " Has a Monster:" + monsterHere+ "\n" );
        return s;
    }

    public void runTests(){
        assert xPos!=0;
        assert yPos !=0;
        assert tileID>0;
    }
}
