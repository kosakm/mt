import org.scalatest.FunSpec
import org.scalatest.Matchers
import play.api.libs.json.Json
import models.instagram.TypeModel
import play.api.libs.json.JsSuccess
import play.api.libs.json.JsError

class TypeModelSpec extends FunSpec with Matchers {
	describe("TypeModel") {
	  it("can parse json with different json field name than the case class field name") {
	    val jsonStr = """{"a": "somelat",
	    				  "b":"somelong:"}"""
	    val json = Json.toJson(jsonStr)
	    println(json)
	    val typeMod = json.validate[TypeModel]
	    typeMod match {
	        case s: JsSuccess[TypeModel] => {println}
	        case e: JsError => println("Errors: " + JsError.toFlatJson(e).toString())
	    }
	    
	    typeMod.get.a should be ("somelat")
	  }
	}
}