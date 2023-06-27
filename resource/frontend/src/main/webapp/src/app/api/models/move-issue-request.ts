/* tslint:disable */
/* eslint-disable */
import { IdentifierLaneView } from './identifier-lane-view';
export interface MoveIssueRequest {
  sourceIndex?: number;
  sourceLane?: IdentifierLaneView;
  targetIndex?: number;
  targetLane?: IdentifierLaneView;
}
