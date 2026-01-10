package glsi.api.tp_jee_egab.Repository;

import glsi.api.tp_jee_egab.Model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface AdminRepository extends JpaRepository<Admin, Long> {

    Optional<Admin> findByEmail(String email);

}
