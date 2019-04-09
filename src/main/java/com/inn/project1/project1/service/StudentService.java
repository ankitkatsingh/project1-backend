package com.inn.project1.project1.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inn.project1.project1.dao.IMarkDao;
import com.inn.project1.project1.dao.IStudentDao;
import com.inn.project1.project1.models.Marks;
import com.inn.project1.project1.models.Student;
import com.inn.project1.project1.utils.Project1Constants;
import com.inn.project1.project1.utils.StudentWrapper;

@Service
public class StudentService {
	
	@Autowired
	private IStudentDao iStudentDao;
	
	@Autowired
	private IMarkDao iMarkDao;
	
	public Student createNewStudent(Student student) {
		try {
			return iStudentDao.save(student);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Student> getAllStudents(){
		try {
			return (List<Student>) iStudentDao.findAll();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Optional<Student> findById(Integer id) {
		try {
			return iStudentDao.findById(id);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void deleteStudentById(Integer id) {
		try {
			iStudentDao.deleteById(id);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public StudentWrapper createStudnetWithMarks(StudentWrapper studentWrapper) {
		try {
			StudentWrapper wrapper = new StudentWrapper();
			if(studentWrapper != null) {
				Student student = null;
				Marks marks = null;
				
				if(studentWrapper.getMarks() != null) {
					marks = createMarksForStudnet(studentWrapper.getMarks());
				}
				if(studentWrapper.getStudent() != null) {
					Student student2 = studentWrapper.getStudent();
					if(marks != null) {
						student2.setMarks(marks);
					}
					String genderCode = student2.getGender().toString().substring(0,1);
					String resultCode = student2.getResult().toString().substring(0,1);
					String name = student2.getName().substring(0,3);
					String studentIdentifier = getStudentUniqueIdentifier(genderCode,resultCode,name);
					student2.setStudentIdentifier(studentIdentifier);
					student = createNewStudent(student2);
				}
				
				wrapper.setStudent(student);
				wrapper.setMarks(marks);
			}
			return wrapper;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Marks createMarksForStudnet(Marks marks) {
		try {
			return iMarkDao.save(marks);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Object[]> getStudentGradesByGroup(){
		try {
			return iStudentDao.getStudentGradesByGroup();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String getStudentUniqueIdentifier(String genderCode,String resultCode,String name) {
		try {
			System.out.println("Name==="+name+"=====Gender Code===="+genderCode+"===resultcode===="+resultCode);
			BigInteger studentUniqueIdentifier = iStudentDao.maxStudentIdentifier(genderCode, resultCode);
			System.out.println("studentUniqueIdentifier123==="+studentUniqueIdentifier);
			if(studentUniqueIdentifier == null) {
				Integer count = iStudentDao.insertStudentIdentifier(genderCode, resultCode);
				studentUniqueIdentifier = BigInteger.valueOf(count);
			}else {
				iStudentDao.updateStudentIdentifier(studentUniqueIdentifier.toString(), genderCode, resultCode);
			}
			String number = String.format("%05d" , studentUniqueIdentifier);
			String finalStudentIdentifier = name + "_" + genderCode + "_" + resultCode + "_" + number;
			return finalStudentIdentifier;
		}catch(Exception e) {
			
		}
		return null;
	}
	
	public String exportStudentListWithMarks() {
		try {
			String fileName = "studentListExport.xlsx";
			
			String sourceFilePath = "/home/ist/Documents/tomcat_8/apache-tomcat-8.5.31/webapps/TimePass/importSamples/" + Project1Constants.STUDENT_EXPORT_SAMPLE_SHEET;
			FileInputStream fileInputStream = new FileInputStream(new File(sourceFilePath));
			XSSFWorkbook xssfWorkbook = new XSSFWorkbook(fileInputStream);
			//SXSSFWorkbook sxssfWorkbook = new SXSSFWorkbook(xssfWorkbook);
			//sxssfWorkbook.setCompressTempFiles(true);
			//SXSSFSheet sheet = sxssfWorkbook.getSheetAt(0);
			
			XSSFSheet sheet = xssfWorkbook.getSheetAt(0);
			List<Student> students = this.getAllStudents();
			
			if(CollectionUtils.isNotEmpty(students)) {
				int rowIndex = 1;
				for(Student s : students) {
					Row row = sheet.getRow(rowIndex);
					if(row == null) {
						row = sheet.createRow(rowIndex);
					}
					int columnIndex = 0;
					row.createCell(columnIndex++).setCellValue(s.getStudentIdentifier() != null ? s.getStudentIdentifier() : "");
					row.createCell(columnIndex++).setCellValue(s.getName() != null ? s.getName() : "");
					row.createCell(columnIndex++).setCellValue(s.getStandard() != null ? s.getStandard() : "");
					row.createCell(columnIndex++).setCellValue(s.getRollNo() != null ? s.getRollNo().toString() : "");
					row.createCell(columnIndex++).setCellValue(s.getGender() != null ? s.getGender().toString() : "");
					
					if(s.getMarks() != null) {
						row.createCell(columnIndex++).setCellValue(s.getMarks().getHindiMark() != null ? s.getMarks().getHindiMark().toString() : "");
						row.createCell(columnIndex++).setCellValue(s.getMarks().getEnglishMark() != null ? s.getMarks().getEnglishMark().toString() : "");
						row.createCell(columnIndex++).setCellValue(s.getMarks().getMathMark() != null ? s.getMarks().getMathMark().toString() : "");
						row.createCell(columnIndex++).setCellValue(s.getMarks().getScienceMark() != null ? s.getMarks().getScienceMark().toString() : "");
						row.createCell(columnIndex++).setCellValue(s.getMarks().getSocialScienceMark() != null ? s.getMarks().getSocialScienceMark().toString() : "");
						row.createCell(columnIndex++).setCellValue(s.getMarks().getTotalMark() != null ? s.getMarks().getTotalMark().toString() : "");
						row.createCell(columnIndex++).setCellValue(s.getMarks().getPercentage() != null ? s.getMarks().getPercentage().toString() : "");
					}else {
						row.createCell(columnIndex++).setCellValue("");
						row.createCell(columnIndex++).setCellValue("");
						row.createCell(columnIndex++).setCellValue("");
						row.createCell(columnIndex++).setCellValue("");
						row.createCell(columnIndex++).setCellValue("");
						row.createCell(columnIndex++).setCellValue("");
						row.createCell(columnIndex++).setCellValue("");
					}
					
					row.createCell(columnIndex++).setCellValue(s.getResult() != null ? s.getResult().toString() : "");
					row.createCell(columnIndex++).setCellValue(s.getGrades() != null ? s.getGrades().toString() : "");
					
					rowIndex++;
				}
			}
			
			String destinationFileFolder = "/home/ist/kavhale/downloads/";
			File destinationFile = new File(destinationFileFolder);
			if(!destinationFile.exists()) {
				destinationFile.mkdirs();
			}
			FileOutputStream fileOutputStream = new FileOutputStream(new File(destinationFileFolder + fileName));
			xssfWorkbook.write(fileOutputStream);
			fileInputStream.close();
			fileInputStream.close();
			
			return destinationFileFolder + fileName;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
