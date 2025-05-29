package baraja;

import cartas.Carta;

public class MetodoBaraja {
    private Baraja cabecera;

    public MetodoBaraja() {
        cabecera = null;
    }

    public boolean esVacia() {
        return cabecera == null;
    }

    public void insertarCartaInicio(Carta carta) {
        Baraja nuevaCarta = new Baraja();
        nuevaCarta.setCarta(carta);
        if (esVacia()) {
            cabecera = nuevaCarta;
            nuevaCarta.setSigCarta(cabecera);
        } else {
            Baraja auxiliar = cabecera;
            while (auxiliar.getSigCarta() != cabecera) {
                auxiliar = auxiliar.getSigCarta();
            }
            auxiliar.setSigCarta(nuevaCarta);
            nuevaCarta.setSigCarta(cabecera);
            cabecera = nuevaCarta;
        }
    }

    public void mostrarCartas() {
        if (cabecera == null) {
            System.out.println("La lista esta vacia");
            return;
        }
        Baraja auxiliar = cabecera;
        System.out.println("Comienza");
        do {
            System.out.print("Palo: " + auxiliar.getCarta().getPalo());
            System.out.println(" - Número: " + auxiliar.getCarta().getNumero());
            auxiliar = auxiliar.getSigCarta();
        } while (auxiliar != cabecera);
    }

    public Carta tomarCarta() {
        if (esVacia()) {
            System.out.println("La lista esta vacia");
            return null;
        }
        Baraja auxiliar = cabecera;
        Carta cartaTomada;
        do {
            auxiliar = auxiliar.getSigCarta();
        } while (auxiliar.getSigCarta() != cabecera);
        auxiliar.setSigCarta(cabecera.getSigCarta());
        cartaTomada = cabecera.getCarta();
        cabecera = cabecera.getSigCarta();
        if (auxiliar == cabecera) {
            cabecera = null;
        }
        return cartaTomada;
    }
}
