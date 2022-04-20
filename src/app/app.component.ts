import {Component, OnInit} from '@angular/core';
import {AngularFireMessaging} from "@angular/fire/messaging";
import {HttpClient} from "@angular/common/http";
import {Message} from "./models/message";
import {environment} from "../environments/environment";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit{
  title = 'fcm-demo-front';
  messages: Array<Message> = [];

  constructor(private msg: AngularFireMessaging, private http: HttpClient) { }
  private api = environment.api;
  ngOnInit() {

    this.msg.requestToken.subscribe(token => {

      console.log(token);
      this.http.post(`http://${this.api}/notification`, {
        target: token,
        title: 'hello world',
        message: 'First notification, kinda nervous',
      }).subscribe(() => {  });

      this.http.post(`http://${this.api}/topic/subscription`, {
        topic: 'weather',
        subscriber: token
      }).subscribe(() => {  });

    }, error => {

      console.log(error);

    });

    this.msg.onMessage((payload) => {
      // Get the data about the notification
      let notification = payload.notification;
      console.log(notification);
      // Create a Message object and add it to the array
      this.messages.push({title: notification.title, body: notification.body, iconUrl: notification.icon});
    });
  }


}
