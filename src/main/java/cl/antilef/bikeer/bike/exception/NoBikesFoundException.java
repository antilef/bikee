package cl.antilef.bikeer.bike.exception;

public class NoBikesFoundException extends RuntimeException{

    public NoBikesFoundException(String message){
        super(message);
    }
}
