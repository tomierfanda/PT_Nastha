package com.tester.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tester.restapi.model.Nilai;

@Repository
public interface NilaiRepository extends JpaRepository<Nilai, Long> {
	Nilai findOneByIdAndIdmhsAndIdmatkul(long id ,long idmhs, long idmatkul);
	Nilai findOneByIdmhs(long idMhs);
	Nilai findOneById(long id);
	
	@Query(value = "SELECT AVG(nilai) FROM test_nashta.nilai WHERE idmhs = ?1" , nativeQuery = true)long getRatarata(long idmhs);
}
