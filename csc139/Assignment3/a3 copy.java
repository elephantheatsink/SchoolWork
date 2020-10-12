//Beomjin Han
//CSC 139 Assignment 3
//Prof. Shobaki

import java.util.*;

import java.util.Scanner;
import java.io.*;
import java.nio.file.Files;
import java.text.DecimalFormat;

public class a3 {

	public static void main(String[] args) throws Exception {
		int timeQuantum;
		int numOfProcs;
		int procs[][];
		DecimalFormat df = new DecimalFormat("#.##");
		File f = new File("input.txt");
		try {
			Scanner in = new Scanner(f);
			String schedType = in.next();
			switch(schedType) {
				case "RR": 
					timeQuantum = in.nextInt();
					//System.out.println(timeQuantum);
					numOfProcs = in.nextInt();
					//System.out.println(numOfProc);
					procs = new int[numOfProcs][4];
					for(int i = 0; i<numOfProcs;i++) {
						for(int j = 0; j<4 ;j++) {
							procs[i][j] = in.nextInt();
						}
					}
					roundRobin(procs, numOfProcs, timeQuantum);
					break;
				case "SJF":
	
					numOfProcs = in.nextInt();
					
					procs = new int[numOfProcs][4];
					for(int i = 0; i<numOfProcs;i++) {
						for(int j = 0; j<4 ;j++) {
							procs[i][j] = in.nextInt();
						}
					}
					shortestJobFirst(procs, numOfProcs);
					break;
					
				case "PR_noPREMP":
					
					numOfProcs = in.nextInt();
					procs = new int[numOfProcs][4];
					for(int i = 0; i<numOfProcs;i++) {
						for(int j = 0; j<4 ;j++) {
							procs[i][j] = in.nextInt();
						}
					}
					priority_noPREMP(procs, numOfProcs);
					break;
					
				case "PR_withPREMP":
					numOfProcs = in.nextInt();
					procs = new int[numOfProcs][4];
					for(int i = 0; i<numOfProcs;i++) {
						for(int j = 0; j<4 ;j++) {
							procs[i][j] = in.nextInt();
						}
					}
					priority_withPREMP(procs, numOfProcs);
					break;
					
				default:
				
			}
		} finally {}
		
		
		
	}
	public static void roundRobin(int procs[][], int numOfProcs, int timeQuantum) {
		DecimalFormat df = new DecimalFormat("#.##");
		List<Integer> fifoQ = new ArrayList<>();
		List<Integer> rrQ = new ArrayList<>();
		

		int time = 0;
		int timeMark1 = 0;

		int[] waits = new int[numOfProcs];
		double waitSum = 0;
		int procFinished = 0;
		
		int hasCpu = 0;
		int hadCpu = 0;
		boolean cpuBusy = false;

		try {
			  File fout = new File("myOutFile.txt");
			  FileOutputStream fos = new FileOutputStream(fout);
			  BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
			  /**
			  for(int i = 0; i < procs.length; i++) {
					for (int j = 0; j<procs[0].length;j++) {
						bw.write(procs[i][j]+" ");
					}
					bw.newLine();
			  }
			  */
	  
			  bw.write("RR "+timeQuantum);
			  
			  bw.newLine();
			  
			  while(procFinished<numOfProcs) {
				  for(int i = 0; i<numOfProcs ;i++ ) { //checking arrival time of each process to add process to fifoQ
					  if(time == procs[i][1]) {
						  fifoQ.add(procs[i][0]);
						  
					  }
				  }
				  
				  if(cpuBusy==false&&fifoQ.size()>0) {
					  //give CPU to proc with lowest priority, and not the same one as before
					  int currentPriority = 10;
					  for (int i = 0; i<fifoQ.size() ; i++) {
						  if(procs[fifoQ.get(i)-1][3]<currentPriority&&!rrQ.contains(fifoQ.get(i))) {
							  
							  currentPriority = procs[fifoQ.get(i)-1][3];
							  hasCpu = fifoQ.get(i);
							  
							  cpuBusy=true;
						  }
				  
					  }
					  
					  if(hasCpu==hadCpu&&fifoQ.size()>1) { 
						  if(rrQ.contains(hasCpu))hasCpu = rrQ.get(0);
						  hasCpu = fifoQ.get(fifoQ.indexOf(hasCpu)+1);
						  //if(hasCpu>numOfProcs) {
							  //hasCpu = fifoQ.get(0);
						  //}
						  
					  }
					  
					  bw.write(time+"    "+hasCpu);
					  bw.newLine();
					  timeMark1 = time;
					  
				  }
				  
				  time++;
				  procs[hasCpu-1][2]--;
				  if(!rrQ.contains(hasCpu)) {
					  rrQ.add(hasCpu);
					  if(rrQ.size()==fifoQ.size()) {
						  rrQ.remove(0);
					  }
				  }
				  
				  
				  
				  for(int i = 0; i<fifoQ.size(); i++) { //increase wait time for procs in the fifoQ
					  if(fifoQ.get(i)!=hasCpu) {
						  waits[fifoQ.get(i)-1]++;
					  }
				  }
				  
				  if(procs[hasCpu-1][2]==0) {
					  procFinished++;
					  fifoQ.remove(fifoQ.indexOf(hasCpu));
					  if(rrQ.contains(hasCpu)) {
						  rrQ.remove(rrQ.indexOf(hasCpu));
					  }
					  
					  cpuBusy = false;
				  }else if(time-timeMark1==timeQuantum) {
					  cpuBusy=false;
					  
					  
				  }
				  hadCpu=hasCpu;
				  
			  }
			  for(int i = 0; i<waits.length; i++) {
				  waitSum=waitSum+waits[i];
			  }
			  waitSum=waitSum/numOfProcs;
			  bw.write("AVG Waiting Time: "+ df.format(waitSum));
			  
			  bw.close();
		
		 	} catch (FileNotFoundException e){
			  // File was not found
			  //e.printStackTrace();
			} catch (IOException e) {
			  // Problem when writing to the file
			  //e.printStackTrace();
			}
			
		
		
	}
	public static void shortestJobFirst(int procs[][], int numOfProcs) {
		DecimalFormat df = new DecimalFormat("#.##");
		int time = 0;
		List<Integer> priorityQ = new ArrayList<>();
		List<Integer> doneProcs = new ArrayList<>();
		int procFinished = 0;
		int hasCpu = 0;
		int[] waits = new int[numOfProcs];
		double waitSum = 0;
		
		
		try {
			File fout = new File("myOutFile.txt");
			  FileOutputStream fos = new FileOutputStream(fout);
			  BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
			  bw.write("SJF");
			  bw.newLine();
			  
			while(procFinished<numOfProcs) {
				  for(int i = 0; i<numOfProcs ;i++ ) { //checking arrival time of each process to add process to priorityQ
					  if(time >= procs[i][1]&&!priorityQ.contains(procs[i][0])&&!doneProcs.contains(procs[i][0])) {
						  priorityQ.add(procs[i][0]);
						  
						  
					  }
				  }
				  for(int i = 0; i<waits.length; i++) {
					  if(!doneProcs.contains(i+1)&&priorityQ.contains(i+1)) {
						  waits[i]=time-procs[i][1];
					  }
				  }
					  
				  int shortestBurst = 10000;
				  for(int i = 0; i<priorityQ.size();i++) { //give CPU to shortest priority
					  if(procs[priorityQ.get(i)-1][2]<shortestBurst) {
						  shortestBurst = procs[priorityQ.get(i)-1][2];
						  hasCpu = priorityQ.get(i);
					  }
				  }
				  bw.write(time+"   "+hasCpu);
				  bw.newLine();
				  time = time+shortestBurst;
				  
				  
					  procFinished++;
					  priorityQ.remove(priorityQ.indexOf(hasCpu));
					  doneProcs.add(hasCpu);
					  
					  
					  
					  
					  
					  
				  
				  
			  /**
		      for (int i = 0; i < numOfProcs-1; i++) { //selection sort
		          int min_idx = i; 
		          for (int j = i+1; j < numOfProcs; j++) 
		              if (procs[j][2] < procs[min_idx][2]) 
		                  min_idx = j; 
		  
		          int temp[] = new int[4];
		          temp=procs[min_idx]; 
		          procs[min_idx] = procs[i]; 
		          procs[i] = temp; 
		      }
			*/
			
			}
			for(int i = 0; i<waits.length; i++) {
				  waitSum=waitSum+waits[i];
			  }
			waitSum=waitSum/numOfProcs;
			  bw.write("AVG Waiting Time: "+ df.format(waitSum));
			bw.close();
			
		} catch (FileNotFoundException e){
			  // File was not found
			  //e.printStackTrace();
		} catch (IOException e) {
			  // Problem when writing to the file
			  //e.printStackTrace();
		}
	}
		
