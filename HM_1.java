/* Класс отвечающий за покупку определенного товара, с учетом скидки
содержит в себе информацию о кол-ве товаров, о цене одного одного товара и о скидке на товар */
class Product
{
    
    private int quantity; //Кол-во товаров
    private double price; //Цена одного товара
    private double discount; // Скидка на товар

    public Product(int quantity, double price, double discount) //Конструктор класса Product
    {
        this.quantity = quantity;
        this.price = price;
        this.discount = discount;
    }


    //Создание метода для расчета и вывода общей суммы покупки со скидкой и без нее.
    public static void calculateTotal(Product purchase)
    {
        double totalWithoutDiscount = purchase.quantity * purchase.price; // Стоимость товара без скидки
        double discountAmount = totalWithoutDiscount * (purchase.discount / 100); // Размер скидки на товар
        double totalWithDiscount = totalWithoutDiscount - discountAmount; // Стоимость товара со скидкой 
        
        System.out.printf("Total price without discount: %.2f\n", totalWithoutDiscount); //Вывод стоимости товара без скидки
        System.out.printf("Total price with discount: %.2f\n", totalWithDiscount);//Вывод стоимости товар со скидкой
        System.out.printf("\n", totalWithDiscount);// Разделитель 
    }
}

class Main
{
    public static void main(String[] args)
    {
        Product purchase1 = new Product(5, 30, 0.75); //Создание обьекта с кол-во товаров 5, ценой за товар 30 и скидкой 0.75% 
        Product purchase2 = new Product(10, 60, 42.575); //Создание обьекта с кол-во товаров 10, ценой за товар 60 и скидкой 42.575% 
        Product purchase3 = new Product(15, 90, 59.1); //Создание обьекта с кол-во товаров 15, ценой за товар 90 и скидкой 59.1% 
    
        Product.calculateTotal(purchase1);//Вызов метода для обьекта
        Product.calculateTotal(purchase2);
        Product.calculateTotal(purchase3);
    }
}