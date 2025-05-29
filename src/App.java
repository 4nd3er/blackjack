import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import baraja.MetodoBaraja;
import cartas.Carta;
import cartas.ListaCartas;
import turno.ColaTurnos;

public class App {
    public static void main(String[] args) throws Exception {
        ListaCartas listaCartas = new ListaCartas();
        List<Carta> cartas = listaCartas.llenarListaCartas();
        MetodoBaraja baraja = new MetodoBaraja();
        barajar(baraja, cartas);

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("********** Bienvenido a Blackjack **********");
        System.out.print("Ingrese su nombre: ");
        String nombreJugador = scanner.nextLine();

        // Inicializar cola de turnos
        ColaTurnos cola = new ColaTurnos();
        cola.agregar(nombreJugador);
        cola.agregar("Dealer");

        List<Carta> manoJugador = new ArrayList<>();
        List<Carta> manoDealer = new ArrayList<>();

        // Repartir dos cartas iniciales a cada uno
        manoJugador.add(baraja.tomarCarta());
        manoJugador.add(baraja.tomarCarta());
        
        manoDealer.add(baraja.tomarCarta());
        manoDealer.add(baraja.tomarCarta());

        boolean jugadorTermino = false;
        boolean dealerTermino = false;

        // Alternar turnos usando la cola hasta que uno se plante
        while (!jugadorTermino && !dealerTermino) {
            String turnoActual = cola.siguienteTurno();
            System.out.println("\nTurno de: " + turnoActual);

            if (turnoActual.equals("Dealer")) {
                // Turno del dealer: decisión al azar
                mostrarMano("Dealer", manoDealer);
                boolean tomar = random.nextBoolean();
                if (tomar) {
                    System.out.println("Dealer decide tomar carta...");
                    manoDealer.add(baraja.tomarCarta());
                } else {
                    dealerTermino = true;
                    System.out.println("Dealer se planta.");
                }
            } else {
                // Turno del jugador
                mostrarMano(nombreJugador, manoJugador);
                System.out.print("¿Desea otra carta? (s/n): ");
                String resp = scanner.nextLine().trim().toLowerCase();
                if (resp.equals("s")) {
                    manoJugador.add(baraja.tomarCarta());
                } else {
                    jugadorTermino = true;
                    System.out.println(nombreJugador + " se planta.");
                }
            }

            // Volver a encolar si no se termina
            if (!jugadorTermino) cola.agregar(nombreJugador);
            if (!dealerTermino) cola.agregar("Dealer");
        }

        // Imprimir las manos de cada jugador
        System.out.println("\n=== MANOS FINALES ===");
        mostrarMano(nombreJugador, manoJugador);
        mostrarMano("Dealer", manoDealer);

        scanner.close();
    }

    private static void mostrarMano(String jugador, List<Carta> mano) {
        System.out.println(jugador + " tiene:");
        for (Carta c : mano) {
            System.out.println("  - " + c.getNumero() + " de " + c.getPalo());
        }
    }

    private static void barajar(MetodoBaraja baraja, List<Carta> cartas) {
        Random generadorAleatorio = new Random();
        int cartasRestantes = cartas.size();
        while (cartasRestantes > 0) {
            int indice = generadorAleatorio.nextInt(cartasRestantes);
            baraja.insertarCartaInicio(cartas.remove(indice));
            cartasRestantes--;
        }
    }
}
