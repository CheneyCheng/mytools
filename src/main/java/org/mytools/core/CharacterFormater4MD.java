/**
 * 
 */
package org.mytools.core;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Cheney.Cheng
 * @since 2017年10月19日
 *
 */
public class CharacterFormater4MD {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String dirPath = "C:\\Work\\TT\\Books\\笔记\\开源框架\\Spring";
		File dir = new File(dirPath);
		if (!dir.exists()) {
			return;
		}
		File[] files = FileUtils.
	}

	private File[] listAllFiles(File dir) {
		if (dir == null || !dir.exists()) {
			return null;
		}
		if (dir.isFile()) {
			return new File[] { dir };
		}
		File[] files = null;
		for (File file : dir.listFiles()) {
			if (files == null) {
				
			}
		}
	}

}
