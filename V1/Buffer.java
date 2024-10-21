public class Buffer{
    private StringBuffer text;
    private int startSel;
    private int endSel;
    private int cursor;


    public Buffer(){
        text = new StringBuffer();
        startSel = 0;
        endSel = 0;
    }


    public String getText(){
        return text.toString();
    }


    public void addText(String str){
        delSel();
        text.insert(cursor, str);
        cursor += str.length();
        startSel = cursor;
        endSel = cursor;
    }

    public void delSel(){
        text=text.delete(getStartSel(),getEndSel());
        cursor = startSel;
        endSel = startSel;
    }

    public void delChar(){
        if(startSel == endSel){
            if(cursor>0){
                text.deleteCharAt(cursor-1);
                cursor--;
                startSel = cursor;
                endSel = cursor;
            }
        } else {delSel();}
        
    }


    public int getStartSel(){
        return startSel;
    }


    public int getEndSel(){
        return endSel;
    }


    public String getSel(){
        return text.substring(startSel,endSel);
    }

    public void setStartSel(int pos){
        startSel = pos;
    }

    public void setEndSel(int pos){
        endSel = pos;
    }

    public void setCursor(int pos){
        cursor = pos;
    }

    public int getCursor(){
        return cursor;
    }
}
