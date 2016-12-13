/*
 * Korpong Sawataksornchuen 5831004821
 * Natt Ruangkriengsin 		5831016321 
 */
package model;

import javafx.scene.canvas.GraphicsContext;

public interface IRenderable {
	public int getZ();

	public void draw(GraphicsContext gc);

	public boolean isDestroy();
}
