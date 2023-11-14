package Cliente.modelo.Serializacion;
import Cliente.modelo.objetos.Cliente;



public class SesionCliente {
    private static Cliente clienteAutenticado;

    public static Cliente getClienteAutenticado(){
        return  clienteAutenticado;
    }
    public static void setClienteAutenticado(Cliente cliente) {
        clienteAutenticado = cliente;
    }


}
