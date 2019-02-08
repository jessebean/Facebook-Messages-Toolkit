package facebookMessages;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

public class messagesWindow
{
	public JFrame frame;

	messagesWindow(JFrame parentFrame, File directory)
	{
		frame = new JFrame("Facebook Data Parser");
		frame.setSize(500, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);
		frame.setVisible(true);

		JFileChooser fileChooser = new JFileChooser(directory);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JSON", "json");
		fileChooser.addChoosableFileFilter(filter);
		fileChooser.setAcceptAllFileFilterUsed(false);
		int returnValue = fileChooser.showOpenDialog(frame);

	}

	public static void main(String[] args)
	{
		new messagesWindow(null, new File("\\\\ccanet\\home\\jhmcdona\\My Documents\\facebook-msot99\\messages"));
	}

}
