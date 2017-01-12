package org.firstinspires.ftc.teamcode.team.Merlin2;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
/*Our cases for our standard RED autonomous
*
* Forward for 30in
* Shoot ball
* load ball
* shot ball
* turn to -45 degrees
* Identify color
* Go forward to white line
* turn to -90 degrees
*
* If not identified
* Drive Back 12 in
* Identify Color
*
* If color is Identified
*
* If left
* Drive left 12in
* turn to -90 degrees
* Drive forward till contact
* drive back 6in
*
* If right drive right 12 Inches
* turn to -90 degrees
* Drive forward till contact
* drive back 6in
* Cross over left light
*
*
* Drive right till right light sensor
*
* Drive Back 12 in
* Identify Color
*
* If color is Identified
*
* If left
* Drive left 12in
* turn to -90 degrees
* Drive forward till contact
* drive back 6in
*
* If right drive right 12 Inches
* turn to -90 degrees
* Drive forward till contact
* drive back 6in
* Cross over left light
*
* Turn to 45 degrees
* Drive 80 inches
*
*
*
*
*
* */

@Autonomous(name = "Merlin2Red1", group = "Merlin2")
public class Merlin2Red1 extends Merlin2Auto {

    @Override
    public void init() {

        robot.init(hardwareMap);
        //super.initCamera();

        telemetry.addData("Say", "Hello Driver");    //
        robot.navx_device.zeroYaw();

        

    }

    @Override
    public void init_loop() {
    }

