// Position.java
// represents a simple row/col position in the world

public class Position {
    private int row;
    private int col;

    public Position() {
        row = 0;
        col = 0;
    }

    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    @Override
    public boolean equals(Object other) {
        Position op = (Position) other;

        // they are equal when both coordinates match
        return this.row == op.row && this.col == op.col;
    }

    // returns whether a position is adjacent to another (or equal)
    public boolean isAdjacent(Position other) {
        int rowdiff = Math.abs(this.row - other.row);
        int coldiff = Math.abs(this.col - other.col);

        if (rowdiff + coldiff < 2) {
            return true;
        } else {
            return false;
        }
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}

