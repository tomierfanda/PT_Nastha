package com.tester.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tester.restapi.model.Matakuliah;

@Repository
public interface MatakuliahRepository extends JpaRepository<Matakuliah, Long>{
	
	Matakuliah findOneByIdmhs (long idMhs);

}
