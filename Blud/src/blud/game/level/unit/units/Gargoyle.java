package blud.game.level.unit.units;

import blud.game.Game;
import blud.game.level.node.Node;
import blud.game.level.unit.Undead;
import blud.game.sprite.sprites.Sprites;

public class Gargoyle extends Undead{

	public Gargoyle() {
		super();
		
		this.sprites.add(
				Sprites.get("GargoyleIdle"),
				Sprites.get("GargoyleAggroUp"),
				Sprites.get("GargoyleAggroDn"),
				Sprites.get("GargoyleWalkUp"),
				Sprites.get("GargoyleWalkDn"),
				Sprites.get("GargoyleIdleAggro")
				);
		this.sprites.set(0);
		
		this.moveFrames   = 30;
		this.attackFrames = 30;
		this.defendFrames = 30;
		
		this.alertFrames = 30;
		this.relaxFrames = 90;
		
		this.aoeDetection = true;
		this.detectThroughUnits = true;
		
		this.moveCooldown = 30;
		this.attackCooldown = 30;
		this.defendCooldown =  0;
		
		this.maxHP = 2;
		this.curHP = 2;
		
		this.lightLevel = 0;
		this.lightRange = 0;
		
		this.detectionRange    = 8;
		
		this.damage   = 2;
		this.priority = 3;
		
		drawFacing = false;
	}
	
	public void onAlert() {
		drawFacing = true;
	}
	
	
	@Override
	public void whileRelaxed() {
		this.sprites.set(0);
	}
	
	@Override
	public void whileAlerted() {
		if(this.target.curHP > 0) {
			//makes the enemy look toward the player
			if(this.node.local.y() == this.target.node.local.y()) {
				if(this.node.local.x() < this.target.node.local.x()) {
					this.facing = Game.EAST;
					this.sprites.flop();
					sprites.loop(4,2f);
				}else {
					this.facing = Game.WEST;
					this.sprites.flip();
					sprites.loop(3,2f);
				}
			}else if(this.node.local.x() == this.target.node.local.x()) {
				if(this.node.local.y() < this.target.node.local.y()) {
					this.facing = Game.NORTH;
					this.sprites.flip();
					sprites.loop(4,2f);
				}else {
					this.facing = Game.SOUTH;
					this.sprites.flop();
					sprites.loop(3, 2f);
				}
			}else if(this.node.local.x() < this.target.node.local.x()) {
				this.facing = Game.EAST;
				this.sprites.flop();
				sprites.loop(4,2f);
			}else if(this.node.local.x() > this.target.node.local.x()) {
				this.facing = Game.WEST;
				this.sprites.flip();
				sprites.loop(3,2f);
			}

			if(!move(facing)) {
				if(node.neighbor[facing] != null && !(node.neighbor[facing].unit instanceof Necro || node.neighbor[facing].unit instanceof FireGhost))
				    engage(facing);
			}
		}
	}
	
	@Override
	public void onTurn() {
		this.node.level.updateLighting = true;
		this.node.level.updateEntityVision = true;
		this.node.level.updatePlayerVision = true;
	}
	
	@Override 
	public void onMove(Node node) {
		switch(this.facing) {
			case Game.EAST:
				this.sprites.flop();
				sprites.loop(4,2f);
				break;
			case Game.NORTH:
				this.sprites.flip();
				sprites.loop(4,2f);
				break;
			case Game.WEST:
				this.sprites.flip();
				sprites.loop(3,2f);
				break;
			case Game.SOUTH:
				this.sprites.flop();
				sprites.loop(3, 2f);
				break;
		}
	}
	
	@Override
	public void onIdle() {
	}
	
}
