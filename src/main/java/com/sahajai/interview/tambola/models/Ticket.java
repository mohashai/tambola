package com.sahajai.interview.tambola.models;

import com.sahajai.interview.tambola.utils.RandomUtils;

import java.util.*;
import java.util.stream.Collectors;

public class Ticket {
    private final String id;
    private final TicketNumber[][] grid;
    private final Map<Integer, TicketNumber> ticketNumbers;
    private int lastMarkedNumber;

    public Ticket(String id) {
        this(id, new RoundMetadata());
    }

    public Ticket(String id, TicketNumber[][] grid) {
        this.id = id;
        this.grid = grid;
        this.ticketNumbers = new HashMap<>();
        populateTicketNumbers(grid);
    }



    public Ticket(String id, RoundMetadata metadata) {
        this.id = id;
        this.grid = new TicketNumber[metadata.getTicketRows()][metadata.getTicketCols()];
        this.ticketNumbers = new HashMap<>();
        fillNumbers(metadata.getTicketRows(), metadata.getTicketCols(), metadata.getMinNumber(), metadata.getMaxNumber());
    }

    private void fillNumbers(int rows, int columns, int minNumber, int maxNumber) {
        List<Integer> randomNumbers = RandomUtils.getNRandomNumbersInRange(rows * columns, minNumber, maxNumber);
        int index = 0;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                int num = randomNumbers.get(index++);
                TicketNumber ticketNumber = new TicketNumber(num);
                grid[row][col] = ticketNumber;
                ticketNumbers.put(num, ticketNumber);
            }
        }
    }

    public String getId() {
        return id;
    }

    public void markNumber(int num) {
        if (ticketNumbers.containsKey(num)) {
            ticketNumbers.get(num).markNumber();
            lastMarkedNumber = num;
        }
    }

    public int getLastMarkedNumber() {
        return lastMarkedNumber;
    }

    public boolean isNumberMarked(int num) {
        if (ticketNumbers.containsKey(num)) {
            return ticketNumbers.get(num).isMarked();
        } else {
            return false;
        }
    }

    public TicketNumber[][] getGrid() {
        return grid;
    }

    public Set<Integer> getMarkedNumbers() {
        return ticketNumbers.entrySet().stream()
                .filter(e -> e.getValue().isMarked())
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
    }

    public Set<Integer> getTicketNumbers() {
        return ticketNumbers.keySet();
    }

    public void printTicket() {
        for (TicketNumber[] row : grid) {
            for (TicketNumber tNum : row) {
                System.out.print(String.format("%2d", tNum.getNumber()) + "  ");
            }
            System.out.println();
        }
    }

    private void populateTicketNumbers(TicketNumber[][] grid) {
        for (TicketNumber[] rows: grid) {
            for(TicketNumber tNum: rows) {
                ticketNumbers.put(tNum.getNumber(), tNum);
            }
        }
    }
}

