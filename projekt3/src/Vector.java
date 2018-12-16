/**
 * Created by Lucyna Zielezińska & Kacper Dondziak on 06.12.18.
 */
public class Vector {
    private int length;
    public  double vector[];
    public  double savedVector[];

    public Vector(int length){
        this.vector = new double[length];
        this.savedVector = new double[length];
    }
    public Vector(double[] v){
        this.vector = v;
        this.length = v.length;
        this.savedVector = new double[length];
        this.copyVector();
    }

    public void copyVector(){
        for(int i = 0;i < this.vector.length; i++){
            this.savedVector[i] = this.vector[i];
        }
    }

    public int getLength() {
        return length;
    }

    public void fillVector() {
        double createdNumber = 0;
        for(int i = 0;i < this.vector.length; i++){
            try{
                createdNumber =  (Randomizer.generateRandomShort()/65536d);
                this.vector[i] = createdNumber;
                this.savedVector[i] = createdNumber;
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }



}
