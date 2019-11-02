package sample;

import javafx.scene.paint.Color;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Generates a random.

 */
public class RandomGenerator {

    /**
     * Generates random  color.
     *
     * @return a random color
     */
    public static Color generateColor() {
        ThreadLocalRandom local = ThreadLocalRandom.current();
        int r = local.nextInt(255);
        int g = local.nextInt(255);
        int b = local.nextInt(255);
        return Color.rgb(r, g, b);
    }

    /**
     * Generates board refresh speed.
     *
     * @param k board refresh speed
     * @return a speed from range 0.5k to 1.5k
     */
    public static double generateRefreshSpeed(long k) {
        return k == 0 ? 0 : ThreadLocalRandom.current().nextDouble(0.5 * k, 1.5 * k);
    }

    /**
     * Generates probability.
     *
     * @return a random probability value
     */
    public static double generateProbability() {
        return ThreadLocalRandom.current().nextDouble(1);
    }
}
