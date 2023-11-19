
public class Pass1 extends MacroProcessor{
	
	private String[] fileContent,lineContent;
	private String currentLine;
	private int mdtp = 0;
	
	public void start() {
		
		int i=1;
		fileContent = getFileContent().split("\\r?\\n");
		currentLine = fileContent[0];
		
		while(!currentLine.equals("END")) {
			
			lineContent = currentLine.split("\\s+");
			
			if(lineContent[0].equals("MACRO")) {
				
				currentLine = fileContent[i++];
				lineContent = currentLine.split("\\s+");
				if(inMnt(lineContent[0])) {
					System.out.println(lineContent[0] + " already exists !!!!, Skipping this MACRO");
					continue;
				}
				updateMnt(lineContent[0],mdtp);
				prepareALA(lineContent);
				updateMdt(this.currentLine);
				mdtp++;
				currentLine = fileContent[i++];
				
				while(!currentLine.equals("MEND")) {
					
					String substitutedLine = substitute_index_notation(currentLine);
					updateMdt(substitutedLine);
					mdtp++;
					currentLine = fileContent[i++];
					
				}
				
				updateMdt("MEND");
				mdtp++;
				initAla();
				continue;
			}
			currentLine = fileContent[i++];
			
		}
		
	}

	private String substitute_index_notation(String currentLine2) {
		
		lineContent = currentLine.split("\\s+");
		String subsLine = "";
		Boolean isFound = false;
		int alaSize = getAlaSize();
		for(String currentWord : lineContent) {
			
			if(currentWord.matches(".+,.+")) {
				
				String[] commaSepr = currentWord.split(",");
				for(String comma : commaSepr) {
					for(int i=0; i<alaSize; i++) {
						if(comma.equals(getArgumentAtIndex(i))) {
							subsLine = subsLine +  i + " ";
							isFound = true;
							break;
						}
					}
				}
				
				
			}else {
				for(int i=0; i<alaSize; i++) {
					if(currentWord.equals(getArgumentAtIndex(i))) {
						subsLine = subsLine + i + " ";
						isFound = true;
						break;
					}
				}
				if(!isFound)
				subsLine = subsLine + currentWord + " ";
			}
			
		}
		
		return subsLine;
	}

}
