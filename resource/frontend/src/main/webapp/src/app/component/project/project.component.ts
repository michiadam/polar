import {Component} from '@angular/core';

import {faUpload, faComment, faPaperclip, faFlag, faEllipsisH} from '@fortawesome/free-solid-svg-icons';
import {ActivatedRoute} from "@angular/router";
import {KanbanResourceService} from "../../api/services/kanban-resource.service";
import {ProjectView} from "../../api/models/project-view";
import {CdkDragDrop} from '@angular/cdk/drag-drop';
import {LaneView} from "../../api/models/lane-view";
import {MoveIssueRequest} from "../../api/models/move-issue-request";
import {IdentifierProjectView} from "../../api/models";

@Component({
    selector: 'app-project',
    templateUrl: './project.component.html',
    styleUrls: ['./project.component.scss']
})
export class ProjectComponent {
    faUpload = faUpload;
    faPaperclip = faPaperclip;
    faComment = faComment;
    faFlag = faFlag;
    faEllipsisH = faEllipsisH;

    project: ProjectView | undefined;

    constructor(private route: ActivatedRoute, private kanbanService: KanbanResourceService) {

        route.params.subscribe(val => {
            this.kanbanService.getProject({
                "project-id": val['projectID']
            }).subscribe(project => {

                this.project = project;
                console.log(project)
            });
        });

    }

    drop(project: ProjectView, event: CdkDragDrop<LaneView>) {
        const container = event.container;
        const previousContainer = event.previousContainer;

        const index = event.currentIndex;
        const previousIndex = event.previousIndex;

        let lane = container.data;
        if (container === previousContainer) {
            if (lane.issues) {
                const issueView = lane.issues.splice(previousIndex, 1)[0];
                lane.issues.splice(index, 0, issueView);
            }
        } else {
            if (lane.issues && previousContainer.data.issues) {
               let issueView = previousContainer.data.issues.splice(previousIndex, 1)[0];
                lane.issues.splice(index, 0, issueView);
            }
        }
        const id = project.id;
        if(!id){
            throw new Error("Project ID is null");
        }
        this.kanbanService.postMoveIssue(
            {
                "project-id": id.id as IdentifierProjectView,
                body: {
                    sourceIndex: previousIndex,
                   targetIndex: index,
                     sourceLane: previousContainer.data.id,
                        targetLane: lane.id
               } as MoveIssueRequest
            }
        ).subscribe(project => {
            console.log(project)
        });

    }


}
