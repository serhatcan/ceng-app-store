/**
 * Created by Serhat CAN
 */
package tr.edu.metu.ceng352.form;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

public class AppForm {

	private static final String NOT_BLANK_MESSAGE = "{notBlank.message}";

	@NotBlank(message = AppForm.NOT_BLANK_MESSAGE)
	private String title;

	@NotBlank(message = AppForm.NOT_BLANK_MESSAGE)
	private String description;

	@NotNull
	private Double price;

    @NotNull
	private Long categoryId;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
}
