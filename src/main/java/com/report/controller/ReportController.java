/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.report.controller;

import com.itextpdf.text.DocumentException;
import com.report.model.Address;
import com.report.model.AmbulanceApplicationForm;
import com.report.report.AmbulanceReport;
import java.io.IOException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author simiyu
 */
@Controller
public class ReportController {
    
    @GetMapping("/")
    public String goToHome(){
        return "index";
    }
    
    @GetMapping("/sample_report/{applicationId}")
    public void getSampleRepoert(HttpServletResponse response, @PathVariable("applicationId") Long applicationId){
        try {
            
            // Application
            AmbulanceApplicationForm form = new AmbulanceApplicationForm();
            form.setAge("34");
            form.setApplicant("Applicant 1");
            form.setApplicationId("AppId232");
            form.setAssistance("Assistance Text");
            form.setBloodgroup("B+");
            form.setComunityMember("Yes");
            form.setDob("1999-12-02");
            form.setEmail("applicant1@gmail.com");
            form.setFname("John");
            form.setFoodAlergy("Food Alergy Text");
            form.setGender("Male");
            form.setInstructions("Instruction Text");
            form.setIsDisable("Yes");
            form.setDisabilityDetails("Disability Details");
            form.setLname("Simpson");
            form.setMedicalAllergy("Medical Alergy Text");
            form.setMname("M");
            form.setMobile("7646464644747");
            form.setNeedAmbulanceFor("Need for Ambulance Text");
            
            //Applicant Address
            Address address = new Address("House No 1", "K Street", "Nairobi", "Eastern District", "Masho", "00200", "Kenya");
            form.getAddress().add(address);
            
            AmbulanceReport r = new AmbulanceReport(form);

            byte[] bytes = r.createPdf();

            response.setContentType("application/pdf");
            response.setContentLength(bytes.length);
            response.setHeader("Content-Disposition", "attachment; filename=\"" + form.getFname()+" "+form.getLname() + "\'s Application\"");
            try (ServletOutputStream outStream = response.getOutputStream()) {
                outStream.write(bytes, 0, bytes.length);
                outStream.flush();
            }


        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }
}
