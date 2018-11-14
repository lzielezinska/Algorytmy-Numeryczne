import java.math.BigInteger;

public class Tests {
    public static void testAllTypes(){
        System.out.println("Test MyType Operations");
        testMyTypeOperations();
        System.out.println("Test MyType");
        testMyType();
        System.out.println("Test Float");
        testFloat();
        System.out.println("Test Double");
        testDouble();
    }
    public static void debugSwapRows(){
        MyMatrix matrix = new MyMatrix<MyType>(3,3,MyType.class);
        matrix.fillMatrixAndVector();
        matrix.printMatrix();
        matrix.swapRows(0,1);
        System.out.println("*******");
        matrix.printMatrix();
    }
    public static void debugSwapColumns(){
        MyMatrix matrix = new MyMatrix<MyType>(3,3,MyType.class);
        matrix.fillMatrixAndVector();
        matrix.printMatrix();
        matrix.swapColumns(0,1);
        System.out.println("*******");
        matrix.printMatrix();
    }
    public static void debugVectorNorm(){
        ANumber[] te = new MyType[3];
        te[0] = new MyType(2,3);
        te[1] = new MyType(4,3);
        te[2] = new MyType(1,3);
        System.out.println(VectorHandler.getNormInf(te));
    }
    public static void debugFloatMAtrix(){
        MyMatrix matrix = new MyMatrix<WrappedFloat>(3,3,WrappedFloat.class);
    }
    public static void debugDoubleeMAtrix(){
        MyMatrix matrix = new MyMatrix<WrappedFloat>(3,3,WrappedDouble.class);
    }
    public static void debugMyTypeMAtrix(){
        MyMatrix matrix = new MyMatrix<MyType>(3,3,MyType.class);
    }
    public static void myTypeGauss(){
        MyType ma[][] = new MyType[4][4];
        ma[0][0] = new MyType(786432, 655360);
        ma[0][1] = new MyType(1703936, 655360);
        ma[0][2] = new MyType(-65536, 655360);
        ma[0][3] = new MyType(98304, 65536);
        ma[1][0] = new MyType(294912, 65536);
        ma[1][1] = new MyType(6422528, 655360);
        ma[1][2] = new MyType(-262144, 655360);
        ma[1][3] = new MyType(3735552, 655360);
        ma[2][0] = new MyType(65536, 655360);
        ma[2][1] = new MyType(-65536, 655360);
        ma[2][2] = new MyType(-196608, 655360);
        ma[2][3] = new MyType(-229376, 65536);
        ma[3][0] = new MyType(294912, 65536);
        ma[3][1] = new MyType(-3407872, 655360);
        ma[3][2] = new MyType(2752512, 655360);
        ma[3][3] = new MyType(-2228224, 655360);
        MyType vec[]= new MyType[4];
        vec[0] = new MyType(8617984, 655360);
        vec[1] = new MyType(326631424, 6553600);
        vec[2] = new MyType(-92274688, 6553600);
        vec[3] = new MyType(-304807936, 6553600);
        MyMatrix<MyType> matrix1 = new MyMatrix<MyType>(ma,vec, 4,4,MyType.class);
        matrix1.printMatrix();
        System.out.println("******");
        matrix1.gauss();
        matrix1.printMatrix();
    }
    public static void testgauss(){
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
    public static void testPartGauss(){
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
        matrix1.partChoiceGauss();
        matrix1.printMatrix();
    }
    public static void testFullGauss(){
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
        matrix1.fulChoiceGauss();
        matrix1.printMatrix();
    }

    public static void testMulVector(){
        WrappedDouble ma[][] = new WrappedDouble[3][3];
        ma[0][0] = new WrappedDouble(1.0);
        ma[0][1] = new WrappedDouble(1.0);
        ma[0][2] = new WrappedDouble(1.0);
        ma[1][0] = new WrappedDouble(1.0);
        ma[1][1] = new WrappedDouble(1.0);
        ma[1][2] = new WrappedDouble(1.0);
        ma[2][0] = new WrappedDouble(1.0);
        ma[2][1] = new WrappedDouble(1.0);
        ma[2][2] = new WrappedDouble(1.0);

        WrappedDouble vec[]= new WrappedDouble[3];
        vec[0] = new WrappedDouble(1.0);
        vec[1] = new WrappedDouble(1.0);
        vec[2] = new WrappedDouble(1.0);

        MyMatrix<WrappedDouble> matrix1 = new MyMatrix<WrappedDouble>(ma,vec, 3,3,WrappedDouble.class);
        matrix1.printMatrix();
        System.out.println("******");
        matrix1.mulMatrixVector();
    }

    public static void testFloat(){
        Randomizer.resetRandomizer();
        MyMatrix matrix = new MyMatrix<WrappedFloat>(3,3,WrappedFloat.class);
        matrix.fillMatrixAndVector();
        matrix.printMatrix();
    }
    public static void testDouble(){
        Randomizer.resetRandomizer();
        MyMatrix matrix = new MyMatrix<WrappedFloat>(3,3,WrappedDouble.class);
        matrix.fillMatrixAndVector();
        matrix.printMatrix();
    }
    public static void testMyType(){
        Randomizer.resetRandomizer();
        //Test macierzy
        MyMatrix matrix = new MyMatrix<MyType>(3,3,MyType.class);
        matrix.fillMatrixAndVector();
        matrix.printMatrix();
    }
    public static void testMyTypeOperations(){
        //Test dzialan
        MyType debugNum1 = new MyType(new BigInteger("2"), new BigInteger("3"));
        MyType debugNum2 = new MyType(new BigInteger("4"), new BigInteger("5"));
        MyType debugAdd = debugNum1.add(debugNum2);
        MyType debugSub = debugNum1.sub(debugNum2);
        MyType debugMul = debugNum1.mul(debugNum2);
        MyType debugDiv = debugNum1.div(debugNum1);
        System.out.println(debugAdd.toString());
        System.out.println(debugSub.toString());
        System.out.println(debugMul.toString());
        System.out.println(debugDiv.toString());
    }
}
