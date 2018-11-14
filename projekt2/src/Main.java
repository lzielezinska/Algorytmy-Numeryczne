import static java.lang.System.*;

public class Main {
    public static void main(String[] args){
        out.println("Lucyna Zielezińska & Kacper Dondziak");
       // Tests.testAllTypes();
        //Hypothesis.E1();
        /*MyMatrix<MyType> debug = new MyMatrix<MyType>(150,150,MyType.class);
        debug.fillMatrixAndVector();
        //debug.printMatrix();
        long timestampBefore,timestampAfter;
        timestampBefore = System.currentTimeMillis();
        debug.gauss();
        timestampAfter = System.currentTimeMillis();
        System.out.println((timestampAfter - timestampBefore)/1000d + "s");*/
        Tests.debugVectorNorm();
       /*Tests.testgauss();
        System.out.println("*********");
       Tests.testPartGauss();
        System.out.println("*********");*/
      /* Tests.testFullGauss();
       Hypothesis.Q1();*/
    //Tests.testFullGauss();
       // Hypothesis.E1();


    }
}
