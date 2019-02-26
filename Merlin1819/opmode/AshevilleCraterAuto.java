package org.firstinspires.ftc.teamcode.team.Merlin1819.opmode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "AshevilleCraterAuto")
public class AshevilleCraterAuto extends AshevilleAuto {
static double CRATERTIME = RIGHTTIME + 2.0; //ToDo this does not get us over thew edge of the crater consistently; increase a little
    protected void afterLanding() {
        if(elapsedTime < CRATERTIME) {
            goRight(.5);
        } else {
            stopMotors();
        }
    }
}

