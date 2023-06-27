/* tslint:disable */
/* eslint-disable */
import { IdentifierLaneView } from './identifier-lane-view';
import { IssueView } from './issue-view';
import { WorkflowType } from './workflow-type';
export interface LaneView {
  description?: string;
  id?: IdentifierLaneView;
  issues?: Array<IssueView>;
  name?: string;
  workflowType?: WorkflowType;
}
