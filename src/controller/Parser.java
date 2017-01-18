package controller;

import entities.CounterWordDecorator;
import view.Regex;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * A class to parse text
 */
public class Parser {
    /**
     * decoratedWords - map of decorated words with counter
     * textProperty - property to get entire text file
     * text - entire text to parse
     */
    private Map<String, CounterWordDecorator> decoratedWords = new HashMap<>();
    private String textProperty;
    private String text;
    
    //Getters and setters
                
    public Map<String, CounterWordDecorator> getDecoratedWords() {
		return decoratedWords;
	}
      
    
	public void setDecoratedWords(Map<String, CounterWordDecorator> decoratedWords) {
		this.decoratedWords = decoratedWords;
	}



	public String getText() {
        return text;
    }
		
    /**
     * A method to split text by required regex
     * While text matches regex - divide text into list of strings
     * @param text text to parse
     * @param regex condition to parse text
     * @return divided text into string array
     */
    public List<String> split(String text, String regex){
        List<String> temp = new ArrayList<>();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        int start = 0;
        int finish;

        while(matcher.find()){
            finish = matcher.start();
            temp.add(text.substring(start, finish).trim());
            start = matcher.end();
        }

        if(start < text.length() - 1){
            temp.add(text.substring(start, text.length()).trim());
        }
        return temp;
    }

    /**
     * A method to get text file to read from
     * @throws IOException if problems occur while reading
     */
    public void readProperties() throws IOException {
        File file = new File("file.properties");
        FileInputStream fileInputStream = new FileInputStream(file);
        Properties properties = new Properties();
        properties.load(fileInputStream);
        textProperty = properties.getProperty("input_text");
        fileInputStream.close();
    }

    /**
     * A method to read text from file
     * Create and try to initialize required readers
     * While there are lines to read - append it to stringBuffer
     * @param from file path to read from
     * @return entire text from file
     * @throws IOException if problems occur while reading from file
     */
    public String readFromFile(String from) throws IOException {
        FileReader fr = null;
        BufferedReader br = null;
        StringBuffer fileValue = new StringBuffer();
        try {
            fr = new FileReader(from);
            br = new BufferedReader(fr);
            String line;

            while ((line = br.readLine()) != null) {
                fileValue.append(line);
                fileValue.append(" ");
            }
        }finally {
            if (fr != null) {
                fr.close();
            }
            if (br != null) {
                br.close();
            }
        }

        return fileValue.toString();
    }
    
    /**
     * A method to read entire text from file
     * @throws IOException if problems occur while reading from file
     */
    public void readText() throws IOException{
        text = readFromFile(textProperty);
    }

    /**
     * A method to divide entire text into sentences
     * Split entire text into String array (temporary sentences)
     * For each string - divide it into words and add to sentences list
     */
    public void parseWords() {
    	List<String> temp = split(text, Regex.SENTENCE_DELIMETERS);
        for (String sent: temp){
            for (String word : sent.split(Regex.WORDS_DELIMETERS)){
            	if (!word.isEmpty()){
            		if (!decoratedWords.containsKey(word))
            			decoratedWords.put(word, new CounterWordDecorator(word));
            		decoratedWords.get(word).increase();
            	}
            }
        }
    }

    /**
     * A method to sort words
     */
    public <K, V extends Comparable<? super V>> Map<K, V> sortDecoratedWords(Map<K, V> map) {
        return map.entrySet()
                  .stream()
                  .sorted(Map.Entry.comparingByValue())
                  .collect(Collectors.toMap(
                    Map.Entry::getKey, 
                    Map.Entry::getValue, 
                    (e1, e2) -> e1, 
                    LinkedHashMap::new
                  ));
    }
   
}
