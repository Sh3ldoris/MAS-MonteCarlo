import OSPRNG.TriangularRNG;
import OSPRNG.UniformContinuousRNG;

public class Example9 {

    public static void main(String[] args) {
        final int replications = 100000;
        TriangularRNG dropPerGen = new TriangularRNG(1.0, 4.0, 11.0);
        UniformContinuousRNG passengerPerRaiseGen = new UniformContinuousRNG(5.0, 14.0);

        double bestPrice = Double.MAX_VALUE;
        double bestDay = 0;

        for (int i = 0; i < 7; i++) {
            double totalPrice = 0;

            for (int j = 0; j < replications; j++) {
                double price = 500;
                double capacity = 27;

                for (int k = 0; k < i; k++) {
                    capacity += passengerPerRaiseGen.sample();
                    if (capacity > 75) {
                        price = price * 1.3;
                        break;
                    } else {
                        price = price * (1 - (dropPerGen.sample() / 100));
                    }
                }

                totalPrice += price;
            }

            double averagePrice = totalPrice / replications;
            if (averagePrice < bestPrice) {
                bestPrice = averagePrice;
                bestDay = i;
            }
        }

        System.out.println("******************************************");
        System.out.println("Najlepsia cena je ---> " + bestPrice);
        System.out.println("najlepsi den je --->" + bestDay);
    }
}
