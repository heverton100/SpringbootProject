package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.PessoaEntity;

public interface PessoaRepository extends JpaRepository <PessoaEntity,Long>{

    List<PessoaEntity> findByEmail(String email);
    
}
