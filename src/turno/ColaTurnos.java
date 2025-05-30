package turno;

public class ColaTurnos {
    private NodoTurno frente;
    private NodoTurno atras;

    public ColaTurnos() {
        this.frente = null; // inicio de la cola
        this.atras = null; // fin de la cola
    }

    // Agrega un jugador al final de la cola
    public void agregar(String nombre) {
        NodoTurno nuevo = new NodoTurno(nombre);
        if (frente == null) { // si la cola está vacía
            frente = atras = nuevo; // nuevo es el único nodo
        } else {
            atras.siguiente = nuevo; // enlaza al final
            atras = nuevo; // actualiza el final
        }
    }

    // Mueve el jugador del frente al final y devuelve su nombre
    public String siguienteTurno() {
        if (frente == null) // cola vacía
            return null;

        String jugadorActual = frente.nombre;
        NodoTurno temporal = frente;
        frente = frente.siguiente; // mueve frente al siguiente nodo

        if (frente == null) {
            // solo había un nodo, queda el mismo como final
            atras = temporal;
        } else {
            atras.siguiente = temporal; // enlaza el nodo sacado al final
            atras = temporal; // actualiza final
        }
        temporal.siguiente = null; // marca el nuevo final

        return jugadorActual; // devuelve el jugador que tenía el turno
    }
}
