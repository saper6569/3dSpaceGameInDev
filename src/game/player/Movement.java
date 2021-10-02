package game.player;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.math.Vector3f;

public class Movement extends AbstractAppState implements ActionListener {

    private Vector3f walkDirection = new Vector3f();
    private boolean left = false, right = false, up = false, down = false, notWalking = true;

    private Vector3f camDir = new Vector3f();
    private Vector3f camLeft = new Vector3f();

    private SimpleApplication app;

    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);
        this.app = (SimpleApplication)app;

        setUpKeys();
    }

    private void setUpKeys() {
        this.app.getInputManager().addMapping("Left", new KeyTrigger(KeyInput.KEY_A));
        this.app.getInputManager().addMapping("Right", new KeyTrigger(KeyInput.KEY_D));
        this.app.getInputManager().addMapping("Up", new KeyTrigger(KeyInput.KEY_W));
        this.app.getInputManager().addMapping("Down", new KeyTrigger(KeyInput.KEY_S));
        this.app.getInputManager().addMapping("Jump", new KeyTrigger(KeyInput.KEY_SPACE));
        this.app.getInputManager().addListener(this, "Left");
        this.app.getInputManager().addListener(this, "Right");
        this.app.getInputManager().addListener(this, "Up");
        this.app.getInputManager().addListener(this, "Down");
        this.app.getInputManager().addListener(this, "Jump");
    }

    public void onAction(String binding, boolean isPressed, float tpf) {
        if (binding.equals("Left")) {
            left = isPressed;
        }
        else if (binding.equals("Right")) {
            right= isPressed;
        }
        else if (binding.equals("Up")) {
            up = isPressed;
        }
        else if (binding.equals("Down")) {
            down = isPressed;
        }
        else if (binding.equals("Jump") && Player.isOnGround()) {
            if (isPressed) {
                Player.jump();
            }
        }
    }

    @Override
    public void update(float tpf) {
        camDir.set(this.app.getCamera().getDirection()).multLocal(0.3f);
        camLeft.set(this.app.getCamera().getLeft()).multLocal(0.2f);
        walkDirection.set(0, 0, 0);
        if (left) {
            walkDirection.addLocal(camLeft);
        }
        if (right) {
            walkDirection.addLocal(camLeft.negate());

        }
        if (up) {
            walkDirection.addLocal(camDir);

        }
        if (down) {
            walkDirection.addLocal(camDir.negate());
        }

        Player.setWalkDirection(walkDirection);
        this.app.getCamera().setLocation(Player.getPhysicsLocation());
    }
}
