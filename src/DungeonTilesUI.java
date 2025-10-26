import processing.core.*;
import processing.sound.*;



public class DungeonTilesUI extends PApplet {

    private Game theGame = new Game();

    static final String fileSeparator = System.getProperty("file.separator");

    // PApplet Functions
    public void settings() {
        size(500, 470);
    }
    public void setup() {
        background(255);
        theGame.loadMedia(this);
    }
    public void draw() {
        background(255);
        //displayTextMenu(this);
        theGame.draw(this);
    }

    public void keyPressed() {
        theGame.keyPressed(this);

    }

    // Milestone 5a Function
    public void displayTextMenu(PApplet p) {
        String s= "Choose one:\n";
        s+= "A. Move Forward \n";
        s+= "B. Move Player Backwards\n";
        s+= "C. attack\n";
        s+= "D. Player dies to monster\n";
        s+= "E. Complete Dungeon\n";
        s+= "F. Play again\n";
        s+= "G. Try Again\n";
        s+= "H. Quit\n";
        //s+= "G. Monsters Move*\n"; //If I have time after implementing everything else, I will look at this.
        p.fill(0);
        p.textSize(10);
        p.text(s, 5, 5);

    }

    public static void main(String[] args) {
        PApplet.main("DungeonTilesUI");
    }
}
