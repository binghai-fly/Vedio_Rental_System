package last;

public class DVD extends Item {
	private String director;

	public DVD(String title, String playingTime,  String director, String comment) {
		super(title, playingTime,  comment);
		this.director=director;
	}

	@Override
	public String toString() {
		return "DVD [director=" + director + ", " + super.toString() + "]";
	}

	@Override
	public String print() {
		String s="DVD: "+super.print()+" "+this.director;
		return s;	
	}
}
