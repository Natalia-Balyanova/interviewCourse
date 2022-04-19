package src.balyanova.lesson1.code_review;
/*
в классе Car есть тип данных Engine, но нет такого класса, поэтому создаем простой класс
 */
public class Engine {
    private String model;//создаем поле

    public Engine(String model) {//и конструктор
        this.model = model;
    }
}
