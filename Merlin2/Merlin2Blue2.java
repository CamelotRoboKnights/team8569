package org.firstinspires.ftc.teamcode.team.Merlin2;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
/*
 * My cases are:
 *
 * Drive Forward
 * Launch
 * Load
 * Launch
 * Identify
 * Turn to first beacon
 * Go to white line going forward till right sensor
 * Turn to 0
 *
 * if Right drive left till hit
 * Back Up
 *
 * if Left Go to white line left till right sensor
 * Drive forward Till hit
 * Back Up
 * Go left Till left Light
 *
 * Go left Till Left right
 * Back up
 * Identify Beacon
 *
 * If right Drive till hit
 * Back Up
 * Turn to Ball
 *
 *
 * if Left drive Right
 * Drive Till hit
 * Back up
 * Turn to ball
 *
 * Drive to ball
 */
@Autonomous(name = "Blue2, BBC", group = "Merlin2")
public class Merlin2Blue2 extends Merlin2Auto {

    @Override
    public void init() {
        super.init();
        super.initCamera();

        telemetry.addData("Say", "Hello Driver");    //
        robot.navx_device.zeroYaw();



    }

    @Override
    public void init_loop() {
    }

    @Override
    public void start() {
    }
    private String CurrentCase = "GoForwardBeforeShoot";
    private String CompletionClause = "";
    private String BeaconSide = "";
    @Override
    public void loop() {
        telemetry.addData("CurrentCase", CurrentCase);
        telemetry.addData("Side", BeaconSide);

        switch (CurrentCase){
            case "GoForwardBeforeShoot"://First Case that drives the robot forward to a position that can launch the balls and identify the beacon
                CompletionClause = super.driveBasedOnEncoders(20, "Forward");
                if(CompletionClause.equals("Done")){
                    CurrentCase = "AngleToDetermineBeacon";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "MakeSureItIsOnAngle":
                CompletionClause = super.turnToGyroHeading(0);//The robot makes sure it is on angle for the shot.
                if(CompletionClause.equals("Done")){
                    CurrentCase = "ShootFirstBall";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "ShootFirstBall"://Shoot the first ball to try to make it in the hoop
                CompletionClause = super.launchBall();
                if(CompletionClause.equals("Done")){
                    CurrentCase = "LoadSecondBall";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "LoadSecondBall"://Load the second ball in the flipper
                CompletionClause = super.loadBall();
                if(CompletionClause.equals("Done")){
                    CurrentCase = "ShootSecondBall";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "ShootSecondBall"://Shoot the second ball into the hoop
                CompletionClause = super.launchBall();
                if(CompletionClause.equals("Done")){
                    CurrentCase = "AngleToDetermineBeacon";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "AngleToDetermineBeacon"://Goes to a 10 degree angle because that was found to measure beacon color best
                CompletionClause = super.turnToGyroHeading(170);//CodeWorks till here
                if(CompletionClause.equals("Done")){
                    CurrentCase = "TryToIdentifyBeaconTakeOne";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "TryToIdentifyBeaconTakeOne"://Try to identify the first beaconn
                BeaconSide = super.choseSide("BLUE");
                if(!BeaconSide.equals("")){
                    CurrentCase = "FirstTurnToBeacon";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "FirstTurnToBeacon"://Turn to the first white line in front of the first beacon
                CompletionClause = super.turnToGyroHeading(-120);
                if(CompletionClause.equals("Done")){
                    CurrentCase = "GoToTheWhiteLineAfterShoot";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "GoToTheWhiteLineAfterShoot"://Go to the white line in front of the beacon
                CompletionClause = super.driveUltilWhiteLine("Back", "Left", .4);
                telemetry.addData("Light", robot.LeftLight.getLightDetected());
                if(CompletionClause.equals("Done")){
                    CurrentCase = "TrunTo90DegreesForTheFirstTime";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "TrunTo90DegreesForTheFirstTime"://Turn so the button press wall is square after that if the beacon was decided to be on the left drive left otherwise just go for the hit
                CompletionClause = super.turnToGyroHeading(-179);
                if(CompletionClause.equals("Done")){
                    if(BeaconSide.equals("Left")){
                        CurrentCase = "DriveLeft12inToHitFirstBeacon";
                        CompletionClause = "NOTDONE";
                        super.resetAll();
                    }
                    else if (BeaconSide.equals("Right")){
                        CurrentCase = "DriveLeftForTheRightButton";
                        CompletionClause = "NOTDONE";
                        super.resetAll();
                    }
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "DriveLeft12inToHitFirstBeacon"://Drive to the left so that the robot is only in front of the beacon's left button
                CompletionClause = super.driveBasedOnEncoders(1, "Forward");
                if(CompletionClause.equals("Done")){
                    CurrentCase = "MakeSureTheRobotIsAngledForTheHitOfTheFirstBeacon";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "DriveLeftForTheRightButton":
                CompletionClause = super.driveBasedOnEncoders(8, "Forward");
                if(CompletionClause.equals("Done")){
                    CurrentCase = "MakeSureTheRobotIsAngledForTheHitOfTheFirstBeacon";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;

            case "MakeSureTheRobotIsAngledForTheHitOfTheFirstBeacon"://Make sure one more time the foam is parallel to the wall
                CompletionClause = super.turnToGyroHeading(178);
                if(CompletionClause.equals("Done")){
                    CurrentCase = "DriveTillTheFirstBeaconIsHit";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "DriveTillTheFirstBeaconIsHit"://Dive till the robot has hit the beacon
                CompletionClause = super.driveUntilHit("Red");
                if(CompletionClause.equals("Done")){
                    CurrentCase = "DriveBackAfterHittingTheFirstBeacon";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "DriveBackAfterHittingTheFirstBeacon"://Drive back after hitting the beacon
                CompletionClause = super.driveBasedOnEncoders(16, "Right");
                if(CompletionClause.equals("Done")){
                    if(BeaconSide.equals("Right")){
                        CurrentCase = "CrossOverTheLeftLight";
                        BeaconSide = "";
                    }
                    else{
                        CurrentCase = "GoToTheSecondBeaconsLine";
                        BeaconSide = "";

                    }
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "CrossOverTheLeftLight"://Cross over the left light to make sure that line doesn't trigger the code
                CompletionClause = super.driveBasedOnEncoders(30, "Back");
                if(CompletionClause.equals("Done")){
                    CurrentCase = "GoToTheSecondBeaconsLine";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "GoToTheSecondBeaconsLine"://Dive till the in front of the second beacon
                CompletionClause = super.driveUltilWhiteLine("Back", "Left", .3);
                telemetry.addData("Ligth", robot.LeftLight.getLightDetected());
                if(CompletionClause.equals("Done")){
                    CurrentCase = "DriveBack12inToDetermineTheSecondBeaconColor";
                    //CurrentCase = "Done";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "DriveBack12inToDetermineTheSecondBeaconColor"://Drive back to a good distance to identify the beacon
                CompletionClause = super.driveBasedOnEncoders(28, "Right");
                if(CompletionClause.equals("Done")){
                    CurrentCase = "DetermineSecondBeaconColor";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "DetermineSecondBeaconColor"://Figure out the second beacon color and if it is on the left drive left otherwise go to hit the right button
                BeaconSide = super.choseSide("RED");
                if(!BeaconSide.equals("")){
                    if(BeaconSide.equals("Right")){
                        CurrentCase = "DriveRight12inToHitSecondBeacon";
                        CompletionClause = "NOTDONE";
                        super.resetAll();
                    }
                    else if (BeaconSide.equals("Left")){
                        CurrentCase = "MakeSureTheRobotIsAngledForTheHitOfTheSecondBeacon";
                        CompletionClause = "NOTDONE";
                        super.resetAll();
                    }
                }
                break;
            case "DriveRight12inToHitSecondBeacon"://Drive left so that the robot is in front of the beacon's second button
                CompletionClause = super.driveBasedOnEncoders(24, "Forward");
                if(CompletionClause.equals("Done")){
                    CurrentCase = "MakeSureTheRobotIsAngledForTheHitOfTheSecondBeacon";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "MakeSureTheRobotIsAngledForTheHitOfTheSecondBeacon"://Make sure the robot is angled properly
                CompletionClause = super.turnToGyroHeading(0);
                if(CompletionClause.equals("Done")){
                    CurrentCase = "DriveTillTheSecondBeaconIsHit";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "DriveTillTheSecondBeaconIsHit"://Drives till the button has been pressed
                CompletionClause = super.driveUntilHit("Blue");
                if(CompletionClause.equals("Done")){
                    CurrentCase = "DriveBackAfterHittingTheSecondBeacon";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "DriveBackAfterHittingTheSecondBeacon"://Drives back and if it is on the left side it goes to turn one amount otherwise it goes to turn a different amount
                CompletionClause = super.driveBasedOnEncoders(18, "Right");
                if(CompletionClause.equals("Done")){
                    if(BeaconSide.equals("Left")){
                        CurrentCase = "TurnToHitBallLeft";
                        BeaconSide = "";
                    }
                    else{
                        CurrentCase = "TurnToHitBallRight";
                        BeaconSide = "";

                    }
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "TurnToHitBallLeft"://Turn so that from the left side the robot is lined to hit the cap ball
                CompletionClause = super.turnToGyroHeading(140);
                if(CompletionClause.equals("Done")){
                    CurrentCase = "HitTheBall";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "TurnToHitBallRight"://Turn so that from the left side the robot is lined to hit the cap ball
                CompletionClause = super.turnToGyroHeading(-225);
                if(CompletionClause.equals("Done")){
                    CurrentCase = "HitTheBall";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "HitTheBall"://Drive forward to hit the cap ball
                CompletionClause = super.driveBasedOnEncoders(80, "Right");
                if(CompletionClause.equals("Done")){
                    CurrentCase = "Done";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;

            case"Done":
                super.done();
                break;
            default:
                super.broken();
                break;
        }
    }

    @Override
    public void stop() {
        super.stopCamera();
    }



}
