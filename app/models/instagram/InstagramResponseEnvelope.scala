package models.instagram

import play.api.libs.json.Json

/**
 * 
 */
case class InstagramResponseEnvelope(meta: Meta, data: List[InstagramData], pagination: InstagramPagination)

object InstagramResponseEnvelope {
	implicit val envelopeReads = Json.reads[InstagramResponseEnvelope]
	implicit val envelopeWrites = Json.writes[InstagramResponseEnvelope]
}