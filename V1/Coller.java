public class Coller extends Commande {
    public Coller(){}

   @Override
   public void execute(Buffer buffer, Clipboard cb) {
       buffer.addText(cb.getContenu());
   }
}
