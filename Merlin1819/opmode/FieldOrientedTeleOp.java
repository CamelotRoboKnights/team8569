package org.firstinspires.ftc.teamcode.team.Merlin1819.opmode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.team.Merlin1819.opmode.robot.MecanumHardwareMap;
import org.firstinspires.ftc.teamcode.team.Merlin1819.opmode.robot.MecanumIMU;

@TeleOp(name = "FieldOrientedTeleOp1")
@Disabled
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

        final double leftStickY = this.gamepad1.left_stick_y,
                     leftStickX = this.gamepad1.left_stick_x,
                    leftTrigger = this.gamepad1.left_trigger,
                   rightTrigger = this.gamepad1.right_trigger;

        double forward = -leftStickY,
                right  =  leftStickX,
                clockwise = (leftTrigger >= rightTrigger) ?
                             leftTrigger : -rightTrigger;
        double degrees = this.imu.getAngle();
        double radians = Math.toRadians(degrees); /* conv 0-360 to 0-2 pi */
        double  temp   = forward * Math.cos(radians) - right * Math.sin(radians);
                right  = forward * Math.sin(radians) + right * Math.cos(radians);
        forward = temp;

        this.telemetry.addData("Debug INFO", "leftStickX = " +
                leftStickX + ", leftStickY = " + leftStickY +
                ", leftTrigger = " + leftTrigger + ", rightTrigger = " +
                rightTrigger + ", forward = " + forward + ", right = " +
                right + ", clockwise = " + clockwise + ", degrees = " +
                degrees);

        double  frontLeft  = forward + clockwise + right,
                frontRight = forward - clockwise - right,
                backLeft   = forward + clockwise - right,
                backRight  = forward - clockwise + right,
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
