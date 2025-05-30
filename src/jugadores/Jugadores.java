package jugadores;

public class Jugadores {
    private static final int TAM = 10; // Tamaño de la tabla hash

    // Nodo de la lista para manejar colisiones
    static class Nodo {
        String clave;
        Jugador jugador;
        Nodo sigJugador;

        public Nodo(String clave, Jugador jugador) {
            this.clave = clave;
            this.jugador = jugador;
            this.sigJugador = null;
        }
    }

    private Nodo[] tabla = new Nodo[TAM]; // Tabla hash

    // Función hash sencilla: suma los caracteres de la clave
    private int hash(String clave) {
        int h = 0;
        for (char c : clave.toCharArray()) {
            h += c;
        }
        return h % TAM;
    }

    // Agrega un jugador nuevo o actualiza si ya existe
    public void agregarJugador(String clave, Jugador jugador) {
        int pos = hash(clave);
        Nodo auxiliar = tabla[pos];

        while (auxiliar != null) {
            if (auxiliar.clave.equals(clave)) {
                auxiliar.jugador = jugador;
                // Actualizar jugador existente
                return;
            }
            auxiliar = auxiliar.sigJugador;
        }

        // Insertar al principio de la lista en la posición correspondiente
        Nodo nuevo = new Nodo(clave, jugador);
        nuevo.sigJugador = tabla[pos];
        tabla[pos] = nuevo;
    }

    // Devuelve el jugador con la clave dada
    public Jugador obtenerJugador(String clave) {
        int pos = hash(clave);
        Nodo auxiliar = tabla[pos];
        while (auxiliar != null) {
            if (auxiliar.clave.equals(clave))
                return auxiliar.jugador;
            auxiliar = auxiliar.sigJugador;
        }
        return null;
    }

    // Elimina un jugador según la clave
    public void eliminarJugador(String clave) {
        int pos = hash(clave);
        Nodo auxiliar = tabla[pos];
        Nodo anterior = null;

        while (auxiliar != null) {
            if (auxiliar.clave.equals(clave)) {
                if (anterior == null)
                    tabla[pos] = auxiliar.sigJugador;
                // Es el primero en la lista
                else
                    anterior.sigJugador = auxiliar.sigJugador;
                // Borrar nodo del medio o final
                return;
            }
            anterior = auxiliar;
            auxiliar = auxiliar.sigJugador;
        }
    }

    // Muestra los nombres de todos los jugadores
    public void mostrarJugadores() {
        for (int i = 0; i < TAM; i++) {
            Nodo auxiliar = tabla[i];
            while (auxiliar != null) {
                System.out.println(auxiliar.jugador.getNombre());
                auxiliar = auxiliar.sigJugador;
            }
        }
    }

    // Muestra los resultados (historial de cartas y puntaje) de cada jugador
    public void mostrarResultadoJugadores() {
        for (int i = 0; i < TAM; i++) {
            Nodo auxiliar = tabla[i];
            while (auxiliar != null) {
                System.out.println(auxiliar.jugador.getNombre() + " tiene:");
                auxiliar.jugador.getHistorialCartas().mostrarCartas();
                System.out.print("Puntaje Final: " + auxiliar.jugador.getPuntajeActual());
                System.out.println("\n");
                auxiliar = auxiliar.sigJugador;
            }
        }
    }
}
