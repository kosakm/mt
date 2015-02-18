package models.instagram

import play.api.libs.json._
import play.api.libs.functional.syntax._

case class TypeModel(a: String, b: String)

object TypeModel {
    implicit val typeModelReads: Reads[TypeModel] = (
    (JsPath \ "a").read[String] and
    (JsPath \ "b").read[String])(TypeModel.apply _)


}