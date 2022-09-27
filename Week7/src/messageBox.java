//class represents a line in the msg box with header and content
//a content has several lines that can be added by calling addLine()


//resizable-array, storing the elements with no size limit,
// can be added and removed whenever I want
import java.util.ArrayList;

public class messageBox {
        String header;
        ArrayList <mBoxLine> content;

//////////////////////////////////////
//default constructor
//set a string for header
        public messageBox () {
            header = "No header";
            content = new ArrayList <> ();
        }

//////////////////////////////////////
//constructor
//set a string for header
        public messageBox (String header) {
            this.header = header;
            content = new ArrayList <> ();
        }

//////////////////////////////////////
//mutators
//assign new value to one obj field
        public void setHeader(String text) {
            header = text;
        }

//////////////////////////////////////
//accessors
//return value of one obj field
        public String getHeader() {
            return header;
        }

//////////////////////////////////////
//add a line to a msg box content
        public void addLine(mBoxLine line) {
            content.add(line);
        }

//////////////////////////////////////
//remove all lines from the content
        public void clearContent() {

            content.clear();
        }

//////////////////////////////////////
//return the msg box content
        public ArrayList getContent() {
            return content;
        }

    }





