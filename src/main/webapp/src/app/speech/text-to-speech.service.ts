import {Injectable} from '@angular/core';
import {ContextService} from '../context.service';
import {HandleError, HttpErrorHandler} from '../http.error.handler.service';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {environment} from '../../environments/environment';
import {ServiceHelper} from '../shared/ServiceHelper';
import {Observable} from 'rxjs/Observable';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
    /*,
    'Authorization': 'my-auth-token'*/
  })
};

@Injectable()
export class TextToSpeechService {

  private apiUrl = environment.texttospeech_url;

  handleError: HandleError;

  constructor(private http: HttpClient,
              httpErrorHandler: HttpErrorHandler,
              private context: ContextService) {
    this.handleError = httpErrorHandler.createHandleError('TextToSpeechService');
  }

  play(text: string): Observable<Blob> {
    /*let context = new AudioContext();
    let audio = new Audio();
    audio.src = ServiceHelper.buildUrl(this.apiUrl, this.context.userName());
    audio.load();
    audio.play();*/

    return this.http.post(ServiceHelper.buildUrl(this.apiUrl, this.context.userName()), {'text': text}, {
      'headers' : { 'Content-Type': 'application/json'},
      'responseType': 'blob'
    });
      /*.pipe(
        catchError(this.handleError('play', ''))
      );*/
  }

}
