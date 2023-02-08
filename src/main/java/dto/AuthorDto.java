package dto;
import java.util.Objects;

public class AuthorDto {
    private final Integer id;
    private final String description;
    public AuthorDto(Integer id, String description) {
        this.id = id;
        this.description = description;
    }
    public Integer getId() {
        return id;
    }
    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorDto authorDto = (AuthorDto) o;
        return Objects.equals(id, authorDto.id) && Objects.equals(description, authorDto.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description);
    }

    @Override
    public String toString() {
        return "AuthorDto{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}
