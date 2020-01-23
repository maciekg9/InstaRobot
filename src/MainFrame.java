import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;

import javax.swing.*;
import javax.swing.text.*;


class JTextFieldLimit extends PlainDocument {
    private int limit;
    JTextFieldLimit(int limit) {
        super();
        this.limit = limit;
    }

    JTextFieldLimit(int limit, boolean upper) {
        super();
        this.limit = limit;
    }

    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
        if (str == null)
            return;

        if ((getLength() + str.length()) <= limit) {
            super.insertString(offset, str, attr);
        }
    }
}
public class MainFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private PrintStream standardOut;
    private JTextField hashTagsTextField = new JTextField(10);


    public  MainFrame(String title) {
        super(title);


        // Set layout manager
        setLayout(new FlowLayout());

        JTextArea consoleLog = new JTextArea(16,38);
        consoleLog.setEditable(false);
        JScrollPane consoleLogScrollPane = new JScrollPane(consoleLog);
        JButton runButton = new JButton("Start Script");
        JButton stopButton = new JButton("Stop Script");
        JButton saveButton = new JButton("+");
        JButton clearButton = new JButton("Clear Added Hashtags");
        JLabel consoleLogLabel = new JLabel("Console Log");
        JLabel hashTagLabel = new JLabel("Add Hashtag");
        JList<String> hashList = new JList<>(DataStorage.hashTags);
        JScrollPane hashListScrollPane = new JScrollPane(hashList);
        // Add swing components to content pane
        PrintStream printStream = new PrintStream(new CustomOutputStream(consoleLog));
        standardOut = System.out;
        System.setOut(printStream);
        System.setErr(printStream);

        Container c = getContentPane();
        c.add(consoleLogLabel);
        c.add(consoleLogScrollPane);
        c.add(runButton);
        c.add(stopButton);
        c.add(clearButton);
        c.add(hashTagLabel);
        c.add(hashTagsTextField);
        c.add(saveButton);
        c.add(hashListScrollPane);
        hashTagsTextField.setDocument(new JTextFieldLimit(15));
		runButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//				InstaService.likeService();
				MultiThreading.likesServiceThread();
				MultiThreading.printLog();
			}
		}
		);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            InstaService.addHashTagService(getTextFromTextArea());
            }
        }
        );
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InstaService.stopService();
            }
        }
        );
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            InstaService.clearService();
            }
        }
        );
    }
    public String getTextFromTextArea()
    {
        return this.hashTagsTextField.getText();
    }

}