package dealer;

public class Dealer {
    private NodoDealer raiz; // Nodo raíz del árbol de decisiones del dealer

    // Inicia el árbol vacío
    public Dealer() {
        this.raiz = null;
    }

    // Inserta la raíz del árbol (puntaje inicial)
    public void insertarRaiz(String info) {
        if (raiz == null) {
            NodoDealer nuevo = new NodoDealer();
            nuevo.setInfo(info);
            raiz = nuevo;
        } else {
            System.out.println("Ya existe una raiz");
        }
    }

    // Actualiza el puntaje en la raíz
    public void insertarPuntaje(String info) {
        if (raiz == null) {
            NodoDealer nuevo = new NodoDealer();
            nuevo.setInfo(info);
            raiz = nuevo;
        } else {
            raiz.setInfo(info);
        }
    }

    // Inserta un nodo a la izquierda del nodo dado
    public void insertarIzquierda(String info, NodoDealer nodo) {
        NodoDealer nuevo = new NodoDealer();
        nuevo.setInfo(info);
        if (raiz == null) {
            raiz = nuevo;
        } else {
            nodo.setIzquierdo(nuevo);
        }
    }

    // Inserta un nodo a la derecha del nodo dado
    public void insertarDerecha(String info, NodoDealer nodo) {
        NodoDealer nuevo = new NodoDealer();
        nuevo.setInfo(info);
        if (raiz == null) {
            raiz = nuevo;
        } else {
            nodo.setDerecho(nuevo);
        }
    }

    // Devuelve la raíz del árbol
    public NodoDealer retornarRaiz() {
        return raiz;
    }
}
