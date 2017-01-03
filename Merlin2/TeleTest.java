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
            double TargetEncoder = 0;
            double LiftHeight = 0;
            feildOrentedDrive();
            collection();
            TargetEncoder = launchBall(TargetEncoder);
            LiftHeight = lift();
            telemetry.addData("LiftHeight", LiftHeight);

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
    public double lift(){
        if(gamepad2.right_trigger > .02){
            robot.Lift.setPower(gamepad2.right_trigger);
        }
        else if(gamepad2.left_trigger > .02){
            robot.Lift.setPower(-gamepad2.left_trigger);
        }
        else{
            robot.Lift.setPower(0);
        }
        return robot.Lift.getCurrentPosition();

    }
    public double launchBall(double TargetEncoder) {
        double CurrentEncoder = robot.Flipper.getCurrentPosition();
        double OneRotation = 1650;
        //if (gamepad2.dpad_up) robot.Flipper.setPower(.9);
        //else robot.Flipper.setPower(0);
        if(TargetEncoder - CurrentEncoder < 3){
            robot.Flipper.setPower(0);
            if(gamepad2.dpad_up){
                TargetEncoder = TargetEncoder+OneRotation;
            }
        }
        else{
            robot.Flipper.setPower(.9);
        }
        return TargetEncoder;
    }

    public void feildOrentedDrive(){
        double JoyX;
        double JoyY;
        double NewX;
        double NewY;
        double OrientationDegrees;
        double OrientationRadians;

        JoyY = -gamepad1.left_stick_y;
        JoyX = gamepad1.left_stick_x;
        OrientationDegrees = robot.navx_device.getYaw();
        OrientationRadians = OrientationDegrees * Math.PI / 180;
        NewY = JoyY * Math.cos(OrientationRadians) + JoyX * Math.sin(OrientationRadians);
        NewX = -JoyY * Math.sin(OrientationRadians) + JoyX * Math.cos(OrientationRadians);

        if(Math.abs(gamepad1.left_stick_y) >= 0.02 || Math.abs(gamepad1.left_stick_x) >= 0.02){
            robot.Motor1.setPower(NewY - NewX);//Sets the motor power for the front right motor
            robot.Motor2.setPower(NewY + NewX);//sets the motor power for the front left motor
            robot.Motor3.setPower(NewY - NewX);//Sets the motor power for the back left motor
            robot.Motor4.setPower(NewY + NewX);//Sets the motor power for the back right motor
        }
        else if(gamepad1.right_trigger >= .02){//This cancels out noise and sets the robot to turn right at the speed of the right trigger
            robot.Motor1.setPower(-gamepad1.right_trigger);
            robot.Motor2.setPower(gamepad1.right_trigger);
            robot.Motor3.setPower(gamepad1.right_trigger);
            robot.Motor4.setPower(-gamepad1.right_trigger);
        }
        else if(gamepad1.left_trigger >= .02){//This cancels out noise and sets the robot to turn left at the speed of the left trigger
            robot.Motor1.setPower(gamepad1.left_trigger);
            robot.Motor2.setPower(-gamepad1.left_trigger);
            robot.Motor3.setPower(-gamepad1.left_trigger);
            robot.Motor4.setPower(gamepad1.left_trigger);
        }
        else{//If none of these is true turn the power off to the motors to stop the robot
            robot.Motor1.setPower(0);
            robot.Motor2.setPower(0);
            robot.Motor3.setPower(0);
            robot.Motor4.setPower(0);
        }
    }
    public void collection(){
        if (gamepad2.x){//if X is pressed make the spinner set to dispose of balls
            robot.LiftCollector.setPower(.9);
        }
        else if(gamepad2.b){//If B os pressed make the spinner set to collect balls
            robot.LiftCollector.setPower(-.9);
        }
        else if(gamepad2.a){//If A is pressed make the spinner not spin
            robot.LiftCollector.setPower(0);
        }

    }

}