package com.example.springboot_crud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springboot_crud.entities.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

}
