import {environment} from '../../environments/environment';
import {ContextService} from '../context.service';
import {HttpErrorHandler, HandleError} from '../http.error.handler.service';
import {Message} from './Message';
import {HttpHeaders} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import {catchError, retry} from 'rxjs/operators';
import {ServiceHelper} from '../shared/ServiceHelper';
import {ChatResponse} from './ChatResponse';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json'/*,
    'Authorization': 'my-auth-token'*/
  })
};

@Injectable()
export class ChatService {

  private apiUrl = environment.conversation_url;

  handleError: HandleError;

  constructor(private http: HttpClient,
    httpErrorHandler: HttpErrorHandler,
    private context: ContextService) {
    this.handleError = httpErrorHandler.createHandleError('ChatService');
  }

  sendMessage(message: Message, user: string): Observable<ChatResponse> {
    return this.http.post<ChatResponse>(ServiceHelper.buildUrl(this.apiUrl, user), message, httpOptions)
      .pipe(
      catchError(this.handleError('sendMessage', { 'source': [] }))
      );
    // return Observable.of({source: 'Reponse bouchon'});
  }

  buildUrl(user: string): string {
    return this.apiUrl + user;
  }

}

