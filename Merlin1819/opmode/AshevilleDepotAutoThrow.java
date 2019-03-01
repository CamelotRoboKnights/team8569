package org.firstinspires.ftc.teamcode.team.Merlin1819.opmode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

@Disabled
@Autonomous(name = "AshevilleDepotAutoThrow")
public class AshevilleDepotAutoThrow extends AshevilleAuto {
    static double CRATERTIME = RIGHTTIME + 2.1;
    static  double BACKTIME = CRATERTIME + 1;
    static double PUSHBACKTIME = BACKTIME + 2;
    protected void afterLanding() {
        if(elapsedTime < CRATERTIME) {
            goRight(1.0); // Moves us towards the depot NOTE TO SELF: CHANGE NAME
        } else if(elapsedTime < BACKTIME) {
            goLeft(.5); // Abruptly reverses direction to throw the marker off
        } else if(elapsedTime < PUSHBACKTIME) {
                goRight(.5); // goes back towards the depot in order to push the marker in,
//                                      in case we didn't get the marker all the way in the depot
        } else {
                stopMotors(); // shuts off the motors after all actions are completed
            }
        }
    }


