package screens;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.asset.plugins.ZipLocator;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.collision.shapes.CapsuleCollisionShape;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bullet.control.CharacterControl;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.bullet.util.CollisionShapeFactory;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;
import environment.Lighting;
import gui.Overlays;

public class MainScreenSetup extends AbstractAppState {

    private SimpleApplication app;
    private Lighting light = new Lighting();
    private Spatial sceneModel;
    private RigidBodyControl landscape;
    private BulletAppState bulletAppState;
    private CharacterControl player;

    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);
        this.app = (SimpleApplication)app;
        Overlays overlays = new Overlays();
        stateManager.attach(overlays);

        this.app.getAssetManager().registerLocator("town.zip", ZipLocator.class);
        sceneModel = this.app.getAssetManager().loadModel("main.scene");
        sceneModel.setLocalScale(2f);

        CollisionShape sceneShape = CollisionShapeFactory.createMeshShape(sceneModel);
        landscape = new RigidBodyControl(sceneShape, 0);
        sceneModel.addControl(landscape);
        this.app.getRootNode().attachChild(sceneModel);

        this.app.getRootNode().addLight(light.setUpLight(ColorRGBA.White.mult(1.3f)));
        this.app.getRootNode().addLight(light.setUpLight(new Vector3f(2.8f, -2.8f, -2.8f), ColorRGBA.White));

        CapsuleCollisionShape capsuleShape = new CapsuleCollisionShape(1.5f, 6f, 1);
        player = new CharacterControl(capsuleShape, 0.05f);
        player.setJumpSpeed(20);
        player.setFallSpeed(30);

        player.setGravity(60);
        player.setPhysicsLocation(new Vector3f(0, 10, 0));

        bulletAppState = new BulletAppState();
        stateManager.attach(bulletAppState);
        bulletAppState.getPhysicsSpace().add(landscape);
        bulletAppState.getPhysicsSpace().add(player);
    }

    @Override
    public void cleanup() {
        super.cleanup();
    }
}
