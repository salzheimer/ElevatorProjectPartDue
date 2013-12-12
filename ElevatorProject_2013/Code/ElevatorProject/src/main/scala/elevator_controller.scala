import scala.actors.Actor
import scala.actors.Actor._

class elevator_controller(top_floor:Int) extends Actor {
  // the elevator controller knows:
  // how tall the building is
  // which floor the elevator is on
  // which direction the elevator is moving

  // Everything is this class should be private
  // *Except
  // ** The list of case classes below
  // ** The act() method

//val top_floor = top_floor
  var location:Int = 1
  var direction:Boolean = true
  val e_queue = new elevator_queue(top_floor)
  val door1 = new elevatorDoor(1)
  val door2 = new elevatorDoor(2)
  val door3 = new elevatorDoor(3)
	var waitingForRequest:Boolean = false
  // Give info from from motor.lineOut() better names:
  // Returns the amount of feet of cable the motor has let out.
  // 36 ft is floor 1
  // 20 ft is floor 2
  // 2 ft is floor 3
  val floors_list:List[Int] = List(0, 36, 20, 2)

  // list requests the controller will respond to:
  case class floor_request(floor:Int, sendingFloor:Int)
  case class floor_clear(floor:Int, direction:Boolean)
  case class alarm(switch:Boolean)
  case class maintenance(switch:Boolean)

  // list internal requests the controller will call on itself:
  private case class floor_move(floor_number:Int, direction:Boolean)
  private case class doors_toggle(door:guiDoor, state:String)

  // our main action loop
  def act = {
    while (true) {
      receive {
        case floor_request(floor,sendingFloor:Int) => 
    				//if there is an open door, and the floor the button was called from is open to the floor
    				//this open door is on, we wish to close this elevator door
         		if(checkDoorsOpen() && sendingFloor == returnOpenDoor()) controlCloseDoors(sendingFloor)
        		start_floor_request(floor)
        case floor_clear(floor, direction) => end_floor_request(floor, direction)
        case alarm(switch) => {
          if (switch) {
            println("fire alarm: on")
          } else {
            println("fire alarm: off")
          }
        }
        case maintenance(switch) => {
          if (switch) {
            println("maintence mode: on")
          } else {
            println("maintenace mode: off")
          }
        }
        case floor_move(floor_number, direction) => {
        	println(floors_list(floor_number))
        	println(Motor.lineOut)
          while(Motor.lineOut() != floors_list(floor_number)) {
            if (direction) {
              Motor.up()
            } else {
              Motor.down()
            }
          }
          Motor.stop()
          println("sending drop passenger")
          drop_passenger(floor_number)
        }
        case doors_toggle(door, state) => {
          println("recieved " + door + state)
          state match {
            case "open" => {
              door.open()
              println("from doors_toggle: doors open")
            }
            case "close" => {
              door.close()
              println("from doors_toggle: doors closed")
            }
          }
        }
        case _ => {println("unknown request")}
      }
    }
  }

  def determine_direction(current_floor:Int, requested_floor:Int):Option[Boolean] = {
    var r_val = None: Option[Boolean]
    if (current_floor < requested_floor) {r_val = Option(true)}
    if (current_floor > requested_floor) {r_val = Option(false)}
    if (current_floor == requested_floor){r_val = None}
    return r_val
  }

	def checkDoorsOpen():Boolean = {
			if (ControllerFactory.elevatorController.door1.isOpen || ControllerFactory.elevatorController.door2.isOpen
			|| ControllerFactory.elevatorController.door3.isOpen) return true
			return false
		}
		
	def returnOpenDoor():Int = {
			if (ControllerFactory.elevatorController.door1.isOpen){
				return 1
			}
			else if(ControllerFactory.elevatorController.door2.isOpen){
				return 2
			}
			else{
				 return 3
			}
		}

  def start_floor_request(floor:Int) = {
   // require(floor <= top_floor)
    // first determine which way we need to move
    var required_direction:Option[Boolean] = determine_direction(location, floor)
    required_direction match {
      case None => {
        // call "drop-off"
        println("same floor: call drop-off")
        drop_passenger(floor)
        e_queue.floor_request_set(floor, direction)
      }
      case _ => {
        e_queue.floor_request_set(floor, required_direction.get)
        ControllerFactory.elevatorController ! floor_move(floor, required_direction.get)
      }
    }
  }

  def end_floor_request(floor_number:Int, direction:Boolean) = {
    // send e_queue floor number and current direction
    e_queue.floor_request_clear(floor_number, direction)
    floor_number match {
    	case 1 => //door1.closeDoor()
    	case 2 => //door2.closeDoor()
    	case 3 => //door3.closeDoor()
    }
  }

  def controlOpenDoors(doorNum:Int)
  {
    waitingForRequest = true
		doorNum match{
			case 1 => door1.open();location = 1;
			case 2 => door2.open(); location = 2;
			case 3 => door3.open();location = 3;
		}
  }

  def controlCloseDoors(doorNum:Int)
  {
  	waitingForRequest = false
    waitingForRequest = true
		doorNum match{
			case 1 => door1.closeDoor()
			case 2 => door2.closeDoor()
			case 3 => door3.closeDoor()
		}
  }

  def breakRequest()
  {
		waitingForRequest = false
  }

  def drop_passenger(floor_number:Int) = {
    // this is for testing, we'll need to repurpose it elsewhere
    println("starting drop passenger")
    //match bsaed on floor number
    println(floor_number)
    floor_number match {
      case 1 => 
      	//open door 1
      		controlOpenDoors(1)
      	//door1.closeDoor()
      	//dim light on floor 1 
      	ControllerFactory.btnController.upButtonFloor1.cancelLight()
      	ControllerFactory.btnController.floor1Button.cancelLight()
      	controlCloseDoors(2)
      case 2 => 
      	//door2.closeDoor()
      	if (direction) {
      		ControllerFactory.btnController.upButtonFloor2.cancelLight()
      	ControllerFactory.btnController.floor2Button.cancelLight()
      	}
      	else
      	{
					ControllerFactory.btnController.downButtonFloor2.cancelLight()
					ControllerFactory.btnController.floor2Button.cancelLight()
      	}
      	controlOpenDoors(2)
      	
      case 3 => 
      	//open door 3
      	controlOpenDoors(3)
      	//dim light on floor 3
      	ControllerFactory.btnController.downButtonFloor3.cancelLight()
      	ControllerFactory.btnController.floor3Button.cancelLight()
      	waitingForRequest = true
    }

  //  println("open doors")
  //  guiGlobals.elev_con ! doors_toggle(door, "open")
   // Thread.sleep(10000)
   // println("close doors")
   // guiGlobals.elev_con ! doors_toggle(door, "close")
  }
}