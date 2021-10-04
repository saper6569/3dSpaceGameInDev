package game.player;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.bullet.collision.shapes.CapsuleCollisionShape;
import com.jme3.bullet.control.CharacterControl;
import com.jme3.light.Light;
import com.jme3.light.SpotLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.LightControl;
import game.environment.Lighting;
import game.screens.MainScreenSetup;

public class Player {

    private static CharacterControl player;
    private SpotLight torch;

    public void initialize(Node node) {
        CapsuleCollisionShape capsuleShape = new CapsuleCollisionShape(1.5f, 6f, 1);
        player = new CharacterControl(capsuleShape, 0.05f);

        player.setJumpSpeed(15);
        player.setFallSpeed(10);
        player.setGravity(30);
        player.setPhysicsLocation(new Vector3f(0, 5, 0));

        torch = new SpotLight();
        torch.setSpotRange(110f);
        torch.setSpotInnerAngle(40f * FastMath.DEG_TO_RAD);
        torch.setSpotOuterAngle(70f * FastMath.DEG_TO_RAD);
        torch.setColor(ColorRGBA.White.mult(10f));
        node.addLight(torch);
    }

    public void update(Camera camera) {
        torch.setDirection(camera.getDirection());
        torch.setPosition(camera.getLocation());
    }

    public Object getPlayer() {
        return player;
    }

    public static void jump() {
        player.jump();
    }

    public static boolean isOnGround() {
        return player.onGround();
    }

    public static void setWalkDirection(Vector3f walkDirection) {
        player.setWalkDirection(walkDirection);
    }

    public static Vector3f getPhysicsLocation() {
        return player.getPhysicsLocation();
    }
}
