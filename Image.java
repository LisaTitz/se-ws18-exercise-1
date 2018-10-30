import java.io.*;

public class Image {

	private int width, height;
	public byte[] data;

	public Image(int width, int height) {
		this.width = width;
		this.height = height;
		data = new byte[height*width*3]; // creating an array to store pixel data
	}

	
	// set method 
	// setting a single pixel at position (x,y)
	public byte[] set(int x, int y, int val) {
		if (x >= height || y >= width*3) {   // input out of range
			System.out.println("Invalid input");
		}
		else {
			data[(x*width+y)*3] 	= (byte) (val >> 16);  //shift to the right (where red is decoded)
			data[x*width+y)*3+ 1] 	= (byte) (val >> 8);	// position of pixel decoding for green
			data[x*width+y)*3 2] 	= (byte) (val);			// position of pixel decoding for blue
		}
		return data;
	}

	// we used the following wep page for help: https://stackoverflow.com/questions/4775457/writing-an-array-to-a-file
	// writing the image data to a file
	public void write(String fileName) {  
		PrintWriter printWriter;
		try {
			printWriter = new PrintWriter(new FileOutputStream(fileName, true));
			// printing the head of the ppm file:
			printWriter.println("P3");		
			printWriter.println(width + " " + height);
			printWriter.println(255);
			for (int i = 0; i < height*width*3; i++) {  // while going to the array
				printWriter.println(data[i] + " ");  // writing each data element to the ppm file
				}
			}
			printWriter.close();
		}
		catch(IOException e) {
			System.out.print(e.getMessage());
		}
	}
}
