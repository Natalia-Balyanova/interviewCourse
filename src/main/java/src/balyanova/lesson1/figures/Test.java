package src.balyanova.lesson1.figures;

public class Test {
    public static void main(String[] args) {
        Figure circle = new Circle(5);
        Figure square = new Square(4);
        Figure triangle = new Triangle(3, 3,4);

        Figure[] figures = {circle, square, triangle};

        for (Figure figure : figures) {
            figure.draw();
            figure.calculateArea();
        }
    }
}
