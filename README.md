# Tower Defence Game

## Overview
This repository contains the implementation of a Tower Defence game written in Java. The game involves strategic placement of towers to defend against waves of enemies traversing a predefined path. The player earns money by defeating enemies, which can be used to buy or upgrade towers. 🎮🛡️💰

## Features 🕹️
- Multiple types of enemies with different attributes.
- Various tower types, including standard and rapid-fire towers.
- A GUI with buttons for tower placement and game control.
- Upgradable Towers and scalable enemies.
- Health and money systems for the player.
- Wave-based enemy spawning system.
- Pause functionality.

## Key Classes 🎯

### `TowerDefenceGame`
The main class that initializes and manages the game. It handles:
- Rendering the game screen.
- Managing game updates, including enemy waves and tower interactions.
- Tracking player resources (health and money).

### `enemies`
Defines the enemy types, their attributes, and behavior:
- `BossEnemy`: Strong, high-health.
- `FastEnemy`: Fast-moving.
- `StandardEnemy`: Default enemy type.
- `TankEnemy`: Very-High-health, slow-moving. 
- `AntiFreezeEnemy`: Medium-health, immune to the freeze Effect. 
- `DistractionEnemy`: High-health, takes all the fire on itself. 
- `RegenEnemy`: High-health, Regenerates Health. 
- `SuperBossEnemy`: Strong, end boss. 

### `towers`
Defines the types of towers available:
- `StandardTower`: Basic attack tower.
- `RapidFireTower`: Fast attack tower.
- `FreezeTower`: Inflicts the status effect Freeze.
- `ShotgunTower`: Shoots 5 Projectiles at once.
- `SniperTower`: Enormous Range.
- `LaserTower`: Shots a Laser with infinite Pierce.

### `projectiles`
Handles the creation and behavior of projectiles fired by towers:
- `Projectile`: Represents a single projectile.
- `RayProjectile`: A Laser Projectile, that stays on the map for a while.

---
Happy Defending! 🎯🛡️👾

