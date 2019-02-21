package org.firstinspires.ftc.teamcode.team.Merlin1819.opmode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "AshevilleDepotAuto")
public class AshevilleDepotAuto extends AshevilleAuto {
    static double DEPOTTIME = RIGHTTIME + 2;
    static double CRATERTIME = DEPOTTIME + 3;
    protected void afterLanding() {
        if(elapsedTime < DEPOTTIME) {
            goRight(.5);
        } else if(elapsedTime < RIGHTTIME) {
            goForward(.5);
        } else {
            stopMotors();
        }
    }

}

