import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class MacroProcessor {
	
	private static HashMap<String,Integer> mnt = new HashMap<String,Integer>(); 
	private static ArrayList<String> mdt = new ArrayList<String>(),ala = new ArrayList<String>();
	private static String FileContent;
	private static Pass1 pass1 = new Pass1();
	private static Pass2 pass2 = new Pass2();

	public static void main(String[] args) throws IOException {
		
		System.out.println("******** Two Pass Macro-Processor **********\n\n");
		/*System.out.println("Enter Name of File containing assembler program\n");
		String FileName = scan.nextLine();*/
		FileContent = getContentFrom("C:\\Users\\mangesh\\eclipse-workspace\\TwoPassMacroProcessor\\src\\asscode.txt");
		//System.out.println(FileContent +"Contents of MNT are : \n\n");
		pass1.start();
		
		System.out.println("Contents of MNT are : \n\n");
		System.out.println(mnt);
		System.out.println("\n\nContents of MDT are : \n\n");
		System.out.println(mdt);
		System.out.println("\n\nContents of ALA are : \n");
		System.out.println(ala);
		
		pass2.start();

	}

	private static String getContentFrom(String fileName) throws IOException {
		
		String content;
		File file = new File(fileName);
		FileInputStream fis = new FileInputStream(file);
		DataInputStream dis = new DataInputStream(fis);

		int f_count = fis.available();

		byte[] b = new byte[f_count];
		dis.readFully(b);

		content = new String(b);
		
		dis.close();
		return content;
	}

	public static String getFileContent() {
		return MacroProcessor.FileContent;
	}
	
	public static Integer getIndexFromMnt(String name) {
		
		if(mnt.containsKey(name))
			return mnt.get(name);
		return -1;
	}

	public static Boolean mntContains(String name) {
		
		if(mnt.containsKey(name))
			return true;
		
		return false;
		
	}
	
	public static void updateMnt(String name, int mdtp) {
		mnt.put(name, mdtp);
	}
	
	public static Boolean inMnt(String name) {
		
			if(mnt.get(name) == null) {
				return false;
			}
		
		return true;
	}

	public static String getLineFromMdt(int index) {
		return mdt.get(index);
	}

	public static void updateMdt(String line) {
		mdt.add(line);
	}

	public static String getLineFromAla(int index) {
		return ala.get(index);
	}

	public static void updateAla(String arg) {
		ala.add(arg);
	}
	
	public static String getArgumentAtIndex(int index) {
		return ala.get(index);
	}
	
	public static int getAlaSize() {
		return ala.size();
	}
	
	public static void initAla() {
		ala = new ArrayList<String>();
	}

	public void prepareALA(String[] lineContent2) {
		
		if(lineContent2[1].matches(".+,.+")) {
			String[] commaSepr = lineContent2[1].split(",");
			for(int i=0; i<commaSepr.length; i++) {
				updateAla(commaSepr[i]);
			}
			return;
		}
		for(int i=1; i<lineContent2.length; i++) {
			updateAla(lineContent2[i]);
		}
		
	}

}
