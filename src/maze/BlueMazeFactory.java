package maze;

public class BlueMazeFactory extends MazeFactory {
	public Wall makeWall ()
	{
		return new BlueWall() ;
	}
	
	public Room makeRoom (int i)
	{
		return new GreenRoom(i);
	}
	
	public Door makeDoor (Room r1, Room r2)
	{
		return new BrownDoor(r1,r2);
	}
}
