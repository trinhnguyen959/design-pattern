package factory.odev;

import factory.odev.factory.AnimalFactory;
import factory.odev.factory.BasicAnimalFactory;
import factory.odev.factory.RandomAnimalFactory;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        AnimalFactory animalFactory;

        Random random = new Random();
        int type = random.nextInt(0, 2);

        if (type == 0) {
            animalFactory = new BasicAnimalFactory();
        } else {
            animalFactory = new RandomAnimalFactory();
        }

        System.out.println(animalFactory.createAnimal().getName());
        System.out.println(animalFactory.createAnimal().getName());
        System.out.println(animalFactory.createAnimal().getName());
        System.out.println(animalFactory.createAnimal().getName());
        System.out.println(animalFactory.createAnimal().getName());
    }
}
