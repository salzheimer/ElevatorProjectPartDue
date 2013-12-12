object ControllerFactory {
	val elevatorController = new elevator_controller(3)
  elevatorController.start()
 	var btnController = new buttonController()
	btnController.start()
}
