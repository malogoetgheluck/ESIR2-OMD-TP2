public class RemText extends Commande {
    // RemText is a command used to remove a character

    private String textRemoved;

    public RemText(Buffer buffer, Clipboard clipboard) {
        super(buffer, clipboard);
    }

    @Override
    public void execute(Buffer buffer, Clipboard cb) {
        // Execute the command
        textRemoved = buffer.delChar();
    }

    @Override
    public void undo(Buffer buffer, Clipboard cb) {
        // Undo the command
        buffer.addText(textRemoved);
    }
}
