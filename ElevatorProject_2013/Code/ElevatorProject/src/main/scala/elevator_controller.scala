import scala.actors.Actor
import scala.actors.Actor._

class elevator_queue(number_of_floors:Int) {
  //hold our floor records in an array of arrays

  // ***** Make This Private *****
  var e_queue = new Array[Array[Boolean]](number_of_floors + 1)
  // ****************************
private var upDown = new Array[Boolean](2)
  for (index <- 0 until e_queue.length) {
    // each floor record in our queue holds two values
    // i1 - up request
    // i0 - down request
    e_queue(index) = upDown
  }

  def floor_request_set(floor_num:Int, direction:Boolean) = {
    // first convert the direction bool to an index
    val direction_index:Int = convert_direction(direction)
    // then set the queue
    e_queue(floor_num)(direction_index) = true
  }

  def floor_request_clear(floor_num:Int, direction:Boolean) = {
    // first convert the direction bool to an index
    val direction_index:Int = convert_direction(direction)
    // then set the queue
    e_queue(floor_num)(direction_index) = false
  }

  // converts the up/down bool to an index
  private def convert_direction(direction_bool:Boolean):Int = {
    direction_bool match {
      // if true, we're going up
      case true => return 1
      // if false, we're going down
      case false => return 0
    }
  }
}

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
  var direction:Boolean = false
  val e_queue = new elevator_queue(top_floor)

  // list requests the controller will respond to:
  case class floor_request(floor:Int)
  case class floor_clear(floor:Int, direction:Boolean)
  case class alarm(switch:Boolean)
  case class maintenance(switch:Boolean)

  // our main action loop
  def act = {
    while (true) {
      receive {
        case floor_request(floor) => start_floor_request(floor)
        case floor_clear(floor, direction) => end_floor_request(floor,direction)
        case alarm(switch) => {
          if (switch) {
            alarm_On()
          } else {
            alarm_Off()
          }
        }
        case maintenance(switch) => {
          if (switch) {
            maintanence_Mode_On()
          } else {
            maintanence_Mode_Off()
          }
        }
        case _ => {println("Unknown Request")}
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

  def start_floor_request(floor:Int) = {
    require(floor <= top_floor)
    // first determine which way we need to move
    var required_direction:Option[Boolean] = determine_direction(location, floor)

    required_direction match {
      case None => {
        // call "drop-off"
        println("Same floor: call drop-off")
      }
      case _ => {
        e_queue.floor_request_set(floor, required_direction.get)
      }
    }
  }

  def end_floor_request(floor_number:Int, direction:Boolean) = {
    // send e_queue floor number and current direction
    e_queue.floor_request_clear(floor_number, direction)
  }
  def openDoors(currentFloor:Int)
  {
      location = currentFloor
      
      currentFloor match
      {
          case 1 =>         guiGlobals.door1.open()
          case 2 =>         guiGlobals.door2.open()
          case 3 =>         guiGlobals.door3.open()
      }
  }
   def alarm_On()
  {
          //clear request 
          e_queue.clear_All_Request()
          //check which floor is closest 
          // stop nearest floor
          val cableLength = Motor.lineOut()
          //if between 1 and 2 closer to 1 goto 1
          if(cableLength > 28)
          {
              while (cableLength!=36)
              {
                Motor.down() 
              }                
              Motor.stop()
              //open doors
              openDoors(1)
                  
          }
          //if between 1 and 2 or between 2 and 3 closer to 2 goto 2
          else if(cableLength < 28 || cableLength < 11)
          {
                  
              if(cableLength >20)
              {
                  while (cableLength!=20)
                  {
                   	 Motor.up() 
                  }
                  Motor.stop()
                  //open doors
                  openDoors(2)
      
              }
              else
              {
                  while (cableLength!=20)
                  {
                          Motor.down() 
                  }                
                  Motor.stop()
                  //open doors
                  openDoors(3)
      
              }        
          }
          else
          {
              while (cableLength!=2)
                    {
                      Motor.up() 
                    }
                    Motor.stop()
                    //open doors
                    openDoors(3)
                  
          }
           
  }

  def alarm_Off()
  {
     moveToFloor1()
  }
  def moveToFloorOne()
  {
      while(Motor.lineOut()!= 32)
       {
           Motor.douwn()
       }
       Motor.stop()
       openDoor(1)        
  }
  def maintanence_Mode_On()
  {
      //not in alarm mode
      //stop receiving requests
      //process current queue
      //move to floor 1
      moveToFloor1()
  }
  def maintanence_Mode_Off()
  {
      guiGlobals.door1.close()
  }

}