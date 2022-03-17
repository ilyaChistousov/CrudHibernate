package view;

import controller.LabelController;

import java.util.Scanner;

public class LabelView {
    private final LabelController labelController = new LabelController();
    private final Scanner scan = new Scanner(System.in);
    private final String SHOW = """
            To create a new Label enter 1.
            To see Label by id enter 2.
            To see all Labels enter 3.
            To edit label enter 4.
            To delete label enter 5.
            """;

    public void showLabelView() {
        System.out.println(SHOW);
        String choice = scan.nextLine();
        switch (choice) {
            case "1" -> addNewLabel();
            case "2" -> getLabel();
            case "3" -> getAllLabels();
            case "4" -> editLabel();
            case "5" -> deleteLabel();
        }
    }

    private void addNewLabel() {
        System.out.println("Enter the name of the Label: ");
        String name = scan.nextLine();
        labelController.create(name);
    }

    private void getLabel() {
        System.out.println("Enter id of the Label: ");
        String id = scan.nextLine();
        System.out.println(labelController.getById(Long.parseLong(id)));
    }

    private void getAllLabels() {
        System.out.println(labelController.getAll());
    }

    private void editLabel() {
        System.out.println("Enter id of the Label you want to edit: ");
        String id = scan.nextLine();
        System.out.println("Enter new name: ");
        String name = scan.nextLine();
        labelController.update(Long.parseLong(id), name);
    }

    private void deleteLabel() {
        System.out.println("Enter id of the Label you want to delete: ");
        String id = scan.nextLine();
        labelController.delete(Long.parseLong(id));
    }
}
