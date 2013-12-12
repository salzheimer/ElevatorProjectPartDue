
class Elevator {
	var location: Int = 1
	var direction: Boolean = true
	var alarmMode: Boolean = false
	var maintenenceMode: Boolean = false
	var numPassengers: Int = 0
	
	def callElevator() = {
		direction match {
			case true =>
			//	upRequests += button.currentFloor
			println("hi")
			case false =>
		//		downRequests += button.currentFloor
		println("hi")
		}
	}
}