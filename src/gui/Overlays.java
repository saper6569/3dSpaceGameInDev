package gui;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.font.BitmapText;

public class Overlays extends AbstractAppState {

    private SimpleApplication app;

    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);
        this.app = (SimpleApplication)app;

        this.app. setDisplayStatView(false);
        this.app.getGuiNode() = this.app.getAssetManager().loadFont("Interface/Fonts/Default.fnt");
        BitmapText ch = new BitmapText(guiFont, false);
        ch.setSize(guiFont.getCharSet().getRenderedSize() * 2);
        ch.setText("X");
        ch.setLocalTranslation( this.app.getCamera().getWidth() / 2 - ch.getLineWidth()/2,this.app.getCamera().getHeight() / 2 + ch.getLineHeight()/2, 0);
        this.app.getGuiNode().attachChild(ch);
    }
}
