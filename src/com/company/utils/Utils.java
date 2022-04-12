package com.company.utils;

import com.company.models.Building;

public class Utils {

    public static int getRandomNumber(int min, int max) {
        min = (int) Math.ceil(min);
        max = (int) Math.floor(max);
        return (int) (Math.random() * (max - min + 1)) + min;
    }

    public static int getRandomFloor(int floor1) {
        int min = 1;
        int max = Building.floors;
        int random;
        do {
            random = (int) (Math.random() * (max - min + 1));
        } while (random == floor1);
        return random;
    }

}
