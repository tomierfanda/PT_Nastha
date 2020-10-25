package com.tester.restapi.dto;

import lombok.Data;

@Data
public class NilaiDto {
	private long idmhs;
	private long idmatkul;
	private Integer nilai;
	private String keterangan;

	private String nama;
	private String mataKuliah;
}
