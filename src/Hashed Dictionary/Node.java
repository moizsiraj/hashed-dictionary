/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HashedDictionary;

/**
 *
 * @author moizs
 */
public class Node {
    String word;
    String meaning;
    Node next;

    public Node(String word, String meaning) {
        this.word = word;
        this.meaning = meaning;
    }
    
}
