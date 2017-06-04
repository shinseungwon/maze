package maze;

import java.awt.Color;
import java.awt.Label;

public class Block {

	int posx;
	int posy;

	Label block, left, right, up, down;

	int bridge = 0;

	Block(int posy, int posx, Label block, Label left, Label right, Label up, Label down) {

		this.posx = posx;
		this.posy = posy;

		this.block = block;
		this.left = left;
		this.right = right;
		this.up = up;
		this.down = down;

	}

	public void reset() {

		block.setBackground(Color.GREEN);
		left.setBackground(Color.BLACK);
		right.setBackground(Color.BLACK);
		up.setBackground(Color.BLACK);
		down.setBackground(Color.BLACK);
	}

	public void traveled(int d) {// from where?

		// 1-l 2-r 3-u 4-d
		switch (d) {
		case 1:
			left.setBackground(Color.PINK);
			break;
		case 2:
			right.setBackground(Color.PINK);
			break;
		case 3:
			up.setBackground(Color.PINK);
			break;
		case 4:
			down.setBackground(Color.PINK);
			break;
		default:
			break;
		}

		block.setBackground(Color.PINK);

	}
}
