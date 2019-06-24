package kz.logistic.pl.exceptions;

import kz.logistic.pl.utils.ErrorContent;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class LogisticsExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorContent errorContent = new ErrorContent("Try another method", "Method not allowed", ex.getLocalizedMessage(), HttpStatus.METHOD_NOT_ALLOWED );
        return new ResponseEntity(errorContent, errorContent.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorContent errorContent = new ErrorContent("Try another media", "Media not supported", ex.getLocalizedMessage(), HttpStatus.UNSUPPORTED_MEDIA_TYPE );
        return new ResponseEntity(errorContent, errorContent.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorContent errorContent = new ErrorContent("Try another media", "Media not acceptable", ex.getLocalizedMessage(), HttpStatus.NOT_ACCEPTABLE );
        return new ResponseEntity(errorContent, errorContent.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorContent errorContent = new ErrorContent("Path not present", "Missing path", ex.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity(errorContent, errorContent.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorContent errorContent = new ErrorContent("Try another request", "Missing servlet", ex.getLocalizedMessage(), HttpStatus.BAD_REQUEST );
        return new ResponseEntity(errorContent, errorContent.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleServletRequestBindingException(ServletRequestBindingException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorContent errorContent = new ErrorContent("Try another request", "Request cant bind", ex.getLocalizedMessage(), HttpStatus.BAD_REQUEST );
        return new ResponseEntity(errorContent, errorContent.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleConversionNotSupported(ConversionNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorContent errorContent = new ErrorContent("Try another conversion", "Conversion not supported", ex.getLocalizedMessage(), HttpStatus.BAD_REQUEST );
        return new ResponseEntity(errorContent, errorContent.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorContent errorContent = new ErrorContent("Try right type", "Type mismatch", ex.getLocalizedMessage(), HttpStatus.BAD_REQUEST );
        return new ResponseEntity(errorContent, errorContent.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorContent errorContent = new ErrorContent("Try another request", "Message not readable", ex.getLocalizedMessage(), HttpStatus.BAD_REQUEST );
        return new ResponseEntity(errorContent, errorContent.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotWritable(HttpMessageNotWritableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorContent errorContent = new ErrorContent("Try another message", "Message not writable", ex.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR );
        return new ResponseEntity(errorContent, errorContent.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorContent errorContent = new ErrorContent("Try another method", "Method not allowed", ex.getLocalizedMessage(), HttpStatus.BAD_REQUEST );
        return new ResponseEntity(errorContent, errorContent.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestPart(MissingServletRequestPartException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorContent errorContent = new ErrorContent("Something wrong", "Servlet Missing", ex.getLocalizedMessage(), HttpStatus.BAD_REQUEST );
        return new ResponseEntity(errorContent, errorContent.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorContent errorContent = new ErrorContent("Something wrong", "Bind not allowed", ex.getLocalizedMessage(), HttpStatus.BAD_REQUEST );
        return new ResponseEntity(errorContent, errorContent.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorContent errorContent = new ErrorContent("Something wrong", "Handler not found", ex.getLocalizedMessage(), HttpStatus.BAD_REQUEST );
        return new ResponseEntity(errorContent, errorContent.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleAsyncRequestTimeoutException(AsyncRequestTimeoutException ex, HttpHeaders headers, HttpStatus status, WebRequest webRequest) {
        ErrorContent errorContent = new ErrorContent("Something wrong", "Request too long", ex.getLocalizedMessage(), HttpStatus.BAD_REQUEST );
        return new ResponseEntity(errorContent, errorContent.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorContent errorContent = new ErrorContent("Something wrong", "Internal Exception", ex.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR );
        return new ResponseEntity(errorContent, errorContent.getStatus());
    }

    @ExceptionHandler(NullPointerException.class)
    protected ResponseEntity<Object> handleNullPointerException(
        NullPointerException ex) {
        ErrorContent errorContent = new ErrorContent("Null", "Null Exception", ex.getLocalizedMessage(), HttpStatus.BAD_REQUEST );
        return new ResponseEntity(errorContent, errorContent.getStatus());
    }
}
