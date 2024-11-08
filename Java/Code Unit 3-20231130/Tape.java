package turing;

/**
 * Represents a Turing machine tape using a doubly-linked list.
 */
public class Tape {
    private Cell currentCell;

    /**
     * Constructs a tape that initially consists of a single cell.
     * The cell contains a blank space, and the current cell pointer points to it.
     */
    public Tape() {
        currentCell = new Cell(' ');
    }

    /**
     * Returns the pointer that points to the current cell.
     *
     * @return The pointer to the current cell.
     */
    public Cell getCurrentCell() {
        return currentCell;
    }

    /**
     * Returns the character from the current cell.
     *
     * @return The character from the current cell.
     */
    public char getContent() {
        return currentCell.content;
    }

    /**
     * Changes the character in the current cell to the specified value.
     *
     * @param ch The new character for the current cell.
     */
    public void setContent(char ch) {
        currentCell.content = ch;
    }

    /**
     * Moves the current cell one position to the left along the tape.
     * If the current cell is the leftmost cell, a new cell is created
     * and added to the tape at the left of the current cell.
     * The content of the new cell is a blank space.
     */
    public void moveLeft() {
        if (currentCell.prev == null) {
            Cell newCell = new Cell(' ');
            newCell.next = currentCell;
            currentCell.prev = newCell;
        }
        currentCell = currentCell.prev;
    }

    /**
     * Moves the current cell one position to the right along the tape.
     * If the current cell is the rightmost cell, a new cell is created
     * and added to the tape at the right of the current cell.
     * The content of the new cell is a blank space.
     */
    public void moveRight() {
        if (currentCell.next == null) {
            Cell newCell = new Cell(' ');
            newCell.prev = currentCell;
            currentCell.next = newCell;
        }
        currentCell = currentCell.next;
    }

    /**
     * Returns a String consisting of the characters from all the cells on the tape,
     * read from left to right. Leading or trailing blank characters are discarded.
     *
     * @return The tape contents as a String.
     */
    public String getTapeContents() {
        StringBuilder tapeContents = new StringBuilder();
        Cell pointer = currentCell;

        // Move to the leftmost non-blank cell
        while (pointer.prev != null && pointer.prev.content != ' ') {
            pointer = pointer.prev;
        }

        // Append non-blank characters to the StringBuilder
        while (pointer != null && pointer.content != ' ') {
            tapeContents.append(pointer.content);
            pointer = pointer.next;
        }

        return tapeContents.toString();
    }
}
