package uz.pdp.appclickapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.appclickapp.entity.View;

@Repository
public interface ViewRepository extends JpaRepository<View, Long> {
    View getByName(String name);
}
