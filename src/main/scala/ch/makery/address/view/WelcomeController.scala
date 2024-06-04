package ch.makery.address.view

import ch.makery.address.MainApp
import scalafx.scene.control.TextField
import scalafxml.core.macros.sfxml

@sfxml
class WelcomeController(private val text1: TextField,
                        private val text2: TextField){
  text2.text <==> text1.text

  //get started button handler
  def handleGetStarted(): Unit ={
    MainApp.showPersonOverview()
  }
}