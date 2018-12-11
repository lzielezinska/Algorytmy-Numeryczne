/**
 * Created by lucyna on 09.12.18.
 */
public class Tests {
    public static void testGauss() {
        System.out.println("Test of Gauss method");
        Vector vec = new Vector(4);
        vec.vector[0] = 13.15;
        vec.vector[1] = 49.84;
        vec.vector[2] = -14.08;
        vec.vector[3] = -46.51;

        MyMatrix m = new MyMatrix(4,4);
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
        Vector vec = new Vector(4);
        vec.vector[0] = 13.15;
        vec.vector[1] = 49.84;
        vec.vector[2] = -14.08;
        vec.vector[3] = -46.51;

        MyMatrix m = new MyMatrix(4,4);
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
    public static void testFull() {
        System.out.println("Test of Gauss method");
        Vector vec = new Vector(4);
        vec.vector[0] = 13.15;
        vec.vector[1] = 49.84;
        vec.vector[2] = -14.08;
        vec.vector[3] = -46.51;

        MyMatrix m = new MyMatrix(4,4);
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
    System.out.println("Expected resoults: x1 = 1.0000 x2 =   ");
    Vector vec = new Vector(3);

    vec.vector[0] = 17;
    vec.vector[1] = -18;
    vec.vector[2] = 25;

    MyMatrix m = new MyMatrix(3,3,vec);
    m.matrix[0][0] = 20;
    m.matrix[0][1] = 1;
    m.matrix[0][2] = -2;

    m.matrix[1][0] = 3;
    m.matrix[1][1] = 20;
    m.matrix[1][2] = -1;

    m.matrix[2][0] = 2;
    m.matrix[2][1] = -3;
    m.matrix[2][2] = 20;

    m.printExtendedMatrix();
    JacobiMethod test = new JacobiMethod(m, vec);
    System.out.println("**************************");
    test.jacobiMethod();
    m.printExtendedMatrix();


    }

}