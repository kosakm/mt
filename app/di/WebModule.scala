package di

import controllers.InstagramController
import services.InstagramService
import services.InstagramServiceImpl
import scaldi.Module
import scaldi.play.condition._

class WebModule extends Module {
  binding to new InstagramController

  bind [InstagramService] when inProdMode to new InstagramServiceImpl
  bind [InstagramService] when (inDevMode or inTestMode) to new InstagramServiceImpl
}