package uicomponents;

import main.*;
import javafx.scene.control.*;

public class MainMenu extends MenuBar{
	private static final String MENU_CSS_ID = "menuButton";
	private static final String SP_MENU_TITLE = "Solve Puzzle";
	private static final String SETTINGS_MENU_TITLE = "Settings";
	private MenuItem currentMenu;
	private Menu menu;
	
	public MainMenu(){
		//initialize class attributes
		currentMenu = new MenuItem(SETTINGS_MENU_TITLE);
		menu = new Menu("Menu");
		menu.getItems().addAll(currentMenu);
		getMenus().addAll(menu);


		//Style menu
		setStyle(COLORS.CSS_WHITE);
		menu.setId(MENU_CSS_ID);
	}
	
	public MenuItem getMenuItem(){
		return currentMenu;
	}

	public void update(){
		String currentMenuTitle = currentMenu.getText();
		if(currentMenuTitle.equals(SP_MENU_TITLE)){
			currentMenu.setText(SETTINGS_MENU_TITLE);
		}else{
			currentMenu.setText(SP_MENU_TITLE);
		}
	}
}