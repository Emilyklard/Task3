package mapper;
import lombok.Builder;
import dto.CreateBookDto;
import entity.Book;
@Builder
public class CreateBookMapper implements Mapper<CreateBookDto, Book> {
    private static final CreateBookMapper INSTANCE = new CreateBookMapper();
    public static CreateBookMapper getInstance(){
        return INSTANCE;
    }
    @Override
    public Book mapFrom(CreateBookDto object) {
        return Book.builder()
                .name(object.getName())
                .year(object.getYear())
                .pages(object.getPages())
                .author_id(object.getAuthor())
                .build();
    }
}
