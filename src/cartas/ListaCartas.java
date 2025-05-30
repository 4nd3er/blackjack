package cartas;

import tablaHash.CartasLetra;

public class ListaCartas {
    private Carta cabecera; // Inicio de la lista circular
    private String[] palos = { "Picas", "Corazon", "Diamante", "Trebol" };
    CartasLetra cartasLetra = new CartasLetra();

    // Inicia la lista vacía
    public ListaCartas() {
        cabecera = null;
    }

    // Verifica si la lista está vacía
    public boolean esVacia() {
        return cabecera == null;
    }

    // Inserta una carta al inicio de la lista
    public void insertarCartaInicio(Carta carta) {
        if (esVacia()) {
            cabecera = carta;
            carta.setSigCarta(cabecera); // Se apunta a sí misma
        } else {
            Carta auxiliar = cabecera;
            while (auxiliar.getSigCarta() != cabecera) {
                auxiliar = auxiliar.getSigCarta();
            }
            auxiliar.setSigCarta(carta);
            carta.setSigCarta(cabecera);
            cabecera = carta;
        }
    }

    // Muestra todas las cartas de la lista
    public void mostrarCartas() {
        if (cabecera == null) {
            System.out.println("La lista esta vacia");
            return;
        }
        Carta auxiliar = cabecera;
        System.out.println("Comienza");
        do {
            System.out.print("Palo: " + auxiliar.getPalo());
            System.out.println(" - Número: " + auxiliar.getNumero());
            auxiliar = auxiliar.getSigCarta();
        } while (auxiliar != cabecera);
    }

    // Busca una carta por su posición
    public Carta buscar(int posicion) {
        if (esVacia() || posicion < 0)
            return null;

        Carta aux = cabecera;
        int contador = 0;

        do {
            if (contador == posicion)
                return aux;
            aux = aux.getSigCarta();
            contador++;
        } while (aux != cabecera);

        return null;
    }

    // Elimina una carta por su posición
    public void eliminar(int posicion) {
        if (esVacia() || posicion < 0)
            return;

        Carta actual = cabecera;
        Carta anterior = null;
        int contador = 0;

        // Eliminar la cabecera
        if (posicion == 0) {
            if (cabecera.getSigCarta() == cabecera) {
                cabecera = null;
            } else {
                // Encontrar la última carta
                Carta ultimo = cabecera;
                while (ultimo.getSigCarta() != cabecera) {
                    ultimo = ultimo.getSigCarta();
                }
                cabecera = cabecera.getSigCarta();
                ultimo.setSigCarta(cabecera);
            }
            return;
        }

        // Buscar la carta a eliminar
        do {
            anterior = actual;
            actual = actual.getSigCarta();
            contador++;
        } while (actual != cabecera && contador < posicion);

        if (contador == posicion) {
            anterior.setSigCarta(actual.getSigCarta());
        }
    }

    // Llena la lista con las 52 cartas (13 por cada palo)
    public void llenarListaCartas() {
        int contador = 1;
        int contadorPalo = 0;

        // Recorre los 4 palos
        while (contadorPalo <= 3) {
            // Agrega 13 cartas por palo
            while (contador <= 13) {
                Carta carta = new Carta();
                String valor = cartasLetra.getCartasLetra().retornarValor(contador);
                String numeroCarta = String.valueOf(contador);

                // Si la carta es letra (J, Q, K, A), usa el valor
                carta.setNumero(valor != null ? valor : numeroCarta);
                carta.setPalo(palos[contadorPalo]);

                insertarCartaInicio(carta);
                contador++;
            }
            contador = 1;
            contadorPalo++;
        }
    }
}
