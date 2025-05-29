import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import baraja.MetodoBaraja;
import cartas.Carta;
import cartas.ListaCartas;
import dealer.Dealer;
import dealer.NodoDealer;
import historialCartas.HistorialCartas;

public class App {

    private static final String PEDIR = "Pedir carta";
    private static final String PLANTARSE = "Plantarse";
    public static void main(String[] args) throws Exception {
        ListaCartas listaCartas = new ListaCartas();
        List<Carta> cartas = new ArrayList<>();
        MetodoBaraja barajaCartas = new MetodoBaraja();
        HistorialCartas historialCartas = new HistorialCartas();

        cartas = listaCartas.llenarListaCartas();

        llenarBaraja(barajaCartas, cartas);

        // listaCartas.mostrarCartas();
        barajaCartas.mostrarCartas();

        Carta cartaTomada = barajaCartas.tomarCarta();
        historialCartas.apilar(cartaTomada);
        
        cartaTomada = barajaCartas.tomarCarta();
        historialCartas.apilar(cartaTomada);

        cartaTomada = barajaCartas.tomarCarta();
        historialCartas.apilar(cartaTomada);

        System.out.println("\n" + "Cartas Tomadas: ");

        historialCartas.mostrarCartas();

        barajaCartas.mostrarCartas();

        Dealer dealer = new Dealer();

        dealer.insertarRaiz("0");
        NodoDealer raiz = dealer.retornarRaiz();

        dealer.insertarIzquierda(PEDIR, raiz);
        dealer.insertarDerecha(PLANTARSE, raiz);
        Scanner scanner = new Scanner(System.in);

        String accion;

        while (true) {
            System.out.println("Numero");
            String numero = scanner.next();
            dealer.insertarPuntaje(numero);
            raiz = dealer.retornarRaiz();
            accion =  Integer.parseInt(raiz.getInfo()) >= 17? raiz.getDerecho().getInfo() : raiz.getIzquierdo().getInfo();

            if (accion == PEDIR) {
                barajaCartas.tomarCarta();
            } else if (accion == PLANTARSE) {
                break;
            }
        }
    }

    public static void llenarBaraja(MetodoBaraja barajaCartas, List<Carta> cartas) {
        int numeroCartas = 52;
        while (!cartas.isEmpty()) {
            int numeroRandom = new Random().nextInt(numeroCartas);
            barajaCartas.insertarCartaInicio(cartas.get(numeroRandom));
            cartas.remove(numeroRandom);
            numeroCartas--;
        }
    }
}