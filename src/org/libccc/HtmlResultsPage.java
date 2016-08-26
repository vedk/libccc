package org.libccc;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.libmots.NamedComparisonResult;

public class HtmlResultsPage {
	private float threshold;
	
	private String path;
	private FileOutputStream fout;
	private ArrayList<NamedComparisonResult> results;
	
	private String header;
	
	
	public HtmlResultsPage(String path, ArrayList<NamedComparisonResult> results, float t) {
		this.path = path;
		this.results = results;
		this.threshold = t;
		
		SimpleDateFormat format = new SimpleDateFormat("dd.MM.yy HH:mm");
		String date = format.format(new Date());
		
		header = "Results " + date;
	}


	public String getPath() {
		return path;
	}


	public void setPath(String path) {
		this.path = path;
	}


	public ArrayList<NamedComparisonResult> getResults() {
		return results;
	}


	public void setResults(ArrayList<NamedComparisonResult> results) {
		this.results = results;
	}
	
	public void writeResults() throws IOException {
		
		fout = new FileOutputStream(path + "/" + header + ".html");
		String tr = "<TR>";
		
		fout.write("<!DOCTYPE html>".getBytes());
		fout.write("<html><head><meta charset=\"UTF-8\"/>".getBytes());
		fout.write(("<title>" + header + "</title></head>").getBytes());
		fout.write(("<body><h1 align=\"center\">" + header + "</h1><hr/><br/>").getBytes());
		fout.write("<center>".getBytes());
		fout.write("<table border=\"1\" width=\"50%\">".getBytes());
		fout.write("<tr><th>File A</th><th>File B</th><th>A to B</th><th>B to A</th></tr>".getBytes());
		
		for (NamedComparisonResult n : results) {
			
			tr = tr.concat("<TD>" + n.getFileA() + "</TD>");
			tr = tr.concat("<TD>" + n.getFileB() + "</TD>");
			//tr = tr.concat("<TD>" + n.compareAToB() + "</TD>");
			//tr = tr.concat("<TD>" + n.compareBToA() + "</TD>");
			
			if (n.compareAToB() >= threshold && n.compareBToA() >= threshold) {
				tr = tr.concat("<td bgcolor=\"RED\"><font color=\"WHITE\">" + n.compareAToB() + "</font></td>");
				tr = tr.concat("<td bgcolor=\"RED\"><font color=\"WHITE\">" + n.compareBToA() + "</font></td>");
			}
			else if (n.compareAToB() >= threshold && n.compareBToA() < threshold) {
				tr = tr.concat("<td bgcolor=\"RED\"><font color=\"WHITE\">" + n.compareAToB() + "</font></td>");
				tr = tr.concat("<td>" + n.compareBToA() + "</td>");
			}
			else if (n.compareBToA() >= threshold && n.compareAToB() < threshold) {
				tr = tr.concat("<td>" + n.compareAToB() + "</td>");
				tr = tr.concat("<td bgcolor=\"RED\"><font color=\"WHITE\">" + n.compareBToA() + "</font></td>");
			}
			else {
				tr = tr.concat("<td>" + n.compareAToB() + "</td>");
				tr = tr.concat("<td>" + n.compareBToA() + "</td>");
			}

			tr = tr.concat("</TR>");
			fout.write(tr.getBytes());
			tr = "";
			//fout.write(("<tr><td>" + n.getFileA() + "</td><td>" + n.getFileB() + "</td><td>" + n.compareAToB() + "</td><td>" + n.compareBToA() + "</td></tr>").getBytes());
		}
		
		fout.write("</table></center></body></html>".getBytes());
		
		fout.close();
	}
}
