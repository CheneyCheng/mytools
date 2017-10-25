/**
 * 
 */
package org.mytools.core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.mytools.util.FileUtils;

/**
 * @author Cheney.Cheng
 * @since 2017年10月19日
 *
 */
public class CharacterFormater4MD {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		String dirpath = "C:\\Work\\TT\\Books\\笔记";
		doFormat(dirpath);
	}

	private static void doFormat(String rootpath) throws IOException {
		File dir = new File(rootpath);
		if (!dir.exists()) {
			System.out.println("文件不存在");
			return;
		}
		File[] files = FileUtils.listAllFiles(dir, new String[] { "md", "txt" });
		System.out.println("待格式化文件数量： " + files.length);
		for (int i = 0; i < files.length; i++) {
			File file = files[i];
			String name = file.getName();
			System.out.println("正在处理第" + (i + 1) + "个文件，文件名为： " + name);
			String extension = name.substring(name.lastIndexOf("."));
			String nameWithNoExtension = name.substring(0, name.lastIndexOf("."));
			File tmp = FileUtils.createNewFile(nameWithNoExtension + "_tmp" + extension);
			BufferedReader reader = new BufferedReader(new FileReader(file));
			BufferedWriter writer = new BufferedWriter(new FileWriter(tmp));
			String line = null;
			while ((line = reader.readLine()) != null) {
				writer.write(formatLine(line) + "\r\n");
			}
			reader.close();
			writer.close();
			file.delete();
			tmp.renameTo(file);
		}
		System.out.println("文件格式化结束。");
	}

	private static String formatLine(String line) {
		if (StringUtils.isEmpty(line)) {
			return line;
		}
		return line.replaceAll("([A-Za-z0-9])([\u4e00-\u9fa5]+)", "$1 $2").replaceAll("([\u4e00-\u9fa5])([A-Za-z0-9]+)",
				"$1 $2");
	}

}
