package com.depromeet.mini_QR.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.depromeet.mini_QR.domain.entity.SeminarRoom;
@Transactional
@Repository
public interface SeminarRoomRepository extends JpaRepository<SeminarRoom, Long>{
	public SeminarRoom findBySeminarTitle(String title);
	public SeminarRoom findBySeminarId(Long id);

}