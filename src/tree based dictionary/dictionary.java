/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment.pkg5;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;

/**
 *
 * @author moizs
 */
public class dictionary {

    
    Node root;
    int maxL = 0;
    int maxR = 0;
    int max = 0;
    
    public dictionary(String directory) throws FileNotFoundException, IOException {
        FileInputStream fstream = new FileInputStream(directory);
        DataInputStream in = new DataInputStream(fstream);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        //ArrayList<Node> arr = new ArrayList<Node>();
        String strLine;
        String print = "";
        while ((strLine = br.readLine()) != null) {
            String word;
            String meaning = "";
            String[] array = strLine.split("  ", 2);
            if (array.length != 0) {
                word = array[0];
            } else {
                word = "";
            }
            for (int i = 1; i < array.length; i++) {
                meaning = meaning + array[i] + " ";
            }
            if (!word.equals("") && !meaning.equals("")) {
                //arr.add(new Node(word, meaning));
                this.insert(word, meaning);
            }
        }
        in.close();

    }


    Node sortedArrayToBST(ArrayList<Node> arr, int start, int end) {

        /* Base Case */
        if (start > end) {
            return null;
        }

        /* Get the middle element and make it root */
        int mid = (start + end) / 2;
        Node node = new Node(arr.get(mid).word, arr.get(mid).meaning);

        /* Recursively construct the left subtree and make it 
         left child of root */
        node.left = sortedArrayToBST(arr, start, mid - 1);


        /* Recursively construct the right subtree and make it 
         right child of root */
        node.right = sortedArrayToBST(arr, mid + 1, end);

        return node;
    }

    public int height() {
        int sum = 0;
        Node temp = root;
        while (temp != null) {
            sum++;
            temp = temp.right;
        }
        return sum;
    }

    public void insert(String word, String meaning) {
        Node n = new Node(word, meaning);
        if (root == null) {
            root = n;
        } else {
            int tempMax = 0;
            Node temp = root;
            Node p = root;
            int tempSum = 0;
            int wordSum = strToAsc(word.toLowerCase());
            while (temp != null) {
                tempSum = strToAsc(temp.word.toLowerCase());
                if (wordSum < tempSum) {
                    p = temp;
                    temp = temp.left;
                    tempMax++;
                } else {
                    p = temp;
                    temp = temp.right;
                    tempMax++;
                }
            }
            if (wordSum < tempSum) {
                p.left = n;
                tempMax++;
            } else {
                p.right = n;
                tempMax++;
            }
            if (tempMax > max) {
                max = tempMax;
            }
        }
    }

    private int strToAsc(String x) {
        int sum = 0;
        for (int i = 0; i < x.length(); i++) {
            sum = sum + (int) x.charAt(i);
        }
        return sum;
    }

    public void find(String word) {
        int col = 0;
        String str = "";
        boolean found = false;
        if (root == null) {
            System.out.println("Tree Empty");
        } else {
            Node temp = root;
            Node p = root;
            while (temp != null) {
                if ((strToAsc(temp.word.toLowerCase()) == strToAsc(word.toLowerCase())) && (temp.word.equalsIgnoreCase(word))) {
                    str = "word: " + temp.word + " meaining: " + temp.meaning;
                    found = true;
                    System.out.println(str);
                    break;
                } else if ((strToAsc(word.toLowerCase()) >= strToAsc(temp.word.toLowerCase())) && (!word.equalsIgnoreCase(temp.word))) {
                    p = temp;
                    temp = temp.right;
                    col++;
                } else {
                    p = temp;
                    temp = temp.left;
                    col++;
                }
            }
            if (temp == null) {
                System.out.println("Not Found");
            }
        }
        System.out.println("Collisions: " + col);
    }

    public void delete(String word) {
        int col = 0;
        boolean found = false;
        if (root == null) {
            System.out.println("Tree Empty");
        } else {
            Node temp = root;
            Node p = root;
            while (temp != null) {
                if ((strToAsc(temp.word.toLowerCase()) == strToAsc(word.toLowerCase())) && (temp.word.equalsIgnoreCase(word))) {
                    found = true;
                    break;
                } else if ((strToAsc(word.toLowerCase()) >= strToAsc(temp.word.toLowerCase())) && (!word.equalsIgnoreCase(temp.word))) {
                    p = temp;
                    temp = temp.right;
                    col++;
                } else {
                    p = temp;
                    temp = temp.left;
                    col++;
                }
            }
            
            if (temp == null) {
                System.out.println("Not Found");;
            } 
            //NO CHILD
            else if (temp.left == null && temp.right == null) {
                if (strToAsc(word) < strToAsc(temp.word)) {
                    p.left = null;
                } else {
                    p.right = null;
                }
            } //ONE CHILD
            else if ((temp.left != null && temp.right == null) || (temp.left == null && temp.right != null)) {
                if (temp.left != null && temp.right == null) {
                    if (strToAsc(word.toLowerCase()) < strToAsc(temp.word.toLowerCase())) {
                        p.left = temp.left;
                    } else {
                        p.right = temp.left;
                    }
                } else if (temp.left == null && temp.right != null) {
                    if (strToAsc(word.toLowerCase()) < strToAsc(temp.word.toLowerCase())) {
                        p.left = temp.right;
                    } else {
                        p.right = temp.right;
                    }
                }
            } //TWO CHILD
            else {
                Node temp2 = temp.left;
                Node p2 = temp.left;
                while (temp2 != null) {
                    p2 = temp2;
                    temp2 = temp2.right;
                }
                String xword = p2.word;
                String xmeaning = p2.meaning;
                delete(p2.word);
                temp.word = xword;
                temp.meaning = xmeaning;
            }
        }
        System.out.println("Collisions: " + col);
    }

    public void display() {
        if (root == null) {
            return;
        }
        Stack<Node> s = new Stack<Node>();
        Node current = root;
        while (current != null || s.size() > 0) {
            while (current != null) {
                s.push(current);
                current = current.left;
            }
            current = s.pop();
            System.out.print("word: " + current.word + " meaning: " + current.meaning + "\n");
            current = current.right;
        }
    }

}
