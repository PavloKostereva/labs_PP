package main;  // Указание пакета main

import product.Product;  // Импортируем класс Product из пакета product
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Создаем список продуктов вручную
        List<Product> products = new ArrayList<>();
        products.add(new Product(1, "Apple", "Fruit Corp", 10.0, 30, 100));
        products.add(new Product(2, "Banana", "Tropical Foods", 8.5, 25, 150));
        products.add(new Product(3, "Milk", "Dairy Farm", 3.0, 10, 200));
        products.add(new Product(4, "Bread", "Baker's Best", 1.5, 5, 50));
        products.add(new Product(5, "Cheese", "Cheese Factory", 5.0, 40, 120));

        // Вызовы методов для отображения данных
        System.out.println("a) Список товаров по наименованию:");
        String searchName = "Apple";
        printProductsByName(products, searchName);

        System.out.println("\nb) Список товаров по наименованию и цене до:");
        String nameForPrice = "Banana";
        double maxPrice = 9.0;
        printProductsByNameAndPrice(products, nameForPrice, maxPrice);

        System.out.println("\nc) Список товаров с сроком хранения более:");
        int minShelfLife = 20;
        printProductsByShelfLife(products, minShelfLife);
    }

    // Методы для отображения товаров
    public static void printProductsByName(List<Product> products, String name) {
        boolean found = false;
        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(name)) {
                System.out.println(product);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Товаров с таким названием не найдено.");
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
            System.out.println("Товаров с таким названием и ценой не найдено.");
        }
    }

    public static void printProductsByShelfLife(List<Product> products, int minShelfLife) {
        boolean found = false;
        for (Product product : products) {
            if (product.getShelfLife() > minShelfLife) {
                System.out.println(product);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Товаров с сроком хранения более " + minShelfLife + " дней не найдено.");
        }
    }
}
