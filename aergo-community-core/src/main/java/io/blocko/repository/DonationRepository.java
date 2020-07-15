package io.blocko.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.blocko.model.Donation;

public interface DonationRepository extends JpaRepository<Donation, Long>{

}
