package product;  // Указание пакета product

public class Product {

    private int id;
    private String name;
    private String manufacturer;
    private double price;
    private int shelfLife;
    private int quantity;

    // Конструктор
    public Product(int id, String name, String manufacturer, double price, int shelfLife, int quantity) {
        this.id = id;
        this.name = name;
        this.manufacturer = manufacturer;
        this.price = price;
        this.shelfLife = shelfLife;
        this.quantity = quantity;
    }

    // Геттеры
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public double getPrice() {
        return price;
    }

    public int getShelfLife() {
        return shelfLife;
    }

    public int getQuantity() {
        return quantity;
    }

    // Сеттеры
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setShelfLife(int shelfLife) {
        this.shelfLife = shelfLife;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Переопределение toString для отображения информации о продукте
    @Override
    public String toString() {
        return "Product{id=" + id + ", name='" + name + '\'' + ", manufacturer='" + manufacturer + '\'' +
                ", price=" + price + ", shelfLife=" + shelfLife + " days, quantity=" + quantity + '}';
    }
}
