import java.util.ArrayList;
import processing.core.*;

public class PlayingField extends PApplet {

    private int xStart, yStart;
    private int tileCount;
    private ArrayList<Tile> tiles = new ArrayList<>();
    private PImage fieldImage;

    public int getTileCount() {
        return tileCount;
    }
    public void setTileCount(int tileCount) {
        this.tileCount = tileCount;
    }
    public int getxStart() {return xStart;}
    public void setxStart(int xStart) {this.xStart = xStart;}
    public int getyStart() {return yStart;}
    public void setyStart(int yStart) {this.yStart = yStart;}
    public ArrayList<Tile> getTiles() {
        return tiles;
    }

    public PlayingField(int tileCount, int xStart, int yStart) {
        this.tileCount = tileCount;
        this.xStart = xStart;
        this.yStart = yStart;
        createLinkedTiles(tileCount, xStart, yStart);
    }

    public Tile getStartTile(){
        return tiles.getFirst();
    }
    public int getStartTileX(){return getStartTile().getxPos();}
    public int getStartTileY(){return getStartTile().getyPos();}
    public Tile getEndTile(){return tiles.getLast();}
    public Tile findTile(int n){
        return tiles.get(n);
    }

    //PApplet Functions
    public void draw(PApplet p) {
        p.image(fieldImage, 0, 0);
    }

    public void loadMedia(PApplet p) {
        fieldImage = p.loadImage("Media"+ DungeonTilesUI.fileSeparator + "Background.png");
        fieldImage.resize(p.width, p.height);
    }

    // GAME FUNCTIONS
    public void createLinkedTiles(int tileCount, int x, int newY) {
        int xStart=x, y=newY;
        for (int i = 0; i <= tileCount; i++) {
            if (i < 6) {
                xStart += 65;
                tiles.add(new Tile(i + 1, xStart, y));
            } else if (i < 8) {
                y += 50;
                tiles.add(new Tile(i + 1, xStart, y));
            } else if (i < 12) {
                xStart -= 65;
                tiles.add(new Tile(i + 1, xStart, y));
            } else if (i < 14) {
                y += 50;
                tiles.add(new Tile(i + 1, xStart, y));
            } else {
                xStart += 70;
                tiles.add(new Tile(i + 1, xStart, y));
            }
        }

        for (int i = 0; i < tiles.size() -1; i++){
            tiles.get(i).setNextTile(tiles.get(i+1));
        }
        getEndTile().setNextTile(getEndTile());
        for (int i = 1; i < tiles.size(); i++){
            tiles.get(i).setPreviousTile(tiles.get(i-1));
        }
        getStartTile().setPreviousTile(getStartTile());
    }

    public String toString() {
        StringBuilder s= new StringBuilder();
        for (Tile tile : tiles) {
            s.append(tile.toString());
        }
        return s.toString();
    }

    public void runTests(){
        assert tiles.size() == tileCount; //(ultimately is just ==17)
        assert tiles.getFirst() != null;
        assert tiles.getLast() != null;

        for( Tile t: tiles){
            t.runTests();
        }
    }


}
