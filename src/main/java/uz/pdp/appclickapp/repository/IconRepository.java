package uz.pdp.appclickapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.appclickapp.entity.Icon;



@Repository
public interface IconRepository extends JpaRepository<Icon, Long> {
}
