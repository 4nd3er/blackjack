package historialCartas;

import cartas.Carta;

public class HistorialCartas {
    private Nodo inicio;

    public HistorialCartas() {
        inicio = null;
    }

    public boolean esVacia() {
        return inicio == null;
    }

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
