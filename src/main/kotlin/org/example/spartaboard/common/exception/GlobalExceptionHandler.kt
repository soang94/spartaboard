package org.example.spartaboard.common.exception

import org.example.spartaboard.common.exception.dto.BaseResponse
import org.example.spartaboard.common.exception.dto.ErrorResponse
import org.example.spartaboard.common.status.ResultCode
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(ModelNotFoundException::class)
    fun handleModelNotFoundException(e: ModelNotFoundException): ResponseEntity<ErrorResponse> {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(ErrorResponse(e.message))
    }

    @ExceptionHandler(InvalidRoleException::class)
    fun handleInvalidRoleException(e: InvalidRoleException): ResponseEntity<ErrorResponse> {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(ErrorResponse(e.message))
    }

    @ExceptionHandler(WrongEmailOrPasswordException::class)
    fun handleWrongEmailOrPasswordException(e: WrongEmailOrPasswordException): ResponseEntity<ErrorResponse> {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(ErrorResponse(e.message))
    }

    @ExceptionHandler(AlreadyExistException::class)
    fun handleAlreadyExistException(e: AlreadyExistException): ResponseEntity<ErrorResponse> {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(ErrorResponse(e.message))
    }

    @ExceptionHandler(UnAuthorizedAccessException::class)
    fun handleUnAuthorizedAccessException(e: UnAuthorizedAccessException): ResponseEntity<ErrorResponse> {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(ErrorResponse(e.message))
    }

    @ExceptionHandler(PasswordMismatchException::class)
    fun handlePasswordMismatchException(e: PasswordMismatchException): ResponseEntity<ErrorResponse> {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(ErrorResponse(e.message))
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    protected fun handleValidationExceptions(ex: MethodArgumentNotValidException): ResponseEntity<BaseResponse<Map<String, String>>> {
        val errors = mutableMapOf<String, String>()
        ex.bindingResult.allErrors.forEach { error ->
            val fieldName = (error as FieldError).field
            val errorMessage = error.defaultMessage
            errors[fieldName] = errorMessage ?: "Not Exception Message"
        }
        return ResponseEntity(BaseResponse(ResultCode.ERROR.name, errors, ResultCode.ERROR.msg), HttpStatus.BAD_REQUEST)
    }
}