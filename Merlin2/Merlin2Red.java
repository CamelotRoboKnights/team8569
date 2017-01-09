package org.firstinspires.ftc.teamcode.team.Merlin2;//This might need to changed to be in a differnt folder like Merlin1 or K9Robo


import android.widget.Switch;

import com.ftdi.j2xx.ft4222.FT_4222_Defines;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CompassSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.DifferentialControlLoopCoefficients;
import com.qualcomm.robotcore.util.Range;

import java.lang.annotation.Target;
import java.util.*;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.team.Merlin1.Merlin1Hardware; //More Import statements may be needed

import java.sql.Time;

@Autonomous(name = "RED2", group = "Merlin2")//This NEEDS to be changed tp the name of the code
//@Disabled //Uncomment this if it is not wanted on the phone
public class Merlin2Red extends LinearOpMode { //The name after public class needs to be the same as the file name
    long StartTime = 0;
    long TargetTime = 0;
    double LastWorldLinearAccelX;
    double LastWorldLinearAccelY;
    static double LeftLightValue = .05;
    static double RightLightValue = .2;
    boolean CheckForWhiteLine = false;
    double DistanceTraveled = 0;
    double VelocityBefore = 0;
    double VelocityNow = 0;
    boolean FirstTime = false;
    double StartEncoder;

    /* Declare OpMode members. */
    Merlin2Hardware robot = new Merlin2Hardware();//The hardware map needs to be the hardware map of the robot we are using

