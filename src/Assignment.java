
public class Assignment implements AssignmentInterface{

	private int maxScore;
	private int assignNumber;
	private static int assignNum = 0;
	
	public Assignment(int maxScore) {
		this.maxScore = maxScore;
		this.assignNumber = assignNum;
		assignNum++;
	}
	
	public void setAssignNum(int assignNum) {
		this.assignNumber = assignNum;
	}
	
	@Override
	public int getAssignmentNumber() {
		return this.assignNumber;
	}

	@Override
	public int getMaxScore() {
		return this.maxScore;
	}
	
}
