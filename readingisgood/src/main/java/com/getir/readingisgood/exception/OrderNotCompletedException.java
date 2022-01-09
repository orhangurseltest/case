package com.getir.readingisgood.exception;

public class OrderNotCompletedException extends BaseRuntimeException {
    public OrderNotCompletedException(Integer stock, Integer amount) {
        super(formatMessage(stock, amount));
    }

    private static String formatMessage(Integer stock, Integer amount){
        return String.format("Order cannot be completed. Stock :%d Amount: %d",stock,amount);
    }
}
