import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { isString } from 'util';

export abstract class AbstractHttpService {
  public queryParams: any = null;

  constructor(
    protected uri: string,
    protected http: HttpClient,
    private withCredentials: boolean = false
  ) {
  }

  /**
   * Recupera o URL padrão para acesso a API de acordo com o ambiente
   */
  protected abstract getBackendUrl(): string;

  // tslint:disable-next-line:typedef
  protected options(
    options?: {
      headers?: HttpHeaders;
      observe?: 'body';
      params?: HttpParams;
      reportProgress?: boolean;
      responseType?: any;
      withCredentials?: boolean;
    }
  ) {

    let headers: HttpHeaders = new HttpHeaders();
    if (!options) {
      options = {headers};
    }
    if (options.headers) {
      for (const headerName of options.headers.keys()) {
        headers = headers.set(headerName, options.headers.getAll(headerName));
      }
    }
    options.headers = headers;

    if (!options.responseType) {
      options.responseType = 'json';
    }

    if (!options.withCredentials) {
      options.withCredentials = this.withCredentials;
    }

    return options;
  }

  // tslint:disable-next-line:typedef
  protected logError(error: any) {
    if (error.status === 0) {
      console.log('Backend indisponível  [' + this.getBackendUrl() + ']');
    }
  }
}
