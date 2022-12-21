package de.tum.in.ase.studentdorm;

import java.util.ArrayList;
import java.util.List;

public class Elevator {

    private final List<Person> passengers;
    private Direction direction;
    private Stops stops;
    private final List<Integer> sequence;
    private final int capacity;
    private final int maxFloor;
    private int currentFloor;
    private static final int DEFAULT_CAPACITY = 15;

    public Elevator(int capacity, int maxFloor) {
        this.passengers = new ArrayList<>();
        this.direction = Direction.IDLE;
        this.stops = new Stops();
        this.sequence = new ArrayList<>();
        if (capacity < DEFAULT_CAPACITY) {
            throw new IllegalArgumentException("Capacity must be larger than 0");
        } else {
            this.capacity = capacity;
        }
        this.maxFloor = maxFloor;
        this.currentFloor = 0;
    }

    public Elevator(int maxFloor) {
        this.passengers = new ArrayList<>();
        this.direction = Direction.IDLE;
        this.stops = new Stops();
        this.sequence = new ArrayList<>();
        this.capacity = DEFAULT_CAPACITY;
        this.maxFloor = maxFloor;
        this.currentFloor = 0;
    }

    public List<Person> getPassengers() {
        return passengers;
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

    public int getCapacity() {
        return capacity;
    }

    public int getMaxFloor() {
        return maxFloor;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public int getDefaultCapacity() {
        return DEFAULT_CAPACITY;
    }

    public void move() {
        if (changeFloor()) {
            this.sequence.add(this.currentFloor);
        }
    }

    public boolean changeFloor() {
        if (this.direction == Direction.IDLE) {
            return false;
        } else {
            if (!this.stops.isEmpty(this.direction)) {
                if (this.direction == Direction.UP && this.currentFloor != this.maxFloor && this.stops.getStopsUp().contains(this.currentFloor)) {
                    if (this.currentFloor == this.stops.getStopsUp().get(0)) {
                        this.stops.remove(this.direction, 0);
                        this.currentFloor++;
                        return true;
                    }
                    this.currentFloor++;
                    return false;
                } else if (this.direction == Direction.DOWN && this.currentFloor != 0 && this.stops.getStopsDown().contains(this.currentFloor)) {
                    if (this.currentFloor == this.stops.getStopsDown().get(0)) {
                        this.stops.remove(this.direction, 0);
                        this.currentFloor--;
                        return true;
                    }
                    this.currentFloor--;
                    return false;
                } else {
                    return false;
                }
            } else {
                this.direction = Direction.IDLE;
                return false;
            }
        }
    }



    public boolean openDoor(Person person) {
//        TODO: check if there is a person waiting on the floor (think about how that might be related to the argument of this method and where this method will be used)
        if (this.passengers.size() == 0 || person == null) {
            this.direction = Direction.IDLE;
            return false;
        } else {
            for (Person p : this.passengers) {
                if (p.getDestinationFloor() == this.currentFloor) {
                    this.passengers.remove(p);
                    if (this.direction != Direction.IDLE) {
                        this.stops.remove(this.direction, p.getDestinationFloor());
                    }
                    return true;
                } else {
                    return false;
                }
            }
            if (person.getDestinationFloor() == this.currentFloor) {
                this.passengers.add(person);
                return true;
            } else {
                return false;
            }
        }
    }

    public void closeDoor() {
//        TODO: checked if the elevator visited all stops in the stops lists
        if (this.stops.isEmpty(this.direction)) {
            this.direction = Direction.IDLE;
        } else if (!this.passengers.isEmpty() && this.stops.isEmpty(this.direction)) {
            this.direction = Direction.getReverseDirection(this.direction);
        } else if (this.currentFloor == this.maxFloor) {
            this.direction = Direction.DOWN;
        } else if (this.currentFloor == 0) {
            this.direction = Direction.UP;
        }
    }

//    TODO: Add capacity checks
    public void printSequence() {
        for (Integer number : sequence) {
            System.out.println(number);
        }
    }
}
