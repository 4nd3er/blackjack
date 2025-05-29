package cartas;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ListaCartas {
    private String[] palos = { "Picas", "Corazon", "Diamante", "Trebol" };
    private List<Carta> cartas = new ArrayList<>();
    Map<Integer, String> cartasLetra = new HashMap<>();

    public List<Carta> getCartas() {
        return cartas;
    }

    public void setCartas(List<Carta> cartas) {
        this.cartas = cartas;
    }

    public void mostrarCartas() {
        Iterator<Carta> it = cartas.iterator();
        while (it.hasNext()) {
            Carta carta = it.next();
            System.out.print("Palo: " + carta.getPalo());
            System.out.println(" Numero: " + carta.getNumero());
        }
    }

    public List<Carta> llenarListaCartas() {
        cartasLetra.put(1, "AS");
        cartasLetra.put(11, "J");
        cartasLetra.put(12, "Q");
        cartasLetra.put(13, "K");

        int contador = 1;
        int contadorPalo = 0;
        while (contadorPalo <= 3) {
            while (contador <= 13) {
                Carta carta = new Carta();
                String valor = cartasLetra.get(contador);
                carta.setNumero(valor != null ? valor : contador);
                carta.setPalo(palos[contadorPalo]);
                cartas.add(carta);
                contador++;
            }
            contador = 1;
            contadorPalo++;
        }
        return cartas;
    }
}
