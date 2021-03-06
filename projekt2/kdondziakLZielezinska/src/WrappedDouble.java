public class WrappedDouble extends ANumber<WrappedDouble> {
    private double value;
    public static WrappedDouble ZERO = new WrappedDouble(0.0);
    public static WrappedDouble ONE = new WrappedDouble(1.0);
    public WrappedDouble(double value){
        this.value = value;
    }

    @Override
    public WrappedDouble returnZero() {
        return new WrappedDouble(0.0);
    }

    @Override
    public WrappedDouble add(WrappedDouble wrappedDouble) {
        double newValue = this.value + wrappedDouble.value;
        return new WrappedDouble(newValue);
    }

    @Override
    public WrappedDouble sub(WrappedDouble wrappedDouble) {
        double newValue = this.value - wrappedDouble.value;
        return new WrappedDouble(newValue);
    }

    @Override
    public WrappedDouble mul(WrappedDouble wrappedDouble) {
        double newValue = this.value * wrappedDouble.value;
        return new WrappedDouble(newValue);
    }

    @Override
    public WrappedDouble div(WrappedDouble wrappedDouble) {
        if(wrappedDouble.equals(ZERO)){
            return null;
        }
        double newValue = this.value / wrappedDouble.value;
        return new WrappedDouble(newValue);
    }

    @Override
    public WrappedDouble changeSign() {
        double newValue = (-1) * this.value;
        return new WrappedDouble(newValue);
    }

    @Override
    public String toString() {
        return Double.toString(this.value);
    }

    @Override
    public Double doubleValue() {
        return value;
    }
    @Override
    public void setToZERO() {
        this.value = 0.0;
    }
    @Override
    public void setToONE() {
        this.value = 1.0;
    }

    @Override
    public WrappedDouble abs() {
        return new WrappedDouble(Math.abs(this.doubleValue()));
    }


    public static WrappedDouble generateRandomNumber() {
        WrappedDouble result;
        result = new WrappedDouble((double)(Randomizer.generateRandomShort()/65536d));
        return result;
    }

    @Override
    public int compareTo(WrappedDouble o) {
        if(this.doubleValue() < o.doubleValue())
            return -1;
        else if(this.doubleValue() > o.doubleValue())
            return 1;
        else
            return 0;
    }
}
