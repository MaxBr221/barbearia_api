package github.MaxBr221.exception;

public class EventFullException extends RuntimeException{
    public EventFullException(){
        super("evento já está cheio");

    }
    public EventFullException(String messagem){
        super(messagem);
    }
}
