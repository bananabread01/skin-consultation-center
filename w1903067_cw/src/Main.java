import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //creating an instance of object
        WestMinsterSkinConsultationManager WS = new WestMinsterSkinConsultationManager();

        while (true){
            menuConsole();

            Scanner scan = new Scanner(System.in);
            System.out.print("Enter an option: ");
            //get user input
            String menuInput = scan.nextLine().toUpperCase();

            switch (menuInput) {
                case "1" :
                    WS.newDoctor();
                    break;
                case "2" :
                    WS.deleteDoctor();
                    break;
                case "3" :
                    WS.printDoctor();
                    break;
                case "4" :
                    WS.storeProgramData();
                    break;
                case "5":
                    WS.loadProgramData();
                    break;
                case "6":
                    GUI myCenter = new GUI(WS);
                    break;
                case "7":
                    exitProgram();
                    break;
                default:
                    System.out.println("---Invalid input----");
            }
        }
    }

    //method to display out options in the menu console
    private static void menuConsole(){
        String menu = ("""
                    -----------------------------------------------------------------
                    ----Welcome to WestMinster Consultation Centre Console System----
                    OPTION 1: Add doctor
                    OPTION 2: Delete doctor
                    OPTION 3: Print list of doctors
                    OPTION 4: Save file
                    OPTION 5: Load information
                    OPTION 6: GUI
                    OPTION 7: Exit program
                    ---------------------------------------------------------------\n""");

        System.out.println(menu);
    }

    //method to exit program
    public static void exitProgram() {
        System.out.println("Thank you!");
        System.exit(0);
    }
}


