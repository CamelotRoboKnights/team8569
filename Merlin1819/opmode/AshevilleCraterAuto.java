package org.firstinspires.ftc.teamcode.team.Merlin1819.opmode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "AshevilleCraterAuto")
public class AshevilleCraterAuto extends AshevilleAuto {
static double CRATERTIME = RIGHTTIME + 3;
    protected void afterLanding() {
        if(elapsedTime < CRATERTIME) {
            goRight(.75);
        } else {
            stopMotors();
        }
    }
}

