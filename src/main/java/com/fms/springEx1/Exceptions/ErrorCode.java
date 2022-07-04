package com.fms.springEx1.Exceptions;

public enum ErrorCode {

	ARTICLES_NOT_FOUND_FOR_BRAND(1000), ARTICLE_NOT_FOUND_FOR_ID(1001), NO_ARTICLES_CORRESPONDING_TO(1002),
	ARTICLE_NOT_FOUND_FOR_NAME(1003), ARTICLE_IN_USE(1004);

	private int errorCode;

	ErrorCode(int code) {
		this.errorCode = code;
	}

	public int getErrorCode() {
		return errorCode;
	}

}
