package com.swm.baseframework.base.code;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ApiResponseCode {

	OK(200, "R20000", "정상"),

	BAD_REQUEST(400, "R40000", "비정상적인 요청입니다."),
	INVALID_INPUT_VALUE(400, "R40001", "유효하지 않은 값입니다."),
	INVALID_TYPE_VALUE(400, "R40002", "유효하지 않은 값입니다."),
	BODY_READ_FAILED(400, "R40003", "BODY JSON parse error"),
	BIZ_DEFAULT_ERROR(400, "R40010", "처리할 수 없는 요청입니다."),
	DUPLICATION_ENTITY_DATA(400, "R40020", "데이터가 중복되어 저장할 수 없습니다."),
	REMOVED_ENTITY_DATA(400, "R40030", "데이터가 삭제된 상태여서 처리할 수 없습니다."),
	NOT_USABLE_ENTITY_DATA(400, "R40040", "데이터가 사용할 수 없는 상태입니다."),
	LIMITED_ENTITY_MAX_COUNT(400, "R40050", "최대 %d개 까지 등록 가능합니다."),
	REQUIRED_ENTITY_MIN_COUNT(400, "R40060", "최소 %d개 이상 등록해야 합니다."),
	
	UNAUTHORIZED(401, "R40100", "인증이 필요한 API 입니다."),
	EXPIRED_TOKEN(401, "R40101", "만료된 토큰입니다."),
	EXPIRED_OR_INVALID_TOKEN(401, "R40102", "만료되었거나 유효하지 않은 토큰입니다."),
	INVALID_TOKEN(401, "R40103", "토큰이 유효하지 않거나, 검증에 실패했습니다."),
	USER_PASSWORD_NOT_MATCH(401, "R40104", "아이디 혹은 비밀번호가 잘못되었습니다."),
	TOKEN_GENERATION_FAILED(401, "R40105", "토큰을 생성할 수 없습니다."),
	LOGIN_FAILED(401, "R40106", "로그인이 실패했습니다."),
	LOGIN_FAILED_NOT_FOUND_OR_NOT_MATCHED_PWD(401, "R40107", "아이디/비밀번호를 확인해 주세요."),
	LOGIN_FAILED_NOT_MATCHED_AUTH_NO(401, "R40108", "인증번호가 일치하지 않습니다."),
	LOGIN_FAILED_EXPIRED_AUTH_NO(401, "R40109", "만료 된 인증번호 입니다. 제한시간 내에 입력하세요."),
	REFRESH_FAILED(401, "R40110", "로그인 이력이 없거나 유효하지 않은 refreshToken 입니다."),
	REFRESH_FAILED_EXPIRED_TOKEN(401, "R40111", "만료 된 refreshToken 입니다."),
	REFRESH_FAILED_EXPIRED_EXTENSION_FAIL(401, "R40112", "토큰을 생성할 수 없습니다. refreshToken 의 만료시간연장이 실패했습니다."),
	LOGIN_FAILED_SDN(401, "R40113", "[재종 ERP] %s"),
	
	ACCESS_DENIED(403, "R40300", "접근이 허용되지 않습니다."),
	UNAUTHORIZED_IP_ADDRESS(403, "R40301", "접근이 허용되지 않는 IP 입니다."),
	
	NOT_FOUND(404, "R40400", "존재하지 않거나 비활성화된 API 입니다."),
	DATA_NOT_FOUND(404, "R40402", "데이터가 존재하지 않습니다."),
	
	METHOD_NOT_ALLOWED(405, "R40500", "허용되지 않는 Http Method 입니다."),
	
	UNSUPPORTED_MEDIA_TYPE(415, "R41500", "지원하지 않거나 처리할 수 없는 요청입니다."),
	
	INTERNAL_SERVER_ERROR(500, "R50000", "처리 중 오류가 발생했습니다."),

	EXTERNAL_API_UNAVAILABLE(503, "R50301", "서버가 요청을 처리할 준비가 되지 않았습니다.");

	private final String code;
	private final String message;
	private final int status;

	ApiResponseCode(final int status, final String code, final String message) {
		this.status = status;
		this.message = message;
		this.code = code;
	}
}
