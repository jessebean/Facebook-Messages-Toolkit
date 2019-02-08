package facebookMessages;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class mainWindow
{

	public JFrame frame;
	public JLabel currentEntry;

	mainWindow()
	{
		frame = new JFrame("Facebook Data Parser");
		frame.setSize(500, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);
		frame.setVisible(true);
		
		
		
		currentEntry = new JLabel("I am hiddent rn");
		currentEntry.setSize(400, 30);
		currentEntry.setLocation(10, 220);
		frame.add(currentEntry);
		currentEntry.setVisible(false);

		JFileChooser fileChooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("ZIP Files", "zip");
		fileChooser.addChoosableFileFilter(filter);
		fileChooser.setAcceptAllFileFilterUsed(false);
		int returnValue = fileChooser.showOpenDialog(frame);
		if (returnValue == JFileChooser.APPROVE_OPTION)
		{
			File selectedFile = fileChooser.getSelectedFile();
			try
			{
				ZipFileThread zipFileExporter = new ZipFileThread(selectedFile, currentEntry);
				zipFileExporter.start();
				currentEntry.setVisible(true);

			} catch (Exception e)
			{
				JOptionPane.showMessageDialog(frame, "Zip file you selected was unzippable.", "Zip UnSuccessful",
						JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
				System.exit(1);
			}
		}

		JButton messageB = new JButton("Messages");
		messageB.setBounds(10, 10, 95, 30);
		messageB.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				
			}
		});

		
		frame.add(messageB);
		
		
	}

	
	
	
	public static void main(String args[])
	{
		new mainWindow();
//		ImageIcon icon = new ImageIcon("C:\\Users\\jhmcdona\\Desktop\\facebookmessages\\loading.gif");
//		icon.setImage(icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
//		JLabel loadingGif = new JLabel(icon);		
//		//loadingGif.setSize(30, 30);
//		loadingGif.setLocation(420, 220);
//		frame.add(loadingGif);
	
	}

}
