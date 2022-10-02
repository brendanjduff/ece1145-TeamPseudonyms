package hotciv.variants;

import hotciv.common.AgingStrategy;

public class LinearAgingStrategy implements AgingStrategy {
    //int age = 0;
    /*public void setInitialAge(int init_age) {
        age = init_age;
    }*/

    public int incrementAge(int age) {
        if(age >= -4000 && age < -100) {
            age += 100;
            return age;
        } else if(age == -100) {
            age = -1;
            return age;
        } else if(age == -1) {
            age = 1;
            return age;
        } else if(age >= 1 && age < 1750) {
            age += 50;
            return age;
        } else if(age >= 1750 && age < 1900) {
            age += 25;
            return age;
        } else if(age >= 1900 && age < 1970) {
            age += 5;
            return age;
        } else if(age >= 1970) {
            age += 1;
            return age;
        }
        return 5811; //this is how i know i goofed
    }
/*
    public int getAge() {
        return age;
    } */
}
