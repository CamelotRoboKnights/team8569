/*
* My methods and what they do.
*
* done - This is used in the done case and prints done on the screen and turns the motors off
* broken - This is used in the default care to tell us that the code lost track of where it should be
* turnToGyroHeading - This will turn the robot within 2 degrees of the "TargetHeading" that I enter.
* moveMotorsPower - This will move all the motors at the speed that is imputed into it
* resetAll - Resets all variables in this program so they can be used in a different method later
* loadBall- Loads the ball to get it ready to shoot
* driveUntilWhiteLine - Drives the robot at any speed it is given till it sees a white line
* collisionDetection - Used to understand if the robot has been hit
* launchBall- launches the ball
* driveUntilHit - Drives the robot till it runs into a wall
* crossLeftLight - Has the robot drive till the left light sensor crosses over the line
* choseSide - Determines beacon color and which side the robot needs to go hit
* driveBasedOnEncoders - drives the robot to a distance based on encoder ticks
* cameraInit - Initializes the camera when called
*
 */

//Commenting is done 17-1-13
package org.firstinspires.ftc.teamcode.team.Merlin2;

import android.widget.Switch;

import com.qualcomm.robotcore.util.Range;//Allows the use of the Range Clip
import org.lasarobotics.vision.android.Cameras;//Lets us access and modify the camera
import org.lasarobotics.vision.ftc.resq.Beacon;//Lets us understand what the beacon looks like
import org.lasarobotics.vision.opmode.VisionOpMode;//lets us use the Vision op mode that is what we are extending
import org.lasarobotics.vision.opmode.extensions.CameraControlExtension;//Gives more control of the camera
import org.lasarobotics.vision.util.ScreenOrientation;//Lets us control the orientation of the screen
import org.opencv.core.Size;//Lets us understand picture size

class Merlin2Auto extends VisionOpMode {//This extends Vision Op Mode witch allows us to use the
    private long StartTime = 0;//Used in the loadBall method
    private long TargetTime = 0;//Used in the loadBall method
    private double LastWorldLinearAccelX;//Used in the collisionDetection method
    private double LastWorldLinearAccelY;//Used in the collisionDetection method
    private static double LeftLightValue = .07;//The value of the white line for the left sensor, used in driveUntilWhiteLine and crossLeftLight
    private static double RightLightValue = .09;//The value of the white line for the right sensor, used in driveUntilWhiteLine and crossLeftLight
    private boolean CheckForWhiteLine = false;//A boolean to say if the white line has been seen, used in driveUntilWhiteLine and crossLeftLight
    private double DistanceTraveled = 0;//Used in driveBasedOnEncoders to remember how far the robot has gone
    private double StartEncoder;//Used in driveBasedOnEncoders to remember the starting encoder value
    private boolean FirstTime = true;//Used in driveBasedOnEncoders and launch ball to signify the first time the method has run
    private double TargetEncoder = 0;//Used in launchBall to determine at what encoder value the ball will be launched.
    private int CantTellCounter;//The rest are used in choseSide to decide what color is it
    private int RightConfidence;
    private int LeftConfidence;
    private int AllBlue;
    private int AllRed;

    /* Declare OpMode members. */
    Merlin2Hardware robot = new Merlin2Hardware();//The hardware map needs to be the hardware map of the robot we are using
    @Override
    public void init(){robot.init(hardwareMap);

    }//This initializes the hardware map for use
    @Override
    public void init_loop(){}//Part of the OpMode requirements
    @Override
    public void start(){}//Part of the OpMode requirements
    @Override
    public void loop(){}//Part of the OpMode requirements
    @Override
    public void stop(){}//Part of the OpMode requirements


    void done(){//What the done class will do when run, it will display Done Case and set the motor powers off
        telemetry.addData("Done","Case");
        moveMotorsPower(0,0,0,0);
    }


    void broken(){//What the robot will do if it gets to the default case, it will display Try Again and turn the motor powers off
        telemetry.addData("Try", "Again");
        moveMotorsPower(0,0,0,0);
    }

