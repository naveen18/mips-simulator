package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import cache.InstructionCache;
import common.AppConfig;
import common.CodeLoader;
import pipeline.Pipeline;

public class Utilities {
	public static int getMask(int num) {
		int count = 0;
		int myNum = 0;
		while (myNum < num) {
			myNum = (int) Math.pow(2, count);
			count++;
		}
		return myNum - 1;
	}

	public static void storeScoreBoard(String path) throws IOException {
		BufferedWriter scoreboardWriter = null;
		final String instFormat = " %-25s  %-4s  %-4s  %-6s  %-6s  %-5s  %-5s  %-4s %-6s";
		final String instFormat1 = " %-25s  %-6s  %-6s  %-6s  %-5s  %-5s  %-5s  %-5s %-5s";
		String headers = String.format(instFormat, "Instruction", "Fetch", "Issue", "Read", "Exec", "Write", "RAW",
				"WAW", "Struct");
		scoreboardWriter = new BufferedWriter(
				new FileWriter(new File("/Users/naveen/Desktop/Architecture/result.txt")));
		scoreboardWriter.write(headers);
		scoreboardWriter.newLine();
		scoreboardWriter.newLine();
		System.out.println();
		for (int i = 0; i < Pipeline.scoreboard.size(); i++) {
			String inst = CodeLoader.programStore.get(Pipeline.scobdIdtoInstId.get(i));
			String fetch = getStringVal(Pipeline.scoreboard.get(i).get(0));
			String issue = getStringVal(Pipeline.scoreboard.get(i).get(1));
			String read = getStringVal(Pipeline.scoreboard.get(i).get(2));
			String exec = getStringVal(Pipeline.scoreboard.get(i).get(3));
			String write = getStringVal(Pipeline.scoreboard.get(i).get(4));
			String raw = Pipeline.scoreboard.get(i).get(5) == 0 ? "N" : "Y";
			String waw = Pipeline.scoreboard.get(i).get(6) == 0 ? "N" : "Y";
			String struct = Pipeline.scoreboard.get(i).get(7) == 0 ? "N" : "Y";
			String line = String.format(instFormat1, inst, fetch, issue, read, exec, write, raw, waw, struct);
			scoreboardWriter.write(line);
			scoreboardWriter.newLine();
		}
		
		if(AppConfig.appConfig.isCacheOn){
			scoreboardWriter.newLine();
			InstructionCache.numIcacheRequests = Pipeline.scoreboard.size();
			String icacheReq = "Total number of access requests for instruction cache:" + InstructionCache.numIcacheRequests;
			String icacheHits = "Number of instruction cache hits:" + (InstructionCache.numIcacheRequests - InstructionCache.numIcacheMiss);
			scoreboardWriter.write(icacheReq);
			scoreboardWriter.newLine();
			scoreboardWriter.write(icacheHits);
		}
		scoreboardWriter.close();
	}

	public static String getStringVal(int n) {
		if (n == 0)
			return " ";
		return String.valueOf(n);
	}
	
	public static void printScoreBoard(String path){
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(path));
			String line = br.readLine();
			while (line != null) {				
				System.out.println(line);
				line = br.readLine();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
