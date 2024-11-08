import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

public class DrawTextPanel extends JPanel {

	private ArrayList<DrawTextItem> textItems;
	private Color currentTextColor = Color.BLACK;
	private Canvas canvas;
	private JTextField input;
	private SimpleFileChooser fileChooser;
	private JMenuBar menuBar;
	private MenuHandler menuHandler;
	private JMenuItem undoMenuItem;

	private class Canvas extends JPanel {
		Canvas() {
			setPreferredSize(new Dimension(800, 600));
			setBackground(Color.LIGHT_GRAY);
			setFont(new Font("Serif", Font.BOLD, 24));
		}

		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
			for (DrawTextItem textItem : textItems) {
				textItem.draw(g);
			}
		}
	}

	private class MenuHandler implements ActionListener {
		public void actionPerformed(ActionEvent evt) {
			doMenuCommand(evt.getActionCommand());
		}
	}

	public DrawTextPanel() {
		fileChooser = new SimpleFileChooser();
		undoMenuItem = new JMenuItem("Remove Item");
		undoMenuItem.setEnabled(false);
		menuHandler = new MenuHandler();
		textItems = new ArrayList<>();
		setLayout(new BorderLayout(3, 3));
		setBackground(Color.BLACK);
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		canvas = new Canvas();
		add(canvas, BorderLayout.CENTER);
		JPanel bottom = new JPanel();
		bottom.add(new JLabel("Text to add: "));
		input = new JTextField("Hello World!", 40);
		bottom.add(input);
		add(bottom, BorderLayout.SOUTH);
		canvas.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				doMousePress(e);
			}
		});
	}

	public void doMousePress(MouseEvent e) {
		String text = input.getText().trim();
		if (text.length() == 0) {
			input.setText("Hello World!");
			text = "Hello World!";
		}
		DrawTextItem s = new DrawTextItem(text, e.getX(), e.getY());
		s.setTextColor(currentTextColor);
		textItems.add(s);

		// SOME OTHER OPTIONS THAT CAN BE APPLIED TO TEXT ITEMS:
		// s.setFont( new Font( "Serif", Font.ITALIC + Font.BOLD, 12 )); // Default is
		// null, meaning font of canvas.
		// s.setMagnification(3); // Default is 1, meaning no magnification.
		// s.setBorder(true); // Default is false, meaning don't draw a border.
		// s.setRotationAngle(25); // Default is 0, meaning no rotation.
		// s.setTextTransparency(0.3); // Default is 0, meaning text is not at all
		// transparent.
		// s.setBackground(Color.BLUE); // Default is null, meaning don't draw a
		// background area.
		// s.setBackgroundTransparency(0.7); // Default is 0, meaning background is not
		// transparent.

		undoMenuItem.setEnabled(true);
		canvas.repaint();
	}

	public JMenuBar getMenuBar() {
		if (menuBar == null) {
			menuBar = new JMenuBar();

			String commandKey;
			if (System.getProperty("mrj.version") == null)
				commandKey = "control ";
			else
				commandKey = "meta ";

			JMenu fileMenu = new JMenu("File");
			menuBar.add(fileMenu);
			JMenuItem saveItem = new JMenuItem("Save...");
			saveItem.setAccelerator(KeyStroke.getKeyStroke(commandKey + "N"));
			saveItem.addActionListener(menuHandler);
			fileMenu.add(saveItem);
			JMenuItem openItem = new JMenuItem("Open...");
			openItem.setAccelerator(KeyStroke.getKeyStroke(commandKey + "O"));
			openItem.addActionListener(menuHandler);
			fileMenu.add(openItem);
			fileMenu.addSeparator();
			JMenuItem saveImageItem = new JMenuItem("Save Image...");
			saveImageItem.addActionListener(menuHandler);
			fileMenu.add(saveImageItem);

			JMenu editMenu = new JMenu("Edit");
			menuBar.add(editMenu);
			undoMenuItem.addActionListener(menuHandler);
			undoMenuItem.setAccelerator(KeyStroke.getKeyStroke(commandKey + "Z"));
			editMenu.add(undoMenuItem);
			editMenu.addSeparator();
			JMenuItem clearItem = new JMenuItem("Clear");
			clearItem.addActionListener(menuHandler);
			editMenu.add(clearItem);

			JMenu optionsMenu = new JMenu("Options");
			menuBar.add(optionsMenu);
			JMenuItem colorItem = new JMenuItem("Set Text Color...");
			colorItem.setAccelerator(KeyStroke.getKeyStroke(commandKey + "T"));
			colorItem.addActionListener(menuHandler);
			optionsMenu.add(colorItem);
			JMenuItem bgColorItem = new JMenuItem("Set Background Color...");
			bgColorItem.addActionListener(menuHandler);
			optionsMenu.add(bgColorItem);
			JMenuItem fontItem = new JMenuItem("Set Font...");
			fontItem.addActionListener(menuHandler);
			optionsMenu.add(fontItem);

		}
		return menuBar;
	}

	private void doMenuCommand(String command) {
		if (command.equals("Save...")) {
			File saveFile = fileChooser.getOutputFile(this, "Select Save File", "drawing.txt");
			if (saveFile != null) {
				try (PrintWriter writer = new PrintWriter(saveFile)) {
					Color bgColor = canvas.getBackground();
					writer.println(bgColor.getRed() + " " + bgColor.getGreen() + " " + bgColor.getBlue());

					for (DrawTextItem item : textItems) {
						writer.println(item.getString());
						writer.println(item.getX() + " " + item.getY());
						Color textColor = item.getTextColor();
						writer.println(textColor.getRed() + " " + textColor.getGreen() + " " + textColor.getBlue());
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(this, "Error saving file:\n" + e.getMessage());
				}
			}
		} else if (command.equals("Open...")) {
			File openFile = fileChooser.getInputFile(this, "Open Drawing");
			if (openFile != null) {
				try (Scanner scanner = new Scanner(openFile)) {
					// Read background color
					int bgRed = Integer.parseInt(scanner.nextLine());
					int bgGreen = Integer.parseInt(scanner.nextLine());
					int bgBlue = Integer.parseInt(scanner.nextLine());
					Color bgColor = new Color(bgRed, bgGreen, bgBlue);
					canvas.setBackground(bgColor);

					// Read DrawTextItems
					textItems.clear(); // Clear existing items
					while (scanner.hasNextLine()) {
						String text = scanner.nextLine();
						
						// Read x and y values
						String[] xyValues = scanner.nextLine().split(" ");
						int x = Integer.parseInt(xyValues[0]);
						int y = Integer.parseInt(xyValues[1]);

						// Read textColor values
						String[] textColorValues = scanner.nextLine().split(" ");
						int textRed = Integer.parseInt(textColorValues[0]);
						int textGreen = Integer.parseInt(textColorValues[1]);
						int textBlue = Integer.parseInt(textColorValues[2]);
						
						Color textColor = new Color(textRed, textGreen, textBlue);
						DrawTextItem newItem = new DrawTextItem(text, x, y);
						newItem.setTextColor(textColor);
						textItems.add(newItem);
					}

				} catch (Exception e) {
					JOptionPane.showMessageDialog(this,
							"Error opening file: " + e.getMessage(),
							"Error", JOptionPane.ERROR_MESSAGE);
				}
				canvas.repaint();
			}
		} else if (command.equals("Clear")) { // remove all strings
			textItems.clear();
			undoMenuItem.setEnabled(false);
			canvas.repaint();
		} else if (command.equals("Remove Item")) {
			textItems.remove(textItems.size() - 1);
			undoMenuItem.setEnabled(!textItems.isEmpty());
			canvas.repaint();
		} else if (command.equals("Set Text Color...")) {
			Color c = JColorChooser.showDialog(this, "Select Text Color", currentTextColor);
			if (c != null)
				currentTextColor = c;
		} else if (command.equals("Set Background Color...")) {
			Color c = JColorChooser.showDialog(this, "Select Background Color", canvas.getBackground());
			if (c != null) {
				canvas.setBackground(c);
				canvas.repaint();
			}
		} else if (command.equals("Save Image...")) { // save a PNG image of the drawing area
			File imageFile = fileChooser.getOutputFile(this, "Select Image File Name", "textimage.png");
			if (imageFile == null)
				try {
					BufferedImage image = new BufferedImage(canvas.getWidth(), canvas.getHeight(),
							BufferedImage.TYPE_INT_RGB);
					Graphics g = image.getGraphics();
					g.setFont(canvas.getFont());
					canvas.paintComponent(g); // draws the canvas onto the BufferedImage, not the screen!
					boolean ok = ImageIO.write(image, "PNG", imageFile); // write to the file
					if (ok == false)
						throw new Exception("PNG format not supported (this shouldn't happen!).");
				} catch (Exception e) {
					JOptionPane.showMessageDialog(this,
							"Sorry, an error occurred while trying to save the image:\n" + e);
				}
		} else if (command.equals("Set Font...")) {
			setFont();
		}
	}

	private void setFont() {
		Font selectedFont = JFontChooser.showDialog(DrawTextPanel.this, "Select Font");
		
		if (selectedFont != null) {
			for (DrawTextItem textItem : textItems) {
				textItem.setFont(selectedFont);
			}
			canvas.repaint();
		}
	}
}
