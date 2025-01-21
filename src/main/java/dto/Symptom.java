package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Symptom {
	private Integer id;
	private Integer recordsId;
	private Integer symptomsId;
	private String  symptomsName;
	private Integer countSymptom;
}
