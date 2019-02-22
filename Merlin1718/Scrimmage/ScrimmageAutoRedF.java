package org.firstinspires.ftc.teamcode.team.Merlin1718.Scrimmage;

import android.hardware.TriggerEventListener;

import com.qualcomm.robotcore.eventloop.SyncdDevice;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import java.sql.Driver;

@Disabled
@Autonomous(name = "RedF", group = "Scrimmage")
public class ScrimmageAutoRedF extends ScrimmageMeathods {

    public void init(){//This only runs once
        super.init();//Initializing everything needed
    }
    @Override
    public void init_loop(){//Tis runs many time during the init phase
    }
    @Override
    public void start(){}//This runs when the start button is pressed

    private String color = "red";
    private double spinRightToKnockOffRightJewel = -7;
    private double spinLeftToKnockOffLeftJewel  = 7;
    private double driveDistanceForwardOffBalencePad = 20;
    private double driveDistanceToRightColumn = 5; // 3
    private double driveDistanceToCenterColumn = 11; // 8
    private double driveDistanceToLeftColumn = 14;
    private double driveForwardToCryptobox = 10;
    private double driveAwayFromCryptobox = 5;

    /* DropSorter
     * IDCryptographPicAndJewelColor
     * SpinRightToKnockOffRightJewel or SpinLeftToKnockOffLeftJewel
     * RaiseSorter
     * SpinBackToStartingPosition
     * ForwardOffBalancePad
     * DriveLeftToRightColumn
     * DriveLeftToCenterColumn
     * DriveLeftToLeftColumn
     * DriveForward
     * ReleaseGrasper
     * DriveForward2
     * DriveBack
     * End
     */

    private String currentCase = "DropSorter";
    private String jewel = "null";
    private String column = "null";
    @Override
    public void loop(){//This runs while opmode is active
        telemetry.addData("CurrentCase", currentCase);
        telemetry.addData("jewel", jewel);
        telemetry.addData("colum", column);
        switch (currentCase){
            case "DropSorter":
                boolean doneYet;
                doneYet = sorter("down");
                glyphAuto("close");
                if(doneYet){
                    currentCase = "IDCryptographPicAndJewelColor";
                }
                break;
            case "IDCryptographPicAndJewelColor":
                jewel = super.jewelColor();
                column = super.key();
                if(!jewel.equals("null") && !column.equals("null")){
                    if(jewel.equals("red")){
                        currentCase = "SpinRightToKnockOffRightJewel";
                    }
                    else {
                        currentCase = "SpinLeftToKnockOffLeftJewel";
                    }
                }
                break;
            case "SpinRightToKnockOffRightJewel":
                doneYet = turnToGyroHeading(spinRightToKnockOffRightJewel, revOrientation());
                if(doneYet){
                    currentCase = "RaiseSorter";
                }
                break;
            case "SpinLeftToKnockOffLeftJewel":
                doneYet = turnToGyroHeading(spinLeftToKnockOffLeftJewel, revOrientation());
                if(doneYet){
                    currentCase = "RaiseSorter";
                }
                break;
            case "RaiseSorter":
                doneYet = sorter("up");
                if(doneYet){
                    currentCase = "SpinBackToStartingPosition";
                }
                break;
            case "SpinBackToStartingPosition":
                doneYet = turnToGyroHeading(0, revOrientation());
                if(doneYet){
                    currentCase = "ForwardOffBalancePad";
                }
                break;
            case "ForwardOffBalancePad":
                doneYet = driveBasedOnEncoders(driveDistanceForwardOffBalencePad, "Forward");
                if(doneYet){
                    currentCase = "SpinBackToStartingPosition2";
                }
                break;
            case "SpinBackToStartingPosition2":
                doneYet = turnToGyroHeading(0, revOrientation());
                if(doneYet){
                    currentCase = choseColumnCase(column);

                }
                break;
            case "DriveLeftToRightColumn":
                doneYet = driveBasedOnEncoders(driveDistanceToRightColumn, "Left");
                if(doneYet){
                    currentCase = "DriveForward";
                }
                break;
            case "DriveLeftToCenterColumn":
                doneYet = driveBasedOnEncoders(driveDistanceToCenterColumn, "Left");
                if(doneYet){
                    currentCase = "DriveForward";
                }
                break;
            case "DriveLeftToLeftColumn":
                doneYet = driveBasedOnEncoders(driveDistanceToLeftColumn, "Left");
                if(doneYet){
                    currentCase = "DriveForward";
                }
                break;
            case "DriveForward":
                doneYet = driveBasedOnEncoders(driveForwardToCryptobox, "Forward");
                if(doneYet){
                    currentCase = "ReleaseGripper";
                }
                break;
            case "ReleaseGripper":
                doneYet = glyphAuto("open");
                if(doneYet){
                    currentCase = "DriveBack";
                }
                break;
            case "DriveBack":
                doneYet = driveBasedOnEncoders(driveAwayFromCryptobox, "Back");
                if(doneYet){
                    currentCase = "DriveForward2";
                }
                break;
            case "DriveForward2":
                doneYet = driveBasedOnEncoders(driveAwayFromCryptobox + 2, "Forward");
                if(doneYet){
                    currentCase = "DriveBack2";
                }
                break;
            case "DriveBack2":
                doneYet = driveBasedOnEncoders(driveAwayFromCryptobox, "Back");
                if(doneYet){
                    currentCase = "End";
                }
                break;
            case "End":
                moveMotorsPower(0,0,0,0);
                break;
            default:
                moveMotorsPower(0,0,0,0);
                telemetry.addData("What Are You", " Doing");
                telemetry.update();
        }
    }
    @Override
    public void stop(){}








    private String choseColumnCase(String column){
        switch (column){
            case "RIGHT":
                return "DriveLeftToRightColumn";
            case "CENTER":
                return "DriveLeftToCenterColumn";
            case "LEFT":
                return "DriveLeftToLeftColumn";
            default:
                telemetry.addData("Not Left Right Or", " Center");
                return "";
        }
    }

}
