/*
 * Korpong Sawataksornchuen 5831004821
 * Natt Ruangkriengsin 		5831016321 
 */
package model;

import java.util.ArrayList;
import java.util.List;

public class ThreadHolder {
	public static final ThreadHolder instance = new ThreadHolder();

	private List<Thread> threads = new ArrayList<Thread>();

	public List<Thread> getThreads() {
		return threads;
	}
}
