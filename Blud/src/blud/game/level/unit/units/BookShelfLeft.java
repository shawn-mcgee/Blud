package blud.game.level.unit.units;

import blud.game.level.unit.Wall;
import blud.game.sprite.sprites.Sprites;

public class BookShelfLeft extends Wall{

	public BookShelfLeft() {
		super();
		sprites.add(Sprites.get("BookShelfLeft"));
	}
	
	@Override
	public void onRender2(RenderContext context) {
	}

	@Override
	public void onUpdate2(UpdateContext context) {
	}

}
