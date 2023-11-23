package Cliente.modelo.objetos;

import java.io.Serializable;
import java.util.ArrayList;

public class Destino implements Serializable {

    private String pais;
    private String ciudad;
    private String descripcion;
    private String imagenes;
    private String clima;
    private String precio;
    private String id;
    private int numeroCupos;

    private int numLike;
    private int numDisLike;

    private ArrayList<String> comentarios;

    public Destino(String pais, String ciudad, String descripcion, String imagenes, String clima, String precio, String id, int numeroCupos, int numLike, int numDisLike,ArrayList<String> comentarios) {
        this.pais = pais;
        this.ciudad = ciudad;
        this.descripcion = descripcion;
        this.imagenes = imagenes;
        this.clima = clima;
        this.precio = precio;
        this.id = id;
        this.numeroCupos = numeroCupos;
        this.numLike = numLike;
        this.numDisLike = numDisLike;
        this.comentarios=comentarios;
    }

    public Destino() {
    }

    public ArrayList<String> getComentarios() {
        return comentarios;
    }

    public void setComentarios(ArrayList<String> comentarios) {
        this.comentarios = comentarios;
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

    public String getImagenes() {
        return imagenes;
    }

    public void setImagenes(String imagenes) {
        this.imagenes = imagenes;
    }

    public String getClima() {
        return clima;
    }

    public void setClima(String clima) {
        this.clima = clima;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getNumeroCupos() {
        return numeroCupos;
    }

    public void setNumeroCupos(int numeroCupos) {
        this.numeroCupos = numeroCupos;
    }

    public int getNumLike() {
        return numLike;
    }

    public void setNumLike(int numLike) {
        this.numLike = numLike;
    }

    public int getNumDisLike() {
        return numDisLike;
    }

    public void setNumDisLike(int numDisLike) {
        this.numDisLike = numDisLike;
    }

    @Override
    public String toString() {
        return "Destino{" +
                "pais='" + pais + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", imagenes='" + imagenes + '\'' +
                ", clima='" + clima + '\'' +
                ", precio='" + precio + '\'' +
                ", id='" + id + '\'' +
                ", numeroCupos=" + numeroCupos +
                '}';
    }
}
