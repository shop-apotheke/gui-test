package com.shopapotheke.util;
import java.util.concurrent.ThreadLocalRandom;

public class DataGenerator {
    public static int generateRandomNumber(int min, int max){
        int randomNumber = ThreadLocalRandom.current().nextInt(min, max + 1);
        return randomNumber;
    }
}
