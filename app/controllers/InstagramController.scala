package controllers

import play.api.mvc.Controller
import play.api.mvc.Action
import play.api.libs.json.Json
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import scala.concurrent.Future
import services.InstagramService
import models.instagram.InstagramResponseEnvelope
import play.api.mvc.BodyParser
import scaldi.{Injector,Injectable}
import scala.util.Success
import play.api.libs.json.JsValueSerializer
import play.api.libs.json.JsValue
import play.api.libs.json.{ JsSuccess, JsError }
import play.api.libs.json.JsSuccess
import play.api.libs.json.JsSuccess
import org.joda.time.DateTime
import play.api.Logger

class InstagramController(implicit inj: Injector) extends Controller with Injectable {
  val instagramService = inject[InstagramService]

  def getLatestInstagram() = Action.async { request =>
    val instagramFuture = instagramService.getTenLatest(List("wasatch", "skiutah", "skialta", "wasatchsnowforecast"))
    instagramFuture.map {
      case responses => {
        val responsesJson = responses.map{a => 
          val json = a.json.validate[InstagramResponseEnvelope]
          json match {
            case e: JsError => println(e)
            case s: JsSuccess[InstagramResponseEnvelope] => s.get 
          }
          json.get
        }
        //val responsessJson = responsesJson.get
        val mergedData = responsesJson.foldLeft(responsesJson(0)) { (resp, respTwo) =>
          resp.copy(data = (resp.data.toSet ++ respTwo.data).toList)
        }
        val y = mergedData.copy(data = (mergedData.data.sortBy(d => -d.created_time.toLong ).take(42)))
        Logger.debug(y.toString())
        Ok(Json.toJson(y))
      }
    }
  }
}