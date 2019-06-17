package sbnz.integracija.siem_center.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sbnz.integracija.siem_center.facts.Alarm;


@Repository
public interface AlarmRepository extends JpaRepository<Alarm, Long>{
	
}
