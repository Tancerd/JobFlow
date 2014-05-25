package org.pwr.zssk.view;

import java.io.FileOutputStream;
import java.util.Date;

import org.pwr.zssk.dataaccess.DataStore;
import org.pwr.zssk.dataaccess.ResultStore;
import org.pwr.zssk.dataaccess.Rule;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class PDFWriter {

	private String path;
	private DataStore dataStore;
	private ResultStore resultStore;

	private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
			Font.BOLD);
	private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
			Font.NORMAL, BaseColor.RED);
	private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
			Font.BOLD);
	private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
			Font.BOLD);

	public PDFWriter(String path, DataStore dataStore, ResultStore resultStore) {
		super();
		this.path = path;
		this.dataStore = dataStore;
		this.resultStore = resultStore;
	}

	public void write() throws Exception {
		try {
			Document document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(path));
			document.open();
			addMetaData(document);
			addTitlePage(document);
			addContent(document);
			document.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	private static void addMetaData(Document document) {
		document.addTitle("Symulation results");
		document.addSubject("Using iText");
		document.addKeywords("Java, PDF");
		document.addAuthor("Micha³ Kowalik");
		document.addCreator("Micha³ Kowalik");
	}

	private static void addTitlePage(Document document)
			throws DocumentException {
		Paragraph preface = new Paragraph();
		// We add one empty line
		addEmptyLine(preface, 1);
		// Lets write a big header
		preface.add(new Paragraph("Symulacja Flow Shop",
				catFont));

		addEmptyLine(preface, 1);
		preface.add(new Paragraph("Raport wygenerowano: " + new Date(), smallBold));
		addEmptyLine(preface, 3);

		document.add(preface);
		// Start a new page
		document.newPage();
	}

	private void addContent(Document document) throws DocumentException {
		Anchor anchor = new Anchor("Dane wejsciowe", catFont);
		anchor.setName("Dane wejsciowe");

		Chapter catPart = new Chapter(new Paragraph(anchor), 1);

		Paragraph subPara = new Paragraph("Zadania", subFont);
		Section subCatPart = catPart.addSection(subPara);
		prepareJobsData(subCatPart);
		
		/////////////////////////////////////////////////

		subPara = new Paragraph("Maszyny", subFont);
		subCatPart = catPart.addSection(subPara);
		prepareMachinesData(subCatPart);

		document.add(catPart);
		
		//////////////////////////////////////////////////
		
		
		anchor = new Anchor("Wyniki symulacji", catFont);
		anchor.setName("Wyniki symulacji");

		// Second parameter is the number of the chapter
		catPart = new Chapter(new Paragraph(anchor), 1);

		subPara = new Paragraph("Subcategory", subFont);
		subCatPart = catPart.addSection(subPara);
		prepareResults(subCatPart);

		// now add all this to the document
		document.add(catPart);

	}

	private void prepareResults(Section subCatPart) {
		subCatPart.add(new Paragraph(resultStore.toString()));
		
	}

	private void prepareMachinesData(Section subCatPart) {
		subCatPart.add(new Paragraph("Liczba maszyn: " + dataStore.getMachineNumber()));
		subCatPart.add(new Paragraph(" "));
		subCatPart.add(new Paragraph("Tabela regul na maszynach"));
		
		PdfPTable table = new PdfPTable(dataStore.getJobNumber());
		table = new PdfPTable(dataStore.getMachineNumber());
		for (int i = 0; i < dataStore.getMachineNumber(); i++)
		{
			PdfPCell c1 = new PdfPCell(new Phrase("Maszyna" + (i+1)));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(c1);
		}
		
		for (Rule s : dataStore.getRules())
		{
			PdfPCell c1 = new PdfPCell(new Phrase(s.toString()));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(c1);
		}
		subCatPart.add(table);
		subCatPart.add(new Paragraph("Macierz przezbrojen"));
		
		table = new PdfPTable(dataStore.getJobNumber()+1);
		table.addCell("");
		
		for (int i = 1; i <= dataStore.getJobNumber(); i++)
		{
			PdfPCell c1 = new PdfPCell(new Phrase("Z " + i));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(c1);
		}
		
		for (int i = 0; i < dataStore.getMachineNumber(); i++)
		{
			PdfPCell c1 = new PdfPCell(new Phrase("M " + (i+1)));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(c1);
			
			for (int j = 0; j < dataStore.getJobNumber(); j++)
			{
				PdfPCell c2 = new PdfPCell(new Phrase(dataStore.getPrepareTimes()[i][j].toString()));
				c2.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(c2);
			}
		}
		subCatPart.add(table);
	}

	private void prepareJobsData(Section subCatPart) {
		subCatPart.add(new Paragraph("Liczba zadan: " + dataStore.getJobNumber()));
		subCatPart.add(new Paragraph("Tabela opoznien zadan"));
		subCatPart.add(new Paragraph(" "));
		
		PdfPTable table = new PdfPTable(dataStore.getJobNumber());
		for (int i = 1; i <= dataStore.getJobNumber(); i++)
		{
			PdfPCell c1 = new PdfPCell(new Phrase("Z " + i));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(c1);
		}
		
		table.setHeaderRows(1);

		for (Integer i : dataStore.getArriveTimes())
		{
			PdfPCell c1 = new PdfPCell(new Phrase(i.toString()));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(c1);
		}

		subCatPart.add(table);
		subCatPart.add(new Paragraph(" "));
		subCatPart.add(new Paragraph("Tabele kolejnosci maszyn dla zadan"));
		subCatPart.add(new Paragraph(" "));
		
		for (int i = 0; i < dataStore.getOrders().length; i++)
		{
			subCatPart.add(new Paragraph("Zadanie " + (i+1)));
			subCatPart.add(new Paragraph(""));
			table = new PdfPTable(dataStore.getMachineNumber() + 1);
			PdfPCell c1 = new PdfPCell(new Phrase("Maszyna"));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(c1);
			for (String s : dataStore.getOrders()[i])
			{
				String split[] = s.split(" ");
				table.addCell(split[0]);
			}
			c1 = new PdfPCell(new Phrase("Czas"));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(c1);
			for (String s : dataStore.getOrders()[i])
			{
				String split[] = s.split(" ");
				table.addCell(split[1]);
			}
			subCatPart.add(table);
		}
		
		
		
	}

	private static void createTable(Section subCatPart)
			throws BadElementException {
		PdfPTable table = new PdfPTable(3);

		// t.setBorderColor(BaseColor.GRAY);
		// t.setPadding(4);
		// t.setSpacing(4);
		// t.setBorderWidth(1);

		PdfPCell c1 = new PdfPCell(new Phrase("Table Header 1"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Table Header 2"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Table Header 3"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);
		table.setHeaderRows(1);

		table.addCell("1.0");
		table.addCell("1.1");
		table.addCell("1.2");
		table.addCell("2.1");
		table.addCell("2.2");
		table.addCell("2.3");

		subCatPart.add(table);

	}

	private static void createList(Section subCatPart) {
		List list = new List(true, false, 10);
		list.add(new ListItem("First point"));
		list.add(new ListItem("Second point"));
		list.add(new ListItem("Third point"));
		subCatPart.add(list);
	}

	private static void addEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}
}
