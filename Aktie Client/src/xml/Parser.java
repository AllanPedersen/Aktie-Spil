package xml;


import java.net.URL;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import logic.Stock;

public class Parser {

	public static ArrayList<Stock> getAllStocks() {
		String[] stocks = {"AAPL", "GOOG", "YHOO", "TWTR", "FB", "GS", "CSCO", "MSFT", "JPM", "C", 
				"GE", "F", "GM", "NOK", "BBRY", "ORCL", "T", "HPQ", "AAL", "FOXA", "JCP", "VZ", "WMT",
				"MCD", "DIS", "AXP", "BA", "KO", "MU", "AA", "ZNGA", "INTC", "AMD", "SIRI", "PFE", "MS"};

		// Create the URL string with all the above stocks
		String url = "";

		url += stocks[0];
		for (int i = 1; i < stocks.length; i++) {
			url += ",";
			url += stocks[i];
		}

		url = "http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.quotes%20where%20symbol%20in%20(%22"
				+ url + "%22)&env=store://datatables.org/alltableswithkeys";

		System.out.println(url);

		ArrayList<Stock> stcks = new ArrayList<Stock>();

		// Fetch XML and parse into objects
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(new URL(url).openStream());

			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("quote");

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					if (Double.parseDouble(eElement.getElementsByTagName("AskRealtime").item(0).getTextContent()) == 0) {
						Stock tempStock = new Stock(eElement.getElementsByTagName("Name").item(0).getTextContent(), 
								Double.parseDouble(eElement.getElementsByTagName("Bid").item(0).getTextContent()));
						tempStock.setSymbol(eElement.getAttribute("symbol"));
						stcks.add(tempStock);
					} else {
						Stock tempStock = new Stock(eElement.getElementsByTagName("Name").item(0).getTextContent(), 
								Double.parseDouble(eElement.getElementsByTagName("AskRealtime").item(0).getTextContent()));
						tempStock.setSymbol(eElement.getAttribute("symbol"));
						stcks.add(tempStock);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}


		return stcks;
	}

	public static double getValueFromSymbol(String symbol) {
		String url = "http://query.yahooapis.com/v1/public/yql?q="
				+ "select%20*%20from%20yahoo.finance.quotes%20where%20symbol%20in%20(%22"+symbol+"%22)"
				+ "&env=store://datatables.org/alltableswithkeys";


		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(new URL(url).openStream());

			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("quote");

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					if (Double.parseDouble(eElement.getElementsByTagName("AskRealtime").item(0).getTextContent()) == 0) {
						return Double.parseDouble(eElement.getElementsByTagName("Bid").item(0).getTextContent());
					} else {
						return Double.parseDouble(eElement.getElementsByTagName("AskRealtime").item(0).getTextContent());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Returns 0 if everything fails
		return 0;
	}

}
