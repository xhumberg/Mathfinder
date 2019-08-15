package mathfinderXMLWrapper;

import characterModel.DiceType;

public class ClassXMLLoader {
//	public static ClassLevel loadClassLevelFromXML(String filename) {
//		filename = formatFilename(filename);
//		MathfinderXMLReader xmlReader = new MathfinderXMLReader(filename);
//		if (!xmlReader.isOfType("class")) {
//			throw new XMLFormatMismatchException("XML is not a class");
//		}
//		String className = xmlReader.getName();
//		DiceType hitDice = DiceType.valueOf(xmlReader.getNodeText("HD"));
//		
//	}

	private static String formatFilename(String filename) {
		if (!filename.endsWith(".xml"))
			return filename + ".xml";
		return filename;
	}
	
	
}
