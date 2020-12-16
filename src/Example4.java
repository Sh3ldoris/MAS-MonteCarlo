import OSPRNG.UniformContinuousRNG;
import OSPRNG.UniformDiscreteRNG;

public class Example4 {


    public static void main(String[] args) {
        final int steps = 1000;
        final int replications = 100000;

        UniformContinuousRNG random = new UniformContinuousRNG();
        int distance = 0;

        System.out.println("**************** 1D ********************");

        for (int i = 0; i < replications; i++) {
            int distancePerTry = 0;
            for (int j = 0; j < steps; j++) {
                if (random.sample() < 0.5) {
                    distancePerTry += 1;
                } else
                    distancePerTry -= 1;
            }

            distance += Math.abs(distancePerTry);
        }

        System.out.println("Experimentalny odhad krokov: " + (double) Math.abs(distance) / replications);
        System.out.println("Vypocitany odhad: " + Math.sqrt((2 * steps) / Math.PI));


        //Dva rozmery
        System.out.println("**************** 2D ********************");

        UniformContinuousRNG random2d = new UniformContinuousRNG();
        int distance2 = 0;
        for (int i = 0; i < replications; i++) {
            int x = 0;
            int y = 0;
            for (int j = 0; j < steps; j++) {
                double num = random2d.sample();
                if (num < 0.25) {
                    x++;
                } else if (num  < 0.5) {
                    x--;
                } else if (num < 0.75) {
                    y++;
                } else
                    y--;
            }

            distance2 += Math.abs(x);
            distance2 += Math.abs(y);
        }

        System.out.println("Experimentalny odhad: " + (double) Math.abs(distance2) / replications);
        System.out.println("Vypocitany odhad: " + Math.sqrt((4 * steps) / Math.PI));

        //Tri rozmery
        System.out.println("**************** 3D ********************");

        UniformContinuousRNG random3d = new UniformContinuousRNG();
        int distance3 = 0;

        for (int i = 0; i < replications; i++) {

            int x = 0;
            int y = 0;
            int z = 0;

            for (int j = 0; j < steps; j++) {
                double num = random3d.sample();
                if (num < 0.16666) {
                    x++;
                } else if (num < (2 * 0.16666)) {
                    x--;
                } else if (num < (3 * 0.16666)) {
                    y++;
                } else if (num < (4 * 0.16666)) {
                    y--;
                } else if (num < (5* 0.16666)) {
                    z++;
                } else
                    z--;
            }

            distance3 += Math.abs(x);
            distance3 += Math.abs(y);
            distance3 += Math.abs(z);
        }

        System.out.println("Experimentalny odhad: " + (double) Math.abs(distance3) / replications);
        System.out.println("Vypocitany odhad: " + Math.sqrt((6 * steps) / Math.PI));

        //Presnejsi sposob pouzit UniformDiscrete
        UniformDiscreteRNG gen3 = new UniformDiscreteRNG(1, 6);
        int vzdialenostSpolu3 = 0;
        for (int i = 0; i < replications; i++) {
            int x = 0;
            int y = 0;
            int z = 0;
            for (int j = 0; j < steps; j++) {
                switch (gen3.sample()) {
                    case 1:
                        x++;
                        break;
                    case 2:
                        x--;
                        break;
                    case 3:
                        y++;
                        break;
                    case 4:
                        y--;
                        break;
                    case 5:
                        z++;
                        break;
                    case 6:
                        z--;
                        break;
                    default:
                        break;
                }
            }
            vzdialenostSpolu3 += Math.abs(x);
            vzdialenostSpolu3 += Math.abs(y);
            vzdialenostSpolu3 += Math.abs(z);
        }
        System.out.println("Trojrozmerny priestor:");
        System.out.println("Vzdialenost je " + (double) vzdialenostSpolu3 / replications);
    }
}
