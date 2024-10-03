package main;
import product.Product;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Product> products = new ArrayList<>();
        products.add(new Product(1, "Apple", "Fruit Corp", 10.0, 30, 100));
        products.add(new Product(2, "Banana", "Tropical Foods", 8.5, 25, 150));
        products.add(new Product(3, "Milk", "Dairy Farm", 3.0, 10, 200));
        products.add(new Product(4, "Bread", "Baker's Best", 1.5, 5, 50));
        products.add(new Product(5, "Cheese", "Cheese Factory", 5.0, 40, 120));
        products.add(new Product(6, "Bread", "Cheese Factory", 5.0, 70, 120));

        products.add(new Product(7, "Milk", "Fruit Corp", 12.0, 30, 100));
        products.add(new Product(8, "Bread", "Tropical bread", 20, 25, 150));
        products.add(new Product(9, "Milk", "Dairy Farm", 30.0, 40, 200));
        products.add(new Product(10, "Bread", "Baker's Best", 15, 5, 50));
        products.add(new Product(11, "Cheese", "Cheese Factory", 7.0, 40, 120));
        products.add(new Product(12, "Bread", "Cheese Factory", 8.0, 70, 120));




        Scanner scanner = new Scanner(System.in);//об'єкт для зчитування

        System.out.println("a) Введіть найменування товару:");
        String searchName = scanner.nextLine();
        printProductsByName(products, searchName);  //метод для виведення товарів з вказаним найменуванням



        System.out.println("\nb) Введіть найменування товару для пошуку за ціною:");
        String nameForPrice = scanner.nextLine();
        System.out.println("Введіть максимальну ціну:");
        double maxPrice = scanner.nextDouble();
        printProductsByNameAndPrice(products, nameForPrice, maxPrice);  //фільтрація товарів



        System.out.println("\nc) Введіть мінімальний термін зберігання (дні):");
        int minShelfLife = scanner.nextInt();
        printProductsByShelfLife(products, minShelfLife);  // фільтрація

        scanner.close();
    }

    public static void printProductsByName(List<Product> products, String name) {
        boolean found = false;  // перевірка наявності товару
        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(name)) {
                System.out.println(product);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Товарів з таким найменуванням не знайдено.");
        }
    }

    public static void printProductsByNameAndPrice(List<Product> products, String name, double maxPrice) {
        boolean found = false;
        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(name) && product.getPrice() <= maxPrice) {
                System.out.println(product);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Товарів з таким найменуванням та ціною не знайдено.");
        }
    }

    // виведення товарів з терміном зберігання більше заданого
    public static void printProductsByShelfLife(List<Product> products, int minShelfLife) {
        boolean found = false;
        for (Product product : products) {
            if (product.getShelfLife() > minShelfLife) {
                System.out.println(product);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Товарів з терміном зберігання більше " + minShelfLife + " днів не знайдено.");
        }
    }
}
