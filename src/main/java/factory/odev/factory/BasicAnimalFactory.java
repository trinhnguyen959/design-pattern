package factory.odev.factory;

import factory.odev.Animal;
import factory.odev.Cat;
import factory.odev.Dog;
import factory.odev.Duck;

public class BasicAnimalFactory implements AnimalFactory {
    int index = 0;
    @Override
    public Animal createAnimal() {
        if (index == 0) {
            index++;
            return new Dog();
        }

        if (index == 1) {
            index++;
            return new Cat();
        }

        if (index == 2) {
            index = 0;
            return new Duck();
        }

        return null;
    }
}
