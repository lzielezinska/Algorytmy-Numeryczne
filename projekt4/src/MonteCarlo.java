public class MonteCarlo {

    public static double[] getSimulationVector(int n, int numberOfSimulations){
        double[] result = new double[((n + 1) * (n + 2) / 2)];
        int index = 0;
        for(int i = 0; i <= n; i++){
            for(int y = 0; y <= (n - i); y++){
                result[index++] = getProbabilityOfNSimulations(i, y, n, numberOfSimulations);
            }
        }
        return result;
    }
    public static double getProbabilityOfNSimulations(int yes, int no, int n, int numberOfSimulations){
        double result;
        int[] sumResult = simulationLoop(yes, no, n, numberOfSimulations);
        result = ((double)sumResult[0]/numberOfSimulations);
        return result;
    }

    public static void printGeneratedMonteCarloVector(int size){
        double[] result = MonteCarlo.getSimulationVector(size, 10000);
        int index = 0;

        for(int i = 0; i <= size; i++){
            for(int y = 0; y <= (size - i); y++){
                System.out.println("P: " + i + " N: " + y + " result: " + result[index++]);
            }
        }
    }

    public static int[] simulationLoop(int yes, int no, int n, int numberOfSimulations){
        Simulation simulation;
        String resultOfSimulation;
        int sumResult[] = {0, 0, 0};
        for(int i = 0; i < numberOfSimulations; i++){
            simulation = new Simulation(yes, no, n);
            resultOfSimulation = simulation.getResultOfSimulation();
            switch (resultOfSimulation){
                case "Y":
                    sumResult[0]++;
                    break;
                case "N":
                    sumResult[1]++;
                    break;
                case "U":
                    sumResult[2]++;
                    break;
            }
        }
        return sumResult;
    }
    public static void doNSimulationsWithPrint(int yes, int no, int n, int numberOfSimulations){
        int[] sumResult = simulationLoop(yes, no, n, numberOfSimulations);
        System.out.println("*****************************");
        System.out.println("Number of simulations: " + numberOfSimulations);
        System.out.println("Yes:       " + sumResult[0] + " (" + ((double)sumResult[0]/numberOfSimulations * 100) + "%)");
        System.out.println("No:        " + sumResult[1] + "  (" + ((double)sumResult[1]/numberOfSimulations * 100) + "%)");
        System.out.println("Undefined: " + sumResult[2] + "   (" + ((double)sumResult[2]/numberOfSimulations * 100) + "%)");
        System.out.println("*****************************");
    }
}
