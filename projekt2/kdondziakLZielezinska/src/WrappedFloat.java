public class WrappedFloat extends ANumber<WrappedFloat>{
    private float value;
    public static WrappedFloat ZERO = new WrappedFloat(0f);
    public static WrappedFloat ONE = new WrappedFloat(1.0f);
    public WrappedFloat(float value){
        this.value = value;
    }

    @Override
    public WrappedFloat returnZero() {
        return new WrappedFloat(0f);
    }

    @Override
    public WrappedFloat add(WrappedFloat wrappedFloat) {
        float newValue = this.value + wrappedFloat.value;
        return new WrappedFloat(newValue);
    }

    @Override
    public WrappedFloat sub(WrappedFloat wrappedFloat) {
        float newValue = this.value - wrappedFloat.value;
        return new WrappedFloat(newValue);
    }

    @Override
    public WrappedFloat mul(WrappedFloat wrappedFloat) {
        float newValue = this.value * wrappedFloat.value;
        return new WrappedFloat(newValue);
    }

    @Override
    public WrappedFloat div(WrappedFloat wrappedFloat) {
        float newValue = this.value / wrappedFloat.value;
        return new WrappedFloat(newValue);
    }

    @Override
    public WrappedFloat changeSign() {
        float newValue = this.value * (-1);
        return new WrappedFloat(newValue);
    }

    @Override
    public String toString() {
        return Float.toString(this.value);
    }

    @Override
    public Double doubleValue() {
        return (double)value;
    }

    @Override
    public WrappedFloat abs() {
        return new WrappedFloat((float)Math.abs(this.doubleValue()));
    }

    @Override
    public void setToZERO() {
        this.value = 0f;
    }
    @Override
    public void setToONE() {
        this.value = 1f;
    }

    public static WrappedFloat generateRandomNumber() {
        WrappedFloat result;
        result = new WrappedFloat(((float)Randomizer.generateRandomShort()/65536f));
        return result;
    }

    @Override
    public int compareTo(WrappedFloat o) {
        if(this.doubleValue() < o.doubleValue())
            return -1;
        else if(this.doubleValue() > o.doubleValue())
            return 1;
        else
            return 0;
    }
}
