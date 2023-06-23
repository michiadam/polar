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


@Injectable({
  providedIn: 'root',
})
export class FrontendGeneratorService extends BaseService {
  constructor(
    config: ApiConfiguration,
    http: HttpClient
  ) {
    super(config, http);
  }

  /**
   * Path part for operation generateGet
   */
  static readonly GenerateGetPath = '/generate';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `generateGet()` instead.
   *
   * This method doesn't expect any request body.
   */
  generateGet$Response(params?: {
  },
  context?: HttpContext

): Observable<StrictHttpResponse<string>> {

    const rb = new RequestBuilder(this.rootUrl, FrontendGeneratorService.GenerateGetPath, 'get');
    if (params) {
    }

    return this.http.request(rb.build({
      responseType: 'json',
      accept: 'application/json',
      context: context
    })).pipe(
      filter((r: any) => r instanceof HttpResponse),
      map((r: HttpResponse<any>) => {
        return r as StrictHttpResponse<string>;
      })
    );
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `generateGet$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  generateGet(params?: {
  },
  context?: HttpContext

): Observable<string> {

    return this.generateGet$Response(params,context).pipe(
      map((r: StrictHttpResponse<string>) => r.body as string)
    );
  }

}
