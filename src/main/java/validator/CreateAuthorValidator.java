package validator;

import dto.AuthorDto;
import dto.CreateAuthorDto;
import entity.Author;
import service.AuthorService;

public class CreateAuthorValidator implements Validator<CreateAuthorDto>{
    private static final CreateAuthorValidator INSTANCE = new CreateAuthorValidator();

    public static CreateAuthorValidator getInstance() {
        return INSTANCE;
    }

    @Override
    public ValidationResult isValid(CreateAuthorDto object) {
        var validationResult = new ValidationResult();
        if(String.valueOf(object.getFirst_name()) == null)
            validationResult.add(Error.of("invalid.first_name", "First_name is invalid!"));
            return validationResult;
    }
}
