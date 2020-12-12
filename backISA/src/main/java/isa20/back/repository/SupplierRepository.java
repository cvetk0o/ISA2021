package isa20.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import isa20.back.model.Supplier;


public interface SupplierRepository extends JpaRepository< Supplier, Long >
{

}
