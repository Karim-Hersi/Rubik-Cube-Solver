package uicomponents;

import javafx.scene.control.Label;

public class ShuffleCubeButton extends Label{
    private static final String ICON_CODE = "   \uf074";
    private static final String CSS_ID = "icon";
    private static final String CSS_FONT_SIZE = "-fx-font-size: 20px;";
    
    public ShuffleCubeButton(){
        super(ICON_CODE);
        setId(CSS_ID);
        setStyle(CSS_FONT_SIZE);
    }
}