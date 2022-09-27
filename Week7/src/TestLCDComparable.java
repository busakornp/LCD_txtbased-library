import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;


public class TestLCDComparable {
    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<LCD> lcd_list = new ArrayList<>();
        lcd_list.add(new LCD(80, 25));
        lcd_list.add(new LCD(90, 25));
        lcd_list.add(new LCD(40, 25));
        lcd_list.add(new LCD(40, 10));
        lcd_list.add(new LCD(100, 10));

        System.out.println(lcd_list);
        Collections.sort(lcd_list);
        System.out.println(lcd_list);
    }
}
