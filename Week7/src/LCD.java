//represents real LCD screen
//static methods = value is fixed for all instances

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;

public class LCD implements Comparable<LCD>{
    int width;
    int height;
    int funcCount; //hold current num of menu items available
    String prompt; //hold prompt msg
    public static String mode; //normal and extended mode for console
    public static boolean reverseBackground; //reverse bg colour?

    final static char escCode = 0x1B;
    final static char topLeft = 9484;
    final static char topRight = 9488;
    final static char bottomLeft = 9492;
    final static char bottomRight = 9496;
    final static char dash = 9472;
    final static char midLeft = 9500;
    final static char midRight = 9508;
    final static char bar = 9474;

    final static char topLeft1 = '┌';
    final static char topRight1 = '┐';
    final static char bottomLeft1 = '└';
    final static char bottomRight1 = '┘';
    final static char dash1 = '-';
    final static char midLeft1 = '├';
    final static char midRight1 = '┤';
    final static char bar1 = '|';


    public interface Comparable<T> {
        public int compareTo(T other_lcd);
    }

    public int compareTo(LCD other_lcd) {
        int dimension = width * height;
        int other_dimension = other_lcd.width * other_lcd.height;
        if (dimension < other_dimension) {
            return -1;
        } else if (dimension == other_dimension) {
            return 0;
        } else { // (dimension > other_dimension)
            return 1;
        }
    }


    //////////////////////////////////////////////////////////////////
//default constructor
    public LCD() throws FileNotFoundException {
        this.width = 80;
        this.height = 25;
        this.funcCount = 0;
        this.prompt = "Select a function";
        this.mode = "N"; // N = Normal, E = Extended
        reverseBackground = false;
        //setting.dat
        Scanner reader = new Scanner(new File("D:/Workspace/Week7/setting.dat"));//read file
        while(reader.hasNext()) {//while file has string
            String data = reader.next();//get string from file
            if (data.charAt(0) == 'N' || data.charAt(0) == 'E') {//if file contain N or E
                if (data.charAt(0) == 'N') {
                    this.mode = "N";//set mode to N
                } else {
                    this.mode = "E";//set mode to E
                }
            } else if (data.charAt(0) == '1' || data.charAt(0) == '2') {//if file contain 1 or 2
                if (data.charAt(0) == '1') {
                    this.reverseBackground = false;
                } else {
                    this.reverseBackground = true;
                }
            }
        }
        //if (!reader.hasNext()){
                //this.mode = "N";//set mode to N
                //this.reverseBackground = false;
            //}




    }

    //////////////////////////////////////////////////////////////////
//constructor = special method: used to initialize objects,set initial values
    public LCD(int width, int height) throws FileNotFoundException  {
        this.width = width;
        this.height = height;
        this.funcCount = 0;
        this.prompt = "Select a function";
        this.mode = "N"; // N = Normal, E = Extended
        reverseBackground = false;
        //setting.dat
        Scanner reader = new Scanner(new File("D:/Workspace/Week7/setting.dat"));//read file
        while(reader.hasNext()) {//while file has string
            String data = reader.next();//get string from file
            if (data.charAt(0) == 'N' || data.charAt(0) == 'E') {//if file contain N or E
                if (data.charAt(0) == 'N') {
                    this.mode = "N";//set mode to N
                } else {
                    this.mode = "E";//set mode to E
                }
            } else if (data.charAt(0) == '1' || data.charAt(0) == '2') {//if file contain 1 or 2
                if (data.charAt(0) == '1') {
                    this.reverseBackground = false;
                } else {
                    this.reverseBackground = true;
                }
            }
        }

        //if (!reader.hasNext()){
            //this.mode = "";//set mode to N
            //this.reverseBackground = false;
        //}

    }

    //////////////////////////////////////////////////////////////////
//mutators = instance method: assign new value to one of the obj fields (set)
    public void setWidth(int new_width) {
        width = new_width;
    }

    public void setHeight(int new_height) {
        height = new_height;
    }

    //////////////////////////////////////////////////////////////////
//accessors = instance method: return value of one of the obj fields (get)
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getResolution() {
        return width * height;
    }

    /////////////////////////////////////////////////////////////////
//toString = instance method that returns a String representation of the obj
    public String toString() {
        return "LCD object with dimension = (" + width + "," + height + ") ";
    }

    //set prompt msg
    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    //return the current prompt msg
    public String getPrompt() {
        return this.prompt;
    }

    //set mode E or N
    public void setMode(String mode) {
        this.mode = mode;
    }

    //return mode
    public String getMode() {
        return this.mode;
    }


///////////////////////////////////////////////////////////////


    //set current num of menu items
    public void setFuncCount(int funcCount) {
        this.funcCount = funcCount;
    }

    //invert bg mode
    public void reverseBackgroundMode() {
        this.reverseBackground = !this.reverseBackground;
    }

    //return bg mode
    public boolean getBackgroundMode() {
        return this.reverseBackground;
    }

