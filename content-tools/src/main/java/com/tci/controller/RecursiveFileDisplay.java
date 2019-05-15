package com.tci.controller;

import java.io.File;
import java.io.IOException;

public class RecursiveFileDisplay {
	public static void main(String[] args) {
		File currentDir = new File("C:\\tci\\tiff"); // current directory
		displayDirectoryContents(currentDir);
	}

	public static void displayDirectoryContents(File dir) {
		try {
			File[] files = dir.listFiles();
			for (File file : files) {
				if (file.isDirectory()) {
					System.out.println("directory:" + file.getCanonicalPath());
					displayDirectoryContents(file);
				} else {
					System.out.println("     file:" + file.getCanonicalPath());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static boolean diretorioFinal(File dir) {
		return dir.isDirectory() &&  dir.listFiles()[0].isFile();
	}

}
