package com.company;

public class Main {

    public static void main(String[] args) {
        Element car = new CarElement();
        car.accept(new HooliganVisitor());
        System.out.println();
        car.accept(new MechanicVisitor());
    }
}

interface Visitor{
    void visit(EngineElement engine);
    void visit(BodyElement body);
    void visit(CarElement car);
    void visit(WheelElement wheel);
}

interface Element{
    void accept(Visitor visitor);
}

// Car body
class BodyElement implements Element{
    public void accept(Visitor visitor){
        visitor.visit(this);
    }
}

// Engine
class EngineElement implements Element{
    public void accept(Visitor visitor){
        visitor.visit(this);
    }
}

// Wheel
class WheelElement implements Element{
    private String name;

    public WheelElement(String name){
        this.name = name;
    }
    public String getName(){return this.name;}
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

// Car
class CarElement implements Element{
    Element[] elements;

    public CarElement(){
        this.elements = new Element[]{
                new WheelElement("front left"), new WheelElement("front right"),
                new WheelElement("back right"), new WheelElement("back left"),
                new BodyElement(), new EngineElement()
        };
    }

    @Override
    public void accept(Visitor visitor) {
        for (Element elem : elements) {
            elem.accept(visitor);
        }
        visitor.visit(this);
    }
}

class HooliganVisitor implements Visitor{

    @Override
    public void visit(EngineElement engine) {
        System.out.println("Started engine");
    }

    @Override
    public void visit(BodyElement body) {
        System.out.println("Knocked to the body");
    }

    @Override
    public void visit(CarElement car) {
        System.out.println("Smoked in the car");
    }

    @Override
    public void visit(WheelElement wheel) {
        System.out.println("Kicked " + wheel.getName() + " wheel");
    }
}

class MechanicVisitor implements Visitor{

    @Override
    public void visit(EngineElement engine) {
        System.out.println("Checked engine");
    }

    @Override
    public void visit(BodyElement body) {
        System.out.println("Cleaned car's body");
    }

    @Override
    public void visit(CarElement car) {
        System.out.println("Checked car's body");
    }

    @Override
    public void visit(WheelElement wheel) {
        System.out.println("Pumped up " + wheel.getName() + " wheel");
    }
}

