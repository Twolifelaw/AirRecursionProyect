package Cliente.modelo.objetos;

import java.io.Serializable;
import java.util.ArrayList;

public class Destino implements Serializable {

    private String pais;
    private String ciudad;
    private String descripcion;
    private String imagenes;
    private String clima;

    public Destino(String pais, String ciudad, String descripcion,String imagenes, String clima) {
        this.pais = pais;
        this.ciudad = ciudad;
        this.descripcion = descripcion;
        this.imagenes = imagenes;
        this.clima = clima;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }



    public String getClima() {
        return clima;
    }

    public void setClima(String clima) {
        this.clima = clima;
    }
}
