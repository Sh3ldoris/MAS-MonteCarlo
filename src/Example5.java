import OSPRNG.TriangularRNG;
import OSPRNG.UniformContinuousRNG;

import java.util.Random;

public class Example5 {

    public static void main(String[] args) {
        final int replication = 10000000;
        double trueBorder = 0.8;

        boolean firstIsRight = false;
        boolean secondIsRight = false;
        boolean thirdIsRight = false;

        int bothIsRight = 0;
        int bothIsSame = 0;

        int threeSame = 0;
        int threeIsRight = 0;

        //Skusil som vytvorit 3 generatory ale aj tak je to len 68%
        UniformContinuousRNG random = new UniformContinuousRNG();
        UniformContinuousRNG random2 = new UniformContinuousRNG();
        UniformContinuousRNG random3 = new UniformContinuousRNG();
        TriangularRNG trueBoundaryGen = new TriangularRNG(0.3, 0.8, 1.0);
        TriangularRNG trueBoundaryGen2 = new TriangularRNG(0.3, 0.8, 1.0);
        TriangularRNG trueBoundaryGen3 = new TriangularRNG(0.3, 0.8, 1.0);

        for (int i = 0; i < replication; i++) {
            if (random.sample() < trueBoundaryGen.sample())
                firstIsRight = true;
            if (random2.sample() < trueBoundaryGen2.sample())
                secondIsRight = true;
            if (random3.sample() < trueBoundaryGen3.sample())
                thirdIsRight = true;

            if ((firstIsRight && secondIsRight) || (!firstIsRight && !secondIsRight)) {
                bothIsSame++;
                if (firstIsRight)
                    bothIsRight++;

            }

            if ((firstIsRight && secondIsRight && thirdIsRight) || (!firstIsRight && !secondIsRight && !thirdIsRight)) {
                threeSame++;
                if (firstIsRight)
                    threeIsRight++;
            }

            //Znova si nastavim hodnoty na false
            firstIsRight = false;
            secondIsRight = false;
            thirdIsRight = false;
        }

        System.out.println("*****************************************************");
        System.out.println("-----------------------------------------------------");

        double result1 = (double) bothIsRight / bothIsSame * 100;
        double result2 = (double) threeIsRight / threeSame * 100;

        System.out.println("Pravdepodobnost - obe maju pravdu: " + result1);
        System.out.println("Pravdepodobnost - tri maju pravdu: " + result2);
    }
}
