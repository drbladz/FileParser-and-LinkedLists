import java.util.NoSuchElementException;
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
		
	public Show clone() 
	{
		Scanner kb = new Scanner(System.in);
		System.out.println("Enter a new ShowID: ");
		String id = kb.nextLine();
		Show copy = new Show(id, this.showName, this.startTime, this.endTime);
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
		else if(this.startTime >= s.endTime || this.endTime <= s.startTime)
			return "Different time";
		else {
			return "Some overlap";
		}
	}
		
		
}


class ShowList {
	
	private class ShowNode {
		private Show sw;
		private ShowNode next;
		
		public ShowNode() 
		{
			sw = null;
			next = null;
		}
		
		public ShowNode(Show s, ShowNode nxt) 
		{
			sw = s;
			next = nxt;
		}
		
		public ShowNode(ShowNode cnode) 
		{
			sw = cnode.sw.clone();
			next = cnode.next;
		}
		
		public ShowNode clone() 
		{
			return new ShowNode(this);
		}
		
		public Show getShow() 
		{
			return sw;
		}
		
		public void setShow(Show s) 
		{
			sw = s;
		}
		
		public ShowNode getNext() 
		{
			return next;
		}
		
		public void setNext(ShowNode nxt)
		{
			next = nxt;
		}
	}
	
	
	private ShowNode head;
	private int size;
	
	public ShowList()
	{
		head = null;
		size = 0;
	}
	
	public ShowList(ShowList lst) 
	{
		head = lst.head;
		size = lst.size;
	}
	
	public void addToStart(Show s) 
	{
		ShowNode sn = new ShowNode(s, head);
		head = sn;
		sn = null;
	}
	
	public void insertAtIndex(Show s, int index)
	{
		if (index > size-1)
		{
			System.out.println("ERROR: Given index is out of range! Program will terminate.");
			throw new NoSuchElementException();
		}
		int i = 0;
		ShowNode temp = head;
		
		// Handle the special case when insertion on head
		if (index == 0)
		{
			ShowNode newNode = new ShowNode(s, head);
			head = newNode;
			newNode = null;
		}
		else
		{
			while (i != index-1)	// Stop at the node that precedes index
			{
				temp = temp.next;
				i++;
			}
			// Now we are pointing at the node preceding index
			ShowNode newNode = new ShowNode(s, temp.next);
			// Next will point to temp.next
			temp.next = newNode;
			newNode = null;
		}
		size++;
	}
	
	public void deleteFromIndex(int index) 
	{
		if (index > size-1)
		{
			System.out.println("ERROR: Given index is out of range! Program will terminate.");
			throw new NoSuchElementException();
		}

		int i = 0;
		ShowNode temp = head;
		
		// Handle the special case when list has only one node
		if (size == 1)
		{
			head = null;
			size--;
			return;
		}
		
		// Handle the special case when deletion on head
		if (index == 0)
		{
			head = head.next;
		}
		// When deletion from the middle
		else	
		{
			while (i != index -1)	// Stop at the node that precedes index
			{
				temp = temp.next;
				i++;
			}
			temp.next = temp.next.next;
		}
		size--;

	}
	
	public boolean deleteFromStart()
	{
		if (head != null)
		{
			head = head.next;
			return true;
		}
		else
			return false;
	}
	
	public void replaceAtIndex(Show s, int index)
	{
		if (index > size-1)
		{
			System.out.println("ERROR: Given index is out of range! Program will terminate.");
			throw new NoSuchElementException();
		}
		
		int i = 0;
		ShowNode temp = head;
		while(i != index)
		{
			temp = temp.next;
			i++;
		}
		temp.sw = s;
	}
	
	public ShowNode find(String id) 
	{
		ShowNode temp = head;
		int count = 0;
		while(temp != null)
		{
			if(temp.sw.showID == id)
				return temp;
			temp = temp.next;
			count++;
		}
		System.out.println(count+" iteration(s) were made.");
		return null;	
	}
	
	public boolean contains(String id)
	{
		if(find(id) != null)
			return true;
		else 
			return false;
	}
	
	//need to do equals method
}
		
			