    @Override
    public void start() {
    }
    String CurrentCase = "GoForwardBeforeShoot";
    String CompletionClause = "";
    String BeaconSide = "";
    @Override
    public void loop() {
        telemetry.addData("CurrentCase", CurrentCase);
        telemetry.addData("Side", BeaconSide);
        switch (CurrentCase){
            case "GoForwardBeforeShoot":
                CompletionClause = super.driveBasedOnEncoders(10, "Forward");
                if(CompletionClause.equals("Done")){
                    CurrentCase = "MakeSureItIsOnAngle";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "MakeSureItIsOnAngle":
                CompletionClause = super.turnToGyroHeading(0);//CodeWorks till here
                if(CompletionClause.equals("Done")){
                    CurrentCase = "ShootFirstBall";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "ShootFirstBall":
                CompletionClause = super.launchBall();
                if(CompletionClause.equals("Done")){
                    CurrentCase = "LoadSecondBall";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "LoadSecondBall":
                CompletionClause = super.loadBall();
                if(CompletionClause.equals("Done")){
                    CurrentCase = "ShootSecondBall";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "ShootSecondBall":
                CompletionClause = super.launchBall();
                if(CompletionClause.equals("Done")){
                    CurrentCase = "TryToIdentifyBeaconTakeOne";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "TryToIdentifyBeaconTakeOne":
                BeaconSide = super.choseSide("RED");
                if(!BeaconSide.equals("")){
                    CurrentCase = "FirstTurnToBeacon";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "FirstTurnToBeacon":
                CompletionClause = super.turnToGyroHeading(-22);//CodeWorks till here
                if(CompletionClause.equals("Done")){
                    CurrentCase = "GoToTheWhiteLineAfterShoot";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "GoToTheWhiteLineAfterShoot":
                CompletionClause = super.driveUltilWhiteLine("Forward", "Right", .4);
                telemetry.addData("Ligth", robot.LeftLight.getLightDetected());
                if(CompletionClause.equals("Done")){
                    CurrentCase = "TrunTo90DegreesForTheFirstTime";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "TrunTo90DegreesForTheFirstTime":
                CompletionClause = super.turnToGyroHeading(0);
                if(CompletionClause.equals("Done")){
                    if(BeaconSide.equals("Left")){
                        CurrentCase = "DriveLeft12inToHitFirstBeacon";
                        CompletionClause = "NOTDONE";
                        super.resetAll();
                    }
                    else if (BeaconSide.equals("Right")){
                        CurrentCase = "DriveRight12inToHitFirstBeacon";
                        CompletionClause = "NOTDONE";
                        super.resetAll();
                    }
                    else {
                        CurrentCase = "DriveBack12inToDetermineBeaconColor";
                        CompletionClause = "NOTDONE";
                        super.resetAll();
                    }
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "DriveBack12inToDetermineBeaconColor":
                BeaconSide = super.choseSide("RED");
                if(!BeaconSide.equals("")){
                    if(BeaconSide.equals("Left")){
                        CurrentCase = "DriveLeft12inToHitFirstBeacon";
                        CompletionClause = "NOTDONE";
                        super.resetAll();
                    }
                    else if (BeaconSide.equals("Right")){
                        CurrentCase = "DriveRight12inToHitFirstBeacon";
                        CompletionClause = "NOTDONE";
                        super.resetAll();
                    }
                }
                break;
            case "DriveLeft12inToHitFirstBeacon":
                CompletionClause = super.driveBasedOnEncoders(12, "Left");
                if(CompletionClause.equals("Done")){
                    CurrentCase = "MakeSureTheRobotIsAngledForTheHitOfTheFirstBeacon";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "DriveRight12inToHitFirstBeacon":
                CompletionClause = super.driveBasedOnEncoders(12, "Right");
                if(CompletionClause.equals("Done")){
                    CurrentCase = "MakeSureTheRobotIsAngledForTheHitOfTheFirstBeacon";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "MakeSureTheRobotIsAngledForTheHitOfTheFirstBeacon":
                CompletionClause = super.turnToGyroHeading(-90);
                if(CompletionClause.equals("Done")){
                    CurrentCase = "DriveTillTheFirstBeaconIsHit";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "DriveTillTheFirstBeaconIsHit":
                CompletionClause = super.driveUntilHit();
                if(CompletionClause.equals("Done")){
                    CurrentCase = "DriveBackAfterHittingTheFirstBeacon";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "DriveBackAfterHittingTheFirstBeacon":
                CompletionClause = super.driveBasedOnEncoders(6, "Back");
                if(CompletionClause.equals("Done")){
                    if(BeaconSide.equals("Left")){
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
            case "CrossOverTheLeftLight":
                CompletionClause = super.crossLeftLight();
                if(CompletionClause.equals("Done")){
                    CurrentCase = "DriveBackAfterHittingTheFirstBeacon";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "GoToTheSecondBeaconsLine":
                CompletionClause = super.driveUltilWhiteLine("Right", "Right", .3);
                if(CompletionClause.equals("Done")){
                    CurrentCase = "DriveBack12inToDetermineTheSecondBeaconColor";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "DriveBack12inToDetermineTheSecondBeaconColor":
                BeaconSide = super.choseSide("RED");
                if(!BeaconSide.equals("")){
                    if(BeaconSide.equals("Left")){
                        CurrentCase = "DriveLeft12inToHitSecondBeacon";
                        CompletionClause = "NOTDONE";
                        super.resetAll();
                    }
                    else if (BeaconSide.equals("Right")){
                        CurrentCase = "DriveRight12inToHitSecondBeacon";
                        CompletionClause = "NOTDONE";
                        super.resetAll();
                    }
                }
                break;
            case "DriveLeft12inToHitSecondBeacon":
                CompletionClause = super.driveBasedOnEncoders(12, "Left");
                if(CompletionClause.equals("Done")){
                    CurrentCase = "MakeSureTheRobotIsAngledForTheHitOfTheSecondBeacon";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "DriveRight12inToHitSecondBeacon":
                CompletionClause = super.driveBasedOnEncoders(12, "Right");
                if(CompletionClause.equals("Done")){
                    CurrentCase = "MakeSureTheRobotIsAngledForTheHitOfTheSecondBeacon";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "MakeSureTheRobotIsAngledForTheHitOfTheSecondBeacon":
                CompletionClause = super.turnToGyroHeading(-90);
                if(CompletionClause.equals("Done")){
                    CurrentCase = "DriveTillTheSecondBeaconIsHit";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "DriveTillTheSecondBeaconIsHit":
                CompletionClause = super.driveUntilHit();
                if(CompletionClause.equals("Done")){
                    CurrentCase = "DriveBackAfterHittingTheSecondBeacon";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "DriveBackAfterHittingTheSecondBeacon":
                CompletionClause = super.driveBasedOnEncoders(6, "Back");
                if(CompletionClause.equals("Done")){
                    if(BeaconSide.equals("Left")){
                        CurrentCase = "CrossOverTheLeftLightForTheSecondTime";
                        BeaconSide = "";
                    }
                    else{
                        CurrentCase = "TurnToHitTheBall";
                        BeaconSide = "";

                    }
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "CrossOverTheLeftLightForTheSecondTime":
                CompletionClause = super.crossLeftLight();
                if(CompletionClause.equals("Done")){
                    CurrentCase = "TurnToHitTheBall";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "TurnToHitTheBall":
                CompletionClause = super.turnToGyroHeading(45);
                if(CompletionClause.equals("Done")){
                    CurrentCase = "HitTheBall";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "HitTheBall":
                CompletionClause = super.driveBasedOnEncoders(80, "Forward");
                if(CompletionClause.equals("Done")){
                    CurrentCase = "HitTheBall";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;

            case"Done":
                super.done();
                break;
            default:
                super.broken();
        }
    }

    @Override
    public void stop() {
    super.stopCamera();
    }



}
