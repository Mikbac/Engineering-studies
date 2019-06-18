package data;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import org.w3c.dom.*;

import javax.xml.parsers.*;
import java.util.Vector;

public class dataSupport {

    public static List getFiles(String path) {
        File folder = new File(path);
        List list = new LinkedList();
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) {
            if (file.isFile()) {
                list.add(file.getName());
            }
        }
        return list;
    }

    public static Vector getDirectories(String dirPath) {
        Vector vector = new Vector();
        File dir = new File(dirPath);
        String[] files = dir.list();
        if (files.length == 0) {
            System.out.println("The directory is empty");
        } else {
            for (String aFile : files) {
                vector.add(aFile);
            }
        }
        return vector;
    }

    public static List getAttributeValue(String name, String AttributeElement) throws Exception {
        LinkedList<String> attributes = new LinkedList<String>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document document = builder.parse(new File("./data/" + name + "/" + name + ".xml"));

        document.getDocumentElement().normalize();

        NodeList nList = document.getElementsByTagName("przystanek");

        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node node = nList.item(temp);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) node;
                attributes.add(eElement.getAttribute(AttributeElement));
            }
        }

        return attributes;
    }


    public static List getHours(String name, String id) throws Exception {
        LinkedList<String> linkedlist = new LinkedList<String>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document document = builder.parse(new File("./data/" + name + "/" + name + ".xml"));

        document.getDocumentElement().normalize();

        NodeList nListPrz = document.getElementsByTagName("przystanek");
        NodeList nListGodz = document.getElementsByTagName("godz");


        for (int tempPrz = 0; tempPrz < nListPrz.getLength(); tempPrz++) {
            Node nodePrz = nListPrz.item(tempPrz);
            if (nodePrz.getNodeType() == Node.ELEMENT_NODE) {
                Element eElementPrz = (Element) nodePrz;

                if (eElementPrz.getAttribute("id").equals(id)) {

                    for (int tempGodz = 0; tempGodz < nListGodz.getLength(); tempGodz++) {
                        Node nodeGodz = nListGodz.item(tempGodz);
                        if (nodeGodz.getNodeType() == Node.ELEMENT_NODE) {
                            Element eElementGodz = (Element) nodeGodz;
                            linkedlist.add(String.valueOf(eElementGodz.getAttribute("h")));

                        }
                        if (linkedlist.contains("23"))
                            return linkedlist;
                    }


                }

            }
        }


        return null;
    }


}
