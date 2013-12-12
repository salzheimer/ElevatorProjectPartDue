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
  var theElevator = new Elevator()
  var elevator_controller = new elevator_controller(3)
  val elevatorController = new elevator_controller(3)
  elevatorController.start()
	
	def act
	{
		while(true){
			receive{
				case "up 1 direction" => 
						onePassenger.pressButton(upButtonFloor1)
						//elevatorController.processRequests(theElevator)		
				case "up 2 direction" =>
						onePassenger.pressButton(upButtonFloor2)
					//	elevatorController.processRequests(true, theElevator)
				case "down 3 direction" =>
						onePassenger.pressButton(downButtonFloor3)
						elevatorController ! elevatorController.floor_request(3)
						println("hi")
				case "down 2 direction" => 
						onePassenger.pressButton(downButtonFloor2)
					//	elevatorController.processRequests(false, theElevator)
				//case "floor 1 button" => 
				//		onePassenger.pressButton(floor1Button)
				//		elevatorController.processRequests()
			//	case "floor 2 button" =>
			//			onePassenger.pressButton(floor2Button)
			//			elevatorController.processRequests()
			//	case "floor 3 button" =>
			//			onePassenger.pressButton(floor3Button)
			//			elevatorController.processRequests()
			//	case "stop button" => 
			//			onePassenger.pressButton(stopButton)
			//			elevatorController.processRequests()
			}
	}
	}
	def upDownPressed(flooorNumber: Int)
	{
		
	}
	

	}