    //receive msg box and print on console
    public void showMessageBox(messageBox mBox) {
        if (mode == "E") {
            clearScreen();
            GotoXY(1, 1);
            System.out.printf("%c", topLeft);
            for (int i = 0; i < width - 2; i++) {
                System.out.print(dash);
            }
            System.out.print(topRight);


            GotoXY(2, 0);
            printBoxLine(mBox.getHeader(), mBoxLine.Alignment.CENTER);

            GotoXY(3, 0);
            printBoxLine(" ", mBoxLine.Alignment.LEFT);

            GotoXY(4, 0);
            System.out.print(midLeft);
            for (int i = 0; i < 78; i++) {
                System.out.print(dash);
            }
            System.out.print(midRight);

            GotoXY(5, 0);
            printBoxLine(" ", mBoxLine.Alignment.LEFT);

//get iterator to arraylist
            Iterator<mBoxLine> lines_iterator = mBox.getContent().iterator();

            Integer n = 1;
//loop stop when no more line
            while (lines_iterator.hasNext()) {
                GotoXY(5 + n, 0);
                //get a line in array
                mBoxLine line = lines_iterator.next();
                printBoxLine(n.toString() + ") " + line.text, line.alignment);
                n++;
            }

            for (int i = n + 5; i < 23; i++) {
                GotoXY(i, 0);
                printBoxLine(" ", mBoxLine.Alignment.LEFT);
            }

            GotoXY(22, 0);
            System.out.print(midLeft);
            for (int i = 0; i < 78; i++) {
                System.out.print(dash);
            }
            System.out.print(midRight);

            GotoXY(23, 0);
            printStatusBar();

            GotoXY(24, 0);
            System.out.print(bottomLeft);
            for (int i = 0; i < 78; i++) {
                System.out.print(dash);
            }
            System.out.print(bottomRight);

            int prompt_size = prompt.length() + 11;
            GotoXY(23, prompt_size);


        } else {
            clearScreen();
            GotoXY(1, 1);
            System.out.printf("%c", topLeft1);
            for (int i = 0; i < width - 2; i++) {
                System.out.print(dash1);
            }
            System.out.print(topRight1);


            GotoXY(2, 0);
            printBoxLine(mBox.getHeader(), mBoxLine.Alignment.CENTER);

            GotoXY(3, 0);
            printBoxLine(" ", mBoxLine.Alignment.LEFT);

            GotoXY(4, 0);
            System.out.print(midLeft1);
            for (int i = 0; i < 78; i++) {
                System.out.print(dash1);
            }
            System.out.print(midRight1);

            GotoXY(5, 0);
            printBoxLine(" ", mBoxLine.Alignment.LEFT);

//get iterator to arraylist
            Iterator<mBoxLine> lines_iterator = mBox.getContent().iterator();

            Integer n = 1;
//loop stop when no more line
            while (lines_iterator.hasNext()) {
                GotoXY(5 + n, 0);
                //get a line in array
                mBoxLine line = lines_iterator.next();
                printBoxLine(n.toString() + ") " + line.text, line.alignment);
                n++;
            }

            for (int i = n + 5; i < 23; i++) {
                GotoXY(i, 0);
                printBoxLine(" ", mBoxLine.Alignment.LEFT);
            }

            GotoXY(22, 0);
            System.out.print(midLeft1);
            for (int i = 0; i < 78; i++) {
                System.out.print(dash1);
            }
            System.out.print(midRight1);

            GotoXY(23, 0);
            printStatusBar();

            GotoXY(24, 0);
            System.out.print(bottomLeft1);
            for (int i = 0; i < 78; i++) {
                System.out.print(dash1);
            }
            System.out.print(bottomRight1);

            int prompt_size = prompt.length() + 11;
            GotoXY(23, prompt_size);
        }
    }


    //hide cursor
    public void hideCursor() {
        if (mode == "E") {
            System.out.printf("%c[?25l", escCode);
        } else {
            System.out.printf("%c[?25l", escCode);
        }
    }

    //show cursor
    public void showCursor() {
        if (mode == "E") {
            System.out.printf("%c[?25h", escCode);
        } else {
            System.out.printf("%c[?25h", escCode);
        }
    }

    //move cursor
    public static void GotoXY(int x, int y) {
        System.out.printf("%c[%d;%df", escCode, x, y);
    }

    //invert bg colour
    public static void invertColor() {
        System.out.printf("%c[7m", escCode);
    }

    //change to normal colour
    public static void normalColor() {
        System.out.printf("%c[0m", escCode);
    }

    //clear screen
    public void clearScreen() {
        if (mode == "E") {
            hideCursor();
            if (reverseBackground)
                invertColor();
            else
                normalColor();

            for (int i = 1; i <= height; i++) {
                for (int j = 1; j <= width; j++) {
                    GotoXY(i, j);
                    System.out.print(' ');
                }
            }
        } else {
                hideCursor();
                if (reverseBackground)
                    invertColor();
                else
                    normalColor();

                for (int i = 1; i <= height; i++) {
                    for (int j = 1; j <= width; j++) {
                        GotoXY(i, j);
                        System.out.print(' ');
                    }
                }
            }
        }


