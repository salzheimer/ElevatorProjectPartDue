import scala.actors.Actor
import scala.actors.Actor._

class buttonController
 extends Actor {

  var upButtonFloor1 = new Button1("up 1 direction")
  var upButtonFloor2 = new Button1("up 2 direction")
  var downButtonFloor3 = new Button1("down 3 direction")
  var downButtonFloor2 = new Button1("down 2 direction")
  var floor1Button = new Button1("floor 1 button")
  var floor2Button = new Button1("floor 2 button")
  var floor3Button = new Button1("floor 3 button")
  var stopButton = new Button1("stop button")
  var maintenenceOn = new Button1("maintenence on")
  var maintenenceOff = new Button1("maintenence off")
  var alarmOn = new Button1("alarm on")
  var alarmOff = new Button1("alarm off")
  var onePassenger = new Passenger()
  onePassenger.start()
  var elevator_controller = new elevator_controller(3)
  val elevatorController = new elevator_controller(3)
  elevatorController.start()
	
	def act
	{
		while(true){
			receive{
				case "up 1 direction" => 
						var loc = ControllerFactory.elevatorController.location
						onePassenger.pressButton(upButtonFloor1)
						ControllerFactory.elevatorController ! ControllerFactory.elevatorController.floor_request(1,loc)
						//elevatorController.processRequests(theElevator)		
				case "up 2 direction" =>
						var loc = ControllerFactory.elevatorController.location
						onePassenger.pressButton(upButtonFloor2)
						ControllerFactory.elevatorController ! ControllerFactory.elevatorController.floor_request(2,loc)
					//	elevatorController.processRequests(true, theElevator)
				case "down 3 direction" =>
						var loc = ControllerFactory.elevatorController.location
						onePassenger.pressButton(downButtonFloor3)
						ControllerFactory.elevatorController ! ControllerFactory.elevatorController.floor_request(3,loc)
				case "down 2 direction" => 
				    var loc = ControllerFactory.elevatorController.location
						onePassenger.pressButton(downButtonFloor2)
						ControllerFactory.elevatorController ! ControllerFactory.elevatorController.floor_request(2,loc)
					//	elevatorController.processRequests(false, theElevator)
				case "floor 1 button" => 
						if (checkDoorsOpen()){
								var loc = ControllerFactory.elevatorController.location
							  onePassenger.pressButton(floor1Button)
								ControllerFactory.elevatorController ! ControllerFactory.elevatorController.floor_request(1,loc)
						}
						else{
							println("No doors open")
						}
						
				case "floor 2 button" =>
						if (checkDoorsOpen())
						{
							  onePassenger.pressButton(floor2Button)
								var loc = ControllerFactory.elevatorController.location	
								ControllerFactory.elevatorController ! ControllerFactory.elevatorController.floor_request(2,loc)
						}else {
							println("No doors open")
						}
				case "floor 3 button" =>
				    
						if (checkDoorsOpen()){
							var loc = ControllerFactory.elevatorController.location
							onePassenger.pressButton(floor3Button)
							ControllerFactory.elevatorController ! ControllerFactory.elevatorController.floor_request(3,loc)
						}
						else {
							println("No doors open")
						}
						
			//	case "stop button" => 
			//			onePassenger.pressButton(stopButton)
			//			elevatorController.processRequests()
			}
	}
	}

	def checkDoorsOpen():Boolean = {
		if (ControllerFactory.elevatorController.door1.isOpen || ControllerFactory.elevatorController.door2.isOpen
		|| ControllerFactory.elevatorController.door3.isOpen) return true
		return false
	}
 }
	
