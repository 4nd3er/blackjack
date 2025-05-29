package dealer;

public class NodoDealer {
    private String info;
    private NodoDealer izquierdo;
    private NodoDealer derecho;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public NodoDealer getIzquierdo() {
        return izquierdo;
    }

    public void setIzquierdo(NodoDealer izquierdo) {
        this.izquierdo = izquierdo;
    }

    public NodoDealer getDerecho() {
        return derecho;
    }

    public void setDerecho(NodoDealer derecho) {
        this.derecho = derecho;
    }
}
