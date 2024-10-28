public class Buffer {
    // Buffer is a class meaned to store and modify the text of the editor

    private StringBuffer text;
    private int startSel;
    private int endSel;
    private int cursor;

    public Buffer() {
        text = new StringBuffer();
        startSel = 0;
        endSel = 0;
    }

    public String getText() {
        // return the text stored
        return text.toString();
    }

    public void addText(String str) {
        // Delete the selection and add a string
        delSel();
        text.insert(cursor, str);
        cursor += str.length();
        startSel = cursor;
        endSel = cursor;
    }

    public void delSel() {
        // Delete the selection
        text = text.delete(getStartSel(), getEndSel());
        cursor = startSel;
        endSel = startSel;
    }

    public String delChar() {
        // Delete the character before the cursor or the selection if there is one
        // Return the string deleted
        if (startSel == endSel) {
            if (cursor > 0) {
                String t = text.substring(cursor - 1, cursor);
                text.deleteCharAt(cursor - 1);
                cursor--;
                startSel = cursor;
                endSel = cursor;
                return t;
            }
        } else {
            String t = getSel();
            delSel();
            return t;
        }
        return "";

    }

    public void delString(int len) {
        // Remove a string from the text
        for (int i = 0; i < len; i++) {
            delChar();
        }
    }

    public int getStartSel() {
        // Return the start of the selection
        return startSel;
    }

    public int getEndSel() {
        // Return the end of the selection
        return endSel;
    }

    public String getSel() {
        // Return the selection
        return text.substring(startSel, endSel);
    }

    public void setStartSel(int pos) {
        // Set the start of the selection
        startSel = pos;
    }

    public void setEndSel(int pos) {
        // Set the end of the selection
        endSel = pos;
    }

    public void moveCursor(boolean dir) {
        // Move the cursor in a direction and reset the selection
        // If dir is false, to the left, else to the right
        if (dir && cursor < text.length()) {
            cursor++;
            startSel = cursor;
            endSel = cursor;
        } else if (!dir && cursor > 0) {
            cursor--;
            startSel = cursor;
            endSel = cursor;
        }
    }

    public void moveOnlyCursor(boolean dir) {
        // Move the cursor in a direction and modify the selection
        // If dir is false, to the left, else to the right
        if (dir && cursor < text.length()) {
            if (cursor == endSel) {
                cursor++;
                endSel = cursor;
            } else {
                cursor++;
                startSel = cursor;
            }
        } else if (!dir && cursor > 0) {
            if (cursor == startSel) {
                cursor--;
                startSel = cursor;
            } else {
                cursor--;
                endSel = cursor;
            }
        }
    }

    public int getCursor() {
        // Return the position of the cursor
        return cursor;
    }
}