    public static void printSpace(int n) {
        for (int i = 0; i <= n; i++) {
            System.out.print(' ');
        }
    }

    //print line in a box with alignment
    private void printBoxLine(String text, mBoxLine.Alignment alignment) {
        if (mode == "E") {
            if (reverseBackground)
                invertColor();
            else
                normalColor();

            int textLength = text.length();
            int padding;
            boolean isEven;
            if (textLength % 2 == 0) { // 21 % 2 = 1 20 / 2 = 10

                padding = (80 - (6 + textLength)) / 2;
                isEven = true;
            } else {
                padding = (80 - (6 + textLength + 1)) / 2;
                isEven = false;
            }

            switch (alignment) {
                case RIGHT:
                    System.out.print(bar + " ");
                    printSpace(75 - textLength);
                    System.out.print(text);
                    System.out.print(" " + bar);
                    break;

                case CENTER:
                    if (isEven) {
                        System.out.print(bar + " ");
                        printSpace(padding);
                        System.out.print(text);
                        printSpace(padding);
                        System.out.print(" " + bar);
                        break;
                    } else {
                        System.out.print(bar + " ");
                        printSpace(padding);
                        System.out.print(text);
                        printSpace(padding + 1);
                        System.out.print(" " + bar);
                        break;
                    }

                default:
                    System.out.print(bar + " ");
                    System.out.print(text);
                    printSpace(75 - textLength);
                    System.out.print(" " + bar);
                    break;
            }
        } else {
            if (reverseBackground)
                invertColor();
            else
                normalColor();


            int textLength = text.length();
            int padding;
            boolean isEven;
            if (textLength % 2 == 0) { // 21 % 2 = 1 20 / 2 = 10

                padding = (80 - (6 + textLength)) / 2;
                isEven = true;
            } else {
                padding = (80 - (6 + textLength + 1)) / 2;
                isEven = false;
            }

            switch (alignment) {
                case RIGHT:
                    System.out.print(bar1 + " ");
                    printSpace(75 - textLength);
                    System.out.print(text);
                    System.out.print(" " + bar);
                    break;

                case CENTER:
                    if (isEven) {
                        System.out.print(bar1 + " ");
                        printSpace(padding);
                        System.out.print(text);
                        printSpace(padding);
                        System.out.print(" " + bar1);
                        break;
                    } else {
                        System.out.print(bar1 + " ");
                        printSpace(padding);
                        System.out.print(text);
                        printSpace(padding + 1);
                        System.out.print(" " + bar1);
                        break;
                    }

                default:
                    System.out.print(bar1 + " ");
                    System.out.print(text);
                    printSpace(75 - textLength);
                    System.out.print(" " + bar1);
                    break;
            }
        }
    }

    private void printStatusBar() {
        if (mode == "E") {
            System.out.print(bar + " ");
            System.out.print(prompt + "(1-" + funcCount + ") >");
            GotoXY(23, 78);
            System.out.print(mode + " " + bar);
        } else {
            System.out.print(bar1 + " ");
            System.out.print(prompt + "(1-" + funcCount + ") >");
            GotoXY(23, 78);
            System.out.print(mode + " " + bar1);
        }
    }

    public void printInvalidInput() {
        if (mode == "E") {
            GotoXY(23, 0);
            System.out.print(bar + " ");
            System.out.print(prompt + "(1-" + funcCount + ") Invalid input. Please try again. >");
            GotoXY(23, 78);
            System.out.print(mode + " " + bar);
            int prompt_size = prompt.length() + 11 + 33;
            GotoXY(23, prompt_size);
        } else {
            GotoXY(23, 0);
            System.out.print(bar1 + " ");
            System.out.print(prompt + "(1-" + funcCount + ") Invalid input. Please try again. >");
            GotoXY(23, 78);
            System.out.print(mode + " " + bar1);
            int prompt_size = prompt.length() + 11 + 33;
            GotoXY(23, prompt_size);
        }
    }

    //delay func to print progress bar on status bar
    public void printProgress() {
        if (mode == "E") {
            for (int j = 1; j <= width - 1; j++) {
                GotoXY(23, j);
                System.out.print(' ');
            }

            GotoXY(23, 0);
            System.out.print(bar + " ");
            System.out.print(prompt + " #");

            for (int i = 0; i < 6; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
                System.out.print("#");
            }
            System.out.print(" Done! Going back to main menu");
        } else {
            for (int j = 1; j <= width - 1; j++) {
                GotoXY(23, j);
                System.out.print(' ');
            }

            GotoXY(23, 0);
            System.out.print(bar1 + " ");
            System.out.print(prompt + " #");

            for (int i = 0; i < 6; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
                System.out.print("#");
            }
            System.out.print(" Done! Going back to main menu");
        }
    }
}