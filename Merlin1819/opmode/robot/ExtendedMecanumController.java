package org.firstinspires.ftc.teamcode.team.Merlin1819.opmode.robot;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.team.Merlin1819.api.robot.RobotComponentController;

public class ExtendedMecanumController implements RobotComponentController
{
    private MecanumHardwareMap hardwareMap;

    ExtendedMecanumController(HardwareMap hardwareMap)
    {
        this.hardwareMap = new MecanumHardwareMap(hardwareMap);
    }

    public void startMovingRetractionArm(float power)
    {
        this.hardwareMap.getRetractArmMotor().setPower(power);
    }

    public void stopMovingRetractionArm()
    {
        this.hardwareMap.getRetractArmMotor().setPower(0);
    }
}
