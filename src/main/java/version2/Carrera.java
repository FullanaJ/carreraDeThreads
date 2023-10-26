package version2;

import java.util.Arrays;

public class Carrera  {
    private char[][] pista = new char[7][50];
    private String podio = "";
    public Carrera(Corredores[] corredores) {
        Arrays.fill(pista[0], '-');
        Arrays.fill(pista[1], ' ');
        Arrays.fill(pista[2], ' ');
        Arrays.fill(pista[3], ' ');
        Arrays.fill(pista[4], ' ');
        Arrays.fill(pista[5], ' ');
        Arrays.fill(pista[6], '-');
        int i = 1;
        int leng = pista[0].length - 1;
        for (Corredores corredor : corredores) {
            pista[i][0] = '/';
            pista[i][1] = corredor.getSimbolo();
            corredor.setCarrera(this);
            corredor.pista = pista[i];
            pista[i][leng] = '/';
            corredor.start();
            i++;
        }
        for (Corredores corredor : corredores) {
            try {
                corredor.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("La carrera ha terminado");
        System.out.println(podio);
    }
    public synchronized void movimiento(Corredores corredor) throws InterruptedException {
        if(corredor.movimiento()) {
            muestraPista();
            corredor.setGanador(true);
            Corredores.setPodio(Corredores.getPodio() + 1);
        }else {
            muestraPista();
        }
    }
    public void guardaPodio(String podio) {
        this.podio = this.podio+podio;
    }
    public synchronized void muestraPista() {
        for (char[] chars : pista) {
            for (char aChar : chars) {
                System.out.print(aChar);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {

        new Carrera(new Corredores[]{new Corredores(2, 3, 5, '@'), new Corredores(2, 3, 5, '?'), new Corredores(2, 3, 5, '#'), new Corredores(2, 3, 5, '&'), new Corredores(2, 3, 5, 'J')});

    }
}
