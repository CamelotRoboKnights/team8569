package org.firstinspires.ftc.teamcode.team.Merlin2;

import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CompassSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;


import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.R;
import org.firstinspires.ftc.teamcode.team.Merlin1.Merlin1Hardware; //More Import statements may be needed
import org.firstinspires.ftc.teamcode.team.Merlin2.Merlin2Hardware;
import org.lasarobotics.vision.android.Cameras;
import org.lasarobotics.vision.ftc.resq.Beacon;
import org.lasarobotics.vision.opmode.VisionOpMode;
import org.lasarobotics.vision.opmode.extensions.CameraControlExtension;
import org.lasarobotics.vision.util.ScreenOrientation;
import org.opencv.core.Size;


public class Merlin2TeleOpMethods extends VisionOpMode{

    Merlin2Hardware robot = new Merlin2Hardware();//The hardware map needs to be the hardware map of the robot we are using
    public boolean ButtonPressed = FALSE;
    public double LiftDivisor = 28000;
    public double TargetEncoder = 0;
    public double LiftHeight = 0;

    public void init(){

    }
    @Override
    public void init_loop(){}
    @Override
    public void start(){}
    @Override
    public void loop(){}
    @Override
    public void stop(){}


    public void print(double LiftHeight, double TargetEncoder){
        telemetry.addData("LiftHeight", LiftHeight);
        telemetry.addData("YAW", robot.navx_device.getYaw());
        telemetry.addData("Flip Encoder Target", TargetEncoder);
        telemetry.addData("Flip Encoder Current", robot.Flipper.getCurrentPosition());
        telemetry.addData("AccelX", robot.navx_device.getWorldLinearAccelX()*386.0886);
        telemetry.addData("Accely", robot.navx_device.getWorldLinearAccelY()*386.0886);
        telemetry.addData("Left Light = ", robot.LeftLight.getLightDetected());
        telemetry.addData("Right Light = ", robot.RightLight.getLightDetected());
        telemetry.addData("Right raw ultrasonic", robot.RightRange.rawUltrasonic());
        telemetry.addData("raw optical", robot.RightRange.rawOptical());
        telemetry.addData("cm optical", "%.2f cm", robot.RightRange.cmOptical());
        telemetry.addData("cm", "%.2f cm", robot.RightRange.getDistance(DistanceUnit.CM));
        telemetry.addData("Left raw ultrasonic", robot.LeftRange.rawUltrasonic());
        telemetry.addData("L raw optical", robot.LeftRange.rawOptical());
        telemetry.addData("L cm optical", "%.2f cm", robot.LeftRange.cmOptical());
        telemetry.addData(" L cm", "%.2f cm", robot.LeftRange.getDistance(DistanceUnit.CM));
//        telemetry.addData("Blue Left", beacon.getAnalysis().isLeftBlue());
//        telemetry.addData("Blue Rigth", beacon.getAnalysis().isRightBlue());
//        telemetry.addData("Red left", beacon.getAnalysis().isLeftRed());
//        telemetry.addData("red right", beacon.getAnalysis().isRightRed());
//        telemetry.addData("Beacon Confidence", beacon.getAnalysis().getConfidenceString());
        telemetry.update();
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
    public double liftCapBallLift(){
        double CurrentEncoder = robot.Lift.getCurrentPosition();
        double FullHeight = 23000;
        if (gamepad2.right_stick_button){
            ButtonPressed = TRUE;
        }
        if(ButtonPressed == TRUE) {
            if (FullHeight - CurrentEncoder < 500) {
                robot.Lift.setPower(0);
                ButtonPressed = FALSE;
            } else {
                robot.Lift.setPower(1);

            }
        }
        else{
            robot.Lift.setPower(0);
        }

        return CurrentEncoder;
    }
    public String primeCapBallLift(String CurrentCase){
        double StartTime;
        double CurrentTime = System.currentTimeMillis();

        switch (CurrentCase){
            case "RaiseLift":
                double CurrentEncoder = robot.Lift.getCurrentPosition();
                double Height = 300;
                if (Height - CurrentEncoder < 3) {
                    robot.Lift.setPower(0);
                    CurrentCase = "Wait";
                    StartTime = System.currentTimeMillis();
                } else {
                    robot.Lift.setPower(.8);
                }
                break;
            case "Wait":
                //if(CurrentTime - StartTime+500 >= 0 ){

                //}
        }
        return "WORK ON THIS MORE";
    }
    public void driveChoice (double LiftHeight){

        if(2*Math.abs(gamepad1.right_stick_x)+.2 <= -gamepad1.right_stick_y) forwardDrive(LiftHeight);
        else if (-2*Math.abs(gamepad1.right_stick_x)-.2 >= -gamepad1.right_stick_y) backDrive(LiftHeight);
        else if (2*Math.abs(-gamepad1.right_stick_y)+.2 <= gamepad1.right_stick_x) rightDrive(LiftHeight);
        else if (-2*Math.abs(-gamepad1.right_stick_y)-.2 >= gamepad1.right_stick_x) leftDrive(LiftHeight);
        else feildOrentedDrive(LiftHeight);
    }

    public void backDrive(double LiftHeight){

        double JoyY = -gamepad1.left_stick_y;
        double JoyX = gamepad1.left_stick_x;
        double Motor1Power = 0;
        double Motor2Power = 0;
        double Motor3Power = 0;
        double Motor4Power = 0;

        double LiftHeightScaled = 1-Math.abs(LiftHeight)/LiftDivisor;

        if(Math.abs(gamepad1.left_stick_y) >= 0.02 || Math.abs(gamepad1.left_stick_x) >= 0.02){

            Motor1Power = JoyY - JoyX;
            Motor1Power = Range.clip(Motor1Power, -1, 1);
            Motor1Power = Motor1Power*LiftHeightScaled;

            Motor2Power = JoyY + JoyX;
            Motor2Power = Range.clip(Motor2Power, -1, 1);
            Motor2Power = Motor2Power*LiftHeightScaled;

            Motor3Power = JoyY - JoyX;
            Motor3Power = Range.clip(Motor3Power, -1, 1);
            Motor3Power = Motor3Power*LiftHeightScaled;

            Motor4Power = JoyY + JoyX;
            Motor4Power = Range.clip(Motor4Power, -1, 1);
            Motor4Power = Motor4Power*LiftHeightScaled;


            Motor1Power = -Motor1Power;
            Motor2Power = -Motor2Power;
            Motor3Power = -Motor3Power;
            Motor4Power = -Motor4Power;


            moveMotorsPower(Motor1Power, Motor2Power, Motor3Power, Motor4Power);

        }
        else if(gamepad1.right_trigger >= .02){//This cancels out noise and sets the robot to turn right at the speed of the right trigger
            Motor1Power = -gamepad1.right_trigger*LiftHeightScaled*.5;
            Motor2Power = gamepad1.right_trigger*LiftHeightScaled*.5;
            Motor3Power = gamepad1.right_trigger*LiftHeightScaled*.5;
            Motor4Power = -gamepad1.right_trigger*LiftHeightScaled*.5;
            moveMotorsPower(Motor1Power, Motor2Power, Motor3Power, Motor4Power);
        }
        else if(gamepad1.left_trigger >= .02){//This cancels out noise and sets the robot to turn left at the speed of the left trigger
            Motor1Power = gamepad1.left_trigger*LiftHeightScaled*.5;
            Motor2Power = -gamepad1.left_trigger*LiftHeightScaled*.5;
            Motor3Power = -gamepad1.left_trigger*LiftHeightScaled*.5;
            Motor4Power = gamepad1.left_trigger*LiftHeightScaled*.5;
            moveMotorsPower(Motor1Power, Motor2Power, Motor3Power, Motor4Power);
        }
        else{//If none of these is true turn the power off to the motors to stop the robot
            moveMotorsPower(0, 0, 0, 0);
        }
        telemetry.addData("M1", Motor1Power);
        telemetry.addData("M2", Motor2Power);
        telemetry.addData("M3", Motor3Power);
        telemetry.addData("M4", Motor4Power);

    }

    public void forwardDrive(double LiftHeight){

        double JoyY = -gamepad1.left_stick_y;
        double JoyX = gamepad1.left_stick_x;
        double Motor1Power = 0;
        double Motor2Power = 0;
        double Motor3Power = 0;
        double Motor4Power = 0;

        double LiftHeightScaled = 1-Math.abs(LiftHeight)/LiftDivisor;

        if(Math.abs(gamepad1.left_stick_y) >= 0.02 || Math.abs(gamepad1.left_stick_x) >= 0.02){

            Motor1Power = JoyY - JoyX;
            Motor1Power = Range.clip(Motor1Power, -1, 1);
            Motor1Power = Motor1Power*LiftHeightScaled;

            Motor2Power = JoyY + JoyX;
            Motor2Power = Range.clip(Motor2Power, -1, 1);
            Motor2Power = Motor2Power*LiftHeightScaled;

            Motor3Power = JoyY - JoyX;
            Motor3Power = Range.clip(Motor3Power, -1, 1);
            Motor3Power = Motor3Power*LiftHeightScaled;

            Motor4Power = JoyY + JoyX;
            Motor4Power = Range.clip(Motor4Power, -1, 1);
            Motor4Power = Motor4Power*LiftHeightScaled;


            Motor1Power = Motor1Power;
            Motor2Power = Motor2Power;
            Motor3Power = Motor3Power;
            Motor4Power = Motor4Power;


            moveMotorsPower(Motor1Power, Motor2Power, Motor3Power, Motor4Power);

        }
        else if(gamepad1.right_trigger >= .02){//This cancels out noise and sets the robot to turn right at the speed of the right trigger
            Motor1Power = -gamepad1.right_trigger*LiftHeightScaled*.5;
            Motor2Power = gamepad1.right_trigger*LiftHeightScaled*.5;
            Motor3Power = gamepad1.right_trigger*LiftHeightScaled*.5;
            Motor4Power = -gamepad1.right_trigger*LiftHeightScaled*.5;
            moveMotorsPower(Motor1Power, Motor2Power, Motor3Power, Motor4Power);
        }
        else if(gamepad1.left_trigger >= .02){//This cancels out noise and sets the robot to turn left at the speed of the left trigger
            Motor1Power = gamepad1.left_trigger*LiftHeightScaled*.5;
            Motor2Power = -gamepad1.left_trigger*LiftHeightScaled*.5;
            Motor3Power = -gamepad1.left_trigger*LiftHeightScaled*.5;
            Motor4Power = gamepad1.left_trigger*LiftHeightScaled*.5;
            moveMotorsPower(Motor1Power, Motor2Power, Motor3Power, Motor4Power);
        }
        else{//If none of these is true turn the power off to the motors to stop the robot
            moveMotorsPower(0, 0, 0, 0);
        }
        telemetry.addData("M1", Motor1Power);
        telemetry.addData("M2", Motor2Power);
        telemetry.addData("M3", Motor3Power);
        telemetry.addData("M4", Motor4Power);

    }
    public void rightDrive(double LiftHeight){

        double JoyY = -gamepad1.left_stick_y;
        double JoyX = gamepad1.left_stick_x;
        double Motor1Power = 0;
        double Motor2Power = 0;
        double Motor3Power = 0;
        double Motor4Power = 0;

        double LiftHeightScaled = 1-Math.abs(LiftHeight)/LiftDivisor;

        if(Math.abs(gamepad1.left_stick_y) >= 0.02 || Math.abs(gamepad1.left_stick_x) >= 0.02){

            Motor1Power = JoyY - JoyX;
            Motor1Power = Range.clip(Motor1Power, -1, 1);
            Motor1Power = Motor1Power*LiftHeightScaled;

            Motor2Power = JoyY + JoyX;
            Motor2Power = Range.clip(Motor2Power, -1, 1);
            Motor2Power = Motor2Power*LiftHeightScaled;

            Motor3Power = JoyY - JoyX;
            Motor3Power = Range.clip(Motor3Power, -1, 1);
            Motor3Power = Motor3Power*LiftHeightScaled;

            Motor4Power = JoyY + JoyX;
            Motor4Power = Range.clip(Motor4Power, -1, 1);
            Motor4Power = Motor4Power*LiftHeightScaled;


            Motor1Power = -Motor1Power;
            Motor2Power = Motor2Power;
            Motor3Power = -Motor3Power;
            Motor4Power = Motor4Power;


            moveMotorsPower(Motor1Power, Motor2Power, Motor3Power, Motor4Power);

        }
        else if(gamepad1.right_trigger >= .02){//This cancels out noise and sets the robot to turn right at the speed of the right trigger
            Motor1Power = -gamepad1.right_trigger*LiftHeightScaled*.5;
            Motor2Power = gamepad1.right_trigger*LiftHeightScaled*.5;
            Motor3Power = gamepad1.right_trigger*LiftHeightScaled*.5;
            Motor4Power = -gamepad1.right_trigger*LiftHeightScaled*.5;
            moveMotorsPower(Motor1Power, Motor2Power, Motor3Power, Motor4Power);
        }
        else if(gamepad1.left_trigger >= .02){//This cancels out noise and sets the robot to turn left at the speed of the left trigger
            Motor1Power = gamepad1.left_trigger*LiftHeightScaled*.5;
            Motor2Power = -gamepad1.left_trigger*LiftHeightScaled*.5;
            Motor3Power = -gamepad1.left_trigger*LiftHeightScaled*.5;
            Motor4Power = gamepad1.left_trigger*LiftHeightScaled*.5;
            moveMotorsPower(Motor1Power, Motor2Power, Motor3Power, Motor4Power);
        }
        else{//If none of these is true turn the power off to the motors to stop the robot
            moveMotorsPower(0, 0, 0, 0);
        }
        telemetry.addData("M1", Motor1Power);
        telemetry.addData("M2", Motor2Power);
        telemetry.addData("M3", Motor3Power);
        telemetry.addData("M4", Motor4Power);

    }
    public void leftDrive(double LiftHeight){

        double JoyY = -gamepad1.left_stick_y;
        double JoyX = gamepad1.left_stick_x;
        double Motor1Power = 0;
        double Motor2Power = 0;
        double Motor3Power = 0;
        double Motor4Power = 0;

        double LiftHeightScaled = 1-Math.abs(LiftHeight)/LiftDivisor;

        if(Math.abs(gamepad1.left_stick_y) >= 0.02 || Math.abs(gamepad1.left_stick_x) >= 0.02){

            Motor1Power = JoyY - JoyX;
            Motor1Power = Range.clip(Motor1Power, -1, 1);
            Motor1Power = Motor1Power*LiftHeightScaled;

            Motor2Power = JoyY + JoyX;
            Motor2Power = Range.clip(Motor2Power, -1, 1);
            Motor2Power = Motor2Power*LiftHeightScaled;

            Motor3Power = JoyY - JoyX;
            Motor3Power = Range.clip(Motor3Power, -1, 1);
            Motor3Power = Motor3Power*LiftHeightScaled;

            Motor4Power = JoyY + JoyX;
            Motor4Power = Range.clip(Motor4Power, -1, 1);
            Motor4Power = Motor4Power*LiftHeightScaled;


            Motor1Power = Motor1Power;
            Motor2Power = -Motor2Power;
            Motor3Power = Motor3Power;
            Motor4Power = -Motor4Power;


            moveMotorsPower(Motor1Power, Motor2Power, Motor3Power, Motor4Power);

        }
        else if(gamepad1.right_trigger >= .02){//This cancels out noise and sets the robot to turn right at the speed of the right trigger
            Motor1Power = -gamepad1.right_trigger*LiftHeightScaled*.5;
            Motor2Power = gamepad1.right_trigger*LiftHeightScaled*.5;
            Motor3Power = gamepad1.right_trigger*LiftHeightScaled*.5;
            Motor4Power = -gamepad1.right_trigger*LiftHeightScaled*.5;
            moveMotorsPower(Motor1Power, Motor2Power, Motor3Power, Motor4Power);
        }
        else if(gamepad1.left_trigger >= .02){//This cancels out noise and sets the robot to turn left at the speed of the left trigger
            Motor1Power = gamepad1.left_trigger*LiftHeightScaled*.5;
            Motor2Power = -gamepad1.left_trigger*LiftHeightScaled*.5;
            Motor3Power = -gamepad1.left_trigger*LiftHeightScaled*.5;
            Motor4Power = gamepad1.left_trigger*LiftHeightScaled*.5;
            moveMotorsPower(Motor1Power, Motor2Power, Motor3Power, Motor4Power);
        }
        else{//If none of these is true turn the power off to the motors to stop the robot
            moveMotorsPower(0, 0, 0, 0);
        }
        telemetry.addData("M1", Motor1Power);
        telemetry.addData("M2", Motor2Power);
        telemetry.addData("M3", Motor3Power);
        telemetry.addData("M4", Motor4Power);

    }

    public void moveMotorsPower (double Motor1Power, double Motor2Power, double Motor3Power, double Motor4Power){
        Motor1Power = Range.clip(Motor1Power, -1, 1);
        Motor2Power = Range.clip(Motor2Power,-1,1);
        Motor3Power = Range.clip(Motor3Power, -1, 1);
        Motor4Power = Range.clip(Motor4Power, -1, 1);


        robot.Motor1.setPower(Motor1Power);
        robot.Motor2.setPower(Motor2Power);
        robot.Motor3.setPower(Motor3Power);
        robot.Motor4.setPower(Motor4Power);
    }

    public void feildOrentedDrive(double LiftHeight){
        double JoyX;
        double JoyY;
        double NewX;
        double NewY;
        double OrientationDegrees;
        double OrientationRadians;
        double Motor1Power = 0;
        double Motor2Power = 0;
        double Motor3Power = 0;
        double Motor4Power = 0;

        JoyY = -gamepad1.left_stick_y;
        JoyX = gamepad1.left_stick_x;
        OrientationDegrees = robot.navx_device.getYaw();
        OrientationRadians = OrientationDegrees * Math.PI / 180;
        NewY = JoyY * Math.cos(OrientationRadians) + JoyX * Math.sin(OrientationRadians);
        NewX = -JoyY * Math.sin(OrientationRadians) + JoyX * Math.cos(OrientationRadians);

        double LiftHeightScaled = 1-Math.abs(LiftHeight)/LiftDivisor;

        if(Math.abs(gamepad1.left_stick_y) >= 0.02 || Math.abs(gamepad1.left_stick_x) >= 0.02){

            Motor1Power = NewY - NewX;
            Motor1Power = Range.clip(Motor1Power, -1, 1);
            Motor1Power = Motor1Power*LiftHeightScaled;

            Motor2Power = NewY + NewX;
            Motor2Power = Range.clip(Motor2Power, -1, 1);
            Motor2Power = Motor2Power*LiftHeightScaled;

            Motor3Power = NewY - NewX;
            Motor3Power = Range.clip(Motor3Power, -1, 1);
            Motor3Power = Motor3Power*LiftHeightScaled;

            Motor4Power = NewY + NewX;
            Motor4Power = Range.clip(Motor4Power, -1, 1);
            Motor4Power = Motor4Power*LiftHeightScaled;


            Motor1Power = Motor1Power;
            Motor2Power = Motor2Power;
            Motor3Power = Motor3Power;
            Motor4Power = Motor4Power;


            moveMotorsPower(Motor1Power, Motor2Power, Motor3Power, Motor4Power);

        }
        else if(gamepad1.right_trigger >= .02){//This cancels out noise and sets the robot to turn right at the speed of the right trigger
            Motor1Power = -gamepad1.right_trigger*LiftHeightScaled*.5;
            Motor2Power = gamepad1.right_trigger*LiftHeightScaled*.5;
            Motor3Power = gamepad1.right_trigger*LiftHeightScaled*.5;
            Motor4Power = -gamepad1.right_trigger*LiftHeightScaled*.5;
            moveMotorsPower(Motor1Power, Motor2Power, Motor3Power, Motor4Power);
        }
        else if(gamepad1.left_trigger >= .02){//This cancels out noise and sets the robot to turn left at the speed of the left trigger
            Motor1Power = gamepad1.left_trigger*LiftHeightScaled*.5;
            Motor2Power = -gamepad1.left_trigger*LiftHeightScaled*.5;
            Motor3Power = -gamepad1.left_trigger*LiftHeightScaled*.5;
            Motor4Power = gamepad1.left_trigger*LiftHeightScaled*.5;
            moveMotorsPower(Motor1Power, Motor2Power, Motor3Power, Motor4Power);
        }
        else{//If none of these is true turn the power off to the motors to stop the robot
            moveMotorsPower(0, 0, 0, 0);
        }
        telemetry.addData("M1", Motor1Power);
        telemetry.addData("M2", Motor2Power);
        telemetry.addData("M3", Motor3Power);
        telemetry.addData("M4", Motor4Power);

    }

    void collection(){
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
    void initCamera(){
        super.init();
        /**
         * Set the camera used for detection
         * PRIMARY = Front-facing, larger camera
         * SECONDARY = Screen-facing, "selfie" camera :D
         **/
        this.setCamera(Cameras.PRIMARY);

        /**
         * Set the frame size
         * Larger = sometimes more accurate, but also much slower
         * After this method runs, it will set the "width" and "height" of the frame
         **/
        this.setFrameSize(new Size(900, 900));

        /**
         * Enable extensions. Use what you need.
         * If you turn on the BEACON extension, it's best to turn on ROTATION too.
         */
        enableExtension(Extensions.BEACON);         //Beacon detection
        enableExtension(Extensions.ROTATION);       //Automatic screen rotation correction
        enableExtension(Extensions.CAMERA_CONTROL); //Manual camera control

        /**
         * Set the beacon analysis method
         * Try them all and see what works!
         */
        beacon.setAnalysisMethod(Beacon.AnalysisMethod.COMPLEX);

        /**
         * Set color tolerances
         * 0 is default, -1 is minimum and 1 is maximum tolerance
         */
        beacon.setColorToleranceRed(0);
        beacon.setColorToleranceBlue(0);

        /**
         * Set analysis boundary
         * You should comment this to use the entire screen and uncomment only if
         * you want faster analysis at the cost of not using the entire frame.
         * This is also particularly useful if you know approximately where the beacon is
         * as this will eliminate parts of the frame which may cause problems
         * This will not work on some methods, such as COMPLEX
         **/
        //beacon.setAnalysisBounds(new Rectangle(new Point(width / 2, height / 2), width - 200, 200));

        /**
         * Set the rotation parameters of the screen
         * If colors are being flipped or output appears consistently incorrect, try changing these.
         *
         * First, tell the extension whether you are using a secondary camera
         * (or in some devices, a front-facing camera that reverses some colors).
         *
         * It's a good idea to disable global auto rotate in Android settings. You can do this
         * by calling disableAutoRotate() or enableAutoRotate().
         *
         * It's also a good idea to force the phone into a specific orientation (or auto rotate) by
         * calling either setActivityOrientationAutoRotate() or setActivityOrientationFixed(). If
         * you don't, the camera reader may have problems reading the current orientation.
         */
        rotation.setIsUsingSecondaryCamera(false);
        rotation.disableAutoRotate();
        rotation.setActivityOrientationFixed(ScreenOrientation.PORTRAIT);

        /**
         * Set camera control extension preferences
         *
         * Enabling manual settings will improve analysis rate and may lead to better results under
         * tested conditions. If the environment changes, expect to change these values.
         */
        cameraControl.setColorTemperature(CameraControlExtension.ColorTemperature.AUTO);
        cameraControl.setAutoExposureCompensation();
    }

}
