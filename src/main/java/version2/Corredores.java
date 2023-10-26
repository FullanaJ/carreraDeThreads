package version2;

import java.util.function.Consumer;

class Corredores extends Thread {
    private final int velocidad, turbo, agilidad;
    private int posicion = 1;
    private static int podio = 1;
    public char[] pista;
    private final char simbolo;
    private Carrera carrera;
    private boolean ganador = false;

    @Override
    public void run() {
        do {
            try {
                carrera.movimiento(this);
                sleep(501);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (!ganador);
    }

    public boolean movimiento() {

        if (tropezado())
            System.out.println("El corredor " + simbolo + " ha tropezado");
        else {
            pista[posicion] = ' ';
            if (turbo()) {
                posicion += velocidad * 2;
                System.out.println();
            } else
                posicion += velocidad;
            if (posicion >= pista.length - 2) {
                posicion = pista.length - 2;
                carrera.guardaPodio("El corredor " + simbolo + " terminó en el puesto " + podio+"\n");
                pista[posicion] = simbolo;
                return true;
            }
        }
        pista[posicion] = simbolo;
        return false;
    }

    private boolean tropezado() {
        return (Math.random() * 5) > (Math.random() * agilidad);
    }

    private boolean turbo() {
        return (Math.random() * 5) < (Math.random() * turbo);
    }

    public Corredores(int velocidad, int turbo, int agilidad, char simbolo) {
        if (velocidad < 1 || turbo < 1 || agilidad < 1)
            throw new IllegalArgumentException("Los valores deben ser mayores que 0");
        else if (velocidad + turbo +  agilidad > 10)
            throw new IllegalArgumentException("La suma de los valores debe ser menor o igual que 10");
        this.velocidad = velocidad;
        this.turbo = turbo;
        this.agilidad = agilidad;
        this.simbolo = simbolo;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    public void setPista(char[] pista) {
        this.pista = pista;
    }

    public int getPosicion() {
        return posicion;
    }

    public char getSimbolo() {
        return simbolo;
    }

    public  void setGanador(boolean ganador) {
         this.ganador= ganador;
    }

    public static void setPodio(int podio) {
        Corredores.podio = podio;
    }

    public static int getPodio() {
        return podio;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

}