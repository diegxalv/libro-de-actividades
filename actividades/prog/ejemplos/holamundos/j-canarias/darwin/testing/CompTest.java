package testing;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/** 
 * CompTest -- Component Tester.
 * A generic main program, for testing a Component-based GUI class that 
 * has a no-argument constructor. This seemed easier than adding a trivial
 * main program to every GUI component that I ever wrote...
 * @author	Ian F. Darwin, http://www.darwinsys.com/
 * @version $Id: CompTest.java,v 1.10 2004/09/08 20:13:39 ian Exp $
 */
public class CompTest {

	/** "main program" method - construct and show */
	public static void main(String[] av) {
		if (av.length == 0) {
			System.err.println("Usage: CompTest ComponentSubclass");
			System.exit(1);
		}
		String name = av[0];

		// create an instance of class named in "name", save in "Component c".
		Component c = null;
		try {
			Class cf = Class.forName(name);
			Object o = cf.newInstance();
			if (!(o instanceof Component)) {
				System.err.println("ERROR: Class " + name +
					" is not a subclass of Component");
				System.exit(1);
			}
			c = (Component)o;
		} catch (Exception e) {
			System.err.println("Component under test got exception in construction or initialization");
			System.err.println(e.toString());
			System.exit(1);
		}

		// create a Frame, and "Component c" to it.
		final JFrame f = new JFrame("CompTest: " + av[0]);
		Container cp = f.getContentPane();

		cp.add(BorderLayout.CENTER, c);		// Add the component under test

		JButton quitButton;
		cp.add(BorderLayout.SOUTH, quitButton = new JButton("Exit")); 
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				f.setVisible(false);
				f.dispose();
				System.exit(0);
			}
		});
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Set things reasonably sized.
		Dimension d = c.getPreferredSize();
		if (d.width == 0 || d.height == 0) {
			// component doesn't have getPreferredSize() yet, hard code a size.
			f.setSize(300, 200);
		} else {
			f.pack();
		}
		f.setLocation(200, 200);
		f.setVisible(true);
	}
}
