package com.meiliinc.mls.designPatterns.singleton;

public class Singleton {

    private static class Person {
        private Person(){
            System.out.println("person");
        }
    }

    private static Person person = new Person();

    private static Singleton instance;

    static {
        instance = new Singleton();
    }

    {
        System.out.println("1111222");
    }

    private Singleton() {
        System.out.println("hello");
    }

    public static Singleton getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        try {
            Class.forName("com.meiliinc.mls.designPatterns.singleton.Singleton");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
