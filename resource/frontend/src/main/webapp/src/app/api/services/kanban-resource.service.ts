/* tslint:disable */
/* eslint-disable */
import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse, HttpContext } from '@angular/common/http';
import { BaseService } from '../base-service';
import { ApiConfiguration } from '../api-configuration';
import { StrictHttpResponse } from '../strict-http-response';
import { RequestBuilder } from '../request-builder';
import { Observable } from 'rxjs';
import { map, filter } from 'rxjs/operators';

import { IdentifierProjectView } from '../models/identifier-project-view';
import { MoveIssueRequest } from '../models/move-issue-request';
import { ProjectView } from '../models/project-view';

@Injectable({
  providedIn: 'root',
})
export class KanbanResourceService extends BaseService {
  constructor(
    config: ApiConfiguration,
    http: HttpClient
  ) {
    super(config, http);
  }

  /**
   * Path part for operation getFindMyProjects
   */
  static readonly GetFindMyProjectsPath = '/api/kanban/findMyProjects';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getFindMyProjects()` instead.
   *
   * This method doesn't expect any request body.
   */
  getFindMyProjects$Response(params?: {
  },
  context?: HttpContext

): Observable<StrictHttpResponse<Array<ProjectView>>> {

    const rb = new RequestBuilder(this.rootUrl, KanbanResourceService.GetFindMyProjectsPath, 'get');
    if (params) {
    }

    return this.http.request(rb.build({
      responseType: 'json',
      accept: 'application/json',
      context: context
    })).pipe(
      filter((r: any) => r instanceof HttpResponse),
      map((r: HttpResponse<any>) => {
        return r as StrictHttpResponse<Array<ProjectView>>;
      })
    );
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `getFindMyProjects$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getFindMyProjects(params?: {
  },
  context?: HttpContext

): Observable<Array<ProjectView>> {

    return this.getFindMyProjects$Response(params,context).pipe(
      map((r: StrictHttpResponse<Array<ProjectView>>) => r.body as Array<ProjectView>)
    );
  }

  /**
   * Path part for operation getProject
   */
  static readonly GetProjectPath = '/api/kanban/project/{project-id}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getProject()` instead.
   *
   * This method doesn't expect any request body.
   */
  getProject$Response(params: {
    'project-id': IdentifierProjectView;
  },
  context?: HttpContext

): Observable<StrictHttpResponse<ProjectView>> {

    const rb = new RequestBuilder(this.rootUrl, KanbanResourceService.GetProjectPath, 'get');
    if (params) {
      rb.path('project-id', params['project-id'], {});
    }

    return this.http.request(rb.build({
      responseType: 'json',
      accept: 'application/json',
      context: context
    })).pipe(
      filter((r: any) => r instanceof HttpResponse),
      map((r: HttpResponse<any>) => {
        return r as StrictHttpResponse<ProjectView>;
      })
    );
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `getProject$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getProject(params: {
    'project-id': IdentifierProjectView;
  },
  context?: HttpContext

): Observable<ProjectView> {

    return this.getProject$Response(params,context).pipe(
      map((r: StrictHttpResponse<ProjectView>) => r.body as ProjectView)
    );
  }

  /**
   * Path part for operation postMoveIssue
   */
  static readonly PostMoveIssuePath = '/api/kanban/project/{project-id}/move';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `postMoveIssue()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  postMoveIssue$Response(params: {
    'project-id': IdentifierProjectView;
    body?: MoveIssueRequest
  },
  context?: HttpContext

): Observable<StrictHttpResponse<boolean>> {

    const rb = new RequestBuilder(this.rootUrl, KanbanResourceService.PostMoveIssuePath, 'post');
    if (params) {
      rb.path('project-id', params['project-id'], {});
      rb.body(params.body, 'application/json');
    }

    return this.http.request(rb.build({
      responseType: 'json',
      accept: 'application/json',
      context: context
    })).pipe(
      filter((r: any) => r instanceof HttpResponse),
      map((r: HttpResponse<any>) => {
        return (r as HttpResponse<any>).clone({ body: String((r as HttpResponse<any>).body) === 'true' }) as StrictHttpResponse<boolean>;
      })
    );
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `postMoveIssue$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  postMoveIssue(params: {
    'project-id': IdentifierProjectView;
    body?: MoveIssueRequest
  },
  context?: HttpContext

): Observable<boolean> {

    return this.postMoveIssue$Response(params,context).pipe(
      map((r: StrictHttpResponse<boolean>) => r.body as boolean)
    );
  }

}
