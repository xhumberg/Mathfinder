package mathfinderXMLWrapper;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public abstract class MathfinderXMLReader<T> {
	private XmlNode root;
	
	protected MathfinderXMLReader(String filename) throws IOException {
		root = null;
		try {
			setupRootNode(filename);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			return;
		} catch (SAXException e) {
			e.printStackTrace();
			return;
		}
	}
	
	public abstract T decodeFromFile();

	private void setupRootNode(String filename) throws ParserConfigurationException, SAXException, IOException {
		File XMLFile = new File(filename);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		dBuilder = dbFactory.newDocumentBuilder();
		Document xml = dBuilder.parse(XMLFile);
		root = new XmlNode(xml.getDocumentElement());
	}
	
	protected boolean rootIsOfType(String type) {
		if (root == null)
			return false;
		return root.getName() == type;
	}
	
	protected String getName() {
		if (root == null)
			return null;
		return ((Element) root).getAttribute("name");
	}
	
	public String getNodeText(String tagName) {
//		return getNode(root, tagName).getTextContent();
		return null;
	}

	private Node getNode(Node parent, String tagName) {
		return getNodeList(parent, tagName).item(0);
	}
	
	private NodeList getNodeList(Node parent, String tagName) {
		Element element = (Element) parent;
		
		return element.getElementsByTagName(tagName);
	}
}
