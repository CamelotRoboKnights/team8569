package org.firstinspires.ftc.teamcode.team.Merlin1819.opmode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.team.Merlin1819.opmode.robot.MecanumHardwareMap;
import org.firstinspires.ftc.teamcode.team.Merlin1819.opmode.robot.MecanumIMU;

@TeleOp(name = "MasterTeleOp1")
public class GunnerAndControllerTeleOp extends OpMode
{
    private static final float GAMEPAD_DEAD_ZONE = 0.2F;

    private static final float COLLECTOR_POWER = 1;

    private static final float SPEED_MULTIPLIER = 1;

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

        //Gunner Section

        //this controls the curl arm motor setting it based on the y axis of the left joy stick.
        if (Math.abs(this.gamepad2.left_stick_y) >= GAMEPAD_DEAD_ZONE) {
            this.hardwareMap.getCurlArmMotor().setPower(-this.gamepad2.left_stick_y *SPEED_MULTIPLIER *
                    Math.abs(this.gamepad2.left_stick_y));
        } else {
            this.hardwareMap.getCurlArmMotor().setPower(0);
        }
        // this is where the driver controls the extention of the arm on the gunner right joystick.
        if (Math.abs(this.gamepad2.right_stick_y) >= GAMEPAD_DEAD_ZONE) {
            this.hardwareMap.getRetractArmMotor().setPower(this.gamepad2.right_stick_y);
        } else {
            this.hardwareMap.getRetractArmMotor().setPower(0);
        }

        Servo servo = this.hardwareMap.getCollectorServo();

        boolean leftTriggerPressed = this.gamepad2.left_trigger >= GAMEPAD_DEAD_ZONE;
        boolean rightTriggerPressed = this.gamepad2.right_trigger >= GAMEPAD_DEAD_ZONE;

        if (leftTriggerPressed || rightTriggerPressed) {
            servo.setDirection(leftTriggerPressed ? Servo.Direction.FORWARD : Servo.Direction.REVERSE);
            servo.setPosition(1);
        } else {
            servo.setPosition(.5);
        }




        /*
        Field oriented drive section.
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

        /*this.telemetry.addData("Debug INFO", "leftStickX = " +
                leftStickX + ", leftStickY = " + leftStickY +
                ", leftTrigger = " + leftTrigger + ", rightTrigger = " +
                rightTrigger + ", forward = " + forward + ", right = " +
                right + ", clockwise = " + clockwise + ", degrees = " +
                degrees);*/

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

        //this.telemetry.addData("Encoder", this.hardwareMap.getRetractArmMotor().getCurrentPosition());

        //Driver controls for the lift. The driver controls the lift because that makes lifting easier because the driv can do it with less coordination.

        if (Math.abs(this.gamepad1.right_stick_y) >= GAMEPAD_DEAD_ZONE) {
            this.hardwareMap.getLiftMotor().setPower(-this.gamepad1.right_stick_y);
        } else {
            this.hardwareMap.getLiftMotor().setPower(0);
        }

        //Gyro reset button for the gunner because the Autonomous will so far statr off ceter and the driver control could be unaligned.

        if (this.gamepad1.y) {

        }

    }
}
