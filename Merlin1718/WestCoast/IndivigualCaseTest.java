package org.firstinspires.ftc.teamcode.team.Merlin1718.WestCoast;





import android.hardware.TriggerEventListener;

import com.qualcomm.robotcore.eventloop.SyncdDevice;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import java.sql.Driver;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuMarkInstanceId;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

import java.io.StringWriter;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

@Autonomous(name = "Case Test", group = "Cardinal")
//@Disabled //Uncomment this if it is not wanted on the phone
public class IndivigualCaseTest extends OpMode {

    public WestCoastHardware robot = new WestCoastHardware();//The hardware map needs to be the hardware map of the robot we are using

    public void init(){//This only runs once
        robot.init(hardwareMap);
        robot.motoG.initCamera();
        robot.glyphCollector.topGrasper.close();
        robot.glyphCollector.bottomGrasper.open();
        ;//Initializing everything needed
    }
    @Override
    public void init_loop(){//Tis runs many time during the init phase
    }
    @Override
    public void start(){}//This runs when the start button is pressed
    // distance of variables


    double versionNumber = 50;


    private String color = "red";
    private double driveForwardToKnockDistance;
    private double driveBackToKnockDistance;
    private double spinRightToKnockOffRightJewel = 7;
    private double spinLeftToKnockOffLeftJewel  = -7;
    private double driveDistanceToRightColumn = 24;//
    private double driveDistanceToCenterColumn = 10000;// 30
    private double driveDistanceToLeftColumn = 36;//36
    private double driveForwardToCryptobox = 10;
    private double driveAwayFromCryptobox = 5;//2



    private String currentCase = "ToCenterColumn";
    private boolean redJewel;
    private String column = "null";
    @Override
    public void loop(){//This runs while opmode is active
        telemetry.addData("Version Number: ", versionNumber);
        telemetry.addData("CurrentCase", currentCase);
        telemetry.addData("jewel", redJewel);
        telemetry.addData("colum", column);
        switch (currentCase){
            case "DropSorter":
                boolean doneYet;
                doneYet = robot.jewelSorter.lower();
                if(doneYet){
                    currentCase = "IDCryptographPicAndJewelColor";
                }
                break;
            case "IDCryptographPicAndJewelColor":
                redJewel = robot.jewelSorter.isRed();
                column = robot.motoG.key();
                if(!column.equals("null")){
                    if(redJewel){
                        currentCase = "SpinRightToKnockOffRightJewel";
                    }
                    else {
                        currentCase = "SpinLeftToKnockOffLeftJewel";
                    }
                }
                break;
            case "SpinRightToKnockOffRightJewel":
                doneYet = robot.westCoast.turnToGyroHeading(spinRightToKnockOffRightJewel, robot.navx.getCurrentOrientation());
                if(doneYet){
                    currentCase = "RaiseSorter";
                }
                break;
            case "SpinLeftToKnockOffLeftJewel":
                doneYet = robot.westCoast.turnToGyroHeading(spinLeftToKnockOffLeftJewel, robot.navx.getCurrentOrientation());
                if(doneYet){
                    currentCase = "RaiseSorter";
                }
                break;
            //raise sorter
            case "RaiseSorter":
                doneYet = robot.jewelSorter.raise();
                if(doneYet){
                    currentCase = "SpinBackToStartingPosition";
                }
                break;
            case "SpinBackToStartingPosition":
                doneYet = robot.westCoast.turnToGyroHeading(0, robot.navx.getCurrentOrientation());
                if(doneYet){
                    currentCase = choseColumnCase(column);
                }
                break;

            case "ToRightColumn":
                doneYet = robot.westCoast.driveBasedOnEncoders(driveDistanceToRightColumn, 1);
                if(doneYet){
                    currentCase = "SpinTo90";
                }
                break;

            case "ToCenterColumn":
                doneYet = robot.westCoast.driveBasedOnEncoders(driveDistanceToCenterColumn, 1);
                if(doneYet){
                    currentCase = "SpinTo90";
                    currentCase = "Done";
                }
                break;

            case "ToLeftColumn":
                doneYet = robot.westCoast.driveBasedOnEncoders(driveDistanceToLeftColumn, 1);
                if(doneYet){
                    currentCase = "SpinTo90";
                }
                break;

            case "SpinTo90":
                doneYet = robot.westCoast.turnToGyroHeading(90, robot.navx.currentOrientation);
                if(doneYet){
                    currentCase = "DriveForward";
                }
                break;

            case "DriveForward":
                doneYet = robot.westCoast.driveBasedOnEncoders(driveForwardToCryptobox, 1);
                if(doneYet){
                    currentCase = "ReleaseGripper";
                }
                break;
            case "ReleaseGripper":
                robot.glyphCollector.topGrasper.open();
                doneYet = true;
                if(doneYet){
                    currentCase = "DriveForward2";
                }
                break;
            //drive forward to make shure glyph is in collumn
            case "DriveForward2":
                doneYet = robot.westCoast.driveBasedOnEncoders(2, 1);
                if(doneYet){
                    currentCase = "DriveBack";
                }
                break;
            //back up
            case "DriveBack":
                doneYet = robot.westCoast.driveBasedOnEncoders(driveAwayFromCryptobox, -1);
                if(doneYet){
                    currentCase = "End";
                }
                break;
            case "End":
                robot.westCoast.drive(0,0);
                break;
            default:
                robot.westCoast.drive(0,0);
                telemetry.addData("What Are You", " Doing");
                telemetry.update();
        }
    }
    @Override
    public void stop(){}








    private String choseColumnCase(String column){
        switch (column){
            case "RIGHT":
                return "ToRightColumn";
            case "CENTER":
                return "ToCenterColumn";
            case "LEFT":
                return "ToLeftColumn";
            default:
                telemetry.addData("Not Left Right Or", " Center");
                return "";
        }
    }

}
