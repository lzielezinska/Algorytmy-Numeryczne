import static java.lang.System.*;

public class Main {
    public static void main(String[] args){
        out.println("Lucyna Zielezińska & Kacper Dondziak");
       // Tests.testAllTypes();
        Hypothesis.E1();
        MyMatrix matrix = new MyMatrix(5,5,WrappedDouble.class);
        matrix.fillMatrix();
        matrix.printMatrix();
    }
}
