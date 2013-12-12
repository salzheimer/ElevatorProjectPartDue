import scala.actors.Actor
import scala.actors.Actor._

class elevatorController 
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
  var elevatorController = new elevatorController()
  var door1 = new elevatorDoor(1)
  var door2 = new elevatorDoor(2)
  var door3 = new elevatorDoor(3)
 
	
	def act
	{
		while(true){
			receive{
				case "upRequest" => 
						println("1")
			}
	}
	}

	def processRequests(elevator:Elevator) = 
	{
		while (!(elevator.Requests.isEmpty()))
		{
			for(i <- elevator.Requests)
				{	
					processFloor(elevator)
				}
				else if (elevator.direction){
					moveMotorUpOneFloor(elevator)
					processFloor(elevator)					
				}
				else{
					moveMotorDownOneFloor(elevator)
					processFloor(elevator)
				}
			}
		}

	def processFloor(elevator:Elevator) = {
			var Requests = new ArrayBuffer[ElevatorDoor]()	
			Requests + door1
			Requests + door2
			Requests + door3
			for ((i,index) <- elevator.requests). {
			if ((i.requestedFloor == elevator.location) && checkDirection(elevator,i)){
				elevatorDoors.open()
				takeRequests()
				elevatorDoors.close()
			}
		}
	}

	def checkDirection(elevator:Elevator, request:Request) = {
		if (elevator.direction && request.requestedFloor > elevator.location){
			true
		}
		else if (!(elevator.direction) && request.requestedFloor < elevator.location){
			true
		}
		else false
	}
	
		def moveMotorUpOneFloor(elevator:Elevator)
		{
				var currentFloor = elevator.location
				var desiredFt = (elevator.location == 1) ? 20 : 36
				while (Motor.lineOut() != desiredFt)
				{
					Motor.up()
				}
				Motor.stop()
		}
		
		def moveMotorDownOneFloor(elevator:Elevator)
		{
				var currentFloor = elevator.location
				var desiredFt = (elevator.location == 3) ? 20 : 2
				while (Motor.lineOut() != desiredFt)
				{
					Motor.down()
				}
				Motor.stop()
		}
 }

 for ((line, i) <- Arr.zipWithIndex) {
   println(i, line)
}