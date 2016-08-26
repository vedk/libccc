package org.libccc;

import org.libmots.NamedComparisonResult;
import org.libmots.Text;
import org.libmots.TextComparator;

public class WordFileComparator {
	
	public static NamedComparisonResult compareWordFiles(WordFile w1, WordFile w2) {
		Text t1 = w1.getText(), t2 = w2.getText();
		return new NamedComparisonResult(TextComparator.compareTexts(t1, t2), t1.getSource(), t2.getSource());
	}
}
