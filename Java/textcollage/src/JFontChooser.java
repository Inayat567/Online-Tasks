import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class JFontChooser extends JComponent {
  public static void main(String[] args) {
    Font font = JFontChooser.showDialog((Component) null, "Font");
    System.out.println("Font: " + font);
  }

  public static Font showDialog(Component parent, String title) {
    final JFontChooser pane = new JFontChooser();

    FontTracker ok = new FontTracker(pane);

    JDialog dialog = createDialog(parent, title, true, pane, ok, null);
    dialog.addWindowListener(new JFontChooserDialog.Closer());
    dialog.addComponentListener(new JFontChooserDialog.DisposeOnClose());
    dialog.setVisible(true);
    return ok.getSelectedFont();
  }

  public static JDialog createDialog(
      Component parent, String title,
      boolean modal,
      JFontChooser chooserPane,
      ActionListener okListener,
      ActionListener cancelListener) {
    return new JFontChooserDialog(parent, title, modal, chooserPane, okListener, cancelListener);
  }

  public JFontChooser() {
    setLayout(new BorderLayout());

    PreviewPanel previewPane = new PreviewPanel();
    m_inputPane = new InputPanel(previewPane);
    add(m_inputPane, BorderLayout.CENTER);
    add(previewPane, BorderLayout.SOUTH);
  }

  public Font getSelectedFont() {
    return m_inputPane.getSelectedFont();
  }

  private InputPanel m_inputPane;

  class InputPanel extends JPanel {
    public InputPanel(ListSelectionListener listener) {
      setLayout(new BorderLayout());

      Box nameBox = Box.createVerticalBox();
      nameBox.add(Box.createVerticalStrut(10));
      JLabel fontNameLabel = new JLabel("Font Name:");
      nameBox.add(fontNameLabel);
      if (listener != null) {
        m_fontNameList.addListSelectionListener(listener);
      }
      JScrollPane namePane = new JScrollPane(m_fontNameList);
      nameBox.add(namePane);
      nameBox.add(Box.createVerticalStrut(10));

      Box styleBox = Box.createVerticalBox();
      styleBox.add(Box.createVerticalStrut(10));
      JLabel fontStyleLabel = new JLabel("Font Style:");
      styleBox.add(fontStyleLabel);
      if (listener != null) {
        m_fontStyleList.addListSelectionListener(listener);
      }
      JScrollPane stylePane = new JScrollPane(m_fontStyleList);
      styleBox.add(stylePane);
      styleBox.add(Box.createVerticalStrut(10));

      Box sizeBox = Box.createVerticalBox();
      sizeBox.add(Box.createVerticalStrut(10));
      JLabel fontSizeLabel = new JLabel("Size:");
      sizeBox.add(fontSizeLabel);
      if (listener != null) {
        m_fontSizeList.addListSelectionListener(listener);
      }
      JScrollPane sizePane = new JScrollPane(m_fontSizeList);
      sizeBox.add(sizePane);
      sizeBox.add(Box.createVerticalStrut(10));

      Box mainBox = Box.createHorizontalBox();
      mainBox.add(Box.createHorizontalStrut(10));
      mainBox.add(nameBox);
      mainBox.add(Box.createHorizontalStrut(10));
      mainBox.add(styleBox);
      mainBox.add(Box.createHorizontalStrut(10));
      mainBox.add(sizeBox);
      mainBox.add(Box.createHorizontalStrut(10));
      add(mainBox, BorderLayout.CENTER);
    }

    public Font getSelectedFont() {
      return new Font(m_fontNameList.getFontName(), m_fontStyleList.getFontStyle(), m_fontSizeList.getFontSize());
    }

    private FontNameList m_fontNameList = new FontNameList();
    private FontStyleList m_fontStyleList = new FontStyleList();
    private FontSizeList m_fontSizeList = new FontSizeList();
  }

  class PreviewPanel extends JPanel
      implements ListSelectionListener {

    public PreviewPanel() {
      setLayout(new FlowLayout());

      Box box = Box.createVerticalBox();
      JLabel previewLabel = new JLabel("Preview:");
      box.add(previewLabel);
      m_text.setEditable(false);
      m_text.setBackground(Color.white);
      m_text.setForeground(Color.black);
      JScrollPane pane = new JScrollPane(m_text);
      pane.setPreferredSize(new Dimension(300, 80));
      box.add(pane);

      add(box);
    }

    public void valueChanged(ListSelectionEvent ev) {
      m_text.setFont(JFontChooser.this.getSelectedFont());
    }

    private JTextField m_text = new JTextField("The quick brown fox jumps over the lazy dog");
  }
}

