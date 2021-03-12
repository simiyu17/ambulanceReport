/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.report.report;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.lowagie.text.pdf.PdfCell;
import com.report.model.Address;
import com.report.model.AmbulanceApplicationForm;
import com.report.model.CurrentMedicineList;
import com.report.model.HealthInfoHistory;
import com.report.model.ProfileDocuments;
import com.report.util.ReportUtil;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 *
 * @author simiyu
 */
public class AmbulanceReport {

    public Document document;
    public float width, height, middle;
    protected float mLeft, mRight, mTop, mBottom;

    private AmbulanceApplicationForm applicationForm;

    public AmbulanceReport() {
    }

    public AmbulanceReport(AmbulanceApplicationForm form) {
        document = new Document(PageSize.A4);
        Rectangle rec = document.getPageSize();
        document.setMargins(20, 20, 20, 20);
        width = rec.getWidth();
        height = rec.getHeight();
        mLeft = document.leftMargin();
        mRight = document.rightMargin();
        mTop = document.topMargin();
        mBottom = document.bottomMargin();
        middle = width / 2 + mLeft + mRight;
        setApplicationForm(form);
    }

    public byte[] createPdf() throws IOException, DocumentException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter writer = PdfWriter.getInstance(document, baos);
        document.open();

        PdfPTable table = new PdfPTable(new float[]{middle});
        table.setWidthPercentage(100f);

        // add header and footer
        table.addCell(ReportUtil.getReportHeaderCell());

        // add title
        table.addCell(ReportUtil.getReportTitleCell("Ambulance Service Request form", ReportUtil.title, PdfCell.ALIGN_CENTER, 25));
        table.addCell(ReportUtil.getBlankCell());

        //Personal Data
        table.addCell(buildPersonalDataTable(getApplicationForm()));

        //Health Condition Data
        table.addCell(buildHealthConditionDataTable(getApplicationForm()));

        //Medicine List Data
        table.addCell(buildMedicineListDataTable(getApplicationForm()));

        //Alergies Data
        table.addCell(buildAlergiesDataTable(getApplicationForm()));

        //Documents Data
        table.addCell(buildDocsDataTable(getApplicationForm()));

        //Notes Data
        table.addCell(buildNotesDataTable(getApplicationForm()));

        document.add(table);
        document.close();

