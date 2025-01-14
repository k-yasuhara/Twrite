package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Symptom {
	private Integer id;
	private Integer recodsId;
	private Integer symptomsId;
}
