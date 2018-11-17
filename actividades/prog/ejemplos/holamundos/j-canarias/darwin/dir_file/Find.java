package dir_file;

import java.io.File;

import com.darwinsys.lang.GetOpt;
import com.darwinsys.util.Debug;

/**
 * Find - find files by name, size, or other criteria. Non-GUI version.
 * @author	Ian Darwin, http://www.darwinsys.com/
 * @version $Id: Find.java,v 1.8 2004/09/11 20:30:37 ian Exp $
 */
public class Find {
	/** Main program */
	public static void main(String[] args) {
		Find finder = new Find();
		GetOpt argHandler = new GetOpt("n:s:");
		int c;
		while ((c = argHandler.getopt(args)) != GetOpt.DONE) {
			switch(c) {
			case 'n': finder.filter.setNameFilter(argHandler.optarg()); break;
			case 's': finder.filter.setSizeFilter(argHandler.optarg()); break;
			default:	
				System.out.println("Got: " + c);
				usage();
			}
		}
		if (args.length == 0 || argHandler.getOptInd()-1 == args.length) {
			finder.doName(".");
		} else {
			for (int i = argHandler.getOptInd()-1; i<args.length; i++)
				finder.doName(args[i]);
		}
	}

	protected FindFilter filter = new FindFilter();

	public static void usage() {
		System.err.println(
			"Usage: Find [-n namefilter][-s sizefilter][dir...]");
		System.exit(1);
	}

	/** doName - handle one filesystem object by name */
	private void doName(String s) {
		Debug.println("flow", "doName(" + s + ")");
		File f = new File(s);
		if (!f.exists()) {
			System.out.println(s + " does not exist");
			return;
		}
		if (f.isFile())
			doFile(f);
		else if (f.isDirectory()) {
			// System.out.println("d " + f.getPath());
			String objects[] = f.list(filter);

			for (int i=0; i<objects.length; i++)
				doName(s + File.separator + objects[i]);
		} else
			System.err.println("Unknown type: " + s);
	}

	/** doFile - process one regular file. */
	private static void doFile(File f) {
		System.out.println("f " + f.getPath());
	}
}
