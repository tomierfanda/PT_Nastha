package com.tester.restapi.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.tester.restapi.model.Mahasiswa;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class ExcelHelper {
	  public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	  static String[] HEADERs = { "idmhs", "alamat", "nama" };
	  static String SHEET = "mahasiswa";

	  public static boolean hasExcelFormat(MultipartFile file) {

	    if (!TYPE.equals(file.getContentType())) {
	      return false;
	    }

	    return true;
	  }

	  public static List<Mahasiswa> excelToMahasiswa(InputStream is) {
	    try {
	      Workbook workbook = new XSSFWorkbook(is);

	      Sheet sheet = (Sheet) workbook.getSheet(SHEET);
	      Iterator<Row> rows = sheet.iterator();

	      List<Mahasiswa> Mahasiswas = new ArrayList<Mahasiswa>();

	      int rowNumber = 0;
	      while (rows.hasNext()) {
	        Row currentRow = rows.next();

	        // skip header
	        if (rowNumber == 0) {
	          rowNumber++;
	          continue;
	        }

	        Iterator<Cell> cellsInRow = currentRow.iterator();

	        Mahasiswa Mahasiswa = new Mahasiswa();

	        int cellIdx = 0;
	        while (cellsInRow.hasNext()) {
	          Cell currentCell = cellsInRow.next();

	          switch (cellIdx) {
//	          case 0:
//	            tutorial.setId((long) currentCell.getNumericCellValue());
//	            break;

	          case 0:
	        	  Mahasiswa.setIdmhs((long) currentCell.getNumericCellValue());
	            break;	  
	           

	          case 1:
	        	  Mahasiswa.setNama(currentCell.getStringCellValue());
	          	break;
	           
	          case 2:
	        	  Mahasiswa.setAlamat(currentCell.getStringCellValue());
	              break;
	              
	          default:
	            break;
	          }

	          cellIdx++;
	        }

	        Mahasiswas.add(Mahasiswa);
	      }

	      workbook.close();

	      return Mahasiswas;
	    } catch (IOException e) {
	      throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
	    }
	  }
}
