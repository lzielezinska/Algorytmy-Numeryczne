public class WrappedFloat extends ANumber<WrappedFloat> {
    private float value;
    public WrappedFloat(float value){
        this.value = value;
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
    public String toString() {
        return Float.toString(this.value);
    }

    @Override
    public WrappedFloat generateRandomNumber() {
        WrappedFloat result;
        result = new WrappedFloat((float)(Randomizer.generateRandomShort()/65536));
        return result;
    }
}
