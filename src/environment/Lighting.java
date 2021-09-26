package environment;

import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;

public class Lighting {

    public static DirectionalLight setUpLight(Vector3f direction, com.jme3.math.ColorRGBA colour) {
        DirectionalLight dl = new DirectionalLight();
        dl.setColor(colour);
        dl.setDirection(direction.normalizeLocal());
        return dl;
    }

    public static AmbientLight setUpLight (ColorRGBA colour) {
        AmbientLight al = new AmbientLight();
        al.setColor(colour);
        return al;
    }
}