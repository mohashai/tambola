package com.sahajai.interview.tambola.models;

import com.sahajai.interview.tambola.constants.Constants;

public class RoundMetadata {
    private int ticketRows;
    private int ticketCols;
    private int minNumber;
    private int maxNumber;

    public RoundMetadata() {
        this.ticketRows = Constants.DEFAULT_TICKET_ROWS;
        this.ticketCols = Constants.DEFAULT_TICKET_COLS;
        this.minNumber = Constants.DEFAULT_TICKET_MIN_VALUE;
        this.maxNumber = Constants.DEFAULT_TICKET_MAX_VALUE;
    }

    public RoundMetadata(int ticketRows, int ticketCols, int minNumber, int maxNumber) {
        this.ticketRows = ticketRows;
        this.ticketCols = ticketCols;
        this.minNumber = minNumber;
        this.maxNumber = maxNumber;
    }

    public int getTicketRows() {
        return ticketRows;
    }

    public void setTicketRows(int ticketRows) {
        this.ticketRows = ticketRows;
    }

    public int getTicketCols() {
        return ticketCols;
    }

    public void setTicketCols(int ticketCols) {
        this.ticketCols = ticketCols;
    }

    public int getMinNumber() {
        return minNumber;
    }

    public void setMinNumber(int minNumber) {
        this.minNumber = minNumber;
    }

    public int getMaxNumber() {
        return maxNumber;
    }

    public void setMaxNumber(int maxNumber) {
        this.maxNumber = maxNumber;
    }

    public boolean isValid() {
        return (minNumber < maxNumber) && (ticketRows * ticketCols) < (maxNumber - minNumber + 1);
    }
}
