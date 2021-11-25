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
import com.jme3.light.PointLight;
import com.jme3.light.SpotLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.LightControl;
import com.jme3.texture.Texture;
import com.jme3.util.TangentBinormalGenerator;
import game.environment.Lighting;
import game.gui.Overlays;
import game.player.Movement;
import game.player.Player;

public class MainScreenSetup extends AbstractAppState {

    private SimpleApplication app;
    private Spatial sceneModel;
    private RigidBodyControl landscape;
    private BulletAppState bulletAppState;
    private Player player = new Player();
    private Material sceneMaterial;

    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);
        this.app = (SimpleApplication)app;

        Movement movement = new Movement();
        Overlays overlays = new Overlays();

        stateManager.attach(overlays);
        player.initialize(this.app.getRootNode());
        stateManager.attach(movement);

        sceneModel = this.app.getAssetManager().loadModel("Starbase/starbase.glb");
        sceneModel.setLocalScale(2.5f);
        this.app.getRootNode().attachChild(sceneModel);

        CollisionShape sceneShape = CollisionShapeFactory.createMeshShape(sceneModel);
        landscape = new RigidBodyControl(sceneShape, 0);
        sceneModel.addControl(landscape);

        this.app.getRootNode().addLight(Lighting.setUpLight(ColorRGBA.White.mult(10)));

        bulletAppState = new BulletAppState();
        stateManager.attach(bulletAppState);
        bulletAppState.getPhysicsSpace().add(landscape);
        bulletAppState.getPhysicsSpace().add(player.getPlayer());
    }

    @Override
    public void update(float delta) {
    }

    @Override
    public void cleanup() {
        super.cleanup();
    }
}
