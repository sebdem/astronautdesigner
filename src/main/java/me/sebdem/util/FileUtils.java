package me.sebdem.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileUtils {

	public static void main(String[] args) throws IOException{
		// FileOperation tests;
		
		BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		String s = reader.readLine();
		writer.write(s + "\n");
		
		s = reader.readLine();
		writer.write(s + "\n");

		s = reader.readLine();
		writer.write(s + "\n");
		
		s = reader.readLine();
		writer.write(s + "\n");
		
		writer.close();
		reader.close();
	}
	
}
