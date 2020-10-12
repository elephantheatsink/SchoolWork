import java.io.*;
import java.util.*;


/**
 * Beomjin Han
 * CSC 139
 * Assignment 4
 */
public class a4 {
	public static void main(String[]args) throws Exception {
		int pages;
		int frames;
		int requests;
		
		try {
			File f = new File("input.txt");
			Scanner in = new Scanner(f);
			pages = in.nextInt();
			frames = in.nextInt();
			requests = in.nextInt();
			int[] reqArr = new int[requests];
			for(int i = 0; i<requests; i++) {
				reqArr[i] = in.nextInt();
			}
			
			fifo(pages, frames, requests, reqArr);
			optimal(pages, frames, requests, reqArr);
			lru(pages, frames, requests, reqArr);
			
		}finally {}
	}

	public static void fifo(int pages, int frames, int requests, int[] reqArr){
		int [] frameArr = new int[frames];
		int nextFrame = 0;
		boolean framesContain = false;
		int containFrame = 0;
		int pageFault = 0;
		
		try {
			  
			  BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"));
			  bw.write("FIFO");
			  bw.newLine();
			  for(int i = 0; i<requests; i++) {
				  for(int j = 0; j<frames; j++) { //loop checking if requested page is already in frames
					  if(frameArr[j]==reqArr[i]) {
						  framesContain = true;
						  containFrame = j;break;
					  }
				  }
				  
				  if(framesContain==true) {
					  bw.write("Page "+reqArr[i]+" already in frame "+containFrame); //page is already in frame
					  framesContain=false;
					  bw.newLine();
				  }else {
					  if(frameArr[nextFrame]==0) { //frame is empty
						  frameArr[nextFrame] = reqArr[i];
						  bw.write("Page "+reqArr[i]+" loaded into frame "+nextFrame);
						  nextFrame = (nextFrame+1)%frames;
						  pageFault++;
						  bw.newLine();
					  }else {                      //frame is not empty and has to be unloaded
						  bw.write("Page "+frameArr[nextFrame]+ " unloaded from Frame "+nextFrame+", ");
						  frameArr[nextFrame] = reqArr[i];
						  bw.write("Page "+reqArr[i]+" loaded into Frame "+nextFrame);
						  nextFrame = (nextFrame+1)%frames;
						  pageFault++;
						  bw.newLine();
						  
					  }
				  }
			  }
			  bw.write(pageFault+" page faults");
			  bw.newLine();
			  bw.newLine();
			  bw.close();
			  
		}catch (FileNotFoundException e){
			  // File was not found
			  //e.printStackTrace();
		} catch (IOException e) {
			  // Problem when writing to the file
			  //e.printStackTrace();
		}
	}
	public static void optimal(int pages, int frames, int requests, int[] reqArr){
		int [] frameArr = new int[frames];
		int nextFrame = 0;
		boolean framesContain = false;
		int containFrame = 0;
		int pageFault = 0;
		try {
			  
			  BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt", true));
			  bw.write("Optimal");
			  bw.newLine();
			  for(int i = 0; i<requests; i++) {
				  for(int j = 0; j<frames; j++) { //loop checking if requested page is already in frames
					  if(frameArr[j]==reqArr[i]) {
						  framesContain = true;
						  containFrame = j;break;
					  }
				  }
				  
				  if(framesContain==true) {
					  bw.write("Page "+reqArr[i]+" already in frame "+containFrame); //page is already in frame
					  framesContain=false;
					  bw.newLine();
				  }else {
					  if(nextFrame<frames) { //frameArr is not full yet
						  frameArr[nextFrame] = reqArr[i];
						  bw.write("Page "+reqArr[i]+" loaded into frame "+nextFrame);
						  nextFrame = nextFrame+1;
						  pageFault++;
						  bw.newLine();
					  }else {                      //frameArr is full and a frame has to be unloaded
						  int chosenPage = 0;
						  int nextUsedTime = 0;
						  //boolean containsFuture = false;
						  int frameCandidate = 0;
						  for(int j = 0; j<frames; j++) {   //going thru each frame to see if that page will ever be used again
							  boolean containsFuture = false;
							  chosenPage = frameArr[j];
							  for(int k = i+1; k<requests; k++) { //going thru requests to match with the chosen page
								  /**
								  if(reqArr[k]==chosenPage&&nextUsedTime<k) { //page matches a future request
									  nextUsedTime=k;
									  containsFuture=true;
									  frameCandidate=j;
									  break;
								  }
								  */
								  if(reqArr[k]==chosenPage) { //page matches a past request
									  if(nextUsedTime<k) {
										  nextUsedTime=k;
										  frameCandidate=j;
										  
										  
									  }
									  containsFuture=true;
									  break;
									  
								  }
							  }
							  if (containsFuture==false) {
								  frameCandidate=j;
								  break;
							  }
						  }
						  bw.write("Page "+frameArr[frameCandidate]+ " unloaded from Frame "+frameCandidate+", ");
						  frameArr[frameCandidate] = reqArr[i];
						  bw.write("Page "+reqArr[i]+" loaded into Frame "+frameCandidate);
						  
						  pageFault++;
						  bw.newLine();
						  
					  }
				  }
			  }
			  bw.write(pageFault+" page faults");
			  bw.newLine();
			  bw.newLine();
			  bw.close();
		}catch (FileNotFoundException e){
			  // File was not found
			  //e.printStackTrace();
		} catch (IOException e) {
			  // Problem when writing to the file
			  //e.printStackTrace();
		}
	}
	public static void lru(int pages, int frames, int requests, int[] reqArr){
		int [] frameArr = new int[frames];
		int nextFrame = 0;
		boolean framesContain = false;
		int containFrame = 0;
		int pageFault = 0;
		try {
			  
			  BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt", true));
			  bw.write("LRU");
			  bw.newLine();
			  for(int i = 0; i<requests; i++) {
				  for(int j = 0; j<frames; j++) { //loop checking if requested page is already in frames
					  if(frameArr[j]==reqArr[i]) {
						  framesContain = true;
						  containFrame = j;break;
					  }
				  }
				  
				  if(framesContain==true) {
					  bw.write("Page "+reqArr[i]+" already in frame "+containFrame); //page is already in frame
					  framesContain=false;
					  bw.newLine();
				  }else {
					  if(nextFrame<frames) { //frameArr is not full yet
						  frameArr[nextFrame] = reqArr[i];
						  bw.write("Page "+reqArr[i]+" loaded into frame "+nextFrame);
						  nextFrame = nextFrame+1;
						  pageFault++;
						  bw.newLine();
					  }else {                      //frameArr is full and a frame has to be unloaded
						  int chosenPage = 0;
						  int lastUsedTime = 99999;
						  
						  int frameCandidate = 0;
						  for(int j = 0; j<frames; j++) {   //going thru each frame to check the page's last used time
							  
							  chosenPage = frameArr[j];
							  for(int k = i-1; k>=0; k--) { //going thru requests BACKWARDS to match with the chosen page
								  if(reqArr[k]==chosenPage) { //page matches a past request
									  if(lastUsedTime>k) {
										  lastUsedTime=k;
										  frameCandidate=j;
										  
									  }
									  break;
									  
								  }
							  }
							  
						  }
						  bw.write("Page "+frameArr[frameCandidate]+ " unloaded from Frame "+frameCandidate+", ");
						  frameArr[frameCandidate] = reqArr[i];
						  bw.write("Page "+reqArr[i]+" loaded into Frame "+frameCandidate);
						  
						  pageFault++;
						  bw.newLine();
						  
					  }
				  }
			  }
			  bw.write(pageFault+" page faults");
			  bw.newLine();
			  
			  bw.close();
		}catch (FileNotFoundException e){
			  // File was not found
			  //e.printStackTrace();
		} catch (IOException e) {
			  // Problem when writing to the file
			  //e.printStackTrace();
		}
	}
}
