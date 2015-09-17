/**
 * Created by Serhat CAN
 */
package tr.edu.metu.ceng352.form;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

public class ReviewForm {

	private static final String NOT_BLANK_MESSAGE = "{notBlank.message}";

	@NotNull
	private Long appId;

	@NotBlank(message = ReviewForm.NOT_BLANK_MESSAGE)
	private String title;

	@NotBlank(message = ReviewForm.NOT_BLANK_MESSAGE)
	private String description;

	@NotNull
	private Integer vote;

	public Long getAppId() {
		return appId;
	}

	public void setAppId(Long appId) {
		this.appId = appId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getVote() {
		return vote;
	}

	public void setVote(Integer vote) {
		this.vote = vote;
	}
}
