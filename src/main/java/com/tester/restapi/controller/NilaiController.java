package com.tester.restapi.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tester.restapi.dto.NilaiDto;
import com.tester.restapi.dto.ResponseJson;
import com.tester.restapi.enumeration.ResponseCodes;
import com.tester.restapi.exception.NotFoundException;
import com.tester.restapi.model.Mahasiswa;
import com.tester.restapi.model.Nilai;
import com.tester.restapi.repository.MahasiswaRepository;
import com.tester.restapi.repository.NilaiRepository;
import com.tester.restapi.service.NilaiService;

@RestController

@RequestMapping("/api/nilai")
public class NilaiController {
	@Autowired
	private NilaiRepository nilaiRepository;
	
	@Autowired
	private MahasiswaRepository mahasiswaRepository;
	
	@Autowired
	private NilaiService NilaiService;


	@GetMapping("/getAllNilai")
	public ResponseEntity<ResponseJson>  getAllNilai() {
		List<Nilai> nilai = nilaiRepository.findAll();
		return ResponseEntity.ok(new ResponseJson(ResponseCodes.SUCCESS, nilai));
	}
	
	//GET
	@RequestMapping(value = "/{id}/{idmhs}/{idmatkul}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<ResponseJson>getByData(
			@PathVariable(name="id", required=true, value="") long Id,
			@PathVariable(name="idmhs", required=true, value="")long Idmhs,
			@PathVariable(name="idmatkul", required=true, value="") long Idmatkul)
	{
		try {
			
			Nilai Nilai = nilaiRepository.findOneByIdAndIdmhsAndIdmatkul(Id, Idmhs, Idmatkul);
			if(Nilai!=null) {
				NilaiDto NilaiDto = NilaiService.transformToDto(Nilai);
				return ResponseEntity.ok(new ResponseJson(ResponseCodes.SUCCESS, NilaiDto));
			}else {
				return ResponseEntity.ok(new ResponseJson(ResponseCodes.NOTFOUND, "Data Not Found"));
		} 
		}catch (Exception e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			String sStackTrace = sw.toString();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseJson(ResponseCodes.OTHER, e.getMessage()));
			}
	}
	
	@RequestMapping(value = "rata-rata/{idmhs}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<ResponseJson>getRata2(
			@PathVariable(name="idmhs", required=true, value="") long Idmhs	)
	{
		try {
//			public double getRatarata(Idmhs)
			Mahasiswa mhs = mahasiswaRepository.findOneByIdmhs(Idmhs);
			if(mhs != null) {
				
				long nilaiRatarata =  nilaiRepository.getRatarata(Idmhs);
				Map<String, Long> map  = new  HashMap<>();
				map.put("rata_rata" , nilaiRatarata);
					return ResponseEntity.ok(new ResponseJson(ResponseCodes.SUCCESS, map));
				
			}else {
				return ResponseEntity.ok(new ResponseJson(ResponseCodes.NOTFOUND, "Data Not Found"));
					} 
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			String sStackTrace = sw.toString();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseJson(ResponseCodes.OTHER, e.getMessage()));
			}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Nilai> getById(@PathVariable(value = "id") Long courseId)
			throws NotFoundException {
		Nilai course = nilaiRepository.findById(courseId)
				.orElseThrow(() -> new NotFoundException("Course not found for this id :: " + courseId));
		return ResponseEntity.ok().body(course);
	}
	
	//POST
	@PostMapping("/createNilai")
	public ResponseEntity<ResponseJson>  createNilai(@Validated @RequestBody NilaiDto nilaiInput) {
		Nilai nilai = new Nilai();
		nilai.setIdmatkul(nilaiInput.getIdmatkul());
		nilai.setIdmhs(nilaiInput.getIdmhs());
		nilai.setNilai(nilaiInput.getNilai());
		nilai.setKeterangan(nilaiInput.getKeterangan());
		nilaiRepository.save(nilai);
		
		return ResponseEntity.ok(new ResponseJson(ResponseCodes.SUCCESS, nilai));
	}

	//PUT
	@PutMapping("/{id}")
	public ResponseEntity<Nilai> putNilai(@PathVariable(value = "id") Long courseId,
			@Validated @RequestBody Nilai courseDetails) throws NotFoundException {
		Nilai course = nilaiRepository.findById(courseId)
				.orElseThrow(() -> new NotFoundException("Course not found for this id :: " + courseId));
		
		course.setIdmhs(courseDetails.getIdmhs());
		course.setIdmatkul(courseDetails.getIdmatkul());
		course.setNilai(courseDetails.getNilai());
		course.setKeterangan(courseDetails.getKeterangan());
		final Nilai updatedCourse = nilaiRepository.save(course);
		return ResponseEntity.ok(updatedCourse);
	}

	//DELETE
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseJson> deleteNilai(@PathVariable(value = "id") Long id)
			throws NotFoundException {
		Nilai nilai = nilaiRepository.findOneById(id);
		Map<String, Boolean> response = new HashMap<>();
		
		if(nilai != null) {
			nilaiRepository.delete(nilai);
			response.put("deleted", Boolean.TRUE);
			return ResponseEntity.ok(new ResponseJson(ResponseCodes.SUCCESS , response));
		} else {
			return ResponseEntity.ok(new ResponseJson(ResponseCodes.NOTFOUND, "Data Not Found"));
			
		}
		
	}
	
	
}
