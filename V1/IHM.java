import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;

public class IHM{
    private Buffer buffer;
    private Clipboard clipboard;

    private JFrame frame;
    private JPanel panel;
    private JTextArea textArea1;
    
    public IHM(){
        // Création du buffer et du clipboard
        this.buffer = new Buffer();
        this.clipboard = new Clipboard();

        // Création de l'interface graphique
        frame = new JFrame("Editeur de texte");
        frame.setSize(1000, 500);
        panel = new JPanel();
        textArea1 = new JTextArea ("",20,100);
        JScrollPane scrollPane = new JScrollPane(textArea1);
        panel.setLayout(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        frame.getContentPane().add(panel);
        frame.setVisible(true);
        textArea1.setEditable(false);
        frame.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        });
    }

    private void addToBuffer(int keyCode){
        switch(keyCode){
            case KeyEvent.VK_A: 
                System.out.println("A");
                buffer.addText("A");
                break;
            case KeyEvent.VK_B: 
                System.out.println("B");
                buffer.addText("B");
                break;
            case KeyEvent.VK_C: 
                System.out.println("C");
                buffer.addText("C");
                break;
            case KeyEvent.VK_D: 
                System.out.println("D");
                buffer.addText("D");
                break;
            case KeyEvent.VK_E: 
                System.out.println("E");
                buffer.addText("E");
                break;
            case KeyEvent.VK_F: 
                System.out.println("F");
                buffer.addText("F");
                break;
            case KeyEvent.VK_G: 
                System.out.println("G");
                buffer.addText("G");
                break;
            case KeyEvent.VK_H: 
                System.out.println("H");
                buffer.addText("H");
                break;
            case KeyEvent.VK_I: 
                System.out.println("I");
                buffer.addText("I");
                break;
            case KeyEvent.VK_J: 
                System.out.println("J");
                buffer.addText("J");
                break;
            case KeyEvent.VK_K: 
                System.out.println("K");
                buffer.addText("K");
                break;
            case KeyEvent.VK_L: 
                System.out.println("L");
                buffer.addText("L");
                break;
            case KeyEvent.VK_M: 
                System.out.println("M");
                buffer.addText("M");
                break;
            case KeyEvent.VK_N: 
                System.out.println("N");
                buffer.addText("N");
                break;
            case KeyEvent.VK_O: 
                System.out.println("O");
                buffer.addText("O");
                break;
            case KeyEvent.VK_P: 
                System.out.println("P");
                buffer.addText("P");
                break;
            case KeyEvent.VK_Q: 
                System.out.println("Q");
                buffer.addText("Q");
                break;
            case KeyEvent.VK_R: 
                System.out.println("R");
                buffer.addText("R");
                break;
            case KeyEvent.VK_S: 
                System.out.println("S");
                buffer.addText("S");
                break;
            case KeyEvent.VK_T: 
                System.out.println("T");
                buffer.addText("T");
                break;
            case KeyEvent.VK_U: 
                System.out.println("U");
                buffer.addText("U");
                break;
            case KeyEvent.VK_V: 
                System.out.println("V");
                buffer.addText("V");
                break;
            case KeyEvent.VK_W: 
                System.out.println("W");
                buffer.addText("W");
                break;
            case KeyEvent.VK_X: 
                System.out.println("X");
                buffer.addText("X");
                break;
            case KeyEvent.VK_Y: 
                System.out.println("Y");
                buffer.addText("Y");
                break;
            case KeyEvent.VK_Z: 
                System.out.println("Z");
                buffer.addText("Z");
                break;
            case KeyEvent.VK_ENTER: 
                System.out.println("ENTER");
                buffer.addText("\n");
                break;
            case KeyEvent.VK_SPACE: 
                System.out.println("SPACE");
                buffer.addText(" ");
                break;
            case KeyEvent.VK_BACK_SPACE: 
                System.out.println("BACKSPACE");
                buffer.delChar();
                break;
            case KeyEvent.VK_COMMA: 
                System.out.println("COMMA");
                buffer.addText(",");
                break;
            case KeyEvent.VK_PERIOD: 
                System.out.println("PERIOD");
                buffer.addText(".");
                break;
        }
    }

    private void actOnBuffer(int keyCode){
        Commande c;
        switch(keyCode){
            case KeyEvent.VK_C:
                c = new Copier();
                c.execute(buffer, clipboard);
                break;
            case KeyEvent.VK_V:
                c = new Coller();
                c.execute(buffer, clipboard);
                break;
            case KeyEvent.VK_X:
                c = new Couper();
                c.execute(buffer, clipboard);
                break;
        }
    }

    public void start(){
        textArea1.addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent e){
                // On récupère la position du curseur
                buffer.setCursor(textArea1.getCaretPosition());
                buffer.setEndSel(textArea1.getSelectionEnd());
                buffer.setStartSel(textArea1.getSelectionStart());

                int keyCode = e.getKeyCode();
                // ajout du caractère dans le buffer
                if (e.isControlDown()){
                    actOnBuffer(keyCode);
                } else {
                    addToBuffer(keyCode);
                }
                
                // Mise à jour de l'affichage
                textArea1.setText(buffer.getText());
                textArea1.setCaretPosition(buffer.getCursor());
                textArea1.setSelectionEnd(buffer.getEndSel());
                textArea1.setSelectionStart(buffer.getStartSel());
            }
        });
    }
}