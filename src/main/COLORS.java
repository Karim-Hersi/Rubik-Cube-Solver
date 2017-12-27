package main;

public class COLORS{
	//CSS strings for modifying text color and color
	public static final String CSS_COLOR = "-fx-background-color: ";
	public static final String CSS_TEXT_COLOR = "-fx-text-fill: ";
	public static final String HIGHLIGHT_TEXT = CSS_TEXT_COLOR + "#154360;";
	public static final String BLACK_TEXT = CSS_TEXT_COLOR + "black;";

	//CSS Strings for all 6 rubik colors
	public static final String CSS_RED = CSS_COLOR + "#D61919;";
	public static final String CSS_BLUE = CSS_COLOR + "#0431B4;";
	public static final String CSS_WHITE = CSS_COLOR + "#FFFFFF;";
	public static final String CSS_GREEN = CSS_COLOR + "#298A08;";
	public static final String CSS_YELLOW = CSS_COLOR + "#FFF033;";
	public static final String CSS_ORANGE = CSS_COLOR + "#FF8000;";

	//Only store one initial for each color to minimize space required
	public static final char RED = 'R';
	public static final char BLUE = 'B';
	public static final char WHITE = 'W';
	public static final char GREEN = 'G';
	public static final char YELLOW = 'Y';
	public static final char ORANGE = 'O';

	public static final char[] COLORS = new char[]{RED, BLUE, WHITE, GREEN, YELLOW, ORANGE};
	public static final String[] CSS_COLORS = new String[]{CSS_RED, CSS_BLUE, CSS_WHITE, CSS_GREEN, CSS_YELLOW, CSS_ORANGE};

	public static String getCSS(char color){
		for(int i = 0; i < COLORS.length; i++){
			if(color == COLORS[i]){
				return CSS_COLORS[i];
			}
		}

		return null;
	}

	public static int getIndexOfColor(char color){
		for(int i = 0; i < COLORS.length; i++){
			if(COLORS[i] == color){
				return i;
			}
		}

		return -1;
	}
}
