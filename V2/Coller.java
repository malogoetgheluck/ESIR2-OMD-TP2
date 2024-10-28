public class Coller extends Commande {
    // Coller is a command use to insert the content of the clipboard in the buffer

    private String content;
    private String textDeleted;
    private boolean startFromRight;

    public Coller(Buffer buffer, Clipboard clipboard) {
        super(buffer, clipboard);
        content = clipboard.getContenu();
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
        buffer.addText(content);
    }

    @Override
    public void undo(Buffer buffer, Clipboard cb) {
        // Undo the command by deleting the string added, adding the string deleted, and put the selection and the cursor as before
        buffer.delString(content.length());
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
