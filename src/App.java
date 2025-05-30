import java.util.Random;
import java.util.Scanner;
import baraja.MetodoBaraja;
import cartas.Carta;
import cartas.ListaCartas;
import dealer.Dealer;
import dealer.NodoDealer;
import historialCartas.HistorialCartas;
import jugadores.Jugador;
import jugadores.Jugadores;
import tablaHash.CartasLetra;
import turno.ColaTurnos;

public class App {

    // Constantes para acciones y estados del juego
    private static final String PEDIR = "Pedir carta";
    private static final String PLANTARSE = "Plantarse";
    private static final String ESTADOJUGANDO = "Jugando";
    private static final String ESTADOESPERANDO = "Esperando";
    private static final String ESTADOGANADO = "Ganó";
    private static final String ESTADOPERDIDO = "Perdió";

    public static void main(String[] args) throws Exception {
        // Inicialización de estructuras de datos necesarias
        ListaCartas listaCartas = new ListaCartas();
        MetodoBaraja baraja = new MetodoBaraja();
        HistorialCartas manoJugador = new HistorialCartas();
        HistorialCartas manoDealer = new HistorialCartas();
        CartasLetra cartasLetra = new CartasLetra();
        Jugadores jugadores = new Jugadores();

        // Se llena la lista de cartas estándar (52 cartas)
        listaCartas.llenarListaCartas();

        // Mezcla aleatoria de las cartas en la baraja
        llenarBaraja(baraja, listaCartas);

        Scanner scanner = new Scanner(System.in);

        System.out.println("********** Bienvenido a Blackjack **********");
        System.out.print("Ingrese su nombre: ");
        String nombreJugador = scanner.nextLine();

        // Cola que controla el orden de los turnos (jugador -> dealer)
        ColaTurnos cola = new ColaTurnos();
        cola.agregar(nombreJugador);
        cola.agregar("Dealer");

        // Árbol de decisión para el dealer
        Dealer dealerDecision = new Dealer();

        // Crear instancias de los jugadores
        Jugador jugador = new Jugador();
        Jugador dealer = new Jugador();
        jugador.setNombre(nombreJugador);
        dealer.setNombre("Dealer");

        jugadores.agregarJugador(nombreJugador, jugador);
        jugadores.agregarJugador("Dealer", dealer);

        // Inicializar árbol de decisiones del dealer
        dealerDecision.insertarRaiz("0");
        NodoDealer raiz = dealerDecision.retornarRaiz();
        dealerDecision.insertarIzquierda(PEDIR, raiz);
        dealerDecision.insertarDerecha(PLANTARSE, raiz);

        String accion;

        // Repartir 2 cartas iniciales a jugador y dealer
        for (int i = 0; i < 2; i++) {
            sumarPuntaje(cartasLetra, baraja, manoJugador, jugador);
            jugador.setHistorialCartas(manoJugador);

            sumarPuntaje(cartasLetra, baraja, manoDealer, dealer);
            dealer.setHistorialCartas(manoDealer);
        }

        boolean jugadorTermino = false;
        boolean dealerTermino = false;

        // Bucle principal del juego: se alternan los turnos hasta que ambos terminen
        while (!jugadorTermino || !dealerTermino) {
            // Condiciones por puntaje
            if (jugador.getPuntajeActual() > 21) {
                jugadorTermino = true;
                jugador.setEstadoJuego(ESTADOPERDIDO);
                break;
            } else if (dealer.getPuntajeActual() > 21) {
                dealerTermino = true;
                dealer.setEstadoJuego(ESTADOPERDIDO);
                break;
            } else if (jugador.getPuntajeActual() == 21) {
                jugador.setEstadoJuego(ESTADOGANADO);
                dealer.setEstadoJuego(ESTADOPERDIDO);
                break;
            } else if (dealer.getPuntajeActual() == 21) {
                dealer.setEstadoJuego(ESTADOGANADO);
                jugador.setEstadoJuego(ESTADOPERDIDO);
                break;
            }

            // Turno actual según la cola
            String turnoActual = cola.siguienteTurno();
            System.out.println("\nTurno de: " + turnoActual);

            // Turno del dealer
            if (turnoActual.equals("Dealer") && !dealerTermino) {
                dealer.setEstadoJuego(ESTADOJUGANDO);

                // Decisión del dealer usando el árbol: pide si tiene < 17, sino se planta
                accion = dealer.getPuntajeActual() >= 17
                        ? raiz.getDerecho().getInfo()
                        : raiz.getIzquierdo().getInfo();

                if (accion == PEDIR) {
                    System.out.println("Dealer decide tomar carta...");
                    sumarPuntaje(cartasLetra, baraja, manoDealer, dealer);
                    dealerDecision.insertarPuntaje(String.valueOf(dealer.getPuntajeActual()));

                    dealer.setEstadoJuego(ESTADOESPERANDO);
                    dealer.setHistorialCartas(manoDealer);
                    raiz.setInfo(String.valueOf(dealer.getPuntajeActual()));
                } else if (accion == PLANTARSE) {
                    System.out.println("Dealer decide plantarse");
                    dealerTermino = true;
                }

                // Turno del jugador
            } else if (turnoActual.equals(nombreJugador) && !jugadorTermino) {
                jugador.getHistorialCartas().mostrarCartas();
                System.out.println(jugador.getPuntajeActual());
                System.out.print("¿Desea otra carta? (s/n): ");
                String resp = scanner.nextLine().trim().toLowerCase();

                if (resp.equals("s")) {
                    sumarPuntaje(cartasLetra, baraja, manoJugador, jugador);
                    jugador.setEstadoJuego(ESTADOESPERANDO);
                    jugador.setHistorialCartas(manoJugador);
                } else {
                    jugadorTermino = true;
                    System.out.println(nombreJugador + " se planta.");
                }
            }

            // Reagregar a la cola si aún no ha terminado
            if (!jugadorTermino)
                cola.agregar(nombreJugador);
            if (!dealerTermino)
                cola.agregar("Dealer");
        }

        // Mostrar el resultado final del juego
        System.out.println("\n=== MANOS FINALES ===");
        jugadores.mostrarResultadoJugadores();

        // Comparación de puntajes para determinar al ganador
        if (jugador.getPuntajeActual() > dealer.getPuntajeActual() && jugador.getPuntajeActual() <= 21
                && jugador.getEstadoJuego() != ESTADOPERDIDO
                || dealer.getEstadoJuego() == ESTADOPERDIDO) {
            System.out.println("Jugador ha ganado!");
        } else if (jugador.getPuntajeActual() < dealer.getPuntajeActual() && dealer.getPuntajeActual() <= 21
                && dealer.getEstadoJuego() != ESTADOPERDIDO
                || jugador.getEstadoJuego() == ESTADOPERDIDO) {
            System.out.println("Dealer ha ganado!");
        } else if (jugador.getPuntajeActual() == dealer.getPuntajeActual()) {
            System.out.println("Hubo un empate");
        }

        scanner.close();
    }

