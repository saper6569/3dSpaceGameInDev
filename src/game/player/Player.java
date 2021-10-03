package game.player;

import com.jme3.bullet.collision.shapes.CapsuleCollisionShape;
import com.jme3.bullet.control.CharacterControl;
import com.jme3.math.Vector3f;
import game.screens.MainScreenSetup;

public class Player {

    private static CharacterControl player;

    private MainScreenSetup mainScreen;

    public void initialize() {
        CapsuleCollisionShape capsuleShape = new CapsuleCollisionShape(1.5f, 6f, 1);
        player = new CharacterControl(capsuleShape, 0.05f);

        player.setJumpSpeed(20);
        player.setFallSpeed(15);
        player.setGravity(30);
        player.setPhysicsLocation(new Vector3f(0, 5, 0));
        mainScreen = new MainScreenSetup();
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
