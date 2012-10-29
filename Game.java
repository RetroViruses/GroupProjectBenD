import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Game {

	//variables
	///////////
	//current score, amount to add to score
	private int currentScore;
	private int addScore;
	//time left to play, time at game start, time when the 6 letter word is solved
	private int timeToPlay;
	private int startTime = this.getStartTime();
	private int timeToSolve=0;
	//stores the string that keeps track of which global dictionary to use
	private String currentDict;
	//word that the user inputs, the six letter word
	private String inWord;
	private String sixLetter;
	//array of the usable characters in this puzzle
	private char[] letters;
	//facebook ID
	private int fbID=1337;
	
	//Constants
	///////////
	private int SCORE_MOD=40;
	private int BONUS=50;
	
	//Constructors
	//////////////
	
	public Game(){
		currentScore=0;
		timeToPlay=startTime;
	}	
	
	public Game(String sixLetterWord){
		currentScore=0;
		timeToPlay=startTime;
		sixLetter=sixLetterWord;
	}
	
	public Game(String sixLetterWord,int facebookID){
		currentScore=0;
		timeToPlay=startTime;
		sixLetter=sixLetterWord;
		fbID=facebookID;
	}
	
	//Methods
	/////////
	//assigns the character array letters to store the six chars in sixLetter
	public void assignLetters(){
		for (int i=0; i<sixLetter.length(); i++){
			letters[i]=sixLetter.charAt(i);
		}
	}
	
	//shuffles the letters in the character array
	public void shuffle(char[] letters){
		Random rNum = new Random();  // Random number generator
		 
		//Shuffle by exchanging each element randomly
		for (int i=0; i < letters.length; i++) {
			int randomPosition = rNum.nextInt(letters.length);
			char temp = letters[i];
			letters[i] = letters[randomPosition];
			letters[randomPosition] = temp;
		}
	}
	
	//sets the character array letters to store the six chars in sixLetterWord
	public void setLetters(String sixLetterWord){
		for (int i=0; i<sixLetterWord.length(); i++){
			letters[i]=sixLetterWord.charAt(i);
		}
	}
	
	
	//check if a word is in a dictionary
	public boolean isInDictionary(String query, Dictionary largeDict){
		for(int x=0;x<largeDict.length();x++){
			if (largeDict.checkWord(x).equals(query)){
				return true;
			}
		}
		return false;
	}
	
	//if the enterword is in the dictionary, adds points to score, removes the word from the dictionary, and returns true
	public boolean wordCheck(String enterWord){
		Dictionary localDict = new Dictionary();
		//test line
		printDict(localDict);
		
		for(int x=0;x<localDict.length();x++){
			if (localDict.dictContent.equals(enterWord)){
				this.updateScore(enterWord.length());
				//test line
				System.out.println(localDict.dictContent[x]);
				System.out.println(enterWord);
				if (enterWord.length()==6){
					timeToSolve=startTime-timeToPlay;
				}
				localDict.removeWord(x);
				//test line
				System.out.println(x);
				
				return true;
			}
		}
		return false;
	}
	
	public void printDict(Dictionary localDict){
			for(int x=0;x<localDict.length();x++){
				localDict.print(x);
			}
	}
	
	//adds the 
	public void updateScore(int toAdd){
		currentScore=currentScore+toAdd;
	}
	
	public void scoring(){
		if (wordCheck(inWord)){
			addScore=inWord.length()*SCORE_MOD;
			if (addScore==6*SCORE_MOD){
				addScore=addScore+BONUS;
			}
			updateScore(addScore);
		}
	}
	
	public int getStartTime(){
		return 120;
	}
	
	public int returnFacebookID(){
		return fbID;
	}
	
	public String getDictType(){
		return "bigone";
	}
	
	//when the user's time runs out, creates a new puzzleData object and returns true, otherwise returns false
	public boolean timeOut(PuzzleData shellData){
		if (timeToPlay==0){
			PuzzleData endData = new PuzzleData(currentScore, timeToSolve, sixLetter, fbID);
			shellData = endData;
			return true;
		}
		else{
			return false;
		}
	}
	
	//gets the input, checks if it is in the dictionary, using wordCheck
	public void getInput(){
		try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("input:");
            String puzzleWord = reader.readLine();
    		wordCheck(puzzleWord);
		}
		catch(IOException e){
			System.out.println(e);
		}
	}
	
	public static void main(String[] args){
		Game newGame = new Game("abcdef");
		PuzzleData newData=null;
		boolean stop = false;
		
		newGame.assignLetters();
		while(stop==false){
			System.out.println(newGame.letters);
			newGame.getInput();
			//is set to true if the time has run out
			stop=newGame.timeOut(newData);
		}
	}
}
