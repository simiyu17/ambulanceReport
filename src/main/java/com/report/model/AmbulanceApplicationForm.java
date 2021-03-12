package com.report.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
public class AmbulanceApplicationForm {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id; 
	private String applicant;
	private String applicationId;
	private String needAmbulanceFor;
	private String comunityMember;
	private String fname;
	private String mname;
	private String lname;
	private String dob;
	private String age;
	private String gender;
	private int loginuser=0;
	private String bloodgroup;
	@Transient
	@OneToMany(mappedBy = "profileId")
	private List<Address> address = new ArrayList();

	private String mobile;
	private String email;
	private String isDisable;
	private String disabilityDetails;
	@Transient
	@OneToMany(mappedBy = "userProfileId")
	private List<HealthInfoHistory> healthInfoHistoryList = new ArrayList();
	
	@Transient
	@OneToMany(mappedBy = "userProfileId")
	private List<CurrentMedicineList> currentMedicineList = new ArrayList();
	private String medicalAllergy;
	private String foodAlergy;
	private String assistance;
	private String instructions;
	@Transient
	private List<ProfileDocuments> docslist = new ArrayList();
	@Transient
	private List profileList = new ArrayList();
	//@Convert(converter = StringListConverter.class)
	@Column(name = "docsSelected")
	@ElementCollection(targetClass=String.class)
	private List<String> docsSelected;

	
	 

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

 

	public List<String> getDocsSelected() {
		return docsSelected;
	}

	public List getProfileList() {
		return profileList;
	}

	public void setProfileList(List profileList) {
		this.profileList = profileList;
	}

	public String getApplicant() {
		return applicant;
	}

	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}

	public String getNeedAmbulanceFor() {
		return needAmbulanceFor;
	}

	public void setNeedAmbulanceFor(String needAmbulanceFor) {
		this.needAmbulanceFor = needAmbulanceFor;
	}

	public String getComunityMember() {
		return comunityMember;
	}

	public void setComunityMember(String comunityMember) {
		this.comunityMember = comunityMember;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBloodgroup() {
		return bloodgroup;
	}

	public void setBloodgroup(String bloodgroup) {
		this.bloodgroup = bloodgroup;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIsDisable() {
		return isDisable;
	}

	public void setIsDisable(String isDisable) {
		this.isDisable = isDisable;
	}

	public String getDisabilityDetails() {
		return disabilityDetails;
	}

	public void setDisabilityDetails(String disabilityDetails) {
		this.disabilityDetails = disabilityDetails;
	}

	public List<HealthInfoHistory> getHealthInfoHistoryList() {
		return healthInfoHistoryList;
	}

	public void setHealthInfoHistoryList(List<HealthInfoHistory> healthInfoHistoryList) {
		this.healthInfoHistoryList = healthInfoHistoryList;
	}

	public List<CurrentMedicineList> getCurrentMedicineList() {
		return currentMedicineList;
	}

	public void setCurrentMedicineList(List<CurrentMedicineList> currentMedicineList) {
		this.currentMedicineList = currentMedicineList;
	}

	public String getMedicalAllergy() {
		return medicalAllergy;
	}

	public void setMedicalAllergy(String medicalAllergy) {
		this.medicalAllergy = medicalAllergy;
	}

	public String getFoodAlergy() {
		return foodAlergy;
	}

	public void setFoodAlergy(String foodAlergy) {
		this.foodAlergy = foodAlergy;
	}

	public String getAssistance() {
		return assistance;
	}

	public void setAssistance(String assistance) {
		this.assistance = assistance;
	}

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	public List<ProfileDocuments> getDocslist() {
		return docslist;
	}

	public void setDocslist(List<ProfileDocuments> docslist) {
		this.docslist = docslist;
	}

	public List<Address> getAddress() {
		return address;
	}

	public void setAddress(List<Address> address) {
		this.address = address;
	}

	public void setDocsSelected(List<String> docsSelected) {
		this.docsSelected = docsSelected;
	}
	
	

}
