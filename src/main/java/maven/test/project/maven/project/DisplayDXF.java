package maven.test.project.maven.project;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kabeja.dxf.DXFConstants;
import org.kabeja.dxf.DXFDocument;
import org.kabeja.dxf.DXFLayer;
import org.kabeja.dxf.DXFPolyline;
import org.kabeja.dxf.DXFVertex;
import org.kabeja.parser.DXFParser;
import org.kabeja.parser.ParseException;
import org.kabeja.parser.Parser;
import org.kabeja.parser.ParserBuilder;
import org.kabeja.svg.SVGGenerator;
import org.kabeja.xml.SAXSerializer;
import org.xml.sax.SAXException;

public class DisplayDXF {
	static InputStream inputStream;
	static FileInputStream fileInputStream;
	Map<Object, Object> map = new HashMap<Object, Object>();

	public static void main(String[] args) {
		String file = "D:\\LIBRARIES\\kabeja-0.4\\kabeja-0.4\\samples\\dxf\\draft1.dxf";
		DisplayDXF displayDXF = new DisplayDXF();
		ParserClass parserClass = displayDXF.new ParserClass();
		parserClass.parseFile(file);

		try {
			fileInputStream = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		DXFExtractor extractor = displayDXF.new DXFExtractor();
		extractor.read(fileInputStream, 0 + "");
	}

	class ParserClass {
		String reading_parsing;
		FileOutputStream outputStream;

		public ParserClass() {
			reading_parsing = "parsing";
		}

		public ParserClass(String reading) {
			reading_parsing = reading;
		}

		public void parseFile(String sourceFile) {

			try {
				if (reading_parsing.equals("parsing")) {
					outputStream = new FileOutputStream(
							"D:\\LIBRARIES\\convertedPng\\draft1.png");
				}
				inputStream = new FileInputStream(sourceFile);
				Parser dxfParser = ParserBuilder.createDefaultParser();
				dxfParser.parse(inputStream, "");
				DXFDocument dxfDocument = dxfParser.getDocument();
				SVGGenerator svgGenerator = new SVGGenerator();
				SAXSerializer saxSerializer = new org.kabeja.batik.tools.SAXPNGSerializer();
				map = new HashMap<Object, Object>();
				saxSerializer.setOutput(outputStream);
				svgGenerator.generate(dxfDocument, saxSerializer, map);
			} catch (ParseException parseException) {
				parseException.printStackTrace();
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}
	}

	class DXFExtractor {

		public void read(InputStream in, String layerid) {
			map.clear();
			Parser parser = ParserBuilder.createDefaultParser();
			try {
				// parse
				parser.parse(in, DXFParser.DEFAULT_ENCODING);
				// get the documnet and the layer
				DXFDocument doc = parser.getDocument();
				DXFLayer layer = doc.getDXFLayer(layerid);
				// get all polylines from the layer
				List<?> plines = layer
						.getDXFEntities(DXFConstants.ENTITY_TYPE_POLYLINE);
				// work with the first polyline
				doSomething((DXFPolyline) plines.get(0));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public void doSomething(DXFPolyline pline) throws FileNotFoundException, SAXException {
			System.out.println("pline.getVertexCount(): "
					+ pline.getVertexCount());
			// iterate over all vertex of the polyline
			for (int i = 0; i < pline.getVertexCount(); i++) {
				map = new HashMap<Object, Object>();
				DXFVertex vertex = pline.getVertex(i);

				System.out.println("vertex.getBounds(): >>>>>>>"
						+ vertex.getBounds());
				
				System.out.println("vertex.getLayerName(): "+vertex.getLayerName());
				DXFDocument dxfDocument = vertex.getDXFDocument();
				
				FileOutputStream outputStream = new FileOutputStream(
						"D:\\LIBRARIES\\readingPolyLines\\draft1.png");
				
				SVGGenerator svgGenerator = new SVGGenerator();
				SAXSerializer saxSerializer = new org.kabeja.batik.tools.SAXPNGSerializer();
				saxSerializer.setOutput(outputStream);
				svgGenerator.generate(dxfDocument, saxSerializer, map);

				// do something like collect the data and
				// build a mesh for a FEM system
			}
		}
	}

}
