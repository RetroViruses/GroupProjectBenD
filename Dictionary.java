import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;


public class Dictionary {

	ArrayList<String> solutions = new ArrayList();
    ArrayList<String> dictionary = new ArrayList();

    public Dictionary(){

        try {
            BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Ben\\Documents\\eclipse\\Group Project\\GroupProjectBenD\\dictionaryalt.txt"));
            boolean bool = true;
            while (bool) {
                String t = br.readLine();

                if (t == null) {
                    bool = false;
                } else {
                    dictionary.add(t);
                }
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                System.out.print("Enter seed:");
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
    
    //creates a long dictionary, for finding words from it (for the random game mode)
    public Dictionary(int x){

        try {
            BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Ben\\Documents\\eclipse\\Group Project\\GroupProjectBenD\\dictionaryalt.txt"));
            boolean bool = true;
            while (bool) {
                String t = br.readLine();

                if (t == null) {
                    bool = false;
                } else {
                    dictionary.add(t);
                }
            }
            solutions = getsolutions(dictionary);
        } catch (IOException e) {
        }
    }
    
    //creates a dictionary from a given string
    public Dictionary(String puzzleWord){

        try {
            BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Ben\\Documents\\eclipse\\Group Project\\GroupProjectBenD\\dictionaryalt.txt"));
            boolean bool = true;
            while (bool) {
                String t = br.readLine();

                if (t == null) {
                    bool = false;
                } else {
                    dictionary.add(t);
                }
            }

            while (true) {
                solutions = getsolutions(puzzleWord, dictionary);
                for (int k = 0; k < solutions.size(); k++) {
                    System.out.println(solutions.get(k));
                }
                System.out.println(puzzleWord + " has " + solutions.size() + " valid words");
                break;
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
    static public ArrayList<String> getsolutions(ArrayList<String> dict) throws IOException {
        ArrayList<String> validwords = new ArrayList<String>();

        for (int i = 0; i < dict.size(); i++) {
            String tocheck = dict.get(i);

            //System.out.println("Current: " + tocheck);
            int index = 0;
            boolean wcheck = true;
            while (wcheck) {
                if (index >= tocheck.length()) {
                    //System.out.println(tocheck + " contains valid characters");
                    validwords.add(tocheck);
                    wcheck = false;
                }
                index++;
            }
        }
        return validwords;
    }
	
	
	public String checkWord(int index){
		return solutions.get(index);
	}
	
	public void removeWord(int index){
		solutions.remove(index);
	}
	
	public int length(){
		return solutions.size();
	}
	
	public void print(int index){
		System.out.println(solutions.get(index));
	}
	
	
}
