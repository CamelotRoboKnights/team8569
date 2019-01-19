package org.firstinspires.ftc.teamcode.team.Merlin1819.MecanumDrive;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.team.Merlin1819.MecanumDrive.robot.MecanumIMU;

public class FieldOrientedTeleOp extends OpMode
{
    private MecanumIMU imu;

    @Override
    public void init()
    {
        this.imu = new MecanumIMU(this.hardwareMap);
    }

    @Override
    public void loop()
    {
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


    }
}
