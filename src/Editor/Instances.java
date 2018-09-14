package Editor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;

import Structure.Constant;
import Structure.Element;
import Structure.ElementCompound;
import Structure.Paragraph;
import Structure.Section;
import Structure.Sentence;
import Structure.Word;

public class Instances {

	private HashMap<String, ArrayList<String>> dictionary;
	private Reader reader;
	
	public Instances() {
		dictionary = new HashMap<>();
	}
	
	public void setReader (Reader r){
		this.reader = r;
	}
	
	public void initDictionary() throws IOException {
		File verbos = new File("diccionario/verbos.txt");
		File adjetivos = new File("diccionario/adjetivos.txt");
		File adverbios = new File("diccionario/adverbios.txt");
		File preposiciones = new File("diccionario/preposiciones.txt");
		File pronombres = new File("diccionario/pronombres.txt");
		FileReader fileReaderVerbos = null;
		FileReader fileReaderAdjetivos = null;
		FileReader fileReaderAdverbios = null;
		FileReader fileReaderPreposiciones = null;
		FileReader fileReaderPronombres = null;
			try {
				fileReaderVerbos = new FileReader(verbos);
				fileReaderAdjetivos = new FileReader(adjetivos);
				fileReaderAdverbios = new FileReader(adverbios);
				fileReaderPreposiciones = new FileReader(preposiciones);
				fileReaderPronombres = new FileReader(pronombres);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
		BufferedReader bufferReader = new BufferedReader(fileReaderVerbos);
		String linea = "";
		ArrayList<String> aux = new ArrayList<>();
		while ((linea = bufferReader.readLine()) != null){
			aux = new ArrayList<>();
			aux.add(Constant.VERBO);
			dictionary.put(linea, aux);
		}
		bufferReader = new BufferedReader(fileReaderAdjetivos);
		while ((linea = bufferReader.readLine()) != null){
			if (dictionary.containsKey(linea))
				dictionary.get(linea).add(Constant.ADJETIVO);
			else{
				aux = new ArrayList<>();
				aux.add(Constant.ADJETIVO);
				dictionary.put(linea, aux);
			}
		}
		bufferReader = new BufferedReader(fileReaderAdverbios);
		while ((linea = bufferReader.readLine()) != null){
			if (dictionary.containsKey(linea))
				dictionary.get(linea).add(Constant.ADVERBIO);
			else{
				aux = new ArrayList<>();
				aux.add(Constant.ADVERBIO);
				dictionary.put(linea, aux);
			}
		}
		bufferReader = new BufferedReader(fileReaderPreposiciones);
		while ((linea = bufferReader.readLine()) != null){
			if (dictionary.containsKey(linea))
				dictionary.get(linea).add(Constant.PREPOSICION);
			else{
				aux = new ArrayList<>();
				aux.add(Constant.PREPOSICION);
				dictionary.put(linea, aux);
			}
		}
		bufferReader = new BufferedReader(fileReaderPronombres);
		while ((linea = bufferReader.readLine()) != null){
			if (dictionary.containsKey(linea))
				dictionary.get(linea).add(Constant.PRONOMBRE);
			else{
				aux = new ArrayList<>();
				aux.add(Constant.PRONOMBRE);
				dictionary.put(linea, aux);
			}
		}
	}
	
	public ArrayList<Element> init (int lvl){
		while (reader.hasToRead()) {
			int k = reader.getLevel();
			if ((k-lvl) > 1){
				JOptionPane.showMessageDialog(null,"Error de formato");
				
				return null;
			}
			if (k > lvl) {
				ArrayList<Element> element = new ArrayList<>();
				reader.incrementPosition(k);
				Section s = new Section (reader.readTitle(), k);
				s.setContent(createInstances(reader.readContent()));
				ArrayList<Element> childList = init (k);
				if (!(childList.isEmpty()))
					for (Element e : childList) {
						if (e != null)
							s.addContent(e);
					}
				element.add(s);
				k = reader.getLevel();
				while ((reader.hasToRead()) && (k == lvl+1)){
					reader.incrementPosition(k);
					Section bro = new Section (reader.readTitle(), k);
					bro.setContent(createInstances(reader.readContent()));
					ArrayList<Element> broChildList = init (k);
					if (!(broChildList.isEmpty()))
						for (Element e : broChildList) {
							if (e != null)
								bro.addContent(e);
						}
					element.add(bro);
					k = reader.getLevel();
				}
				return element;	
			}
			else
				return new ArrayList<>();
		}
		return new ArrayList<>();
	}
	
	public ArrayList<Element> createInstances(String text){
		ElementCompound oracion = new Sentence();
		ElementCompound parrafo = new Paragraph();
		ArrayList<Element> content = new ArrayList<>();
		int inicio = 0;
		for (int i = inicio; i < text.length()-1; i++) {
			if (i >= inicio){
				if(text.substring(i, i+1).equals(" ")){
					Element nuevo = new Word(text.substring(inicio,i), getType(text.substring(inicio,i)));
					oracion.addContent(nuevo);
					inicio = i+1;
				}
				else{
					if((text.substring(i, i+2).equals("."+" ")) || (text.substring(i, i+2).equals("?"+" ")) || (text.substring(i, i+2).equals("!"+" "))){
						Element nuevo = new Word(text.substring(inicio,i+1), getType(text.substring(inicio,i+1)));
						oracion.addContent(nuevo);
						parrafo.addContent(oracion);
						inicio = i+2;
						oracion = new Sentence();
					}
					else
						if((text.substring(i, i+2).equals("."+"\n")) || (text.substring(i, i+2).equals("."+"\r")) || (text.substring(i, i+2).equals("?"+"\n")) || (text.substring(i, i+2).equals("!"+"\n"))){
							Element nuevo = new Word(text.substring(inicio,i+1), getType(text.substring(inicio,i+1)));
							oracion.addContent(nuevo);
							parrafo.addContent(oracion);
							inicio = i+2;
							oracion = new Sentence();
							content.add(parrafo);
							parrafo = new Paragraph();
							while ((inicio < text.length()) && ((text.substring(inicio, inicio+1)).equals("\n") || (text.substring(inicio, inicio+1)).equals("\r"))){
								inicio++;
							}
						}
						else
							if (text.substring(i, i+1).equals("\t")){
								inicio = i;
								while ((inicio < text.length()) && (!text.substring(inicio, inicio+1).equals("\n")))
									inicio++;
								inicio++;
							}
				}
			}
		}
		if (inicio < text.length()){
			int fin = 0;
			while (text.substring(text.length()-1-fin, text.length()-fin).equals("\n"))
				fin++;
			Element nuevo = new Word(text.substring(inicio,(text.length()-fin)), getType(text.substring(inicio,(text.length()-fin))));
			oracion.addContent(nuevo);
			parrafo.addContent(oracion);
			content.add(parrafo);
		}
		return content;
	}
	
	private ArrayList<String> getType (String text){
		String aux = text.toLowerCase();
		if (((aux.codePointAt(0) < Constant.INICIO) || (aux.codePointAt(0) > Constant.FIN)) && (aux.codePointAt(0) != Constant.ENIE))
			aux = aux.substring(1);
		
		if (((aux.codePointAt(aux.length()-1) < Constant.INICIO) || (aux.codePointAt(aux.length()-1) > Constant.FIN)) && (aux.codePointAt(aux.length()-1) != Constant.ENIE))
			aux = aux.substring(0, aux.length()-1);
		return dictionary.get(aux);
	}
	
}
