package org.mytools.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author Cheney.Cheng
 * @since 2017年10月24日
 *
 */
public class FileUtils extends org.apache.commons.io.FileUtils {

	/**
	 * 加载所有
	 * 
	 * @param root
	 * @return
	 * @throws IOException
	 */
	public static File[] listFiles(File root) throws IOException {
		if (root == null || !root.exists()) {
			throw new FileNotFoundException();
		}
		if (root.isFile()) {
			return new File[] { root };
		}
		File[] files = null;
		for (File file : root.listFiles()) {
			if (files == null) {
				files = listFiles(file);
			} else {
				files = ArrayUtils.addAll(files, listFiles(file));
			}
		}
		return files;
	}

}
