/**
 * 
 */
package com.sfcc.ttf;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import java.util.HashMap;

/**
 * @author Saurabh Kumar
 *
 *
 *
 */

public class HTMLReport {

	static public void generateHTMLReport(HashMap<String, String> comment) {
		try {

			String currentDir = System.getProperty("user.dir");
			File statText = new File(currentDir + "\\" + "TTF_Report_Final.html");
			FileOutputStream is = new FileOutputStream(statText, false);
			OutputStreamWriter osw = new OutputStreamWriter(is);
			Writer report = new BufferedWriter(osw);
			String cr = "green";

			for (HashMap.Entry<String, String> entry : comment.entrySet()) {

				if (!entry.getValue().equals("TTF is Pass"))

				{
					cr = "red";
					break;
				}

			}

			report.write("<!DOCTYPE html>");
			report.write("\n");

			report.write("<html>");
			report.write("\n");
			report.write("<head>");
			report.write("\n");
			report.write("<style>");
			report.write("\n");
			report.write("table, th, td {");
			report.write("\n");
			report.write("    border: 1px solid black;");
			report.write("\n");
			report.write(" border-collapse: collapse;");
			report.write("\n");
			report.write("}");
			report.write("\n");
			report.write("th, td {");
			report.write("\n");
			report.write("padding: 5px;");
			report.write("\n");
			report.write("text-align: left;");
			report.write("\n");
			report.write("}");
			report.write("\n");
			report.write("</style>");
			report.write("\n");
			report.write("</head>");
			report.write("\n");
			report.write("<body>");
			report.write("\n");

			report.write("<table style=\"width:100%\">");
			report.write("\n");
			report.write("<caption ><font color=" + cr + ">" + "TTF_REPORT" + "");
			report.write("\n");
			report.write("</caption>");
			report.write("<tr>");
			report.write("\n");
			report.write("<th>");
			report.write("\n");
			report.write("MODULE");
			report.write("\n");
			report.write("</th>");
			report.write("\n");
			report.write("<th>");
			report.write("\n");
			report.write("Comment");
			report.write("\n");
			report.write("</th>");
			report.write("\n");
			report.write("\n");

			for (HashMap.Entry<String, String> entry : comment.entrySet()) {

				report.write("<tr>");
				report.write("\n");

				report.write("<td>");
				report.write("\n");
				report.write(entry.getKey());
				report.write("</td>");
				report.write("\n");

				report.write("<td>");
				report.write("\n");
				report.write(entry.getValue());
				report.write("</td>");
				report.write("\n");

				report.write("</tr>");
				report.write("\n");
			}
			report.write("</table>");

			report.write("\n");
			report.write("</body>");
			report.write("\n");

			report.write("</html>");
			report.write("\n");
			report.close();
		} catch (IOException e) {
			System.err.println("Problem writing to the file statsTest.txt");
		}

	}

}
