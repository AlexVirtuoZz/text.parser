package entities;

/**
 * A class to improve standard Word class by opportunity to be counted by occurrences
 */
public class CounterWordDecorator extends Word implements Comparable {
    /**
     * entireCounter - number of occurrences in text
     */
    private int entireCounter;

    /**
     * Constructor with value
     * @param value word's text value
     */
    public CounterWordDecorator(String value) {
        super(value);
    }

    //Getters and setters

    public int getEntireCounter() {
        return entireCounter;
    }   

    //Utility methods

    /**
     * A method to increase word's counters
     */
    public void increase(){
        entireCounter++;
    }
    
    //Overridden method
    /**
     * A method to compare words
     * Used to sort the words by number of occurrences
     * if number of occurrences is same - sort lexicographically
     * @param o is specified word to compare
     * @return counter difference
     */
    @Override
    public int compareTo(Object o) {
        CounterWordDecorator temp = (CounterWordDecorator) o;
        if (temp.getEntireCounter() == this.getEntireCounter())
        	return this.toString().compareTo(temp.toString());
        return temp.getEntireCounter() - this.getEntireCounter();
    }
    
    @Override
    public boolean equals(Object o) {    	
    	if (super.equals(o)){
    		CounterWordDecorator tmp = (CounterWordDecorator) o;
    		return tmp.getEntireCounter() == this.entireCounter;
    	}    	
    	return false;
    }
    
}
