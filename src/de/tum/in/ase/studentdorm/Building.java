package de.tum.in.ase.studentdorm;

import java.util.ArrayList;
import java.util.List;

public class Building {
    /**
     * This class represents our student dorm building.
     */
    //TODO: add class attributes


    //todo: add constructors as described in the problem statement


    /**
     * This method operates the elevator. It will move, open and close the doors as long as necessary in order to fulfill
     * every passenger request.
     */
    public void operateElevator() {

        //TODO: implement the operateElevator method as described in the problem statement

        //TODO: uncomment the following two lines for easier local testing

        // elevator.printSequence();
        // System.out.println("Elevator finished on " + elevator.getCurrentFloor());
    }


    /**
     * This method is used before operating the elevator. It basically prepares the stops which will be visited
     * by the elevator. People waiting on the floors are taken into consideration when filling the lists. The lists are then
     * used to create a new Stops object for elevator.Do not forget that the elevator always starts on floor 0, so the lists
     * have to be sorted accordingly. It is advisable for you to orient yourself on the SCAN algorithm.
     */
    public void processRequests() {
        List<Integer> up = new ArrayList<>();
        List<Integer> down = new ArrayList<>();

        //TODO finish the implementation of the method

    }

    /**
     * This method can be used to initialize some standard values in order to test the implementation locally.
     */
    public void setupPeopleWaiting() {
        //TODO: use this method to generate People waiting on floor
        // this method does not affect the tests, use it as you wish

        /*
        floors[4] = new Person(3);
        floors[0] = new Person(1);
        floors[1] = new Person(0);
        */
    }




    //TODO: use the main method for local testing and debugging
    public static void main(String[] args) {
        //Building building = new Building(5);
        //building.setupPeopleWaiting();
        //building.processRequests();
        //building.operateElevator();
    }

}