    // Llena la baraja con cartas en orden aleatorio extrayendo desde una lista
    // enlazada de cartas.
    public static void llenarBaraja(MetodoBaraja barajaCartas, ListaCartas cartas) {
        int numeroCartas = 52;
        while (!cartas.esVacia() && numeroCartas > 0) {
            int numeroRandom = new Random().nextInt(numeroCartas); // Genera posición aleatoria
            Carta carta = cartas.buscar(numeroRandom); // Busca la carta en esa posición
            barajaCartas.insertarCartaInicio(carta); // Inserta al inicio de la baraja
            cartas.eliminar(numeroRandom); // Elimina de la lista original
            numeroCartas--;
        }
    }

    // Extrae una carta de la baraja y actualiza el puntaje del jugador
    public static void sumarPuntaje(CartasLetra cartasLetra, MetodoBaraja baraja,
            HistorialCartas mano, Jugador jugadorReciente) {
        Carta cartaTomada = baraja.tomarCarta();
        int numeroEsLetra = cartasLetra.getCartasLetra().retornarClave(cartaTomada.getNumero());

        // Convertir letras a su valor numérico (J, Q, K -> 10)
        numeroEsLetra = numeroEsLetra > 11 ? 10 : numeroEsLetra;

        // Si no es letra, se convierte directamente a entero
        int puntaje = numeroEsLetra != -1 ? numeroEsLetra : Integer.parseInt(cartaTomada.getNumero());

        int nuevoPuntaje = jugadorReciente.getPuntajeActual() + puntaje;
        mano.apilar(cartaTomada); // Guardar en historial de cartas
        jugadorReciente.setPuntajeActual(nuevoPuntaje); // Actualizar puntaje
    }
}
