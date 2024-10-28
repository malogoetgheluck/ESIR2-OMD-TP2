import java.util.ArrayList;

public class History {
    // History is a class used to keep all the command that has been used and undo on the buffer

    private ArrayList<Commande> done;
    private ArrayList<Commande> undone;

    public History() {
        done = new ArrayList<Commande>();
        undone = new ArrayList<Commande>();
    }

    public void addCommand(Commande c) {
        // Add a command to the history
        done.add(c);
        undone.clear();
    }

    public void undo(Buffer buffer, Clipboard clipboard) {
        // Undo the previous command used, and add it to the undone list
        if (!done.isEmpty()) {
            Commande c = done.removeLast();
            c.undo(buffer, clipboard);
            undone.add(c);
        }
    }

    public void redo(Buffer buffer, Clipboard clipboard) {
        // Undo the previous command undone, and add it to the done list
        if (!undone.isEmpty()) {
            Commande c = undone.removeLast();
            c.execute(buffer, clipboard);
            done.add(c);
        }
    }
}
