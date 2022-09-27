//class represents a line the msg box content


public class mBoxLine {

    enum Alignment {
        LEFT,
        CENTER,
        RIGHT
    }

public String text;
public Alignment alignment;

public mBoxLine(String text, Alignment alignment) {
    this.text = text;
    this.alignment = alignment;
    }
 }
