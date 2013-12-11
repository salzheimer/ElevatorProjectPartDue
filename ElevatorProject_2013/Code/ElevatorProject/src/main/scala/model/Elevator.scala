scala.collection.mutable.ArrayBuffer

class Elevator {
	var location: Int = 1
	var direcion: Boolean = true
	var alarmMode: Boolean = false
	var maintenenceMode: Boolean = false
	var numPassengers: Int = 0
	var Requests = new ArrayBuffer[Request]()
	
	def callElevator(button:modelDirectionButton) = {
		button.upDown match {
			case true =>
				upRequests += button.currentFloor
			case false =>
				downRequests += button.currentFloor
		}
	}
}