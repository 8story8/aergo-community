package io.blocko.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.blocko.model.Wallet;

public interface WalletRepository extends JpaRepository<Wallet, Long>{

}
