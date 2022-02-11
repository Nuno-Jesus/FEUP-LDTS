package pt.up.fe.ldts.example2;

public abstract class Shape {
    protected double x;
    protected double y;

    public Shape(double x, double y){
        this.x = x;
        this.y = y;
    }

    protected abstract double getArea();
    protected abstract double getPerimeter();
    protected abstract void draw(GraphicFramework graphics);
}
