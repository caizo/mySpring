package org.pmv.myspring.repo;

import org.pmv.myspring.entities.Menu;
import org.pmv.myspring.entities.Plato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long> {

}