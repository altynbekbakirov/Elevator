package com.company.models;

import com.company.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class Building {
    public static final int floors = Utils.getRandomNumber(5, 10);
    private final List<Floor> floorList = new ArrayList<>();

    public Building() {
        for (int i = 0; i < floors; i++) {
            floorList.add(new Floor(i));
        }
    }

    public List<Floor> getFloorList() {
        return floorList;
    }

}
