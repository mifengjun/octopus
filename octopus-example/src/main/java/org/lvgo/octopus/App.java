package org.lvgo.octopus;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");


        Octopus.init(new DouBanSimulator()).start();
    }
}
