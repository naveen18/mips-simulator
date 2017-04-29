package main;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Map.Entry;

import common.AppConfig;
import common.CodeLoader;
import common.Instruction;
import common.InstructionCache;
import common.Memory;
import common.constants.CommonConstants;
import functionalunits.FuntionalUnitManager;
import pipeline.FetchStage;
import pipeline.IssueStage;
import pipeline.Pipeline;
import registers.Register;

public class Main {
	public static int clockCycle = 0;
	public static void main(String args[]) throws Exception {
		AppConfig.updateConfig("/Users/naveen/Desktop/Architecture/config.txt");
		CodeLoader.loadCode("/Users/naveen/Desktop/Architecture/code.txt");
		InstructionCache.initCache();
		FuntionalUnitManager.initFuntionalUnits();
		try {
			Memory.readMemory("/Users/naveen/Desktop/Architecture/data.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(AppConfig.getAppConfig().toString());
		Pipeline.startPipeLine();
		printScoreBoard();
		storeScoreBoard();
	}
	
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
	
	public static void printHeaderstoFile(OutputStream os) throws IOException{
		os.write("Instruction".getBytes());
		printSpacetoFile(os, 13);
		os.write("Fetch".getBytes());
		printSpacetoFile(os, 2);
		os.write("Issue".getBytes());
		printSpacetoFile(os, 2);
		os.write("Read".getBytes());
		printSpacetoFile(os, 3);
		os.write("Exec".getBytes());
		printSpacetoFile(os, 3);
		os.write("Write".getBytes());
		printSpacetoFile(os, 3);
		os.write("RAW".getBytes());
		printSpacetoFile(os, 3);
		os.write("WAW".getBytes());
		printSpacetoFile(os, 3);
		os.write("Struct".getBytes());
		os.write("\n".getBytes());
		os.write("\n".getBytes());
	}
	
	public static void printSpacetoFile(OutputStream os, int n) throws IOException{
		for(int i=0; i<n; i++) os.write(" ".getBytes());
	}
	
	public static void storeScoreBoard() throws IOException{
		OutputStream os = new FileOutputStream("/Users/naveen/Desktop/Architecture/myresult.txt");
		printHeaderstoFile(os);
		for(int i=0; i<2; i++){
			String inst = CodeLoader.programStore.get(i);
			int lenCode = inst.length();
			//System.out.printf("%s", inst);
			printSpace(20-lenCode);
			for(int j=0; j<Pipeline.scoreboard.get(i).size(); j++){
				printSpacetoFile(os, 7 - String.valueOf(Pipeline.scoreboard.get(i).get(j)).length());
				if(j < 5){
					os.write(Pipeline.scoreboard.get(i).get(j).byteValue());
				} else{
					if(Pipeline.scoreboard.get(i).get(j) == 1) os.write((byte)'Y');
					else os.write((byte)'N');
				}
			}
			os.write("\n".getBytes());;
		}
	}
}
