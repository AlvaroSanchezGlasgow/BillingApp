package com.app.bill.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.app.bill.DTO.BillDTO;
import com.itextpdf.io.IOException;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfDocumentInfo;
import com.itextpdf.kernel.pdf.PdfViewerPreferences;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.draw.SolidLine;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.LineSeparator;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;



public class GeneratePDF {
	
	private static final Logger logger = LoggerFactory.getLogger(GeneratePDF.class);

	final static Color genericColor = new DeviceRgb(134, 0, 0);
	
	

	public static void generateBillingPDF(
			HttpServletResponse response, BillDTO billDTO) throws IOException, java.io.IOException {
		
		PdfWriter writer = new PdfWriter(response.getOutputStream());

		PdfDocument pdfDoc = new PdfDocument(writer);
		Document document = new Document(pdfDoc, PageSize.A4);

		try {

			response.setContentType("application/pdf");
			pdfDoc.setTagged();
			pdfDoc.getCatalog().setViewerPreferences(new PdfViewerPreferences().setDisplayDocTitle(true));

			// Set meta tags

			PdfDocumentInfo pdfMetaData = pdfDoc.getDocumentInfo();
			pdfMetaData.setAuthor("Billing Generator App");
			pdfMetaData.addCreationDate();
			pdfMetaData.getProducer();
			pdfMetaData.setCreator("Billing Generator App");
			pdfMetaData.setKeywords("Bill, Billing Generator App");
			pdfMetaData.setSubject("Billing Generator App");

			document = new Document(pdfDoc, PageSize.A4);

			// We add the header
			addReportHeader(document,billDTO);

			// We add the main table
			addReportTable(document);

			// We Add footer
			addReportFooter(document);

			document.flush();
			document.close();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			document.flush();
			document.close();
		}

	}

