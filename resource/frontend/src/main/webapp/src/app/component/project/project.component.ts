import { Component } from '@angular/core';

import { faUpload, faComment, faPaperclip, faFlag, faEllipsisH } from '@fortawesome/free-solid-svg-icons';
import {ActivatedRoute} from "@angular/router";
import {KanbanResourceService} from "../../api/services/kanban-resource.service";
import {ProjectView} from "../../api/models/project-view";

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
            this.kanbanService.kanbanProjectProjectIdGet({
                "project-id": val['projectID']
            }).subscribe(project => {

                this.project = project;
                console.log(project)
            });
        });

    }

}
