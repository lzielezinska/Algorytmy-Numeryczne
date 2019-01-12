/**
 * Created by lucyna on 09.12.18.
 */
public class Tests {
    public static void testGenaration() {
        MyMatrix mac = new MyMatrix(100,100);
        mac.fillMatrixAndVector();
        mac.printExtendedMatrix();

    }

        public static void testGauss() {
        System.out.println("Test of Gauss method");


        MyMatrix m = new MyMatrix(4,4);
        m.vector[0] = 13.15;
        m.vector[1] = 49.84;
        m.vector[2] = -14.08;
        m.vector[3] = -46.51;

        m.matrix[0][0] = 1.2;
        m.matrix[0][1] = 2.6;
        m.matrix[0][2] = -0.1;
        m.matrix[0][3] = 1.5;
        m.matrix[1][0] = 4.5;
        m.matrix[1][1] = 9.8;
        m.matrix[1][2] = -0.4;
        m.matrix[1][3] = 5.7;
        m.matrix[2][0] = 0.1;
        m.matrix[2][1] = -0.1;
        m.matrix[2][2] = -0.3;
        m.matrix[2][3] = -3.5;
        m.matrix[3][0] = 4.5;
        m.matrix[3][1] = -5.2;
        m.matrix[3][2] = 4.2;
        m.matrix[3][3] = -3.4;
        m.printExtendedMatrix();
        System.out.println("******");
        m.gauss();
        m.printExtendedMatrix();



    }
    public static void testPart() {
        System.out.println("Test of Gauss method");
        MyMatrix m = new MyMatrix(4,4);

        m.vector[0] = 13.15;
        m.vector[1] = 49.84;
        m.vector[2] = -14.08;
        m.vector[3] = -46.51;

        m.matrix[0][0] = 1.2;
        m.matrix[0][1] = 2.6;
        m.matrix[0][2] = -0.1;
        m.matrix[0][3] = 1.5;
        m.matrix[1][0] = 4.5;
        m.matrix[1][1] = 9.8;
        m.matrix[1][2] = -0.4;
        m.matrix[1][3] = 5.7;
        m.matrix[2][0] = 0.1;
        m.matrix[2][1] = -0.1;
        m.matrix[2][2] = -0.3;
        m.matrix[2][3] = -3.5;
        m.matrix[3][0] = 4.5;
        m.matrix[3][1] = -5.2;
        m.matrix[3][2] = 4.2;
        m.matrix[3][3] = -3.4;

        System.out.println("******");
        System.out.println("PART CHOICE");
        m.partChoiceGauss();
        m.printExtendedMatrix();



    }
    public static void testPart1() {
        System.out.println("Test of Gauss method");
        MyMatrix m = new MyMatrix(4,4);

        m.vector[0] = 13.15;
        m.vector[1] = 49.84;
        m.vector[2] = -14.08;
        m.vector[3] = -46.51;

        m.matrix[0][0] = 1.2;
        m.matrix[0][1] = 2.6;
        m.matrix[0][2] = -0.1;
        m.matrix[0][3] = 1.5;
        m.matrix[1][0] = 4.5;
        m.matrix[1][1] = 9.8;
        m.matrix[1][2] = -0.4;
        m.matrix[1][3] = 5.7;
        m.matrix[2][0] = 0.1;
        m.matrix[2][1] = -0.1;
        m.matrix[2][2] = -0.3;
        m.matrix[2][3] = -3.5;
        m.matrix[3][0] = 4.5;
        m.matrix[3][1] = -5.2;
        m.matrix[3][2] = 4.2;
        m.matrix[3][3] = -3.4;

        System.out.println("******");
        System.out.println("PART CHOICE");
        m.partChoiceGaussForSparseMatrix();
        m.printExtendedMatrix();



    }
    public static void testFull() {
        System.out.println("Test of Gauss method");

        MyMatrix m = new MyMatrix(4,4);

        m.vector[0] = 13.15;
        m.vector[1] = 49.84;
        m.vector[2] = -14.08;
        m.vector[3] = -46.51;

        m.matrix[0][0] = 1.2;
        m.matrix[0][1] = 2.6;
        m.matrix[0][2] = -0.1;
        m.matrix[0][3] = 1.5;
        m.matrix[1][0] = 4.5;
        m.matrix[1][1] = 9.8;
        m.matrix[1][2] = -0.4;
        m.matrix[1][3] = 5.7;
        m.matrix[2][0] = 0.1;
        m.matrix[2][1] = -0.1;
        m.matrix[2][2] = -0.3;
        m.matrix[2][3] = -3.5;
        m.matrix[3][0] = 4.5;
        m.matrix[3][1] = -5.2;
        m.matrix[3][2] = 4.2;
        m.matrix[3][3] = -3.4;

        System.out.println("******");
        System.out.println("FULL CHOICE");
        m.fulChoiceGauss();
        m.printExtendedMatrix();


    }

