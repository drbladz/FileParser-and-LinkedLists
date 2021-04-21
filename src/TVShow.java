import java.util.Scanner;

public class TVShow {
	
	protected String showID;
	protected String showName;
	protected double startTime;
	protected double endTime;
	
}

class Show extends TVShow implements Watchable{
	public Show(String showID, String showName, double startTime, double endTime) 
	{
		this.showID = showID;
		this.showName = showName;
		this.startTime = startTime;
		this.endTime = endTime;
	}
		
	public Show(Show s, String id) 
	{
		showID = id;
		showName = s.showName;
		startTime = s.startTime;
		endTime = s.endTime;
	}
		
	public String getShowID() 
	{
		return showID;
	}
		
	public void setShowID(String id) 
	{
		showID = id;
	}
		
	public String getShowName() 
	{
		return showName;
	}
		
	public void setShowName(String name) 
	{
		showName = name;
	}
		
	public double getStartTime() 
	{
		return startTime;
	}
		
	public void setStartTime(double start) 
	{
		startTime = start;
	}
		
	public double getEndTime() 
	{
		return endTime;
	}
		
	public void setEndTime(double end) 
	{
		endTime = end;
	}
		
	public Show clone(Show s) 
	{
		Scanner kb = new Scanner(System.in);
		System.out.println("Enter a new ShowID: ");
		String id = kb.nextLine();
		Show copy = new Show(id, s.showName, s.startTime, s.endTime);
		kb.close();
		return copy;
	}
		
	public String toString() 
	{
		return "This show is "+showName+" with ID "+showID+". It starts at "+startTime+" and ends at "+endTime;
	}
		
	public boolean equals(Object x)
	{
		if(x == null || this == null || this.getClass() != x.getClass())
			return false;
		else {
			Show s = (Show)x;
			return (this.getShowName() == s.getShowName() && this.getStartTime() == s.getStartTime() && this.getEndTime() == s.getEndTime());
		}
	}
	
	public String isOnSameTime(Show s) 
	{
		if(this.startTime == s.startTime && this.endTime == s.endTime)
			return "Same Time";
		if(this.startTime >= s.endTime || this.endTime <= s.startTime)
			return "Different time";
		else {
			return "Some overlap";
		}
	}
		
		
		
}
		
			