package at.michaeladam.polar.service.kanban.mapper;

import at.michaeladam.polar.persistence.kanban.model.Issue;
import at.michaeladam.polar.service.common.IDMapper;
import at.michaeladam.polar.service.common.MapperBase;
import at.michaeladam.polar.service.kanban.view.IssueView;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = IDMapper.class)
public interface IssueMapper extends MapperBase<Issue, IssueView> {

    static IssueMapper getInstance() {
        return Mappers.getMapper(IssueMapper.class);
    }

    @Mapping(target = "id", source = "id")
    Issue toEntity(IssueView issueView);
    @Mapping(target = "id", source = "id")

    IssueView toView(Issue issue);


}
