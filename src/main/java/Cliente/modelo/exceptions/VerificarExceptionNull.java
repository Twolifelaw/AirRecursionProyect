package Cliente.modelo.exceptions;

public class VerificarExceptionNull extends NullPointerException {
    public VerificarExceptionNull(String mensaje) {
        System.out.println(mensaje);
    }
}
