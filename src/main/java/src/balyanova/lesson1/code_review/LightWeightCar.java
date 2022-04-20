package src.balyanova.lesson1.code_review;

class LightWeightCar extends Car implements Moveable {//оставляем дефолтный модификатор (под вопросом)

    public LightWeightCar(Engine engine, String color, String name) {// раз классу Car мы добавили конструктор, значит и наследнику тоже создаем
        super(engine, color, name);
    }

    @Override
    void open() {
        System.out.println("Car is open");
    }
    @Override
    public void move() {
        System.out.println("Car is moving");
    }

}

//class LightWeightCar extends Car implements Moveable {
//    @Override
//    void open() {
//        System.out.println("Car is open");
//    }
//    @Override
//    public void move() {
//        System.out.println("Car is moving");
//    }
//}