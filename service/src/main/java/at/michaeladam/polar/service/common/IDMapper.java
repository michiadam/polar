package at.michaeladam.polar.service.common;

import at.michaeladam.polar.persistence.common.ID;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper
public interface IDMapper {


    //map function to convert from ID to Identifier
    @Mapping(target = "id", source = "oid")
    Identifier toView(ID id);

    //map function to convert from Identifier to ID
    @Mapping(target = "oid", source = "id")
    ID toEntity(Identifier idView);

    //map function to convert from UUID to Identifier
    default Identifier toViewID(UUID id) {
        return new Identifier(id);
    }

    //map function to convert from Identifier to UUID
    default UUID toEntityID(Identifier idView) {
        return idView.getId();
    }



}
