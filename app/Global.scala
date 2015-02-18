

import di.WebModule
import play.api.GlobalSettings
import scaldi.play.ScaldiSupport

object Global extends GlobalSettings with ScaldiSupport {
  override def applicationModule = new WebModule

}