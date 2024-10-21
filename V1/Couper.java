public class Couper extends Commande{
    public Couper(){}

   @Override
   public void execute(Buffer buffer, Clipboard cb) {
       cb.setContenu(buffer.getSel());
       buffer.delSel();
   }
}
