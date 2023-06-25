/* tslint:disable */
/* eslint-disable */
import { IdWorkflowStatusView } from './id-workflow-status-view';
import { IssueView } from './issue-view';
import { WorkflowType } from './workflow-type';
export interface WorkflowStatusView {
  description?: string;
  id?: IdWorkflowStatusView;
  issues?: Array<IssueView>;
  name?: string;
  workflowType?: WorkflowType;
}
