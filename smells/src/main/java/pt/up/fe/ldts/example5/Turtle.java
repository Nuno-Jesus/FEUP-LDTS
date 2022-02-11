package pt.up.fe.ldts.example5;

public class Turtle {
    private int row;
    private int column;
    private Direction direction;

    public Turtle(int row, int column, Direction direction) {
        this.row = row;
        this.column = column;
        this.direction = direction;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public char getDirection(){ return direction.getDirection(); }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void setDirection(Direction direction){
        this.direction = direction;
    }

    public void execute(char command) {
        if (command == 'L')
            setDirection(direction.rotateLeft());

        else if (command == 'R')
            setDirection(direction.rotateRight());

        else if (command == 'F')
            direction.moveForward(this);
    }
}
