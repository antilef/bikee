package cl.antilef.bikeer.rent.exception;

public class RentNotExistException extends RuntimeException{
    public RentNotExistException(String message){
        super(message);
    }
}
