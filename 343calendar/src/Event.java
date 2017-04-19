
import java.util.*;
import java.text.*;
import java.io.*;

public class Event implements Serializable {
	// private static long serialVersionUID = 8066314175045915910;
	private String name;
	private GregorianCalendar strtTime;
	private GregorianCalendar endTime;
	private DateFormat df;

	public Event(String name, Date strt, Date end) {
		df = new SimpleDateFormat("HH:mm");
		this.name = name;
		strtTime = new GregorianCalendar();
		strtTime.setTime(strt);
		if (end != null) {
			endTime = new GregorianCalendar();
			endTime.setTime(end);
		}
	}

	/**
	 * get name of event
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/*
	 * get start time
	 */
	public GregorianCalendar getstrtTime() {
		return strtTime;
	}

	/*
	 * gets end time
	 */
	public GregorianCalendar getendTime() {
		return endTime;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String res = getName() + " " + df.format(strtTime.getTime());
		if (endTime != null)
			res = res + " - " + df.format(endTime.getTime());
		return res;
	}

	/**
	 * sets name
	 */
	public void setName(String n) {
		name = n;
	}

	/*
	 * sets start time
	 */
	public void setstrtTime(GregorianCalendar strt) {
		strtTime = strt;
	}

	/*
	 * sets end time
	 */
	public void setendTime(GregorianCalendar end) {
		endTime = end;
	}

	/**
	 * Checks to see if events conflict
	 * 
	 * @param e
	 * @return
	 */
	public boolean checker(Event e) {
		// this event is earlier than e
		if (this.getendTime() == null) {
			if (this.getstrtTime().before(e.getstrtTime()))
				return false;
		} else if (this.getendTime().before(e.getstrtTime()))
			return false;

		// this event is later than e
		if (e.getendTime() == null) {
			if (e.getstrtTime().before(this.getstrtTime()))
				return false;
		} else if (e.getendTime().before(this.getstrtTime()))
			return false;
		return true;
	}
}
