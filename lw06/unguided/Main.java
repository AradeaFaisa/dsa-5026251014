import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner read = new Scanner(Main.class.getResourceAsStream("treenumbers.txt"));

        String[] numbers = read.nextLine().trim().split("\\s+"); 
        Node root = null; 

        for (int i = 0; i<numbers.length; i++) { 
            int data = Integer.parseInt(numbers[i]);
            root = insert(root, data); 
        }

        int target = read.nextInt(); 

        System.out.print("Preorder : ");
        preorder(root);
        System.out.println();

        System.out.print("Inorder : ");
        inorder(root);
        System.out.println();

        System.out.print("Postorder : ");
        postorder(root);
        System.out.println();

        System.out.println("Search " + target + " : " + search(root, target)); 
        System.out.println("Nodes : " + countNodes(root));
        System.out.println("Leaves : " + countLeaves(root));


        read.close();
    }

    static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
        }
    }

    static Node insert(Node root, int data) {
        if (root == null) {
            return new Node(data);
        }

        if (data < root.data) {
            root.left = insert(root.left, data);
        } else if (data > root.data) {
            root.right = insert(root.right, data);
        }

        return root;
    }

    static void preorder(Node node) {
        if (node == null) return;
        System.out.print(node.data + " ");
        preorder(node.left);
        preorder(node.right);
    }

    static void inorder(Node node) {
        if (node == null) return;
        inorder(node.left);
        System.out.print(node.data + " ");
        inorder(node.right);
    }

    static void postorder(Node node) {
        if (node == null) return;
        postorder(node.left);
        postorder(node.right);
        System.out.print(node.data + " ");
    }

    static int countNodes(Node node) {
        if (node == null) return 0;
        return 1 + countNodes(node.left) + countNodes(node.right);
    }

    static int countLeaves(Node node) {
        if (node == null) return 0;
        if (node.left == null && node.right == null) return 1;
        return countLeaves(node.left) + countLeaves(node.right);
    }

    static boolean search(Node node, int target) {
        if (node == null) return false;
        if (target == node.data) return true;
        return target < node.data
                ? search(node.left, target)
                : search(node.right, target);
    }
}