    String turnToGyroHeading(double TargetHeading) {//Working and will turn the robot to a gyro heading within 2degrees
        String ReturnValue;//The value the method will return
        double CurrentHeading = robot.navx_device.getYaw();//The robot's current heading
        double HeadingDifference = TargetHeading - CurrentHeading;//How far the robot is from its target heading
        double HeadingScaler = .005;//The scalier that edits how much the speed is affect
        double HeadingDiffernceScalled = HeadingDifference * HeadingScaler;//The scaled value that is used for the motor power
        HeadingDiffernceScalled = Range.clip(HeadingDiffernceScalled, -1, 1);//Making sure that the number is within a reasonable motor power

        if(HeadingDiffernceScalled < .09 && HeadingDiffernceScalled > 0){//making sure the motor power is not so low that the robot wont move
            HeadingDiffernceScalled = .09;
        }
        else if(Math.abs(HeadingDiffernceScalled) < .09 && HeadingDiffernceScalled <0){//making sure the motor power is not so low that the robot wont move
            HeadingDiffernceScalled = -.09;
            telemetry.addData("I got", "Here");
        }
        else{
            telemetry.addData("THIS IS BEING DUMB","YES IT IS");//making sure the motor power is not so low that the robot wont move
        }
        telemetry.addData("HDS", HeadingDiffernceScalled);//Prints the motor powers
        telemetry.addData("CurrentYAW", CurrentHeading);//Prints the current angle the robot is at
        moveMotorsPower(-HeadingDiffernceScalled, HeadingDiffernceScalled, HeadingDiffernceScalled, -HeadingDiffernceScalled);//My method to run the motors
        if (1 >= Math.abs(HeadingDifference)) {//If it is within 2 degrees I am done
            ReturnValue = "Done";
        } else {//Otherwise it isn't done
            ReturnValue = "NOTDONE";
        }
        return ReturnValue;
    }
    private void moveMotorsPower (double Motor1Power, double Motor2Power, double Motor3Power, double Motor4Power){//This method range clips the values put into it and sets these to the motor powers
        Motor1Power = Range.clip(Motor1Power, -1, 1);
        Motor2Power = Range.clip(Motor2Power,-1,1);
        Motor3Power = Range.clip(Motor3Power, -1, 1);
        Motor4Power = Range.clip(Motor4Power, -1, 1);


        robot.Motor1.setPower(Motor1Power);
        robot.Motor2.setPower(Motor2Power);
        robot.Motor3.setPower(Motor3Power);
        robot.Motor4.setPower(Motor4Power);
    }
    void resetAll(){//These reset all the values for future use and turns off all motors to get it ready for the next case
        StartTime = 0;
        TargetTime = 0;
        CheckForWhiteLine = false;
        DistanceTraveled = 0;
        StartEncoder = 0;
        FirstTime = true;
        TargetEncoder = 0;
        moveMotorsPower(0,0,0,0);
        robot.Flipper.setPower(0);
        robot.Lift.setPower(0);
        robot.LiftCollector.setPower(0);
        CantTellCounter = 0;
    }
    String loadBall() {//Works with the knot in the front and ball at the top of the shoot.
        String ReturnValue = "";
        double CurrentTime = System.currentTimeMillis();//Gets the current time
        if (StartTime == 0) {//if it is the first time through get the start time
            StartTime = System.currentTimeMillis();
            TargetTime = StartTime + 1000;
        }
        else if (TargetTime - CurrentTime <= 10) {//otherwise if it is close to the time of completion be done
            ReturnValue = "Done";
            StartTime = 0;
            robot.LiftCollector.setPower(0);
        }
        else {//but if it still has time to go keep going
            ReturnValue = "NOTDONE";
            robot.LiftCollector.setPower(.5);
        }
        return ReturnValue;
    }

    String driveUltilWhiteLine(String Direction, String Sensor, double speed) {//Works Max speed, .3 min speed .9 Max speed passes line by about an inch
        double LightRead = 0;
        String ReturnValue = "";
        switch (Sensor){
            case "Left"://If the sensor I am useing is the left one
                telemetry.addData("Left", "Sensor");
                LightRead = robot.LeftLight.getLightDetected();
                if (LightRead > LeftLightValue) {
                    moveMotorsPower(0,0,0,0);
                    ReturnValue = "Done";
                }
                else ReturnValue = "NOTDONE";
                break;
            case "Right"://if the sensor I am using is the right one
                telemetry.addData("Right", "Sensor");
                LightRead = robot.RightLight.getLightDetected();
                if (LightRead > RightLightValue) {
                    moveMotorsPower(0,0,0,0);
                    ReturnValue = "Done";
                }
                else ReturnValue = "NOTDONE";
                break;
            default://If it is given anything else it stops and informs that It was given a bad imput
                moveMotorsPower(0,0,0,0);
                telemetry.addData("bad", "sensor");
                break;

        }
        switch (Direction){//Decides the direction the robot is going
            case "Forward"://If the direction is forward
                moveMotorsPower(speed,speed,speed,speed);//Set the motors speed
                telemetry.addData("Forward", "Forward");//Display that Im going forward
                break;
            case "Back"://If the direction is back
                moveMotorsPower(-speed,-speed,-speed,-speed);//Set the motors speed
                telemetry.addData("Back", "Back");//Display that Im going back
                break;
            case "Left"://If the direction is left
                moveMotorsPower(speed,-speed,speed,-speed);//Set the motors speed
                telemetry.addData("Left", "Left");//Display that Im going left
                break;
            case "Right"://If the direction is left
                moveMotorsPower(-speed,speed,-speed,speed);//Set the motors speed
                telemetry.addData("Right", "Right");//Display that Im going right
                break;
            default://If it is given a weird direction
                telemetry.addData("You Broke", "it with direction");//Tell me that it is broken
                break;
        }
        telemetry.addData("Light read", LightRead);//Display the light value
        return ReturnValue;


    }

