package io.blocko.exception.advice;

import org.springframework.web.bind.annotation.ControllerAdvice;

import io.blocko.controller.BoardController;

@ControllerAdvice(basePackageClasses = BoardController.class)
public class BoardExceptionHandler {

}
