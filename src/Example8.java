import OSPRNG.TriangularRNG;

public class Example8 {

    public static void main(String[] args) {
        final int replications = 10000;
        TriangularRNG areaGen = new TriangularRNG(1.0, 3.0, 3.5);

        int bestCount = 50;

        for (int k = 4; k < 50; k++) {
            int over2days = 0;

            for (int i = 0; i < replications; i++) {
                double totalArea = 300;
                double cutArea = 0;

                for (int j = 0; j < 20; j++) {
                    for (int l = 0; l < k; l++) {
                        cutArea += areaGen.sample();
                    }
                }
                if ((totalArea - cutArea) > 0)
                    over2days++;
            }

            if (((double) over2days / replications) < 0.1) {
                bestCount = k;
                break; //kludne to mozem zastavit na prvom vhodbom pretoze pocet kombajnoc by bol az prilis vysoky
            }
        }

        System.out.println("******************************");
        System.out.println("Najlepsi pocet kombajnov je ---> " + bestCount);
    }
}
