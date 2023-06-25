package at.michaeladam.polar.service.kanban.view;

import at.michaeladam.polar.persistence.common.ID;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Accessors(chain = true)
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
public class IssueView {

    private ID<IssueView> id;

    private String title;
    private String description;

}
