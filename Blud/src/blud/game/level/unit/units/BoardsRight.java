package blud.game.level.unit.units;

import blud.game.level.unit.Unit;
import blud.game.sprite.sprites.Sprites;

public class BoardsRight extends Unit{

	public BoardsRight() {
		super();
		sprites.add(Sprites.get("BoardsRight"));
		this.curHP = 1;
		this.maxHP = 1;
		this.defendFrames = 12;
		this.blocksPlayerVision = true;
		this.blocksEntityVision = true;
	}
	
	@Override
	public void onRender1(RenderContext context) {
		if(node != null)
			sprites.get().frame = (node.local.x() + node.local.y()) & 1;		
	}
}
