/* Our autonomous codes
 *
 * 1 at the end - The main autonomous that sets up at the normal spot does everything including: Launch, beacon, beacon, Cap ball
 * 2 at the end - A secondary autonomous that sets up close to the line and does: launch, Cap Ball.
 * 3 at the end - A main Autonomous that sets up at the normal spot and does: Launch, beacon, beacon, CornerVortex. NEEDS CREATING
 * 4 at the end - A tertiary Autonomous that sets as far from the corner vortex and does: launch, CornerVortex. NEEDS CREATIG
 *
 * For what is on the drivers station
 *
 * Blue- Blue side
 * Red- Red side
 * 1- Standard location
 * 2- Secondary location
 * L- Launch
 * B- Beacon
 * C- Cap Ball
 *
 *
 *
 */

package org.firstinspires.ftc.teamcode.team.Merlin1617.Merlin2_2;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

/*
 * My cases are:
 *
 * Drive Forward away from wall
 * Turn To Go to identification location
 * Drive Forward to identification location
 * Turn to identify
 * Shoot/Identify
 * Turn to identify second beacon
 * Identify
 * Drive Left till Left sensor has seen white
 * Square against wall
 * Press beacon
 * Forward drive to get close to second white line
 * Drive forward to white line
 * Press beacon
 * Drive Right to hit the cap ball
 * Spin to knock the ball off
 *
 *
 * Press beacon includes
 * if left drive back some
 * if right drive forward some
 * drive till hit
 * back up
 *
 */
@Autonomous(name = "Blue1, BBC, 0 sec", group = "Merlin2")
public class Merlin2Blue1 extends Merlin2Auto {

    @Override
    public void init() {
        super.init();
        super.initCamera();

        telemetry.addData("Say", "Hello Driver");
        telemetry.addData("Blue1", "BBC");
        telemetry.update();
        robot.navx_device.zeroYaw();



    }

    @Override
    public void init_loop() {
    }

    @Override
    public void start() {
    }
    private String CurrentCase = "GoForwardToBeAbleToTurn";
    private String CompletionClause = "";

    private String FirstBeaconSide = "";
    private String SecondBeaconSide = "";

    private double DriveForwardToBeAbleToTurnDistance = 5;

    private double AngleToDriveToFirstBeaconIdentifyingSpot = 23;

    private double DistanceToDriveForwardToBeaconIdentifyingSpot = 68;

    private double TurnToIdentifyFirstBeaconAndLaunchBalls = -129;

    private double TurnToIdentifySecondBeacon = 140.55;

    private double TurnToDriveTillSecondBeaconsWhiteLine = 115;

    private double SquareAgainstTheSecondBeacon = 178;

    private double DriveToHitTheLeftButtonOfTheSecondBeacon = 10;
    private String DriveToHitTheLeftButtonDirectionOfTheSecondBeacon = "Back";

    private double DriveToHitTheRightButtonOfTheSecondBeacon = 0;
    private String DriveToHitTheRightButtonDirectionOfTheSecondBeacon = "Back";

    private double MakeSureTheRobotIsAngledForToHitTheSecondBeacon = 175;

    private double DriveBackAfterHittingTheSecondBeacon = 15;

    private double TurnToGoToFirstBeaconLine = -165;

    private double DriveToGetCloseToTheFirstWhiteLine = 24;

    private double DriveBackToDetermineColor = 12;

    private double DriveToHitTheLeftButtonOfTheFirstBeacon = 3;
    private String DriveToHitTheLeftButtonOfTheFirstBeaconDirection = "Back";

    private double DriveToHitTheRightButtonOfTheFirstBeacon = 3;
    private String DriveToHitTheRightButtonOfTheFirstBeaconDirection = "Forward";

    private double SquareAgainstTheFirstBeacon = 178;

    private double MakeSureTheRobotIsAngledForToHitTheFirstBeacon = 178;


    private double DriveBackAndHitCapBall = 40;
    private double SpinToKnockOffCapBall = 0;

    private double DriveOntoTheCornerVortex = 40;