    public static void testJacobi(){
        System.out.println("Test of Jacobi method");
        System.out.println("Example from lecture");

        MyMatrix m = new MyMatrix(4,4);

        m.vector[0] = 6;
        m.vector[1] = 25;
        m.vector[2] = -11;
        m.vector[3] = 15;

        m.matrix[0][0] = 10;
        m.matrix[0][1] = -1;
        m.matrix[0][2] = 2;
        m.matrix[0][3] = 0;

        m.matrix[1][0] = -1;
        m.matrix[1][1] = 11;
        m.matrix[1][2] = -1;
        m.matrix[1][3] = 3;

        m.matrix[2][0] = 2;
        m.matrix[2][1] = -1;
        m.matrix[2][2] = 10;
        m.matrix[2][3] = -1;

        m.matrix[3][0] = 0;
        m.matrix[3][1] = 3;
        m.matrix[3][2] = -1;
        m.matrix[3][3] = 8;

        m.printExtendedMatrix();
        IteratedMethod test = new IteratedMethod(m);
        System.out.println("**************************");
        test.jacobiMethod();
        m.printExtendedMatrix();


    }

    public static void testGaussSeidel(){
        System.out.println("Test of Gauss-Seidel method");
        System.out.println("Example from lecture");

        MyMatrix m = new MyMatrix(4,4);

        m.vector[0] = 6;
        m.vector[1] = 25;
        m.vector[2] = -11;
        m.vector[3] = 15;

        m.matrix[0][0] = 10;
        m.matrix[0][1] = -1;
        m.matrix[0][2] = 2;
        m.matrix[0][3] = 0;

        m.matrix[1][0] = -1;
        m.matrix[1][1] = 11;
        m.matrix[1][2] = -1;
        m.matrix[1][3] = 3;

        m.matrix[2][0] = 2;
        m.matrix[2][1] = -1;
        m.matrix[2][2] = 10;
        m.matrix[2][3] = -1;

        m.matrix[3][0] = 0;
        m.matrix[3][1] = 3;
        m.matrix[3][2] = -1;
        m.matrix[3][3] = 8;

        m.printExtendedMatrix();
        IteratedMethod test = new IteratedMethod(m);
        System.out.println("**************************");
        test.gaussSeidelMethod();
        m.printExtendedMatrix();


    }

    public static void testAproximationForGaussSeidel(){
        double argumnets[] = {0.0, 0.5, 1.0, 1.5, 2.0, 3.0, 3.5};
        double values[] = {1.02, 0.62, 0.50, 0.60, 0.98, 3.12, 5.08};

        Aproximation.solveAproximationEquasionForGaussSeidelMethod(argumnets, values);

    }

    public static void testAproximationForGaussSparseMatrix(){
        double argumnets[] = {0.0, 0.5, 1.0, 1.5, 2.0, 3.0, 3.5};
        double values[] = {1.02, 0.62, 0.50, 0.60, 0.98, 3.12, 5.08};

        Aproximation.solveAproximationEquasionForGaussSparseMatrix(argumnets, values);

    }

    public static void testAproximationForGauss(){
        double argumnets[] = {0.0, 0.5, 1.0, 1.5, 2.0, 3.0, 3.5};
        double values[] = {1.02, 0.62, 0.50, 0.60, 0.98, 3.12, 5.08};

        Aproximation.solveAproximationEquasionForGauss(argumnets, values);

    }



}
