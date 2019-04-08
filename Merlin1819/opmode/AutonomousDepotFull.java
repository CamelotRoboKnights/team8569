package org.firstinspires.ftc.teamcode.team.Merlin1819.opmode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.team.Merlin1819.api.robot.Robot;
import org.firstinspires.ftc.teamcode.team.Merlin1819.opmode.robot.MecanumRobot;


@Autonomous(name = "AutonomousDepotFull")
public class AutonomousDepotFull extends AutonomousCraterFull {

    @Override
    boolean depotMode()
    {
        return true;
    }
}


