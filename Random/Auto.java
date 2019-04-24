import java.util.*;
import java.io.*;

class Auto {
    public static void main(String[] args) {
        int i = 3, m = 5, M = 1;
        double alpha = 0.05;
        int length = 100;

        ArrayList<Double> randomNos = new ArrayList<>();

        Random rand = new Random();

        for ( int j = 0; j < length; j+=1 ) {
            randomNos.add(rand.nextDouble());  
        }

        M =  (int) Math.floor(( length - i ) / m ) - 1;

        Double rho = 0.0;

        for ( int j = 0; j <= M; j+=1) {
            rho += ( randomNos.get(i+j*m) *  randomNos.get(i+(j+1)*m) );
        }

        rho = ( 1/((double) M+1) * rho ) - 0.25;

        System.out.println("Rho: " + rho);

        Double sigma = Math.sqrt(13*M + 7) / (12 *( M + 1 ));

        System.out.println("Sigma: " + sigma);

        Double z = rho/sigma;

        System.out.println(z);
        if ( z < 1.96 && z > -1.96) {
            System.out.println("Hpyothesis of independence Not rejected");
        } else {
            System.out.println("Hpyothesis of independence rejected");
        }
    }
}