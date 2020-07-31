package io.blocko.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.blocko.repository.BoardRepository;

@Service
@Transactional
public class BoardService {
	
	@Autowired
	private BoardRepository boardRepository;
}
