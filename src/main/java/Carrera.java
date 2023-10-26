import javax.security.auth.callback.Callback;
import java.lang.reflect.Array;
import java.util.Arrays;

public class Carrera implements Corredores.Callback {
    private char[][] pista = new char[7][50];
    private Corredores[] corredores;

    public Carrera(Corredores[] corredores) {
        Arrays.fill(pista[0], '-');
        Arrays.fill(pista[1], ' ');
        Arrays.fill(pista[2], ' ');
        Arrays.fill(pista[3], ' ');
        Arrays.fill(pista[4], ' ');
        Arrays.fill(pista[5], ' ');
        Arrays.fill(pista[6], '-');
        this.corredores = corredores;
        int i = 1;
        int leng = pista[0].length - 1;
        for (Corredores corredor : corredores) {
            pista[i][0] = '/';
            pista[i][1] = corredor.getSimbolo();
            pista[i][leng] = '/';
            corredor.pista = pista[i];
            corredor.setCallback(this);
            i++;
        }

    }

    public void start() {
        corredores[0].start();
    }

    public synchronized void muestraPista() {
        for (char[] chars : pista) {
            for (char aChar : chars) {
                System.out.print(aChar);
            }
            System.out.println();
        }
    }

    @Override
    public synchronized void onCallback(Corredores corredor) {
        muestraPista();
        corredor.espera();
        notifyAll();
    }

    @Override
    public synchronized void termino() {
        for (Corredores corredor : corredores) {
            corredor.interrupt();
        }
        System.out.println("Termino");
    }

    public static void main(String[] args) {
        try {
            new Carrera(new Corredores[]{new Corredores(2, 3, 5, '@'), new Corredores(2, 3, 5, '?'), new Corredores(2, 3, 5, '#'), new Corredores(2, 3, 5, '&'), new Corredores(2, 3, 5, 'J')}).start();
        }catch (Exception e){
            System.out.println("Termino");
        }
    }
}
