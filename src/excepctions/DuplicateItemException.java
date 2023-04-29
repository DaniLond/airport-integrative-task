package excepctions;

public class DuplicateItemException extends RuntimeException{
    public DuplicateItemException(){
        super("Duplicate item");
    }
}
