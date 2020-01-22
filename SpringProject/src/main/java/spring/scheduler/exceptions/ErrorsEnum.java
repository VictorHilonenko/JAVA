package spring.scheduler.exceptions;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorsEnum {
    PAGE_NOT_FOUND_ERROR(HttpStatus.NOT_FOUND, "error.page_not_found"),
    REPOSITORY_ISSUE(HttpStatus.GONE, "error.some_repository_issue_try_again_later"), 
    WRONG_DATA_PASSED(HttpStatus.NOT_ACCEPTABLE, "error.wrong_data_passed"), 
    NO_IDLE_MASTER(HttpStatus.CONFLICT, "warning.no_idle_master"), 
    ACCESS_DENIED(HttpStatus.FORBIDDEN, "error.access_denied"),
    NOT_ACCEPTABLE_DATA_PASSED(HttpStatus.NOT_ACCEPTABLE, "error.not_acceptable_data_passed"),
    MAIL_ISSUE(HttpStatus.GONE, "error.some_mail_issue_try_again_later"),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "error.user_not_found");
	
    private HttpStatus statusCode;
    private String errorMessage;
}