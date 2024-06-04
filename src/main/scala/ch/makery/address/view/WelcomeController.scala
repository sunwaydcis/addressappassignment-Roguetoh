package ch.makery.address.view

import ch.makery.address.MainApp
import scalafxml.core.macros.sfxml

@sfxml
class WelcomeController(){
  //get started button handler
  def handleGetStarted(): Unit ={
    MainApp.showPersonOverview()
  }
}