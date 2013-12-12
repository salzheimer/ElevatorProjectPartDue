import scala.actors.Actor
import scala.actors.Actor._

class buttonController 
 extends Actor {

	var upButtonFloor1 = new modelDirectionButton(1,true,"up 1 direction")
	var upButtonFloor2 = new modelDirectionButton(2,true, "up 2 direction")
	var downButtonFloor3 = new modelDirectionButton(3,false, "down 3 direction")
  var downButtonFloor2 = new modelDirectionButton(2,false, "down 2 direction")
 	var floor1Button = new FloorButton(1,"floor 1 button")
	var floor2Button = new FloorButton(2, "floor 2 button")
	var floor3Button = new FloorButton(3,"floor 3 button")
  var stopButton = new stopButton(2,"stop button")
  var onePassenger = new Passenger()
  var theElevator = new Elevator()
  var elevatorController = new elevatorController()
	
	def act
	{
		while(true){
			receive{
				case "up 1 direction" => 
						theElevator.Requests.add(new Request(upButtonFloor1.Floor))
						onePassenger.pressButton(upButtonFloor1)
						elevatorController.processRequests(theElevator)		
				case "up 2 direction" =>
						onePassenger.pressButton(upButtonFloor2)
						elevatorController.processRequests(true, theElevator)
				case "down 3 direction" =>
						onePassenger.pressButton(downButtonFloor3)
						elevatorController.processRequests(false, theElevator)
				case "down 2 direction" => 
						onePassenger.pressButton(downButtonFloor2)
						elevatorController.processRequests(false, theElevator)
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