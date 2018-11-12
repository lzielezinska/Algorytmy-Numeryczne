public class VectorHandler {
    public static double getNormInf(ANumber[] vec){
        ANumber result = vec[0];
        for(int i = 1; i < vec.length; i++){
            if(result.abs() < vec[i].abs()){
                result = vec[i];
            }
        }
        return result.doubleValue();
    }
}
