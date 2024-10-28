public class Cursor extends Commande {
    // Cursor is a command use to move the cursor or the selection

    private boolean dir;
    private boolean moveSel;

    public Cursor(Buffer buffer, Clipboard clipboard, boolean dir, boolean moveSel) {
        super(buffer, clipboard);
        this.dir = dir;
        this.moveSel = moveSel;
    }

    @Override
    public void execute(Buffer buffer, Clipboard cb) {
        // Execute the command
        if (moveSel) {
            buffer.moveCursor(dir);
        } else {
            buffer.moveOnlyCursor(dir);
        }
    }

    @Override
    public void undo(Buffer buffer, Clipboard cb) {
        // Undo the commmand
        if (moveSel) {
            buffer.moveCursor(!dir);
        } else {
            buffer.moveOnlyCursor(!dir);
        }
    }
}
