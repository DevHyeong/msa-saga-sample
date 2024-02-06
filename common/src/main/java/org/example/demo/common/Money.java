package org.example.demo.common;

import java.math.BigDecimal;

public class Money {
    public static Money ZERO = new Money(0);
    private BigDecimal amount;
    private Money(){

    }
    public Money(BigDecimal amount){
        this.amount = amount;
    }
    public Money(String s){
        this.amount = new BigDecimal(s);
    }
    public Money(int i){
        this.amount = new BigDecimal(i);
    }
    public Money add(Money delta){
        return new Money(amount.add(delta.amount));
    }
    public Money multiply(int x){
        return new Money(amount.multiply(new BigDecimal(x)));
    }
}
