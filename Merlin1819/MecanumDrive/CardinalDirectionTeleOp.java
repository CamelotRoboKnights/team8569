package org.firstinspires.ftc.teamcode.team.Merlin1819.MecanumDrive;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.team.Merlin1819.MecanumDrive.robot.MecanumRobot;

import org.firstinspires.ftc.teamcode.team.Merlin1819.api.robot.Robot;

/**
 *
 * An operational mode that
 * allows the user to only
 * move within the current robots
 * orientation, north, south, east or west.
 *
 * @author Zigy Lim
 * @author Elijah Terry
 * @author Pete Ryden
 *
 * @since 1.1
 * @version 1.0
 *
 * @see AllDirectionTeleOp
 */
@TeleOp(name = "CardinalDirectionTeleOp")
public class CardinalDirectionTeleOp extends OpMode
{
    private static final float MOVE_DEAD_ZONE = 0.2F,
                             ROTATE_DEAD_ZONE = 0.2F;
    private Robot robot;

    @Override
    public void init()
    {
        this.robot = new MecanumRobot(this.hardwareMap);
    }

    @Override
    public void loop()
    {
        /*
         * Movement code (Forward, backward, etc).
         */
        if (this.gamepad1.left_stick_y > CardinalDirectionTeleOp.MOVE_DEAD_ZONE) {
            this.robot.startMoving(Robot.MovementDirection.FORWARD, Math.abs(this.gamepad1.left_stick_y));
        } else if (this.gamepad1.left_stick_y < -CardinalDirectionTeleOp.MOVE_DEAD_ZONE) {
            this.robot.startMoving(Robot.MovementDirection.BACKWARD, Math.abs(this.gamepad1.left_stick_y));
        } else if (this.gamepad1.right_stick_x < -CardinalDirectionTeleOp.MOVE_DEAD_ZONE) {
            this.telemetry.addData("Telemetry", "Strafing Left");
            this.robot.startMoving(Robot.MovementDirection.LEFT, Math.abs(this.gamepad1.right_stick_x));
        } else if (this.gamepad1.right_stick_x > CardinalDirectionTeleOp.MOVE_DEAD_ZONE) {
            this.telemetry.addData("Telemetry", "Strafing Right");
            this.robot.startMoving(Robot.MovementDirection.RIGHT, Math.abs(this.gamepad1.right_stick_x));
        }


        /*
         * Rotation code (left, right).
         */

        else if (this.gamepad1.left_trigger > CardinalDirectionTeleOp.ROTATE_DEAD_ZONE
                && this.gamepad1.right_trigger > CardinalDirectionTeleOp.ROTATE_DEAD_ZONE) {
            this.robot.stopRotating();
        } else if (this.gamepad1.left_trigger > CardinalDirectionTeleOp.ROTATE_DEAD_ZONE) {
            this.robot.startRotating(Robot.RotationDirection.LEFT, this.gamepad1.left_trigger);
        } else if (this.gamepad1.right_trigger > CardinalDirectionTeleOp.ROTATE_DEAD_ZONE) {
            this.robot.startRotating(Robot.RotationDirection.RIGHT, this.gamepad1.right_trigger);
        } else {
            this.robot.stopMoving();
            this.robot.stopRotating();
        }


    }
}
