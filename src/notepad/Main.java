package notepad;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static List<Record> recordList = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            System.out.println("Enter command ('help' for help):");
            String cmd = scanner.next();
            switch (cmd) {
                case "createperson":
                case "cp":
                    createPerson();
                    break;
                case "createnote":
                case "cn":
                    createNote();
                    break;
                case "list":
                    printList();
                    break;
                case "remove":
                    removeById();
                    break;
                case "find":
                    find();
                    break;
                case "help":
                    showHelp();
                    break;
                case "exit":
                    return;
                default:
                    System.out.println("It isn't a command");
            }
        }
    }

    private static void find() {
        System.out.println("Find what?");
        String str = askString();
        for (Record r : recordList) {
            if (r.hasSubstring(str)) {
                System.out.println(r);
            }
        }
    }

    private static void createNote() {
        System.out.println("Enter text:");
        String txt = askString();
        Note note = new Note();
        note.setText(txt);
        recordList.add(note);
        System.out.println(note);
    }

    private static void showHelp() {
        System.out.println("createPerson - bla bla bla bla");
        System.out.println("remove - bla bla bla bla");
        System.out.println("bla bla bla bla");
        System.out.println("bla bla bla bla");
    }

    private static void removeById() {
        System.out.println("Enter ID to remove:");
        int id = askInt();
        for (int i = 0; i < recordList.size(); i++) {
            Record p = recordList.get(i);
            if (id == p.getId()) {
                recordList.remove(i);
                break;
            }
        }
    }

    private static int askInt() {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                scanner.next(); // skip wrong input
                System.out.println("It isn't a number");
            }
        }
    }


    //    private static void removeById() {
//        System.out.println("Enter ID to remove:");
//        int id = scanner.nextInt();
//        for (Person p : recordList) {
//            if (id == p.getId()) {
//                recordList.remove(p); // not very optimal
//                break;
//            }
//        }
//    }
    private static void printList() {
        for (Record p : recordList) {
            System.out.println(p);
        }
    }

    private static void createPerson() {
        System.out.println("Enter name:");
        String name = askString();

        System.out.println("Enter surname:");
        String surname = askString();

        System.out.println("Enter phone:");
        String phone = askPhone();

        System.out.println("Enter email:");
        String email = askString();

        Person p = new Person();
        p.setName(name);
        p.setSurname(surname);
        p.setPhone(phone);
        p.setEmail(email);

        recordList.add(p);

        System.out.println(p);
    }

    private static String askString() {
        var result = new ArrayList<String>();
        var word = scanner.next();
        if (word.startsWith("\"")) {

            do {
                result.add(word);
                if (word.endsWith("\"")) {
                    String str = String.join(" ", result);
                    return str.substring(1, str.length() - 1);
                }
                word = scanner.next();
            } while (true);

        } else {
            return word;
        }

    }

    //  Simple phone validation
    private static String askPhone_SIMPLE() {
        while (true) {
            String phone = askString();
            if (phone.length() >= 5) {
                return phone; // valid
            } else {
                System.out.println("Phone number is too short (min 5 digits)");
            }
        }
    }

    // More advanced phone validation Ļ(but still should be treated as an example)
    private static String askPhone() {
        while (true) {
            String phone = askString();
            // checking if there any characters expect digits, spaces, pluses and dashes
            if (phone.chars().anyMatch(c -> !Character.isDigit(c) && c != ' ' && c != '+' && c != '-')) {
                System.out.println("Only digits, spaces, plus and dash are allowed!");
                continue;
            }

            // checking how many digits in the entered number (excluding spaces and other non-digits)
            if (phone.chars().filter(Character::isDigit).count() < 5) {
                System.out.println("At least 5 digits in phone number");
                continue;
            }

            // validation passed
            return phone;
        }
    }
}
