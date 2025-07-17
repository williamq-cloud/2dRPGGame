public class TileMap {
    public static final int WIDTH = 100;
    public static final int HEIGHT = 100;

    private TileType[][] map;

    public TileMap() {
        map = new TileType[HEIGHT][WIDTH];

        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                map[y][x] = TileType.WALL;
            }
        }

        NoiseGen noise = new NoiseGen(1234); // or any seed
        double scale = 0.07;

        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                double value = noise.noise(x * scale, y * scale);

                if (value < 0.3) map[y][x] = TileType.WATER;
                else if (value < 0.45) map[y][x] = TileType.SAND;
                else if (value < 0.7) map[y][x] = TileType.GRASS;
                else if (value < 0.85) map[y][x] = TileType.FOREST;
                else map[y][x] = TileType.MOUNTAIN;
            }
        }
        
        carveTrail(50, 50, 200);
        carveTrail(20, 80, 100);

        for (int y = 0; y < HEIGHT; y++) {
            map[y][WIDTH / 2] = TileType.WATER;
        }
    }

    public TileType getTile(int x, int y) {
        if (x < 0 || x >= WIDTH || y < 0 || y >= HEIGHT) {
            return TileType.WALL;
        }
        return map[y][x];
    }

    public void setTile(int x, int y, TileType type) {
        if (x >= 0 && x < WIDTH && y >= 0 && y < HEIGHT) {
            map[y][x] = type;
        }
    }

    public void carveTrail(int startX, int startY, int length) {
        int x = startX;
        int y = startY;

        for (int i = 0; i < length; i++) {
            if (x < 0 || x >= WIDTH || y < 0 || y >= HEIGHT) break;

            map[y][x] = TileType.DIRT;

            int dir = (int)(Math.random() * 4);
            switch (dir) {
                case 0:
                    y -= 1;
                    break;
                case 1:
                    y += 1;
                    break;
                case 2:
                    x -= 1;
                    break;
                case 3:
                    x += 1;
                    break;
            }
        }
    }
}