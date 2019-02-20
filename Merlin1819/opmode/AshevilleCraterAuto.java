package org.firstinspires.ftc.teamcode.team.Merlin1819.opmode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;

import org.firstinspires.ftc.teamcode.team.Merlin1819.opmode.robot.MecanumHardwareMap;
import org.firstinspires.ftc.teamcode.team.Merlin1819.api.iterative.IterativeActionOpMode;
import org.firstinspires.ftc.teamcode.team.Merlin1819.api.iterative.IterativeState;
import org.firstinspires.ftc.teamcode.team.Merlin1819.opmode.robot.MecanumIMU;

@Autonomous(name = "AshevilleCraterAuto")
public class AshevilleCraterAuto extends AshevilleAuto {
static double CRATERTIME = LEFTTIME + 3;
    public void afterLanding() {
        if(elapsedTime < CRATERTIME) {
            goLeft(.75);
        } else {
            stopMotors();
        }
    }
}

