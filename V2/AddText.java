public class AddText extends Commande {
    // AddText is a class used to add a character into the buffer

    private String text;

    public AddText(Buffer buffer, Clipboard clipboard, String text) {
        super(buffer, clipboard);
        this.text = text;
    }

    @Override
    public void execute(Buffer buffer, Clipboard cb) {
        // Add to the buffer
        buffer.addText(text);
    }

    @Override
    public void undo(Buffer buffer, Clipboard cb) {
        // Remove from the buffer
        buffer.delChar();
    }
}
