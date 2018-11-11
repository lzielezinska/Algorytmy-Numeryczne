public class VectorHandler {
    public static ANumber getNormInf(ANumber[] vec){
        ANumber result = vec[0];
        for(int i = 1; i < vec.length; i++){
            if(result.compareTo(vec[i]) == 1){
                result = vec[i];
            }
        }
        return result;
    }
}
