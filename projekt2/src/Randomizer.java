import java.util.Random;

public class Randomizer {
    public static final int SEED = 123465;
    private static Random randomGenerator = new Random(SEED);
    private Randomizer(){
    }
    public static int generateRandomShort(){
        return randomGenerator.nextInt() % 65535;
    }
}
