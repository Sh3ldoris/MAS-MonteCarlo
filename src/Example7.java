import OSPRNG.TriangularRNG;

import java.math.BigInteger;

public class Example7 {

    public static void main(String[] args) {
        final int replications = 10000; //Zvolit nie vela replikacii pretoze potom pretecie integer pri lowercots
        TriangularRNG demandGen = new TriangularRNG(1000.0, 4000.0, 8500.0);

        int bestCount = 0;
        double lowestCosts = Double.MAX_VALUE;

        for (int i = 4000; i < 8500; i++) {
            int costs = 0;

            for (int j = 0; j < replications; j++) {
                int demand = (int) Math.round(demandGen.sample());

                if ((demand - i) < 0) {
                    costs += ((i - demand) * 5);
                } else
                    costs += ((demand - i) * 15);
            }

            double avrgCosts = (double) costs / replications;
            if (avrgCosts < lowestCosts) {
                bestCount = i;
                lowestCosts = avrgCosts;
            }
        }

        System.out.println("*********************************");
        System.out.println("Najlepsi pocet vakcin je ---> " + bestCount);
        System.out.println("Najnizsie dodatocne naklady su ---> " + lowestCosts);
    }
}
