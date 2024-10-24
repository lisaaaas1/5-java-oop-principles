package com.example.task02;

public class Task02Main {

    private static final Item ITEM1 = new Item("Товар 1", 10);
    private static final Item ITEM2 = new Item("Товар 2", 20);
    private static final Item ITEM3 = new Item("Товар 3", 30);
    private static final Item ITEM4 = new Item("Товар 4", 40);
    private static final Item ITEM5 = new Item("Товар 5", 50);
    private static final Item ITEM6 = new Item("Товар 6", 60);

    public static void main(String[] args) {
        Bill bill = new Bill();
        bill.add(ITEM1, 7);
        System.out.println(bill);

        DiscountBill discountBill = new DiscountBill(80);
        discountBill.add(ITEM1, 10);
        // Выводим информацию о счете со скидкой
        System.out.println("\nСчет со скидкой 10%:");
        System.out.println(discountBill);
        System.out.println("Итоговая цена со скидкой: " + discountBill.getPrice());
        System.out.println("Размер скидки: " + discountBill.getAbsoluteDiscount());

    }
}
