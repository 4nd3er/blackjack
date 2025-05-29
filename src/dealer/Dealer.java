package dealer;

public class Dealer {
    private NodoDealer raiz;

    public Dealer() {
        this.raiz = null;
    }

    public void insertarRaiz(String info) {
        if (raiz == null) {
            NodoDealer nuevo = new NodoDealer();
            nuevo.setInfo(info);
            raiz = nuevo;
        } else {
            System.out.println("Ya existe una raiz");
        }
    }
    
    public void insertarPuntaje(String info) {
        if (raiz == null) {
            NodoDealer nuevo = new NodoDealer();
            nuevo.setInfo(info);
            raiz = nuevo;
        } else {
            raiz.setInfo(info);
        }
    }

    public void insertarIzquierda(String info, NodoDealer nodo) {
        NodoDealer nuevo = new NodoDealer();
        nuevo.setInfo(info);
        if (raiz == null) {
            raiz = nuevo;
        } else {
            nodo.setIzquierdo(nuevo);
        }
    }

    public void insertarDerecha(String info, NodoDealer nodo) {
        NodoDealer nuevo = new NodoDealer();
        nuevo.setInfo(info);
        if (raiz == null) {
            raiz = nuevo;
        } else {
            nodo.setDerecho(nuevo);
        }
    }

    public NodoDealer retornarRaiz() {
        return raiz;
    }
}
