package org.firstinspires.ftc.teamcode.team.Merlin1819.opmode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.team.Merlin1819.opmode.robot.MecanumHardwareMap;

@TeleOp(name = "ArmMotorTestZigy")
public class ArmMotorTestClass extends OpMode
{
    private static final int MAX_TICK_COUNT = 5000;
    private static final double ROBOT_POWER = 1.0;

    private MecanumHardwareMap hardwareMap;
    private int counter;


    @Override
    public void init()
    {
        this.hardwareMap = new MecanumHardwareMap(super.hardwareMap);
        this.counter = 0;
    }

    @Override
    public void loop()
    {
        this.telemetry.addData("Data",
                "Power = " + this.hardwareMap.getCurlArmMotor().getPower() + ", counter = " + this.counter);
        if (this.counter < ArmMotorTestClass.MAX_TICK_COUNT) {
            this.hardwareMap.getCurlArmMotor().setPower(ArmMotorTestClass.ROBOT_POWER);
            this.counter++;
        } else {
            this.hardwareMap.getCurlArmMotor().setPower(0);
        }
    }

    @Override
    public void stop()
    {
        this.hardwareMap.getRetractArmMotor().setPower(0);
        this.hardwareMap.getCurlArmMotor().setPower(0);
    }
}
