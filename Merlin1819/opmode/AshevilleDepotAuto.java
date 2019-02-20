package org.firstinspires.ftc.teamcode.team.Merlin1819.opmode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.team.Merlin1819.opmode.robot.MecanumHardwareMap;
import org.firstinspires.ftc.teamcode.team.Merlin1819.api.iterative.IterativeActionOpMode;
import org.firstinspires.ftc.teamcode.team.Merlin1819.api.iterative.IterativeState;
import org.firstinspires.ftc.teamcode.team.Merlin1819.opmode.robot.MecanumIMU;

@Autonomous(name = "AshevilleDepotAuto")
public class AshevilleDepotAuto extends AshevilleAuto {
    static double DEPOTTIME = LEFTTIME + 2;
    static double CRATERTIME = DEPOTTIME + 3;
    public void afterLanding() {
        if(elapsedTime < DEPOTTIME) {
            goLeft(.5);
        } else if(elapsedTime < LEFTTIME) {
            goForward(.5);
        } else {
            stopMotors();
        }
    }

}

