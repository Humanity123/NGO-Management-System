package BackEnd;

import java.util.ArrayList;
import java.util.*;

public class Organization {
	private ArrayList<PledgedDonor> donors;
	private ArrayList<Helper> helpers;
	private ArrayList<Student> students;
	private ArrayList<Donation> donations;
	private Map<Donor,ArrayList<Donation> > donorMap;
	
	private Manager manager;
	private int funds;
	private ArrayList<Expenditure> expenditure;
	private Map<Student,ArrayList<Record> > performanceRecords;
	
	static private Organization ngo;
	
	public Organization(){
		
	}
	
	public static Organization getOrganization(){
		return ngo;
	}
	
	public static void init(){
		
	}
	
		
}
