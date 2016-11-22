package org.firstinspires.ftc.teamcode.Merlin1;


import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CompassSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.Merlin1.Merlin1Hardware;

@TeleOp(name = "TeleOp1", group = "Merlin1")//This is the name of the code and what will be seen on the robot phone
//@Disabled //Uncomment this if it is not wanted on the phone
public class Merlin1TeleOp extends LinearOpMode {


    /* Declare OpMode members. */
    Merlin1Hardware robot           = new Merlin1Hardware();//Setting up the Hardware map

    @Override
    public void runOpMode() {
        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);
        //boolean calibration_complete = false;


        int number = 1;//This is used in having things run only once at the beginning of the code
        double gyroPosition;//The angle of the robot from the start
        double compassPosition = 0;//The current heading
        double compassOriginal = 0;//The original heading and will need to be set to each color and each field
        int FlipperNumber = 0 ;//How many times the flipper has gone half a rotation
        double gyroPositionRadians;//The angle from start in radians because Java does math in radians
        double pi = 3.14159;//This is pi
        double NewX;//The rotated value of X
        double NewY;//The rotated value of Y
        double CurrentEncoder = 0;
        double OneRotation = 1650;
        double TargetEncoder = 0;

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Say", "Hello Driver");    //It is ready to run
        telemetry.update();//Updates and displays data on the screen.
        robot.Flipper.setMode(DcMotor.RunMode.RUN_USING_ENCODER);//Set the mode to using encoders in order to reset them
        robot.Flipper.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);//Reset the encoders
        robot.Flipper.setMode(DcMotor.RunMode.RUN_TO_POSITION);//Set them to run to position



        //while ( calibration_complete == false) {
            /* navX-Micro Calibration completes automatically ~15 seconds after it is
            powered on, as long as the device is still.  To handle the case where the
            navX-Micro has not been able to calibrate successfully, hold off using
            the navX-Micro Yaw value until calibration is complete.
             */

        //    calibration_complete = !robot.navx_device.isCalibrating();
        //    if (!calibration_complete) {

        //    }
        //}
        //robot.navx_device.zeroYaw();



        robot.compas.setMode(CompassSensor.CompassMode.CALIBRATION_MODE);//Set the compass to calibrate before the match
        if(robot.compas.isCalibrating()){
            telemetry.addData("Still" , "Working");
        }
        robot.Flipper.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);//First Reset the Encoders
        waitForStart();//whit for the start button to be pressed
        robot.compas.setMode(CompassSensor.CompassMode.MEASUREMENT_MODE);//Set the compas to measure the movement
        robot.Flipper.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {






            CurrentEncoder = robot.Flipper.getCurrentPosition();
            /*compassPosition = robot.compas.getDirection();//this gets the current compass position
            if(number<10){
                compassOriginal = compassPosition;
                number++;
            }
            gyroPosition = compassPosition-compassOriginal;//This finds how far it is from the current heading
            if(gyroPosition > 180){//If it is greater than 180 this turns it to a negative value over -180
                gyroPosition = gyroPosition - 360;
            }
            else if (gyroPosition < -180){//If it is less than -180 this turns it to a positive value under 180
                gyroPosition = gyroPosition + 360;
            }
            gyroPositionRadians = gyroPosition*pi/180;//This turns the degree value of how far the robot is from the start into radians
            NewY = -gamepad1.left_stick_y * Math.cos(gyroPositionRadians) + gamepad1.left_stick_y * Math.sin(gyroPositionRadians);//shifts the Y axis to the persons Y axis from the robot's
            NewX = gamepad1.left_stick_y * Math.sin(gyroPositionRadians) + gamepad1.left_stick_x * Math.cos(gyroPositionRadians);//shifts the X axis to the persons X axis from the robot's
            if(gamepad1.left_stick_x > .02 || gamepad1.left_stick_x < -.02 || gamepad1.left_stick_y < -.02 || gamepad1.left_stick_y > .02) {//This cancels out noise from the gamepad
                robot.Motor1.setPower(NewY - NewX);//Sets the motor power for the front right motor
                robot.Motor2.setPower(NewY + NewX);//sets the motor power for the front left motor
                robot.Motor3.setPower(NewY - NewX);//Sets the motor power for the back left motor
                robot.Motor4.setPower(NewY + NewX);//Sets the motor power for the back right motor
            }
            */







            if(gamepad1.left_stick_x > .02 || gamepad1.left_stick_x < -.02 || gamepad1.left_stick_y < -.02 || gamepad1.left_stick_y > .02) {//This cancels out noise from the gamepad
                robot.Motor1.setPower(-gamepad1.left_stick_y - gamepad1.left_stick_x);//Sets the motor power for the front right motor
                robot.Motor2.setPower(-gamepad1.left_stick_y + gamepad1.left_stick_x);//sets the motor power for the front left motor
                robot.Motor3.setPower(-gamepad1.left_stick_y - gamepad1.left_stick_x);//Sets the motor power for the back left motor
                robot.Motor4.setPower(-gamepad1.left_stick_y + gamepad1.left_stick_x);//Sets the motor power for the back right motor
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
            else{//If none of these are true make the robot not move
                robot.Motor1.setPower(0);
                robot.Motor2.setPower(0);
                robot.Motor3.setPower(0);
                robot.Motor4.setPower(0);
            }


            if (gamepad2.x){//if X is pressed make the spinner set to dispose of balls
                robot.Sweeper.setPower(-.5);
            }
            else if(gamepad2.b){//If B os pressed make the spinner set to collect balls
                robot.Sweeper.setPower(.5);
            }
            else if(gamepad2.a){//If A is pressed make the spinner not spin
                robot.Sweeper.setPower(0);
            }


            if(gamepad2.dpad_down){
                robot.Flipper.setPower(-.5);
            }

            else if(TargetEncoder - CurrentEncoder < 3){
                robot.Flipper.setPower(0);
                if(gamepad2.dpad_up){
                    TargetEncoder = TargetEncoder+OneRotation;
                }
            }
            else{
                robot.Flipper.setPower(.9);
            }



            telemetry.addData("Encoder Target", TargetEncoder);
            telemetry.addData("Encoder Current", robot.Flipper.getCurrentPosition());


            /*telemetry.addData("Left Light = ", robot.LeftLight.getLightDetected());
            telemetry.addData("Right Light = ", robot.RightLight.getLightDetected());
            telemetry.addData("raw ultrasonic", robot.rangeSensor.rawUltrasonic());
            telemetry.addData("raw optical", robot.rangeSensor.rawOptical());
            telemetry.addData("cm optical", "%.2f cm", robot.rangeSensor.cmOptical());
            telemetry.addData("cm", "%.2f cm", robot.rangeSensor.getDistance(DistanceUnit.CM));
            telemetry.addData("Ci2c", robot.compas.getI2cAddress());
            telemetry.addData("gro", gyroPosition);
            telemetry.addData("compass", robot.compas.getDirection());
            telemetry.addData("Compas orgignal", compassOriginal);
            telemetry.addData("Compas New", compassPosition);
            telemetry.addData("Runtime", getRuntime());
            telemetry.addData("",robot.compas.getAcceleration());


            /*telemetry.addData("NavX Heading", robot.navx_device.getCompassHeading());
            telemetry.addData("NavX Altitude", robot.navx_device.getAltitude());
            telemetry.addData("NavX BoardYawAxis", robot.navx_device.getBoardYawAxis());
            telemetry.addData("NavX getQuaternionW", robot.navx_device.getQuaternionW());
            telemetry.addData("NavX getQuaternionY", robot.navx_device.getQuaternionY());
            telemetry.addData("NavX getQuaternionX", robot.navx_device.getQuaternionX());
            telemetry.addData("NavX getQuaternionZ", robot.navx_device.getQuaternionZ());
            telemetry.addData("NavX getRawAccelX", robot.navx_device.getRawAccelX());
            telemetry.addData("NavX getRawAccelY", robot.navx_device.getRawAccelY());
            telemetry.addData("NavX getRawAccelZ", robot.navx_device.getRawAccelZ());
            telemetry.addData("NavX getRawGyroX", robot.navx_device.getRawGyroX());
            telemetry.addData("NavX getRawGyroY", robot.navx_device.getRawGyroY());
            telemetry.addData("NavX getRawGyroZ", robot.navx_device.getRawGyroZ());
            telemetry.addData("NavX getPitch", robot.navx_device.getPitch());
            telemetry.addData("NavX getRawMagX", robot.navx_device.getRawMagX());
            telemetry.addData("NavX getRawMagY", robot.navx_device.getRawMagY());
            telemetry.addData("NavX getRawMagZ", robot.navx_device.getRawMagZ());
            telemetry.addData("NavX getRoll", robot.navx_device.getRoll());
            telemetry.addData("NavX getWorldLinearAccelX", robot.navx_device.getWorldLinearAccelX());
            telemetry.addData("NavX getWorldLinearAccelY", robot.navx_device.getWorldLinearAccelY());
            telemetry.addData("NavX getWorldLinearAccelZ", robot.navx_device.getWorldLinearAccelZ());
            telemetry.addData("NavX getYaw", robot.navx_device.getYaw());*/

            telemetry.update();






























            /*
            double forwrd = -gamepad1.left_stick_y; // Invert stick Y axis
            double strafe = gamepad1.left_stick_x;

            double pi = 3.1415926;

            // Adjust Joystick X/Y inputs by navX MXP yaw angle

            double gyro_degrees =1;// = ahrs->GetYaw();
            double gyro_radians = gyro_degrees * pi/180;
            double temp = forwrd * Math.cos(gyro_radians) + strafe * Math.sin(gyro_radians);
            strafe = -forwrd * Math.sin(gyro_radians) + strafe * Math.cos(gyro_radians);
            forwrd = temp;

            // At this point, Joystick X/Y (strafe/forwrd) vectors have been
            // rotated by the gyro angle, and can be sent to drive system
            */




            // Pause for metronome tick.  40 mS each cycle = update 25 times a second.
            robot.waitForTick(40);
        }
    }

}
