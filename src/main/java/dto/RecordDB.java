package dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecordDB {
	private Integer id;
	private String registerId;
	private Date registered;
	private Date updated;
	private Date start;
	private Date end;
	private Integer patientPattern;
	private String consContent;
	private String respContent;
	private Integer editror;
	private Integer staffId;
	private String symptoms;
	private Staff staff;
	private Patient patient;
	
}
