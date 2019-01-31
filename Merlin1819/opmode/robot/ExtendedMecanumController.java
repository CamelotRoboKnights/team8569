package org.firstinspires.ftc.teamcode.team.Merlin1819.opmode.robot;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.team.Merlin1819.api.robot.RobotComponentController;

public class ExtendedMecanumController implements RobotComponentController
{
    private HardwareMap hardwareMap;

    ExtendedMecanumController(HardwareMap hardwareMap)
    {
        this.hardwareMap = hardwareMap;
    }
}
