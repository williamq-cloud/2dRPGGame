public enum TileType {
    
    WALL(true),
    WATER(false),
    DIRT(true), 
    GRASS(true), 
    SAND(true), 
    FOREST(true), 
    MOUNTAIN(true);

    public final boolean isSolid;
    
    TileType(boolean isSolid) {
        this.isSolid = isSolid;
    }

}
