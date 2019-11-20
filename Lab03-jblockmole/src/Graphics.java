import java.awt.Color;
import se.lth.cs.pt.window.SimpleWindow;
/*
Klass som hanterar all grafik. Importerar SimpleWindow och Color för
ett fönster och färg respektive
*/
public class Graphics {
	private int width, blockSize, height;
	private SimpleWindow w;
	
	/*
	 * Detta är konstruktorn som plockar in argumenten w, h och bs som sätter width,
	 * height och blockSize respektive.
	 */
	public Graphics(int w, int h, int bs) {
		width = w;
		height = h;
		blockSize = bs;	
		this.w = new SimpleWindow(width * blockSize, height * blockSize, "Digging");
	}

	/*
	 * Getters för width, height och blockSize. Setters kanske kommer i någon framtida labb?
	 */
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	public int getBlockSize() {
		return blockSize;
	}
	
	
	public void square() {
		w.moveTo(10, 10);
		w.lineTo(10, 20);
		w.lineTo(20, 20);
		w.lineTo(20, 10);
		w.lineTo(10, 10);
	}
	
	public void block(int x, int y, Color color) {
		/*
		 * X bestämmer x-koordinat för vänsterhörnet och Y y-koordinat. Bottom blir
		 * storleken i y-led som bestäms av blocksize. Color sätts av setLineColor.
		 */
		int left = x * blockSize;
		int right = left + blockSize - 1;
		int top = y * blockSize;
		int bottom = top + blockSize - 1;
		w.setLineColor(color);
		
		/*
		 * För att visualisera hur den ritas kan man introducera en delay I det här
		 * fallet ritas de från vänster till höger, rad för rad. moveTo flyttar cursorn
		 * ned till nästa rad. Den ritar ut så många rader som 
		 * w.delay(50);
		 */	
		for(int row = top; row <= bottom; row++) {
			w.moveTo(left, row);
			w.lineTo(right,  row);		
		}
	}
	
	/*
	 * Skapar en rektangel med två nestade for-loopar. Sätter färg genom color och
	 * respektive paramterer i block
	 */
	public void rectangle(int x, int y, int width, int height, Color color) {
		for (int yy = y; yy < y + height; yy++) {
			for (int xx = x; xx < x + width; xx++) {
				block(xx, yy, color);
			}
		}
	}
	
	/* Delegator som kopplar graphics till simpleWindow */
	public char waitForKey() {
		return w.waitForKey();
	}
}
