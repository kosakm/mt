package services

import play.api.Play.current
import play.api.Play
import play.api.libs.ws._
import play.api.libs.ws.ning.NingAsyncHttpClientConfigBuilder
import scala.concurrent.Future
import play.api.libs.json.Json
import models.instagram.InstagramResponseEnvelope
import scala.concurrent.ExecutionContext.Implicits.global
import play.api.Logger
import scala.util.Success
import play.api.libs.json.JsValue
import play.api.libs.json.JsNull
import models.instagram.InstagramTagResponse

class InstagramServiceImpl extends InstagramService {
  private val defaultMax = "10"
  private val defaultMin = "1"
  private val vClientId = "CLIENT-ID"
  private val clientConfigName = "instagram.clientid"
  val recentTagUrl = "https://api.instagram.com/v1/tags/tagname/media/recent?"

  def getTenLatest(hashtags: Seq[String]) = {
    val instagramFuture = Future.sequence(hashtags.map { hashtag =>
      val url = recentTagUrl.replaceFirst("tagname", hashtag)
      println("URL=" + url)
      WS.url(url)
        .withQueryString("client_id" -> Play.current.configuration.getString(clientConfigName).getOrElse("4f3ba9a844be4e2384e68943c4e28d16"),
                         "MIN_TAG_ID" -> defaultMin,
                         "MAX_TAG_ID" -> defaultMax)
        .get
    })
    instagramFuture
  }
}