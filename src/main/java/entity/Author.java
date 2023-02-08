package entity;
import lombok.*;
@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
@Data
@Builder
public class Author {
    private Integer id;
    private String first_name;
    private String last_name;
public Author(){}
}
