package Model;

public class MazeTraversal {

	public static void main(String[] args) {
		
		char [][] maze2 = {{'.', '.', 'x', '.', '.'}, 
						  {'x', '.', 'x', '.', '.'},
						  {'.', '.', '.', '.', '.'},
						  {'.', 'x', '.', 'x', 'x'},
						  {'.', '.', '.', 'x', '.'}};
		char [][] maze = {{'.','.','.'},
						  {'.','x','x'},
						  {'.','x','.'}};
		
		boolean success = move(maze, 0, 0);
		if (success) {
			System.out.println("Successfully traversed maze");
		} else {
			System.out.println("could not reach exit in bottom right corner");
		}
		for(int row = 0; row < maze.length; row++) {
			for (int col = 0; col < maze[row].length; col++)
				System.out.print(maze[row][col]);
			System.out.println();
		}
	}

	private static boolean move(char[][] maze, int row, int col) {
		
		boolean success = false;
		System.out.println("DEBUG - tried to move to " + row + ", " + col);
		
		if (validMove(maze,row, col)) {
			//drop a bread crumb to track we've been here
			markVisited(maze, row, col); 
			if (atExit(maze, row, col))
				return true;
			//not at exit so need to try other directions
			success = move(maze, row+1, col); //down
			if (!success)
				success = move(maze, row, col+1); //right
			if (!success)
				success = move(maze, row-1, col); //up
			if (!success)
				success = move(maze, row, col-1); //left
			if (!success)
				markDeadEnd(maze, row, col);
		}
		return success;
	}

	private static void markDeadEnd(char[][] maze, int row, int col) {

		maze[row][col] = 'd';
		
	}

	private static void markVisited(char[][] maze, int row, int col) {

		maze[row][col] = '*';
		
	}

	private static boolean atExit(char[][] maze, int row, int col) {
		
		return row == maze.length-1 && col == maze[row].length-1;
	}

	private static boolean validMove(char[][] maze, int row, int col) {
		//inside maze and non visited room
		return row >= 0 && row < maze.length
				&& col >= 0 && col < maze[row].length
				&& maze[row][col] == '.';
		
	}

}
