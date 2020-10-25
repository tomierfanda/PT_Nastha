package com.tester.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tester.restapi.model.Mahasiswa;

@Repository
public interface MahasiswaRepository extends JpaRepository<Mahasiswa, Long> {
	
	Mahasiswa findOneByIdmhs (long idmhs);

}
