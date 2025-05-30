package tablaHash;

public class CartasLetra {
    // Tabla hash para guardar las cartas con letra (AS, J, Q, K)
    TablaHash cartasLetra = new TablaHash(14);

    public CartasLetra() {
        // Agrega las cartas especiales con sus números correspondientes
        cartasLetra.agregar(1, "AS", cartasLetra.retornarArreglo());
        cartasLetra.agregar(11, "J", cartasLetra.retornarArreglo());
        cartasLetra.agregar(12, "Q", cartasLetra.retornarArreglo());
        cartasLetra.agregar(13, "K", cartasLetra.retornarArreglo());
    }

    // Devuelve la tabla hash con las cartas especiales
    public TablaHash getCartasLetra() {
        return cartasLetra;
    }
}
