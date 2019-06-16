package sbnz.integracija.siem_center.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sbnz.integracija.siem_center.facts.Log;;

@Repository
public interface LogRepository extends JpaRepository<Log, Long>{

}
