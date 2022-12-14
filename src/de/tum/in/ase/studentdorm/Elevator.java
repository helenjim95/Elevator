package de.tum.in.ase.studentdorm;

import java.util.ArrayList;
import java.util.List;

public class Elevator {

    private List<Person> passangers;
    private Direction direction;
    private Stops stops;
    private List<Integer> sequence;
    private int capacity;
    private int maxFloor;
    private int currentFloor;
    private static final int DEFAULT_CAPACITY = 15;

    public Elevator(int capacity, int maxFloor) {
        this.passangers = new ArrayList<>();
        this.direction = Direction.IDLE;
        this.stops = new Stops();
        this.sequence = new ArrayList<>();
        if (capacity > 0 && capacity <= DEFAULT_CAPACITY) {
            this.capacity = capacity;
        } else {
            throw new IllegalArgumentException("Capacity must be between 1 and 15");
        }
        this.maxFloor = maxFloor;
        this.currentFloor = 0;
    }

    public Elevator(int maxFloor) {
        this.passangers = new ArrayList<>();
        this.direction = Direction.IDLE;
        this.stops = new Stops();
        this.sequence = new ArrayList<>();
        this.capacity = DEFAULT_CAPACITY;
        this.maxFloor = maxFloor;
        this.currentFloor = 0;
    }

    public List<Person> getPassangers() {
        return passangers;
    }

    public void setPassangers(List<Person> passangers) {
        this.passangers = passangers;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Stops getStops() {
        return stops;
    }

    public void setStops(Stops stops) {
        this.stops = stops;
    }

    public List<Integer> getSequence() {
        return sequence;
    }

    public void setSequence(List<Integer> sequence) {
        this.sequence = sequence;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getMaxFloor() {
        return maxFloor;
    }

    public void setMaxFloor(int maxFloor) {
        this.maxFloor = maxFloor;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public int getDEFAULT_CAPACITY() {
        return DEFAULT_CAPACITY;
    }

    public void move() {
        if (changeFloor()) {
            sequence.add(currentFloor);
        }
    }

    public boolean changeFloor() {
        if (!this.stops.isEmpty(this.direction)) {
            if (this.direction == Direction.UP) {
                this.currentFloor = this.stops.getStopsUp().get(0);
                this.stops.remove(this.direction, 0);
                return true;
            } else if (this.direction == Direction.DOWN) {
                this.currentFloor = this.stops.getStopsDown().get(0);
                this.stops.remove(this.direction, 0);
                return true;
            } else {
                this.direction = Direction.UP;
                return true;
            }
        } else {
            this.direction = Direction.IDLE;
            return false;
        }
    }

    public boolean openDoor(Person person) {
//        TODO: check if there is a person waiting on the floor (think about how that might be related to the argument of this method and where this method will be used)
        if (this.passangers.size() == 0) {
            this.direction = Direction.IDLE;
            return false;
        } else {
            if (person.getDestinationFloor() == this.getCurrentFloor()) {
                this.passangers.remove(person);
                if (this.direction != Direction.IDLE) {
                    this.stops.remove(this.direction, person.getDestinationFloor());
                }
                return true;
            } else {
                return false;
            }
        }
    }

    public void closeDoor() {
//        TODO: checked if the elevator visited all stops in the stops lists
        if (!this.passangers.isEmpty()) {
            if (this.stops.isEmpty(this.direction)) {
                this.direction = Direction.getReverseDirection(this.direction);
            }
        }
    }

//    TODO: Add capacity checks
    public void printSequence() {
        for (Integer number : sequence) {
            System.out.println(number);
        }
    }
}
