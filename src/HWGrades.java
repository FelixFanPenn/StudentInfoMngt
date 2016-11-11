
public class HWGrades {
	private Assignment as;
	private double percentage;
	
	public HWGrades(Assignment as, double pc) {
		this.as = as;
		this.percentage = pc;
	}
	
	public void setPC(double pc) {
		this.percentage = pc;
	}
	
	public double getRawScore(){
		return this.percentage * as.getMaxScore();
	}
	
	public Assignment getHomework(){
		return this.as;
	}
}
