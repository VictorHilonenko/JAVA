package spring.scheduler.dto;

import java.util.HashMap;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
//NOTE: through this class implemented security policy of business logic with providing proper access to each field
//and also the volume of data transfered will be less when we use a map instead of many "null"s
public class AppointmentDTO {
	private HashMap<String, String> map;

	public AppointmentDTO() {
		this.map = new HashMap<String, String>();
	}
	
	public AppointmentDTO(HashMap<String, String> fieldsMap) {
		this.map = fieldsMap;
	}
}