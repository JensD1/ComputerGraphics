package configuration;

import java.awt.*;

public class Configuration {

    public static final int SCREEN_WIDTH = 1920; // Make sure these are even numbers.
    public static final int SCREEN_HEIGHT = 1080;

    public static final double ROUNDING_ERROR = 0.00000001;

    public static final Color BACKGROUND_COLOR = Color.black;

    public static final int MAX_RECURSE_LEVEL = 5; // The recurse level counts for additional ray's shot ==> the total depth of rays = original ray + recurselevel

    public static final double MIN_SHININESS = 0.05;
    public static final double MIN_TRANSPARENTNESS = 0.3;
    public static final double MIN_IN_LIGHT = 0.3;

    public static final int LOWEST_PRIORITY = 5;

}