	public static void priority_noPREMP(int procs[][], int numOfProcs) {
		DecimalFormat df = new DecimalFormat("#.##");
		int time = 0;
		List<Integer> priorityQ = new ArrayList<>();
		List<Integer> doneProcs = new ArrayList<>();
		int procFinished = 0;
		int hasCpu = 0;
		int[] waits = new int[numOfProcs];
		double waitSum = 0;
		
		
		try {
			File fout = new File("myOutFile.txt");
			  FileOutputStream fos = new FileOutputStream(fout);
			  BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
			  bw.write("PR_noPREMP");
			  bw.newLine();		
			  
			  while(procFinished<numOfProcs) {  
				  for(int i = 0; i<numOfProcs ;i++ ) { //checking arrival time of each process to add process to priorityQ
					  if(time>= procs[i][1]&&!priorityQ.contains(procs[i][0])&&!doneProcs.contains(procs[i][0])) {
						  priorityQ.add(procs[i][0]);
					  }
				  }
				  for(int i = 0; i<waits.length; i++) {
					  if(!doneProcs.contains(i+1)&&priorityQ.contains(i+1)) {
						  waits[i]=time-procs[i][1];
					  }
				  }
				
				  if(!priorityQ.isEmpty()) {
					  int pri = 1000;
					  for(int i = 0; i<priorityQ.size();i++) { //give CPU to shortest priority
						  if(procs[priorityQ.get(i)-1][3]<pri) {
							  pri = procs[priorityQ.get(i)-1][3];
							  hasCpu = priorityQ.get(i);
						  }
					  }
					  bw.write(time+"   "+hasCpu);
					  bw.newLine();
					  time = time+procs[hasCpu-1][2];
					  procFinished++;
					  int ind = priorityQ.indexOf(hasCpu);
					  priorityQ.remove(ind);
					  doneProcs.add(hasCpu);
				  }else {
					  time++;
				  }
			  
			  
			  }
				for(int i = 0; i<waits.length; i++) {
				   waitSum=waitSum+waits[i];
				}
				waitSum=waitSum/numOfProcs;
				bw.write("AVG Waiting Time: "+ df.format(waitSum));
				bw.close();
				
				
			  
		} catch (FileNotFoundException e){
			// File was not found
				  //e.printStackTrace();
		} catch (IOException e) {
			// Problem when writing to the file
			//e.printStackTrace();
		}
				  
		
	}
	public static void priority_withPREMP(int procs[][], int numOfProcs) {
		DecimalFormat df = new DecimalFormat("#.##");
		int time = 0;
		int timeMark = 0;
		List<Integer> priorityQ = new ArrayList<>();
		List<Integer> doneQ = new ArrayList<>();
		int procFinished = 0;
		
		int[] waits = new int[numOfProcs];
		for(int i = 0; i<numOfProcs; i++) {
			waits[i] = 0;
		}
		double waitSum = 0;
		int hasCpu = 0;
		int hadCpu = 0;
		boolean cpuBusy = false;
		int currentPriority = 99999;
		
		
		try {
			File fout = new File("myOutFile.txt");
			  FileOutputStream fos = new FileOutputStream(fout);
			  BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
			  bw.write("PR_withPREMP");
			  bw.newLine();		
			  
			  while(procFinished<numOfProcs) {  
			  
				  for(int i = 0; i<numOfProcs ;i++ ) { //checking arrival time of each process to add process to the queue. arrival
					  if(time == procs[i][1]) {
						  priorityQ.add(procs[i][0]);
						  
					  }
				  }
				  int tempCurrPri = 99999; //check to see which has lowest priority after procs arrive
				  for(int i = 0; i<priorityQ.size(); i++) {
					  if(tempCurrPri>procs[priorityQ.get(i)-1][3]) {
						  tempCurrPri = procs[priorityQ.get(i)-1][3];
					  }
				  }
				  if(currentPriority>tempCurrPri) {             // if there is new lowest priority, update and choose first proc fron 
					  currentPriority = tempCurrPri;            // priorityQ that has this lowest priority and give to cpu
					  for(int i = 0; i<priorityQ.size(); i++) {
						  if(procs[priorityQ.get(i)-1][3]==currentPriority) {
							  hasCpu = priorityQ.get(i);
							  cpuBusy = true;
							  bw.write(time+"   "+hasCpu);
							  bw.newLine();
							  break;
						  }
					  }
					  
				  }
				  
				  procs[hasCpu-1][2]--;
				  time++;
				  for(int i = 0; i<priorityQ.size(); i++) {
					  if(procs[priorityQ.get(i)-1][2]==0) {   //there is no time remaining for the process, so it is done.
						  procFinished++;
						  doneQ.add(priorityQ.get(i));
						  priorityQ.remove(i);
						  currentPriority = 99999;
						  cpuBusy = false;
						  break;
					  }else {
						  
					  }
				  }
				  for(int j = 0; j<priorityQ.size(); j++) {  //goes through every proc in priorityQ
					  if(priorityQ.get(j)!=hasCpu) {         //if it doesn't have CPU, wait gets incremented
						  waits[priorityQ.get(j)-1]++;
					  }
				  }
				  
			  }
				for(int i = 0; i<waits.length; i++) {
				   waitSum=waitSum+waits[i];
				}
				waitSum=waitSum/numOfProcs;
				bw.write("AVG Waiting Time: "+ df.format(waitSum));
				bw.close();
				
				
			  
		} catch (FileNotFoundException e){
			// File was not found
				  //e.printStackTrace();
		} catch (IOException e) {
			// Problem when writing to the file
			//e.printStackTrace();
		}
		
	}
	
}
