package facebookMessages;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.swing.JLabel;

import facebookMessages.ZipFile;

public class ZipFileThread extends Thread
{

	public File zip;
	public ZipFile zipFileClass;
	public JLabel cur;

	ZipFileThread(File fileZip, JLabel currentEntry)
	{

		zip = fileZip;
		cur = currentEntry;
	}

	public void run()
	{
		try
		{
			zipFileClass = new ZipFile(zip,cur);
			zipFileClass.readFile();
			

		} catch (Exception e)
		{
			// Throwing an exception
			System.out.println("Exception is caught");
			e.printStackTrace();
		}
	}

	public static void main(String args[]) throws InterruptedException, IOException
	{
		File f = new File("\\\\ccanet\\home\\jhmcdona\\My Documents\\facebook-msot99.zip");

		ZipFileThread zipFileExporter = new ZipFileThread(f, null);
		zipFileExporter.start();
		zipFileExporter.setName("UnZip");
		
		while(true)
		{
			Thread.sleep(5000);
			System.out.println(zipFileExporter.zipFileClass.zis.available());
			
		}

	}

}