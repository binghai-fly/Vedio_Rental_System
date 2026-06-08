package last;

public class CD extends Item {
	private String singer;

	public CD(String title, String playingTime,  String singer, String comment ) {
		super(title, playingTime, comment);
		this.singer=singer;
	}

	@Override
	public String toString() {
		return "CD [singer=" + singer + ","+  super.toString() + "]";
	}

	@Override
	public String print() {
		String s="CD: "+super.print()+" "+this.singer;
		return s;
	}
	
}
