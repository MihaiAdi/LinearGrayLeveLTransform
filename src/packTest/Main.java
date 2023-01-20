package packTest;

import packWork.*;
import java.io.*;
import java.nio.file.*;
import java.util.Scanner;

public class Main {
	
	private static final int width;
	private static final int height;
	
	static{
		width = 1920 ;	
		height = 1080;
	}

	private static String inputFile;
	private static String outputFile;
	

	
	public static void main(String[] args) throws IOException {
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Numele pozei trimisa ca input: (+extensia .bmp)");
		String inputFile = scanner.nextLine();
		
		System.out.println("Numele pozei dupa procesare: (+extensia .bmp)");
		String outputFile = scanner.nextLine();
	
		scanner.close();
		
		
		Buffer b = new Buffer();
		ImageProducer p1 = new ImageProducer(inputFile, width, height, b);
		PipedOutputStream pipeOut = new PipedOutputStream();
		PipedInputStream pipeIn = new PipedInputStream(pipeOut);
		ImageConsumer c1 = new ImageConsumer(width, height, b, pipeOut);
		ImageWriter resultWriter = new ImageWriter(outputFile, width, height, pipeIn);
		p1.start();
		c1.start();
		resultWriter.start();
	}
	
}

