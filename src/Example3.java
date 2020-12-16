import OSPRNG.UniformContinuousRNG;

public class Example3 {

    private static double r = 0.5;
    private static double x0 = 0.5;
    private static double y0 = 0.5;


    public static void main(String[] args) {
        UniformContinuousRNG xGen = new UniformContinuousRNG();
        UniformContinuousRNG yGen = new UniformContinuousRNG();

        int total = 0;
        double estimatePi = 0;

        final int replications = 100;

        //Netusim ci su tu vhodne replikacie
        for (int i = 0; i < replications; i++) {
            int totalPerTry = 0;
            int inCircle = 0;

            while (true) {
                double x = xGen.sample();
                double y = yGen.sample();

                if (isInCircle(x, y)) {
                    inCircle++;
                }

                total++;
                totalPerTry++;
                double area = (double) inCircle / totalPerTry;

                estimatePi = (area / Math.pow(r, 2));
                if (Math.abs((estimatePi - Math.PI)) < 0.000001)
                    break;
            }
            System.out.println(i + ". ---> " + totalPerTry);
        }

        System.out.println("Bolo potrebnych umiestnit " + (double) total / replications + " bodov!");
    }

    private static boolean isInCircle(double x, double y) {
        return (Math.pow((x - x0), 2) + Math.pow((y - y0), 2)) <= Math.pow(r, 2) ;
    }
}
