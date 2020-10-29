/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HashedDictionary;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author 14818
 */
public class Dictionary {

    LinkList[] table;

    public Dictionary(String directory) throws FileNotFoundException, IOException {
        table = new LinkList[521];
        for (int i = 0; i < table.length; i++) {
            table[i] = new LinkList();
        }
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

    /*public boolean checkPrime(int size) {
        boolean prime = true;
        int i = 2;
        while (prime == true && i < size) {
            if (size % i == 0) {
                prime = false;
            }
            i++;
        }
        return prime;
    }*/

    /*public void Dictionary(int size) {
        size = size + (size / 3);
        while (!checkPrime(size)) {
            size = size + 1;
            checkPrime(size);
        }
        table = new LinkList[size];
        for (int i = 0; i < table.length; i++) {
            table[i] = new LinkList();
        }
    }*/

    private long hash(String word) {
        long index;
        long sum = 0;
        for (int i = 0; i < word.length(); i++) {
            sum = (long) ((int) word.charAt(i) * Math.pow(3, i));
        }
        index = sum % table.length;
        return index;
    }

    public void insert(String word, String meaning) {
        String temp = word.toLowerCase();
        int index = (int) hash(temp);
        table[index].insert(word, meaning);
    }

    public void find(String v) {
        String temp = v.toLowerCase();
        int index = (int) hash(temp);
        Node found = table[index].find(v);
        if (found != null) {
            String print = "word: " + found.word + " meaning: " + found.meaning;
            System.out.println(print);
        } else {
            System.out.println("Not Found");
        }
    }

    public void delete(String v) {
        String temp = v.toLowerCase();
        int index = (int) hash(temp);
        if (!table[index].isEmpty()) {
            table[index].delete(v);
        } else {
            System.out.println("Not Found");
        }
    }

    public void display() {
        String print = "";
        for (int i = 0; i < table.length; i++) {
            print = print + i + " " + table[i].toString() + "\n";
        }
        System.out.println(print);
    }
}
