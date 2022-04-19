package src.balyanova.lesson1.code_review;

abstract class Car {
    private Engine engine;//нужен приватный модификатор, особенно при наличии геттеров и сеттеров
    private String color;
    private String name;

     void start() {//делаем дефолтный модификатор, никто ничего не должен потерять
        System.out.println("Car starting");
    }

    abstract void open();

    public Engine getEngine() {//для более удобного представления кода, можно добавить зависимость lombok  c аннотацией @Data или @Getter @Setter
        return engine;
    }
    public void setEngine(Engine engine) {
        this.engine = engine;
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

    public Car(Engine engine, String color, String name) {//конструктор для создания новых объектов класса будет полезен
        this.engine = engine;
        this.color = color;
        this.name = name;
    }
}

//abstract class Car {
//    public Engine engine;
//    private String color;
//    private String name;
//    protected void start() {
//        System.out.println("Car starting");
//    }
//    abstract void open();
//    public Engine getEngine() {
//        return engine;
//    }
//    public void setEngine(Engine engine) {
//        this.engine = engine;
//    }
//    public String getColor() {
//        return color;
//    }
//    public void setColor(String color) {
//        this.color = color;
//    }
//    public String getName() {
//        return name;
//    }
//    public void setName(String name) {
//        this.name = name;
//    }
//}