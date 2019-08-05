package blud.game.level.unit.units;

import blud.game.level.unit.Wall;
import blud.game.sprite.sprites.Sprites;

public class StoneWall extends Wall{

	public StoneWall() {
		super();
		sprites.add(Sprites.get("StoneWall"));
	}
	
	@Override
	public void onRender(RenderContext context) {
	}

	@Override
	public void onUpdate(UpdateContext context) {
	}

}
