package com.zhen777.www.invokee;

import com.zhen777.www.spi.open.Printer;

import java.util.ServiceLoader;

public class MainApp {


    public static void main(String[] args) {
        ServiceLoader<Printer> printerLoader = ServiceLoader.load(Printer.class);
        for (Printer printer : printerLoader) {
            printer.print();
        }
    }
}