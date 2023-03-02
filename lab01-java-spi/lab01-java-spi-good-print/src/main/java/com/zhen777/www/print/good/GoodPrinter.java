package com.zhen777.www.print.good;

import com.zhen777.www.spi.open.Printer;

public class GoodPrinter implements Printer {
    public void print() {
        System.out.println("你是个好人~");
    }
}