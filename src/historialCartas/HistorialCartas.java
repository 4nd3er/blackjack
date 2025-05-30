package historialCartas;

import cartas.Carta;

public class HistorialCartas {
    private Nodo inicio; // Puntero al inicio de la pila (última carta tomada)

    // Crea la pila vacía
    public HistorialCartas() {
        inicio = null;
    }

    // Verifica si la pila está vacía
    public boolean esVacia() {
        return inicio == null;
    }

    // Apila una nueva carta a la pila (LIFO)
    public void apilar(Carta carta) {
        Nodo nuevaCarta = new Nodo();
        nuevaCarta.setCarta(carta);
        if (esVacia()) {
            inicio = nuevaCarta;
        } else {
            nuevaCarta.setSigCarta(inicio);
            inicio = nuevaCarta;
        }
    }

    // Muestra todas las cartas del historial
    public void mostrarCartas() {
        if (esVacia()) {
            System.out.println("El historial esta vacio");
            return;
        } else {
            Nodo auxiliar = inicio;
            while (auxiliar != null) {
                System.out.print("Palo: " + auxiliar.getCarta().getPalo());
                System.out.println(" - Número: " + auxiliar.getCarta().getNumero());
                auxiliar = auxiliar.getSigCarta();
            }
        }
    }
}
