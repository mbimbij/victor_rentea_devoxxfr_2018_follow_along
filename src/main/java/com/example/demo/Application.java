package com.example.demo;

import java.util.function.BiFunction;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class Movie {
    private final Type type;

    Movie(Type type) {
        this.type = type;
    }

    enum Type {
        REGULAR(PriceService::computeRegularPrice),
        NEW_RELEASE(PriceService::computeNewReleasePrice),
        CHILDREN(PriceService::computeChildrenPrice);
        public final BiFunction<PriceService, Integer, Integer> priceAlgo;

        Type(BiFunction<PriceService, Integer, Integer> priceAlgo) {
            this.priceAlgo = priceAlgo;
        }
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
        return type.priceAlgo.apply(this, days);
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
