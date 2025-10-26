import java.util.ArrayList;
import java.util.*;
import processing.core.*;

public class Game extends PApplet {

    //Game setup parameters
    private int monsterMax = 7;
    private int tileCount = 18;

    //Graphical/Animation Cutscene Flag
    private boolean gameStarted = false;    //instruction screen flag
    private boolean gameOver = false;       //Try again screen flag
    private boolean gameWon = false;        //Winning screen flag

    //Game Object Creation
    private CountDown counter = new CountDown(460, 150, 15);
    private PlayingField playingField = new PlayingField(tileCount, -50, 150);
    private ArrayList<Monster> monsters = new ArrayList<Monster>();
    private Player thePlayer;

    private PImage startScreen;
    private PImage deathScreen;
    private PImage winScreen;

    //private SoundFile music;


    //CONSTRUCTOR
    public Game() {
        createPlayer();
        createMonsters(monsterMax);
        setMonsterTiles();
        runTests();

    }//end of constuctor

    //PAPPLET METHODS
    public void loadMedia(PApplet p) {
        playingField.loadMedia(p);
        for (Monster m : monsters) {
            m.loadMedia(p);
        }
        thePlayer.loadMedia(p);

        startScreen = p.loadImage("Media"+ DungeonTilesUI.fileSeparator + "startScreen.png");
        startScreen.resize(p.width, p.height);

        deathScreen = p.loadImage("Media"+ DungeonTilesUI.fileSeparator +"deathScreen.png");
        deathScreen.resize(p.width, p.height);

        winScreen = p.loadImage("Media"+ DungeonTilesUI.fileSeparator +"winScreen.png");
        winScreen.resize(p.width, p.height);

    }

    public void draw(PApplet p) {
        if(!gameStarted){
            p.image(startScreen, 0, 0);
        }
        else {
            playingField.draw(p);
            thePlayer.draw(p);
            for (Monster monster : monsters) {
                monster.draw(p);
            }
            counter.draw(p);
            if (!counter.isStarted() && gameStarted && !gameWon) {
                thePlayer.setDead(true);
            }
            //Victory;
            if (thePlayer.getAtTile() == playingField.getEndTile()) {
                gameWon = true;
                p.imageMode(PApplet.CENTER);
                p.image(winScreen, p.width/2, p.height/2);
                p.imageMode(PApplet.CORNER);
            }
            //Player death; end of game
            if (thePlayer.isDead()) {
                gameOver = true;
                p.imageMode(PApplet.CENTER);
                p.image(deathScreen, p.width/2, p.height/2);
                p.imageMode(PApplet.CORNER);
            }
        }
    }

    public void keyPressed(PApplet p) {
        if(!gameStarted) {
            if(p.key == ' ') {
                counter.reset();
                gameStarted = true;
            }
        }
        else {
            if (p.key == 'F' || p.key == 'f') { //F
                thePlayer.keyPressed(p.key, playingField, monsters);
            }
            if (p.key == 'B' || p.key == 'b') { //B
                thePlayer.keyPressed(p.key, playingField, monsters);
            }
            if (p.key == 'A' || p.key == 'a') { //A
                thePlayer.keyPressed(p.key, playingField, monsters);
            }
            if (gameOver) {
                if (p.key == 'Q' || p.key == 'q') {
                    System.exit(0);
                }
                if (p.key == 'T' || p.key == 't') {
                    tryAgain();
                }
            }
            if (gameWon) {
                if (p.key == 'Q' || p.key == 'q') {
                    System.exit(0);
                }
                if (p.key == 'P' || p.key == 'p') {
                    playAgain();
                }
            }
        }
    }

    //GAME METHODS
    public void createPlayer() {
        thePlayer = new Player(playingField.getStartTile(), playingField.getStartTileX()+25, playingField.getStartTileY()-25);
    }

    public void createMonsters(int mm) {
        for (int i = 0 ; i < mm; i++) {
            monsters.add(new Monster(i+1));
        }
    }
    public void setMonsterTiles() {
        Random rand = new Random();
        ArrayList <Integer> nums = new ArrayList<>(0);
        while (nums.size() < monsterMax) {
            for(int i = 0; i < monsterMax;) {
                int num = rand.nextInt((tileCount - 2)) + 2;
                if (!nums.contains(num)) {
                    monsters.get(i).setAtTile(playingField.findTile(num));
                    monsters.get(i).setxPos(monsters.get(i).getTileX());
                    monsters.get(i).setyPos(monsters.get(i).getTileY());
                    nums.add(num);
                    i++;
                }
            }
        }
        for(Monster m : monsters) {
            for (Tile t : playingField.getTiles()) {
                if (m.getAtTile() == t) {
                    t.setMonsterHere(true);
                }
            }
        }
    }

    public void tryAgain() {
        gameOver = false;
        thePlayer.setDead(false);
        thePlayer.setAtTile(playingField.getStartTile());
        thePlayer.setxPos(thePlayer.getTileX()+25);
        thePlayer.setyPos(thePlayer.getTileY()-25);
        for(Monster m : monsters) {
            m.setIsDead(false);
            for (Tile t : playingField.getTiles()) {
                if (m.getAtTile() == t) {
                    t.setMonsterHere(true);
                }
            }
        }
        counter.reset();

    }
    public void playAgain() {
        gameWon = false;
        thePlayer.setAtTile(playingField.getStartTile());
        thePlayer.setxPos(thePlayer.getTileX()+25);
        thePlayer.setyPos(thePlayer.getTileY()-25);
        for (Monster m : monsters) {
            m.setIsDead(false);
            m.setAtTile(null);
        }
        for(Tile t : playingField.getTiles()) {
            t.setMonsterHere(false);
        }
        setMonsterTiles();
        counter.reset();
    }

    //DEVELOPMENT METHODS
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(thePlayer);
        for (Monster monster : monsters) {
            s.append(monster);
        }
        s.append(playingField);

        //s += textBox;
        return s.toString();
    }
    public void runTests() {
        assert playingField != null;
        //assert textBox != null;
        assert thePlayer != null;
        playingField.runTests();
        thePlayer.runTests();


    }
}

