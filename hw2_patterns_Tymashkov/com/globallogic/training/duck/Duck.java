package hw2_patterns_Tymashkov.com.globallogic.training.duck;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import hw2_patterns_Tymashkov.com.globallogic.training.duck.model.Bird;
import hw2_patterns_Tymashkov.com.globallogic.training.duck.model.StandingState;
import hw2_patterns_Tymashkov.com.globallogic.training.duck.model.State;

public class Duck implements Cloneable, Bird {
    private static final String EMPTY_STRING = "";
    private static final AtomicInteger duckIdIncrement = new AtomicInteger(0);
    private final AtomicInteger duckId;
    private int age;
    private String color;
    private String name;
    private double weight;
    private double wingsLength;

    private State state = new StandingState();

    private Map<String, String> innerProperties = new HashMap<>();

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getWingsLength() {
        return wingsLength;
    }

    public void setWingsLength(double wingsLength) {
        this.wingsLength = wingsLength;
    }

    public Duck() {
        duckIdIncrement.getAndIncrement();
        this.duckId = duckIdIncrement;
    }

    public void setProperty(String name, String value) {
        if ( Objects.isNull(name)) {
            return;
        }
        innerProperties.put(name, value);
    }

    public String getProperty(String name) {
        if (null == name || !innerProperties.containsKey(name)) {
            return EMPTY_STRING;
        }
        return innerProperties.get(name);
    }

    public void removeProperty(String name) {
        if (null == name || !innerProperties.containsKey(name)) {
            return;
        }
        innerProperties.remove(name);
    }

    public void removeProperties() {
        innerProperties.clear();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Duck duck = (Duck) o;

        return duckId.equals(duck.duckId);
    }

    @Override
    public int hashCode() {
        return duckId.get() ^ (duckId.get() >>> 32);
    } //shift operation '>>>' by overly large constant value

    private Duck(AtomicInteger duckId) {
        this.duckId = duckId;
    }

    public Duck(Duck sourceDuck) {
        this.duckId = sourceDuck.duckId;
        this.age = sourceDuck.age;
        this.color = sourceDuck.color;
        this.name = sourceDuck.name;
        this.weight = sourceDuck.weight;
        this.wingsLength = sourceDuck.wingsLength;
        this.state = sourceDuck.state;
        this.innerProperties = sourceDuck.innerProperties;
    }

    @Override
    public Duck clone() throws CloneNotSupportedException {
        return (Duck) super.clone();
    }

    @Override
    public String toString() {
        return "Duck " +
                "name='" + name + '\'' +
                ", age=" + age +
                ", color='" + color + '\'' +
                ", weight=" + weight +
                ", wingsLength=" + wingsLength +
                ", state=" + state.toString() +
                ", {" +
                "innerProperties=" + innerProperties +
                '}';
    }

    @Override
    public void makeSound() {
        System.out.println("Krya-Krya");
    }
}
