import OSPRNG.TriangularRNG;
import OSPRNG.UniformContinuousRNG;

public class BabkaExample {

    public static void main(String[] args) {
        TriangularRNG priceGen = new TriangularRNG(0.5, 0.75, 1.0);
        TriangularRNG timeGen = new TriangularRNG(2.0, 6.0, 10.0);
        UniformContinuousRNG shortTimeGen = new UniformContinuousRNG(1.0, 3.0);

        final int replications = 1000000;
        double totalProfit = 0;
        int totalNotSold = 0;

        for (int i = 0; i < replications; i++) {
            int gingerBreadC = 100;
            double workTime = 480;
            double price = priceGen.sample();
            boolean fastMode = false;

            while (workTime > 0) {
                double timeBetween = 0;
                if (gingerBreadC == 0)
                    break;

                if (workTime <= 60 && (gingerBreadC >= 10 || fastMode)) {
                    timeBetween = shortTimeGen.sample();

                    if (!fastMode)
                        price = (price * 0.25);

                    fastMode = true;
                } else {
                    timeBetween = timeGen.sample();
                }

                workTime -= timeBetween;
                totalProfit += price;
                gingerBreadC--;
            }

            totalNotSold += gingerBreadC;
        }

        System.out.println("************************************************");
        System.out.println("Babke priemerne ostane --> " + (double) totalNotSold / replications);
        System.out.println("Babka priemerne zarobi --> " + totalProfit / replications);



        TriangularRNG generatorCeny = new TriangularRNG(0.5,0.75,1.0);
        TriangularRNG generatorMedzeriPredaja = new TriangularRNG(2.0,6.0,10.0);
        UniformContinuousRNG generatorRychlejsiehoPredaja = new UniformContinuousRNG(1.0,3.0);

        int numOfReplications = 1000000;


        double pocetPredanych = 0;
        double minutes = 480;

        double cenaPredaja;
        double casMedziPredajmi = 0.0;

        double napocitaneZostatky = 0;
        double napocitanyZisk = 0;
        double priemerZisk = 0.0;
        double priemerZostatok = 0.0;

        for (int i = 0; i < numOfReplications; i++) {
            double timeLeft = 480;
            int numberSold=0;
            double celkovyPredaj = 0;
            double zostatok = 0;
            int pocetNapecenych = 100;
            boolean fasterMode = false;

            cenaPredaja = generatorCeny.sample();

            while (timeLeft > 0 && pocetNapecenych > 0) {
                if (timeLeft > 60) {
                    casMedziPredajmi = generatorMedzeriPredaja.sample();
                    pocetNapecenych--;
                    celkovyPredaj += cenaPredaja;
                } else {
                    if (pocetNapecenych > 10) {
                        fasterMode = true;
                    }
                    if(fasterMode) {
                        casMedziPredajmi = generatorRychlejsiehoPredaja.sample();
                        celkovyPredaj += (cenaPredaja * 0.25);
                    } else {
                        casMedziPredajmi = generatorMedzeriPredaja.sample();
                        celkovyPredaj += cenaPredaja;
                    }
                    pocetNapecenych--;
                }

                if(timeLeft - casMedziPredajmi >= 0) {
                    timeLeft -= casMedziPredajmi;
                } else {
                    timeLeft = 0;
                }
                numberSold++;
            }

            if(numberSold < 100) {
                zostatok = 100 - numberSold;
            } else {
                zostatok = 0;
            }
            napocitaneZostatky += zostatok;
            napocitanyZisk += celkovyPredaj;
        }

        priemerZisk = napocitanyZisk / (double)numOfReplications;
        //priemerZisk *= 100;
        System.out.printf("Priemerny zisk za medovniky je: %.2f \n", priemerZisk);

        priemerZostatok = napocitaneZostatky / (double)numOfReplications;
        //priemerZostatok *= 100;
        System.out.printf("Priemerny zostatok medovnikov je: %.2f \n", priemerZostatok);

    }


}
