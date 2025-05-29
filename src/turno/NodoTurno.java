package turno;

public class NodoTurno {
    String nombre;
    NodoTurno siguiente;

    public NodoTurno(String nombre) {
        this.nombre = nombre;
        this.siguiente = null;
    }
}
