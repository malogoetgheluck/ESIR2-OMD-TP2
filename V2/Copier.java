public class Copier extends Commande {
    public Copier(){}

   @Override
   public void execute(Buffer buffer, Clipboard cb) {
       cb.setContenu(buffer.getSel());
   }
}
