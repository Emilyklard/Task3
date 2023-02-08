package dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class BookDto {
    private final Long id;
    private final String name;
    private final Short year;
    private final Short pages;
    private final Integer author_id;




}
