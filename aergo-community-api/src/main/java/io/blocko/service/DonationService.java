package io.blocko.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.blocko.repository.DonationRepository;

@Service
public class DonationService {

	@Autowired
	private DonationRepository donationRepository;
}
