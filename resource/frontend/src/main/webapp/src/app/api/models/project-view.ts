/* tslint:disable */
/* eslint-disable */
import { IdProjectView } from './id-project-view';
import { WorkflowStatusView } from './workflow-status-view';
export interface ProjectView {
  description?: string;
  id?: IdProjectView;
  name?: string;
  workflowStatus?: Array<WorkflowStatusView>;
  workflowStatuses?: Array<WorkflowStatusView>;
}
