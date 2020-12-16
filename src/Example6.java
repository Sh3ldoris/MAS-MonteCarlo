import OSPRNG.TriangularRNG;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Example6 {

    public static void main(String[] args) {
        final int replication = 10000;
        final int season = 15;

        TriangularRNG fastGen = new TriangularRNG(40.0, 50.0, 75.0);
        TriangularRNG furiousGen = new TriangularRNG(35.0, 52.0, 80.0);
        List<Car> cars = new ArrayList<>();

        int fastWin = 0; //Fast je na prvych miestach

        for (int i = 0; i < replication; i++) {
            for (int j = 0; j < 5; j++) {
                cars.add(new Car("fast", fastGen.sample()));
                cars.add(new Car("furious", furiousGen.sample()));
            }

            Collections.sort(cars);

            if (cars.get(0).getType().equals("fast") && cars.get(1).getType().equals("fast") ) {
                fastWin++;
            }

            cars.clear();
        }

        System.out.println("Pravdepodobnost, ze Fast je na 1. a 2. miest --> " + (double) fastWin / replication * 100);

        int fastSeasonWin = 0;
        int furiSeasonWin = 0;

        for (int i = 0; i < replication; i++) {
            int fastPoints = 0;
            int furiPoints = 0;

            for (int j = 0; j < season; j++) {

                for (int m = 0; m < 5; m++) {
                    cars.add(new Car("fast", fastGen.sample()));
                    cars.add(new Car("furious", furiousGen.sample()));
                }

                Collections.sort(cars);

                for (int k = 0; k < cars.size(); k++) {
                    Car car = cars.get(k);
                    if (car.getType().equals("fast")) {
                        fastPoints += 10 - k;
                    } else {
                        furiPoints += 10 - k;
                    }
                }

                cars.clear();
            }
            if (fastPoints > furiPoints) {
                fastSeasonWin++;
            } else
                furiSeasonWin++;
        }
        System.out.println("Fast ziska viac bodov v sezone s % ---> " + (double) fastSeasonWin / replication * 100);
        System.out.println("Furious ziska viac bodov v sezone s % ---> " + (double) furiSeasonWin / replication * 100);
    }


    private static class Car implements Comparable<Car> {
        private String type;
        private double raceTime;

        public Car(String type, double raceTime) {
            this.type = type;
            this.raceTime = raceTime;
        }

        public String getType() {
            return type;
        }

        public double getRaceTime() {
            return raceTime;
        }

        @Override
        public int compareTo(Car o) {
            return Double.compare(raceTime, o.getRaceTime());
        }
    }
}
