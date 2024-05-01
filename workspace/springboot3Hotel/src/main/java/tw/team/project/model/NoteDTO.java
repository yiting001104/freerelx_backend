package tw.team.project.model;

import org.springframework.web.multipart.MultipartFile;

public class NoteDTO {

	private String json;
	
	private MultipartFile multipartFile;

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	public MultipartFile getMultipartFile() {
		return multipartFile;
	}

	public void setMultipartFile(MultipartFile multipartFile) {
		this.multipartFile = multipartFile;
	}
	
	
}
