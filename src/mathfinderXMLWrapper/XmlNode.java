package mathfinderXMLWrapper;

import org.w3c.dom.Node;

public class XmlNode {
	Node node;
	
	public XmlNode(Node node) {
		this.node = node;
	}

	public String getName() {
		return node.getNodeName();
	}
}
