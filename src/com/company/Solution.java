package com.company;

import com.company.models.Building;
import com.company.models.Floor;
import com.company.models.Passenger;
import com.company.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Solution {
    private static final int MAX_PASSENGERS_COUNT = 5;
    private int currentFloor = 0;
    private int maxFloor = 0;
    private State status = State.UP;
    private final List<Passenger> elevatorList = new ArrayList<>();
    private final Building building = new Building();
    private int passengerUp, passengerDown, pass1, pass2, pass0;
    private Floor floor;
    StringBuilder liftOut = new StringBuilder();
    StringBuilder liftIn = new StringBuilder();
    StringBuilder liftWaiting = new StringBuilder();

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.start();
    }

    void start() {
        System.out.printf("*******************\nВ здании %d этажей\n*******************\n", building.getFloorList().size());
        System.out.println();
        while (true) {
            if (status == State.UP) {
                goUp();
            } else if (status == State.DOWN) {
                goDown();
            } else if (status == State.STOP) {
                break;
            }
        }
    }

    void goUp() {
        liftOut = new StringBuilder();
        liftIn = new StringBuilder();
        liftWaiting = new StringBuilder();

        passengerUp = 0;
        passengerDown = 0;
        pass0 = 0;
        pass1 = 0;
        pass2 = 0;

        System.out.printf("\n^ ****** %d этаж  ****** ^\n", currentFloor + 1);
        floor = building.getFloorList().get(currentFloor);

        pass0 = elevatorList.size();
        elevatorOut(currentFloor);
        pass1 = elevatorList.size();

        liftWaiting.append("---- Ждушие лифт ----");
        for (int i = 0; i < floor.getPassengerList().size(); i++) {
            liftWaiting.append(String.format("\n#%d, откуда %d, куда %d",
                    i + 1,
                    floor.getPassengerList().get(i).getCurrentFloor() + 1,
                    floor.getPassengerList().get(i).getFloorToGo() + 1));
            if (floor.getPassengerList().get(i).getFloorToGo() > currentFloor) {
                passengerUp++;
                elevatorIn(floor.getPassengerList().get(i));
            } else {
                passengerDown++;
            }
        }
        pass2 = elevatorList.size();
        liftWaiting.append(String.format("\nВсего %d(вверх %d, вниз %d)",
                floor.getPassengerList().size(), passengerUp, passengerDown));

        if (elevatorList.size() > 0) {
            liftIn.append("---- В лифте ----");
            for (int i = 0; i < elevatorList.size(); i++) {
                liftIn.append(String.format("\n#%d, откуда %d, куда %d",
                        i + 1, elevatorList.get(i).getCurrentFloor() + 1, elevatorList.get(i).getFloorToGo() + 1));
            }
            liftIn.append(String.format("\nВсего %d (до %d, после %d)\nМаксимальный этаж - %d\n",
                    elevatorList.size(), pass1 > 0 ? pass1 : pass0, pass2, maxFloor + 1));
        }

        if (liftOut.length() > 0) System.out.println(liftOut);
        if (liftWaiting.length() > 0) System.out.println(liftWaiting);
        if (liftIn.length() > 0) System.out.println(liftIn);

        delay(1);

        if (maxFloor == currentFloor) {
            status = State.DOWN;
            reArrangePassengers();
        } else currentFloor++;
    }

    void goDown() {
        liftOut = new StringBuilder();
        liftIn = new StringBuilder();
        liftWaiting = new StringBuilder();

        passengerUp = 0;
        passengerDown = 0;
        pass0 = 0;
        pass1 = 0;
        pass2 = 0;

        System.out.printf("\nv ****** %d этаж  ****** v\n", currentFloor + 1);
        floor = building.getFloorList().get(currentFloor);

        pass0 = elevatorList.size();
        elevatorOut(currentFloor);
        pass1 = elevatorList.size();

        liftWaiting.append("---- Ждушие лифт ----");
        for (int i = 0; i < floor.getPassengerList().size(); i++) {
            liftWaiting.append(String.format("\n#%d, откуда %d, куда %d",
                    i + 1,
                    floor.getPassengerList().get(i).getCurrentFloor() + 1,
                    floor.getPassengerList().get(i).getFloorToGo() + 1));
            if (floor.getPassengerList().get(i).getFloorToGo() > currentFloor) {
                passengerUp++;
            } else {
                elevatorIn(floor.getPassengerList().get(i));
                passengerDown++;
            }
        }
        pass2 = elevatorList.size();
        liftWaiting.append(String.format("\nВсего %d(вверх %d, вниз %d)",
                floor.getPassengerList().size(), passengerUp, passengerDown));

        if (elevatorList.size() > 0) {
            liftIn.append("---- В лифте ----");
            for (int i = 0; i < elevatorList.size(); i++) {
                liftIn.append(String.format("\n#%d, откуда %d, куда %d",
                        i + 1, elevatorList.get(i).getCurrentFloor() + 1, elevatorList.get(i).getFloorToGo() + 1));
            }
            liftIn.append(String.format("\nВсего %d (до %d, после %d)\nМаксимальный этаж - %d\n",
                    elevatorList.size(), pass1 > 0 ? pass1 : pass0, pass2, maxFloor + 1));
        }

        if (liftOut.length() > 0) System.out.println(liftOut);
        if (liftWaiting.length() > 0) System.out.println(liftWaiting);
        if (liftIn.length() > 0) System.out.println(liftIn);

        delay(1);

        if (maxFloor == currentFloor) {
            status = State.UP;
            reArrangePassengers();
        } else currentFloor--;
    }

    void elevatorOut(int currentFloor) {
        List<Passenger> found = new ArrayList<>();
        for (Passenger passenger : elevatorList) {
            if (passenger.getFloorToGo() == currentFloor) {
                found.add(passenger);
                passenger.setCurrentFloor(currentFloor);
                passenger.setFloorToGo(Utils.getRandomFloor(currentFloor));
                liftOut.append(String.format("\nНовое значение: %d - %d", passenger.getCurrentFloor() + 1, passenger.getFloorToGo() + 1));

            }
        }
        if (found.size() > 0) {
            liftOut.insert(0, "--- Выходящие из лифта ---");
            liftOut.append(String.format("\nВсего %d", found.size()));
        }
        elevatorList.removeAll(found);
    }

    void elevatorIn(Passenger passenger) {
        if (elevatorList.size() < MAX_PASSENGERS_COUNT) {
            if (passenger.getFloorToGo() > maxFloor && status == State.UP) maxFloor = passenger.getFloorToGo();
            if (passenger.getFloorToGo() < maxFloor && status == State.DOWN) maxFloor = passenger.getFloorToGo();
            elevatorList.add(passenger);
        }
    }

    void reArrangePassengers() {
        for (int i = 0; i < building.getFloorList().size(); i++) {
            Floor floor = building.getFloorList().get(i);
            ListIterator<Passenger> iterator = floor.getPassengerList().listIterator();
            while (iterator.hasNext()) {
                Passenger passenger = iterator.next();
                if (passenger.getCurrentFloor() != i) {
                    building.getFloorList().get(passenger.getCurrentFloor()).getPassengerList().add(passenger);
                    iterator.remove();
                }
            }
        }
    }

    void chooseDirection() {
        int max = maxFloor;
        if (elevatorList.size() == 0 && maxFloor < building.getFloorList().size() && maxFloor != 0) {
            if (status == State.UP && passengerUp > 0 && passengerUp > passengerDown) {
                for (int i = 0; i < floor.getPassengerList().size(); i++) {

                }
            }
        }
    }

    void delay(int ms) {
        try {
            Thread.sleep(ms * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    enum State {
        UP,
        DOWN,
        STOP
    }

}
