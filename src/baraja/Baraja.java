package baraja;
import cartas.Carta;

public class Baraja {
    private Carta carta;
    private Baraja sigCarta;

    public Baraja() {
        this.carta = null;
        this.sigCarta = null;
    }

    public Carta getCarta() {
        return carta;
    }

    public void setCarta(Carta carta) {
        this.carta = carta;
    }

    public Baraja getSigCarta() {
        return sigCarta;
    }

    public void setSigCarta(Baraja sigCarta) {
        this.sigCarta = sigCarta;
    }
}