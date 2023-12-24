package WCheck.converter;
import WCheck.dtos.UserDTO;
import WCheck.entities.UserName;
import org.modelmapper.ModelMapper;

public class EntityDtoConverter {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static <T, U> U convertToDto(T entity, Class<U> dtoClass) {
        return modelMapper.map(entity, dtoClass);
    }

    public static <T, U> U convertToEntity(T dto, Class<U> entityClass) {
        return modelMapper.map(dto, entityClass);
    }
}
