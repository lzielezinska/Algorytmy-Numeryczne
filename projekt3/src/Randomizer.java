import java.util.Random;

/**
 * Created by Lucyna Zielezińska & Kacper Dondziak on 06.12.18.
 */
public class Randomizer {
    public static final int SEED = 23415;
    private static Random randomGenerator = new Random(SEED);
    private Randomizer(){
    }
    public static void resetRandomizer(){
        randomGenerator = new Random(SEED);
    }
    public static int generateRandomShort(){
        return randomGenerator.nextInt() % 65535;
    }
}
