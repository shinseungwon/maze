package maze;

import java.awt.Color;

public class Generator {
	Board b;
	Block[][] BLOCKS;

	Generator(Board b) {
		this.b = b;
		this.BLOCKS = b.BLOCKS;
	}

	public void Generate() {
		// start
		// start: x=8,y=9
		// end: x=1,y=0
		int posx = 8;
		int posy = 9;
		int cdir = 4;// entry direction
		// BLOCKS[9][8].traveled(4);

		// Hunt and Kill algorithm

		traversal(posx, posy, cdir);
		Block b;
		while ((b = Checker()) != null) {

			System.out.println("Dead end :" + "//" + b.posx + "," + b.posy);
			if (b.bridge == 1)// go left
				traversal(b.posx, b.posy, b.bridge);
			else if (b.bridge == 2)// go right
				traversal(b.posx, b.posy, b.bridge);
			else if (b.bridge == 3)// go up
				traversal(b.posx, b.posy, b.bridge);
			else if (b.bridge == 4)// go down
				traversal(b.posx, b.posy, b.bridge);
			else
				System.out.println("Bridge Error");
		}
		BLOCKS[9][8].down.setBackground(Color.WHITE);
	}

	private void traversal(int posx, int posy, int cdir) {
		if (posx > 9 || posx < 0 || posy > 9 || posy < 0)
			return;
		else if (BLOCKS[posy][posx].block.getBackground() == Color.PINK)
			return;
		else {

			// Delay for show
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
			}
			BLOCKS[posy][posx].traveled(cdir);

			int[] temp = new int[4];
			for (int i = 0; i < 4; i++)
				temp[i] = 0;

			int size = 0;
			if (posx != 0) {
				if (BLOCKS[posy][posx - 1].block.getBackground() != Color.PINK) {
					temp[0] = 1;
					size++;
				}
			}
			if (posx != 9) {
				if (BLOCKS[posy][posx + 1].block.getBackground() != Color.PINK) {
					temp[1] = 1;
					size++;
				}
			}
			if (posy != 0) {
				if (BLOCKS[posy - 1][posx].block.getBackground() != Color.PINK) {
					temp[2] = 1;
					size++;
				}
			}
			if (posy != 9) {
				if (BLOCKS[posy + 1][posx].block.getBackground() != Color.PINK) {
					temp[3] = 1;
					size++;
				}
			}

			int way = cdir;
			if (size != 0)
				while (way == cdir || temp[way - 1] == 0)
					way = (int) (Math.random() * 4) + 1;

			else {
				System.out.println("finish");
				return;
			}
			if (way == 1)// go left
				traversal(posx - 1, posy, 2);
			else if (way == 2)// go right
				traversal(posx + 1, posy, 1);
			else if (way == 3)// go up
				traversal(posx, posy - 1, 4);
			else if (way == 4)// go down
				traversal(posx, posy + 1, 3);
			else
				System.out.println("Error");

		}
	}

	private Block Checker() {
		for (int posy = 0; posy < 10; posy++) {
			for (int posx = 0; posx < 10; posx++) {
				if (BLOCKS[posy][posx].block.getBackground() != Color.PINK) {
					int[] temp = new int[4];
					for (int i = 0; i < 4; i++)
						temp[i] = 0;

					int size = 0;

					if (posx != 0) {
						if (BLOCKS[posy][posx - 1].block.getBackground() == Color.PINK) {
							temp[0] = 1;
							size++;
						}
					}
					if (posx != 9) {
						if (BLOCKS[posy][posx + 1].block.getBackground() == Color.PINK) {
							temp[1] = 1;
							size++;
						}
					}
					if (posy != 0) {
						if (BLOCKS[posy - 1][posx].block.getBackground() == Color.PINK) {
							temp[2] = 1;
							size++;
						}
					}
					if (posy != 9) {
						if (BLOCKS[posy + 1][posx].block.getBackground() == Color.PINK) {
							temp[3] = 1;
							size++;
						}
					}
					if (size == 0)
						continue;
					else {

						int way = -1;
						while (way == -1 || temp[way - 1] == 0)
							way = (int) (Math.random() * 4) + 1;

						if (way == 1) {
							BLOCKS[posy][posx].bridge = 1;
							return BLOCKS[posy][posx];
						} else if (way == 2) {
							BLOCKS[posy][posx].bridge = 2;
							return BLOCKS[posy][posx];
						} else if (way == 3) {
							BLOCKS[posy][posx].bridge = 3;
							return BLOCKS[posy][posx];
						} else if (way == 4) {
							BLOCKS[posy][posx].bridge = 4;
							return BLOCKS[posy][posx];
						} else {
							System.out.println("Nothing Found");
							return null;
						}
					}
				}
			}
		}
		return null;
	}
}
