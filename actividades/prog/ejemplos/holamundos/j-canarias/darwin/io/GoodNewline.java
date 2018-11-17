package io;

import java.io.*;

/**
 * Multiple lines output from one method.
 * @author Ian F. Darwin, http://www.darwinsys.com/
 * @version $Id: GoodNewline.java,v 1.5 2004/09/08 20:12:57 ian Exp $
 */
public class GoodNewline {
	//+
	String myName;
	public static void main(String[] argv) {
		GoodNewline jack = new GoodNewline("Jack Adolphus Schmidt, III");
		jack.print(System.out);
	}

	protected void print(PrintStream out) {
		out.println(toString());	// classname and hashcode
		out.println(myName);		// print name  on next line
	}

	//-
	/* Constructor */
	public GoodNewline(String s) {
		myName = s;
	}
}
