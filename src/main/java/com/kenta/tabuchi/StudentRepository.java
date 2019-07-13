package com.kenta.tabuchi;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
		
	public List<Student> findAllById(Long id);
	public List<Student> findAllByNameLike(String name);
	public List<Student> findAllByRomaLike(String name);
	public List<Student> findAllByBirthday(String birthday);
	public List<Student> findAllByPhone(String phone);
	public List<Student> findAllByEmailLike(String email);
	public List<Student> findAllByAddressLike(String address);
	public List<Student> findAllByGraduation(String graduation);
}
