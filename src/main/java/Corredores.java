class Corredores extends Thread  {
    private final  int velocidad, turbo, agilidad;
    private int posicion = 1;
    private final char simbolo;
    public char[] pista;

    private Callback callback;

    @Override
    public void run() {
        do {
            if (tropezado())
                System.out.println("El corredor " + simbolo + " ha tropezado");
            else {
                pista[posicion] = ' ';
                if (turbo()) {
                    posicion += velocidad * 2;
                    System.out.println();
                }else
                    posicion += velocidad;
                if(posicion >= pista.length - 2) {
                    posicion = pista.length - 2;
                    System.out.println("El corredor " + simbolo + " ha ganado");
                    callback.termino();
                }
                pista[posicion] = simbolo;
            }
            callback.onCallback(this);
        } while (posicion < pista.length - 1 );

    }
    public synchronized void espera(){
        try {
            this.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private boolean tropezado() {
        return (Math.random() * 5) > (Math.random() * agilidad);
    }

    private boolean turbo() {
        return (Math.random() * 5) < (Math.random() * turbo);
    }

    public Corredores(int velocidad, int turbo, int agilidad, char simbolo) {
        this.velocidad = velocidad;
        this.turbo = turbo;
        this.agilidad = agilidad;
        this.simbolo = simbolo;
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    public int getPosicion() {
        return posicion;
    }

    public char getSimbolo() {
        return simbolo;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public void setPista(char[] pista) {
        this.pista = pista;
    }
    public interface Callback {
        void onCallback(Corredores corredor);
        void termino();
    }
}