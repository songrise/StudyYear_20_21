class Shape {
    int n = 1;

    Shape() {

    }

    Shape(double x) {

    }

    double area() {
        return 0;
    }
}

class Circle extends Shape {
    private double r;
    int n = 2;

    Circle() {

    }

    Circle(double r) {
        this.r = r;
    }

    @Override
    double area() {
        return 3;
    }

    double perimeter() {
        return 6;
    }
}

public class TestInheritance {

    public static void main(String[] args) throws Exception {
        Shape s = new Circle(5);
        System.out.println(s);
        System.out.println(s.n);// field static bind
        Shape[] shapes = { new Shape(1), new Circle() };
        for (Shape sp : shapes) {
            System.out.println(sp.area());
        }
    }
}
