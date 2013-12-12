import scala.actors.Actor
import scala.actors.Actor._

class Passenger extends Actor {
        
        def act()
        {
                //this will be receiving message of type button
                while(true){
                        receive{
                                //when a button is received, pass it to the pressButton method
        case x: Button1 =>
          pressButton(x)
                                case _ => println("Invalid input recieved")
                        }
        }
        }
        //call Lightup method of the button that was pressed
        def pressButton(button:Button1) {
                button.lightUp()
        }
        
}