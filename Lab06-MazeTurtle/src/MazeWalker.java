import se.lth.cs.pt.maze.Maze;


public class MazeWalker {
	/*
	 * Super simple algorithm to traverse maze using the 
	 * left hand method supplied according to the lab instructions
	 */
	private Turtle turtle;
	private int d, x, y;

	public MazeWalker(Turtle t) {
		turtle = t;
	}

	public void walk(Maze maze) {
		/* Get direction and positions, put in variables */
		d = turtle.getDirection();
		x = turtle.getX();
		y = turtle.getY();

		/* Left and front collision check, and move */
		if (maze.wallAtLeft(d, x, y)) {
			if (maze.wallInFront(d, x, y)) {
				turtle.left(-90);
				turtle.forward(1);
			} else {
				turtle.forward(1);
			}

		} else {
			turtle.left(90);
			turtle.forward(1);
		}
	}
}
