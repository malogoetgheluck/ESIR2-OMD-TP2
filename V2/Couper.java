public class Couper extends Commande {
    // Couper is a command used to delete the selection and add it to the clipboard

    private String textDeleted;
    private boolean startFromRight;

    public Couper(Buffer buffer, Clipboard clipboard) {
        super(buffer, clipboard);
        textDeleted = buffer.getSel();
        if (buffer.getCursor() == buffer.getStartSel()) {
            startFromRight = true;
        } else {
            startFromRight = false;
        }
    }

    @Override
    public void execute(Buffer buffer, Clipboard cb) {
        // Execute the command
        cb.setContenu(buffer.getSel());
        buffer.delSel();
    }

    @Override
    public void undo(Buffer buffer, Clipboard cb) {
        // Undo the command by adding the string deleted, and restore the selection and the cursor
        buffer.addText(textDeleted);
        if (!startFromRight) {
            for (int i = 0; i < textDeleted.length(); i++) {
                buffer.moveCursor(false);
            }
        }
        for (int i = 0; i < textDeleted.length(); i++) {
            buffer.moveOnlyCursor(!startFromRight);
        }
    }
}
