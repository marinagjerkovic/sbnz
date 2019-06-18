package sbnz.integracija.siem_center.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sbnz.integracija.siem_center.facts.Machine;


@Repository
public interface MachineRepository extends JpaRepository<Machine, Long>{
	Machine findByIp(String ip);
}
