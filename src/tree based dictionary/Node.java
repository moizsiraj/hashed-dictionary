/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment.pkg5;

/**
 *
 * @author moizs
 */
public class Node {

    String word;
    String meaning;
    Node left;
    Node right;

    public Node(String word, String meaning) {
        this.word = word;
        this.meaning = meaning;
    }

    @Override
    public String toString() {
        String string = "word: " + this.word + " meaning: " + this.meaning + "\n";
        return string;
    }

}
