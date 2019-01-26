package org.firstinspires.ftc.teamcode.team.Merlin1819.MecanumDrive;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.team.Merlin1819.MecanumDrive.robot.MecanumHardwareMap;
import org.firstinspires.ftc.teamcode.team.Merlin1819.MecanumDrive.robot.MecanumIMU;

@TeleOp(name = "MasterTeleOp")
public class GunnerAndControllerTeleOp extends OpMode
{
    private static final float GAMEPAD_DEAD_ZONE = 0.2F;

    private static final float COLLECTOR_POWER = 1F;

    private static final float SPEED_MULTIPLIER = 0.5F;

    private MecanumHardwareMap hardwareMap;
    private MecanumIMU imu;

    @Override
    public void init()
    {
        this.hardwareMap = new MecanumHardwareMap(super.hardwareMap);
        this.imu = new MecanumIMU(super.hardwareMap);
    }

    @Override
    public void loop()
    {
        /*
        Gunner Section
         */
        if (Math.abs(this.gamepad2.left_stick_y) >= GAMEPAD_DEAD_ZONE) {
            this.hardwareMap.getCurlArmMotor().setPower(-this.gamepad2.left_stick_y *SPEED_MULTIPLIER);
        } else {
            this.hardwareMap.getCurlArmMotor().setPower(0);
        }

        if (Math.abs(this.gamepad2.right_stick_y) >= GAMEPAD_DEAD_ZONE) {
            this.hardwareMap.getRetractArmMotor().setPower(this.gamepad2.right_stick_y);
        } else {
            this.hardwareMap.getRetractArmMotor().setPower(0);
        }

        if (this.gamepad2.left_trigger >= GAMEPAD_DEAD_ZONE) {
            this.hardwareMap.getCollectorMotor().setPower(COLLECTOR_POWER);
        }  else if (this.gamepad2.right_trigger >= GAMEPAD_DEAD_ZONE) {
            this.hardwareMap.getCollectorMotor().setPower(-COLLECTOR_POWER);
        } else {
            this.hardwareMap.getCollectorMotor().setPower(0);
        }

        /*
        Field oriented section.
         */
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
}
