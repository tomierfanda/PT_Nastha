package com.tester.restapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tester.restapi.model.Mahasiswa;
import com.tester.restapi.repository.MahasiswaRepository;

@Service
public class MahasiswaServiceImpl implements MahasiswaService {

	@Autowired
	private MahasiswaRepository MahasiswaRepo;
	
	@Override
	public Mahasiswa save(Mahasiswa Mahasiswa) {
		return MahasiswaRepo.save(Mahasiswa);
	}
	
}
