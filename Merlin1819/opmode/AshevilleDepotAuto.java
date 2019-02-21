package org.firstinspires.ftc.teamcode.team.Merlin1819.opmode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "AshevilleDepotAuto")
public class AshevilleDepotAuto extends AshevilleAuto {
    static double CRATERTIME = RIGHTTIME + 3;
    static  double  MARKTIME = CRATERTIME + 1;
    protected void afterLanding() {
        if(elapsedTime < CRATERTIME) {
            goRight(.25);
        } else if ( elapsedTime < MARKTIME){
            mark();
        } else {
            stopMotors();
        }
    }

}

