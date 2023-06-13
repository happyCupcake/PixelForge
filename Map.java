import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class Map {
    CoinCollectingGame ccg;
    public Tile[] tile;
    public int mapTileNum[][];

    public Map(CoinCollectingGame ccg){
        this.ccg = ccg;
        tile = new Tile[10];
        mapTileNum = new int[ccg.maxWorldCol][ccg.maxWorldRow];

        getTileImage();
        loadMap();
    }

    public void getTileImage() {

        try{
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("res/grass.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("res/wall.png"));
            tile[1].collision = true;

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("res/water.png"));
            tile[2].collision = true;

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("res/path.png"));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("res/tree.png"));
            tile[4].collision = true;

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("res/path.png"));
        }catch(IOException e){
           
            e.printStackTrace();
        }
    }

    public void loadMap(){
        try{
            InputStream is = getClass().getResourceAsStream("maps/map1.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int row = 0;
            int col = 0;

            while(col< ccg.maxWorldCol && row<ccg.maxWorldRow){
                String line = br.readLine();

                while(col < ccg.maxWorldCol){
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                    //System.out.print(" "+num+ " ");
                }

                if(col == ccg.maxWorldCol){
                    col = 0;
                    row ++;
                }
                //System.out.println();
            }
            br.close();

        }catch(Exception e){

        }
    }

    public void draw(Graphics2D g2){

        int worldCol =0;
        int worldRow =0;


        while(worldCol < ccg.maxWorldCol && worldRow < ccg.maxWorldRow){

            int tileNum = mapTileNum[worldCol][worldRow];
            //System.out.println(tileNum);

            int worldX = worldCol * ccg.tileSize;
            int worldY = worldRow * ccg.tileSize;

            int screenX = worldX - ccg.playerWorldX + ccg.playerX;
            int screenY = worldY - ccg.playerWorldY + ccg.playerY;

            g2.drawImage(tile[tileNum].image, screenX, screenY, ccg.tileSize, ccg.tileSize, null);
            worldCol++;
            if(worldCol == ccg.maxWorldCol){
                worldCol = 0;
                worldRow++;
            }
        }

        //TESTING WHY ONLY ONE TILE KEPT REPEATING
        /*for(int i=0;i<16;i++){
            for(int j=0;j<12;j++){
                System.out.println(mapTileNum[i][j]);
            }
        }*/

    }
}