        return baos.toByteArray();
    }

    private PdfPCell buildPersonalDataTable(AmbulanceApplicationForm appForm) {
        PdfPTable table = new PdfPTable(new float[]{1});
        
        //Address
        Address address = appForm.getAddress().size() < 1 ? null : appForm.getAddress().get(0);
                

        PdfPTable sectionOne = new PdfPTable(new float[]{1, 1, 1});

        ReportUtil.addValues(sectionOne, Element.ALIGN_LEFT, false, 1, "Need  Ambulance Service for (*):  ");
        ReportUtil.addValuesUnderlined(sectionOne, Element.ALIGN_LEFT, false, 2, appForm.getNeedAmbulanceFor() == null ? "" : appForm.getNeedAmbulanceFor());
        //ReportUtil.addValues(sectionOne, Element.ALIGN_LEFT, false, "");

        ReportUtil.addValues(sectionOne, Element.ALIGN_LEFT, false, 1, "Are you member of this Community  (*): ");
        ReportUtil.addValuesUnderlined(sectionOne, Element.ALIGN_LEFT, false, 2, appForm.getComunityMember() == null ? "NO" : appForm.getComunityMember());
        //ReportUtil.addValues(sectionOne, Element.ALIGN_LEFT, false, "");

        ReportUtil.addValues(sectionOne, Element.ALIGN_LEFT, false, 1, "First Name*: " + (appForm.getFname() == null ? "" : appForm.getFname()));
        ReportUtil.addValues(sectionOne, Element.ALIGN_LEFT, false, 1, "Middle Name: " + (appForm.getMname() == null ? "" : appForm.getMname()));
        ReportUtil.addValues(sectionOne, Element.ALIGN_LEFT, false, 1, "Last Name*: " + (appForm.getLname() == null ? "" : appForm.getLname()));

        ReportUtil.addValues(sectionOne, Element.ALIGN_LEFT, false, 1, "DOB(BS)*: " + (appForm.getDob() == null ? "" : appForm.getDob()));
        ReportUtil.addValues(sectionOne, Element.ALIGN_LEFT, false, 2, "DOB (AD)*: " + (appForm.getDob() == null ? "" : appForm.getDob()));
        //ReportUtil.addValues(sectionOne, Element.ALIGN_LEFT, false, "");

        ReportUtil.addValues(sectionOne, Element.ALIGN_LEFT, false, 1, "Age*: " + (appForm.getAge() == null ? "" : appForm.getAge()));
        ReportUtil.addValues(sectionOne, Element.ALIGN_LEFT, false, 1, "Gender: " + (appForm.getGender() == null ? "" : appForm.getGender()));
        ReportUtil.addValues(sectionOne, Element.ALIGN_LEFT, false, 1, "Blood Group*: " + (appForm.getBloodgroup() == null ? "" : appForm.getBloodgroup()));

        PdfPCell cellSecOne = new PdfPCell(sectionOne);
        table.addCell(cellSecOne);

        //Add Space
        table.addCell(ReportUtil.getBlankCell());

        table.addCell(ReportUtil.getReportTitleCell("Permanent Address:", FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLDITALIC), Element.ALIGN_LEFT, 20));

        PdfPTable sectionTwo = new PdfPTable(new float[]{1, 1, 1});

        ReportUtil.addValues(sectionTwo, Element.ALIGN_LEFT, false, 1, "House No: " + (address == null ? "" : address.getHouseNo()));
        ReportUtil.addValues(sectionTwo, Element.ALIGN_LEFT, false, 1, "Street Name: " + (address == null ? "" : address.getStreetName()));
        ReportUtil.addValues(sectionTwo, Element.ALIGN_LEFT, false, 1, "City: " + (address == null ? "" : address.getCity()));

        ReportUtil.addValues(sectionTwo, Element.ALIGN_LEFT, false, 1, "District: " + (address == null ? "" : address.getDistrict()));
        ReportUtil.addValues(sectionTwo, Element.ALIGN_LEFT, false, 1, "Provence/State: " + (address == null ? "" : address.getProvince()));
        ReportUtil.addValues(sectionTwo, Element.ALIGN_LEFT, false, 1, "ZIP/Post code.: " + (address == null ? "" : address.getZip()));

        ReportUtil.addValues(sectionTwo, Element.ALIGN_LEFT, false, 3, "Country: " + (address == null ? "" : address.getCountry()));

        PdfPCell cellSecTwo = new PdfPCell(sectionTwo);
        table.addCell(cellSecTwo);

        //Add Space
        table.addCell(ReportUtil.getBlankCell());
        table.addCell(ReportUtil.getReportTitleCell("Pick-up address*: (will be same  permanent Address by default )", FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLDITALIC), Element.ALIGN_LEFT, 20));

        PdfPTable sectionThree = new PdfPTable(new float[]{1, 1, 1});

        ReportUtil.addValues(sectionThree, Element.ALIGN_LEFT, false, 1, "House No: " + (address == null ? "" : address.getHouseNo()));
        ReportUtil.addValues(sectionThree, Element.ALIGN_LEFT, false, 1, "Street Name: " + (address == null ? "" : address.getStreetName()));
        ReportUtil.addValues(sectionThree, Element.ALIGN_LEFT, false, 1, "City: " + (address == null ? "" : address.getCity()));

        ReportUtil.addValues(sectionThree, Element.ALIGN_LEFT, false, 1, "District: " + (address == null ? "" : address.getDistrict()));
        ReportUtil.addValues(sectionThree, Element.ALIGN_LEFT, false, 1, "Provence/State: " + (address == null ? "" : address.getProvince()));
        ReportUtil.addValues(sectionThree, Element.ALIGN_LEFT, false, 1, "ZIP/Post code: " + (address == null ? "" : address.getZip()));

        ReportUtil.addValues(sectionThree, Element.ALIGN_LEFT, false, 1, "Country: " + (address == null ? "" : address.getCountry()));
        ReportUtil.addValues(sectionThree, Element.ALIGN_LEFT, false, 1, "Phone/Mobile*: " + (appForm.getMobile() == null ? "" : appForm.getMobile()));
        ReportUtil.addValues(sectionThree, Element.ALIGN_LEFT, false, 1, "Email: " + (appForm.getEmail() == null ? "" : appForm.getEmail()));

        PdfPCell cellSecThree = new PdfPCell(sectionThree);
        table.addCell(cellSecThree);

        //Add Space
        table.addCell(ReportUtil.getBlankCell());

        if (appForm.getIsDisable().equalsIgnoreCase("Yes")) {
            PdfPTable disableTable = new PdfPTable(new float[]{1});
            // add text
            PdfPCell disability = new PdfPCell();
            disability.setPaddingBottom(5);
            disability.setBorder(Rectangle.NO_BORDER);
            disability.addElement(new Phrase("Disability (if any)*: ", new Font(Font.FontFamily.HELVETICA, 12)));
            disability.addElement(new Phrase(" ", new Font(Font.FontFamily.HELVETICA, 12)));
            disability.addElement(new Phrase(appForm.getDisabilityDetails(), ReportUtil.underlined));
            disableTable.addCell(disability);

            PdfPCell disableCell = new PdfPCell(disableTable);
            disableCell.setBorderColor(BaseColor.LIGHT_GRAY);

            table.addCell(disableCell);
        }

        //Add Space
        table.addCell(ReportUtil.getBlankCell());

        PdfPCell cell = new PdfPCell(table);
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setPaddingTop(10);
        cell.setColspan(2);
        return cell;
    }

    private PdfPCell buildHealthConditionDataTable(AmbulanceApplicationForm appForm) {
        PdfPTable table = new PdfPTable(new float[]{1});

        table.addCell(ReportUtil.getReportTitleCell("Medical condition :", FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLDITALIC), Element.ALIGN_LEFT, 20));
        PdfPTable medicalCondtion = new PdfPTable(new float[]{1, 7});

        java.util.List<HealthInfoHistory> healthconds = appForm.getHealthInfoHistoryList();
        ReportUtil.addValues(medicalCondtion, Element.ALIGN_LEFT, true, 1, "Sn");
        ReportUtil.addValues(medicalCondtion, Element.ALIGN_LEFT, true, 1, "Condition");
        healthconds.forEach(c -> {
            ReportUtil.addValues(medicalCondtion, Element.ALIGN_LEFT, true, 1, c.getSn());
            ReportUtil.addValues(medicalCondtion, Element.ALIGN_LEFT, true, 1, c.getCondition());
        });

        PdfPCell cellSecMedCond = new PdfPCell(medicalCondtion);
        table.addCell(cellSecMedCond);

        //Add Space
        table.addCell(ReportUtil.getBlankCell());

        PdfPCell cell = new PdfPCell(table);
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setPaddingTop(10);
        cell.setColspan(2);
        return cell;
    }

    private PdfPCell buildMedicineListDataTable(AmbulanceApplicationForm appForm) {
        PdfPTable table = new PdfPTable(new float[]{1});

        table.addCell(ReportUtil.getReportTitleCell("Current Medicine List :", FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLDITALIC), Element.ALIGN_LEFT, 20));
        PdfPTable medicineList = new PdfPTable(new float[]{1, 3, 3, 3});

        java.util.List<CurrentMedicineList> medList = appForm.getCurrentMedicineList();
        ReportUtil.addValues(medicineList, Element.ALIGN_LEFT, true, 1, "Sn");
        ReportUtil.addValues(medicineList, Element.ALIGN_LEFT, true, 1, "Medicine List");
        ReportUtil.addValues(medicineList, Element.ALIGN_LEFT, true, 1, "Used for");
        ReportUtil.addValues(medicineList, Element.ALIGN_LEFT, true, 1, "Unit");
        medList.forEach(m -> {
            ReportUtil.addValues(medicineList, Element.ALIGN_LEFT, true, 1, m.getSn());
            ReportUtil.addValues(medicineList, Element.ALIGN_LEFT, true, 1, m.getMedicineList());
            ReportUtil.addValues(medicineList, Element.ALIGN_LEFT, true, 1, m.getMedicineUse());
            ReportUtil.addValues(medicineList, Element.ALIGN_LEFT, true, 1, m.getUnit());
        });

        PdfPCell cellSecMedList = new PdfPCell(medicineList);
        table.addCell(cellSecMedList);

        //Add Space
        table.addCell(ReportUtil.getBlankCell());

        PdfPCell cell = new PdfPCell(table);
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setPaddingTop(10);
        cell.setColspan(2);
        return cell;
    }

    private PdfPCell buildAlergiesDataTable(AmbulanceApplicationForm appForm) {
        PdfPTable table = new PdfPTable(new float[]{1});

        PdfPTable alergyTable = new PdfPTable(new float[]{1});
        // add text
        PdfPCell alergyText = new PdfPCell();
        alergyText.setPaddingBottom(5);
        alergyText.setBorder(Rectangle.NO_BORDER);
        alergyText.addElement(new Phrase("Medicine Allergy : ", new Font(Font.FontFamily.HELVETICA, 12)));
        alergyText.addElement(new Phrase((appForm.getMedicalAllergy() == null ? "-" : appForm.getMedicalAllergy()), ReportUtil.underlined));
        alergyText.addElement(new Phrase("Food Allergy : ", new Font(Font.FontFamily.HELVETICA, 12)));
        alergyText.addElement(new Phrase((appForm.getFoodAlergy() == null ? "-" : appForm.getFoodAlergy()), ReportUtil.underlined));
        alergyText.addElement(new Phrase(" ", new Font(Font.FontFamily.HELVETICA, 12)));
        alergyText.addElement(new Phrase("If  you need assistant for this , please provide details: ", new Font(Font.FontFamily.HELVETICA, 12)));
        alergyText.addElement(new Phrase((appForm.getAssistance() == null ? "-" : appForm.getAssistance()), ReportUtil.underlined));
        alergyText.addElement(new Phrase(" ", new Font(Font.FontFamily.HELVETICA, 12)));
        alergyText.addElement(new Phrase("Message/Instruction: ", new Font(Font.FontFamily.HELVETICA, 12)));
        alergyText.addElement(new Phrase((appForm.getInstructions() == null ? "-" : appForm.getInstructions()), ReportUtil.underlined));
        alergyTable.addCell(alergyText);

        PdfPCell alergyCell = new PdfPCell(alergyTable);
        alergyCell.setBorderColor(BaseColor.LIGHT_GRAY);
        table.addCell(alergyCell);

        //Add Space
        table.addCell(ReportUtil.getBlankCell());

        PdfPCell cell = new PdfPCell(table);
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setPaddingTop(10);
        cell.setColspan(2);
        return cell;
    }

    private PdfPCell buildDocsDataTable(AmbulanceApplicationForm appForm) {
        PdfPTable table = new PdfPTable(new float[]{1});

        table.addCell(ReportUtil.getReportTitleCell("Select the documents you would like to include with your service request.", FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLDITALIC), Element.ALIGN_LEFT, 20));
        PdfPTable docs = new PdfPTable(new float[]{1, 7});

        java.util.List<ProfileDocuments> profDocs = appForm.getDocslist();
        ReportUtil.addValues(docs, Element.ALIGN_LEFT, true, 1, "No#");
        ReportUtil.addValues(docs, Element.ALIGN_LEFT, true, 1, "Name");
        profDocs.forEach(d -> {
            ReportUtil.addValues(docs, Element.ALIGN_LEFT, true, 1, d.getdNo());
            ReportUtil.addValues(docs, Element.ALIGN_LEFT, true, 1, d.getName());
        });

        PdfPCell cellDocs = new PdfPCell(docs);
        table.addCell(cellDocs);

        //Add Space
        table.addCell(ReportUtil.getBlankCell());

        PdfPCell cell = new PdfPCell(table);
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setPaddingTop(10);
        cell.setColspan(2);
        return cell;
    }

    private PdfPCell buildNotesDataTable(AmbulanceApplicationForm appForm) {
        PdfPTable table = new PdfPTable(new float[]{1});

        PdfPTable notesTable = new PdfPTable(new float[]{1});
        // add text
        PdfPCell notesText = new PdfPCell();
        notesText.setPaddingBottom(5);
        notesText.setBorder(Rectangle.NO_BORDER);
        notesText.addElement(new Phrase("Note for all Service request please keep the Preview Button on the top of the page which will be view all the time:", new Font(Font.FontFamily.HELVETICA, 12)));
        notesText.addElement(new Phrase("Notes Text Samples", ReportUtil.underlined));
        notesTable.addCell(notesText);

        PdfPCell notesCell = new PdfPCell(notesTable);
        notesCell.setBorderColor(BaseColor.LIGHT_GRAY);
        table.addCell(notesCell);

        PdfPCell cell = new PdfPCell(table);
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setPaddingTop(10);
        cell.setColspan(2);
        return cell;
    }

    /**
     * @return the applicationForm
     */
    public AmbulanceApplicationForm getApplicationForm() {
        return applicationForm;
    }

    /**
     * @param applicationForm the applicationForm to set
     */
    public void setApplicationForm(AmbulanceApplicationForm applicationForm) {
        this.applicationForm = applicationForm;
    }

}
