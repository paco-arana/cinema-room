package cinema;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

public final class Cinema {

    private static Cinema instance;
    private final int total_rows;
    private final int total_columns;

    private List<Seat> available_seats = new ArrayList<>();
    private List<Seat> booked_seats = new ArrayList<>();

    // Private constructor
    private Cinema(int total_rows, int total_columns) {
        this.total_rows = total_rows;
        this.total_columns = total_columns;
        for (int i = 1; i <= total_rows; i++) {
            for (int j = 1; j <= total_columns; j++) {
                available_seats.add(new Seat(i, j));
            }
        }
    }

    //  Cinema is a singleton
    public static Cinema getInstance(int total_rows, int total_columns) {
        if (instance == null) {
            instance = new Cinema(total_rows, total_columns);
        }
        return instance;
    }

    public String getSeatsAsString() {
        StringBuilder all_seats = new StringBuilder();
        all_seats.append("[");
        for (Seat currentSeat : available_seats) {
            Map<String,Object> map = new HashMap<>();
            map.put("row", currentSeat.getRow());
            map.put("column", currentSeat.getColumn());
            map.put("price", currentSeat.getPrice());
            all_seats.append(map);
            all_seats.append(",");
        }
        all_seats.deleteCharAt(all_seats.length() - 1);
        all_seats.append("]");
        return all_seats.toString();
    }

    public List<Seat> getAvailable_seats() {
        return available_seats;
    }

    public Seat bookSeat(Seat seatToBuy) {
        for (Seat seatFound: available_seats) {
            if (seatFound.getRow() == seatToBuy.getRow()
                    && seatFound.getColumn() == seatToBuy.getColumn()) {
                booked_seats.add(seatFound);
                available_seats.remove(seatFound);
                return seatFound;
            }
        }
        return null;
    }

    public Seat returnTicket(UUID token) {
        for (Seat seatFound: booked_seats) {
            UUID compareToken = seatFound.getToken();
            if (compareToken.equals(token)) {
                System.out.println("Yes, it's here!");
                available_seats.add(seatFound);
                booked_seats.remove(seatFound);
                return seatFound;
            }
        }
        return null;
    }

    public void removeFromAvailableSeats(Seat seat) {
        this.booked_seats.add(seat);
        this.available_seats.remove(seat);
    }

    public String getRoom() {
        Map<String,Object> map = new HashMap<>();
        map.put("total_rows", total_rows);
        map.put("total_columns", total_columns);
        map.put("available_seats", this.getSeatsAsString());

        return map.toString();
    }

    public ResponseEntity<Object> statResponse(HttpStatus status) {
        int currentIncome = 0;
        for (Seat seat: booked_seats) {
            currentIncome += seat.getPrice();
        }

        int numAvailable = available_seats.size();
        int numPurchased = booked_seats.size();

        Map<String, Object> map = new HashMap<>();
        map.put("current_income", currentIncome);
        map.put("number_of_available_seats", numAvailable);
        map.put("number_of_purchased_tickets", numPurchased);

        return  new ResponseEntity<>(map, status);


    }

    /*
    public ResponseEntity<Object> returnResponse(HttpStatus status) {
        Map<String, Object> nestedMap = new HashMap<>();
        nestedMap.put("row", this.row);
        nestedMap.put("column", this.column);
        nestedMap.put("price", this.price);

        Map<String, Object> map = new HashMap<>();
        map.put("returned_ticket", nestedMap);

        return new ResponseEntity<Object>(map,status);
    }

    */

    public int getTotal_rows() {
        return total_rows;
    }

    public int getTotal_columns() {
        return total_columns;
    }
}
