package blud.game.sprite;

import java.awt.AlphaComposite;
import java.awt.image.BufferedImage;

import blud.core.Renderable;
import blud.core.Updateable;
import blud.util.Copyable;

public class Sprite implements Renderable, Updateable, Copyable<Sprite> {
	private static final int
		STOP = 0,
		PLAY = 1,
		LOOP = 2;
	protected BufferedImage[]
		sprite_frames,
		shadow_frames;
	public String
		name;
	public float
		frame,
		speed,
		sprite_transparency,
		shadow_transparency = 1f;
	protected int
		mode;
	
	public Sprite(Sprite sprite) {
		this(
				sprite.name,
				sprite.sprite_frames,
				sprite.shadow_frames
				);
	}
	
	public Sprite(
			String name,
			BufferedImage[] sprite_frames,
			BufferedImage[] shadow_frames
			) {
		this.name = name;
		this.sprite_frames = sprite_frames;
		this.shadow_frames = shadow_frames;
	}
	
	public BufferedImage getSpriteFrame() {
		return sprite_frames[(int)frame];
	}
	
	public BufferedImage getShadowFrame() {
		return shadow_frames[(int)frame];
	}
	
	public void nextFrame() {
		frame ++;
		if(frame >= sprite_frames.length)
			frame = 0f;
	}
	
	public void prevFrame() {
		frame --;
		if(frame < 0)
			frame = sprite_frames.length - 1;
	}
	
	public void setFrame(float frame) {
		this.frame = frame;
	}
	
	public void setSpeed(float speed) {
		this.speed = speed;
	}
	
	public void setTransparency(float transparency) {
		this.sprite_transparency = transparency;
		this.shadow_transparency = transparency;
	}
	
	public void setSpriteTransparency(float transparency) {
		this.sprite_transparency = transparency;
	}
	
	public void setShadowTransparency(float transparency) {
		this.shadow_transparency = transparency;
	}
	
	public void play(float speed) {
		this.setSpeed(speed);
		this.setFrame(0f);
		this.play();
	}
	
	public void play() {
		this.mode = PLAY;
	}
	
	public void loop(float speed) {
		this.setSpeed(speed);
		this.setFrame(0f);
		this.mode = LOOP;
	}
	
	public void loop() {
		this.mode = LOOP;
	}
	
	public void stop() {
		this.setFrame(0f);
		this.mode = STOP;
	}

	@Override
	public void render(RenderContext context) {
		context = context.push();
		context.g2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f - sprite_transparency));
		context.g2D.drawImage(sprite_frames[(int)frame], null, 0,  0);
		context.g2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f - shadow_transparency));
		context.g2D.drawImage(shadow_frames[(int)frame], null, 0,  0);
		context = context.pull();
	}
	
	@Override
	public void update(UpdateContext context) {
		if(mode > 0) {
			frame += speed * context.dt;
			switch(mode) {
				case PLAY: 
					if(frame <  0f           ) stop();
					if(frame >= sprite_frames.length) stop(); 
					break;
				case LOOP:
					if(frame <  0f           ) frame = sprite_frames.length - 1;
					if(frame >= sprite_frames.length) frame = 0f               ;
			}
		}		
	}	

	@Override
	public Sprite copy() {
		return new Sprite(this);
	}	
	
	public static class Group implements Renderable, Updateable {
		protected Sprite[]
			sprites;
		protected int
			sprite;
		
		public Group(Sprite... sprites) {
			this.sprites = sprites;
		}
		
		public Sprite getSprite() {
			return sprites[sprite];
		}
		
		public void nextFrame() {
			this.getSprite().nextFrame();
		}
		
		public void prevFrame() {
			this.getSprite().prevFrame();
		}
		
		public void setFrame(float frame) {
			this.getSprite().setFrame(frame);
		}
		
		public void setSpeed(float speed) {
			this.getSprite().setSpeed(speed);
		}
		
		public void setTransparency(float transparency) {
			for(int i = 0; i < sprites.length; i ++)
				sprites[i].setTransparency(transparency);
		}
		
		public void setSpriteTransparency(float transparency) {
			for(int i = 0; i < sprites.length; i ++)
				sprites[i].setSpriteTransparency(transparency);
		}
		
		public void setShadowTransparency(float transparency) {
			for(int i = 0; i < sprites.length; i ++)
				sprites[i].setShadowTransparency(transparency);
		}
		
		public void play() {
			this.getSprite().play();
		}
		
		public void loop() {
			this.getSprite().loop();
		}
		
		public void stop() {
			this.getSprite().stop();
		}
		
		public Sprite nextSprite() {
			sprite ++;
			if(sprite >= sprites.length)
				sprite = 0;
			return sprites[sprite];
		}
		
		public Sprite prevSprite() {
			sprite --;
			if(sprite < 0)
				sprite = sprites.length - 1;
			return sprites[sprite];
		}
		
		public Sprite setSprite(int sprite) {
			return sprites[this.sprite = sprite];
		}
		
		public void play(int sprite, float speed) {
			this.setSprite(sprite).play(speed);
		}
		
		public void loop(int sprite, float speed) {
			this.setSprite(sprite).loop(speed);
		}
		
		public void stop(int sprite) {
			this.setSprite(sprite).stop();
		}

		@Override
		public void render(RenderContext context) {
			sprites[sprite].render(context);
		}

		@Override
		public void update(UpdateContext context) {
			sprites[sprite].update(context);
		}
	}	
}
