package org.firstinspires.ftc.teamcode.team.Merlin1819.opmode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

//Notes: currently the servo does not move, and does not knock the marker into the depot

@Autonomous(name = "AshevilleDepotAuto")
public class AshevilleDepotAuto extends AshevilleAuto {
    static double CRATERTIME = RIGHTTIME + 3;
    static  double  MARKTIME = CRATERTIME + 1;
    protected void afterLanding() {
        if(elapsedTime < CRATERTIME) {
            goRight(.25); // moves us toward the depot: NOTE TO SELF: change name
        } else if ( elapsedTime < MARKTIME){
            mark(); // should move the servo
        } else {
            stopMotors();
        }
    }

}

