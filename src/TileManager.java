import java.awt.Color;
import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Map;

public class TileManager {

    private Map<TileType, Color> tileImages;

    int TILE_SIZE = GameSettings.TILE_SIZE;

    public TileManager() {
        tileImages = new HashMap<>();
        tileImages.put(TileType.DIRT, new Color(161, 130, 99));
        tileImages.put(TileType.WATER, new Color(93, 152, 208));
        tileImages.put(TileType.GRASS, new Color(131, 201, 140));
        tileImages.put(TileType.WALL, new Color(191, 191, 191));
        tileImages.put(TileType.SAND, new Color(230, 220, 160));
        tileImages.put(TileType.FOREST, new Color(40, 120, 60));
        tileImages.put(TileType.MOUNTAIN, new Color(120, 120, 120));
    }

    public void draw(Graphics2D g, TileMap map, int cameraX, int cameraY, int screenWidth, int screenHeight) {
        int startCol = cameraX / TILE_SIZE;
        int startRow = cameraY / TILE_SIZE;
        int endCol = (cameraX + screenWidth) / TILE_SIZE + 1;
        int endRow = (cameraY + screenHeight) / TILE_SIZE + 1;

        for (int row = startRow; row <= endRow; row++) {
            for (int col = startCol; col <= endCol; col++) {
                TileType type = map.getTile(col, row);

                int screenX = col * TILE_SIZE - cameraX;
                int screenY = row * TILE_SIZE - cameraY;

                Color tileColor = tileImages.get(type);

                g.setColor(tileColor);
                g.fillRect(screenX, screenY, TILE_SIZE, TILE_SIZE);
            }
        }
    }

}
