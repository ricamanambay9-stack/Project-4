
import java.util.Scanner;

public class BHSDirectorySystem{

    static Scanner sc = new Scanner(System.in);

    // BHS data
    static String[] bhsNames = new String[10];
    static int bhsCount = 0;

    // Personnel data
    static String[][] personnelNames = new String[10][5];
    static String[][] personnelRoles = new String[10][5];
    static int[] personnelCount = new int[10];

    public static void main(String[] args) {
        // Show welcome screen
        if (!showWelcomeScreen()) {
            return; // exit program if user chooses Exit
        }

        int choice;
        do {
            showMenu();
            choice = getMenuChoice();

            switch (choice) {
                case 1: addBHS(); break;
                case 2: searchBHSByName(); break;
                case 3: editBHS(); break;
                case 4: displayAllBHS(); break;
                case 5: addPersonnel(); break;
                case 6: editPersonnel(); break;
                case 7: removePersonnel(); break;
                case 8: countPersonnel(); break;
                case 9: System.out.println("Program exited. Thank you!");return; 
                default: System.out.println("Invalid choice!Try Again ");
            }
        } while (choice != 9);
    }

    // ================= WELCOME SCREEN =================
    static boolean showWelcomeScreen() {
        System.out.println("====================================");
        System.out.println("WELCOME TO BARANGAY HEALTH STATION");
        System.out.println("====================================");
        System.out.println("1. Continue");
        System.out.println("2. Exit");
        System.out.print("Choose: ");

        int choice = getMenuChoice();

        if (choice == 1) {
            return true;   // continue to main menu
        } else if (choice == 2) {
            System.out.println("Thank you for using the system. Goodbye!");
            return false;  // exit program
        } else {
            System.out.println("Invalid choice!");
            return showWelcomeScreen(); // show again if invalid
        }
    }

    // ================= MENU =================
    static void showMenu() {
        System.out.println("\n=== BHS DIRECTORY SYSTEM ===");
        System.out.println("1. Add BHS");
        System.out.println("2. Search BHS by Name");
        System.out.println("3. Edit BHS Information");
        System.out.println("4. Display All BHS");
        System.out.println("5. Add Personnel");
        System.out.println("6. Edit Personnel");
        System.out.println("7. Remove Personnel");
        System.out.println("8. Count Personnel");
        System.out.println("9. Exit");
        System.out.print("Choose: ");
    }

    // ================= VALIDATION =================
    static int getMenuChoice() {
        while (!sc.hasNextInt()) {
            System.out.print("Enter a number only: ");
            sc.next();
        }
        return sc.nextInt();
    }

    static boolean validateName(String name) {
        return name.matches("^[A-Za-z]+( [A-Za-z]+)*$");
    }

    static boolean validateRole(String role) {
        return role.matches("(?i)^(Midwife|BHW|Nurse)$");
    }

    // ================= BHS METHODS =================
    static void addBHS() {
        if (bhsCount >= bhsNames.length) {
            System.out.println("BHS list is full.");
            return;
        }

        sc.nextLine();
        System.out.println("\n===============================");
        System.out.print("Enter BHS Name: ");
        String name = sc.nextLine();

        if (!validateName(name)) {
            System.out.println("Invalid name! Letters and spaces only.");
            return;
        }

        bhsNames[bhsCount++] = name;
        System.out.println("BHS added successfully!");
    }

    static void searchBHSByName() {
        sc.nextLine();
        System.out.println("\n===============================");
        System.out.print("Enter BHS Name to search: ");
        String search = sc.nextLine();

        for (int i = 0; i < bhsCount; i++) {
            if (bhsNames[i].equalsIgnoreCase(search)) {
                System.out.println("BHS found at index " + (i + 1));
                return;
            }
        }
        System.out.println("BHS not found.");
    }

    static void editBHS() {
        displayAllBHS();
        System.out.println("\n===============================");
        System.out.print("Enter BHS index to edit: ");
        int userIndex = sc.nextInt();
        int index = userIndex - 1; // convert to 0-based

        if (index < 0 || index >= bhsCount) {
            System.out.println("Invalid index.");
            return;
        }

        sc.nextLine();
        System.out.println("\n===============================");
        System.out.print("Enter new BHS name: ");
        String newName = sc.nextLine();

        if (!validateName(newName)) {
            System.out.println("Invalid name!");
            return;
        }

        bhsNames[index] = newName;
        System.out.println("BHS updated successfully.");
    }

    static void displayAllBHS() {
        if (bhsCount == 0) {
            System.out.println("No BHS available.");
            return;
        }

        System.out.println("\n==========LIST OF BHS============");
        for (int i = 0; i < bhsCount; i++) {
        System.out.println((i + 1) + ". " + bhsNames[i]); // 1-based index
    }
}

