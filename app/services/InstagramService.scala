package services

import scala.concurrent.Future
import play.api.libs.json.JsValue
import play.api.libs.ws.WSResponse

trait InstagramService {
  def getTenLatest(hashtags: Seq[String]): Future[Seq[WSResponse]]
}