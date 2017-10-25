package org.mytools.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Cheney.Cheng
 * @since 2017年10月24日
 *
 */
public class FileUtils extends org.apache.commons.io.FileUtils {

	/**
	 * 加载指定目录下的所有文件（包含子目录）
	 * 
	 * @param root
	 * @param extensions
	 * @return
	 * @throws IOException
	 */
	public static File[] listAllFiles(File root, String[] extensions) throws IOException {
		if (root == null || !root.exists()) {
			throw new FileNotFoundException();
		}
		if (root.isFile()) {
			return new File[] { root };
		}
		List<File> files = new ArrayList<>();
		for (File file : root.listFiles()) {
			if (file.isFile()) {
				if (matchExtension(extensions, file)) {
					files.add(file);
				}
			} else {
				files.addAll(Arrays.asList(listAllFiles(file, extensions)));
			}
		}
		return files.toArray(new File[files.size()]);
	}

	/**
	 * 创建新文件
	 * 
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	public static File createNewFile(String filePath) throws IOException {
		File file = new File(filePath);
		if (file.exists()) {
			file.delete();
		}
		file.createNewFile();
		return file;
	}

	private static boolean matchExtension(String[] extensions, File file) {
		String name = file.getName();
		String fileExt = name.substring(name.lastIndexOf(".") + 1);
		for (String extension : extensions) {
			if (extension.equalsIgnoreCase(fileExt)) {
				return true;
			}
		}
		return false;
	}

}
