package com.mygdx.dsav;

import com.mygdx.dsav.Screens.MainMenuScreen;
import com.mygdx.dsav.Screens.ArrayScreen;
import com.mygdx.dsav.Screens.GraphScreen;
import com.mygdx.dsav.Screens.LinkedListScreen;
import com.mygdx.dsav.Screens.QueueScreen;
import com.mygdx.dsav.Screens.StackScreen;

import java.util.Hashtable;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class DSAVisualiser extends Game {
	private Hashtable<String, FactOption> screens;
	private String oldFactSelector;
	private String factSelector;

	@Override
	public void create () {

		screens = new Hashtable<>();
		screens.put("menu", new MainMenuScreen());
		screens.put("array", new ArrayScreen());
		screens.put("llist", new LinkedListScreen());
		screens.put("stack", new StackScreen());
		screens.put("queue", new QueueScreen());
		screens.put("graph", new GraphScreen());

		oldFactSelector = "!";
		factSelector = "menu";
	}

	@Override
	public void render() {
		switch (factSelector) {
			case "menu":
			case "array":
			case "llist":
			case "stack":
			case "queue":
			case "graph":
				if ( factSelector != oldFactSelector ) {
					screens.get(factSelector).reset();
					oldFactSelector = factSelector;
				}
				factSelector = screens.get(factSelector).frame(factSelector);
				break;

			case "quit":
				Gdx.app.exit();
				break;

			case "!":
				factSelector = "menu";
				screens.get(factSelector).reset();
				break;

			default:
				System.out.println("factSelector switch defaulted");
				factSelector = "quit";
		}
	}

}
