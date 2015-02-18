import services.InstagramService
import org.scalatest.Matchers
import org.scalatest.FunSpec
import play.api.test._
import org.scalatestplus.play._
import play.api.libs.json.Json
import io.Source
import models.instagram.InstagramResponseEnvelope
import play.api.Logger
import models.instagram.InstagramData

class InstagramServiceSpec extends FunSpec with Matchers with OneAppPerSuite{
  val fakeApplication = FakeApplication
	describe("InstagramService") {
	  it("can parse a successful tag data response") {
	    val x = io.Source.fromURL(getClass().getResource("/instagram_tag_data.txt")) 
	    val z = x.mkString
	    val y = Json.parse(z).validate[InstagramData]
	    y.recoverTotal(error => Logger.error(s"Error parsing json: $error"))
	    x.close()
	    val model = y.asOpt
	    model should not be (None)
	  }
	  
	  it("can parse a successful tag response") {
	    val x = io.Source.fromURL(getClass().getResource("/instagram_tag_response.txt")) 
	    val z = x.mkString
	    val y = Json.parse(z).validate[InstagramResponseEnvelope]
	    y.recoverTotal(error => Logger.error(s"Error parsing json: $error"))
	    x.close()
	    val model = y.asOpt
	    model should not be (None)
	  }
	} 
}