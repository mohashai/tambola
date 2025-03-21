package com.sahajai.interview.tambola.models;

public class TicketNumber {
    private int number;
    private boolean isMarked;

    public TicketNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isMarked() {
        return isMarked;
    }

    public void setMarked(boolean marked) {
        isMarked = marked;
    }

    public void markNumber() {
        isMarked = true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TicketNumber)) return false;
        TicketNumber that = (TicketNumber) o;
        return number == that.number;
    }
}
