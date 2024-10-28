import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
import javax.swing.text.DefaultCaret;

public class IHM{
    private Buffer buffer;
    private Clipboard clipboard;
    private History history;
    private Script script;

    private JFrame frame;
    private JPanel panel;
    private JTextArea textArea1;
    
    public IHM(){
        // Buffer, clipboard, history and script creation
        this.buffer = new Buffer();
        this.clipboard = new Clipboard();
        this.history = new History();
        this.script = new Script();

        // Graphic interface creation
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
        textArea1.setBackground(Color.gray);
        textArea1.setForeground(Color.white);
        frame.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        });
    }

    private void exec(Commande c){
        // Execute a command and add it to the history and if needed to the script
        c.execute(buffer, clipboard);
        history.addCommand(c);
        if (script.isRecording()){script.addCommand(c);}
    }

    private void noDown(KeyEvent e){
        // If neither ctrl nor shift is pressed
        int keyCode = e.getKeyCode();
        Commande c;
        switch(keyCode){
            case KeyEvent.VK_BACK_SPACE: // Delete a character
                c = new RemText(buffer, clipboard);
                exec(c);
                break;
            case KeyEvent.VK_RIGHT: // Move the cursor to the right and reset the selection
                c = new Cursor(buffer, clipboard, true, true);
                exec(c);
                break;
            case KeyEvent.VK_LEFT: // Move the cursor to the left and reset the selection
                c = new Cursor(buffer, clipboard, false, true);
                exec(c);
                break;
            default: // Print the character
                String t = Character.toString(e.getKeyChar());
                c = new AddText(buffer, clipboard, t);
                exec(c);
                break;
        }
    }

    private void shftDown(KeyEvent e){
        // Shift is pressed
        if (!(e.getKeyCode()==KeyEvent.VK_SHIFT)){ // prevent to add any character if only shift is pressed
            int keyCode = e.getKeyCode();
            Commande c;
            switch(keyCode){
                case KeyEvent.VK_RIGHT: // Move the cursor to the right and modify the selection
                    c = new Cursor(buffer, clipboard, true, false);
                    exec(c);
                    break;
                case KeyEvent.VK_LEFT: // Move the cursor to the left and modify the selection
                    c = new Cursor(buffer, clipboard, false, false);
                    exec(c);
                    break;
                default: // Print the character
                    String t = Character.toString(e.getKeyChar());
                    c = new AddText(buffer, clipboard, t);
                    exec(c);
                    break;
            }
        }
        
    }

    private void ctrlDown(KeyEvent e){
        // Control is pressed
        int keyCode = e.getKeyCode();
        Commande c;
        switch(keyCode){
            case KeyEvent.VK_C: // Copy the selection
                c = new Copier(buffer, clipboard);
                c.execute(buffer, clipboard);
                if (script.isRecording()){script.addCommand(c);}
                break;
            case KeyEvent.VK_V: // Paste the selection
                c = new Coller(buffer, clipboard);
                exec(c);
                break;
            case KeyEvent.VK_X: // Cut the selection
                c = new Couper(buffer, clipboard);
                exec(c);
                break;
            case KeyEvent.VK_Z: // Undo
                history.undo(buffer, clipboard);
                break;
            case KeyEvent.VK_Y: // Redo
                history.redo(buffer, clipboard);
                break;
            case KeyEvent.VK_R: // Start or stop to record a script
                if (script.isRecording()){
                    script.setRecording(false);
                    textArea1.setCaretColor(Color.BLACK);
                } else {
                    script.clear();
                    script.setRecording(true);
                    textArea1.setCaretColor(Color.RED);
                }
                break;
            case KeyEvent.VK_E: // Replay a script
                script.execute(buffer, clipboard, history);
                break;
        }
    }

    public void start(){
        // We want to prevent the user to move the cursor with the mouse
        textArea1.setCaret(new DefaultCaret() {
            @Override
            public void mouseClicked(MouseEvent e) {}
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseDragged(MouseEvent e) {}
        });

        // Get the key pressed
        textArea1.addKeyListener(new KeyAdapter(){ 
            @Override
            public void keyPressed(KeyEvent e){
                
                // The keyboard entry is processed
                if (e.isControlDown()){
                    ctrlDown(e);
                } else if (e.isShiftDown()){
                    shftDown(e);
                } else {
                    noDown(e);
                }
                e.consume();
                
                // We update the display
                textArea1.setText(buffer.getText());
                textArea1.setCaretPosition(buffer.getCursor());
                textArea1.setSelectionStart(buffer.getStartSel());
                textArea1.setSelectionEnd(buffer.getEndSel());
            }
        });
    }
}