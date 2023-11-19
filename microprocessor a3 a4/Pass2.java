
public class Pass2 extends MacroProcessor {
	
	private String newFileContent = "";
	private String finalFileContent = "";
	private int mdtp;
	
	public void start() {
		
		updateFileContent();
		String[] lineContent = newFileContent.split("\\r?\\n");
		
		for(String currentLine : lineContent) {
			
			String[] line = currentLine.split("\\s+");
			
			if(macroNameFoundAt(line)) {
				
				//System.out.println("macro name found " + line[0]);
			
				mdtp = getIndexFromMnt(line[0]);
				mdtp++;
				
				prepareALA(line);
				
				String mdtLine = getLineFromMdt(mdtp);
				while(!mdtLine.equals("MEND")) {
					
					String[] mdtspace = mdtLine.split("\\s+");
					
					finalFileContent = finalFileContent + mdtspace[0] + " ";
					for(int i=1; i<mdtspace.length; i++) {
						//System.out.println(mdtspace[i]);
						mdtspace[i] = getLineFromAla(Integer.parseInt(mdtspace[i]));
						finalFileContent = finalFileContent + mdtspace[i] + " ";
					}
					//mdtLine = mdtspace.toString();
					finalFileContent = finalFileContent + "\n";
					mdtLine = getLineFromMdt(++mdtp);
					
				}
				
				initAla();
				continue;
				
			}
			
			finalFileContent = finalFileContent + currentLine + "\n";
			
		}
		
		System.out.println("\n\nFinal content of file after processing  : \n\n"+finalFileContent);
		
	}

	private boolean macroNameFoundAt(String[] line) {
		
		String macroName = "";
		macroName = line[0];
		
		if(mntContains(macroName))
			return true;
		
		return false;
	}

	private void updateFileContent() {
		
		String[] temp = getFileContent().split("\\r?\\n");
		int i = 0;
		while(!temp[i].contains("START")) 
			i++;
		
		for(;i<temp.length;i++) {
			
			newFileContent = newFileContent + temp[i] + "\n";
			
		}
		
	}

	
}
