package ru.netology.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ticket implements Comparable<Ticket> {
    private int id;
    private int price;
    private String from;
    private String to;
    private int travelTimeInMin;

    @Override
    public int compareTo(Ticket o) {
        return this.price - o.price;
    }

    public boolean matches(String departureAirport, String arrivalAirport) {
        if (from.contains(departureAirport) & to.contains(arrivalAirport)) {
            return true;
        }
        return false;
    }
}