    // ================= PERSONNEL METHODS =================
    static void addPersonnel() {
        displayAllBHS();
        System.out.println("\n===============================");
        System.out.print("Select BHS index: ");
        int userIndex = sc.nextInt();
        int index = userIndex - 1; // convert to 0-based

        if (index < 0 || index >= bhsCount) {
            System.out.println("Invalid BHS index.");
            return;
        }

        if (personnelCount[index] >= 5) {
            System.out.println("Personnel list is full.");
            return;
        }

        sc.nextLine();
        System.out.println("\n===============================");
        System.out.print("Enter Personnel Name: ");
        String name = sc.nextLine();

        if (!validateName(name)) {
            System.out.println("Invalid name! Letters only.");
            return;
        }

        System.out.println("\n===============================");
        System.out.print("Enter Role (Midwife/BHW/Nurse): ");
        String role = sc.nextLine();

        if (!validateRole(role)) {
            System.out.println("Invalid role! Choose Midwife, BHW, or Nurse only.");
            return;
        }

        personnelNames[index][personnelCount[index]] = name;
        personnelRoles[index][personnelCount[index]] = role;
        personnelCount[index]++;

        System.out.println("Personnel added successfully.");
    }

    static void editPersonnel() {
        displayAllBHS();
        System.out.println("\n===============================");
        System.out.print("Select BHS index: ");
        int userBHSIndex = sc.nextInt();
        int bhsIndex = userBHSIndex - 1; // convert to 0-based

        if (bhsIndex < 0 || bhsIndex >= bhsCount || personnelCount[bhsIndex] == 0) {
            System.out.println("No personnel found.");
            return;
        }

        for (int i = 0; i < personnelCount[bhsIndex]; i++) {
            System.out.println((i + 1) + ". " + personnelNames[bhsIndex][i] +
                    " - " + personnelRoles[bhsIndex][i]); // show 1-based index
        }

        System.out.println("\n===============================");
        System.out.print("Select personnel index: ");
        int userPIndex = sc.nextInt();
        int pIndex = userPIndex - 1; // convert to 0-based
        sc.nextLine();

        if (pIndex < 0 || pIndex >= personnelCount[bhsIndex]) {
            System.out.println("Invalid personnel index.");
            return;
        }

        System.out.print("Enter new role (Midwife/BHW/Nurse): ");
        String newRole = sc.nextLine();

        if (!validateRole(newRole)) {
            System.out.println("Invalid role!");
            return;
        }

        personnelRoles[bhsIndex][pIndex] = newRole;
        System.out.println("Personnel updated.");
    }

    static void removePersonnel() {
        displayAllBHS();
        System.out.println("\n===============================");
        System.out.print("Select BHS index: ");
        int userBHSIndex = sc.nextInt();
        int bhsIndex = userBHSIndex - 1; // convert to 0-based

        if (bhsIndex < 0 || bhsIndex >= bhsCount || personnelCount[bhsIndex] == 0) {
            System.out.println("No personnel to remove.");
            return;
        }

        for (int i = 0; i < personnelCount[bhsIndex]; i++) {
            System.out.println((i + 1) + ". " + personnelNames[bhsIndex][i]); // 1-based
        }

        System.out.println("\n===============================");
        System.out.print("Enter index to remove: ");
        int userRemoveIndex = sc.nextInt();
        int removeIndex = userRemoveIndex - 1; // convert to 0-based

        if (removeIndex < 0 || removeIndex >= personnelCount[bhsIndex]) {
            System.out.println("Invalid index.");
            return;
        }

        for (int i = removeIndex; i < personnelCount[bhsIndex] - 1; i++) {
            personnelNames[bhsIndex][i] = personnelNames[bhsIndex][i + 1];
            personnelRoles[bhsIndex][i] = personnelRoles[bhsIndex][i + 1];
        }

        personnelCount[bhsIndex]--;
        System.out.println("Personnel removed successfully.");
    }

    static void countPersonnel() {
        displayAllBHS();
        System.out.println("\n===============================");
        System.out.print("Select BHS index: ");
        int userIndex = sc.nextInt();
        int index = userIndex - 1; // convert to 0-based

        if (index < 0 || index >= bhsCount) {
            System.out.println("Invalid BHS index.");
            return;
        }

        System.out.println("\n===============================");
        System.out.println("Total Personnel for " + bhsNames[index] + ": " + personnelCount[index]);
        // Display personnel details
        if (personnelCount[index] > 0) {
            System.out.println("Personnel List:");
            for (int i = 0; i < personnelCount[index]; i++) {
                System.out.println((i + 1) + ". " + personnelNames[index][i] + " - " + personnelRoles[index][i]);
            }
        } else {
            System.out.println("No personnel assigned to this BHS.");
        }
    }
}