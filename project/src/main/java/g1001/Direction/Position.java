package g1001.Direction;

import java.util.Objects;

public class Position {
    private int x, y;

    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString(){
        return "(" + x + ", " + y + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof Position)) return false;

        Position p = (Position) o;
        return x == p.getX() && y == p.getY();
    }

    @Override
    public int hashCode(){
        return Objects.hash(x, y);
    }
}
