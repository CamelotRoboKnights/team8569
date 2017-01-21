/* Our autonomous codes
 *
 * 1 at the end - The main autonomous that sets up at the normal spot does everything including: Launch, beacon, beacon, Cap ball
 * 2 at the end - A secondary autonomous that sets up close to the line and does: launch, Cap Ball.
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

package org.firstinspires.ftc.teamcode.team.Merlin2_2;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.team.Merlin2_2.Merlin2Auto;

@Autonomous(name = "Blue2, LC", group = "Merlin2")
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
    @Override
    public void loop() {
        telemetry.addData("CurrentCase", CurrentCase);

        switch (CurrentCase){
            case "GoFrowardAwayFromWall":
                CompletionClause = super.driveBasedOnEncoders(5, "Forward");
                if(CompletionClause.equals("Done")){
                    CurrentCase = "TurnToShoot";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "TurnToShoot":
                CompletionClause = super.turnToGyroHeading(45);//The robot makes sure it is on angle for the shot.
                if(CompletionClause.equals("Done")){
                    CurrentCase = "ShootFirstBall";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;

            case "GoForwardBeforeShoot"://First Case that drives the robot forward to a position that can launch the balls and identify the beacon
                CompletionClause = super.driveBasedOnEncoders(20, "Forward");
                if(CompletionClause.equals("Done")){
                    CurrentCase = "MakeSureItIsOnAngle";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "MakeSureItIsOnAngle":
                CompletionClause = super.turnToGyroHeading(45);//The robot makes sure it is on angle for the shot.
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
            case "HitTheCapBall":
                CompletionClause = super.driveBasedOnEncoders(20, "Forward");
                if(CompletionClause.equals("Done")){
                    CurrentCase = "MakeSureItIsOnAngle";
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