    @Override
    public void loop() {
        telemetry.addData("CurrentCase", CurrentCase);
        telemetry.addData("FirstBeaconSide", FirstBeaconSide);
        telemetry.addData("SecondBeaconSide", SecondBeaconSide);
        telemetry.addData("Yaw", robot.navx_device.getYaw());

        switch (CurrentCase){
            case "GoForwardToBeAbleToTurn"://First Case that drives the robot forward to a position that can launch the balls and identify the beacon
                CompletionClause = super.driveBasedOnEncoders(DriveForwardToBeAbleToTurnDistance, "Forward");
                if(CompletionClause.equals("Done")){
                    CurrentCase = "TurnToBeAbleToDriveForwardToBeaconIdentifyingSpot";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "TurnToBeAbleToDriveForwardToBeaconIdentifyingSpot":
                CompletionClause = super.turnToGyroHeading(AngleToDriveToFirstBeaconIdentifyingSpot);//The robot makes sure it is on angle for the shot.
                if(CompletionClause.equals("Done")){
                    CurrentCase = "DriveForwardToBeaconIdentifyingSpot";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "DriveForwardToBeaconIdentifyingSpot"://First Case that drives the robot forward to a position that can launch the balls and identify the beacon
                CompletionClause = super.driveBasedOnEncoders(DistanceToDriveForwardToBeaconIdentifyingSpot, "Forward");
                if(CompletionClause.equals("Done")){
                    CurrentCase = "TurnToIdentifySecondBeacon";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "TurnToIdentifySecondBeacon":
                CompletionClause = super.turnToGyroHeading(TurnToIdentifySecondBeacon);//The robot makes sure it is on angle for the shot.
                if(CompletionClause.equals("Done")){
                    CurrentCase = "IdentifySecondBeacon";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "IdentifySecondBeacon":
                SecondBeaconSide = super.choseSide("BLUE");
                if(!SecondBeaconSide.equals("")){
                    CurrentCase = "TurnToDriveTillSecondBeaconsWhiteLine";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "TurnToDriveTillSecondBeaconsWhiteLine":
                CompletionClause = super.turnToGyroHeading(TurnToDriveTillSecondBeaconsWhiteLine);//The robot makes sure it is on angle for the shot.
                if(CompletionClause.equals("Done")){
                    CurrentCase = "DriveTillSecondBeaconsWhiteLine";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "DriveTillSecondBeaconsWhiteLine":
                CompletionClause = super.driveUltilWhiteLine("Left", "Right", .3);
                telemetry.addData("Light", robot.LeftLight.getLightDetected());
                if(CompletionClause.equals("Done")){
                    CurrentCase = "SquareAgainstTheSecondBeacon";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "SquareAgainstTheSecondBeacon":
                CompletionClause = super.turnTo180Degrees();//The robot makes sure it is on angle for the shot.
                if(CompletionClause.equals("Done")){
                    switch (SecondBeaconSide){
                        case "Left":
                            CurrentCase = "DriveToHitTheLeftButtonOfTheSecondBeacon";
                            break;
                        case "Right":
                            CurrentCase = "DriveToHitTheRightButtonOfTheSecondBeacon";
                            break;
                    }
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "DriveToHitTheLeftButtonOfTheSecondBeacon"://Drive to the left so that the robot is only in front of the beacon's left button
                CompletionClause = super.driveBasedOnEncoders(DriveToHitTheLeftButtonOfTheSecondBeacon, DriveToHitTheLeftButtonDirectionOfTheSecondBeacon);
                if(CompletionClause.equals("Done")){
                    CurrentCase = "MakeSureItIsOnAngle";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "DriveToHitTheRightButtonOfTheSecondBeacon":
                CompletionClause = super.driveBasedOnEncoders(DriveToHitTheRightButtonOfTheSecondBeacon, DriveToHitTheRightButtonDirectionOfTheSecondBeacon);

                if(CompletionClause.equals("Done")){
                    CurrentCase = "MakeSureItIsOnAngle";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "MakeSureItIsOnAngle":
                CompletionClause = super.turnTo180Degrees();//The robot makes sure it is on angle for the shot.
                if(CompletionClause.equals("Done")){
                    CurrentCase = "DriveTillTheSecondBeaconIsHit";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "MakeSureTheRobotIsAngledForToHitTheSecondBeacon"://Make sure one more time the foam is parallel to the wall
                CompletionClause = super.turnTo180Degrees();
                if(CompletionClause.equals("Done")){
                    CurrentCase = "DriveTillTheSecondBeaconIsHit";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "DriveTillTheSecondBeaconIsHit"://Dive till the robot has hit the beacon
                CompletionClause = super.driveUntilHit("Red");
                if(CompletionClause.equals("Done")){
                    CurrentCase = "DriveBackAfterHittingTheSecondBeacon";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "DriveBackAfterHittingTheSecondBeacon"://Drive back after hitting the beacon
                CompletionClause = super.driveBasedOnEncoders(DriveBackAfterHittingTheSecondBeacon, "Right");
                if(CompletionClause.equals("Done")){
                    CurrentCase = "TurnToGoToFirstBeaconLine";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "TurnToGoToFirstBeaconLine":
                CompletionClause = super.turnToGyroHeading(TurnToGoToFirstBeaconLine);
                if(CompletionClause.equals("Done")){
                    CurrentCase = "DriveToGetCloseToTheFirstWhiteLine";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }

            case "DriveToGetCloseToTheFirstWhiteLine":
                CompletionClause = super.driveBasedOnEncoders(DriveToGetCloseToTheFirstWhiteLine, "Forward");
                if(CompletionClause.equals("Done")){
                    CurrentCase = "DriveToBeOnTheFirstBeaconWhiteLine";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "DriveToBeOnTheFirstBeaconWhiteLine":
                CompletionClause = super.driveUltilWhiteLine("Forward", "Left", .25);
                telemetry.addData("Light", robot.LeftLight.getLightDetected());
                if(CompletionClause.equals("Done")){
                    CurrentCase = "SquareAgainstTheFirstBeacon";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "SquareAgainstTheFirstBeacon":
                CompletionClause = super.turnTo180Degrees();//The robot makes sure it is on angle for the shot.
                if(CompletionClause.equals("Done")){
                    CurrentCase = "DriveBackToDetermineColor";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "DriveBackToDetermineColor":
                CompletionClause = super.driveBasedOnEncoders(DriveBackToDetermineColor, "Right");
                if(CompletionClause.equals("Done")){
                    CurrentCase = "DetermineFirstBeaconColor";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "DetermineFirstBeaconColor":
                FirstBeaconSide = super.choseSide("BLUE");
                if(!FirstBeaconSide.equals("")){
                    super.resetAll();
                    switch (FirstBeaconSide){
                        case "Left":
                            CurrentCase = "DriveToHitTheLeftButtonOfTheFirstBeacon";
                            break;
                        case "Right":
                            CurrentCase = "DriveToHitTheRightButtonOfTheFirstBeacon";
                            break;
                    }
                }
                break;
            case "DriveToHitTheLeftButtonOfTheFirstBeacon"://Drive to the left so that the robot is only in front of the beacon's left button
                CompletionClause = super.driveBasedOnEncoders(DriveToHitTheLeftButtonOfTheFirstBeacon, DriveToHitTheLeftButtonOfTheFirstBeaconDirection);
                if(CompletionClause.equals("Done")){
                    CurrentCase = "MakeSureTheRobotIsAngledForToHitTheFirstBeacon";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "DriveToHitTheRightButtonOfTheFirstBeacon":
                CompletionClause = super.driveBasedOnEncoders(DriveToHitTheRightButtonOfTheFirstBeacon, DriveToHitTheRightButtonOfTheFirstBeaconDirection);

                if(CompletionClause.equals("Done")){
                    CurrentCase = "MakeSureTheRobotIsAngledForToHitTheFirstBeacon";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;

            case "MakeSureTheRobotIsAngledForToHitTheFirstBeacon"://Make sure one more time the foam is parallel to the wall
                CompletionClause = super.turnToGyroHeading(MakeSureTheRobotIsAngledForToHitTheFirstBeacon);
                if(CompletionClause.equals("Done")){
                    CurrentCase = "DriveTillTheFirstBeaconIsHit";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "DriveTillTheFirstBeaconIsHit"://Dive till the robot has hit the beacon
                CompletionClause = super.driveUntilHit("Red");
                if(CompletionClause.equals("Done")){
                    CurrentCase = "DriveBackAndHitCapBall";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "DriveBackAndHitCapBall":
                CompletionClause = super.driveBasedOnEncoders(DriveBackAfterHittingTheSecondBeacon, "Right");
                if(CompletionClause.equals("Done")){
                    CompletionClause = "NOTDONE";
                    CurrentCase = "SpinToKnockOffCapBall";
                    super.resetAll();
                }
                break;
            case "SpinToKnockOffCapBall":
                CompletionClause = super.turnToGyroHeading(SpinToKnockOffCapBall);
                if(CompletionClause.equals("Done")){
                    CurrentCase = "Done";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;








            case "DriveBackAfterHittingTheFirstBeacon"://Drive back after hitting the beacon
                CompletionClause = super.driveBasedOnEncoders(DriveBackAfterHittingTheSecondBeacon, "Right");
                if(CompletionClause.equals("Done")){
                    CompletionClause = "NOTDONE";
                    CurrentCase = "DriveOntoTheCornerVortex";
                            super.resetAll();
                }
                break;
            case "DriveOntoTheCornerVortex":
                CompletionClause = super.driveBasedOnEncoders(DriveOntoTheCornerVortex, "Forward");
                if(CompletionClause.equals("Done")){
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
