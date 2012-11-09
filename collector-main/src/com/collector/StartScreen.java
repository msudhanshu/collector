package com.collector;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GLCommon;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class StartScreen implements Screen {

	//things needed to draw
	SpriteBatch batcher;
	Texture texture;
	Texture playbutton;
	Vector2 playbuttonposition;
	Pixmap pixmap;
	Sprite playbuttonsprite;
    private TextureRegion playbuttonregion;
	Vector2 smiley_pos;
	Vector2 playbuttonSize;
	
	
	public StartScreen () {
		
	}
	
	private void DrawSmiley()
	{
		Gdx.app.log("MyLibGDXGame", "Game.DrawSmiley()");
		//-------Going to draw a smiley face on the pixmap, which will show on the texture
		//draw a yellow circle for the smiley faces head
		pixmap.setColor(1, 1, 0, 1);
		pixmap.fillCircle(256/2, 256/2, 256/2);
 
		//first draw a black circle for the smile
		pixmap.setColor(0, 0, 0,1);
		pixmap.fillCircle(256/2, 140, 80);
 
		//then a yellow larger over it, to make it look like a partial circle/ a smile
		pixmap.setColor(1, 1, 0, 1);
		pixmap.fillCircle(256/2, 100, 100);
 
		//now draw the two eyes
		pixmap.setColor(0, 0, 0,1);
		pixmap.fillCircle(256/3, 100, 30);
		pixmap.fillCircle(256-256/3, 100, 30);
 
		texture.draw(pixmap, 0, 0);
		texture.bind();
	}
	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		Gdx.app.log("MyLibGDXGame", "Game.dispose()");
		//dispose any object you created to free up the memory
		texture.dispose();
		pixmap.dispose();
		batcher.dispose();
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float arg0) {
		// TODO Auto-generated method stub
		//need to get the deltaTime- the amount of time passed since the last frame in seconds. We'll want to move everything based on the time passed- so if it renders slower on the phone, it should still move at the same rate
	
		//Gdx.app.log("MyLibGDXGame", "StartScreen.render()");
		
		float deltaTime=Gdx.graphics.getDeltaTime();
 
		//clear the background to black each frame- otherwise we'll see our smiley face "ghosting" as it moves
		GLCommon gl = Gdx.gl;
		Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
		    Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT | GL10.GL_STENCIL_BUFFER_BIT);
 
		//start the batcher, so we would want to do all of our draw calls between batcher.begin and .end
		batcher.begin();

		batcher.draw(playbutton,playbuttonposition.x,playbuttonposition.y);
		//batcher.draw(texture, smiley_pos.x, smiley_pos.y);
		batcher.draw(playbuttonregion,playbuttonposition.x,playbuttonposition.y);
		playbuttonsprite.draw(batcher);
		
		batcher.end();
		
		//move it up to 4 px per frame
				if(200*deltaTime<4)
					smiley_pos.x+=200*deltaTime;
				//otherwise, we'll only want to move him 4pixels every frame
				else
					smiley_pos.x+=4;
		 
				//if the left side of the smiley face is off the left side of the screen, move it to -smiley width- which puts the right side of the smiley face just off the left side of the screen, so it looks like it wraps around
				if(smiley_pos.x> Config.SCREEN_WIDTH)
					smiley_pos.x=-256;
	}

	@Override
	public void resize(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		Gdx.app.log("MyLibGDXGame", "StartScreen.Show()");
		
		playbuttonposition = new Vector2(Config.SCREEN_WIDTH/2, Config.SCREEN_HEIGHT/2);
	//	playbuttonSize = new Vector2(Config.SCREEN_WIDTH/2, Config.SCREEN_HEIGHT/2);
		playbuttonSize = new Vector2(10, 10);
		FileHandle imageFileHandle = Gdx.files.internal(Config.START_PLAY_BUTTON); 
		Gdx.app.log("StartScreen", "Got handle"+imageFileHandle);
		playbutton = new Texture(imageFileHandle);
		playbuttonregion = new TextureRegion(playbutton, (int) playbuttonposition.x, (int)playbuttonposition.y, (int)playbuttonSize.x, (int)playbuttonSize.y);
		playbuttonsprite = new Sprite(playbutton, (int) playbuttonposition.x, (int)playbuttonposition.y, (int)playbuttonSize.x, (int)playbuttonSize.y);
		playbuttonsprite.rotate(40);
		playbutton.bind();
		
		//we'll get whatever the set width is- 800x480 above, but will be the device resolution when running the android version
		Config.SCREEN_WIDTH= Gdx.graphics.getWidth();
		Config.SCREEN_HEIGHT= Gdx.graphics.getHeight();
 
		//setup these 3 for rendering- sprite batch will render out textures, and pixmaps allow you to draw on them
		batcher = new SpriteBatch();
		pixmap = new Pixmap(256, 256, Pixmap.Format.RGBA8888);
		texture = new Texture(pixmap);
 
		//setup where we want out smiley face vector graphic to start at
		smiley_pos = new Vector2(Config.SCREEN_WIDTH/2-pixmap.getWidth()/2, Config.SCREEN_HEIGHT/2-pixmap.getHeight()/2);

		DrawSmiley();
	}

}
