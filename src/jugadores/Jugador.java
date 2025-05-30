package jugadores;

import historialCartas.HistorialCartas;

public class Jugador {
    private String nombre;
    private HistorialCartas historialCartas;
    private int puntajeActual;
    private String estadoJuego;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public HistorialCartas getHistorialCartas() {
        return historialCartas;
    }

    public void setHistorialCartas(HistorialCartas historialCartas) {
        this.historialCartas = historialCartas;
    }

    public int getPuntajeActual() {
        return puntajeActual;
    }

    public void setPuntajeActual(int puntajeActual) {
        this.puntajeActual = puntajeActual;
    }
    
    public String getEstadoJuego() {
        return estadoJuego;
    }

    public void setEstadoJuego(String estadoJuego) {
        this.estadoJuego = estadoJuego;
    }
}