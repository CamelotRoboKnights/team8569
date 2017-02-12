/*
 * This was written when trying to write the field oriented drive, it was used
 * to do mass amounts of math for us
 */

package org.firstinspires.ftc.teamcode.team.Other;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="Math", group = "Other")
//@Disabled
public class LongMath extends LinearOpMode {

    double versionNumber = 1;
    double pi = 3.141592653589;
    /* Declare OpMode members. */


    @Override
    public void runOpMode() {
        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */


        // Send telemetry message to signify robot waiting;
        telemetry.addData("Say", "Hello Driver");    //
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            double angle = 0;
            telemetry.addData("M1, 0", .5*(1-2*Math.pow(Math.sin(Math.toRadians(angle + 45)), 2)) + .5 * Math.sin(Math.toRadians(angle + 45)));
            telemetry.addData("M2, 0", .5*(1-2*Math.pow(Math.sin(Math.toRadians(angle - 45)), 2)) + .5 * Math.sin(Math.toRadians(angle - 45)));
            telemetry.addData("M3, 0", .5*(1-2*Math.pow(Math.sin(Math.toRadians(angle - 135)), 2)) + .5 * Math.sin(Math.toRadians(angle - 135)));
            telemetry.addData("M4, 0", .5*(1-2*Math.pow(Math.sin(Math.toRadians(angle + 135)), 2)) + .5 * Math.sin(Math.toRadians(angle + 135)));
            angle = 45;
            telemetry.addData("M1, 45", .5*(1-2*Math.pow(Math.sin(Math.toRadians(angle + 45)), 2)) + .5 * Math.sin(Math.toRadians(angle + 45)));
            telemetry.addData("M2, 45", .5*(1-2*Math.pow(Math.sin(Math.toRadians(angle - 45)), 2)) + .5 * Math.sin(Math.toRadians(angle - 45)));
            telemetry.addData("M3, 45", .5*(1-2*Math.pow(Math.sin(Math.toRadians(angle - 135)), 2)) + .5 * Math.sin(Math.toRadians(angle - 135)));
            telemetry.addData("M4, 45", .5*(1-2*Math.pow(Math.sin(Math.toRadians(angle + 135)), 2)) + .5 * Math.sin(Math.toRadians(angle + 135)));
            angle = 90;
            telemetry.addData("M1, 90", .5*(1-2*Math.pow(Math.sin(Math.toRadians(angle + 45)), 2)) + .5 * Math.sin(Math.toRadians(angle + 45)));
            telemetry.addData("M2, 90", .5*(1-2*Math.pow(Math.sin(Math.toRadians(angle - 45)), 2)) + .5 * Math.sin(Math.toRadians(angle - 45)));
            telemetry.addData("M3, 90", .5*(1-2*Math.pow(Math.sin(Math.toRadians(angle - 135)), 2)) + .5 * Math.sin(Math.toRadians(angle - 135)));
            telemetry.addData("M4, 90", .5*(1-2*Math.pow(Math.sin(Math.toRadians(angle + 135)), 2)) + .5 * Math.sin(Math.toRadians(angle + 135)));
            angle = 135;
            telemetry.addData("M1, 135", .5*(1-2*Math.pow(Math.sin(Math.toRadians(angle + 45)), 2)) + .5 * Math.sin(Math.toRadians(angle + 45)));
            telemetry.addData("M2, 135", .5*(1-2*Math.pow(Math.sin(Math.toRadians(angle - 45)), 2)) + .5 * Math.sin(Math.toRadians(angle - 45)));
            telemetry.addData("M3, 135", .5*(1-2*Math.pow(Math.sin(Math.toRadians(angle - 135)), 2)) + .5 * Math.sin(Math.toRadians(angle - 135)));
            telemetry.addData("M4, 135", .5*(1-2*Math.pow(Math.sin(Math.toRadians(angle + 135)), 2)) + .5 * Math.sin(Math.toRadians(angle + 135)));
            angle = 180;
            telemetry.addData("M1, 180", .5*(1-2*Math.pow(Math.sin(Math.toRadians(angle + 45)), 2)) + .5 * Math.sin(Math.toRadians(angle + 45)));
            telemetry.addData("M2, 180", .5*(1-2*Math.pow(Math.sin(Math.toRadians(angle - 45)), 2)) + .5 * Math.sin(Math.toRadians(angle - 45)));
            telemetry.addData("M3, 180", .5*(1-2*Math.pow(Math.sin(Math.toRadians(angle - 135)), 2)) + .5 * Math.sin(Math.toRadians(angle - 135)));
            telemetry.addData("M4, 180", .5*(1-2*Math.pow(Math.sin(Math.toRadians(angle + 135)), 2)) + .5 * Math.sin(Math.toRadians(angle + 135)));
            angle = -45;
            telemetry.addData("M1, -45", .5*(1-2*Math.pow(Math.sin(Math.toRadians(angle + 45)), 2)) + .5 * Math.sin(Math.toRadians(angle + 45)));
            telemetry.addData("M2, -45", .5*(1-2*Math.pow(Math.sin(Math.toRadians(angle - 45)), 2)) + .5 * Math.sin(Math.toRadians(angle - 45)));
            telemetry.addData("M3, -45", .5*(1-2*Math.pow(Math.sin(Math.toRadians(angle - 135)), 2)) + .5 * Math.sin(Math.toRadians(angle - 135)));
            telemetry.addData("M4, -45", .5*(1-2*Math.pow(Math.sin(Math.toRadians(angle + 135)), 2)) + .5 * Math.sin(Math.toRadians(angle + 135)));
            angle = -90;
            telemetry.addData("M1, -90", .5*(1-2*Math.pow(Math.sin(Math.toRadians(angle + 45)), 2)) + .5 * Math.sin(Math.toRadians(angle + 45)));
            telemetry.addData("M2, -90", .5*(1-2*Math.pow(Math.sin(Math.toRadians(angle - 45)), 2)) + .5 * Math.sin(Math.toRadians(angle - 45)));
            telemetry.addData("M3, -90", .5*(1-2*Math.pow(Math.sin(Math.toRadians(angle - 135)), 2)) + .5 * Math.sin(Math.toRadians(angle - 135)));
            telemetry.addData("M4, -90", .5*(1-2*Math.pow(Math.sin(Math.toRadians(angle + 135)), 2)) + .5 * Math.sin(Math.toRadians(angle + 135)));
            angle = -135;
            telemetry.addData("M1, -135", .5*(1-2*Math.pow(Math.sin(Math.toRadians(angle + 45)), 2)) + .5 * Math.sin(Math.toRadians(angle + 45)));
            telemetry.addData("M2, -135", .5*(1-2*Math.pow(Math.sin(Math.toRadians(angle - 45)), 2)) + .5 * Math.sin(Math.toRadians(angle - 45)));
            telemetry.addData("M3, -135", .5*(1-2*Math.pow(Math.sin(Math.toRadians(angle - 135)), 2)) + .5 * Math.sin(Math.toRadians(angle - 135)));
            telemetry.addData("M4, -135", .5*(1-2*Math.pow(Math.sin(Math.toRadians(angle + 135)), 2)) + .5 * Math.sin(Math.toRadians(angle + 135)));
            telemetry.update();



        }
    }



}
