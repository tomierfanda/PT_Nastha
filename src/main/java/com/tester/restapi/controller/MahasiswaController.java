package com.tester.restapi.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tester.restapi.dto.ResponseJson;
import com.tester.restapi.enumeration.ResponseCodes;
import com.tester.restapi.model.Mahasiswa;
import com.tester.restapi.repository.MahasiswaRepository;
import com.tester.restapi.repository.MatakuliahRepository;
import com.tester.restapi.service.ExcelService;
import com.tester.restapi.service.MahasiswaService;

@RestController

@RequestMapping("/api/mahasiswa")
public class MahasiswaController {
	@Autowired
	MahasiswaRepository mahasiswaRepository;
	
	@Autowired
	MahasiswaService MahasiswaService;
	
	@Autowired
	MatakuliahRepository matakuliahrepository;
	
	@Autowired
	ExcelService excelService;
	
	   private static Logger logger = LoggerFactory
			      .logger(MahasiswaController.class);
	
	
	@GetMapping("/getAllMhs")
	public  ResponseEntity<ResponseJson> getAllMhs(){
		logger.info("Methode getAllMhs is called");
		
		List<Mahasiswa> mhs = mahasiswaRepository.findAll();
		
		return ResponseEntity.ok(new ResponseJson(ResponseCodes.SUCCESS , mhs));	
	}
	
	    @PostMapping("/import")
	    public ResponseEntity<ResponseJson> getExcelToDB(@RequestParam("file") MultipartFile reapExcelDataFile) 
	    		throws IOException {
	    try {

	        excelService.ReadDataFromExcel(reapExcelDataFile);
	        return ResponseEntity.status(HttpStatus.OK).body(new ResponseJson(ResponseCodes.SUCCESS, "OK"));

	        } catch (Exception e) {
		    	StringWriter sw = new StringWriter();
				PrintWriter pw = new PrintWriter(sw);
				e.printStackTrace(pw);
				String sStackTrace = sw.toString();
		        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseJson(ResponseCodes.OTHER, e.getMessage()));
		      }
	    }

}
