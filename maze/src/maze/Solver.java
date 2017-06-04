package maze;

import java.awt.Color;
import java.util.ArrayList;

public class Solver {

	Board b;
	Block[][] BLOCKS;

	ArrayList<Integer> x = new ArrayList<>();
	ArrayList<Integer> y = new ArrayList<>();

	Solver(Board b) {
		this.b = b;
		this.BLOCKS = b.BLOCKS;
	}

	public void solve() {
		floodFill(8, 9);
		for (int i = x.size() - 1; i >= 0; i--) {

			// Delay for show
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
			}

			BLOCKS[y.get(i)][x.get(i)].block.setBackground(Color.YELLOW);

			if (i > 0) {
				if (y.get(i) - y.get(i - 1) == 1)
					BLOCKS[y.get(i)][x.get(i)].up.setBackground(Color.YELLOW);
				else if (y.get(i) - y.get(i - 1) == -1)
					BLOCKS[y.get(i)][x.get(i)].down.setBackground(Color.YELLOW);

				if (x.get(i) - x.get(i - 1) == 1)
					BLOCKS[y.get(i)][x.get(i)].left.setBackground(Color.YELLOW);
				else if (x.get(i) - x.get(i - 1) == -1)
					BLOCKS[y.get(i)][x.get(i)].right.setBackground(Color.YELLOW);
			}
		}
	}

	private boolean floodFill(int posx, int posy) {
		// Delay for show
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
		}

		if (BLOCKS[posy][posx].block.getBackground() == Color.CYAN)
			return false;

		BLOCKS[posy][posx].block.setBackground(Color.CYAN);
		boolean flag1 = false;
		if (posx == 1 && posy == 0) {
			System.out.println("Reach the End, Solved!");
			flag1 = true;
		}

		boolean flag2 = false;

		if (posx > 0) {
			if (BLOCKS[posy][posx].left.getBackground() != Color.BLACK) {
				if (floodFill(posx - 1, posy))
					flag2 = true;
				BLOCKS[posy][posx].left.setBackground(Color.CYAN);
			}
		}

		if (posx < 9) {
			if (BLOCKS[posy][posx].right.getBackground() != Color.BLACK) {
				if (floodFill(posx + 1, posy))
					flag2 = true;
				BLOCKS[posy][posx].right.setBackground(Color.CYAN);
			}
		}

		if (posy > 0) {
			if (BLOCKS[posy][posx].up.getBackground() != Color.BLACK) {
				if (floodFill(posx, posy - 1))
					flag2 = true;
				BLOCKS[posy][posx].up.setBackground(Color.CYAN);
			}
		}

		if (posy < 9) {
			if (BLOCKS[posy][posx].down.getBackground() != Color.BLACK) {
				if (floodFill(posx, posy + 1))
					flag2 = true;
				BLOCKS[posy][posx].down.setBackground(Color.CYAN);
			}
		}

		if (flag1 || flag2) {
			x.add(posx);
			y.add(posy);
			return true;
		} else
			return false;
	}
}
