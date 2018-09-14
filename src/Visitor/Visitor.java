package Visitor;

import Structure.Paragraph;
import Structure.Section;
import Structure.Sentence;
import Structure.Word;

public interface Visitor {
	
	public Object visitorWord(Word w);
	public Object visitorSentence(Sentence s);
	public Object visitorParagraph(Paragraph p);
	public Object visitorSection(Section se);
}
