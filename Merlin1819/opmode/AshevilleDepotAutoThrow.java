package org.firstinspires.ftc.teamcode.team.Merlin1819.opmode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "AshevilleDepotAutoThrow")
public class AshevilleDepotAutoThrow extends AshevilleAuto {
    static double CRATERTIME = RIGHTTIME + 2.1;
    static  double BACKTIME = CRATERTIME + 1;
    static double PUSHBACKTIME = BACKTIME + 2;
    protected void afterLanding() {
        if(elapsedTime < CRATERTIME) {
            goRight(1.0);
        } else if(elapsedTime < BACKTIME) {
            goLeft(.5);
        } else if(elapsedTime < PUSHBACKTIME) {
                goRight(.5);
        } else {
                stopMotors();
            }
        }
    }