class FontNameList extends JList {
  FontNameList() {
    super(m_fontNames);
    setSelectedIndex(0);
    setVisibleRowCount(5);
  }

  String getFontName() {
    String name = (String) getSelectedValue();
    return name;
  }

  private static final String[] m_fontNames = GraphicsEnvironment.getLocalGraphicsEnvironment()
      .getAvailableFontFamilyNames();
}

class FontStyleList extends JList {
  FontStyleList() {
    super(m_fontStyles);
    setSelectedIndex(0);
    setVisibleRowCount(5);
  }

  int getFontStyle() {
    int style = 0;
    String name = (String) getSelectedValue();
    if (name.equals("Regular")) {
      style = Font.PLAIN;
    } else if (name.equals("Italic")) {
      style = Font.ITALIC;
    } else if (name.equals("Bold")) {
      style = Font.BOLD;
    } else {
      style = Font.BOLD + Font.ITALIC;
    }
    return style;
  }

  private static final String[] m_fontStyles = { "Regular", "Italic", "Bold", "Bold Italic" };
}

class FontSizeList extends JList {
  FontSizeList() {
    super(m_fontSizes);
    setSelectedIndex(4);
    setVisibleRowCount(5);
  }

  int getFontSize() {
    int size = Integer.parseInt((String) getSelectedValue());
    return size;
  }

  private static final String[] m_fontSizes = {
      "6", "8", "10", "12", "14", "16", "18",
      "20", "22", "24", "36", "72"
  };
}

class JFontChooserDialog extends JDialog {
  public JFontChooserDialog(
      Component component,
      String title,
      boolean modal,
      JFontChooser chooserPane,
      ActionListener okListener,
      ActionListener cancelListener) {
    super(JOptionPane.getFrameForComponent(component), title, modal);

    m_chooserPane = chooserPane;

    Container contentPane = getContentPane();
    contentPane.setLayout(new BorderLayout());
    contentPane.add(m_chooserPane, BorderLayout.CENTER);

    JPanel buttonPane = new JPanel();
    buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));

    JButton okButton = new JButton("OK");
    getRootPane().setDefaultButton(okButton);
    okButton.setActionCommand("OK");
    if (okListener != null) {
      okButton.addActionListener(okListener);
    }
    okButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        setVisible(false);
      }
    });
    buttonPane.add(okButton);
    JButton cancelButton = new JButton("Cancel");
    cancelButton.setActionCommand("cancel");
    if (cancelListener != null) {
      cancelButton.addActionListener(cancelListener);
    }
    cancelButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        setVisible(false);
      }
    });
    buttonPane.add(cancelButton);

    contentPane.add(buttonPane, BorderLayout.SOUTH);

    pack();
    setLocationRelativeTo(component);
  }

  static class Closer extends WindowAdapter {
    public void windowClosing(WindowEvent e) {
      Window w = e.getWindow();
      w.setVisible(false);
    }
  }

  static class DisposeOnClose extends ComponentAdapter {
    public void componentHidden(ComponentEvent e) {
      Window w = (Window) e.getComponent();
      w.dispose();
    }
  }

  private JFontChooser m_chooserPane;
}

class FontTracker implements ActionListener {
  public FontTracker(JFontChooser chooser) {
    m_chooser = chooser;
  }

  public void actionPerformed(ActionEvent e) {
    m_font = m_chooser.getSelectedFont();
  }

  public Font getSelectedFont() {
    return m_font;
  }

  private JFontChooser m_chooser;
  private Font m_font;
}