package game.screens;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.asset.plugins.ZipLocator;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.bullet.util.CollisionShapeFactory;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;
import game.environment.Lighting;
import game.gui.Overlays;
import game.player.Movement;
import game.player.Player;

public class MainScreenSetup extends AbstractAppState {

    private SimpleApplication app;
    private Spatial sceneModel;
    private RigidBodyControl landscape;
    private BulletAppState bulletAppState;
    private Player player;

    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);
        this.app = (SimpleApplication)app;
        Movement movement = new Movement();
        Overlays overlays = new Overlays();
        stateManager.attach(movement);
        stateManager.attach(overlays);
        player = new Player();
        player.initialize();

        this.app.getAssetManager().registerLocator("town.zip", ZipLocator.class);
        sceneModel = this.app.getAssetManager().loadModel("main.scene");
        sceneModel.setLocalScale(2f);

        CollisionShape sceneShape = CollisionShapeFactory.createMeshShape(sceneModel);
        landscape = new RigidBodyControl(sceneShape, 0);
        sceneModel.addControl(landscape);
        this.app.getRootNode().attachChild(sceneModel);

        this.app.getRootNode().addLight(Lighting.setUpLight(ColorRGBA.White.mult(1.3f)));
        this.app.getRootNode().addLight(Lighting.setUpLight(new Vector3f(2.8f, -2.8f, -2.8f), ColorRGBA.White));

        bulletAppState = new BulletAppState();
        stateManager.attach(bulletAppState);
        bulletAppState.getPhysicsSpace().add(landscape);
        bulletAppState.getPhysicsSpace().add(player.getPlayer());
    }

    @Override
    public void cleanup() {
        super.cleanup();
    }
}
