package com.tester.restapi.service;

import org.springframework.stereotype.Service;

import com.tester.restapi.dto.NilaiDto;
import com.tester.restapi.model.Nilai;

@Service
public class NilaiService {
	public NilaiDto transformToDto(Nilai Nilai) {
		NilaiDto NilaiDto = new NilaiDto();
		
		NilaiDto.setIdmatkul(Nilai.getIdmatkul());
		NilaiDto.setIdmhs(Nilai.getIdmhs());
		NilaiDto.setNilai(Nilai.getNilai());
		NilaiDto.setKeterangan(Nilai.getKeterangan());
		NilaiDto.setNama(Nilai.getMhs().getNama());
		NilaiDto.setMataKuliah(Nilai.getMatkul().getMataKuliah());
		return NilaiDto;
	}
}
