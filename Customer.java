package last;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Customer implements Seach{
	private int numsOfBorrow;
	private String name;
	
	private HashMap<Date, String> BorrowRecord=new HashMap<Date, String>();
	private HashMap<Date ,String> ReturnRocord=new HashMap<Date, String>();
	public Customer(String name) {
		this.name=name;
	}
	public String borrowByIndex(int n) {
		if (numsOfBorrow>=3) return "You have borrowed three items and cannot borrow any more.";
		if (n<Manager.list.size()) {
			if (Manager.list.get(n).isGot())
			return "Sorry, it's alreadly been borrowed!";
			else {
				Manager.list.get(n).setGot(true);
				this.numsOfBorrow++;
				BorrowRecord.put(new Date(), Manager.list.get(n).getTitle());
				return "Got it: "+Manager.list.get(n).getTitle();
			}
		}
		return "Invalid Index!";
	}
	
	public String borrowByTitle(String title) {
		if (numsOfBorrow>=3) return "You have borrowed three items and cannot borrow any more.";
		for (Item item:Manager.list) {
			if (item.getTitle().equals(title)) {
				if (item.isGot()) {
					return "Sorry, it's alreadly been borrowed!";
				}	
				else {
					item.setGot(true);
					this.numsOfBorrow++;
					BorrowRecord.put(new Date(), title);
					return "Got it: "+title;
				}
			}
		}
		return "Sorry, don't find "+title;
	}
	
	public String ReturnByTitle(String title) {
		for (Item item:Manager.list) {
			if (item.getTitle().equals(title)) {
				if (BorrowRecord.containsValue(title)&&item.isGot()) {	
					item.setGot(false);
					this.numsOfBorrow--;
					ReturnRocord.put(new Date(), title);
					return "Returned it:"+ item.getTitle();
				}

				else {
					return "Invalid return! Because you didn't borrow"+item.getTitle();
				}
				
			}
		}
		return "Sorry, don't find "+title;	
	}
	
	public String ReturnByIndex(int n) {
		if (n<Manager.list.size()) {
			if (BorrowRecord.containsValue(Manager.list.get(n).getTitle())&&Manager.list.get(n).isGot()) {
				Manager.list.get(n).setGot(false);
				this.numsOfBorrow--;
				ReturnRocord.put(new Date(), Manager.list.get(n).getTitle());
				return "Returned it:"+ Manager.list.get(n).getTitle();
			
			}
				
			else {
				return"Invalid return! Because you didn't borrow "+Manager.list.get(n).getTitle();
			}
		}
		return "Invalid Index!";
		
	}
	
	public String SearchSth(String title) {
		String s="";
		for (int i=0;i<Manager.list.size();i++) {
			if (Manager.list.get(i).getTitle().equals(title)) {
				s+= "{ "+Manager.list.get(i).getTitle() +" index is "+i+"\nIts infomation: "+Manager.list.get(i).toString()+" }\n";
			}
		}
		if (!s.isEmpty())
		return s;
		else
		return "Sorry, don't find "+title;	
	}
	
	public String SearchSelf() {
		String s="";
		
		if (BorrowRecord.isEmpty()) {
			s+="\n No borrows recorded yet!";
		}
		else {
			s+="\t\tRecord of Borrow\n\n";
			for (Date key:BorrowRecord.keySet()) {
				s+=key+"   "+ BorrowRecord.get(key)+"\n";
			}
		}
		
		if (ReturnRocord.isEmpty()) {
			s+="\n\n No returns recorded yet!";
		}
		else {
			s+="\n\n\t\tRecord of Return \n\n";
			for (Date key:ReturnRocord.keySet()) {
				s+=key+"   "+ReturnRocord.get(key)+"\n";
			}
		}
		
		return s;
	}
	
	public String getName() {
		return this.name;
	}
}
