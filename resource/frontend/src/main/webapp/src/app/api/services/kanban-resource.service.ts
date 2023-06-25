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
   * Path part for operation kanbanFindMyProjectsGet
   */
  static readonly KanbanFindMyProjectsGetPath = '/kanban/findMyProjects';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `kanbanFindMyProjectsGet()` instead.
   *
   * This method doesn't expect any request body.
   */
  kanbanFindMyProjectsGet$Response(params?: {
  },
  context?: HttpContext

): Observable<StrictHttpResponse<Array<ProjectView>>> {

    const rb = new RequestBuilder(this.rootUrl, KanbanResourceService.KanbanFindMyProjectsGetPath, 'get');
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
   * To access the full response (for headers, for example), `kanbanFindMyProjectsGet$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  kanbanFindMyProjectsGet(params?: {
  },
  context?: HttpContext

): Observable<Array<ProjectView>> {

    return this.kanbanFindMyProjectsGet$Response(params,context).pipe(
      map((r: StrictHttpResponse<Array<ProjectView>>) => r.body as Array<ProjectView>)
    );
  }

  /**
   * Path part for operation kanbanProjectProjectIdGet
   */
  static readonly KanbanProjectProjectIdGetPath = '/kanban/project/{project-id}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `kanbanProjectProjectIdGet()` instead.
   *
   * This method doesn't expect any request body.
   */
  kanbanProjectProjectIdGet$Response(params: {
    'project-id': IdentifierProjectView;
  },
  context?: HttpContext

): Observable<StrictHttpResponse<ProjectView>> {

    const rb = new RequestBuilder(this.rootUrl, KanbanResourceService.KanbanProjectProjectIdGetPath, 'get');
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
   * To access the full response (for headers, for example), `kanbanProjectProjectIdGet$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  kanbanProjectProjectIdGet(params: {
    'project-id': IdentifierProjectView;
  },
  context?: HttpContext

): Observable<ProjectView> {

    return this.kanbanProjectProjectIdGet$Response(params,context).pipe(
      map((r: StrictHttpResponse<ProjectView>) => r.body as ProjectView)
    );
  }

}
