package blud;

import blud.core.Engine;
import blud.game.menu.menus.Menus;
import blud.game.sound.sounds.Sounds;
import blud.game.sprite.sprites.Sprites;
import blud.util.Version;

public class Blud {
	public static final Version
		VERSION = new Version("Blud", 0, 0, 8);
	
	public static void main(String[] args) {
		System.out.println(VERSION);		
		
		Sprites.load();
		//Sounds.load();
		Sounds.load("Track1");
		Engine.setScene(Menus.SPLASH);
		Engine.init();
		//Sounds.get("Track1").loop();
	}
}
