package com.mygdx.dsav;

import com.mygdx.dsav.Screens.MainMenuScreen;
import com.mygdx.dsav.Screens.ThemeScreen;
import com.mygdx.dsav.Screens.ArrayScreen;
import com.mygdx.dsav.Screens.GraphScreen;
import com.mygdx.dsav.Screens.LinkedListScreen;
import com.mygdx.dsav.Screens.QueueScreen;
import com.mygdx.dsav.Screens.StackScreen;
import com.mygdx.dsav.Screens.TreeScreen;
import com.mygdx.dsav.Screens.BubbleSortScreen;
import com.mygdx.dsav.Screens.BinarySearchScreen;
import com.mygdx.dsav.Screens.PrimsAlgScreen;
//import com.mygdx.dsav.Screens.DijkstrasAlgScreen;
import com.mygdx.dsav.Screens.DfsScreen;
import com.mygdx.dsav.Screens.BfsScreen;

import java.util.Hashtable;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;

public class DSAVisualiser extends Game {
	private Hashtable<String, FactOption> screens;
	private String oldFactSelector;
	private String factSelector;

	@Override
	public void create () {
		String localTheme;
		try {
			FileHandle fh = Gdx.files.local("data/colourLoad.txt");
			localTheme = fh.readString();
		} catch (Exception _) {
			localTheme = "classic";
		}

		if (localTheme.length() > 5 && 
			localTheme.substring(0, 6).equals("custom")) 
		{
			// ColorHandler.applyTheme("classic");
			String[] cols = localTheme.split("\\s+#");   // split on " #"

			float[] c;
			c = ColorHandler.hexToUnitInterval(cols[1]);
			BenHelper.DEFAULT_TEXT_COLOUR = new Color(c[0], c[1], c[2], 1);
			c = ColorHandler.hexToUnitInterval(cols[2]);
			BenHelper.BACKGROUND_COLOUR = new Color(c[0], c[1], c[2], 1);
			c = ColorHandler.hexToUnitInterval(cols[3]);
			BenHelper.HOVER_TEXT_COLOUR = new Color(c[0], c[1], c[2], 1);
			c = ColorHandler.hexToUnitInterval(cols[4]);
			BenHelper.HINT_TEXT_COLOUR = new Color(c[0], c[1], c[2], 1);
	
			ColorHandler.setCurrentThemeName("custom");
			ColorHandler.tryCustomTheme(cols[1], cols[2], cols[3], cols[4]);
		} else {
			ColorHandler.setCurrentThemeName(localTheme);
			ColorHandler.applyTheme(localTheme);
		}

		screens = new Hashtable<>();
		screens.put("menu", new MainMenuScreen());
		screens.put("theme", new ThemeScreen());
		screens.put("array", new ArrayScreen());
		screens.put("llist", new LinkedListScreen());
		screens.put("stack", new StackScreen());
		screens.put("queue", new QueueScreen());
		screens.put("graph", new GraphScreen());
		screens.put("tree", new TreeScreen());
		screens.put("bubble", new BubbleSortScreen());
		screens.put("binsrch", new BinarySearchScreen());
		screens.put("prims", new PrimsAlgScreen());
		//screens.put("dijkstras", new DijkstrasAlgScreen());
		screens.put("dfs", new DfsScreen());
		screens.put("bfs", new BfsScreen());

		oldFactSelector = "!";
		factSelector = "menu";
	}

	@Override
	public void render() {
		switch (factSelector) {
			case "menu": case "theme":
			case "array": case "llist": case "stack":
			case "queue": case "graph": case "tree":
			case "bubble": case "binsrch": case "prims":
			case "dijkstras": case "dfs": case "bfs":
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
				System.out.println("FACTSELECTOR SWITCH DEFAULTED");
				factSelector = "quit";
		}
	}

}
