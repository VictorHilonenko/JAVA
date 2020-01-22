package spring.scheduler.dto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class FeedbackDTO {
	@NotNull
    private Long id;
    
	@NotNull
	private Byte rating;
    
	@NotNull
	private String textMessage;
}