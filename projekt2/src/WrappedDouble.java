public class WrappedDouble extends ANumber<WrappedDouble> {
    private double value;
    public WrappedDouble(double value){
        this.value = value;
    }
    @Override
    public WrappedDouble add(WrappedDouble wrappedDouble) {
        double newValue = this.value + wrappedDouble.value;
        return new WrappedDouble(newValue);
    }

    @Override
    public WrappedDouble sub(WrappedDouble wrappedDouble) {
        double newValue = this.value + wrappedDouble.value;
        return new WrappedDouble(newValue);
    }

    @Override
    public WrappedDouble mul(WrappedDouble wrappedDouble) {
        double newValue = this.value + wrappedDouble.value;
        return new WrappedDouble(newValue);
    }

    @Override
    public WrappedDouble div(WrappedDouble wrappedDouble) {
        double newValue = this.value + wrappedDouble.value;
        return new WrappedDouble(newValue);
    }

    @Override
    public String toString() {
        return Double.toString(this.value);
    }

    @Override
    public WrappedDouble generateRandomNumber() {
        WrappedDouble result;
        result = new WrappedDouble((double)(Randomizer.generateRandomShort()/65536));
        return result;
    }
}
