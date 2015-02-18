package models.instagram

import play.api.libs.json._

case class Meta(code: Int, errorType: Option[String], errorMessage: Option[String])

object Meta {
	implicit val metaReads = Json.reads[Meta]
	implicit val metaWrites = Json.writes[Meta]
}