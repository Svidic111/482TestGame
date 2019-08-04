package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

public class FieldCell {
    private boolean isEmpty;
    private  int x;
    private  int y;
    private Texture texture;
    private Plant plant;


    public FieldCell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public void setEmpty(boolean empty) {
        isEmpty = empty;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public Plant getPlant() {
        return plant;
    }

    public void setPlant(Plant plant) {
        this.plant = plant;
    }
}
