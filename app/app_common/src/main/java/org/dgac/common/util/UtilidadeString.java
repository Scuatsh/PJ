package org.dgac.common.util;

import java.awt.List;
import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class UtilidadeString.
 */
public class UtilidadeString {

	/**
	 * Primera letra mayuscula.
	 *
	 * @param cadena the cadena
	 * @return the string
	 */
	public static String primeraLetraMayuscula(String cadena)
	{
		try
		{
			char[] caracteres = cadena.toCharArray();
			caracteres[0] = Character.toUpperCase(caracteres[0]);
			for (int i = 0; i < cadena.length()- 2; i++) 

				if (caracteres[i] == ' ' || caracteres[i] == '.' || caracteres[i] == ',')
				{
					caracteres[i + 1] = Character.toUpperCase(caracteres[i + 1]);
				}
			return new String(caracteres);
		}
		catch(Exception error)
		{
			error.printStackTrace();
			return null;
		}
	}
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {

		ArrayList<Integer> numbers = new ArrayList<Integer>();
        numbers.add(10);
        numbers.add(20);
        numbers.add(30);

        System.out.println("ArrayList contains : " + numbers);

        // Calling remove(index)
        numbers.remove(1); //removing object at index 1 i.e. 2nd Object, which is 2

        //Calling remove(object)
        numbers.remove(3);

    }

}
