package src.balyanova.lesson1.code_review;

class Lorry extends Car implements Moveable, Stopable {//можно наследоваться от одного класса и имплементить несколько интерфейсов

    public Lorry(Engine engine, String color, String name) {// раз классу Car мы добавили конструктор, значит и наследнику тоже создаем
        super(engine, color, name);
    }

    @Override//желательна аннотация для наглядности
    public void move(){
        System.out.println("Car is moving");
    }
    @Override//желательна аннотация для наглядности
    public void stop(){
        System.out.println("Car is stop");
    }

    @Override//реализуем родительский метод
    void open() {
        System.out.println("Car is open");
    }
}

//class Lorry extends Car, Moveable, Stopable {
//    public void move(){
//        System.out.println("Car is moving");
//    }
//    public void stop(){
//        System.out.println("Car is stop");
//    }
//}