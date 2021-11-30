package game.gui;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.font.BitmapFont;
import com.jme3.font.BitmapText;

public class Overlays extends AbstractAppState {

    private SimpleApplication app;
    private BitmapFont guiFont;

    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);
        this.app = (SimpleApplication)app;

        this.app. setDisplayStatView(false);
        guiFont = this.app.getAssetManager().loadFont("Interface/Fonts/Default.fnt");
        BitmapText cross = new BitmapText(guiFont, false);
        cross.setSize(guiFont.getCharSet().getRenderedSize() * 2);
        cross.setText("+");
        cross.setLocalTranslation( this.app.getCamera().getWidth() / 2 - cross.getLineWidth()/2, this.app.getCamera().getHeight() / 2 + cross.getLineHeight()/2, 0);
        this.app.getGuiNode().attachChild(cross);
    }
}
