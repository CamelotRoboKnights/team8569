package org.firstinspires.ftc.teamcode.team.Merlin1819.opmode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

@Disabled
@Autonomous(name = "AshevilleCraterAuto")
public class AshevilleCraterAuto extends AshevilleAuto {
static double CRATERTIME = RIGHTTIME + 2.0;


    protected void afterLanding() {
        if(elapsedTime < CRATERTIME) {
            goRight(.5);
        } else {
            stopMotors();
        }
    }
}

