package pt.up.fe.ldts.example2;

public class Circle extends Shape{
    private double radius;

    public Circle(double x, double y, double radius){
        super(x, y);
        this.radius = radius;
    }

    @Override
    protected double getArea(){
        return Math.PI * Math.pow(radius, 2);
    }

    @Override
    protected double getPerimeter(){
        return 2 * Math.PI * radius;
    }

    @Override
    protected void draw(GraphicFramework graphics){
        graphics.drawCircle(x, y, radius);
    }

}
