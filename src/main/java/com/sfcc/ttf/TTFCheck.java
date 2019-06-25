package com.sfcc.ttf;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author saurabh.k
 *
 */
public class TTFCheck {
	public static void main(String[] args) {

		ExecutedFile executedFile = new ExecutedFile();

		executedFile.TTFList("Input");
		GroupFile gFile = new GroupFile(executedFile.getPtuList(), executedFile.getXrdList(), executedFile.getTioList(),
				executedFile.getFdcList(), executedFile.getTtfList());

		HashMap<String, List<String>> ttfRun = gFile.sortTTF(gFile.groupTTF());

		
		ExecutedFile executedFileTotal = new ExecutedFile();

		executedFileTotal.TTFList("TargetFiles");
		GroupFile gFileTotal = new GroupFile(executedFileTotal.getPtuList(), executedFileTotal.getXrdList(), executedFileTotal.getTioList(),
				executedFileTotal.getFdcList(), executedFileTotal.getTtfList());

		HashMap<String, List<String>> totalTtf = gFileTotal.sortTTF(gFileTotal.groupTTF());

		
		for (Map.Entry<String, List<String>> entry : ttfRun.entrySet()) {
			String key = entry.getKey();
			System.out.println(key);
			List<String> value = entry.getValue();

			for (Iterator<String> iterator = value.iterator(); iterator.hasNext();) {
				String string = (String) iterator.next();
				System.out.println("\t" + string);
			}

		}
		
		System.out.println("\n\n\n *************************** \n\n\n");
		for (Map.Entry<String, List<String>> entry : totalTtf.entrySet()) {
			String key = entry.getKey();
			System.out.println(key);
			List<String> value = entry.getValue();

			for (Iterator<String> iterator = value.iterator(); iterator.hasNext();) {
				String string = (String) iterator.next();
				System.out.println("\t" + string);
			}

		}
		
		
		HashMap<String, String> comment = ValidateTTF.getCommnet(totalTtf,ttfRun);
		HTMLReport.generateHTMLReport(comment);	
		
	}
}
