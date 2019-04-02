package com.inn.project1.project1.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="marks")
public class Marks {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="hindi_mark")
	private Integer hindiMark;
	
	@Column(name="english_mark")
	private Integer englishMark;
	
	@Column(name="math_mark")
	private Integer mathMark;
	
	@Column(name="science_mark")
	private Integer scienceMark;
	
	@Column(name="social_science_mark")
	private Integer socialScienceMark;
	
	@Column(name="total_mark")
	private Integer totalMark;
	
	@Column(name="percentage")
	private Double percentage;
	
	public Double getPercentage() {
		return percentage;
	}

	public void setPercentage(Double percentage) {
		this.percentage = percentage;
	}

	public Integer getTotalMark() {
		return totalMark;
	}

	public void setTotalMark(Integer totalMark) {
		this.totalMark = totalMark;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getHindiMark() {
		return hindiMark;
	}

	public void setHindiMark(Integer hindiMark) {
		this.hindiMark = hindiMark;
	}

	public Integer getEnglishMark() {
		return englishMark;
	}

	public void setEnglishMark(Integer englishMark) {
		this.englishMark = englishMark;
	}

	public Integer getMathMark() {
		return mathMark;
	}

	public void setMathMark(Integer mathMark) {
		this.mathMark = mathMark;
	}

	public Integer getScienceMark() {
		return scienceMark;
	}

	public void setScienceMark(Integer scienceMark) {
		this.scienceMark = scienceMark;
	}

	public Integer getSocialScienceMark() {
		return socialScienceMark;
	}

	public void setSocialScienceMark(Integer socialScienceMark) {
		this.socialScienceMark = socialScienceMark;
	}

	@Override
	public String toString() {
		return "Marks [id=" + id + ", hindiMark=" + hindiMark + ", englishMark=" + englishMark + ", mathMark="
				+ mathMark + ", scienceMark=" + scienceMark + ", socialScienceMark=" + socialScienceMark
				+ ", totalMark=" + totalMark + "]";
	}

	

	
}
