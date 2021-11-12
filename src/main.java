import com.jme3.app.SimpleApplication;
import com.jme3.font.BitmapFont;
import com.jme3.math.ColorRGBA;
import game.screens.MainScreenSetup;

class main extends SimpleApplication {

    MainScreenSetup mainScreenSetup = new MainScreenSetup();

    public static void main(String[] args) {
        main app = new main();
        app.start();
    }

    @Override
    public void simpleInitApp() {
        stateManager.attach(mainScreenSetup);
        viewPort.setBackgroundColor(new ColorRGBA(0.7f, 0.8f, 1f, 1f));
        flyCam.setMoveSpeed(100);
    }
}

