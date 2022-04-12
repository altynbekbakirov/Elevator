package com.company.models;

import com.company.utils.Utils;

public class Passenger {
    private int currentFloor;
    private int floorToGo;

    public Passenger(int currentFloor) {
        this.currentFloor = currentFloor;
        this.floorToGo = Utils.getRandomFloor(currentFloor);
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public int getFloorToGo() {
        return floorToGo;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public void setFloorToGo(int floorToGo) {
        this.floorToGo = floorToGo;
    }
}
