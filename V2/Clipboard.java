public class Clipboard {
    // Clipboard is a class used to store a temporary string

    private String contenu;

    public Clipboard() {
        this.contenu = "";
    }

    public String getContenu() {
        // Return the content of the clipboard
        return this.contenu;
    }

    public void setContenu(String c) {
        // Set the content of the clipboard
        this.contenu = c;
    }

}
