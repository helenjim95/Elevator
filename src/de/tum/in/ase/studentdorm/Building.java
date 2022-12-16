package de.tum.in.ase.studentdorm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Building {
    /**
     * This class represents our student dorm building.
     */
    //add class attributes
    private Person[] peopleOnFloor;
    private Elevator elevator;

    //add constructors as described in the problem statement
//    TODO: Take into account that according to German law, buildings with fewer than 5 floors should not be allowed to have an elevator.
    public Building(int numberOfFloors) {
        if (numberOfFloors < 5) {
            numberOfFloors = 5;
        }
        this.peopleOnFloor = new Person[numberOfFloors];
        this.elevator = new Elevator(numberOfFloors);
        for (int i = 0; i < numberOfFloors; i++) {
            this.peopleOnFloor[i] = new Person(i);
        }
    }

    public Building(int numberOfFloors, Elevator elevator) {
        this.elevator = elevator;
        this.peopleOnFloor = new Person[numberOfFloors];
        for (int i = 0; i < numberOfFloors; i++) {
            this.peopleOnFloor[i] = new Person(i);
        }
    }

    public Person[] getPeopleOnFloor() {
        return peopleOnFloor;
    }

    public void setPeopleOnFloor(Person[] peopleOnFloor) {
        this.peopleOnFloor = peopleOnFloor;
    }

    public Elevator getElevator() {
        return elevator;
    }

    public void setElevator(Elevator elevator) {
        this.elevator = elevator;
    }

    /**
     * This method operates the elevator. It will move, open and close the doors as long as necessary in order to fulfill
     * every passenger request.
     */
    public void operateElevator() throws IllegalArgumentException {
        //TODO: implement the operateElevator method as described in the problem statement
        while (elevator.getDirection() != Direction.IDLE) {
            elevator.move();
            for (Person person : peopleOnFloor) {
                if (person != null) {
                    if (person.getDestinationFloor() == elevator.getCurrentFloor()) {
                        elevator.openDoor(person);
                        if (peopleOnFloor[elevator.getCurrentFloor()] != null) {
                            int availableSeats = elevator.getCapacity() - elevator.getPassengers().size();
                            if (availableSeats >= peopleOnFloor[elevator.getCurrentFloor()].getDestinationFloor()) {
                                Person newPerson = peopleOnFloor[elevator.getCurrentFloor()];
                                elevator.getPassengers().add(newPerson);
                                elevator.getSequence().add(newPerson.getDestinationFloor());
                                peopleOnFloor[elevator.getCurrentFloor()] = null;
                            } else {
                                throw new IllegalArgumentException("Elevator is full");
                            }
                        }
                        elevator.closeDoor();
                    }
                }
            }

        }
        //uncomment the following two lines for easier local testing
        elevator.printSequence();
        System.out.println("Elevator finished on " + elevator.getCurrentFloor());
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
        for (Person person : peopleOnFloor) {
            if (person != null) {
                if (person.getDestinationFloor() > elevator.getCurrentFloor() && !up.contains(person.getDestinationFloor())) {
                    up.add(person.getDestinationFloor());
                } else if (person.getDestinationFloor() < elevator.getCurrentFloor() && !down.contains(person.getDestinationFloor())) {
                    down.add(person.getDestinationFloor());
                }
            }
        }
        up.sort(Integer::compareTo);
        down.sort(Collections.reverseOrder());
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
