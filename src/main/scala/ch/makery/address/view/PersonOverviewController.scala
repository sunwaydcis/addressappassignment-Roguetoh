package ch.makery.address.view

import ch.makery.address.model.Person
import ch.makery.address.MainApp
import ch.makery.address.MainApp.stage
import ch.makery.address.util.DateUtil._
import scalafx.scene.control.{Alert, Label, TableColumn, TableView}
import scalafxml.core.macros.sfxml
import scalafx.beans.property.StringProperty
import scalafx.Includes._
import scalafx.event.ActionEvent
import scalafx.scene.Scene
import scalafx.scene.control.Alert.AlertType
import scalafx.stage.{Modality, Stage}
import scalafxml.core.{FXMLLoader, NoDependencyResolver}

@sfxml
class PersonOverviewController(
                                private val personTable : TableView[Person],
                                private val firstNameColumn : TableColumn[Person, String],
                                private val lastNameColumn : TableColumn[Person, String],
                                private val firstNameLabel : Label,
                                private val lastNameLabel : Label,
                                private val streetLabel : Label,
                                private val postalCodeLabel : Label,
                                private val cityLabel :  Label,
                                private val  birthdayLabel : Label
                              ) {
  // initialize Table View display contents model
  personTable.items = MainApp.personData
  // initialize column's cell values
  firstNameColumn.cellValueFactory = {_.value.firstName}
  lastNameColumn.cellValueFactory  = {_.value.lastName}

  private def showPersonDetails (person : Option[Person]) = {
    person match {
      case Some(person) =>
        // Fill the labels with info from the person object.
        firstNameLabel.text <== person.firstName
        lastNameLabel.text  <== person.lastName
        streetLabel.text    <== person.street
        cityLabel.text      <== person.city;
        postalCodeLabel.text = person.postalCode.value.toString
        birthdayLabel.text   = person.date.value.asString

      case None =>
        // Person is null, remove all the text.
        firstNameLabel.text.unbind()
        lastNameLabel.text.unbind()
        streetLabel.text.unbind()
        cityLabel.text.unbind()

        firstNameLabel.text = ""
        lastNameLabel.text  = ""
        streetLabel.text    = ""
        postalCodeLabel.text= ""
        cityLabel.text      = ""
        birthdayLabel.text  = ""
    }
  }

  showPersonDetails(None)

  personTable.selectionModel().selectedItem.onChange((a, b, c) => {
    showPersonDetails(Option(c))
  })

  def handleDeletePerson(action : ActionEvent) = {
    val selectedIndex = personTable.selectionModel().selectedIndex.value
    if (selectedIndex >= 0) {
      personTable.items().remove(selectedIndex);
    }
    else { // Nothing selected.
      val alert = new Alert(AlertType.Error){
        initOwner(MainApp.stage)
        title       = "No Selection"
        headerText  = "No Person Selected"
        contentText = "Please select a person in the table."
      }.showAndWait()
    }
  }

  def handleNewPerson(action : ActionEvent) = {
    val person = new Person("","")
    val okClicked = MainApp.showPersonEditDialog(person);
    if (okClicked) {
      MainApp.personData += person
    }
  }
  def handleEditPerson(action : ActionEvent) = {
    val selectedPerson = personTable.selectionModel().selectedItem.value
    if (selectedPerson != null) {
      val okClicked = MainApp.showPersonEditDialog(selectedPerson)

      if (okClicked) showPersonDetails(Some(selectedPerson))

    } else {
      // Nothing selected.
      val alert = new Alert(Alert.AlertType.Warning){
        initOwner(MainApp.stage)
        title       = "No Selection"
        headerText  = "No Person Selected"
        contentText = "Please select a person in the table."
      }.showAndWait()
    }
  }

}