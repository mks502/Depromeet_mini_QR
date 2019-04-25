package com.depromeet.mini_QR.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.depromeet.mini_QR.domain.entity.Member;
@Transactional
@Repository
public interface MemberRepository extends JpaRepository<Member, Long>{
	Long countBySeminarId(Long id);	
}