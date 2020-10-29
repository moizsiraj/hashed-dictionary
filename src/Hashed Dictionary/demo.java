/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HashedDictionary;

import assignment.pkg5.dictionary;
import java.io.IOException;

/**
 *
 * @author moizs
 */
public class demo {
    public static void main(String[] args) throws IOException{
    Dictionary D = new Dictionary("E://Dictionary.txt");
        D.display();
        D.find("money order");
        D.delete("mOnEy OrdeR");
        D.find("money order");
    }
}
