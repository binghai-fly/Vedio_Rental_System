package last;

public class Item {
	private String title;
	private String playingTime;
	private boolean gotIt;
	private String comment;
	
	public Item(String title, String playingTime,  String comment) {
		super();
		this.title = title;
		this.playingTime = playingTime;
		this.comment = comment;
	}

	
	@Override
	public String toString() {
		return "title = " + title +  ",  playingTime =  " + playingTime +  " ,  gotIt = " + gotIt +  ", comment =  " + comment;
	}

	public String print() {

		return this.title+" , ";
	};
	
	public boolean isGot() {
		if (this.gotIt)
			return true;
		else return false;
	}
	public void setGot(boolean gotIt) {
		this.gotIt=gotIt;
	}
	public String getTitle() {
		return this.title;
	}
}
