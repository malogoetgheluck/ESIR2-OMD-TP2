import java.util.ArrayList;

public class Script {
    // Script is a class used to store the commands for a script

    private ArrayList<Commande> commands;
    private boolean recording;

    public Script() {
        commands = new ArrayList<Commande>();
        recording = false;
    }

    public void addCommand(Commande c) {
        // Add a command to the script
        commands.add(c);
    }

    public boolean isRecording() {
        // Return if the script is recording (listening for the user to enter command)
        return recording;
    }

    public void setRecording(boolean rec) {
        // Set the variable saying if the user is recording a script
        recording = rec;
    }

    public void clear() {
        // Clear the script
        commands.clear();
    }

    public void execute(Buffer buffer, Clipboard clipboard, History history) {
        // Execute each command from the script
        for (Commande c : commands) {
            c.execute(buffer, clipboard);
            if (!(c instanceof Copier)) {
                history.addCommand(c);
            }
        }
    }
}
