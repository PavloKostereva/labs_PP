public class Main {
    // Метод для генерації чисел Фібоначч і
    public static FibonacciNumber[] generateFibonacciNumbers(int n) {
        FibonacciNumber[] fibonacciNumbers = new FibonacciNumber[n];
        long prev = 1, current = 1;

        fibonacciNumbers[0] = new FibonacciNumber(1, 1); // перше число Фібоначчі
        if (n > 1) {
            fibonacciNumbers[1] = new FibonacciNumber(2, 1); // друге число Фібоначчі
        }

        for (int i = 2; i < n; i++) {
            long next = prev + current;
            fibonacciNumbers[i] = new FibonacciNumber(i + 1, next);
            prev = current;
            current = next;
        }
        return fibonacciNumbers;
    }

    // Основна функція
    public static void main(String[] args) {
        // Читання числа N з командного рядка або з консолі
        int n;
        if (args.length > 0) {
            n = Integer.parseInt(args[0]);//з рядка в число
        } else {
            java.util.Scanner scanner = new java.util.Scanner(System.in);
            System.out.print("Введіть кількість чисел Фібоначчі (N): ");
            n = scanner.nextInt();
        }

        // Генеруємо перші N чисел Фібоначчі
        FibonacciNumber[] fibonacciNumbers = generateFibonacciNumbers(n);

        // Виводимо всі числа Фібоначчі та перевіряємо умову
        System.out.println("Числа Фібоначчі:");
        for (FibonacciNumber fib : fibonacciNumbers) {
            System.out.printf("Число Фібоначчі №%d: %d", fib.getIndex(), fib.getValue());
            if (fib.isOneLessThanSquare()) {
                System.out.println(" - на 1 менше, ніж квадрат");
            } else {
                System.out.println();
            }
        }
    }
}
