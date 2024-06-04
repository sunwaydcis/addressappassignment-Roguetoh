package ch.makery.address.model

import scalafx.beans.property.{StringProperty, IntegerProperty, ObjectProperty}
import java.time.LocalDate;

class Person ( _firstName : String, _lastName : String ){
  var firstName  = new StringProperty(_firstName)
  var lastName   = new StringProperty(_lastName)
  var street     = new StringProperty("some Street")
  //var postalCode = IntegerProperty(1234)
  var postalCode = ObjectProperty[Int](1234)
  var city       = new StringProperty("some city")
  var date       = ObjectProperty[LocalDate](LocalDate.of(1999, 2, 21))
}