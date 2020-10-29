/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;

/**
 *
 * @author moizs
 */
public class test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        //dictionary d = new dictionary("E://Dictionary.txt");
        dictionary D = new dictionary("E://Dictionary.txt");
        D.display();
        D.find("money order");
        D.delete("mOnEy OrdeR");
        D.find("money order");
        System.out.println("Height of unbalanced Tree: " + D.max);
        //d.root = d.sortedArrayToBST(arr, 0, arr.size()-1);
        //d.display();
        //System.out.println("Height of tree: " + d.height());
        //System.out.println("Total words: " + arr.size());
    }
}
