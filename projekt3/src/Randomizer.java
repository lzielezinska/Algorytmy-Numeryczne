import java.util.Random;

public class Randomizer {
    public static final int SEED = 123415;
    private static Random randomGenerator = new Random(SEED);
    private static Random seededRandomGenerator;
    private Randomizer(){
    }
    public static void resetRandomizer(){
        randomGenerator = new Random(SEED);
    }
    public static int generateRandomShort(){
        return randomGenerator.nextInt() % 65535;
    }
    public static int getSeededRandomInt(){
        seededRandomGenerator = new Random(System.currentTimeMillis());
        return seededRandomGenerator.nextInt();
    }
}
