import java.math.BigInteger;

public class Main {
    public static void main(String[] args){
        //Debug section
        System.out.println("Lucyna Zielezińska & Kacper Dondziak");
        testAllTypes();
    }
    public static void testAllTypes(){
        System.out.println("Test MyType");
        testMyType();
        System.out.println("Test Float");
        testFloat();
        System.out.println("Test Double");
        testDouble();
    }
    public static void testFloat(){
        Randomizer.resetRandomizer();
        MyMatrix matrix = new MyMatrix<WrappedFloat>(3,3,WrappedFloat.class);
        matrix.fillMatrix();
        matrix.printMatrix();
    }
    public static void testDouble(){
        Randomizer.resetRandomizer();
        MyMatrix matrix = new MyMatrix<WrappedFloat>(3,3,WrappedDouble.class);
        matrix.fillMatrix();
        matrix.printMatrix();
    }
    public static void testMyType(){
        Randomizer.resetRandomizer();
        //Test dzialan
        MyType debugNum1 = new MyType(new BigInteger("2"), new BigInteger("3"));
        MyType debugNum2 = new MyType(new BigInteger("4"), new BigInteger("5"));
        MyType debugAdd = debugNum1.add(debugNum2);
        MyType debugSub = debugNum1.sub(debugNum2);
        MyType debugMul = debugNum1.mul(debugNum2);
        MyType debugDiv = debugNum1.div(debugNum2);
        System.out.println(debugAdd.toString());
        System.out.println(debugSub.toString());
        System.out.println(debugMul.toString());
        System.out.println(debugDiv.toString());
        //Test macierzy
        MyMatrix matrix = new MyMatrix<MyType>(3,3,MyType.class);
        matrix.fillMatrix();
        matrix.printMatrix();
    }
}
