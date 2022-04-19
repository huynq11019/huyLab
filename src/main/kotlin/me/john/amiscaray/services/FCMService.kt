package me.john.amiscaray.services

import org.springframework.stereotype.Service
import com.google.firebase.messaging.*
import me.john.amiscaray.dtos.SubscriptionRequest
import me.john.amiscaray.dtos.DirectNotification
import me.john.amiscaray.dtos.TopicNotification
import org.slf4j.Logger
import org.slf4j.LoggerFactory


@Service
class FCMService {
    var logger: Logger = LoggerFactory.getLogger(FirebaseInitializer::class.java)

    fun sendNotificationToTarget(notification: DirectNotification){

        val message = Message.builder()
                .setWebpushConfig(
                        WebpushConfig.builder()
                                .setNotification(
                                        WebpushNotification.builder()
                                                .setTitle(notification.title)
                                                .setBody(notification.message)
                                                .setIcon("https://ci5.googleusercontent.com/proxy/EpnPPwkrn3YityMtiMJcNnJGW3Ki-_nYdKVzIwXNCW7bsPHQR7gk3vwbNUQypkbyrR61jzz428f3bPcGYCT9ol2GWmBzdEKFv12X3pUOksU3cwZdwwe1nk5hIaGFLqWl2hWGaXAXC90ZBgM6o9JXr9qjGeEDPySujlJEbR1Y5OziU1eiX4ygN5rA0P8tonzk7voNrFeXm5e6rARnGPLzZ71QbFf8ny91mRu5pSWV5ffryQr7FtYTvCL2xqsccM29c3W7TFkY0jsXZEE6qn3mFSDOng=s0-d-e1-ft#https://salt.topdev.vn/5ckrApB0SomXV1fBUTfrHoDqtrag76xkiGS2fLK6ebI/fit/160/90/ce/1/aHR0cHM6Ly9hc3NldHMudG9wZGV2LnZuL2ZpbGVzL2xvZ29zLzVkYmIxZTMzNTJhZGMyMWQwMjMwNjE5OTBlOGNmNGZkLnBuZw/70203.jpg")
                                                .build()
                                ).build()
                )
                .setToken(notification.target)
                .build()
        logger.info("Sending notification to target: ${notification.target}")
        FirebaseMessaging.getInstance().sendAsync(message)

    }

    fun sendNotificationToTopic(notification: TopicNotification){

        val message = Message.builder()
                .setWebpushConfig(
                        WebpushConfig.builder()
                                .setNotification(
                                        WebpushNotification.builder()
                                                .setTitle(notification.title)
                                                .setBody(notification.message)
                                                .setIcon("https://ci5.googleusercontent.com/proxy/EpnPPwkrn3YityMtiMJcNnJGW3Ki-_nYdKVzIwXNCW7bsPHQR7gk3vwbNUQypkbyrR61jzz428f3bPcGYCT9ol2GWmBzdEKFv12X3pUOksU3cwZdwwe1nk5hIaGFLqWl2hWGaXAXC90ZBgM6o9JXr9qjGeEDPySujlJEbR1Y5OziU1eiX4ygN5rA0P8tonzk7voNrFeXm5e6rARnGPLzZ71QbFf8ny91mRu5pSWV5ffryQr7FtYTvCL2xqsccM29c3W7TFkY0jsXZEE6qn3mFSDOng=s0-d-e1-ft#https://salt.topdev.vn/5ckrApB0SomXV1fBUTfrHoDqtrag76xkiGS2fLK6ebI/fit/160/90/ce/1/aHR0cHM6Ly9hc3NldHMudG9wZGV2LnZuL2ZpbGVzL2xvZ29zLzVkYmIxZTMzNTJhZGMyMWQwMjMwNjE5OTBlOGNmNGZkLnBuZw/70203.jpg")
                                                .build()
                                ).build()
                ).setTopic(notification.topic)
                .build()
        logger.info("Sending toppic notification to target: ${notification.topic}")

        FirebaseMessaging.getInstance().sendAsync(message)
//        FirebaseMessaging.getInstance().send(message)

    }

    fun subscribeToTopic(subscription: SubscriptionRequest){

        FirebaseMessaging.getInstance().subscribeToTopic(listOf(subscription.subscriber), subscription.topic)
        logger.info("Sending toppic notification to target: ${subscription.topic}")

    }

}
