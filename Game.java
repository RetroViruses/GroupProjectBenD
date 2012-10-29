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
	//private String currentDict;
	//word that the user inputs, the six letter word
	private String inWord;
	private String sixLetter;
	//array of the usable characters in this puzzle
	private char[] letters=new char[6];
	//facebook ID
	private int fbID=1337;
	//stores any incorrectly guessed words
	//private ArrayList<String> wrongWords;
	private String gameType;
	//locally stored dictionary
	private Dictionary localDict;
	//Constants
	///////////
	private int SCORE_MOD=40;
	private int BONUS=50;
	
	//Constructors
	//////////////

	public Game(){
		currentScore=0;
		timeToPlay=startTime;
		gameType="random";
	}	
	
	public Game(String sixLetterWord){
		currentScore=0;
		timeToPlay=startTime;
		sixLetter=sixLetterWord;
		letters=sixLetterWord.toCharArray();
		gameType="random";
	}
	
	public Game(String sixLetterWord,int facebookID){
		currentScore=0;
		timeToPlay=startTime;
		sixLetter=sixLetterWord;
		fbID=facebookID;
		letters=sixLetterWord.toCharArray();
		gameType="random";
	}	
	
	public Game(String sixLetterWord,int facebookID, Dictionary dict){
		currentScore=0;
		timeToPlay=startTime;
		sixLetter=sixLetterWord;
		fbID=facebookID;
		letters=sixLetterWord.toCharArray();
		gameType="random";
	}	
	
	public Game(String sixLetterWord,int facebookID, String typeOfGame){
		currentScore=0;
		timeToPlay=startTime;
		sixLetter=sixLetterWord;
		fbID=facebookID;
		letters=sixLetterWord.toCharArray();
		gameType=typeOfGame;
	}
	
	//Methods
	/////////
	//assigns the character array letters to store the six chars in sixLetter
	public void assignLetters(){
		for (int i=0; i<sixLetter.length(); i++){
			letters[i]=sixLetter.charAt(i);
		}
	}	

	public void setSixLetterWord(String newSeed){
		sixLetter=newSeed;
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
	
	//if the enterword is in the dictionary, adds points to score, 
	//removes the word from the dictionary, and returns true. 
	//If it isn't a word, stores it and returns false
	public boolean wordCheck(String enterWord){
		//Dictionary localDict = new Dictionary(0);
		//test line
		//printDict();
		
		for(int x=0;x<localDict.length();x++){
			if (localDict.solutions.get(x).equals(enterWord)){
				//test line
				System.out.println(localDict.solutions.get(x));
				System.out.println(enterWord);
				if (enterWord.length()==6){
					timeToSolve=startTime-timeToPlay;
				}
				localDict.removeWord(x);
				//score updates
				this.updateScore(enterWord.length());
				System.out.println("The current score is: " + currentScore);
			}
			return true;
				
		}
		System.out.println(enterWord + " is not valid");
		return false;
		
	}
	
	
	public void printDict(){
			for(int x=0;x<localDict.length();x++){
				localDict.print(x);
			}
	}
	
	//adds the points from the correctly guessed word to currentScore
	public void updateScore(int toAdd){
		addScore=inWord.length()*SCORE_MOD;
		if (addScore==6*SCORE_MOD){
			addScore=addScore+BONUS;
		}
		updateScore(addScore);
		currentScore=currentScore+toAdd;
	}
	
	/*public void scoring(){
		if (wordCheck(inWord)){
			addScore=inWord.length()*SCORE_MOD;
			if (addScore==6*SCORE_MOD){
				addScore=addScore+BONUS;
			}
			updateScore(addScore);
		}
	}*/
	
	public int getStartTime(){
		return 120;
	}
	
	public String getGameType(){
		return gameType;
	}
	
	public String getRandom(){
		Dictionary dictRand = new Dictionary(0);
		String randWord;
		shuffleDict(dictRand);
		for (int x=0; x<dictRand.length();x++){
			randWord=dictRand.solutions.get(x);
			System.out.println(randWord);
			if (randWord.length()==6){
				System.out.println(randWord);
				return randWord;
			}
		}
		return "errors";
	}
	

	 
	 public void shuffleDict(Dictionary toShuffle){
				Random rNum = new Random();  // Random number generator
				 
				//Shuffle by exchanging each element randomly
				for (int i=0; i < toShuffle.length(); i++) {
					int randomPosition = rNum.nextInt(toShuffle.length());
					String temp = toShuffle.solutions.get(i);
					toShuffle.solutions.set(i, toShuffle.solutions.get(randomPosition));
					toShuffle.solutions.set(randomPosition, temp);
				}
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
            System.out.println("Enter a word of those letters:");
            String puzzleWord = reader.readLine();
            System.out.println(puzzleWord);
    		wordCheck(puzzleWord);
		}
		catch(IOException e){
			System.out.println(e);
		}
	}
	
	public static void main(String[] args){
		Game newGame = new Game();
		String gameSeed=newGame.getRandom();
		newGame.localDict=new Dictionary(gameSeed);
		newGame.setLetters(gameSeed);
		newGame.setSixLetterWord(gameSeed);
		newGame.shuffle(gameSeed.toCharArray());
		PuzzleData newData=null;
		boolean stop = false;
		
		while(stop==false){
			newGame.shuffle(newGame.letters);
			System.out.println(newGame.letters);
			newGame.getInput();
			//is set to true if the time has run out
			stop=newGame.timeOut(newData);
		}
	}
}
