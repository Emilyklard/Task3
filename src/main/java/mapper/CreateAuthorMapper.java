package mapper;
import lombok.Builder;
import dto.CreateAuthorDto;
import entity.Author;
@Builder
public class CreateAuthorMapper implements Mapper<CreateAuthorDto, Author> {
    private static final CreateAuthorMapper INSTANCE = new CreateAuthorMapper();
    public static CreateAuthorMapper getInstance(){
        return INSTANCE;
    }

    @Override
    public Author mapFrom(CreateAuthorDto object) {
         return Author.builder()
                .first_name(object.getFirst_name())
                .last_name(object.getLast_name())
                .build();
    }
}
