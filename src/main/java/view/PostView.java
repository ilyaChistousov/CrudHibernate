package view;

import controller.PostController;

import java.util.Scanner;


public class PostView {
    private final PostController postController = new PostController();
    private final Scanner scan = new Scanner(System.in);
    private final String SHOW = """
            To create a new Post enter 1.
            o see a Post enter 2.
            To see all Posts enter 3.
            To update Post enter 4.
            To delete Post enter 5.
            To add Label to Post enter 6.
            """;

    public void showPostView() {
        System.out.println(SHOW);
        String choice = scan.nextLine();
        switch (choice) {
            case "1" -> createNewPost();
            case "2" -> getPost();
            case "3" -> getAllPosts();
            case "4" -> updatePost();
            case "5" -> deletePost();
            case "6" -> addLabel();
        }
    }

    private void createNewPost() {
        System.out.println("Enter writer id: ");
        String writerId = scan.nextLine();
        System.out.println("Enter the text: ");
        String content = scan.nextLine();
        postController.create(content, Long.parseLong(writerId));
        System.out.println();

    }

    private void getPost() {
        System.out.println("Enter id of the Post: ");
        String id = scan.nextLine();
        System.out.println(postController.getById(Long.parseLong(id)));
        System.out.println();
    }

    private void getAllPosts() {
        System.out.println(postController.getAll());
        System.out.println();
    }

    private void updatePost() {
        System.out.println("Enter id of the Post you want to edit: ");
        String id = scan.nextLine();
        System.out.println("Enter new content: ");
        String content = scan.nextLine();
        postController.update(Long.parseLong(id), content);
    }

    private void deletePost() {
        System.out.println("Enter id of the Post you want to delete: ");
        String postId = scan.nextLine();
        postController.delete(Long.parseLong(postId));
    }

    private void addLabel() {
        System.out.println("Enter id of the Label: ");
        String labelId = scan.nextLine();
        System.out.println("Enter id of the Post: ");
        String postId = scan.nextLine();
        postController.addLabelToPost(Long.parseLong(labelId), Long.parseLong(postId));
    }
}
