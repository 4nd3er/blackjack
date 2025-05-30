package historialCartas;

import cartas.Carta;

public class Nodo {
    private Carta carta;
    private Nodo sigCarta;

    public Carta getCarta() {
        return carta;
    }

    public void setCarta(Carta carta) {
        this.carta = carta;
    }


    public Nodo getSigCarta() {
        return sigCarta;
    }

    public void setSigCarta(Nodo sigCarta) {
        this.sigCarta = sigCarta;
    }
}
