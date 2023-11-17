package Cliente.modelo.exceptions;

public class CargarImagenException extends  RuntimeException{
    public CargarImagenException(String mensaje){
        super(mensaje);
    }

    public CargarImagenException(String mensaje, Throwable causa){
        super(mensaje,causa);
    }
}
