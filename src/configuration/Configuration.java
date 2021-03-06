package configuration;

import java.awt.*;

public class Configuration {

    public static final int SCREEN_WIDTH = 1920; // Make sure these are even numbers.
    public static final int SCREEN_HEIGHT = 1080;

    public static final double ROUNDING_ERROR = 0.00000001;

    public static final Color BACKGROUND_COLOR = Color.black;

    public static final int MAX_RECURSE_LEVEL = 3; // The recurse level counts for additional ray's shot ==> the total depth of rays = original ray + recurselevel

    public static final double MIN_SHININESS = Configuration.ROUNDING_ERROR;
    public static final double MIN_TRANSPARENTNESS = Configuration.ROUNDING_ERROR;
    public static final double MIN_IN_LIGHT = Configuration.ROUNDING_ERROR;

    public static final int LOWEST_PRIORITY = 5;

}
