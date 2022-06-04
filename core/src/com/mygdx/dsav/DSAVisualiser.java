package com.mygdx.dsav;

import com.mygdx.dsav.Screens.MainMenuScreen;

import java.util.Hashtable;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class DSAVisualiser extends Game {
	private Hashtable<Character, FactOption> screens;
	private char oldFactSelector;
	private char factSelector;
	/////
	// 'm' = menu
	// 'g' = game
	//

	@Override
	public void create () {

		screens = new Hashtable<>();
		screens.put('m', new MainMenuScreen());

		oldFactSelector = '!';
		factSelector = 'm';
	}

	@Override
	public void dispose () {}

	@Override
	public void render() {
		switch (factSelector) {
			case 'm':
				if ( factSelector != oldFactSelector ) {
					screens.get(factSelector).reset();
					oldFactSelector = factSelector;
				}
				factSelector = screens.get(factSelector).frame(factSelector);
				break;

			case 'q':
				Gdx.app.exit();
				break;

			case '!':
				factSelector = 'm';
				screens.get(factSelector).reset();
				break;

			default:
				System.out.println("factSelector switch defaulted");
				factSelector = 'q';
		}
	}

}
