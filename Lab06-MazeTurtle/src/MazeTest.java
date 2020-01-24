import se.lth.cs.pt.maze.Maze;
import se.lth.cs.pt.window.SimpleWindow;

/*
 * Tester for MazeWalker
 * */
public class MazeTest {

	public static void main(String[] args) {

		/* Initial variables and things that need to be instanced */
		SimpleWindow window;
		Turtle turtle;
		Maze maze;
		int mazeNbr = 0;

		/* Initial screen, choose maze */
		window = new SimpleWindow(512, 512, "MazeTest");
		window.moveTo(window.getWidth() / 2, window.getHeight() / 2);
		window.writeText("Vilken labyrint?");
		mazeNbr = Character.getNumericValue(window.waitForKey());
		window.clear();

		/* Instantiate maze and turtle, as well as mazewalker */
		maze = new Maze(mazeNbr);
		turtle = new Turtle(window, maze.getXEntry(), maze.getYEntry());
		MazeWalker mw = new MazeWalker(turtle);
		turtle.penDown();
		maze.draw(window);
		mw.walk(maze);

		/* Loop for mazewalker */
		while (!maze.atExit(turtle.getX(), turtle.getY())) {
			mw.walk(maze);
			window.delay(10);
		}

	}

}