    @Override
    public void runOpMode() {
        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);
        //init other variables.
        String CaseValue = "test";
        String CaseCompletion = "";

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Say", "Hello Driver");    //It is ready to run
        telemetry.update();//Updates and displays data on the screen.
        // run until the end of the match (driver presses STOP)
        waitForStart();
        while (opModeIsActive()) {

            switch (CaseValue){
                case "test":

                    //CaseCompletion = driveBasedOnEncoders(24, "Forward");
                    CaseCompletion = turnToGyroHeading(-45);
                    if(CaseCompletion.equals("Done")){
                        telemetry.addData("Done","Done");
                        CaseValue = "Done";
                    }
                    else if (CaseCompletion.equals("NOTDONE")){
                        telemetry.addData("NotDone", 1);
                    }
                    else{
                        telemetry.addData("BROKEN", CaseCompletion);
                    }

                    break;
                case "Done":
                    done();
                    break;
                default:
                    broken();
                    break;
            }


            telemetry.update();
            // Pause for metronome tick.  40 mS each cycle = update 25 times a second.
            robot.waitForTick(40);
        }
    }
    public void done(){
        telemetry.addData("Done","Case");
        moveMotorsPower(0,0,0,0);
    }
    public void broken(){
        telemetry.addData("Try", "Again");
        moveMotorsPower(0,0,0,0);
    }

    public String turnToGyroHeading(double TargetHeading) {//Working
        String ReturnValue = "----";
        double CurrentHeading = robot.navx_device.getYaw();
        double HeadingDifference = TargetHeading - CurrentHeading;
        double HeadingScaler = .005;
        double HeadingDiffernceScalled = HeadingDifference * HeadingScaler;
        HeadingDiffernceScalled = Range.clip(HeadingDiffernceScalled, -1, 1);

        if(HeadingDiffernceScalled < .09 && HeadingDiffernceScalled > 0){
            HeadingDiffernceScalled = .09;
        }
        else if(Math.abs(HeadingDiffernceScalled) < .09 && HeadingDiffernceScalled <0){
            HeadingDiffernceScalled = -.09;
            telemetry.addData("I got", "Here");
        }
        else{
            telemetry.addData("THIS IS BEING DUMB","YES IT IS");
        }
        telemetry.addData("HDS", HeadingDiffernceScalled);
        telemetry.addData("CurrentYAW", CurrentHeading);
        moveMotorsPower(-HeadingDiffernceScalled, HeadingDiffernceScalled, HeadingDiffernceScalled, -HeadingDiffernceScalled);
        if (1 >= Math.abs(HeadingDifference)) {
            ReturnValue = "Done";
        } else {
            ReturnValue = "NOTDONE";
        }
        return ReturnValue;
    }
    public void moveMotorsPower (double Motor1Power, double Motor2Power, double Motor3Power, double Motor4Power){//Working, base
        Motor1Power = Range.clip(Motor1Power, -1, 1);
        Motor2Power = Range.clip(Motor2Power,-1,1);
        Motor3Power = Range.clip(Motor3Power, -1, 1);
        Motor4Power = Range.clip(Motor4Power, -1, 1);


        robot.Motor1.setPower(Motor1Power);
        robot.Motor2.setPower(Motor2Power);
        robot.Motor3.setPower(Motor3Power);
        robot.Motor4.setPower(Motor4Power);
    }

    public String loadBall() {//Works with the knot in the front and ball at the top of the shoot.
        String ReturnValue = "";
        double CurrentTime = System.currentTimeMillis();
        if (StartTime == 0) {
            StartTime = System.currentTimeMillis();
            TargetTime = StartTime + 3000;
        }
        if (TargetTime - CurrentTime <= 10) {
            ReturnValue = "Done";
            StartTime = 0;
            robot.LiftCollector.setPower(0);
        } else {
            ReturnValue = "----";
            robot.LiftCollector.setPower(.5);
        }
        return "";
    }

    public String driveUltilWhiteLine(String Direction, String Sensor, double speed) {//Works Max speed, .3 min speed .9 Max speed passes line by about an inch
        double LightRead = 0;
        String ReturnValue = "";
        if (Sensor.equals("Left")) {
            telemetry.addData("Left", "Sensor");
            LightRead = robot.LeftLight.getRawLightDetected();
            if(LightRead > LeftLightValue && !CheckForWhiteLine){
                CheckForWhiteLine = true;
                ReturnValue = "NOTDONE";
            }
            else if (LightRead > LeftLightValue && CheckForWhiteLine) {
                moveMotorsPower(0,0,0,0);
                ReturnValue = "Done";
                CheckForWhiteLine = false;
            }
            else ReturnValue = "NOTDONE";
        }
        else if (Sensor.equals("Right")) {
            telemetry.addData("Right", "Sensor");
            LightRead = robot.RightLight.getRawLightDetected();
            if(LightRead > RightLightValue && !CheckForWhiteLine){
                CheckForWhiteLine = true;
                ReturnValue = "NOTDONE";
            }
            if (LightRead > RightLightValue && CheckForWhiteLine) {
                moveMotorsPower(0,0,0,0);
                ReturnValue = "Done";
                CheckForWhiteLine = false;
            }
            else ReturnValue = "NOTDONE";
        }
        else {
            moveMotorsPower(0,0,0,0);
            telemetry.addData("bad", "sensor");
        }
        if(Direction.equals("Forward")){
            moveMotorsPower(speed,speed,speed,speed);
            telemetry.addData("Forward", "Forward");
        }
        else if(Direction.equals("Back")){
            moveMotorsPower(speed,speed,speed,speed);
            telemetry.addData("Back", "Back");
        }
        else if (Direction.equals("Left")){
            moveMotorsPower(speed,speed,speed,speed);
            telemetry.addData("Left", "Left");
        }
        else if (Direction.equals("Right")){
            moveMotorsPower(speed,speed,speed,speed);
            telemetry.addData("Right", "Right");
        }
        else {
            telemetry.addData("You Broke", "it with direction");
        }
        telemetry.addData("Light read", LightRead);
        return ReturnValue;


    }

    public String collisionDetection() {//IT WORKS!!!!
        double CurrWorldLinearAccelX;//My current acceleration
        double CurrWorldLinearAccelY;
        double CurrentJerkX;//My chang in Acceleration
        double CurrentJerkY;
        final double CollisionThesholdG = .25;//The threshold that has to be crossed to trigger a colision
        String ReturnValue = "";//The Value that will be returned

        CurrWorldLinearAccelX = robot.navx_device.getWorldLinearAccelX();//Gives the current acceleration
        CurrWorldLinearAccelY = robot.navx_device.getWorldLinearAccelY();
        CurrentJerkX = CurrWorldLinearAccelX - LastWorldLinearAccelX;//Figures out the acceleration change
        CurrentJerkY = CurrWorldLinearAccelY - LastWorldLinearAccelY;
        if (CollisionThesholdG < Math.abs(CurrentJerkX) || CollisionThesholdG < Math.abs(CurrentJerkY)) {//Detects if a collision has happened
            if (CurrentJerkX >= CollisionThesholdG) {
                ReturnValue = "YFast";//The robot is going faster in the Y direction
            } //if
            else if (CurrentJerkX <= -CollisionThesholdG) {
                ReturnValue = "YStop";//The robot is going slower in the Y direction
            } //else if

            if (CurrentJerkY >= CollisionThesholdG) {
                ReturnValue = "XFast";//The robot is going faster in the X direction
            } //if
            else if (CurrentJerkY <= -CollisionThesholdG) {
                ReturnValue = "XStop";//The robot is going slower in the X direction
            }//else if
        } //if
        else {
            ReturnValue = "";
        }//else
        telemetry.addData("Collision", ReturnValue);
        telemetry.addData("AccelX", CurrWorldLinearAccelX);
        telemetry.addData("AccelY", CurrWorldLinearAccelX);

        LastWorldLinearAccelX = CurrWorldLinearAccelX;
        LastWorldLinearAccelY = CurrWorldLinearAccelY;

        return ReturnValue;
    }

    public double launchBall(double TargetEncoder) {//NEEDS MORE WORK
        double CurrentEncoder = robot.Flipper.getCurrentPosition();
        double OneRotation = 1650;

        if (TargetEncoder - CurrentEncoder < 3) {
            robot.Flipper.setPower(0);
            if (gamepad2.dpad_up) {
                TargetEncoder = TargetEncoder + OneRotation;
            }
        } else {
            robot.Flipper.setPower(.9);
        }
        return TargetEncoder;
    }


    public void lineFollow(String SideOfRobot, String SideOfLine) {// We wont use this so untested
        double LightError = 0;
        double LightInegral = 0;
        double LightDerivative = 0;
        double LightErrorScaled;
        double LightInegralScaled;
        double LightDerivativeScaled;
        double LightErrorScaler = 1;
        double LightIntegralScaler = 1;
        double LightDerivativeScaler = 1;

        double TargetLightValue = 0;
        double CurrentLightValue = 0;
        double LastLightError = 0;
        double MotorPowerMotoifier;
        double Motor1Power = 0;
        double Motor2Power = 0;
        double Motor3Power = 0;
        double Motor4Power = 0;

        if (SideOfRobot.equals("left")) {
            TargetLightValue = LeftLightValue;//The left is 0.0771358 and the right is .02833764
            CurrentLightValue = robot.LeftLight.getLightDetected();
        } else if (SideOfRobot.equals("right")) {
            TargetLightValue = RightLightValue;
            CurrentLightValue = robot.LeftLight.getLightDetected();
        } else {
            telemetry.addData("Break!!!!!!!!!!!!!!!!!!", "");
            telemetry.update();
        }
        LightError = TargetLightValue - CurrentLightValue;
        LightInegral = LightInegral + LightError;
        LightDerivative = LightError - LastLightError;
        LastLightError = LightError;
        LightErrorScaled = LightError * LightErrorScaler;
        LightInegralScaled = LightInegral * LightIntegralScaler;
        LightDerivativeScaled = LightDerivative * LightDerivativeScaler;
        MotorPowerMotoifier = LightErrorScaled + LightDerivativeScaled + LightInegralScaled;
        if (SideOfLine.equals("left")) {
            Motor1Power = .2 - MotorPowerMotoifier;
            Motor2Power = -.2 + MotorPowerMotoifier;
            Motor3Power = .2 + MotorPowerMotoifier;
            Motor4Power = -.2 - MotorPowerMotoifier;
        } else if (SideOfLine.equals("right")) {
            Motor1Power = .2 + MotorPowerMotoifier;
            Motor2Power = -.2 - MotorPowerMotoifier;
            Motor3Power = .2 - MotorPowerMotoifier;
            Motor4Power = -.2 + MotorPowerMotoifier;
        } else {
            telemetry.addData("Break!!!!!!!!!!!!!!!!!!", "");
            telemetry.update();
        }

        Motor1Power = Range.clip(Motor1Power, -1, 1);
        Motor2Power = Range.clip(Motor2Power, -1, 1);
        Motor3Power = Range.clip(Motor3Power, -1, 1);
        Motor4Power = Range.clip(Motor4Power, -1, 1);
        robot.Motor1.setPower(Motor1Power);
        robot.Motor2.setPower(Motor2Power);
        robot.Motor3.setPower(Motor3Power);
        robot.Motor4.setPower(Motor4Power);

        telemetry.addData("current light value", CurrentLightValue);
        telemetry.addData("light error", LightError);
        telemetry.addData("leght derivitatve", LightDerivative);
        telemetry.addData("light integral", LightInegral);
        telemetry.addData("motor modifier", MotorPowerMotoifier);
        telemetry.addData("motor1", Motor1Power);
        telemetry.addData("motor2", Motor2Power);
        telemetry.addData("motor3", Motor3Power);
        telemetry.addData("motor4", Motor4Power);


    }

    public String DriveUntilHit() {//This Works speed might be able to be deresed but it passed the mom test
        //The threshold that has to be crossed to trigger a colision
        String ReturnedValue = collisionDetection();
        moveMotorsPower(.6,-.6,.6,-.6);

        if (ReturnedValue.equals("YStop")) {
            moveMotorsPower(0,0,0,0);
            return "Done";
        }
        else if (ReturnedValue.equals("XStop")) {
            moveMotorsPower(0,0,0,0);
            return "Done";
        }
        else {
            return "NOTDONE";
        }
    }
    public String CrossLeftLight() {//It works

        double CurrentLightValue;
        String ReturnValue= "";

        CurrentLightValue = robot.LeftLight.getLightDetected();

        if(CurrentLightValue < LeftLightValue && CheckForWhiteLine){
            ReturnValue = "Done";
            moveMotorsPower(0,0,0,0);
        }
        else if(CurrentLightValue > LeftLightValue && !CheckForWhiteLine){
            CheckForWhiteLine = true;
            ReturnValue = "NOTDONE";
            moveMotorsPower(.2,.2,.3,.3);
        }
        else{
            telemetry.addData("Nothing","");
            ReturnValue = "NOTDONE";
            moveMotorsPower(.3,.3,.3,.3);
            telemetry.update();
        }
        telemetry.addData("WHITE SEEN?", CheckForWhiteLine);
        return ReturnValue;
    }
    public String driveDistanceAccelAndGyroHeadingFollow(String Direction, double Distance){
        double Acceleration = 0;
        String ReturnValue = "";
        double CurrentTime = (System.currentTimeMillis()/1000) - StartTime;
        double Speed = 0;

        if (StartTime == 0) {
            StartTime = System.currentTimeMillis()/1000;
        }
        if(Direction.equals("Forward") || Direction.equals("Back")){
            Acceleration = robot.navx_device.getWorldLinearAccelY()*386.0886;
            telemetry.addData("Y", "Y");
        }
        else if (Direction.equals("Left") || Direction.equals("Right")){
            Acceleration = robot.navx_device.getWorldLinearAccelX()*386.0886;
            telemetry.addData("X","X");
        }
        else{
            telemetry.addData("BREAK", "BREAK");
        }
        //Acceleration = Math.round(Acceleration);
        VelocityNow = VelocityBefore + Acceleration;
        DistanceTraveled = DistanceTraveled + (((VelocityNow+VelocityBefore)/2)*CurrentTime);
        VelocityBefore = VelocityNow;
        Speed = (Distance-DistanceTraveled)*.1;
        if(Speed < .2) Speed = .2;
        if(DistanceTraveled >= Distance){
            moveMotorsPower(0,0,0,0);
            telemetry.addData("DONE", "DONE");
            //ReturnValue = "Done";
        }
        else {
            if(Direction.equals("Forward")) moveMotorsPower(.2,.2,.2,.2);
            else if (Direction.equals("Back")) moveMotorsPower(-Speed,-Speed,-Speed,-Speed);
            else if (Direction.equals("Left")) moveMotorsPower(Speed, -Speed,Speed,-Speed);
            else if (Direction.equals("Right")) moveMotorsPower(-Speed,Speed,-Speed,Speed);
            else telemetry.addData("DIRECTION Broke it", "Yes it did");
            ReturnValue = "NOTDONE";
        }
        telemetry.addData("Distance gone", DistanceTraveled);
        telemetry.addData("Accel", Acceleration);
        telemetry.addData("StartTime", StartTime);
        telemetry.addData("CurrentTime", CurrentTime);
        telemetry.addData("Time", CurrentTime);
        telemetry.addData("Vol", VelocityNow);
        return ReturnValue;

    }


    public String IdentifyColor(){ //this is for red side
        //these variables need to be passed in from other vision code
        String BeaconLeftSide = "red";
        String BeaconRightSide = "blue";
        if (BeaconLeftSide == "red" & BeaconRightSide == "blue"){
            return "go left";
        //    telemetry.addData("go left", BeaconLeftSide);
        }
        else if(BeaconLeftSide == "blue" & BeaconRightSide == "red"){
            return "go right";
         //   telemetry.addData("go right", BeaconLeftSide);
        }
        else{
            return "bad!!!!!!";
        //    telemetry.addData("no color detected", "");
        }

    }
    public String driveBasedOnEncoders(double Distance, String Direction){
        String ReturnValue = "";
        double Speed;

        if(!FirstTime) {
            StartEncoder = robot.Motor1.getCurrentPosition();
            FirstTime = true;
        }
        else {
            double CurrentEncoder = robot.Motor1.getCurrentPosition() - StartEncoder;
            double OneRotation = 1120;
            double WheelSize = 4*Math.PI;
            DistanceTraveled = ((Math.abs(CurrentEncoder) / OneRotation) * WheelSize)*1.125;
            telemetry.addData("",CurrentEncoder);
            if (DistanceTraveled > Distance) {
                moveMotorsPower(0,0,0,0);
                ReturnValue = "Done";
            }
            else {
                ReturnValue = "NOTDONE";
                Speed = Distance - DistanceTraveled*.1;
                switch (Direction){
                    case "Forward":
                        moveMotorsPower(.2,.2,.2,.2);
                        break;
                    case "Back":
                        moveMotorsPower(-Speed,-Speed,-Speed,-Speed);
                        break;
                    case "Left":
                        moveMotorsPower(Speed, -Speed,Speed,-Speed);
                        break;
                    case "Right":
                        moveMotorsPower(-Speed,Speed,-Speed,Speed);
                        break;
                    default:
                        telemetry.addData("DIRECTION Broke it", "Yes it did");
                        break;


                }
            }
        }

        telemetry.addData("Distance", DistanceTraveled);
        return ReturnValue;

    }

}