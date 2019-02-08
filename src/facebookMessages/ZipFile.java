package facebookMessages;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.swing.JLabel;

public class ZipFile
{
	public ZipInputStream zis;
	public String directory;
	public JLabel currentEntry;

	ZipFile(File fileZip, JLabel cur) throws IOException
	{
		directory = fileZip.getPath().substring(0, fileZip.getPath().indexOf(".zip"));
		zis = new ZipInputStream(new FileInputStream(fileZip));
		currentEntry = cur;

	}

	public boolean readFile() throws IOException
	{
		File destDir = new File(directory);
		ZipEntry zipEntry = zis.getNextEntry();
		byte[] buffer = new byte[1024];
		while (zipEntry != null)
		{
			currentEntry.setText(zipEntry.getName());
			
			File destFile = new File(destDir, zipEntry.getName());

			if (destFile.getName().indexOf('.') == -1 && !destFile.exists())
			{
				destFile.mkdirs();
				continue;
			} else if (destFile.exists())
			{

				zipEntry = zis.getNextEntry();
				continue;
			}

			File newFile = newFile(destDir, zipEntry);
			FileOutputStream fos = new FileOutputStream(newFile);
			int len;
			while ((len = zis.read(buffer)) > 0)
			{
				fos.write(buffer, 0, len);
			}
			fos.close();
			zipEntry = zis.getNextEntry();

		}
		zis.closeEntry();
		zis.close();
		return true;
	}

	public static File newFile(File destinationDir, ZipEntry zipEntry) throws IOException
	{

		File destFile = new File(destinationDir, zipEntry.getName());

		String destDirPath = destinationDir.getCanonicalPath();
		String destFilePath = destFile.getCanonicalPath();

		if (!destFilePath.startsWith(destDirPath + File.separator))
		{
			throw new IOException("Entry is outside of the target dir: " + zipEntry.getName());
		}

		return destFile;
	}

	public static void main(String args[]) throws IOException
	{
		new ZipFile(new File("\\\\ccanet\\home\\jhmcdona\\My Documents\\facebook-msot99.zip"), null);
	}

}