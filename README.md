# Tower Defence Game

## Overview
This repository contains the implementation of a Tower Defence game written in Java. The game involves strategic placement of towers to defend against waves of enemies traversing a predefined path. The player earns money by defeating enemies, which can be used to buy or upgrade towers. 🎮🛡️💰

## Features 🕹️
- Multiple types of enemies with different attributes.
- Various tower types, including standard and rapid-fire towers.
- A GUI with buttons for tower placement and game control.
- Health and money systems for the player.
- Wave-based enemy spawning system.
- Pause functionality.

## Project Structure 🌲
```
TowerDefenceGame
├── src/game
   ├── enemies
   │   ├── AntiFreezeEnemy.java
   │   ├── BossEnemy.java
   │   ├── DistractEnemy.java
   │   ├── Enemy.java
   │   ├── EnemyList.java
   │   ├── EnemyType.java
   │   ├── FastEnemy.java
   │   ├── RegenEnemy.java
   │   ├── StandardEnemy.java
   │   ├── SuperBoss100Enemy.java
   │   └── TankEnemy.java
   ├── framework
   │   ├── Drawable.java
   │   ├── Frame.java
   │   ├── MusicPlayer.java
   │   ├── PaintArea2d.java
   │   ├── Screen.java
   │   └── Vector.java
   ├── gui
   │   ├── BackgroundGrass.java
   │   ├── BackgroundVolcano.java
   │   ├── BackgroundWater.java
   │   ├── Button.java
   │   ├── GUI.java
   │   ├── MainMenu.java
   │   ├── MenuGUI.java
   │   └── TowerButton.java
   ├── path
   │   ├── GamePath.java
   │   └── PathSegment.java
   ├── projectiles
   │   ├── CollisionDetection.java
   │   ├── DamageType.java
   │   ├── Projectile.java
   │   ├── ProjectileList.java
   │   ├── RayProjectile.java
   │   └── StatusEffect.java
   ├── towers
   │   ├── FreezeTower.java
   │   ├── LaserTower.java
   │   ├── RapidFireTower.java
   │   ├── ShotgunTower.java
   │   ├── SniperTower.java
   │   ├── StandardTower.java
   │   ├── Tower.java
   │   ├── TowerList.java
   │   └── TowerType.java
   ├── wave
       ├── Wave.java
       └──WaveList.java
   ├── TowerDefenceGame.java
   ├── res
   │   ├── level1.wav
   │   ├── level2.wav
   │   └── level3.wav

.gitignore
README.md
```

## Key Classes 🎯

### `TowerDefenceGame`
The main class that initializes and manages the game. It handles:
- Rendering the game screen.
- Managing game updates, including enemy waves and tower interactions.
- Tracking player resources (health and money).

### `enemies`
Defines the enemy types, their attributes, and behavior:
- `BossEnemy.java`: Strong, high-health enemies.
- `FastEnemy.java`: Fast-moving enemies.
- `StandardEnemy.java`: Default enemy type.
- `TankEnemy.java`: High-health, slow-moving enemies. 

### `towers`
Defines the types of towers available:
- `StandardTower.java`: Basic attack tower.
- `RapidFireTower.java`: Fast attack tower. 

### `projectiles`
Handles the creation and behavior of projectiles fired by towers:
- `Projectile.java`: Represents a single projectile.
- `ProjectileList.java`: Manages all projectiles in the game. 

### `wave`
Manages the waves of enemies:
- `Wave.java`: Represents a single wave.
- `WaveList.java`: Stores and processes multiple waves. 

### `gui`
Provides graphical elements for user interaction:
- `Button.java`: Defines interactive buttons.
- `GUI.java`: Manages the overall graphical interface. 

### `framework`
Provides foundational classes and utilities:
- `Drawable.java`: Base interface for drawable objects.
- `Vector.java`: Handles 2D vector operations.
- `Frame.java`: Manages rendering frames.
- `PaintArea2d.java`: Provides 2D painting utilities. 


## Future Enhancements 📈
- Add more enemy and tower types.
- Implement a tower upgrade system.
- Introduce difficulty levels.
- Enhance graphics and animations.
- Add sound effects. 


---
Happy Defending! 🎯🛡️👾

