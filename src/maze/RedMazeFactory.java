package maze;

public class RedMazeFactory extends MazeFactory{

	@Override
	public Wall makeWall ()
	{
		return new RedWall() ;
	}
	
	@Override
	public Room makeRoom (int i)
	{
		return new RedRoom(i);
	}
}
