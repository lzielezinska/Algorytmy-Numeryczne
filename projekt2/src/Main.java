import java.math.BigInteger;

public class Main {
    private static final String MYTYPE = "MYTYPE";
    private static final String WRAPPEDFLOAT = "WRAPPEDFLOAT";
    private static final String WRAPPEDDOUBLE = "WRAPPEDDOUBLE";
    public static void main(String[] args){
        //Debug section
        System.out.println("hello");
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

        MyMatrix matrix = new MyMatrix(3,3,MYTYPE);
        matrix.fillMatrix();
        matrix.printMatrix();
    }
}
