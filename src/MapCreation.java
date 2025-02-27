import java.util.ArrayList;

public class MapCreation {
	private ArrayList<Platform> map;
	
	public MapCreation(int number) {
		map = new ArrayList<Platform>();
		if(number == 1) {
			map.add(new Platform(0,DD.SCREENHEIGHT-DD.PLATLARGEHEIGHT,DD.SCREENWIDTH, DD.PLATSMALLHEIGHT));
			map.add(new Platform(DD.THIRDSPACING, DD.SCREENHEIGHT - 3*DD.PLATLARGEHEIGHT, DD.PLATLARGEWIDTH, DD.PLATSMALLHEIGHT));
			map.add(new Platform(2*DD.THIRDSPACING+DD.PLATLARGEWIDTH, DD.SCREENHEIGHT - 3*DD.PLATLARGEHEIGHT, DD.PLATLARGEWIDTH, DD.PLATSMALLHEIGHT));
			map.add(new Platform(3*DD.THIRDSPACING+2*DD.PLATLARGEWIDTH, DD.SCREENHEIGHT - 3*DD.PLATLARGEHEIGHT, DD.PLATLARGEWIDTH, DD.PLATSMALLHEIGHT));
			
			map.add(new Platform(DD.PLYRWIDTH, DD.SCREENHEIGHT - 5*DD.PLATLARGEHEIGHT, DD.PLATLARGEWIDTH, DD.PLATSMALLHEIGHT));
			map.add(new Platform(DD.PLYRWIDTH + DD.FOURTHSPACING + DD.PLATLARGEWIDTH, DD.SCREENHEIGHT -5*DD.PLATLARGEHEIGHT, DD.PLATLARGEWIDTH, DD.PLATSMALLHEIGHT));
			map.add(new Platform(DD.SCREENWIDTH - DD.PLYRWIDTH - DD.FOURTHSPACING  - 2*DD.PLATLARGEWIDTH, DD.SCREENHEIGHT -5*DD.PLATLARGEHEIGHT, DD.PLATLARGEWIDTH, DD.PLATSMALLHEIGHT));
			map.add(new Platform(DD.SCREENWIDTH - DD.PLYRWIDTH - DD.PLATLARGEWIDTH, DD.SCREENHEIGHT - 5*DD.PLATLARGEHEIGHT, DD.PLATLARGEWIDTH, DD.PLATSMALLHEIGHT));
		
			map.add(new Platform(DD.THIRDSPACING, DD.SCREENHEIGHT - 7*DD.PLATLARGEHEIGHT, DD.PLATLARGEWIDTH, DD.PLATSMALLHEIGHT));
			map.add(new Platform(2*DD.THIRDSPACING+DD.PLATLARGEWIDTH, DD.SCREENHEIGHT - 7*DD.PLATLARGEHEIGHT, DD.PLATLARGEWIDTH, DD.PLATSMALLHEIGHT));
			map.add(new Platform(3*DD.THIRDSPACING+2*DD.PLATLARGEWIDTH, DD.SCREENHEIGHT - 7*DD.PLATLARGEHEIGHT, DD.PLATLARGEWIDTH, DD.PLATSMALLHEIGHT));
			
		}
	}

	public ArrayList<Platform> getMap() {
		return map;
	}

	public void setMap(ArrayList<Platform> map) {
		this.map = map;
	}
	
	
}
