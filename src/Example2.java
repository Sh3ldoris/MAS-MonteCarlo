
import OSPRNG.TriangularRNG;
import OSPRNG.UniformDiscreteRNG;

import java.util.Random;

public class Example2 {

    public static void main(String[] args) {
        final int replication = 1000000;

        final int countA = 70;
        final int priceA = 3;

        final int countB = 90;
        final int priceB = 2;

        TriangularRNG costA = new TriangularRNG(1.0, 1.75,2.5);
        TriangularRNG costB = new TriangularRNG(0.7, 1.2,1.7);

        UniformDiscreteRNG demandA = new UniformDiscreteRNG(40, 79);
        UniformDiscreteRNG demandB = new UniformDiscreteRNG(66, 154);

        double profitA = 0;
        double profitB = 0;
        double seasonCostsA = 0;
        double seasonCostsB = 0;

        for (int i = 0; i < replication; i++) {

            seasonCostsA = 0;
            seasonCostsB = 0;

            for (int j = 0; j < countA; j++) {
                double cA = costA.sample();
                seasonCostsA += cA;
            }

            for (int j = 0; j < countB; j++) {
                double cB = costB.sample();
                seasonCostsB += cB;
            }


            int seasonDemandA = Math.min(demandA.sample(), countA);
            int seasonDemandB = Math.min(demandB.sample(), countB);

            double gainsA = seasonDemandA * priceA;
            double gainsB = seasonDemandB * priceB;

            profitA += (gainsA - seasonCostsA);
            profitB += (gainsB - seasonCostsB);
        }

        System.out.println("*****************************************************");
        System.out.println("-----------------------------------------------------");

        double resultA = profitA / replication;
        double resultB = profitB / replication;

        System.out.println("Odhadované zisky výrobku A: " + resultA);
        System.out.println("Odhadované zisky výrobku B: " + resultB);
    }
}
