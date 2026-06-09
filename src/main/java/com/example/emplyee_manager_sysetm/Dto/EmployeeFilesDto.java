package com.example.emplyee_manager_sysetm.Dto;

import java.nio.file.Path;

public class EmployeeFilesDto {
	private String fileName;
	private String fileType;
	private Path filePath;
	private String message;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public Path getFilePath() {
		return filePath;
	}

	public void setFilePath(Path path) {
		this.filePath = path;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
