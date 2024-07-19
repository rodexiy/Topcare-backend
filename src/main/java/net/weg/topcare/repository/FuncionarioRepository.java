package net.weg.topcare.repository;

import net.weg.topcare.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository extends JpaRepository<Employee, Long> {
}
