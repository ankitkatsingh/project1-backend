package com.inn.project1.project1.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inn.project1.project1.dao.IMarkDao;
import com.inn.project1.project1.dao.IStudentDao;
import com.inn.project1.project1.models.Marks;
import com.inn.project1.project1.models.Student;
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
	
	public String exportStudent() throws IOException, InvalidFormatException {
		
		File destinationFolder = new File("/home/ist/kavhale/project1/excelFiles/");
		if(!destinationFolder.exists()) {
			destinationFolder.mkdirs();
		}
		
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Students");
		
		CellStyle style = workbook.createCellStyle();
		style.setFillBackgroundColor(IndexedColors.RED.getIndex());
		style.setFillPattern(FillPatternType.BIG_SPOTS);
		
		Row headerRow = sheet.createRow(0);
		
		Cell idCell = headerRow.createCell(0);
		idCell.setCellValue("Id");
		idCell.setCellStyle(style);
		
		Cell nameCell = headerRow.createCell(1);
		nameCell.setCellValue("Name");
		nameCell.setCellStyle(style);
		
		Cell standardCell = headerRow.createCell(2);
		standardCell.setCellValue("Standard");
		standardCell.setCellStyle(style);
		
		Cell rollNoCell = headerRow.createCell(3);
		rollNoCell.setCellValue("Roll No");
		rollNoCell.setCellStyle(style);
		
		Cell genderCell = headerRow.createCell(4);
		genderCell.setCellValue("Gender");
		genderCell.setCellStyle(style);
		
		Iterable<Student> studnetsList = getAllStudents();
		int rowIndex=1;
		for(Student s : studnetsList) {
			Row row = sheet.getRow(rowIndex);
			if(row == null) {
				row = sheet.createRow(rowIndex);
			}
			
			int cellIndex = 0;
			Cell idDataCell = row.createCell(cellIndex);
			idDataCell.setCellValue(s.getId() != null ? s.getId().toString() : "");
			cellIndex++;
			
			Cell nameDataCell = row.createCell(cellIndex);
			nameDataCell.setCellValue(s.getName() != null ? s.getName() : "");
			cellIndex++;
			
			Cell genderDataCell = row.createCell(cellIndex);
			genderDataCell.setCellValue(s.getGender() != null ? s.getGender().toString() : "");
			cellIndex++;
			
			Cell standardDataCell = row.createCell(cellIndex);
			standardDataCell.setCellValue(s.getStandard() != null ? s.getStandard() : "");
			cellIndex++;
			
			Cell rollNoDataCell = row.createCell(cellIndex);
			rollNoDataCell.setCellValue(s.getRollNo() != null ? s.getRollNo().toString() : "" );
		
			rowIndex++;
		}
		
		FileOutputStream fileOutputStream = new FileOutputStream(new File(destinationFolder,"studentList.xls"));
		workbook.write(fileOutputStream);
		fileOutputStream.close();
		
		return "/home/ist/kavhale/project1/excelFiles/studentList.xls";
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
}
