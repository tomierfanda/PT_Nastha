package com.tester.restapi.service;

import java.io.IOException;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.tester.restapi.model.Mahasiswa;
import com.tester.restapi.repository.MahasiswaRepository;

@Service
public class ExcelService {
	  @Autowired
	  MahasiswaRepository repository;
	  
	   private static Logger logger = LoggerFactory
			      .logger(ExcelService.class);

	  public void save(MultipartFile file) {
	    try {
	      List<Mahasiswa> Mahasiswas = ExcelHelper.excelToMahasiswa(file.getInputStream());
	      repository.saveAll(Mahasiswas);
	    } catch (IOException e) {
	      throw new RuntimeException("fail to store excel data: " + e.getMessage());
	    }
	  }

	  public List<Mahasiswa> getAllMahasiswas() {
	    return (List<Mahasiswa>) repository.findAll();
	  }
	  
	  public List<Mahasiswa> ReadDataFromExcel(MultipartFile excelPath) throws EncryptedDocumentException, 
	  	InvalidFormatException, IOException
	  {
		  	XSSFWorkbook workbook = new XSSFWorkbook(excelPath.getInputStream());
		  	XSSFSheet ws = workbook.getSheetAt(0);
		  	
		  	
			// Retrieving the number of sheets in the Workbook
//		  	 System.out.println("Retrieving Sheets using for-each loop " +she);
//		  	 
//		  	XSSFCell n1Cell = row.getCell(0);
//		  	if ( n1Cell == null ){
//		  	    continue;
//		  	}
	        System.out.println("Workbook has " + workbook.getNumberOfSheets() + " Sheets : ");
	        System.out.println("Retrieving Sheets using for-each loop ");
	        for(Sheet sheet: workbook) {
	            System.out.println("=> " + sheet.getSheetName());

	            for (Row row: sheet) {
	            	 
	                System.out.println(row.getCell(0).getNumericCellValue());
	                System.out.println(row.getCell(1).getStringCellValue());
	                System.out.println(row.getCell(2).getStringCellValue());
	            	
	            	double idmhs = row.getCell(0).getNumericCellValue();
	            	String nama = row.getCell(1).getStringCellValue();
	            	String alamat = row.getCell(2).getStringCellValue();
	            	
	            	  
	            	Mahasiswa mhs = new Mahasiswa();
	            	mhs.setIdmhs((long)idmhs);
	            	mhs.setNama(nama);
	            	mhs.setAlamat(alamat);
	            	
	            	
	            	repository.save(mhs);
	            	
	                System.out.println(row.getCell(0).getNumericCellValue());
	                System.out.println(row.getCell(1).getStringCellValue());
	                System.out.println(row.getCell(2).getStringCellValue());
	              
	            }
	        }
			return null;
	  }
}


