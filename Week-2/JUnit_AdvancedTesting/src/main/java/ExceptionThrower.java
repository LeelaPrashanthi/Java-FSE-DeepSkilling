public class ExceptionThrower {

    public void throwIfNegative(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("Negative number not allowed");
        }
    }
}
