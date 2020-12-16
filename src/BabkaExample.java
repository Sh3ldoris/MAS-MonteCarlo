import OSPRNG.TriangularRNG;
import OSPRNG.UniformContinuousRNG;

public class BabkaExample {

    public static void main(String[] args) {
        TriangularRNG priceGen = new TriangularRNG(0.5, 0.75, 1.0);
        TriangularRNG timeGen = new TriangularRNG(2.0, 6.0, 10.0);
        UniformContinuousRNG shortTimeGen = new UniformContinuousRNG(1.0, 3.0);

        final int replications = 1000000;
        double totalProfit = 0;
        double totalNotSold = 0;
        for (int i = 0; i < replications; i++) {
            int gingerBreadC = 100;
            double workTime = 480;
            double price = priceGen.sample();

            while (workTime > 0) {
                if (gingerBreadC == 0)
                    break;

                if (workTime <= 60 && gingerBreadC >= 10) {
                    workTime -= shortTimeGen.sample();
                    totalProfit += (price * 0.25);
                } else {
                    workTime -= timeGen.sample();
                    totalProfit += price;
                }
                gingerBreadC--;
            }

            totalNotSold += gingerBreadC;
        }

        System.out.println("************************************************");
        System.out.println("Babke priemerne ostane --> " + (double) totalNotSold / replications);
        System.out.println("Babka priemerne zarobi --> " + (double) totalProfit / replications);

    }
}
