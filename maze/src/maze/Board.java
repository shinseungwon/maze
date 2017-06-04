package maze;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Board extends Frame {
	private static final long serialVersionUID = 1L;

	private static final int WIDTH = 70;

	Panel board = new Panel();
	Panel buttons = new Panel();
	Button generate = new Button("generate");
	Button solve = new Button("solve");
	Label[][] blocks = new Label[10][10];
	Label[][] vlines = new Label[10][11];
	Label[][] hlines = new Label[11][10];

	Block[][] BLOCKS = new Block[10][10];

	Generator g = new Generator(this);

	Solver s = new Solver(this);

	Board b = this;

	boolean reset = false;

	public Board(String title) {
		super(title);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
				System.exit(0);
			}
		});
		init();
		super.setSize(400, 550);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frm = super.getSize();
		int xpos = (int) (screen.getWidth() / 2 - frm.getWidth() / 2);
		int ypos = (int) (screen.getHeight() / 2 - frm.getWidth() / 2);
		super.setLocation(xpos, ypos);
		super.setVisible(true);
	}

	public void init() {
		// make a interface in here

		buttons.setLayout(new GridLayout(1, 2));
		buttons.add(generate);
		buttons.add(solve);
		board.setLayout(null);
		this.setLayout(new BorderLayout());
		this.add("Center", board);
		this.add("South", buttons);

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 11; j++) {
				vlines[i][j] = new Label("");
				vlines[i][j].setAlignment(1);
				board.add(vlines[i][j]);
				vlines[i][j].setBackground(Color.BLACK);
				vlines[i][j].setSize(5, 30);
				vlines[i][j].setLocation(j * 35 + 15, i * 35 + WIDTH);
			}
		}

		for (int i = 0; i < 11; i++) {
			for (int j = 0; j < 10; j++) {
				hlines[i][j] = new Label("");
				hlines[i][j].setAlignment(1);
				board.add(hlines[i][j]);
				hlines[i][j].setBackground(Color.BLACK);
				hlines[i][j].setSize(30, 5);
				hlines[i][j].setLocation(j * 35 + 20, i * 35 + WIDTH - 5);
			}
		}

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				blocks[i][j] = new Label("");
				blocks[i][j].setAlignment(1);
				board.add(blocks[i][j]);
				blocks[i][j].setBackground(Color.GREEN);
				blocks[i][j].setSize(30, 30);
				blocks[i][j].setLocation(j * 35 + 20, i * 35 + WIDTH);
				BLOCKS[i][j] = new Block(i, j, blocks[i][j], vlines[i][j], vlines[i][j + 1], hlines[i][j],
						hlines[i + 1][j]);
			}
		}

		hlines[10][8].setBackground(Color.WHITE);
		hlines[0][1].setBackground(Color.WHITE);

		Label start = new Label("Start");
		start.setSize(50, 50);
		Label finish = new Label("Finish");
		finish.setSize(50, 50);

		board.add(start);
		board.add(finish);

		start.setLocation(300, 410);
		finish.setLocation(50, 25);

		generate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!reset) {
					g.Generate();
					BLOCKS[0][1].up.setBackground(Color.WHITE);
					generate.setLabel("reset");
					reset = true;

				} else {
					for (int i = 0; i < 10; i++) {
						for (int j = 0; j < 10; j++) {
							BLOCKS[i][j].reset();
						}
					}
					generate.setLabel("Generate");
					reset = false;
				}
			}
		});

		solve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				s.solve();
			}
		});

	}

}
