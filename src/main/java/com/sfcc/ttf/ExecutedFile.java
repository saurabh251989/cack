package com.sfcc.ttf;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExecutedFile {

	private final String targetFiles = "TargetFiles";
	private String currentDir = System.getProperty("user.dir");

	private HashMap<String, List<String>> sourceAndPtu = new HashMap<String, List<String>>();

	// private final String ptuCodePath =
	// "unit_test\\testscripts\\opsw\\source\\ saurabh";

	/**
	 * @return the sourceAndPtu
	 */
	public HashMap<String, List<String>> getSourceAndPtu() {
		return sourceAndPtu;
	}

	/**
	 * @param sourceAndPtu the sourceAndPtu to set
	 */
	public void setSourceAndPtu(HashMap<String, List<String>> sourceAndPtu) {
		this.sourceAndPtu = sourceAndPtu;
	}

	// private final String sourceCodePath = "sourcecode\\opsw\\source\\";
	private List<String> fileCheckList1 = new ArrayList<String>();

	private List<String> fileCheckList2 = new ArrayList<String>();

	private List<String> fileCheckList3 = new ArrayList<String>();

	private List<String> fileCheckList4 = new ArrayList<String>();
	private List<String> ptuList = new ArrayList<String>();

	private List<String> xrdList = new ArrayList<String>();
	private List<String> ttfList = new ArrayList<String>();

	private List<String> tioList = new ArrayList<String>();
	private List<String> fdcList = new ArrayList<String>();

	void TTFList(String input) {

		String currentDir = System.getProperty("user.dir");

		StringBuilder sb = new StringBuilder();

		try {
			Files.walk(Paths.get(currentDir + "\\" + input)).forEach(filePath -> {

				if (Files.isRegularFile(filePath) && (filePath.getFileName().toString().endsWith(".ttf"))) {
					try {

						sb.append(readFile(filePath.toString()));

					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	void PTUList(String path) {

		if (!fileCheckList1.contains(path.toString())) {
			fileCheckList1.add(path.toString());

		}
		try {
			Files.walk(Paths.get(path)).forEach(filePath -> {

				if (Files.isRegularFile(filePath) && (filePath.getFileName().toString().endsWith(".ptu"))) {
					try {
						String temp=filePath.toString();
						temp=temp.replace(currentDir+"\\PTU-RES","unit_test\\testscripts");
						if (!ptuList.contains(temp))
						{
							
							FileInputStream fs= new FileInputStream(filePath.toString());
							BufferedReader br = new BufferedReader(new InputStreamReader(fs));
							for(int i = 0; i < 3; i++)
							  br.readLine();
							String sourcePath = br.readLine();
							
							sourcePath=sourcePath.substring(sourcePath.indexOf("<")+1, sourcePath.lastIndexOf(">"));
							
							if (!sourceAndPtu.containsKey(sourcePath)) {
							    List<String> list = new ArrayList<String>();
							    list.add(temp);

							    sourceAndPtu.put(sourcePath, list);
							} else {
								sourceAndPtu.get(sourcePath).add(temp);
							}
							
							br.close();
							fs.close();
							ptuList.add(temp);
						}
						} catch (Exception e) {
						e.printStackTrace();
					}
				} else {

					File file = new File(filePath.toString());

					if (file.isDirectory()) {

						if (!fileCheckList1.contains(filePath.toString())) {
							fileCheckList1.add(filePath.toString());

							PTUList(filePath.toString());

						}

					}

				}

			});
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	void XRDList(String path) {

		if (!fileCheckList2.contains(path.toString())) {
			fileCheckList2.add(path.toString());

		}
		try {
			Files.walk(Paths.get(path)).forEach(filePath -> {

				if (Files.isRegularFile(filePath) && (filePath.getFileName().toString().endsWith(".xrd"))) {
					try {

						String temp=filePath.toString();
						temp=temp.replace(currentDir+"\\PTU-RES","unit_test\\testscripts");
						if (!xrdList.contains(temp))
						{
							xrdList.add(temp);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {

					File file = new File(filePath.toString());

					if (file.isDirectory()) {

						if (!fileCheckList2.contains(filePath.toString())) {
							fileCheckList2.add(filePath.toString());
							XRDList(filePath.toString());
						}
					}

				}

			});
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	void FDCList(String path) {

		if (!fileCheckList3.contains(path.toString())) {
			fileCheckList3.add(path.toString());

		}
		try {
			Files.walk(Paths.get(path)).forEach(filePath -> {

				if (Files.isRegularFile(filePath) && (filePath.getFileName().toString().endsWith(".fdc"))) {
					try {

						String temp=filePath.toString();
						temp=temp.replace(currentDir+"\\PTU-RES","unit_test\\testscripts");
						if (!fdcList.contains(temp))
						{
							
							fdcList.add(temp);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {

					File file = new File(filePath.toString());

					if (file.isDirectory()) {

						if (!fileCheckList3.contains(filePath.toString())) {
							fileCheckList3.add(filePath.toString());
							FDCList(filePath.toString());
						}
					}

				}

			});
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	void TIOList(String path) {

		if (!fileCheckList4.contains(path.toString())) {
			fileCheckList4.add(path.toString());

		}

		try {
			Files.walk(Paths.get(path)).forEach(filePath -> {

				if (Files.isRegularFile(filePath) && (filePath.getFileName().toString().endsWith(".tio"))) {
					try {

						String temp=filePath.toString();
						temp=temp.replace(currentDir+"\\PTU-RES","unit_test\\testscripts");
						if (!tioList.contains(temp))
						{
							
							tioList.add(temp);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {

					File file = new File(filePath.toString());

					if (file.isDirectory()) {

						if (!fileCheckList4.contains(filePath.toString())) {
							fileCheckList4.add(filePath.toString());
							TIOList(filePath.toString());
						}
					}

				}

			});
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	String readFile(String fileName) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		try {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
					ttfList.add(line);
		
				sb.append(line);
				sb.append("\n");
				line = br.readLine();
			}
			return sb.toString();
		} finally {
			br.close();
		}
	}

	public List<String> getTtfList() {
		return ttfList;
	}

	public List<String> getPtuList() {
		return ptuList;
	}

	public List<String> getXrdList() {
		return xrdList;
	}

	public List<String> getTioList() {
		return tioList;
	}

	public List<String> getFdcList() {
		return fdcList;
	}
}
