package src.balyanova.lesson1.figures;

public class Square implements Figure{
    private final float length;

    public Square(float length) {
        this.length = length;
    }

    @Override
    public void draw() {
        System.out.println("Квадрат со сторонами " + length);
    }

    @Override
    public void calculateArea() {
        System.out.println("Площадь квадрата = " + length * length);
    }
}
