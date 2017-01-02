package org.firstinspires.ftc.teamcode.team.Merlin2;//This might need to changed to be in a differnt folder like Merlin1 or K9Robo


import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CompassSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.team.Merlin1.Merlin1Hardware; //More Import statements may be needed
import org.firstinspires.ftc.teamcode.team.Merlin2.Merlin2Hardware;

@TeleOp(name = "TestOp", group = "Merlin2")//This NEEDS to be changed tp the name of the code
//@Disabled //Uncomment this if it is not wanted on the phone
public class TeleTest extends LinearOpMode { //The name after public class needs to be the same as the file name


    /* Declare OpMode members. */
    Merlin2Hardware robot = new Merlin2Hardware();//The hardware map needs to be the hardware map of the robot we are using

    @Override
    public void runOpMode() {
        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);
        //init other variables.

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Say", "Hello Driver");    //It is ready to run
        telemetry.update();//Updates and displays data on the screen.
        // run until the end of the match (driver presses STOP)

        waitForStart();

        while (opModeIsActive()) {

            //Where you write the code that you want to run
            if(gamepad1.x){
                robot.Motor1.setPower(.5);
            }
            else if(gamepad1.y){
                robot.Motor2.setPower(.5);
            }
            else if(gamepad1.b){
                robot.Motor3.setPower(.5);
            }
            else if(gamepad1.a){
                robot.Motor4.setPower(.5);
            }
            else if(gamepad1.right_bumper){
                robot.Lift.setPower(.5);
            }
            else if(gamepad1.left_bumper){
                robot.LiftCollector.setPower(.5);
            }
            else if(gamepad1.dpad_up){
                robot.Flipper.setPower(.5);
            }
            else if(gamepad1.dpad_down){
                robot.Lift.setPower(-.5);
            }
            else {
                robot.Motor1.setPower(0);
                robot.Motor2.setPower(0);
                robot.Motor3.setPower(0);
                robot.Motor4.setPower(0);
                robot.Lift.setPower(0);
                robot.LiftCollector.setPower(0);
                robot.Flipper.setPower(0);
            }


            telemetry.addData("Left Light = ", robot.LeftLight.getLightDetected());
            telemetry.addData("Right Light = ", robot.RightLight.getLightDetected());
            telemetry.addData("Right raw ultrasonic", robot.RightRange.rawUltrasonic());
            telemetry.addData("raw optical", robot.RightRange.rawOptical());
            telemetry.addData("cm optical", "%.2f cm", robot.RightRange.cmOptical());
            telemetry.addData("cm", "%.2f cm", robot.RightRange.getDistance(DistanceUnit.CM));
            telemetry.addData("Left raw ultrasonic", robot.LeftRange.rawUltrasonic());
            telemetry.addData("raw optical", robot.LeftRange.rawOptical());
            telemetry.addData("cm optical", "%.2f cm", robot.LeftRange.cmOptical());
            telemetry.addData("cm", "%.2f cm", robot.LeftRange.getDistance(DistanceUnit.CM));
            telemetry.update();
            // Pause for metronome tick.  40 mS each cycle = update 25 times a second.
            robot.waitForTick(40);
        }
    }

}