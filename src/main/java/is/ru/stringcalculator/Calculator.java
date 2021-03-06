package is.ru.stringcalculator;

import java.util.ArrayList;

public class Calculator {
	static String delimiter = "";
	static int len = 1;
	static boolean isAnyLength = false;
	public static int add(String text){
		if(text.equals("")){
			return 0;
		}
		
		if(text.startsWith("//") && text.charAt(3) == '\n'){
			delimiter = String.valueOf(text.charAt(2));
			text = text.substring(4, text.length());
		}
		else if(text.startsWith("//") && text.charAt(2) == '[') {
			delimiter = String.valueOf(text.charAt(3));
			isAnyLength = true;
			int i = 4;
			while(text.charAt(i) == text.charAt(3)) {
				i++;
				len++;
			}

			text = text.substring(5 + len, text.length());
		}

		String[] nums = splitNumbers(text);
		isNegative(nums);
		nums = ignoreLargeNumbers(nums);
		
		
		if(text.contains("\n") || text.contains(",") || 
			text.contains(delimiter)){
			//return sum(splitNumbers(text));
			return sum(nums);
		}
		else
			return 1;
	}

	private static int toInt(String number){
		return Integer.parseInt(number);
	}

	private static String[] splitNumbers(String numbers){

		//if (isAnyLength == false) {

			return numbers.split("[,\n"+delimiter+"]+{"+len+"}");
		//}
		//else {
			//return numbers.split("["+delimiter+"]+");
		//}
	    

	}
      
    private static int sum(String[] numbers){
 	    int total = 0;
        for(String number : numbers){
		    total += toInt(number);
		}
		return total;
    }
    
    public static void isNegative(String[] numbers) throws IllegalArgumentException {
    	boolean[] negs = new boolean[numbers.length];   	
    	int numberOfNegatives = 0;
    	
    	for (int i = 0; i < numbers.length; i++) {
    		if (toInt(numbers[i]) < 0) {
    			negs[i] = true;
    		}
    	}
    	
    	for (boolean b : negs) {
    		if (b == true)
    			numberOfNegatives++;
    	}
    	
    	String[] negatives = new String[numberOfNegatives];
    	int i = 0;
    	
    	for (String s : numbers) {
    		if (toInt(s) < 0) {
    			negatives[i++] = s;
    		}
    	}
    	
    	if (negatives.length == 0) {
    		//System.out.println("No negatives");
    	}
    	else {
    		String str = "[";
    		
    		for (int j = 0; j < negatives.length; j++) {
    			if (j == negatives.length - 1) {
    				str += negatives[j] + "]";
    			}
    			else {
    				str += negatives[j] + ", ";
    			}
    		}
    		throw new IllegalArgumentException("Negatives not allowed: " + str);
    	}
    }

    public static String[] ignoreLargeNumbers(String[] numbers) {
    	ArrayList<String> newList = new ArrayList<String>();

    	for (String s : numbers) {
    		if (toInt(s) <= 1000) {
    			newList.add(s);
    		}
    	}

    	String[] result = new String[newList.size()];
    	int i = 0;

    	for (String s : newList) {
    		result[i++] = s;
    	}

    	return result;
    }

    public static void main(String args[]) {
    	Calculator.add("3,6,8,-2");
    }

}