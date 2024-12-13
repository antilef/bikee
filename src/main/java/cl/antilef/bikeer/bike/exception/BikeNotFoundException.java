package cl.antilef.bikeer.bike.exception;

public class BikeNotFoundException extends RuntimeException{

    public BikeNotFoundException(String message) {
        super(message);
    }
}
