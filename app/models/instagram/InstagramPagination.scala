package models.instagram

import play.api.libs.json._

case class InstagramPagination(next_max_tag_id: String, next_url: String, deprecation_warning: String, next_max_id: String)

object InstagramPagination {
	implicit val paginationReads = Json.reads[InstagramPagination]
	implicit val paginationWrites = Json.writes[InstagramPagination]
}