package factory.odev.factory;

import factory.odev.Animal;
import factory.odev.Cat;
import factory.odev.Dog;
import factory.odev.Duck;

import java.util.Random;

public class RandomAnimalFactory implements AnimalFactory{
    @Override
    public Animal createAnimal() {
        Random random = new Random();
        int type = random.nextInt(0, 3);
        if (type == 0) {
            return new Dog();
        } else if (type == 1) {
            return new Cat();
        } else {
            return new Duck();
        }
    }
}
