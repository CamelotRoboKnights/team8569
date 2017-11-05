package org.firstinspires.ftc.teamcode.team.DemoBots;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

/**
 * This OpMode uses the common HardwareK9bot class to define the devices on the robot.
 * All device access is managed through the HardwareK9bot class. (See this class for device names)
 * The code is structured as a LinearOpMode
 *
 * This particular OpMode executes a basic Tank Drive Teleop for the K9 bot
 * It raises and lowers the arm using the Gampad Y and A buttons respectively.
 * It also opens and closes the claw slowly using the X and B buttons.
 *
 * Note: the configuration of the servos is such that
 * as the arm servo approaches 0, the arm position moves up (away from the floor).
 * Also, as the claw servo approaches 0, the claw opens up (drops the game element).
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@TeleOp(name="Demo: Telop", group="Demo")

public class KickDemoCode2017 extends LinearOpMode {

    /* Declare OpMode members. */
    KickDemoHardware2017 robot           = new KickDemoHardware2017();              // Use a K9'shardware
    double servoPosition = 0;
    final double    SERVO_SPEED       = 0.01 ;                            // sets rate to move servo

    @Override
    public void runOpMode() {
        double left;
        double right;

        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Say", "Hello Driver");    //
        telemetry.update();


        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            // Run wheels in tank mode (note: The joystick goes negative when pushed forwards, so negate it)
            left = -gamepad1.left_stick_y + gamepad1.left_stick_x;
            right = -gamepad1.left_stick_y - gamepad1.left_stick_x;
            robot.leftMotor.setPower(left);
            robot.rightMotor.setPower(right);

            // Use gamepad Y & A raise and lower the arm
            if (gamepad1.a)
                servoPosition += SERVO_SPEED;
            else if (gamepad1.y)
                servoPosition -= SERVO_SPEED;


            // Move both servos to new position.
            servoPosition  = Range.clip(servoPosition, 0, 1);
            robot.spinner.setPosition(servoPosition);

            // Send telemetry message to signify robot running;
            telemetry.addData("arm",   "%.2f", servoPosition);
            telemetry.addData("left",  "%.2f", left);
            telemetry.addData("right", "%.2f", right);
            telemetry.update();

            // Pause for metronome tick.  40 mS each cycle = update 25 times a second.
            robot.waitForTick(40);
        }
    }
}
