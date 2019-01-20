package org.firstinspires.ftc.teamcode.team.Merlin1819.MecanumDrive;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.team.Merlin1819.MecanumDrive.robot.MecanumHardwareMap;
import org.firstinspires.ftc.teamcode.team.Merlin1819.MecanumDrive.robot.MecanumIMU;
import org.firstinspires.ftc.teamcode.team.Merlin1819.MecanumDrive.robot.MecanumRobot;

@TeleOp(name = "FieldOrientedTeleOp")
public class FieldOrientedTeleOp extends OpMode
{
    private MecanumIMU imu;
    private MecanumHardwareMap hardwareMap;

    @Override
    public void init()
    {
        this.imu = new MecanumIMU(super.hardwareMap);
        this.hardwareMap = new MecanumHardwareMap(super.hardwareMap);
    }

    @Override
    public void loop()
    {
        this.imu.updateAngle();

        this.telemetry.addData("Angle", this.imu.getAngle());
        double forward = -this.gamepad1.left_stick_y,
                right  = this.gamepad1.left_stick_x,
                clockwise = (gamepad1.left_trigger >= gamepad1.right_trigger) ?
                        gamepad1.left_trigger : -gamepad1.right_trigger,

                radians = Math.toRadians(this.imu.getAngle()),
                temp =  forward * Math.cos(radians) + right * Math.sin(radians);

        right = -forward * Math.sin(radians) + right * Math.sin(radians);
        forward = temp;

        double frontLeft = forward + clockwise + right,
                frontRight = forward - clockwise - right,
                backLeft = forward + clockwise - right,
                backRight = forward - clockwise + right,
                max = Math.abs(frontLeft);

        if (frontRight > max) max = Math.abs(frontRight);
        if (backLeft   > max) max = Math.abs(backLeft);
        if (backRight  > max) max = Math.abs(backRight);
        if (max > 1) {
            frontLeft  /= max;
            frontRight /= max;
            backLeft   /= max;
            backRight  /= max;
        }

        this.hardwareMap.getFrontLeftMotor().setPower(frontLeft);
        this.hardwareMap.getFrontRightMotor().setPower(frontRight);
        this.hardwareMap.getBackLeftMotor().setPower(backLeft);
        this.hardwareMap.getBackRightMotor().setPower(backRight);

    }

    @Override
    public void stop()
    {
        this.hardwareMap.getFrontLeftMotor().setPower(0);
        this.hardwareMap.getFrontRightMotor().setPower(0);
        this.hardwareMap.getBackLeftMotor().setPower(0);
        this.hardwareMap.getBackRightMotor().setPower(0);
    }
}
