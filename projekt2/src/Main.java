import static java.lang.System.*;

public class Main {
    public static void main(String[] args){
        out.println("Lucyna Zielezińska & Kacper Dondziak");
       // Tests.testAllTypes();
      //  Hypothesis.E1();
        MyMatrix<MyType> debug = new MyMatrix<MyType>(3,3,MyType.class);
        debug.fillMatrixAndVector();
        debug.printMatrix();
        debug.gauss();
        System.out.println("*********");
        debug.printMatrix();
    }
}
