package de.tum.in.ase.studentdorm;

import java.util.ArrayList;
import java.util.List;

public class Stops {

    /**
     * This class basically acts like a wrapper class that holds two lists representing stops in upwards and downwards
     * direction.
     *
     */
    private List<Integer> stopsUp;
    private List<Integer> stopsDown;

    public Stops(List<Integer> stopsUp, List<Integer> stopsDown) {
        this.stopsUp = stopsUp;
        this.stopsDown = stopsDown;
    }

    public Stops() {
        stopsUp = new ArrayList<>();
        stopsDown = new ArrayList<>();
    }

    //TODO: add Error Handling to each method

    /**
     * This method takes a direction value and based on that value it checks if the corresponding list is empty.
     */
    public boolean isEmpty(Direction direction) throws IllegalArgumentException {
        if (direction == Direction.IDLE) {
            throw new IllegalArgumentException("Direction cannot be IDLE");
        } else if (direction == Direction.UP) {
            return stopsUp.isEmpty();
        } else {
            return stopsDown.isEmpty();
        }
    }

    /**
     *
     * This method removes a stop from one of the two lists. Which stop will be removed, is based on the direction and
     * int that are passed within in the parameters. The direction value determines the list from which to remove and
     * the int value determines the position within the list to remove from.
     *
     */
    public void remove(Direction direction, int i) throws IllegalArgumentException {
        if (direction == Direction.IDLE) {
            throw new IllegalArgumentException("Direction cannot be IDLE");
        } else if (i < 0) {
            throw new IllegalArgumentException("Index out of bounds");
        } else if (direction == Direction.UP) {
            if (!stopsUp.contains(i)) {
                throw new IllegalArgumentException("Index out of bounds");
            } else {
                stopsUp.remove(i);
            }
        } else {
            if (!stopsDown.contains(i)) {
                throw new IllegalArgumentException("Index out of bounds");
            } else {
                stopsDown.remove(i);
            }
        }
    }

    /**
     *
     * This method adds a stop to one of the two lists.The direction value determines which list to add to and the int
     * value represents the floor number which should be added.
     *
     */
    public void addStop(Direction direction, int i) throws IllegalArgumentException {
        if (direction == Direction.IDLE) {
            throw new IllegalArgumentException("Direction cannot be IDLE");
        } else if (i < 0) {
            throw new IllegalArgumentException("Index out of bounds");
        } else if (direction == Direction.UP) {
            stopsUp.add(i);
        } else {
            stopsDown.add(i);
        }
    }


    /**
     * This method takes a direction value as well as an int value. Based on the direction value the method chooses the
     * list to retrieve the value from and the int value acts as an index indicating the position within the list to get the value from.
     *
     * @return an int value representing a floor number
     */
    public int getNextStop(Direction direction, int i) throws IllegalArgumentException {
        if (direction == Direction.IDLE) {
            throw new IllegalArgumentException("Direction cannot be IDLE");
        } else if (i < 0) {
            throw new IllegalArgumentException("Index out of bounds");
        } else if (direction == Direction.UP) {
            if (!stopsUp.contains(i)) {
                throw new IllegalArgumentException("Index out of bounds");
            } else {
                return stopsUp.get(i);
            }
        } else {
            if (!stopsDown.contains(i)) {
                throw new IllegalArgumentException("Index out of bounds");
            } else {
                return stopsDown.get(i);
            }
        }
    }

    public List<Integer> getStopsUp() {
        return stopsUp;
    }

    public void setStopsUp(List<Integer> stopsUp) {
        this.stopsUp = stopsUp;
    }

    public List<Integer> getStopsDown() {
        return stopsDown;
    }

    public void setStopsDown(List<Integer> stopsDown) {
        this.stopsDown = stopsDown;
    }
}
