package view;

import java.util.Scanner;

public class MainView {
    private final LabelView labelView = new LabelView();
    private final PostView postView = new PostView();
    private final WriterView writerView = new WriterView();
    private final Scanner scan = new Scanner(System.in);
    private final String SHOW = """
                    To work with Writers enter 1:
                    To work with Posts enter 2:
                    To work with Labels enter 3: 
                    To exit the application enter 4: 
                    """;

    public void show() {
        loop : while (true) {
            System.out.println(SHOW);
            String choice = scan.nextLine();
            switch (choice) {
                case "1" -> writerView.showWriterView();
                case "2" -> postView.showPostView();
                case "3" -> labelView.showLabelView();
                case "4" -> {
                    break loop;
                }
                default -> System.out.println("Invalid input");
            }
        }
    }
}

