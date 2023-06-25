/* tslint:disable */
/* eslint-disable */
import { IdentifierProjectView } from './identifier-project-view';
import { LaneView } from './lane-view';
export interface ProjectView {
  description?: string;
  id?: IdentifierProjectView;
  lanes?: Array<LaneView>;
  name?: string;
}
