package src.balyanova.lesson1.figures;

public class Triangle implements Figure{
    private final float sideLeft;
    private final float sideRight;
    private final float sideBase;

    public Triangle(float sideLeft, float sideRight, float sideBase) {
        this.sideLeft = sideLeft;
        this.sideRight = sideRight;
        this.sideBase = sideBase;
    }

    @Override
    public void draw() {
        System.out.println("Треугольник со сторонами: левая сторона " + sideLeft + ", правая сторона " + sideRight + ", основание: " + sideBase);
    }

    @Override
    public void calculateArea() {
        float perimeter = (sideBase + sideLeft + sideRight) / 2;
        float area = (float) Math.sqrt(perimeter * (perimeter - sideBase) * (perimeter - sideLeft) * (perimeter - sideRight));
        System.out.println("Площадь треугольника по трем сторонам = " + area);
    }
}
