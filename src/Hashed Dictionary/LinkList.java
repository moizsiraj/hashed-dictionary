/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HashedDictionary;

/**
 *
 * @author moizs
 * @param <Node>
 */
public class LinkList {

    Node head;

    public void insert(String word, String meaning) {
        Node n = new Node(word, meaning);
        if (head == null) {
            head = n;
        } else {
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = n;
        }
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void reverse() {

        //Runs only if there are more than 2 nodes
        if (head.next != null) {

            Node temp = head;
            Node p = head.next;
            Node q = head.next.next;

            while (temp != null) {

                if (temp == head && p != null && q != null) {
                    p.next = temp;
                    temp.next = null;
                    temp = p;
                    p = q;
                    q = p.next;
                } else if (p.next == null) {
                    p.next = temp;
                    temp = p;
                    head.next = null;
                    head = temp;
                    temp = null;
                } else {
                    p.next = temp;
                    temp = p;
                    p = q;
                    q = p.next;
                }
            }
        }
    }

    public Node find(String word) {
        Node temp = head;
        int col = 0;

        while (temp != null) {
            if (temp.word.equalsIgnoreCase(word)) {
                System.out.println("Collisions: " + col);
                return temp;
            }
            col++;
            temp = temp.next;
        }
        return null;
    }

    public void clear() {
        head = null;
    }

    public void delete(String word) {
        Node temp = head;
        Node p = head;
        int col = 0;
        if (head.word.equalsIgnoreCase(word) && head.next == null) {
            head = null;
        } else if (head.word.equalsIgnoreCase(word)) {
            head = head.next;
        } else {
            while (temp != null) {
                col++;
                if (temp.word.equalsIgnoreCase(word)) {
                    p.next = temp.next;
                    break;
                } else {
                    p = temp;
                    temp = temp.next;
                }
            }
            if (temp == null) {
                System.out.println("Not Found");
            }
            System.out.println("Collisions " + col);
        }
    }

    @Override
    public String toString() {
        Node temp = head;
        String str = "";

        while (temp != null) {
            str = str + temp.word + ", ";
            temp = temp.next;
        }
        return str;
    }
}
