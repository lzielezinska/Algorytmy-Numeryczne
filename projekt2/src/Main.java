import static java.lang.System.*;

public class Main {
    public static void main(String[] args){
        out.println("Lucyna Zielezińska & Kacper Dondziak");
       // Tests.testAllTypes();
        //Hypothesis.E1();
       /* MyMatrix<MyType> debug = new MyMatrix<MyType>(11,11,WrappedDouble.class);
        debug.fillMatrixAndVector();
        debug.printMatrix();
        debug.fulChoiceGauss();
        System.out.println("*********");
        debug.printMatrix();
        System.out.println("*********");*/
       Tests.testgauss();
        System.out.println("*********");
       Tests.testPartGauss();
        System.out.println("*********");
       Tests.testFullGauss();
       Hypothesis.Q1();





    }
}
