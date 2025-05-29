package turno;

public class ColaTurnos {
    private NodoTurno frente;
    private NodoTurno atras;

    public ColaTurnos() {
        this.frente = null;
        this.atras = null;
    }

    // Agregar un nuevo jugador al final de la cola.
    public void agregar(String nombre) {
        NodoTurno nuevo = new NodoTurno(nombre);
        if (frente == null) {
            frente = atras = nuevo;
        } else {
            atras.siguiente = nuevo;
            atras = nuevo;
        }
    }

    // Pasar al siguiente turno.
    // Devuelve el nombre del jugador que tenía el turno
    // y lo mueve al final de la cola.
    public String siguienteTurno() {
        if (frente == null)
            return null;

        String jugadorActual = frente.nombre;
        NodoTurno temporal = frente;
        frente = frente.siguiente;

        if (frente == null) {
            atras = temporal;
        } else {
            atras.siguiente = temporal;
            atras = temporal;
        }
        temporal.siguiente = null;

        return jugadorActual;
    }
}
