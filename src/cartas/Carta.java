package cartas;

public class Carta {
    private String palo;
    private String numero;
    private Carta sigCarta;

    public String getPalo() {
        return palo;
    }

    public void setPalo(String palo) {
        this.palo = palo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Carta getSigCarta() {
        return sigCarta;
    }

    public void setSigCarta(Carta sigCarta) {
        this.sigCarta = sigCarta;
    }
}
