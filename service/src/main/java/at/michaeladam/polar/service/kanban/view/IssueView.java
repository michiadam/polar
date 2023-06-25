package at.michaeladam.polar.service.kanban.view;

import at.michaeladam.polar.service.common.Identifier;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@NoArgsConstructor
@Accessors(chain = true)
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
public class IssueView {

    private Identifier<IssueView> id;

    private String title;
    private String description;

}
