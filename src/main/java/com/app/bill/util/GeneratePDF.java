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
	
	

	public void generateBillingPDF(HttpServletResponse response, BillDTO billPdf) throws IOException, java.io.IOException {
		
		PdfWriter writer = new PdfWriter(response.getOutputStream());

		PdfDocument pdfDoc = new PdfDocument(writer);
		Document document = new Document(pdfDoc, PageSize.A4);

		try {
			
			//billPdf.

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
			addReportHeader(document,billPdf);

			// We add the main table
			addReportTable(document,billPdf);
			
			//foter
			addReportFooter(document,billPdf);

			
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
	public static void addReportHeader(Document document, BillDTO billPdf) throws IOException, java.io.IOException {

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

		document.add(new Paragraph(new Date().toString()).setBold()
				.setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA)).setFontColor(ColorConstants.BLACK)
				.setTextAlignment(TextAlignment.RIGHT).setFontSize(7));
		document.add(new Paragraph(billPdf.getCustomerCompanyData()).setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
				.setFontColor(ColorConstants.BLACK).setTextAlignment(TextAlignment.RIGHT).setFontSize(7));
		document.add(new Paragraph(billPdf.getAdicionalCustomerData()).setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
				.setFontColor(ColorConstants.BLACK).setTextAlignment(TextAlignment.RIGHT).setFontSize(7));
		
		document.add(new Paragraph(billPdf.getProviderCompany())
				.setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA)).setFontColor(ColorConstants.BLACK)
				.setTextAlignment(TextAlignment.LEFT).setFontSize(7));
		
		document.add(new Paragraph(billPdf.getAddressLine1())
				.setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA)).setFontColor(ColorConstants.BLACK)
				.setTextAlignment(TextAlignment.LEFT).setFontSize(7));
		document.add(new Paragraph(billPdf.getAddressLine2())
				.setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA)).setFontColor(ColorConstants.BLACK)
				.setTextAlignment(TextAlignment.LEFT).setFontSize(7));
		document.add(new Paragraph(billPdf.getProviderEmail())
				.setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA)).setFontColor(ColorConstants.BLACK)
				.setTextAlignment(TextAlignment.LEFT).setFontSize(7));
		
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
	 * @param billPdf 
	 * @param pListData
	 * @param listRatesDTO 
	 * @throws IOException
	 * @throws java.io.IOException 
	 */
	public static void addReportTable(Document document, BillDTO billPdf) throws IOException, java.io.IOException {

		Table table = new Table(4);
		// table.setWidth();

		List<String> listHeaders = new ArrayList<String>();
		listHeaders.add("Ammount");
		listHeaders.add("Description");
		listHeaders.add("Unit Price");
		listHeaders.add("Total");
		

		Cell cell = new Cell();

		//HEADER

			cell = new Cell().add(new Paragraph("AMMOUNT")).setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
					.setFontColor(ColorConstants.BLACK).setTextAlignment(TextAlignment.CENTER)
					.setBackgroundColor(ColorConstants.WHITE).setTextAlignment(TextAlignment.CENTER).setBackgroundColor(ColorConstants.LIGHT_GRAY)
					.setFontSize(12);

			table.addHeaderCell(cell);
			
			cell = new Cell().add(new Paragraph("DESCRIPTION")).setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
					.setFontColor(ColorConstants.BLACK).setTextAlignment(TextAlignment.CENTER)
					.setBackgroundColor(ColorConstants.WHITE).setTextAlignment(TextAlignment.CENTER).setBackgroundColor(ColorConstants.LIGHT_GRAY)
					.setFontSize(12);

			table.addHeaderCell(cell);
			
			cell = new Cell().add(new Paragraph("UNIT PRICE")).setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
					.setFontColor(ColorConstants.BLACK).setTextAlignment(TextAlignment.CENTER)
					.setBackgroundColor(ColorConstants.WHITE).setTextAlignment(TextAlignment.CENTER).setBackgroundColor(ColorConstants.LIGHT_GRAY)
					.setFontSize(12);
			
			table.addHeaderCell(cell);
			
			cell = new Cell().add(new Paragraph("TOTAL")).setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
					.setFontColor(ColorConstants.BLACK).setTextAlignment(TextAlignment.CENTER)
					.setBackgroundColor(ColorConstants.WHITE).setTextAlignment(TextAlignment.CENTER).setBackgroundColor(ColorConstants.LIGHT_GRAY)
					.setFontSize(12);

			table.addHeaderCell(cell);
			//table.addHeaderCell(cell).setFixedLayout().setHeight(4).setWidth(200);
	
			//BODY
		
					
			table.addCell(new Cell()
					.add(new Paragraph(""+billPdf.getAmmount0()).setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
							.setFontColor(ColorConstants.BLACK).setBackgroundColor(ColorConstants.WHITE)
							.setTextAlignment(TextAlignment.CENTER).setFontSize(9)));
							//.setFixedLayout().setHeight(4).setWidth(200);
			
			table.addCell(new Cell()
					.add(new Paragraph(""+billPdf.getDescription0()).setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
							.setFontColor(ColorConstants.BLACK).setBackgroundColor(ColorConstants.WHITE)
							.setTextAlignment(TextAlignment.CENTER).setFontSize(9)));
			
			
			table.addCell(new Cell()
					.add(new Paragraph(""+billPdf.getUnitPrice0()).setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
							.setFontColor(ColorConstants.BLACK).setBackgroundColor(ColorConstants.WHITE)
							.setTextAlignment(TextAlignment.CENTER).setFontSize(9)));
							//.setFixedLayout().setHeight(4).setWidth(200);
			
			table.addCell(new Cell()
					.add(new Paragraph(""+billPdf.getTotal0()).setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
							.setFontColor(ColorConstants.BLACK).setBackgroundColor(ColorConstants.WHITE)
							.setTextAlignment(TextAlignment.CENTER).setFontSize(9)));
							//.setFixedLayout().setHeight(4).setWidth(200);
	
			
			//////////////////
			
			
				
			if (billPdf.getAmmount1()!=0) {
			table.addCell(new Cell()
					.add(new Paragraph(""+billPdf.getAmmount1()).setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
							.setFontColor(ColorConstants.BLACK).setBackgroundColor(ColorConstants.WHITE)
							.setTextAlignment(TextAlignment.CENTER).setFontSize(9)));
							//.setFixedLayout().setHeight(4).setWidth(200);
			
			table.addCell(new Cell()
					.add(new Paragraph(""+billPdf.getDescription1()).setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
							.setFontColor(ColorConstants.BLACK).setBackgroundColor(ColorConstants.WHITE)
							.setTextAlignment(TextAlignment.CENTER).setFontSize(9)));
			
			
			table.addCell(new Cell()
					.add(new Paragraph(""+billPdf.getUnitPrice1()).setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
							.setFontColor(ColorConstants.BLACK).setBackgroundColor(ColorConstants.WHITE)
							.setTextAlignment(TextAlignment.CENTER).setFontSize(9)));
							//.setFixedLayout().setHeight(4).setWidth(200);
			
			table.addCell(new Cell()
					.add(new Paragraph(""+billPdf.getTotal1()).setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
							.setFontColor(ColorConstants.BLACK).setBackgroundColor(ColorConstants.WHITE)
							.setTextAlignment(TextAlignment.CENTER).setFontSize(9)));
							//.setFixedLayout().setHeight(4).setWidth(200);
			}
			
			////////////////
			
			if (billPdf.getAmmount2()!=0) {
				table.addCell(new Cell()
						.add(new Paragraph(""+billPdf.getAmmount2()).setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
								.setFontColor(ColorConstants.BLACK).setBackgroundColor(ColorConstants.WHITE)
								.setTextAlignment(TextAlignment.CENTER).setFontSize(9)));
								//.setFixedLayout().setHeight(4).setWidth(200);
				
				table.addCell(new Cell()
						.add(new Paragraph(""+billPdf.getDescription2()).setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
								.setFontColor(ColorConstants.BLACK).setBackgroundColor(ColorConstants.WHITE)
								.setTextAlignment(TextAlignment.CENTER).setFontSize(9)));
				
				
				table.addCell(new Cell()
						.add(new Paragraph(""+billPdf.getUnitPrice2()).setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
								.setFontColor(ColorConstants.BLACK).setBackgroundColor(ColorConstants.WHITE)
								.setTextAlignment(TextAlignment.CENTER).setFontSize(9)));
								//.setFixedLayout().setHeight(4).setWidth(200);
				
				table.addCell(new Cell()
						.add(new Paragraph(""+billPdf.getTotal2()).setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
								.setFontColor(ColorConstants.BLACK).setBackgroundColor(ColorConstants.WHITE)
								.setTextAlignment(TextAlignment.CENTER).setFontSize(9)));
								//.setFixedLayout().setHeight(4).setWidth(200);
				}
				
				////////////////
				
			if (billPdf.getAmmount3()!=0) {
				table.addCell(new Cell()
						.add(new Paragraph(""+billPdf.getAmmount3()).setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
								.setFontColor(ColorConstants.BLACK).setBackgroundColor(ColorConstants.WHITE)
								.setTextAlignment(TextAlignment.CENTER).setFontSize(9)));
								//.setFixedLayout().setHeight(4).setWidth(200);
				
				table.addCell(new Cell()
						.add(new Paragraph(""+billPdf.getDescription3()).setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
								.setFontColor(ColorConstants.BLACK).setBackgroundColor(ColorConstants.WHITE)
								.setTextAlignment(TextAlignment.CENTER).setFontSize(9)));
				
				
				table.addCell(new Cell()
						.add(new Paragraph(""+billPdf.getUnitPrice3()).setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
								.setFontColor(ColorConstants.BLACK).setBackgroundColor(ColorConstants.WHITE)
								.setTextAlignment(TextAlignment.CENTER).setFontSize(9)));
								//.setFixedLayout().setHeight(4).setWidth(200);
				
				table.addCell(new Cell()
						.add(new Paragraph(""+billPdf.getTotal3()).setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
								.setFontColor(ColorConstants.BLACK).setBackgroundColor(ColorConstants.WHITE)
								.setTextAlignment(TextAlignment.CENTER).setFontSize(9)));
								//.setFixedLayout().setHeight(4).setWidth(200);
				}
				
				////////////////
			if (billPdf.getAmmount4()!=0) {
				table.addCell(new Cell()
						.add(new Paragraph(""+billPdf.getAmmount4()).setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
								.setFontColor(ColorConstants.BLACK).setBackgroundColor(ColorConstants.WHITE)
								.setTextAlignment(TextAlignment.CENTER).setFontSize(9)));
								//.setFixedLayout().setHeight(4).setWidth(200);
				
				table.addCell(new Cell()
						.add(new Paragraph(""+billPdf.getDescription4()).setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
								.setFontColor(ColorConstants.BLACK).setBackgroundColor(ColorConstants.WHITE)
								.setTextAlignment(TextAlignment.CENTER).setFontSize(9)));
				
				
				table.addCell(new Cell()
						.add(new Paragraph(""+billPdf.getUnitPrice4()).setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
								.setFontColor(ColorConstants.BLACK).setBackgroundColor(ColorConstants.WHITE)
								.setTextAlignment(TextAlignment.CENTER).setFontSize(9)));
								//.setFixedLayout().setHeight(4).setWidth(200);
				
				table.addCell(new Cell()
						.add(new Paragraph(""+billPdf.getTotal4()).setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
								.setFontColor(ColorConstants.BLACK).setBackgroundColor(ColorConstants.WHITE)
								.setTextAlignment(TextAlignment.CENTER).setFontSize(9)));
								//.setFixedLayout().setHeight(4).setWidth(200);
				}
				
				////////////////
			
			if (billPdf.getAmmount5()!=0) {
				table.addCell(new Cell()
						.add(new Paragraph(""+billPdf.getAmmount5()).setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
								.setFontColor(ColorConstants.BLACK).setBackgroundColor(ColorConstants.WHITE)
								.setTextAlignment(TextAlignment.CENTER).setFontSize(9)));
								//.setFixedLayout().setHeight(4).setWidth(200);
				
				table.addCell(new Cell()
						.add(new Paragraph(""+billPdf.getDescription5()).setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
								.setFontColor(ColorConstants.BLACK).setBackgroundColor(ColorConstants.WHITE)
								.setTextAlignment(TextAlignment.CENTER).setFontSize(9)));
				
				
				table.addCell(new Cell()
						.add(new Paragraph(""+billPdf.getUnitPrice5()).setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
								.setFontColor(ColorConstants.BLACK).setBackgroundColor(ColorConstants.WHITE)
								.setTextAlignment(TextAlignment.CENTER).setFontSize(9)));
								//.setFixedLayout().setHeight(4).setWidth(200);
				
				table.addCell(new Cell()
						.add(new Paragraph(""+billPdf.getTotal5()).setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
								.setFontColor(ColorConstants.BLACK).setBackgroundColor(ColorConstants.WHITE)
								.setTextAlignment(TextAlignment.CENTER).setFontSize(9)));
								//.setFixedLayout().setHeight(4).setWidth(200);
				}
				
				////////////////
			
			if (billPdf.getAmmount6()!=0) {
				table.addCell(new Cell()
						.add(new Paragraph(""+billPdf.getAmmount6()).setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
								.setFontColor(ColorConstants.BLACK).setBackgroundColor(ColorConstants.WHITE)
								.setTextAlignment(TextAlignment.CENTER).setFontSize(9)));
								//.setFixedLayout().setHeight(4).setWidth(200);
				
				table.addCell(new Cell()
						.add(new Paragraph(""+billPdf.getDescription6()).setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
								.setFontColor(ColorConstants.BLACK).setBackgroundColor(ColorConstants.WHITE)
								.setTextAlignment(TextAlignment.CENTER).setFontSize(9)));
				
				
				table.addCell(new Cell()
						.add(new Paragraph(""+billPdf.getUnitPrice6()).setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
								.setFontColor(ColorConstants.BLACK).setBackgroundColor(ColorConstants.WHITE)
								.setTextAlignment(TextAlignment.CENTER).setFontSize(9)));
								//.setFixedLayout().setHeight(4).setWidth(200);
				
				table.addCell(new Cell()
						.add(new Paragraph(""+billPdf.getTotal6()).setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
								.setFontColor(ColorConstants.BLACK).setBackgroundColor(ColorConstants.WHITE)
								.setTextAlignment(TextAlignment.CENTER).setFontSize(9)));
								//.setFixedLayout().setHeight(4).setWidth(200);
				}
				
				////////////////
			 
			if (billPdf.getAmmount7()!=0) {
				table.addCell(new Cell()
						.add(new Paragraph(""+billPdf.getAmmount7()).setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
								.setFontColor(ColorConstants.BLACK).setBackgroundColor(ColorConstants.WHITE)
								.setTextAlignment(TextAlignment.CENTER).setFontSize(9)));
								//.setFixedLayout().setHeight(4).setWidth(200);
				
				table.addCell(new Cell()
						.add(new Paragraph(""+billPdf.getDescription7()).setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
								.setFontColor(ColorConstants.BLACK).setBackgroundColor(ColorConstants.WHITE)
								.setTextAlignment(TextAlignment.CENTER).setFontSize(9)));
				
				
				table.addCell(new Cell()
						.add(new Paragraph(""+billPdf.getUnitPrice7()).setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
								.setFontColor(ColorConstants.BLACK).setBackgroundColor(ColorConstants.WHITE)
								.setTextAlignment(TextAlignment.CENTER).setFontSize(9)));
								//.setFixedLayout().setHeight(4).setWidth(200);
				
				table.addCell(new Cell()
						.add(new Paragraph(""+billPdf.getTotal7()).setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
								.setFontColor(ColorConstants.BLACK).setBackgroundColor(ColorConstants.WHITE)
								.setTextAlignment(TextAlignment.CENTER).setFontSize(9)));
								//.setFixedLayout().setHeight(4).setWidth(200);
				}
				
				////////////////
			
			if (billPdf.getAmmount8()!=0) {
				table.addCell(new Cell()
						.add(new Paragraph(""+billPdf.getAmmount8()).setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
								.setFontColor(ColorConstants.BLACK).setBackgroundColor(ColorConstants.WHITE)
								.setTextAlignment(TextAlignment.CENTER).setFontSize(9)));
								//.setFixedLayout().setHeight(4).setWidth(200);
				
				table.addCell(new Cell()
						.add(new Paragraph(""+billPdf.getDescription8()).setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
								.setFontColor(ColorConstants.BLACK).setBackgroundColor(ColorConstants.WHITE)
								.setTextAlignment(TextAlignment.CENTER).setFontSize(9)));
				
				
				table.addCell(new Cell()
						.add(new Paragraph(""+billPdf.getUnitPrice8()).setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
								.setFontColor(ColorConstants.BLACK).setBackgroundColor(ColorConstants.WHITE)
								.setTextAlignment(TextAlignment.CENTER).setFontSize(9)));
								//.setFixedLayout().setHeight(4).setWidth(200);
				
				table.addCell(new Cell()
						.add(new Paragraph(""+billPdf.getTotal8()).setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
								.setFontColor(ColorConstants.BLACK).setBackgroundColor(ColorConstants.WHITE)
								.setTextAlignment(TextAlignment.CENTER).setFontSize(9)));
								//.setFixedLayout().setHeight(4).setWidth(200);
				}
				
				////////////////
			
			if (billPdf.getAmmount9()!=0) {
				table.addCell(new Cell()
						.add(new Paragraph(""+billPdf.getAmmount9()).setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
								.setFontColor(ColorConstants.BLACK).setBackgroundColor(ColorConstants.WHITE)
								.setTextAlignment(TextAlignment.CENTER).setFontSize(9)));
								//.setFixedLayout().setHeight(4).setWidth(200);
				
				table.addCell(new Cell()
						.add(new Paragraph(""+billPdf.getDescription9()).setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
								.setFontColor(ColorConstants.BLACK).setBackgroundColor(ColorConstants.WHITE)
								.setTextAlignment(TextAlignment.CENTER).setFontSize(9)));
				
				
				table.addCell(new Cell()
						.add(new Paragraph(""+billPdf.getUnitPrice9()).setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
								.setFontColor(ColorConstants.BLACK).setBackgroundColor(ColorConstants.WHITE)
								.setTextAlignment(TextAlignment.CENTER).setFontSize(9)));
								//.setFixedLayout().setHeight(4).setWidth(200);
				
				table.addCell(new Cell()
						.add(new Paragraph(""+billPdf.getTotal9()).setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
								.setFontColor(ColorConstants.BLACK).setBackgroundColor(ColorConstants.WHITE)
								.setTextAlignment(TextAlignment.CENTER).setFontSize(9)));
								//.setFixedLayout().setHeight(4).setWidth(200);
				}
				
				////////////////
			
			if (billPdf.getAmmount10()!=0) {
				table.addCell(new Cell()
						.add(new Paragraph(""+billPdf.getAmmount10()).setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
								.setFontColor(ColorConstants.BLACK).setBackgroundColor(ColorConstants.WHITE)
								.setTextAlignment(TextAlignment.CENTER).setFontSize(9)));
								//.setFixedLayout().setHeight(4).setWidth(200);
				
				table.addCell(new Cell()
						.add(new Paragraph(""+billPdf.getDescription10()).setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
								.setFontColor(ColorConstants.BLACK).setBackgroundColor(ColorConstants.WHITE)
								.setTextAlignment(TextAlignment.CENTER).setFontSize(9)));
				
				
				table.addCell(new Cell()
						.add(new Paragraph(""+billPdf.getUnitPrice10()).setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
								.setFontColor(ColorConstants.BLACK).setBackgroundColor(ColorConstants.WHITE)
								.setTextAlignment(TextAlignment.CENTER).setFontSize(9)));
								//.setFixedLayout().setHeight(4).setWidth(200);
				
				table.addCell(new Cell()
						.add(new Paragraph(""+billPdf.getTotal10()).setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
								.setFontColor(ColorConstants.BLACK).setBackgroundColor(ColorConstants.WHITE)
								.setTextAlignment(TextAlignment.CENTER).setFontSize(9)));
								//.setFixedLayout().setHeight(4).setWidth(200);
				}
				
				////////////////
			table.addCell(new Cell()
					.add(new Paragraph("").setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
							.setFontColor(ColorConstants.BLACK).setBackgroundColor(ColorConstants.WHITE)
							.setTextAlignment(TextAlignment.CENTER).setFontSize(9)).setBorder(Border.NO_BORDER));
							//.setFixedLayout().setHeight(4).setWidth(200);
			
			table.addCell(new Cell()
					.add(new Paragraph("").setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
							.setFontColor(ColorConstants.BLACK).setBackgroundColor(ColorConstants.WHITE)
							.setTextAlignment(TextAlignment.CENTER).setFontSize(9)).setBorder(Border.NO_BORDER));
							//.setFixedLayout().setHeight(4).setWidth(200);
			
			table.addCell(new Cell()
					.add(new Paragraph("SUBTOTAL: ").setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
							.setFontColor(ColorConstants.BLACK).setBold().setBackgroundColor(ColorConstants.WHITE)
							.setTextAlignment(TextAlignment.CENTER).setFontSize(9)));
							//.setFixedLayout().setHeight(4).setWidth(200);
			
			table.addCell(new Cell()
					.add(new Paragraph(""+billPdf.getSubTotal()).setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
							.setFontColor(ColorConstants.BLACK).setBackgroundColor(ColorConstants.WHITE)
							.setTextAlignment(TextAlignment.CENTER).setFontSize(9)));
			
			table.addCell(new Cell()
					.add(new Paragraph("").setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
							.setFontColor(ColorConstants.BLACK).setBackgroundColor(ColorConstants.WHITE)
							.setTextAlignment(TextAlignment.CENTER).setFontSize(9)).setBorder(Border.NO_BORDER));
							//.setFixedLayout().setHeight(4).setWidth(200);
			
			table.addCell(new Cell()
					.add(new Paragraph("").setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
							.setFontColor(ColorConstants.BLACK).setBackgroundColor(ColorConstants.WHITE)
							.setTextAlignment(TextAlignment.CENTER).setFontSize(9)).setBorder(Border.NO_BORDER));
							//.setFixedLayout().setHeight(4).setWidth(200);
			
			table.addCell(new Cell()
					.add(new Paragraph("VAT: ").setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
							.setFontColor(ColorConstants.BLACK).setBold().setBackgroundColor(ColorConstants.WHITE)
							.setTextAlignment(TextAlignment.CENTER).setFontSize(9)));
							//.setFixedLayout().setHeight(4).setWidth(200);
			
			table.addCell(new Cell()
					.add(new Paragraph(""+billPdf.getVatPercentage()+"% - "+billPdf.getVatAmmount()).setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
							.setFontColor(ColorConstants.BLACK).setBackgroundColor(ColorConstants.WHITE)
							.setTextAlignment(TextAlignment.CENTER).setFontSize(9)));
							//.setFixedLayout().setHeight(4).setWidth(200);
			
			table.addCell(new Cell()
					.add(new Paragraph("").setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
							.setFontColor(ColorConstants.BLACK).setBackgroundColor(ColorConstants.WHITE)
							.setTextAlignment(TextAlignment.CENTER).setFontSize(9)).setBorder(Border.NO_BORDER));
							//.setFixedLayout().setHeight(4).setWidth(200);
			
			table.addCell(new Cell()
					.add(new Paragraph("").setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
							.setFontColor(ColorConstants.BLACK).setBackgroundColor(ColorConstants.WHITE)
							.setTextAlignment(TextAlignment.CENTER).setFontSize(9)).setBorder(Border.NO_BORDER));
							//.setFixedLayout().setHeight(4).setWidth(200);
			
			table.addCell(new Cell()
					.add(new Paragraph("TOTAL BILL: ").setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
							.setFontColor(ColorConstants.BLACK).setBold().setBackgroundColor(ColorConstants.WHITE)
							.setTextAlignment(TextAlignment.CENTER).setFontSize(9)));
							//.setFixedLayout().setHeight(4).setWidth(200);
			
			table.addCell(new Cell()
					.add(new Paragraph(""+billPdf.getTotal()).setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
							.setFontColor(ColorConstants.BLACK).setBackgroundColor(ColorConstants.WHITE)
							.setTextAlignment(TextAlignment.CENTER).setFontSize(9)));
							//.setFixedLayout().setHeight(4).setWidth(200);
			
		
		/*	table.addFooterCell(new Paragraph("TOTAL")).setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
					.setFontColor(ColorConstants.BLACK).setBold().setBackgroundColor(ColorConstants.WHITE)
					.setTextAlignment(TextAlignment.CENTER);
		*/
			
		
		
		document.add(table.setHorizontalAlignment(HorizontalAlignment.CENTER).setFixedLayout().setWidth(550));
	}

	/**
	 * 
	 * @param document
	 * @param billPdf 
	 * @throws IOException
	 * @throws java.io.IOException 
	 */
	public static void addReportFooter(Document document, BillDTO billPdf) throws IOException, java.io.IOException {

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
