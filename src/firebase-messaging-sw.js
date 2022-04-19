
importScripts('https://www.gstatic.com/firebasejs/8.7.0/firebase-app.js')
importScripts('https://www.gstatic.com/firebasejs/8.7.0/firebase-messaging.js')

firebase.initializeApp({
  apiKey: "AIzaSyBo2e9jEM9Yvx1EUP2-U5MIPoBrH7CwT10",
  authDomain: "fe-travel.firebaseapp.com",
  projectId: "fe-travel",
  storageBucket: "fe-travel.appspot.com",
  messagingSenderId: "583275033902",
  appId: "1:583275033902:web:719996161378a4883176bf",
  measurementId: "G-CD8HSCY3PT"
})

const messaging = firebase.messaging();

// When a notification is received, the push event is called.
self.addEventListener('push', function (event) {

  console.log("event:push")
  let messageTitle = "MESSAGETITLE"
  let messageBody = "MESSAGEBODY"
  let messageTag = "MESSAGETAG"

  const notificationPromise = self.registration.showNotification(
    messageTitle,
    {
      body: messageBody,
      tag: messageTag
    });

  event.waitUntil(notificationPromise);

}, false)

// If the web application is in the background, setBackGroundMessageHandler is called.
messaging.setBackgroundMessageHandler(function (payload) {


  console.log("backgroundMessage", payload)

  let messageTitle = "MESSAGETITLE"
  let messageBody = "MESSAGEBODY"

  return self.registration.showNotification(
    messageTitle,
    {
      body: messageBody,
      tag: messageTag
    });
});
