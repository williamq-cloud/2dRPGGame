# 2D RPG Development Roadmap

## Phase 1: Core Rendering + Input
- [ ] Set up basic `GamePanel` with fixed size
- [ ] Implement `drawAtWorldCoords` method for coordinate translation
- [ ] Add simple player rectangle rendering
- [ ] Implement keyboard input for player movement (WASD/arrow keys)
- [ ] Implement camera follow logic to center player
- [ ] Clamp player movement within world bounds

## Phase 2: Tilemap & World
- [ ] Create tile-based map system (2D array of tile IDs)
- [ ] Render visible tiles based on camera viewport
- [ ] Differentiate tile types by color or images
- [ ] Add basic collision detection for walls/obstacles

## Phase 3: Player & Entities
- [ ] Replace player placeholder with animated sprite
- [ ] Create `Entity` class for NPCs, monsters, objects
- [ ] Manage and render a list of entities with position updates
- [ ] Implement simple interactions (talk, pick up, attack)

## Phase 4: UI & HUD
- [ ] Create HUD overlay (health, mana, XP bars, hotbar)
- [ ] Implement inventory panel with toggle open/close
- [ ] Add dialogue box panel for NPC conversations
- [ ] Display quest objectives on screen

## Phase 5: Game Mechanics
- [ ] Implement player stats, leveling, and experience system
- [ ] Add combat system with attacks, damage, enemy AI
- [ ] Add item pickups and inventory management
- [ ] Implement save/load game state functionality

## Phase 6: Polish & Expand
- [ ] Add sound effects and background music
- [ ] Implement parallax background layers for depth
- [ ] Add quest system with branching dialogues and NPC interactions
- [ ] Optimize rendering and input handling for performance
- [ ] Bug fixing and general polishing
