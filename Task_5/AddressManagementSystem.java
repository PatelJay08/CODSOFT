package Task_5;

import java.sql.SQLException;
import java.util.Scanner;

import Task_5.model.Contact;
import Task_5.service.DatabaseService;

public class AddressManagementSystem {

    public static void main(String[] args) throws SQLException {

        DatabaseService databaseService = new DatabaseService();

        Scanner scanner = new Scanner(System.in);

        Contact contact = new Contact();

        boolean isRunning = true;
        while (isRunning) {

            System.out.println("Enter your choice from below options");
            System.out.println("1. Add new Contact");
            System.out.println("2. Update a Contact");
            System.out.println("3. Delete a Contact");
            System.out.println("4. Search a Contact by name");
            System.out.println("5. Show all Contacts");
            System.out.println("8. Exit");

            System.out.println("Enter choice : ");
            int action = scanner.nextInt();

            switch (action) {
                case 1:
                    System.out.println("Enter Contact name : ");
                    contact.setName(scanner.next());
                    System.out.println("Enter Contact phone number : ");
                    contact.setPhone_no(scanner.nextLong());
                    System.out.println("Enter Contact Email : ");
                    contact.setEmail(scanner.next());

                    if (databaseService.addContact(contact)) {
                        System.out.println("Record added successfully !");
                    } else {
                        System.out.println("Insert failed");
                    }
                    break;

                case 2:

                    System.out.println("Enter name of Contact which you want to update : ");
                    String name = scanner.next();

                    System.out.println("Enter Contact Name : ");
                    contact.setName(scanner.next());
                    System.out.println("Enter Contact phone no : ");
                    contact.setPhone_no(scanner.nextLong());
                    System.out.println("Enter Contact Email : ");
                    contact.setEmail(scanner.next());

                    if (databaseService.updateContact(name, contact)) {
                        System.out.println("Record updated successfully !");
                    } else {
                        System.out.println("Update failed");
                    }
                    break;

                case 3:
                    System.out.println("Enter name of contact which you want to delete :");
                    name = scanner.next();

                    if (databaseService.deleteAContact(name)) {
                        System.out.println("Record deleted successfully !");
                    } else {
                        System.out.println("Delete failed");
                    }
                    break;

                case 4:
                    System.out.println("Enter name of contact you want to search : ");
                    name = scanner.next();
                    contact = databaseService.getAContact(name);

                    System.out.println("Name: " + contact.getName());
                    System.out.println("Phone no: " + contact.getPhone_no());
                    System.out.println("Email : " + contact.getEmail());
                    break;
                case 5:
                    int size = databaseService.resultsetSize();
                    int start = 1;
                    while (start <= size) {
                        contact = databaseService.Select(start, size);
                        System.out.println(contact.getName());
                        start++;
                    }
                    break;
                case 8:
                    isRunning = false;
                    break;

                default:
                    break;
            }
        }
        scanner.close();
    }

}
