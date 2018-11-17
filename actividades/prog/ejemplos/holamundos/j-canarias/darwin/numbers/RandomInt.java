package numbers;

import java.util.*;

/** Generate random ints by asking Random() for
 * a series of random integers from 1 to 10, inclusive.
 *
 * @author Ian Darwin, http://www.darwinsys.com/
 * @version $Id: RandomInt.java,v 1.5 2004/09/08 20:12:48 ian Exp $
 */
public class RandomInt {
	public static void main(String[] a) {
		Random r = new Random();
		for (int i=0; i<1000; i++)
			// nextInt(10) goes from 0-9; add 1 for 1-10;
			System.out.println(1+Math.round(r.nextInt(10)));
	}
}
