package io.blocko.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.blocko.repository.CommentRepository;

@Service
@Transactional
public class CommentService {

	@Autowired
	private CommentRepository commentRepository;
}
