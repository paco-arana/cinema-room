package cinema;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Seat {
    private int row;
    private int column;

    private int price;

    private UUID token;

    public Seat(){

    }

    public Seat(int row, int column) {
        this.row = row;
        this.column = column;
        this.price = this.row <= 4 ? 10 : 8;
        this.token = UUID.randomUUID();
    }

    public String asString() {
        Map<String,Object> map = new HashMap<>();
        map.put("row", row);
        map.put("column", column);
        map.put("price", price);
        return map.toString();
    }

    public ResponseEntity<Object> generateResponse(HttpStatus status) {
        Map<String, Object> nestedMap = new HashMap<>();
        nestedMap.put("row", this.row);
        nestedMap.put("column", this.column);
        nestedMap.put("price", this.price);

        Map<String, Object> map = new HashMap<>();
        map.put("ticket", nestedMap);
        map.put("token", this.token);

        return new ResponseEntity<Object>(map,status);
    }

    public ResponseEntity<Object> returnResponse(HttpStatus status) {
        Map<String, Object> nestedMap = new HashMap<>();
        nestedMap.put("row", this.row);
        nestedMap.put("column", this.column);
        nestedMap.put("price", this.price);

        Map<String, Object> map = new HashMap<>();
        map.put("returned_ticket", nestedMap);

        return new ResponseEntity<Object>(map,status);
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getPrice() {
        return price;
    }

    public UUID getToken() {
        return token;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
