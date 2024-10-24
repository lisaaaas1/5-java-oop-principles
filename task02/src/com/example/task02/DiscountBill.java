package com.example.task02;

public class DiscountBill extends Bill {
    private final int discount;

    public DiscountBill(int discount) {
        this.discount = discount;
    }

    // считаем цену со скидкой
    @Override
    public long getPrice() {
        return (super.getPrice() - getAbsoluteDiscount());
    }

    public long getAbsoluteDiscount() {
        return (super.getPrice() * discount / 100);
    }

    // получение размера скидки
    public int getDiscount() {
        return discount;
    }
}
