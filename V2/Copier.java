public class Copier extends Commande {
    // Copier is a command used to set the content of the clipboard to the text selected

    public Copier(Buffer buffer, Clipboard clipboard) {
        super(buffer, clipboard);
    }

    @Override
    public void execute(Buffer buffer, Clipboard cb) {
        // Execute the command
        cb.setContenu(buffer.getSel());
    }

    @Override
    public void undo(Buffer buffer, Clipboard cb) {
        // Does nothing
    }
}