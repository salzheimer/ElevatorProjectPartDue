object guiOutput {

  def Floor1Up()
  {
    //Place your code here for when the up button is pressed on floor 1.
    println("Floor 1 Up Button Pressed")
    ControllerFactory.btnController ! "up 1 direction"
  }

  def Floor2Up()
  {
    //Place your code here for when the up button is pressed on floor 2.
    println("Floor 2 Up Button Pressed")
    //guiGlobals.elev_con ! guiGlobals.elev_con.floor_request(3)
    ControllerFactory.btnController ! "up 2 direction"

  }
  def Floor2Down()
  {
    //Place your code here for when the down button is pressed on floor2.
    println("Floor 2 Down Button Pressed")
    ControllerFactory.btnController ! "down 2 direction"
  }

  def Floor3Down()
  {
    //Place your code here for when the down button is pressed on floor3.
    println("Floor 3 Down Button Pressed")
    println("*** Sending elve_con.floor_request(3) ***")
    //guiGlobals.elev_con ! guiGlobals.elev_con.floor_request(3)
    ControllerFactory.btnController ! "down 3 direction"
    println("*** elve_con.floor_request(3) Sent ***")
  }

  def elevFloor1()
  {
    //Place your code here for when the 1 button is pressed in the elevator.
    println("Elevator Button 1 Pressed")
    ControllerFactory.btnController ! "floor 1 button"
  }

  def elevFloor2()
  {
    //Place your code here for when the 2 button is pressed in the elevator
    println("Elevator Button 2 Pressed")
    ControllerFactory.btnController ! "floor 2 button"
  }

  def elevFloor3()
  {
    //Place your code here for when the 3 button is pressed in the elevator
    println("Elevator Button 3 Pressed")
    ControllerFactory.btnController ! "floor 3 button"
  }

  def elevStop()
  {
    //Place your code here for when the stop button is pressed in the elevator
    println("Elevator Stop Button Pressed")
  }

  def MaintenanceModeOn()
  {
    //Place your code here for when the maintanence mode is switched to on.
    println("Maintenance Mode On")
  }
  def MaintenanceModeOff()
  {
    //Place your code here for when the maintanence mode is switched to off.
    println("Maintenance Mode Off")
  }

  def AlarmModeOn()
  {
    //Place your code here for when the alarm mode is switched to on.
    println("Alarm On")
  }

  def AlarmModeOff()
  {
    //Place your code here for when the alarm mode is switched to off.
    println("Alarm Off")
  }


}