	/**
	 * 
	 * @param customerName
	 * @param document
	 * @param request
	 * @throws IOException
	 * @throws java.io.IOException 
	 */
	public static void addReportHeader(Document document, BillDTO billDTO) throws IOException, java.io.IOException {

		Date today = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(today);
		int year = cal.get(Calendar.YEAR);
		
		// Creating an ImageData object
		try {
		
			ImageData imageData = ImageDataFactory.create("");
			Image img = new Image(imageData);
			
			document.add(img);
				
			
		} catch (Exception e) {
			logger.error("Error: " + e.getMessage());
		}
		
		document.add(new Paragraph(billDTO.getProviderCompany())
				.setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA)).setFontColor(ColorConstants.BLACK)
				.setTextAlignment(TextAlignment.LEFT));
		document.add(new Paragraph(billDTO.getAddressLine1())
				.setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA)).setFontColor(ColorConstants.BLACK)
				.setTextAlignment(TextAlignment.LEFT));
		document.add(new Paragraph(billDTO.getAddressLine1())
				.setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA)).setFontColor(ColorConstants.BLACK)
				.setTextAlignment(TextAlignment.LEFT));
		document.add(new Paragraph(billDTO.getProviderEmail())
				.setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA)).setFontColor(ColorConstants.BLACK)
				.setTextAlignment(TextAlignment.LEFT));
		

		document.add(new Paragraph(year + " - "+billDTO.getCustomerCompanyData()).setBold()
				.setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA)).setFontColor(ColorConstants.BLACK)
				.setTextAlignment(TextAlignment.RIGHT));
		document.add(new Paragraph(billDTO.getAdicionalCustomerData()).setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
				.setFontColor(ColorConstants.BLACK).setTextAlignment(TextAlignment.RIGHT));
		
		document.add(new Paragraph("")
				.setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA)).setFontColor(ColorConstants.BLACK)
				.setTextAlignment(TextAlignment.CENTER));
		document.add(new Paragraph("")
				.setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA)).setFontColor(ColorConstants.BLACK)
				.setTextAlignment(TextAlignment.CENTER));
		document.add(new Paragraph("")
				.setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA)).setFontColor(ColorConstants.BLACK)
				.setTextAlignment(TextAlignment.CENTER));
		 SolidLine lineDrawer = new SolidLine(1f);
			lineDrawer.setColor(genericColor);
			document.add(new LineSeparator(lineDrawer));
		document.add(new Paragraph("")
				.setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA)).setFontColor(ColorConstants.BLACK)
				.setTextAlignment(TextAlignment.CENTER));
		document.add(new Paragraph("")
				.setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA)).setFontColor(ColorConstants.BLACK)
				.setTextAlignment(TextAlignment.CENTER));
		document.add(new Paragraph("")
				.setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA)).setFontColor(ColorConstants.BLACK)
				.setTextAlignment(TextAlignment.CENTER));
		
		
		
		
	}

	/**
	 * 
	 * @param document
	 * @param pListData
	 * @param listRatesDTO 
	 * @throws IOException
	 * @throws java.io.IOException 
	 */
	public static void addReportTable(Document document) throws IOException, java.io.IOException {

		Table table = new Table(3);
		// table.setWidth();

		List<String> listHeaders = new ArrayList<String>();
		listHeaders.add("ID");
		listHeaders.add("Concept");
		listHeaders.add("Price");
		

		Cell cell = new Cell();

		for (String i : listHeaders) {

			cell = new Cell().add(new Paragraph(i)).setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
					.setFontColor(ColorConstants.BLACK).setTextAlignment(TextAlignment.CENTER)
					.setBackgroundColor(ColorConstants.WHITE).setTextAlignment(TextAlignment.CENTER)
					.setBorder(Border.NO_BORDER).setFontSize(12);

			table.addHeaderCell(cell);
			//table.addHeaderCell(cell).setFixedLayout().setHeight(4).setWidth(200);
		}
		
		

		
			table.addCell(new Cell(1, 3)
					.add(new Paragraph("No Results Found").setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
							.setFontColor(ColorConstants.BLACK).setBackgroundColor(ColorConstants.WHITE)
							.setTextAlignment(TextAlignment.CENTER).setBorder(Border.NO_BORDER).setFontSize(9)));
							//.setFixedLayout().setHeight(4).setWidth(200);
	

		/*	table.addFooterCell(new Paragraph("TOTAL")).setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
					.setFontColor(ColorConstants.BLACK).setBold().setBackgroundColor(ColorConstants.WHITE)
					.setTextAlignment(TextAlignment.CENTER);
		*/
		document.add(table.setHorizontalAlignment(HorizontalAlignment.CENTER));
	}

	/**
	 * 
	 * @param document
	 * @throws IOException
	 * @throws java.io.IOException 
	 */
	public static void addReportFooter(Document document) throws IOException, java.io.IOException {

		document.add(new Paragraph("")
				.setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA)).setFontColor(ColorConstants.BLACK)
				.setTextAlignment(TextAlignment.CENTER));
		document.add(new Paragraph("")
				.setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA)).setFontColor(ColorConstants.BLACK)
				.setTextAlignment(TextAlignment.CENTER));
		
		 SolidLine lineDrawer = new SolidLine(1f);
			lineDrawer.setColor(genericColor);
			document.add(new LineSeparator(lineDrawer));
			
		/*
		  document.add(new Paragraph("")
		 		.setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA)).setFontColor(ColorConstants.BLACK)
				.setTextAlignment(TextAlignment.CENTER));
		document.add(new Paragraph("")
				.setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA)).setFontColor(ColorConstants.BLACK)
				.setTextAlignment(TextAlignment.CENTER));
		document.add(new Paragraph("")
				.setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA)).setFontColor(ColorConstants.BLACK)
				.setTextAlignment(TextAlignment.CENTER));
		
		document.add(new Paragraph("Notes")
				.setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA)).setFontColor(ColorConstants.BLACK).setBold()
				.setTextAlignment(TextAlignment.LEFT).setFontSize(7));
		document.add(new Paragraph("TNUoS Savings (C) = Average Avoided Triad Demand (A) x TNUoS charge (B)")
				.setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA)).setFontColor(ColorConstants.BLACK)
				.setTextAlignment(TextAlignment.LEFT).setFontSize(7));
		document.add(new Paragraph("Total Savings (E) = TNUoS Savings (C) + Total Energy Cost Avoided (D)")
				.setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA)).setFontColor(ColorConstants.BLACK)
				.setTextAlignment(TextAlignment.LEFT).setFontSize(7));
		document.add(new Paragraph("SPERL Triad Management Fee (G) = (Total Savings (E) - Fuel cost (F)) x 15%")
				.setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA)).setFontColor(ColorConstants.BLACK)
				.setTextAlignment(TextAlignment.LEFT).setFontSize(7));
		document.add(new Paragraph("Customer Net Saving (H) = Total Savings (E) - Fuel Cost (F) - SPERL Triad Management Fee (G)")
				.setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA)).setFontColor(ColorConstants.BLACK)
				.setTextAlignment(TextAlignment.LEFT).setFontSize(7));
*/
		document.add(new Paragraph("")
				.setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA)).setFontColor(ColorConstants.BLACK)
				.setTextAlignment(TextAlignment.CENTER));
		document.add(new Paragraph("")
				.setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA)).setFontColor(ColorConstants.BLACK)
				.setTextAlignment(TextAlignment.CENTER));
		document.add(new Paragraph("")
				.setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA)).setFontColor(ColorConstants.BLACK)
				.setTextAlignment(TextAlignment.CENTER));
		document.add(new Paragraph("")
				.setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA)).setFontColor(ColorConstants.BLACK)
				.setTextAlignment(TextAlignment.CENTER));

		
			document.add(new LineSeparator(lineDrawer));
		
		document.add(new Paragraph("Visit: http://www.billinggenerator.co.uk/")
				.setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA)).setFontColor(ColorConstants.BLACK)
				.setTextAlignment(TextAlignment.RIGHT).setFontSize(9));

		document.add(new Paragraph("Email: info@billinggenerator.com")
				.setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA)).setFontColor(ColorConstants.BLACK)
				.setTextAlignment(TextAlignment.RIGHT).setFontSize(9));

	}


}
