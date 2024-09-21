public class FibonacciNumber {
    private int index;//поле зберігає індекс числа Фібоначчі у послідовності
    private long value;//зберігає саме число Фібоначчі.

    // Конструктор
    public FibonacciNumber(int index, long value) {
        this.index = index;
        this.value = value;
    }

    // повертає індекс числа
    public int getIndex() {
        return index;
    }

    //  перевіряє чи є число на 1 меншим за квадрат деякого числа:
    public long getValue() {
        return value;
    }

    //чи є число на 1 менше за квадрат
    public boolean isOneLessThanSquare() {
        double sqrt = Math.sqrt(this.value + 1);
        return sqrt == (int) sqrt;
    }
}
