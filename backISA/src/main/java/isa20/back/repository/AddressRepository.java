package isa20.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import isa20.back.model.Address;


public interface AddressRepository extends JpaRepository< Address,Long>
{

}
