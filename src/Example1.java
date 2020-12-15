import OSPRNG.TriangularRNG;
import OSPRNG.UniformContinuousRNG;

public class Example1 {

    public static void main(String[] args) {
        TriangularRNG priceGen = new TriangularRNG(0.25, 0.6, 0.95);
        UniformContinuousRNG workTimeGen = new UniformContinuousRNG(250.0, 420.0);
        int replications = 1000000;

        int maxPackages = 16;
        double bestProfit = 0;
        int bestPackagesCount = 0;

        final double buyPrice = 0.15;
        final double returnPrice = 0.15 * 0.65;

        for (int cPackages = 1; cPackages < maxPackages; cPackages++) {
            double profit = 0;

            for (int i = 0; i < replications; i++) {
                int boughtPapers = cPackages * 10;
                double canSell = Math.ceil(workTimeGen.sample() / 2.7);
                double cSold = Math.min(canSell, boughtPapers);
                double expense = boughtPapers * buyPrice;
                profit += ((cSold * priceGen.sample()) + ((boughtPapers - cSold) * returnPrice) - expense);
            }

            profit /= replications;
            if (profit > bestProfit) {
                bestProfit = profit;
                bestPackagesCount = cPackages;
            }

        }

        System.out.println("Suboptimalnz pocet balikov je: " + bestPackagesCount);
        System.out.println("Zisk je: " + bestProfit);
    }
}
