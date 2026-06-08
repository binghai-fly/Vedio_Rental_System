package last;

import java.util.ArrayList;

public class Manager {
	private final String password="binghai";
	public static ArrayList<Item> list=new ArrayList<Item>();
	
	public void add(Item item) {
		Manager.list.add(item);
	}
	
	public String deleteByIndex(int n) {
		if (n<Manager.list.size()) {
			String s= Manager.list.get(n).getTitle()+ " had been removed successfully!";
			Manager.list.remove(n);
			return s;
			
		}
		else return "Invalid Index!";
	}
	
	public String deleteByTitle(String title) {
		for (int i=0;i<Manager.list.size();i++) {
			if (Manager.list.get(i).getTitle().equals(title)) {
				
				String s= Manager.list.get(i).getTitle()+ " had been removed successfully!";
				Manager.list.remove(i);
				return s;
			}
		}
		return "Sorry, don't find "+title;	
	}
	
	public String getPassword() {
		return this.password;
	}
}
