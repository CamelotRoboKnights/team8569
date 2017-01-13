/*
 * Another class to try to understand the flippers encoder
 */
package org.firstinspires.ftc.teamcode.team.Other;


import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.team.Merlin1.Merlin1Hardware;

@TeleOp(name = "Flipper Encoder", group = "Other")
@Disabled
public class Merlin1FlipperEncoder extends LinearOpMode {


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

        int number  = 1;

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            if(number == 1){
                robot.Flipper.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                idle();
                robot.Flipper.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                number ++;
            }
            robot.Flipper.setTargetPosition(560);
            robot.Flipper.setPower(.9);





            // Pause for metronome tick.  40 mS each cycle = update 25 times a second.
            robot.waitForTick(40);
        }
    }
}