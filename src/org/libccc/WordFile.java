package org.libccc;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.libmots.Text;

public class WordFile {
	private Text text;
	private XWPFDocument doc;
	private XWPFWordExtractor extractor;
	
	private File file;

	public WordFile(File file) throws IOException {
		text = new Text("", "");
		this.file = file;
		
		extractText();
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public Text getText() {
		return text;
	}

	public void setText(Text text) {
		this.text = text;
	}
	
	private void extractText() throws IOException {
		doc = new XWPFDocument(new FileInputStream(file));
		extractor = new XWPFWordExtractor(doc);
		
		text.setText(new StringBuilder(extractor.getText()));
		text.setSource(file.getName());
		
		extractor.close();
		doc.close();
	}
}
