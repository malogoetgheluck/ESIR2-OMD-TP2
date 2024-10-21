public abstract class Commande {
    public Commande(){}

    public abstract void execute(Buffer buffer, Clipboard cb);
}