package io.blocko.exception.advice;

import org.springframework.web.bind.annotation.ControllerAdvice;

import io.blocko.controller.CommentController;

@ControllerAdvice(basePackageClasses = CommentController.class)
public class CommentExceptionHandler {
}
