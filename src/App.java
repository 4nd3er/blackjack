import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import baraja.MetodoBaraja;
import cartas.Carta;
import cartas.ListaCartas;

public class App {
    public static void main(String[] args) throws Exception {
        ListaCartas listaCartas = new ListaCartas();
        List<Carta> cartas = new ArrayList<>();
        MetodoBaraja barajaCartas = new MetodoBaraja();

        cartas = listaCartas.llenarListaCartas();

        llenarBaraja(barajaCartas, cartas);

        // listaCartas.mostrarCartas();
        barajaCartas.mostrarCartas();

        Carta cartaTomada = barajaCartas.tomarCarta();

        System.out.println("Carta Tomada: "  + cartaTomada.getNumero());

        barajaCartas.mostrarCartas();
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