package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureArray;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.util.HashMap;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	OrthographicCamera camera;
	Texture img;
	Texture boxImg;
	Array<FieldCell> cells;
	Array<Rectangle> boxes;
	HashMap<Integer, String> plants;
	long lastBoxAppearence;



	@Override
	public void create () {

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 400, 800);

		batch = new SpriteBatch();

		img = new Texture("background.png");
		boxImg = new Texture("closedBox.png");

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				cells.add(new FieldCell((i * 80), (j * 80)));
			}
		}

//		plants.put(1,"Fruits.apple.png");
//		plants.put(2,"Fruits.pear.png");
//		plants.put(3,"Fruits.banana.png");
//		plants.put(4, "Fruits.orange.png");
//		plants.put(5,"Fruits.kiwi.png");
//		plants.put(11,"Vegetable.tomato.png");
//		plants.put(12,"Vegetable.cucumber.png");
//		plants.put(13,"Vegetable.eggplant.png");
//		plants.put(14,"Vegetable.potato.png");
//		plants.put(15, "Vegetable.carrot.png");

		boxes = new Array<>();
		createBox();

	}



	private void createBox() {
		FieldCell cell = cells.get(MathUtils.random(0, 24));

		Rectangle box = new Rectangle();
		box.width = 80;
		box.height = 80;
		box.x = cell.getX();
		box.y = cell.getY();
		boxes.add(box);

		cell.setEmpty(false);
		lastBoxAppearence = TimeUtils.nanoTime();
	}



	private  void createPlant(Rectangle box) {
		Plant plant;
		boolean check = MathUtils.randomBoolean();
		if (check) {
			plant = new Fruit();
			plant.setRank(MathUtils.random(1, 5));
			plant.setTexture(plants.get(plant.getRank()));
		}
		else {
			plant = new Vegetable();
			plant.setRank(MathUtils.random(11, 15));
			plant.setTexture(plants.get(plant.getRank()));
		}
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camera.update();

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(img, 0, 0);
//		for (int i = 0 ; i < 25; i++) {
//			batch.draw(cells.get(i).getTexture(), cells.get(i).getX(), cells.get(i).getY());
//		}
		for (Rectangle box : boxes) {
			batch.draw(boxImg, box.x, box.y);
		}
		batch.end();

		if (TimeUtils.nanoTime() - lastBoxAppearence > 5000000000L) {
			createBox();
		}
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
