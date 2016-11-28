package org.firstinspires.ftc.teamcode.team.Merlin1;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.team.Merlin1.Merlin1Hardware;

@TeleOp(name = "TestOp1", group = "Merlin1")
public class Merlin1TestOp extends LinearOpMode {


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

        double CurrentEncoder = 0;
        double HalfARotation = 1650;
        double TargetEncoder = 0;


        robot.Flipper.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);//First Reset the Encoders
        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        robot.Flipper.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            if(number == 1){
                robot.Flipper.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                idle();
                robot.Flipper.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                number++;
            }


            if(gamepad1.b){
                robot.Motor1.setPower(.5);
            }
            else if(gamepad1.a){
                robot.Motor4.setPower(.5);
            }
            else if (gamepad1.y){
                robot.Motor2.setPower(.5);
            }
            else if (gamepad1.x){
                robot.Motor3.setPower(.5);
            }
            else if (gamepad1.dpad_up){
                robot.Sweeper.setPower(.5);
            }
            else {
                robot.Motor1.setPower(0);
                robot.Motor2.setPower(0);
                robot.Motor3.setPower(0);
                robot.Motor4.setPower(0);
                robot.Sweeper.setPower(0);
            }
            if(robot.Flipper.isBusy()){

            }
            else if (gamepad1.dpad_down){
                robot.Flipper.setTargetPosition(600+ number*600);
                robot.Flipper.setPower(.9);
                number++;
            }

            telemetry.addData("Left Light = ", robot.LeftLight.getLightDetected());
            telemetry.addData("Right Light = ", robot.RightLight.getLightDetected());
            telemetry.addData("raw ultrasonic", robot.rangeSensor.rawUltrasonic());
            telemetry.addData("raw optical", robot.rangeSensor.rawOptical());
            telemetry.addData("cm optical", "%.2f cm", robot.rangeSensor.cmOptical());
            telemetry.addData("cm", "%.2f cm", robot.rangeSensor.getDistance(DistanceUnit.CM));
            telemetry.update();

            //telemetry.update();




            CurrentEncoder = robot.Flipper.getCurrentPosition();

            if(TargetEncoder - CurrentEncoder < 3){
                robot.Flipper.setPower(0);
                if(gamepad1.dpad_down){
                    TargetEncoder = TargetEncoder+HalfARotation;
                }
            }
            else{
                robot.Flipper.setPower(.9);
            }



            // Pause for metronome tick.  40 mS each cycle = update 25 times a second.
            robot.waitForTick(40);
        }
    }
}