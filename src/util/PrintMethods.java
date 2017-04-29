package util;

import common.CodeLoader;
import pipeline.Pipeline;

public class PrintMethods {
	public static void printScoreBoard(){
		printHeaders();
		for(int i=0; i<Pipeline.scoreboard.size() && i < CodeLoader.programStore.size(); i++){
			String inst = CodeLoader.programStore.get(i);
			int lenCode = inst.length();
			System.out.printf("%s", inst);
			printSpace(20-lenCode);
			for(int j=0; j<Pipeline.scoreboard.get(i).size(); j++){
				printSpace(7 - String.valueOf(Pipeline.scoreboard.get(i).get(j)).length());
				if(j < 5){
					System.out.print(String.format("%d", Pipeline.scoreboard.get(i).get(j)));
				} else{
					if(Pipeline.scoreboard.get(i).get(j) == 1) System.out.print('Y');
					else System.out.print('N');
				}
			}
			System.out.println();
		}
	}
	
	public static void printSpace(int n){
		for(int i=0; i<n; i++) System.out.print(" ");
	}
	
	
	public static void printHeaders(){
		System.out.print("Instruction");
		printSpace(13);
		System.out.print("Fetch");
		printSpace(2);
		System.out.print("Issue");
		printSpace(2);
		System.out.print("Read");
		printSpace(3);
		System.out.print("Exec");
		printSpace(3);
		System.out.print("Write");
		printSpace(3);
		System.out.print("RAW");
		printSpace(3);
		System.out.print("WAW");
		printSpace(3);
		System.out.print("Struct");
		System.out.println();
		System.out.println();
	}
	
}
