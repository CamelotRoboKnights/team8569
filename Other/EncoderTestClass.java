/*
 * This class was created in order to try to understand what the flipper does and
 * how it works, after we understood what it does it was written into both of our launchBall
 * programs.
 *
 */


package org.firstinspires.ftc.teamcode.team.Other;



import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.team.Merlin1.Merlin1Hardware;

@Autonomous(name = "EncoderText", group = "Other")
@Disabled
public class EncoderTestClass extends LinearOpMode {


    /* Declare OpMode members. */
    Merlin1Hardware robot           = new Merlin1Hardware();









    @Override
    public void runOpMode() {
        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Say", "Hello Driver");    //
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        robot.Flipper.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.Flipper.setTargetPosition(560);
        robot.Flipper.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.Flipper.setPower(.4);
        robot.Sweeper.setPower(.6);












        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            if(robot.Flipper.isBusy()){
                telemetry.addData("Encoder Current = ", robot.Flipper.getCurrentPosition());
                telemetry.addData("Encoder target", robot.Flipper.getTargetPosition());
                telemetry.addData("Encoder left = ", robot.Flipper.getTargetPosition()-robot.Flipper.getCurrentPosition());
                telemetry.update();
            }
            else{
                robot.Flipper.setPower(0);
                robot.Sweeper.setPower(0);
            }

            // Pause for metronome tick.  40 mS each cycle = update 25 times a second.
            robot.waitForTick(40);
        }
    }

}

