package com.example.demo;

class Movie {
    private final Type type;

    Movie(Type type) {
        this.type = type;
    }

    public int computePrice(int days) {
        switch (type) {
            case REGULAR: return days + 1;
            case NEW_RELEASE: return days * 2;
            case CHILDREN: return 5;
            case ELDER: return 1;
            default: throw new IllegalArgumentException("shouldnt happen.");
        }
    }

    enum Type {
        REGULAR, NEW_RELEASE, CHILDREN, ELDER
    }
}

public class Application {
    public static void main(String[] args) {
        System.out.println(new Movie(Movie.Type.REGULAR).computePrice(2));
        System.out.println(new Movie(Movie.Type.NEW_RELEASE).computePrice(2));
        System.out.println(new Movie(Movie.Type.CHILDREN).computePrice(2));
        System.out.println("commit NOW !");
    }
}
