package view;

import controller.WriterController;

import java.util.Scanner;

public class WriterView {
    private final WriterController writerController = new WriterController();
    private final Scanner scan = new Scanner(System.in);
    private final String SHOW =
            """
            To create a new Writer enter 1.
            To see a Writer enter 2.
            To see all Writer enter 3.
            To update Writer enter 4.
            To delete Writer enter 5.
            """;

    public void showWriterView() {
        System.out.println(SHOW);
        String choice = scan.nextLine();
        switch (choice) {
            case "1" -> createNewWriter();
            case "2" -> getWriter();
            case "3" -> getAllWriters();
            case "4" -> updateWriter();
            case "5" -> deleteWriter();
        }
    }

    private void createNewWriter() {
        System.out.println("Enter firstName: ");
        String firstName = scan.nextLine();
        System.out.println("Enter lastName: ");
        String lastName = scan.nextLine();
        writerController.create(firstName, lastName);
    }

   private void getWriter() {
        System.out.println("Enter id of the Writer: ");
        String id = scan.nextLine();
        System.out.println(writerController.getById(Long.parseLong(id)));
    }

    private void getAllWriters() {
        System.out.println(writerController.getAll());
    }

    private void updateWriter() {
        System.out.println("Enter id of the Writer");
        String writerId = scan.nextLine();
        System.out.println("Enter new first name: ");
        String firstName = scan.nextLine();
        System.out.println("Enter new last name: ");
        String lastName = scan.nextLine();
        writerController.update(Long.parseLong(writerId), firstName, lastName);
    }

    private void deleteWriter() {
        System.out.println("Enter id of Writer: ");
        String writerId = scan.nextLine();
        writerController.delete(Long.parseLong(writerId));
    }
}
