class elevatorDoor(var floorNum:Int) {
	
	var isOpen: Boolean = false
	
  def open() = {
  	floorNum match
  	{
			case 1 => SystemStatus.door1Open = true; isOpen = true
			case 2 => SystemStatus.door2Open = true; isOpen = true
			case 3 =>SystemStatus.door3Open = true; isOpen = true
  	}
  }
  

  def closeDoor() = {
  	floorNum match
  	{
			case 1 => SystemStatus.door1Open = false; isOpen = false
			case 2 => SystemStatus.door2Open = false; isOpen = false
			case 3 =>SystemStatus.door3Open = false; isOpen = false
  	}
  }
}