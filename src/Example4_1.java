import OSPRNG.UniformContinuousRNG;

public class Example4_1 {


    public static void main(String[] args) {
        final int steps = 1000;
        final int replications = 100000;

        UniformContinuousRNG random = new UniformContinuousRNG();
        int distance = 0;

        for (int i = 0; i < replications; i++) {
            int distancePerTry = 0;
            for (int j = 0; j < steps; j++) {
                if (random.sample() < 0.5) {
                    distancePerTry += 1;
                } else
                    distancePerTry -= 1;
            }

            distance += Math.abs(distancePerTry);
            System.out.println(i + ". --> " + Math.abs(distancePerTry));
        }

        System.out.println("Experimentalny odhad: " + (double) Math.abs(distance) / replications);
        System.out.println("Vypocitany odhad: " + Math.sqrt((2 * steps) / Math.PI));
    }
}
