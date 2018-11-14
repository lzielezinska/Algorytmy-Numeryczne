import static java.lang.System.*;

public class Main {
    public static void main(String[] args){
        out.println("Lucyna Zielezińska & Kacper Dondziak");
       // Tests.testAllTypes();
      //  Hypothesis.E1();
        /*MyMatrix<MyType> debug = new MyMatrix<MyType>(5,5,WrappedDouble.class);
        debug.fillMatrixAndVector();
        debug.printMatrix();
        debug.gauss();
        System.out.println("*********");
        debug.printMatrix();*/





        WrappedDouble ma[][] = new WrappedDouble[4][4];
        ma[0][0] = new WrappedDouble(1.2);
        ma[0][1] = new WrappedDouble(2.6);
        ma[0][2] = new WrappedDouble(-0.1);
        ma[0][3] = new WrappedDouble(1.5);
        ma[1][0] = new WrappedDouble(4.5);
        ma[1][1] = new WrappedDouble(9.8);
        ma[1][2] = new WrappedDouble(-0.4);
        ma[1][3] = new WrappedDouble(5.7);
        ma[2][0] = new WrappedDouble(0.1);
        ma[2][1] = new WrappedDouble(-0.1);
        ma[2][2] = new WrappedDouble(-0.3);
        ma[2][3] = new WrappedDouble(-3.5);
        ma[3][0] = new WrappedDouble(4.5);
        ma[3][1] = new WrappedDouble(-5.2);
        ma[3][2] = new WrappedDouble(4.2);
        ma[3][3] = new WrappedDouble(-3.4);
        WrappedDouble vec[]= new WrappedDouble[4];
        vec[0] = new WrappedDouble(13.15);
        vec[1] = new WrappedDouble(49.84);
        vec[2] = new WrappedDouble(-14.08);
        vec[3] = new WrappedDouble(-46.51);

        MyMatrix<WrappedDouble> matrix1 = new MyMatrix<WrappedDouble>(ma,vec, 4,4,WrappedDouble.class);
        matrix1.printMatrix();
        System.out.println("******");
        matrix1.gauss();
        matrix1.printMatrix();

    }
}