    private String collisionDetection() {//This is a private method that other methods use to determine if the robot has been hit
        double CurrWorldLinearAccelX;//My current acceleration
        double CurrWorldLinearAccelY;
        double CurrentJerkX;//My change in Acceleration
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
    String launchBall() {//Launches the ball
        double CurrentEncoder = robot.Flipper.getCurrentPosition();//Gts my current encoder
        double OneRotation = 1650;//One rotation is this many ticks
        String ReturnValue;

        if (TargetEncoder - CurrentEncoder < 3 && FirstTime) {//If it is the first time running the code set the target encoder and turn it to run.
            TargetEncoder = CurrentEncoder + OneRotation;
            robot.Flipper.setPower(.9);
            FirstTime = false;
            ReturnValue = "NOTDONE";
        }
        else if(TargetEncoder - CurrentEncoder < 3 && !FirstTime){//If it has gotten back to the start it is done
            ReturnValue = "Done";
            robot.Flipper.setPower(0);
            FirstTime = true;
        }
        else {//Otherwise keep going
            robot.Flipper.setPower(.9);
            ReturnValue = "NOTDONE";
        }
        return ReturnValue;
    }

    String driveUntilHit(String Side) {//This Works speed might be able to be deresed but it passed the mom test
        String ReturnedValue = collisionDetection();//This gets the collision value
        switch(Side){
            case "Red":
                moveMotorsPower(.6,-.6,.6,-.6);//Go forward at .6 motor power
                break;
            case "Blue":
                moveMotorsPower(-.6,.6,-.6,.6);//Go forward at .6 motor power
                break;
        }

        switch (ReturnedValue){ //If the value returned is slowing it down in any direction it is done otherwise im not done yet
            case "YStop":
                moveMotorsPower(0,0,0,0);
                return "Done";
            case "XStop":
                moveMotorsPower(0,0,0,0);
                return "Done";
            default:
                return "NOTDONE";
        }
    }

    String crossLeftLight() {//It crosses over the left light sensor till it has passed the white line

        double CurrentLightValue = robot.LeftLight.getLightDetected();//The current light value
        String ReturnValue;//The value that will be returned

        if(CurrentLightValue < LeftLightValue && CheckForWhiteLine){//If I see black and have already seen white I am done
            ReturnValue = "Done";
            moveMotorsPower(0,0,0,0);
        }
        else if(CurrentLightValue > LeftLightValue && !CheckForWhiteLine){//If I see white line remember that
            CheckForWhiteLine = true;
            ReturnValue = "NOTDONE";
            moveMotorsPower(.3,.3,.3,.3);
        }
        else{//Otherwise keep going
            telemetry.addData("Nothing","");
            ReturnValue = "NOTDONE";
            moveMotorsPower(.3,.3,.3,.3);
            telemetry.update();
        }
        telemetry.addData("WHITE SEEN?", CheckForWhiteLine);//Tell me if ive seen white
        return ReturnValue;
    }

    String choseSide(String TeamColor){ //this is for red side
        String Side = "";//The side of the beacon is the color I want
        if(TeamColor.equals("RED")){//If my team color is red
            if (beacon.getAnalysis().isBeaconFound()){//if I am confident enough in my color desision
                if(beacon.getAnalysis().isLeftBlue() && beacon.getAnalysis().isRightBlue()){//If both sides are blue return that is is all blue
                    AllBlue++;
                    if(AllBlue > 1000) {
                        Side = "NOBlue";
                    }
                }
                else if (beacon.getAnalysis().isLeftRed() && beacon.getAnalysis().isRightRed()){//If both sides are red return that is is all red
                    AllRed++;
                    if(AllRed > 1000){
                        Side = "NORed";
                    }
                }
                else if(beacon.getAnalysis().isLeftBlue() && beacon.getAnalysis().isRightRed()){//If red is on the right tell me red is on the right
                    RightConfidence++;
                    if(RightConfidence > 1000) {
                        Side = "Right";
                    }
                }
                else if(beacon.getAnalysis().isLeftRed() && beacon.getAnalysis().isRightBlue()){//if the left side is red tell me I want the left side
                    LeftConfidence++;
                    if(LeftConfidence > 1000){
                        Side = "Left";
                    }
                }
                else{//If none of those are true and I can't tell even though im 90% certain then tell me that
                    CantTellCounter++;
                    if(CantTellCounter > 1000) {
                        Side = "JustNO";
                    }
                }
            }
            else{//If it isnt 90% accurate tell me it cant tell after running a few times
                if(CantTellCounter > 1000){
                    Side = "CantTell";
                    telemetry.addData("Can't", "tell");
                }
                else CantTellCounter++;
            }
        }
        else if(TeamColor.equals("BLUE")){//Same thing as the red but it is looking for one side to be blue
            if (beacon.getAnalysis().isBeaconFound()){
                if(beacon.getAnalysis().isLeftBlue() && beacon.getAnalysis().isRightBlue()){
                    Side = "NOBLUE";
                }
                else if (beacon.getAnalysis().isLeftRed() && beacon.getAnalysis().isRightRed()){
                    Side = "NORED";
                }
                else if(beacon.getAnalysis().isLeftBlue() && beacon.getAnalysis().isRightRed()){
                    Side = "Left";
                }
                else if(beacon.getAnalysis().isLeftRed() && beacon.getAnalysis().isRightBlue()){
                    Side = "Right";
                }
                else{
                    Side = "JustNO";

                }
            }
            else{
                if(CantTellCounter > 1000){
                    Side = "CantTell";
                    telemetry.addData("Can't", "tell");
                }
                else CantTellCounter++;
            }

        }
        telemetry.addData("Blue Left", beacon.getAnalysis().isLeftBlue());//Printing everything that I need to see what the robot is thinking
        telemetry.addData("Blue Rigth", beacon.getAnalysis().isRightBlue());
        telemetry.addData("Red left", beacon.getAnalysis().isLeftRed());
        telemetry.addData("red right", beacon.getAnalysis().isRightRed());
        telemetry.addData("Beacon Confidence", beacon.getAnalysis().getConfidenceString());
        telemetry.addData("Side", Side);
        return Side;//Return what side the beacon is on

    }

    String driveBasedOnEncoders(double Distance, String Direction){
        String ReturnValue = "";
        if(FirstTime) {//If it is the first time running the code get the starting value
            switch (Direction){//Determines what direction I am trying to go and tries to find a motor that willl be going positive if possible
                case "Forward":
                    StartEncoder = robot.Motor1.getCurrentPosition();
                    FirstTime = false;
                    break;
                case "Back":
                    StartEncoder = robot.Motor1.getCurrentPosition();
                    FirstTime = false;
                    break;
                case "Left":
                    StartEncoder = robot.Motor1.getCurrentPosition();
                    FirstTime = false;
                    break;
                case "Right":
                    StartEncoder = robot.Motor2.getCurrentPosition();
                    FirstTime = false;
                    break;

            }
        }
        else {//If it is not the first time get how far the encoders have gone
            double CurrentEncoder = 0;
            switch (Direction){
                case "Forward":
                    CurrentEncoder = robot.Motor1.getCurrentPosition() - StartEncoder;
                    break;
                case "Back":
                    CurrentEncoder = StartEncoder - robot.Motor1.getCurrentPosition();
                    break;
                case "Left":
                    CurrentEncoder = robot.Motor1.getCurrentPosition() - StartEncoder;
                    break;
                case "Right":
                    CurrentEncoder = robot.Motor2.getCurrentPosition() - StartEncoder;
                    break;

            }
            double OneRotation = 1120;//Then turn it to actual distance
            double WheelSize = 4*Math.PI;
            DistanceTraveled = ((Math.abs(CurrentEncoder) / OneRotation) * WheelSize)*1.125;
            telemetry.addData("",CurrentEncoder);

            if (DistanceTraveled > Distance) {//If I have gone the distance I want stop moving
                moveMotorsPower(0,0,0,0);
                ReturnValue = "Done";
                FirstTime = true;
            }
            else {//Otherwise keep going
                ReturnValue = "NOTDONE";
                switch (Direction){
                    case "Forward":
                        moveMotorsPower(.3,.3,.3,.3);
                        break;
                    case "Back":
                        moveMotorsPower(-.3,-.3,-.3,-.3);
                        break;
                    case "Left":
                        moveMotorsPower(.3, -.3,.3,-.3);
                        break;
                    case "Right":
                        moveMotorsPower(-.3,.3,-.3,.3);
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

    void initCamera(){//This initializes the camera for use
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
        this.setFrameSize(new Size(1280, 720));

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
        beacon.setColorToleranceRed(-.5);
        beacon.setColorToleranceBlue(-.5);

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
        rotation.setActivityOrientationFixed(ScreenOrientation.LANDSCAPE_REVERSE);

        /**
         * Set camera control extension preferences
         *
         * Enabling manual settings will improve analysis rate and may lead to better results under
         * tested conditions. If the environment changes, expect to change these values.
         */
        cameraControl.setColorTemperature(CameraControlExtension.ColorTemperature.AUTO);
        cameraControl.setAutoExposureCompensation();
    }
    void stopCamera(){super.stop();}
}
