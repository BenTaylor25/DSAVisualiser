package com.mygdx.dsav;

import com.mygdx.dsav.Screens.MainMenuScreen;
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
		screens.put("stack", new StackScreen());
		screens.put("queue", new QueueScreen());

		oldFactSelector = "!";
		factSelector = "menu";
	}

	@Override
	public void render() {
		switch (factSelector) {
			case "menu":
			case "stack":
			case "queue":
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
