package com.tecsup.petclinic.mapper;

import com.tecsup.petclinic.domain.VisitTO;
import com.tecsup.petclinic.entities.Visit;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface VisitMapper {

    VisitMapper INSTANCE = Mappers.getMapper(VisitMapper.class);

    Visit toVisit(VisitTO visitTO);

    VisitTO toVisitTO(Visit visit);

    List<VisitTO> toVisitTOList(List<Visit> visitList);

    List<Visit> toVisitList(List<VisitTO> visitTOList);
}
