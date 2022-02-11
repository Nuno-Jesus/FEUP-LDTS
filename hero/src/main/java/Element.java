import com.googlecode.lanterna.graphics.TextGraphics;

public abstract class Element {
    /******************** ATTRIBUTE *******************/
    protected Position position;

    /******************* CONSTRUCTOR ******************/
    public Element(Position position){
        this.position = position;
    }

    /******************** METHODS *********************/
    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public abstract void draw(TextGraphics graphics);
}
