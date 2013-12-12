
class Elevator {
	var location: Int = 1
	var direction: Boolean = true
	var alarmMode: Boolean = false
	var maintenenceMode: Boolean = false
	var numPassengers: Int = 0
	var elevatorDoors = Array[elevatorDoor](new elevatorDoor(1), new elevatorDoor(2), new elevatorDoor(3))
	
	def moveFloor() = {
		direction match {
			case true =>
			//	upRequests += button.currentFloor
			case false =>
		//		downRequests += button.currentFloor
		}
	}

	def openDoor(doorNum: Int)
	{

	}
}