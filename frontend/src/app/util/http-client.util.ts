import {HttpClient, HttpHandler, HttpHeaders, HttpParams} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {HttpMethod} from "../enums/http-method";
import {Observable} from "rxjs";

@Injectable({
    providedIn: 'root'
})
export class HttpClientUtil extends HttpClient {
    headers?: HttpHeaders
    token?: string;

    constructor(handler: HttpHandler) {
        super(handler);
    }

    fetch<T>(url: string, auth: boolean, params?: HttpParams): Observable<T> {
        let options: any = {};
        params ? options.params = params : {};
        auth ? options.headers = {} : {};
        return this.request<T>(HttpMethod.GET, url, <Object>options);
    }

    load<T>(method: HttpMethod, url: string, auth: boolean, body?: any): Observable<T> {
        let options: any = {};
        body ? options.body = body : {};
        auth ? options.headers = {} : {};
        return this.request<T>(method, url, <Object>options);
    }

    createHttpParams(options: Record<string, any>) {
        let params = new HttpParams();
        Object.keys(options)
            .forEach(k => options[k] ? params = params.set(k, options[k] as string) : {})
        return params;
    }
}
