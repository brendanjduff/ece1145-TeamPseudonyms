package hotciv.common;

/** This strategy changes the age of the world at the end of each round
 */

public interface AgingStrategy {
    //public void setInitialAge(int age);

    public int incrementAge(int age);

    //public int getAge();
}
