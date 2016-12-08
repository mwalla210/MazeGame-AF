package maze;

import maze.ui.MazeViewer;

public class Driver {
	public static RedMazeFactory rmf = new RedMazeFactory();
	public static BlueMazeFactory bmf = new BlueMazeFactory();
	public static MazeFactory mf = new MazeFactory();
	public static MazeGame mg = new MazeGame();
	public static void main(String[] args)
	{
	    if (args.length > 1) //passed type and filepath
	    {
	    	if (args[0].equals("red"))
	    	{
	    		//Red load
	    		Maze maze2 = mg.loadMaze(rmf, args[1]) ;
		    	MazeViewer viewer = new MazeViewer(maze2);
			    viewer.run();
	    	}
	    	else if (args[0].equals("blue"))
	    	{
	    		//Blue load
	    		Maze maze2 = mg.loadMaze(bmf, args[1]) ;
		    	MazeViewer viewer = new MazeViewer(maze2);
			    viewer.run();
	    	}
	    	else if (args[0].equals("basic"))
	    	{
	    		//Basic load
	    		Maze maze2 = mg.loadMaze(mf, args[1]) ;
		    	MazeViewer viewer = new MazeViewer(maze2);
			    viewer.run();
	    	}
	    }
	    else if (args.length == 1)
	    {
	    	if (args[0].equals("red")) //passed type but no filepath
	    	{
	    		//Make default red
	    		Maze maze = mg.createMaze(rmf) ;
			    MazeViewer viewer = new MazeViewer(maze);
			    viewer.run();
	    	}
	    	else if (args[0].equals("blue")) //passed type but no filepath
	    	{
	    		//Make default blue
	    		Maze maze = mg.createMaze(bmf);
			    MazeViewer viewer = new MazeViewer(maze);
			    viewer.run();
	    	}
	    	else if (args[0].equals("basic")) //passed type but no filepath
	    	{
	    		//Make default basic
	    		Maze maze = mg.createMaze(mf);
			    MazeViewer viewer = new MazeViewer(maze);
			    viewer.run();
	    	}
	    	else //passed just a file path, proceed with basic
	    	{
	    		//Basic load
	    		Maze maze2 = mg.loadMaze(mf, args[0]) ;
		    	MazeViewer viewer = new MazeViewer(maze2);
			    viewer.run();
	    	}
	    }
	    else{
	    	Maze maze = mg.createMaze(mf);
		    MazeViewer viewer = new MazeViewer(maze);
		    viewer.run();
	    }
	}
}
