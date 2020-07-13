package com.example.demo;

import org.mockito.Mockito;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class Movie {
    private final Type type;

    Movie(Type type) {
        this.type = type;
    }

    enum Type {
        REGULAR, NEW_RELEASE, CHILDREN;
    }
}

interface FactorRepo {
    Double getFactor();
}

class PriceService {
    private final FactorRepo repo;

    PriceService(FactorRepo repo) {
        this.repo = repo;
    }

    protected Integer computeNewReleasePrice(int days) {
        return (int) (repo.getFactor() * days);
    }

    protected Integer computeRegularPrice(int days) {
        return days + 1;
    }

    protected Integer computeChildrenPrice(int days) {
        return 5;
    }

    public Integer computePrice(Movie.Type type, int days) {
        switch (type) {
            case NEW_RELEASE:
                return computeNewReleasePrice(days);
            case REGULAR:
                return computeRegularPrice(days);
            case CHILDREN:
                return computeChildrenPrice(days);
            default:
                throw new IllegalArgumentException();
        }
    }
}

public class Application {
    public static void main(String[] args) {
        FactorRepo repo = mock(FactorRepo.class);
        when(repo.getFactor()).thenReturn(2d);
        PriceService priceService = new PriceService(repo);

        System.out.println(priceService.computeRegularPrice(2));
        System.out.println(priceService.computeNewReleasePrice(2));
        System.out.println(priceService.computeChildrenPrice(2));

//        System.out.println(new Movie(Movie.Type.REGULAR).computePrice(2));
//        System.out.println(new Movie(Movie.Type.NEW_RELEASE).computePrice(2));
//        System.out.println(new Movie(Movie.Type.CHILDREN).computePrice(2));
        System.out.println("commit NOW !");
    }
}
