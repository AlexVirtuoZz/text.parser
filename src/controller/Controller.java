package controller;

import entities.CounterWordDecorator;
import view.View;

import java.io.*;
import java.util.Map;

/**
 * A class to process entire program
 */
public class Controller {
    /**
     * parser - object to parse text
     * view - object to print messages
     */
    private Parser parser;
    private View view;
    private final int WORDS_LIMIT = 100;

    /**
     * Controller constructor
     * @param parser its parser object
     * @param view its view object
     */
    public Controller(Parser parser, View view) {
        this.parser = parser;
        this.view = view;
    }

    /**
     * Main method to process entire program
     * read properties to get text file
     * print welcome message
     * parse text from file
     * print entire text
     * parse words from text
     * obtain  word : text info
     * print words in frequency descending order
     * @see #countWord(CounterWordDecorator)
     * @exception IOException - if occur - display appropriate message and exit program
     */
    @SuppressWarnings("unchecked")
	public void process()  {
        try {
            parser.readProperties();
            view.print(View.ENTIRE_TEXT);
            parser.readText();
            view.print(parser.getText());
            
        } catch (IOException e){
            view.print(View.IOEXCEPTION);
            System.exit(0);
        }
        view.print(View.PARSING_WORDS);
        parser.parseWords();      
        parser.setDecoratedWords(parser.sortDecoratedWords(parser.getDecoratedWords()));
        view.print(View.SORTED_WORDS);
        countWords();
        
    }

    //Utility methods

    /**
     * A method to display word : text info
     * @param word specified word
     */
    private void countWords(){
    	int limit = 0;
        for (Map.Entry<String, CounterWordDecorator> entry : parser.getDecoratedWords().entrySet()){
    	   view.printWithEntireCount(entry.getKey(), entry.getValue().getEntireCounter());
    	   limit ++;
    	   if (limit >= WORDS_LIMIT) return;
        }
    }
}
