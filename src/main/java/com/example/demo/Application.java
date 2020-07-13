package com.example.demo;

class Movie {
    private final Type type;

    Movie(Type type) {
        this.type = type;
    }

    public int computePrice(int days) {
        return type.computePrice(days);
    }

    enum Type {
        REGULAR {
            public int computePrice(int days) {
                return days + 1;
            }
        }, NEW_RELEASE {
            public int computePrice(int days) {
                return days * 2;
            }
        }, CHILDREN {
            public int computePrice(int days) {
                return 5;
            }
        };

        public abstract int computePrice(int days);
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
