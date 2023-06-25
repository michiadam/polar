package at.michaeladam.polar.service.kanban.mapper;

import at.michaeladam.polar.persistence.kanban.model.Lane;
import at.michaeladam.polar.service.common.IDMapper;
import at.michaeladam.polar.service.common.MapperBase;
import at.michaeladam.polar.service.kanban.view.LaneView;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {
        IDMapper.class,
        IssueMapper.class
}, collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED)
public interface LaneMapper extends MapperBase<Lane, LaneView> {

    static LaneMapper getInstance() {
        return Mappers.getMapper(LaneMapper.class);
    }

    @Mapping(target = "workflowType",source = "workflowType", qualifiedByName = "workflowType")
    Lane toEntity(LaneView workflowStatusView);

    LaneView toView(Lane lane);

    @Named("workflowType")
    default Lane.WorkflowType workflowType(Lane.WorkflowType workflowType) {
        if(workflowType == null)
            return Lane.WorkflowType.NORMAL;
        return workflowType;
    }

}
