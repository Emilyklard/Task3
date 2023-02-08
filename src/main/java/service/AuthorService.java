package service;
import dao.AuthorDao;
import dto.AuthorDto;
import dto.CreateAuthorDto;
import mapper.CreateAuthorMapper;
import validator.CreateAuthorValidator;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class AuthorService {
    private static final AuthorService INSTANCE = new AuthorService();
    private final CreateAuthorValidator createAuthorValidator = CreateAuthorValidator.getInstance();
    private final CreateAuthorMapper createAuthorMapper = CreateAuthorMapper.getInstance();
    private AuthorService(){}
    private final AuthorDao authorDao = AuthorDao.getInstance();
    public List<AuthorDto> findAll(){
        return authorDao.findAll().stream()
                .map(author -> new AuthorDto(
                        author.getId(), """
                        %s - %s """.formatted(author.getFirst_name(), author.getLast_name())
                ))
                .collect(toList());
    }
    public static AuthorService getInstance(){
        return INSTANCE;
    }

    public Integer create(CreateAuthorDto authorDto){
        var validationResult = createAuthorValidator.isValid(authorDto);
        var authorEntity = createAuthorMapper.mapFrom(authorDto);
        authorDao.save(authorEntity);
        return authorEntity.getId();

    }
}
