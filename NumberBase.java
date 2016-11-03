
//By Adam Slifco
import java.util.Arrays;
import java.lang.IllegalArgumentException;
import java.util.ArrayList;

public class NumberBase {

	static ArrayList<String> numbers = new ArrayList<>(
			Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f", "g", "h", "i",
					"j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v"));
	// an ArrayList used to store all of the values from the input
	static ArrayList<Integer> values = new ArrayList<>();

	public static String convert(String input, int base_in, int base_out) {
		String result = "";
		int newValue = 0;
		int remainder = 0;
		int total = 0;
		isValid1(input,base_in, base_out);
		// Goes through each character in the input
		for (int i = 0; i <= input.length() - 1; i++) {
			/// assigns local char variable to the current char in the input
			String temp = input.substring(i, i + 1);
			// gets the value of the char by locating the index of it in my
			// ArrayList
			int v = numbers.indexOf(temp);
			isValid2(v,base_in, temp);
			if (base_in < v) {
				clearValue();
				throw new IllegalArgumentException();
				
			}
			// adds the values to an arrayList since there can be more than one
			// integer value

			values.add(v);

		}
		for (Integer i : values) { // assuming list is of type values<Integer>
			total = 10 * total + i;
		}
		//if (total >= 10 && total < base_out) {
			//clearValue();
			//return numbers.get(total);
		//}
		// if were converting to a lower base
		if (base_out == 10) {
			// it adds the new base value of each number because it's still
			// in the loop
			for (int i = 0; i < values.size(); i++) {
				int tempValue = values.get(i);
				int tempPower = values.size() - 1 - i;
				newValue += tempValue * (int) Math.pow(base_in, tempPower);
			}
			result = toString(newValue);
		}
		// if were converting to a higher base
		else if (base_in == 10) {
			int temp = total;
			ArrayList<Integer> tempFlip = new ArrayList<>();
			if(temp>base_out){ 
			while (temp >= base_out) {
				// adds the proper number regardless of what base it is
				if(temp/base_out < base_out){
					tempFlip.add(temp%base_out);
					tempFlip.add(temp/base_out);
					remainder = temp % base_out;
					temp = remainder;
				}else{
				tempFlip.add(temp% base_out);
				temp = temp/base_out;
				}
				
			}for(int x = 0; x < tempFlip.size(); x++) {
				result = result.concat(numbers.get(tempFlip.get(tempFlip.size() -  1 - x)));
			}
			}else{
				result = result.concat(numbers.get(total));
			}}else if(base_in !=10 && base_out!= 10){
				int temp = total;
				ArrayList<Integer> tempFlip = new ArrayList<>();
				for (int i = 0; i < values.size(); i++) {
					int tempValue = values.get(i);
					int tempPower = values.size() - 1 - i;
					newValue += tempValue * (int) Math.pow(base_in, tempPower);
				}
				
				 temp = newValue;
				if(temp>base_out){
				while (temp >= base_out) {
					// adds the proper number regardless of what base it is
					if(temp/base_out < base_out){
						tempFlip.add(temp%base_out);
						tempFlip.add(temp/base_out);
						remainder = temp % base_out;
						temp = remainder;
					}else{
					tempFlip.add(temp% base_out);
					temp = temp/base_out;
					}
					
				
			}
				for(int x = 0; x < tempFlip.size(); x++) {
					result = result.concat(numbers.get(tempFlip.get(tempFlip.size() -  1 - x)));
				}
				}else{
					
				result = result.concat(numbers.get(temp));	
			}
			
			}
			//if base in and base out is the same
		else {
			result = input;
		}
		clearValue();
		return result;

	}

	public static String toString(int value) {
		return "" + value;
	}

	private static void clearValue() {
		for (int i = values.size() - 1; i >= 0; i--) {
			values.remove(i);
		}
	}

	private static boolean isValid1(String input, int in, int out) {
		if (in < 2 || in > 32 || out < 2 || out > 32) {
			throw new IllegalArgumentException();
		} else {
			return true;
		}

	}

	// this helper method checks to see if a number is higher than it's initial
	// base and then if it's even a valid character
	private static boolean isValid2(int v, int in, String temp) {
		if (v >= in) {
			clearValue();
			throw new IllegalArgumentException();
		}
		if (!numbers.contains(temp)) {
			clearValue();
			throw new IllegalArgumentException();
		} else {
			return true;
		}

	}

}
