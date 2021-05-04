package br.com.aijalon.medical.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorResponse {

		private Date timestamp;
		private String message;
		private String path;
}
