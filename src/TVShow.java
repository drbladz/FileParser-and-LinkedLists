import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * TV show class
 */
public class TVShow {

	protected String showID;
	protected String showName;
	protected double startTime;
	protected double endTime;
	
}

class Show extends TVShow implements Watchable{
	/**
	 * Show constructor
	 * @param showID
	 * @param showName
	 * @param startTime
	 * @param endTime
	 */
	public Show(String showID, String showName, double startTime, double endTime) 
	{
		this.showID = showID;
		this.showName = showName;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	/**
	 * constructor
	 * @param s
	 * @param id
	 */
	public Show(Show s, String id) 
	{
		showID = id;
		showName = s.showName;
		startTime = s.startTime;
		endTime = s.endTime;
	}

	/**
	 * showID getter
	 * @return
	 */
	public String getShowID() 
	{
		return showID;
	}

	/**
	 * showID setter
	 * @param id
	 */
	public void setShowID(String id) 
	{
		showID = id;
	}

	/**
	 * showName getter
	 * @return
	 */
	public String getShowName() 
	{
		return showName;
	}

	/**
	 * showName setter
	 * @param name
	 */
	public void setShowName(String name) 
	{
		showName = name;
	}

	/**
	 * startTime getter
 	 * @return
	 */
	public double getStartTime()
	{
		return startTime;
	}

	/**
	 * startTime setter
	 * @param start
	 */
	public void setStartTime(double start) 
	{
		startTime = start;
	}

	/**
	 * endTime getter
	 * @return
	 */
	public double getEndTime() 
	{
		return endTime;
	}

	/**
	 * endTime setter
	 * @param end
	 */
	public void setEndTime(double end) 
	{
		endTime = end;
	}

	/**
	 * clone method
	 * @return
	 */
	public Show clone() 
	{
		Scanner kb = new Scanner(System.in);
		System.out.println("Enter a new ShowID: ");
		String id = kb.nextLine();
		Show copy = new Show(id, this.showName, this.startTime, this.endTime);
		kb.close();
		return copy;
	}

	/**
	 * toString method
	 * @return
	 */
	public String toString() 
	{
		return "This show is "+showName+" with ID "+showID+". It starts at "+startTime+" and ends at "+endTime;
	}

	/**
	 * equals method
	 * @param x
	 * @return
	 */
	public boolean equals(Object x)
	{
		if(x == null || this == null || this.getClass() != x.getClass())
			return false;
		else {
			Show s = (Show)x;
			return (this.getShowName() == s.getShowName() && this.getStartTime() == s.getStartTime() && this.getEndTime() == s.getEndTime());
		}
	}

	/**
	 * isOnSameTime method, to check if two shows are at the same time
	 * @param s
	 * @return
	 */
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
	
	class ShowNode {
		private Show sw;
		private ShowNode next;

		/**
		 * default constructor
		 */
		public ShowNode() 
		{
			sw = null;
			next = null;
		}

		/**
		 * parametrized constructor
		 * @param s
		 * @param nxt
		 */
		public ShowNode(Show s, ShowNode nxt) 
		{
			sw = s;
			next = nxt;
		}

		/**
		 * copy constructor
		 * @param cnode
		 */
		public ShowNode(ShowNode cnode) 
		{
			sw = cnode.sw.clone();
			next = cnode.next;
		}

		/**
		 * clone method
		 * @return
		 */
		public ShowNode clone() 
		{
			return new ShowNode(this);
		}

		/**
		 * show getter
		 * @return
		 */
		public Show getShow() 
		{
			return sw;
		}

		/**
		 * show setter
		 * @param s
		 */
		public void setShow(Show s) 
		{
			sw = s;
		}

		/**
		 * showNode getter
		 * @return
		 */
		public ShowNode getNext() 
		{
			return next;
		}

		/**
		 * setnext setter
		 * @param nxt
		 */
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

	/**
	 * add to start method
	 * @param s
	 */
	public void addToStart(Show s) 
	{
		ShowNode sn = new ShowNode(s, head);
		head = sn;
		sn = null;
		size++;
	}

	/**
	 * insert at index method
	 * @param s
	 * @param index
	 */
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

	/**
	 * delete from index method
	 * @param index
	 */
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

	/**
	 * delete from start method
	 * @return
	 */
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

	/**
	 * replace at index method
	 * @param s
	 * @param index
	 */
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

	/**
	 * find method
	 * @param name
	 * @return
	 */
	public ShowNode find(String name)
	{
		ShowNode temp = head;
		int count = 0;
		while(temp != null)
		{
			if(temp.sw.getShowName().equals(name))
				return temp;
			temp = temp.next;
			count++;
		}
		System.out.println(count+" iteration(s) were made.");
		return null;	
	}

	/**
	 * contains method
	 * @param name
	 * @return
	 */
	public boolean contains(String name)
	{
		if(find(name) != null)
			return true;
		else 
			return false;
	}

	/**
	 * equals method
	 * @param lst
	 * @return
	 */
	public boolean equals(ShowList lst) {
		return(this.head.getShow().equals(lst.head.getShow()));
	}

	/**
	 * show list contents method
	 */
	public void showListContents()
	{
		ShowNode temp = head;
		if (temp == null)
			System.out.println("\nThere is nothing to display; list is empty." );
		else
			System.out.println("\nHere are the contents of the list." );
		while(temp != null)
		{
			System.out.println(temp.sw + " ---> ");
			temp = temp.next;
		}
		System.out.println("X");			}
}
		
			