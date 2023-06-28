package cinema;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@SpringBootApplication
@RestController
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    Cinema cinema = Cinema.getInstance(9, 9);
    @GetMapping("/seats")
    public ResponseEntity<String> getSeats() {
        return ResponseEntity.ok(cinema.getRoom());
    }

    @PostMapping("/purchase")
    public ResponseEntity<Object> buySeat(@RequestBody Seat seatToBuy) {
        if (seatToBuy.getRow() > cinema.getTotal_rows()
                || seatToBuy.getRow() < 1
                || seatToBuy.getColumn() > cinema.getTotal_columns()
                || seatToBuy.getColumn() < 1){
            return CustomResponse.generateResponse("The number of a row or a column is out of bounds!", HttpStatus.BAD_REQUEST);
        }
        Seat seatFound = cinema.bookSeat(seatToBuy);
        if (seatFound != null) {
            return seatFound.generateResponse(HttpStatus.OK);
        } else {

            return CustomResponse.generateResponse("The ticket has been already purchased!", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/return")
    public ResponseEntity<Object> returnSeat(@RequestBody Seat seat) {
        UUID token = seat.getToken();
        System.out.println(token);
        Seat seatFound = cinema.returnTicket(token);
        if (seatFound != null) {
            return seatFound.returnResponse(HttpStatus.OK);
        } else {
            return CustomResponse.generateResponse("Wrong token!", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/stats")
    public ResponseEntity<Object> getStats(@RequestParam(value = "password", defaultValue = "none", required = false) String password) {
        if (password.equals("super_secret")) {
            return cinema.statResponse(HttpStatus.OK);
        } else {
            return CustomResponse.generateResponse("The password is wrong!", HttpStatus.UNAUTHORIZED);
        }

    }
}
