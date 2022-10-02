package hotciv.variants;

import hotciv.common.AgingStrategy;

public class LinearAgingStrategy implements AgingStrategy {
    //int age = 0;
    /*public void setInitialAge(int init_age) {
        age = init_age;
    }*/

    public int incrementAge(int age) {
        age += 100;
        return age;
    }
/*
    public int getAge() {
        return age;
    } */
}
