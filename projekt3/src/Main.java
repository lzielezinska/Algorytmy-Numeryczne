public class Main {
    public static void main(String[] args) {
        int size = 20;
        /*MyMatrix matrix = MatrixGenerator.prepareMatrix(size);
        matrix.gauss();
        int index = 0;
        for(int i = 0; i <= size; i++){
            for(int y = 0; y <= (size - i); y++){
                System.out.println("P: " + i + " N: " + y + " result: " + matrix.vector.vector[index++]);
            }
        }
        System.out.println("********************");
        double er = IteratedMethod.getError(matrix.vector.vector, MonteCarlo.getSimulationVector(size, 10000));
        System.out.println(er);*/
        Hypothesis2.Q1();
        /*Vector vector = MatrixGenerator.prepareVector(3);
        MyMatrix matrix = MatrixGenerator.prepareMatrix(3);
        matrix.printExtendedMatrix();
        System.out.println("****");

        IteratedMethod iteratedMethod = new IteratedMethod(matrix, matrix.vector);
        iteratedMethod.gaussSeidelMethod();
        matrix.printExtendedMatrix();
        System.out.println("*");
        Tests.testJacobi();*/
    }


}