import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class DrawTextItem {

	private final String string;

	private Font font = null;
	private int x = 0;
	private int y = 0;
	private Color textColor = Color.BLACK;
	private Color background = null;
	private boolean border = false;
	private double rotationAngle = 0;
	private double magnification = 1;
	private double textTransparency = 0;
	private double backgroundTransparency = 0;

	public DrawTextItem(String stringToDraw) {
		this(stringToDraw, 0, 0);
		this.font = new Font("Serif", Font.BOLD, 24);
		this.background = null;
	}

	public DrawTextItem(String stringToDraw, int x, int y) {
		if (stringToDraw == null)
			throw new NullPointerException("String can't be null.");
		string = stringToDraw;
		this.x = x;
		this.y = y;
	}

	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g.create();
		if (font != null)
			g2.setFont(font);
		FontMetrics fm = g2.getFontMetrics();
		int width = fm.stringWidth(string);
		int height = fm.getAscent() + fm.getDescent();
		g2.translate(x, y);
		if (magnification != 1) {
			float pixelSize = 1 / (float) magnification;
			g2.setStroke(new BasicStroke(pixelSize));
			g2.scale(magnification, magnification);
		}
		if (rotationAngle > 0)
			g2.rotate(-Math.PI * (rotationAngle / 180));
		Color colorToUseForText = textColor;
		if (colorToUseForText == null)
			colorToUseForText = g2.getColor();
		if (background != null) {
			if (backgroundTransparency == 0)
				g2.setColor(background);
			else
				g2.setColor(new Color(background.getRed(), background.getGreen(), background.getBlue(),
						(int) (255 * (1 - backgroundTransparency))));
			g2.fillRect(-width / 2 - 3, -height / 2 - 3, width + 6, height + 6);
		}
		if (textTransparency == 0)
			g2.setColor(colorToUseForText);
		else
			g2.setColor(new Color(colorToUseForText.getRed(),
					colorToUseForText.getGreen(), colorToUseForText.getBlue(),
					(int) (255 * (1 - textTransparency))));
		if (border)
			g2.drawRect(-width / 2 - 3, -height / 2 - 3, width + 6, height + 6);
		g2.drawString(string, -width / 2, -height / 2 + fm.getAscent());
	}

	public String getString() {
		return string;
	}

	public void setBackground(Color background) {
		this.background = background;
	}

	public void setBackgroundTransparency(double backgroundTransparency) {
		if (backgroundTransparency < 0 || backgroundTransparency > 1)
			throw new IllegalArgumentException("Transparency must be in the range 0 to 1.");
		this.backgroundTransparency = backgroundTransparency;
	}

	public void setBorder(boolean border) {
		this.border = border;
	}

	public void setFont(Font font) {
		this.font = font;
	}

	public void setMagnification(double magnification) {
		if (magnification == 0)
			throw new IllegalArgumentException("Magnification cannot be 0.");
		this.magnification = magnification;
	}

	public void setRotationAngle(double rotationAngle) {
		this.rotationAngle = rotationAngle;
	}

	public void setTextColor(Color textColor) {
		this.textColor = textColor;
	}

	public void setTextTransparency(double textTransparency) {
		if (textTransparency < 0 || textTransparency > 1)
			throw new IllegalArgumentException("Transparency must be in the range 0 to 1.");
		this.textTransparency = textTransparency;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Color getBackground() {
		return background;
	}

	public double getBackgroundTransparency() {
		return backgroundTransparency;
	}

	public boolean getBorder() {
		return border;
	}

	public Font getFont() {
		return font;
	}

	public double getMagnification() {
		return magnification;
	}

	public double getRotationAngle() {
		return rotationAngle;
	}

	public Color getTextColor() {
		return textColor;
	}

	public double getTextTransparency() {
		return textTransparency;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

}
