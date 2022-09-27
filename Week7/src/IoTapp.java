//lcd1 and lcd2 have different width and height

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class IoTapp {

    enum MenuType {

        MAIN,

        LOGIN,

        FUNCTION,FUNCTION2,
        TEMP,

        CONSOLE_SETTING,

        CONSOLE_SETTING_MODE,

        RESTART,

        SHUTDOWN,

    }

    public static MenuType currentMenu = MenuType.MAIN;

    public static void main(String[] args) throws IOException {

        LCD lcd1 = new LCD(80, 25);

        lcd1.hideCursor();

        lcd1.clearScreen();

        showMainMenu(lcd1);
        Scanner console = new Scanner(System.in);

        //FileWriter file1 = new FileWriter("D:/Workspace/Week7/setting.dat");
        //Scanner reader = new Scanner(new File("D:/Workspace/Week7/setting.dat"));//read file
        //if (!reader.hasNext()) {
            //file1.write("\nMode: E");//set mode to N
            //file1.write("\nColour: 1");
        //}file1.flush();

        while (true) {
            //setting.dat
            //create file with false = overwrite
            FileWriter file = new FileWriter("D:/Workspace/Week7/setting.dat",false);
            int user_input = console.nextInt();
            if (lcd1.mode == "E") {//if mode is E
                file.write("\nMode: E");//write E to file
                if (lcd1.reverseBackground == false) {//and if lcd is false
                    file.write("\nColour: 1");//write 1 to file (normal)
                } else if (lcd1.reverseBackground == true) {
                    file.write("\nColour: 2");//write 2 to file (reverse)
                }
                file.flush();//clear file
                file.close();
                switch (currentMenu) {
                    case MAIN:
                        if (user_input == 1 || user_input == 2 || user_input == 3 || user_input == 4 || user_input == 5) {

                            switch (user_input) {

                                case 1:
                                    currentMenu = MenuType.LOGIN;
                                    showLogin(lcd1);
                                    break;
                                case 2:
                                    currentMenu = MenuType.CONSOLE_SETTING;
                                    showConsoleSetting(lcd1);
                                    break;
                                case 3:
                                    currentMenu = MenuType.RESTART;
                                    showRestart(lcd1);currentMenu = MenuType.MAIN;
                                    showMainMenu(lcd1);
                                    break;
                                case 4:
                                    currentMenu = MenuType.SHUTDOWN;
                                    showShutdown(lcd1);currentMenu = MenuType.MAIN;
                                    showMainMenu(lcd1);
                                    break;
                                case 5:
                                    lcd1.clearScreen(); /*showQuit();*/
                                    System.exit(0);
                                    break;
                                default:
                                    lcd1.printInvalidInput();

                            }
                        } else {
                            lcd1.printInvalidInput();
                            break;
                        }
                        break;
                    case LOGIN:
                        switch (user_input) {
                            case 1: currentMenu = MenuType.TEMP; showChangeACSetP(lcd1);currentMenu = MenuType.MAIN;
                                showMainMenu(lcd1);break;
                            case 2: currentMenu = MenuType.FUNCTION; showIPSettingMode(lcd1);break;
                            case 3: currentMenu = MenuType.FUNCTION2; showAntiSettingMode(lcd1);break;
                            case 4:
                                currentMenu = MenuType.MAIN;
                                showMainMenu(lcd1);
                                break;
                            default:
                                lcd1.printInvalidInput();
                        }
                        break;
                    case RESTART:
                    case SHUTDOWN:
                        currentMenu = MenuType.MAIN;
                        showMainMenu(lcd1);
                        break;
                    case CONSOLE_SETTING:
                        switch (user_input) {
                            case 1:
                                currentMenu = MenuType.CONSOLE_SETTING_MODE;
                                showConsoleSettingMode(lcd1);
                                break;
                            case 2:
                                lcd1.reverseBackgroundMode();
                                currentMenu = MenuType.
                                        MAIN;
                                showMainMenu(lcd1);
                                break;
                            case 3:
                                currentMenu = MenuType.MAIN;
                                showMainMenu(lcd1);
                                break;
                            default:
                                lcd1.printInvalidInput();
                        }

                        break;
                    case CONSOLE_SETTING_MODE:
                        switch (user_input) {
                            case 0:
                                lcd1.setMode("N");
                                currentMenu = MenuType.MAIN;
                                showMainMenu(lcd1);
                                break;
                            case 1:
                                lcd1.setMode("E");
                                currentMenu = MenuType.MAIN;
                                showMainMenu(lcd1);
                                break;
                            default:
                                lcd1.printInvalidInput();
                        }
                        break;
                    case FUNCTION:
                        switch (user_input) {
                            case 1:
                                lcd1.setPrompt("Enabling Camera...");
                                lcd1.printProgress();
                                currentMenu = MenuType.MAIN;
                                showMainMenu(lcd1);
                                break;
                            case 2:
                                lcd1.setPrompt("Disabling Camera...");
                                lcd1.printProgress();
                                currentMenu = MenuType.MAIN;
                                showMainMenu(lcd1);
                                break;
                            default:
                                lcd1.printInvalidInput();
                                break;
                        }
                        break;
                    case FUNCTION2:
                        switch (user_input) {
                            case 1:
                                lcd1.setPrompt("Turning on...");
                                lcd1.printProgress();
                                currentMenu = MenuType.MAIN;
                                showMainMenu(lcd1);
                                break;
                            case 2:
                                lcd1.setPrompt("Turning off...");
                                lcd1.printProgress();
                                currentMenu = MenuType.MAIN;
                                showMainMenu(lcd1);
                                break;
                            default:
                                lcd1.printInvalidInput();
                                break;
                        }
                        break;
                    case TEMP:
                        currentMenu = MenuType.MAIN;
                        showMainMenu(lcd1);
                        break;
                }
            } else {
                file.write("\nMode: N");
                if (!lcd1.reverseBackground) {
                    file.write("\nColour: 1");
                } else if (lcd1.reverseBackground) {
                    file.write("\nColour: 2");
                }
                file.flush();
                file.close();
                //int user_input = console.nextInt();

                switch (currentMenu) {

                    case MAIN:

                        if (user_input == 1 || user_input == 2 || user_input == 3 || user_input == 4 || user_input == 5) {

                            switch (user_input) {

                                case 1:
                                    currentMenu = MenuType.LOGIN;
                                    showLogin(lcd1);
                                    break;
                                case 2:
                                    currentMenu = MenuType.CONSOLE_SETTING;
                                    showConsoleSetting(lcd1);
                                    break;
                                case 3:
                                    currentMenu = MenuType.RESTART;
                                    showRestart(lcd1);currentMenu = MenuType.MAIN;
                                    showMainMenu(lcd1);
                                    break;
                                case 4:
                                    currentMenu = MenuType.SHUTDOWN;
                                    showShutdown(lcd1);currentMenu = MenuType.MAIN;
                                    showMainMenu(lcd1);
                                    break;
                                case 5:
                                    lcd1.clearScreen(); /*showQuit();*/
                                    System.exit(0);
                                    break;
                                default:
                                    lcd1.printInvalidInput();

                            }
                        } else {
                            lcd1.printInvalidInput();
                            break;
                        }
                        break;
                    case LOGIN:
                        switch (user_input) {
                            case 1: currentMenu = MenuType.TEMP; showChangeACSetP(lcd1);currentMenu = MenuType.MAIN;
                                showMainMenu(lcd1);break;
                            case 2: currentMenu = MenuType.FUNCTION; showIPSettingMode(lcd1);break;
                            case 3: currentMenu = MenuType.FUNCTION2; showAntiSettingMode(lcd1);break;
                            case 4:
                                currentMenu = MenuType.MAIN;
                                showMainMenu(lcd1);
                                break;
                            default:
                                lcd1.printInvalidInput();
                        }
                        break;
                    case RESTART:
                    case SHUTDOWN:
                        currentMenu = MenuType.MAIN;
                        showMainMenu(lcd1);
                        break;
                    case CONSOLE_SETTING:
                        switch (user_input) {
                            case 1:
                                currentMenu = MenuType.CONSOLE_SETTING_MODE;
                                showConsoleSettingMode(lcd1);
                                break;
                            case 2:
                                lcd1.reverseBackgroundMode();
                                currentMenu = MenuType.MAIN;
                                showMainMenu(lcd1);
                                break;
                            case 3:
                                currentMenu = MenuType.MAIN;
                                showMainMenu(lcd1);
                                break;
                            default:
                                lcd1.printInvalidInput();
                        }

                        break;
                    case CONSOLE_SETTING_MODE:
                        switch (user_input) {
                            case 0:
                                lcd1.setMode("N");
                                currentMenu = MenuType.MAIN;
                                showMainMenu(lcd1);
                                break;
                            case 1:
                                lcd1.setMode("E");
                                currentMenu = MenuType.MAIN;
                                showMainMenu(lcd1);
                                break;
                            default:
                                lcd1.printInvalidInput();
                        }
                        break;
                    case FUNCTION:
                        switch (user_input) {
                            case 1:
                                lcd1.setPrompt("Enabling Camera...");
                                lcd1.printProgress();
                                currentMenu = MenuType.MAIN;
                                showMainMenu(lcd1);
                                break;
                            case 2:
                                lcd1.setPrompt("Disabling Camera...");
                                lcd1.printProgress();
                                currentMenu = MenuType.MAIN;
                                showMainMenu(lcd1);
                                break;
                            default:
                                lcd1.printInvalidInput();
                                break;
                        }
                        break;
                    case FUNCTION2:
                        switch (user_input) {
                            case 1:
                                lcd1.setPrompt("Turning on...");
                                lcd1.printProgress();
                                currentMenu = MenuType.MAIN;
                                showMainMenu(lcd1);
                                break;
                            case 2:
                                lcd1.setPrompt("Turning off...");
                                lcd1.printProgress();
                                currentMenu = MenuType.MAIN;
                                showMainMenu(lcd1);
                                break;
                            default:
                                lcd1.printInvalidInput();
                                break;
                        }
                        break;
                    case TEMP:
                        currentMenu = MenuType.MAIN;
                        showMainMenu(lcd1);
                        break;

                }
            }
        }
    }

    public static messageBox createFunctionMenu () {
        messageBox msgBox = new messageBox ("- Stamford Function Menu - ");
        mBoxLine mainMenu_l1 = new mBoxLine("Change AC Temperature set point", mBoxLine.Alignment.LEFT);
        msgBox.addLine(mainMenu_l1);

        mBoxLine mainMenu_l2 = new mBoxLine("Enable/Disable IP Camera", mBoxLine.Alignment.LEFT);
        msgBox.addLine(mainMenu_l2);

        mBoxLine mainMenu_l3 = new mBoxLine("Turn anti-theft system on/off ", mBoxLine.Alignment.LEFT);
        msgBox.addLine(mainMenu_l3);

        mBoxLine mainMenu_l4 = new mBoxLine("Back", mBoxLine.Alignment.LEFT);
        msgBox.addLine(mainMenu_l4);

        return msgBox;
    }

    public static messageBox createMainMenu () {
        messageBox msgBox = new messageBox ("- Stamford Menu System - ");

        mBoxLine mainMenu_l1 = new mBoxLine("Login", mBoxLine.Alignment.LEFT);
        msgBox.addLine(mainMenu_l1);

        mBoxLine mainMenu_l2 = new mBoxLine("Console Setting", mBoxLine.Alignment.LEFT);
        msgBox.addLine(mainMenu_l2);

        mBoxLine mainMenu_l3 = new mBoxLine("Restart", mBoxLine.Alignment.LEFT);
        msgBox.addLine(mainMenu_l3);

        mBoxLine mainMenu_l4 = new mBoxLine("Shutdown", mBoxLine.Alignment.LEFT);

        msgBox.addLine(mainMenu_l4);
        mBoxLine mainMenu_l5 = new mBoxLine("Exit", mBoxLine.Alignment.LEFT);

        msgBox.addLine(mainMenu_l5);

        return msgBox;
    }


    public static messageBox createConsoleSettingMenu () {
        messageBox msgBox = new messageBox ("- Console Setting - ");

        mBoxLine mainMenu_l1 = new mBoxLine("Change Console mode", mBoxLine.Alignment.LEFT);
        msgBox.addLine(mainMenu_l1);

        mBoxLine mainMenu_l2 = new mBoxLine("Reverse background color", mBoxLine.Alignment.LEFT);
        msgBox.addLine(mainMenu_l2);

        mBoxLine mainMenu_l3 = new mBoxLine("Back", mBoxLine.Alignment.LEFT);
        msgBox.addLine(mainMenu_l3);

        return msgBox;
    }

    public static messageBox createConsoleSettinModegMenu (LCD lcd) {
        messageBox msgBox = new messageBox ("- Console Setting, Mode - ");
        String mode = lcd.getMode();
        String final_mode = mode.equals("E") ? "Extended" : "Normal";

        mBoxLine mainMenu_l1 = new mBoxLine("Current Mode is " + final_mode , mBoxLine.Alignment.LEFT);
        msgBox.addLine(mainMenu_l1);
        return msgBox;
    }

    public static messageBox createRestartMenu () {
        messageBox msgBox = new messageBox ("- System Restart - ");

        mBoxLine mainMenu_l1 = new mBoxLine("System is being restarted, please be patient.", mBoxLine.Alignment.LEFT);
        msgBox.addLine(mainMenu_l1);

        return msgBox;
        }

    public static messageBox createShutdownMenu () {
        messageBox msgBox = new messageBox ("- System Shutdown - ");

        mBoxLine mainMenu_l1 = new mBoxLine("System is being shutdown, please be patient.", mBoxLine.Alignment.LEFT);
                msgBox.addLine(mainMenu_l1);
        return msgBox;
    }

    public static void showMainMenu(LCD lcd) {
        messageBox mainMenu_msgBox = createMainMenu ();
        lcd.setPrompt("Select a function");
        lcd.setFuncCount(mainMenu_msgBox.getContent().size());
        lcd.showMessageBox(mainMenu_msgBox);
    }

    public static void showLogin(LCD lcd) {
        messageBox loginMenu_msgBox = createFunctionMenu ();
        lcd.setPrompt("Select a function");
        lcd.setFuncCount(loginMenu_msgBox.getContent().size());
        lcd.showMessageBox(loginMenu_msgBox);
    }

    public static void showRestart(LCD lcd) {
        messageBox restartMenu_msgBox = createRestartMenu ();
        lcd.setPrompt("Restarting");
        lcd.setFuncCount(restartMenu_msgBox.getContent().size());
        lcd.showMessageBox(restartMenu_msgBox);
        lcd.printProgress();
    }

    public static void showShutdown(LCD lcd) {
        messageBox shutdownMenu_msgBox = createShutdownMenu ();
        lcd.setPrompt("Shutting down");
        lcd.setFuncCount(shutdownMenu_msgBox.getContent().size());
        lcd.showMessageBox(shutdownMenu_msgBox);
        lcd.printProgress();
        }

    public static void showConsoleSetting(LCD lcd) {
        messageBox consoleSettingMenu_msgBox = createConsoleSettingMenu ();
        lcd.setPrompt("Select a function");
        lcd.setFuncCount(consoleSettingMenu_msgBox.getContent().size());
        lcd.showMessageBox(consoleSettingMenu_msgBox);
    }

    public static void showConsoleSettingMode(LCD lcd) {
        messageBox consoleSettingModeMenu_msgBox = createConsoleSettinModegMenu (lcd);
        lcd.setPrompt("Choose console mode: 0 for Normal, 1 for Extended");
        lcd.setFuncCount(consoleSettingModeMenu_msgBox.getContent().size());
        lcd.showMessageBox(consoleSettingModeMenu_msgBox);
        }

    public static messageBox createChangeACSetPMenu() {
        messageBox msgBox = new messageBox (" - Applying New Temperature Set Point -  ");

        mBoxLine mainMenu_l1 = new mBoxLine("\"New temperature set point will be in effect soon...", mBoxLine.Alignment.LEFT);
        msgBox.addLine(mainMenu_l1);

        return msgBox;
    }

    public static void showChangeACSetP(LCD lcd) {
        messageBox ACSetPMenu_msgBox = createChangeACSetPMenu ();
        lcd.setPrompt("Restarting");
        lcd.setFuncCount(ACSetPMenu_msgBox.getContent().size());
        lcd.showMessageBox(ACSetPMenu_msgBox);
        lcd.printProgress();
    }

    public static messageBox createIPSetPMenu() {
        messageBox msgBox = new messageBox (" - Turn camera system on/off -  ");

        mBoxLine mainMenu_l1 = new mBoxLine("Enable", mBoxLine.Alignment.LEFT);
        msgBox.addLine(mainMenu_l1);

        mBoxLine mainMenu_l2 = new mBoxLine("Disable", mBoxLine.Alignment.LEFT);
        msgBox.addLine(mainMenu_l2);
        return msgBox;
    }

    public static void showIPSettingMode(LCD lcd) {
        messageBox IPSettingMenu_msgBox = createIPSetPMenu();
        lcd.setPrompt("Select a function:");
        lcd.setFuncCount(IPSettingMenu_msgBox.getContent().size());
        lcd.showMessageBox(IPSettingMenu_msgBox);

    }

    public static messageBox createAntiPMenu() {
        messageBox msgBox = new messageBox (" - Turn anti-theft system on/off -  ");

        mBoxLine mainMenu_l1 = new mBoxLine("On", mBoxLine.Alignment.LEFT);
        msgBox.addLine(mainMenu_l1);

        mBoxLine mainMenu_l2 = new mBoxLine("Off", mBoxLine.Alignment.LEFT);
        msgBox.addLine(mainMenu_l2);

        return msgBox;
    }

    public static void showAntiSettingMode(LCD lcd) {
        messageBox AntiSettingMenu_msgBox = createAntiPMenu();
        lcd.setPrompt("Select a function:");
        lcd.setFuncCount(AntiSettingMenu_msgBox.getContent().size());
        lcd.showMessageBox(AntiSettingMenu_msgBox);

    }
}

















 

 