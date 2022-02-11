package pt.up.fe.ldts.example2;

public class Rectangle extends Shape{
    private double width;
    private double height;

    public Rectangle(double x, double y, double width, double height){
        super(x, y);
        this.width = width;
        this.height = height;
    }

    @Override
    protected double getArea(){
        return width * height;
    }

    @Override
    protected double getPerimeter(){
        return 2 * (width + height);
    }

    @Override
    protected void draw(GraphicFramework graphics){
        graphics.drawLine(x, y, x + width, y);
        graphics.drawLine(x + width, y, x + width, y + height);
        graphics.drawLine(x + width, y + height, x, y + height);
        graphics.drawLine(x, y + height, x, y);
    }
}
