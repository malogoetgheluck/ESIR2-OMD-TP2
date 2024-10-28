public abstract class Commande {
    // Command is an abstract class for all the command used

    public Commande(Buffer buffer, Clipboard clipboard) {
    }

    // Execute the command
    public abstract void execute(Buffer buffer, Clipboard cb);

    // Undo the command
    public abstract void undo(Buffer buffer, Clipboard clipboard);
}
