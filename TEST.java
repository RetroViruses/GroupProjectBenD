/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.Collection;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.ListIterator;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;

/**
 *
 * @author Matt
 */
public class TEST {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArrayList<String> solutions = new ArrayList();
        ArrayList<String> dictionary = new ArrayList();
        /*
         * dict.add("god"); dict.add("good"); dict.add("goood");
         * dict.add("goad"); dict.add("dog"); dict.add("do"); dict.add("dg");
         * dict.add("drag"); dict.add("drug"); dict.add("guru");
         * dict.add("grunt");
         */
        try {
            BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Ben\\Documents\\eclipse\\Group Project\\GroupProjectBenD\\dictionaryalt.txt"));
            boolean bool = true;
            while (bool) {
                String t = br.readLine();
                //System.out.println(t);
                if (t == null) {
                    bool = false;
                } else {
                    dictionary.add(t);
                }
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                System.out.print("input:");
                String puzzleword = reader.readLine();
                solutions = getsolutions(puzzleword, dictionary);
                for (int k = 0; k < solutions.size(); k++) {
                    System.out.println(solutions.get(k));
                }
                System.out.println(puzzleword + " has " + solutions.size() + " valid words");
            }
        } catch (IOException e) {
        }
    }

    static public ArrayList<String> getsolutions(String puzword, ArrayList<String> dict) throws IOException {
        ArrayList<String> validwords = new ArrayList<String>();

        for (int i = 0; i < dict.size(); i++) {
            String tocheck = dict.get(i);
            String puztemp = puzword;

            //System.out.println("Current: " + tocheck);
            int index = 0;
            boolean wcheck = true;
            while (wcheck) {
                if (index >= tocheck.length()) {
                    //System.out.println(tocheck + " contains valid characters");
                    validwords.add(tocheck);
                    wcheck = false;
                } else {
                    char ch = tocheck.charAt(index);

                    if (puzword.indexOf(ch) == -1) {
                        //System.out.println(puzzleword + " no " + ch);
                        wcheck = false;
                        index = 0;
                    } else if (puztemp.indexOf(ch) == -1) {
                        //System.out.println("duplicate character");
                        wcheck = false;
                        index = 0;
                    } else {
                        puztemp = puztemp.replaceFirst(String.valueOf(ch), " ");
                    }
                }
                index++;
            }
        }
        return validwords;
    }
}