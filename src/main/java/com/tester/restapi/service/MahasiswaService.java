package com.tester.restapi.service;

import org.springframework.stereotype.Service;

import com.tester.restapi.dto.MahasiswaDto;
import com.tester.restapi.model.Mahasiswa;

@Service
public interface MahasiswaService {
	public Mahasiswa save(Mahasiswa Mahasiswa);
	
}
