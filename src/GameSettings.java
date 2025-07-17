public class GameSettings {
    
    // ==== Tile Settings ====
    public static final int RAW_TILE_SIZE = 16;
    public static final int SCALE = 3;
    public static final int TILE_SIZE = RAW_TILE_SIZE * SCALE;

    // ==== Screen Settings ====
    public static final int SCREEN_ROWS = 16;
    public static final int SCREEN_COLS = 12;
    public static final int SCREEN_WIDTH = SCREEN_ROWS * TILE_SIZE;
    public static final int SCREEN_HEIGHT = SCREEN_COLS * TILE_SIZE;

    // ==== World Settings ====
    public static final int WORLD_ROWS = 100;
    public static final int WORLD_COLS = 100;
    public static final int WORLD_WIDTH = WORLD_ROWS * TILE_SIZE;
    public static final int WORLD_HEIGHT = WORLD_COLS * TILE_SIZE;

    private GameSettings() {}

}
