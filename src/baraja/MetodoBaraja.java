package baraja;

import cartas.Carta;

//Clase que representa la baraja del juego.
//Usa una lista circular enlazada.
public class MetodoBaraja {
    private Baraja cabecera; // Inicio de la lista

    // crea la baraja
    public MetodoBaraja() {
        cabecera = null;
    }

    // Verifica si la baraja está vacía
    public boolean esVacia() {
        return cabecera == null;
    }

    // Inserta una carta al inicio
    public void insertarCartaInicio(Carta carta) {
        Baraja nuevaCarta = new Baraja();
        nuevaCarta.setCarta(carta);

        if (esVacia()) {
            // Si la baraja está vacía, la carta se apunta a sí misma
            cabecera = nuevaCarta;
            nuevaCarta.setSigCarta(cabecera);
        } else {
            // Si no está vacía, se inserta antes de la cabecera
            Baraja auxiliar = cabecera;
            while (auxiliar.getSigCarta() != cabecera) {
                auxiliar = auxiliar.getSigCarta();
            }
            auxiliar.setSigCarta(nuevaCarta);
            nuevaCarta.setSigCarta(cabecera);
            cabecera = nuevaCarta;
        }
    }

    // Muestra todas las cartas de la baraja
    public void mostrarCartas() {
        if (cabecera == null) {
            System.out.println("La lista esta vacia");
            return;
        }

        Baraja auxiliar = cabecera;
        System.out.println("Comienza");

        // Recorre hasta volver al inicio
        do {
            System.out.print("Palo: " + auxiliar.getCarta().getPalo());
            System.out.println(" - Número: " + auxiliar.getCarta().getNumero());
            auxiliar = auxiliar.getSigCarta();
        } while (auxiliar != cabecera);
    }

    // Toma (y elimina) la carta del inicio de la baraja
    public Carta tomarCarta() {
        if (esVacia()) {
            System.out.println("La lista esta vacia");
            return null;
        }

        Baraja auxiliar = cabecera;
        Carta cartaTomada;

        // Busca el último nodo
        do {
            auxiliar = auxiliar.getSigCarta();
        } while (auxiliar.getSigCarta() != cabecera);

        // Reorganiza los enlaces para quitar la primera carta
        auxiliar.setSigCarta(cabecera.getSigCarta());
        cartaTomada = cabecera.getCarta();
        cabecera = cabecera.getSigCarta();

        // Si solo quedaba una carta, se vacía la lista
        if (auxiliar == cabecera) {
            cabecera = null;
        }

        return cartaTomada;
    }
}
