package mathfinderXMLWrapper;

import java.io.IOException;

import characterModel.CharacterClass;

public class ClassXMLLoader extends MathfinderXMLReader<CharacterClass>{

	public ClassXMLLoader(String filename) throws IOException, XMLFormatMismatchException {
		super(filename);
		if (!rootIsOfType("Class")) {
			throw new XMLFormatMismatchException("File is not a Class XML");
		}
	}

	@Override
	public CharacterClass decodeFromFile() {
		String className = getName();
		
		return null;
	}
}
