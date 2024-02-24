package com.example.common.exception;

public class CustomException {

	public static class DuplicateUserException extends RuntimeException {

		private static final long serialVersionUID = 1L;

		public DuplicateUserException(String message) {
			super(message);
		}
	}

	public static class UserNotFoundException extends RuntimeException {
		private static final long serialVersionUID = 1L;

		public UserNotFoundException(String message) {
			super(message);
		}

	}

	public static class MenuServiceException extends RuntimeException {

		private static final long serialVersionUID = 1L;

		public MenuServiceException(String message) {
			super(message);
		}

		public MenuServiceException(String string, Exception e) {
			super(string, e);
		}

	}

}
