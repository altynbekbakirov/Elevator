package com.company.models;

import com.company.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class Floor {
    int currentFloor;
    List<Passenger> passengerList = new ArrayList<>();
    int numberOfPassengers = Utils.getRandomNumber(1, 2);

    public Floor(int currentFloor) {
        this.currentFloor = currentFloor;
        for (int i = 0; i < numberOfPassengers; i++) {
            passengerList.add(new Passenger(currentFloor));
        }
    }

    public List<Passenger> getPassengerList() {
        return passengerList;
    }
}
