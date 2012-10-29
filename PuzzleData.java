
public class PuzzleData {

	private int score;
	private int solveTime;
	private String puzzleSeed;
	private int fbID;
	
	public PuzzleData(int finalScore, int timeToSolve, String seed, int facebookID){
		score=finalScore;
		solveTime=timeToSolve;
		puzzleSeed=seed;
		fbID=facebookID;
	}

	public int getScore(){
		return score;
	}
	public int getTime(){
		return solveTime;
	}
	public String getSeed(){
		return puzzleSeed;
	}
	public int getFacebookID(){
		return fbID;
	}
}
