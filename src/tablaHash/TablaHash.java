package tablaHash;

public class TablaHash {
    String[] arreglo;
    int longitud;

    public TablaHash(int longi) {
        longitud = longi;
        arreglo = new String[longi];
    }

    public void agregar(int indice, String elemento, String[] arreglo) {
        arreglo[indice] = elemento;
    }

    public String[] retornarArreglo() {
        return arreglo;
    }

    public int retornarClave(String elemento) {
        int indice = 0;
        while (indice < longitud) {
            if (arreglo[indice] == elemento) {
                return indice;
            } else {
                indice++;
            }
        }
        return -1;
    }

    public String retornarValor(int indice) {
        if (indice > longitud) {
            return null;
        }
        return arreglo[indice];
    }
}
