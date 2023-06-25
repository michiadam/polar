package at.michaeladam.polar.service.kanban.view;

import at.michaeladam.polar.service.common.Identifier;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
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
/**
 * This class is a view class for the project entity.
 * @see at.michaeladam.polar.persistence.kanban.model.Project
 */
public class ProjectView {
    private Identifier<ProjectView> id;
    private String name;
    private String description;
    private List<LaneView> lanes;

    public List<LaneView> getLanes() {
        if (lanes == null) {
            lanes = new ArrayList<>();
        }
        return lanes;
    }

}
