package models.instagram

import play.api.libs.json._ // JSON library
import play.api.libs.json.Reads._ // Custom validation helpers
import play.api.libs.functional.syntax._ // Combinator syntax

  case class Location(latitude: BigDecimal, name: String, longitude: BigDecimal, id: Long)
  
  case class Comments(count: Int, data: List[CommentData])
  
  case class CommentData(created_time: String, text: String, from: User)
  
  case class Likes(count: Int, data: List[User])
  
  case class Images(low_resolution: Picture, thumbnail: Picture, standard_resolution: Picture)
  
  case class Picture(url: String, width: Int, height: Int)
  
  case class Position(x: Float, y: Float)
  
  case class Caption(created_time: Option[String])
  
  case class User(username: String, profile_picture: String, id: String, full_name: String)
  
  case class UsersInPhoto(position: Position, user: User)
  
  case class InstagramData(tags: Seq[String], mediaType: String, location: Option[Location], comments: Comments, 
    filter: String, created_time: String, link: String, likes: Likes, images: Images, users_in_photo: Seq[UsersInPhoto],
	caption: Caption, id: String, user: User)

	
object InstagramData {
  	implicit val locationReads = Json.reads[Location]
	implicit val locationWrites = Json.writes[Location]
  	
	implicit val userReads = Json.reads[User]
	implicit val userWrites = Json.writes[User]
	
	implicit val commentDataReads = Json.reads[CommentData]
	implicit val commentDataWrites = Json.writes[CommentData]
  	
	implicit val commentReads = Json.reads[Comments]
	implicit val commentWrites = Json.writes[Comments] 	
	
	implicit val likesReads = Json.reads[Likes]
	implicit val likesWrites = Json.writes[Likes]

	implicit val pictureReads = Json.reads[Picture]
	implicit val pictureWrites = Json.writes[Picture]
  	
	implicit val imagesReads = Json.reads[Images]
	implicit val imagesWrites = Json.writes[Images]
	
	implicit val positionReads = Json.reads[Position]
	implicit val positionWrites = Json.writes[Position]
	
	implicit val captionReads = Json.reads[Caption]
	implicit val captionWrites = Json.writes[Caption]
  	
  	implicit val usersInPhotoReads = Json.reads[UsersInPhoto]
  	implicit val usersInPhotoWrites = Json.writes[UsersInPhoto]
	
  	implicit val instagramReads: Reads[InstagramData] = (
  			(JsPath \ "tags").read[Seq[String]] and
  			(JsPath \ "type").read[String] and
  			(JsPath \ "location").read[Option[Location]] and
  			(JsPath \ "comments").read[Comments] and
  			(JsPath \ "filter").read[String] and
  			(JsPath \ "created_time").read[String] and
  			(JsPath \ "link").read[String] and
  			(JsPath \ "likes").read[Likes] and
  			(JsPath \ "images").read[Images] and
  			(JsPath \ "users_in_photo").read[Seq[UsersInPhoto]] and
  			(JsPath \ "caption").read[Caption] and
  			(JsPath \ "id").read[String] and
  			(JsPath \ "user").read[User]
  			).apply(InstagramData.apply _)
  			
  	  	implicit val instagramWrites: Writes[InstagramData] = (
  			(JsPath \ "tags").write[Seq[String]] and
  			(JsPath \ "type").write[String] and
  			(JsPath \ "location").write[Option[Location]] and
  			(JsPath \ "comments").write[Comments] and
  			(JsPath \ "filter").write[String] and
  			(JsPath \ "created_time").write[String] and
  			(JsPath \ "link").write[String] and
  			(JsPath \ "likes").write[Likes] and
  			(JsPath \ "images").write[Images] and
  			(JsPath \ "users_in_photo").write[Seq[UsersInPhoto]] and
  			(JsPath \ "caption").write[Caption] and
  //			(JsPath \ "user_has_liked").write[Option[Boolean]] and
  			(JsPath \ "id").write[String] and
  			(JsPath \ "user").write[User]
  			)(unlift(InstagramData.unapply))

}