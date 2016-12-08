package maze;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MazeGame {
	public Maze createMaze(MazeFactory fact)
	{
		Maze maze = new Maze();
		Room firstroom = fact.makeRoom(0) ;
		Room secondroom = fact.makeRoom(1) ;
		maze.addRoom(firstroom);
		maze.addRoom(secondroom);
		Door door = fact.makeDoor(firstroom, secondroom);
		firstroom.setSide(Direction.North, fact.makeWall());
		firstroom.setSide(Direction.East, door);
		firstroom.setSide(Direction.South, fact.makeWall());
		firstroom.setSide(Direction.West, fact.makeWall());
		secondroom.setSide(Direction.North, fact.makeWall());
		secondroom.setSide(Direction.East, fact.makeWall());
		secondroom.setSide(Direction.South, fact.makeWall());
		secondroom.setSide(Direction.West, door);
		maze.setCurrentRoom(0);
		//System.out.println("The maze does not have any rooms yet!");
		return maze;
	}

	public Maze loadMaze(MazeFactory fact, final String path)
	{
		List <Room> rooms = new ArrayList <Room> () ; 
		List <Door> doors = new ArrayList <Door> () ;
		List <List <String>> doorinfo = new ArrayList <List <String>> () ;
		List <List <String>> roominfo = new ArrayList <List <String>> () ;
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		    	String[] arr = line.split("\\s+") ;
		    	if (arr.length > 2){
			    	if (arr[0].equals("room")){
			        	System.out.print("Was room ");
			        	try { 
		                    int c = Integer.parseInt(arr[1]); 
		                    System.out.println("with index " + c);
		                    rooms.add(fact.makeRoom(c)) ;
		                } catch(NumberFormatException e) { 
		                    System.out.print(""); 
		                } catch(NullPointerException e) {
		                	System.out.print("");
		                }
			        	List <String> info = new ArrayList <String> () ;
			        	info.add(arr[2]) ; //N
			        	info.add(arr[3]) ; //S
			        	info.add(arr[4]) ; //E
			        	info.add(arr[5]) ; //W
			        	roominfo.add(info) ;
		            	//walls/openings/doors
			        }
			        else{
			        	System.out.println("Was door with index " + arr[1]);
			        	List <String> info = new ArrayList <String> () ;
			        	info.add(arr[2]) ; //room 1
			        	info.add(arr[3]) ; //room 2
			        	info.add(arr[4]) ; //opened/closed
			        	doorinfo.add(info) ;
			        }
		    	}
		    }
		} catch (FileNotFoundException e1) {
			System.out.println("Error opening file");
		} catch (IOException e1) {
			System.out.println("Error reading file");
		}
	  	
		Maze maze = new Maze();
		for (int i = 0; i < rooms.size(); i++){
			maze.addRoom(rooms.get(i));
		}
		//create doors
		for (int i = 0; i < doorinfo.size(); i++){
			String s = doorinfo.get(i).get(0) ;
			int roomindex1 = 0, roomindex2 = 0 ;
			try { 
				roomindex1 = Integer.parseInt(s) ;
            } catch(NumberFormatException e) { 
                System.out.println("Error converting to int"); 
            } catch(NullPointerException e) {
            	System.out.println("Error converting null to int");
            }
			s = doorinfo.get(i).get(1) ;
			try { 
				roomindex2 = Integer.parseInt(s) ;
            } catch(NumberFormatException e) { 
                System.out.println("Error converting to int"); 
            } catch(NullPointerException e) {
            	System.out.println("Error converting null to int");
            }
			Door temp = fact.makeDoor(rooms.get(roomindex1), rooms.get(roomindex2)) ; 
			if (doorinfo.get(i).get(2).equals("open"))
					temp.setOpen(true) ;
			doors.add(temp) ;
		}
		//sides (walls, doors, rooms)
		for (int i = 0; i < rooms.size(); i++){
			Direction[] dirs = {Direction.North, Direction.South, Direction.East, Direction.West} ;
			for (int j = 0; j < roominfo.get(i).size(); j++){
				String s = roominfo.get(i).get(j) ;
				if (s.equals("wall")){
					rooms.get(i).setSide(dirs[j], fact.makeWall()) ;
				}
				else if (s.charAt(0) == 'd'){
					int doorindex = Integer.parseInt(String.valueOf(s.charAt(1))) ;
					rooms.get(i).setSide(dirs[j], doors.get(doorindex)) ;
				}
				else
					rooms.get(i).setSide(dirs[j], rooms.get(Integer.parseInt(s))) ;
			}
		}
		
		maze.setCurrentRoom(0);
		//System.out.println("Please load a maze from the file!");
		return maze;
	}
}
